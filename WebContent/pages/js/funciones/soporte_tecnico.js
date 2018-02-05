
$(document).ready(function(){
		restringir();
        buscarIncidencia();
		loadIncidencias();
		cargarTipoServicios();
		cargarTipoEstado();
		cargarTipoEquipo();
		cargarTipoAtencion();
		cargarMarca();
		cargarModelo()
});

function restringir(){	
	$.ajax({
	    data: {accion:"recuperarIdRol"},
	    type: "GET",
	    dataType: "json",
	    url:"./../Usuario.do"
	})
	 .done(function( json, textStatus, jqXHR ) { 
		 var rol = json.idRol[0];
		 switch(rol){
		 case "2": 
			 $('#g_usuario').hide();
			 $('#gestionUsuarios').hide();
			 $('#soporte_tecnico').hide();
			 $('#soporteTecnico').hide();
			 $('#g_equipo').hide();
			 $('#gestionEquipo').hide();
			 break;
		 case "3": 
			 $('#g_usuario').hide();
			 $('#gestionUsuarios').hide();
			 $('#centro_llamadas').hide();
			 $('#centroLlamadas').hide();
			 break;
		 }
		 if(rol!="3" && rol!="1"){
			 window.location.href = "menu.jsp";
		 }
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}

/////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////BUSCAR POR FECHA/////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

function buscarIncidencia(){
    
    var now = new Date();
	var month = (now.getMonth() + 1);               
	var day = now.getDate();
	//var day1 = (now.getDate()+1);
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
    var fe = now.getFullYear() + '-' + month + '-' + day;
    var today = now.getFullYear() + '-' + month + '-' + day + 'T'+now.getHours()+':'+now.getMinutes();
    //var today1 = now.getFullYear() + '-' + month + '-' + day1;
    var fecha = today.replace("T"," ");
    $('#mFechaInicio').val(fe);
    $('#mFechaFin').val(fe);
    $('#mFechaTerm').val(today);
}


/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////TABLA DE ATENCION////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
	
function loadIncidencias(){
	var fInicio=$('#mFechaInicio').val();
	var fFin=$('#mFechaFin').val();
	//alert(fInicio);
	$.ajax({
	    data: {accion:"recuperarPorFechasInciAte",fInicio:fInicio,fFin:fFin},
	    type: "GET",
	    dataType: "json",
	    url:"./../Incidencia.do"
	})
	 .done(function( json, textStatus, jqXHR ) {
		var btn;
		var color;
	    var dataSet=''					      
	 	var fil;
	  	for (i in json.incidencias ){						  		
	  		btn='\"<button type=\'button\' id=\'btnAtender\' onClick=\'cargarEstado('+json.incidencias[i].idIncidencia+','+json.incidencias[i].numIncidencia+')\' title=\'Atender\''+'><i class=\'fa fa-paper-plane fa-lg\'></i></button>\ <button type=\'button\' id=\'btnEditar\' onClick=\'cargarAtenciones('+json.incidencias[i].idIncidencia+','+json.incidencias[i].anioIncidencia+','+json.incidencias[i].numIncidencia+')\' title=\'Editar Atencion\''+'><i class=\'fa fa-edit fa-lg\'></i></button>\ <button type=\'button\' id=\'btnAnular\' onClick=\'fncLlenarAnulacion('+json.incidencias[i].idIncidencia+','+json.incidencias[i].numIncidencia+','+json.incidencias[i].anioIncidencia+')\' title=\'Eliminar Atencion\''+'><i class=\'fa fa-trash fa-lg\' ></i></button>\ <form name=\'form1\' method=\'get\' action=\'../Atencion.do\' target=\'_black\'><input type=\'hidden\' name=\'accion\' value=\'pdf\'><input type=\'hidden\' name=\'mIdIncidencia\' value=\''+json.incidencias[i].idIncidencia+'\'><button type=\'submit\' title=\'PDF\''+'><i class=\'fa fa-file-pdf-o fa-lg\'></i></button>\ </form>\"';
	  		
	  		switch(json.incidencias[i].estado){
	  		case "RESUELTO": json.incidencias[i].estado="<p class='text-success'><b>"+json.incidencias[i].estado+"</b></p>";
	  		break;
	  		case "RESUELTO POR DISCOVERY": json.incidencias[i].estado="<p class='text-success'><b>"+json.incidencias[i].estado+"</b></p>";
	  		break;
	  		case "": json.incidencias[i].estado="<p class='text-danger'><b>No Atendido</b></p>";
	  		break;
	  		case "PENDIENTE POR GARANTIA": json.incidencias[i].estado="<p class='text-warning'><b>"+json.incidencias[i].estado+"</b></p>";
	  		break;
	  		case "PENDIENTE POR REPUESTO": json.incidencias[i].estado="<p class='text-warning'><b>"+json.incidencias[i].estado+"</b></p>";
	  		break;
	  		}
	  		
	  		fil = '{"idIncidencia":"'+json.incidencias[i].idIncidencia+'"'+
	    	  		',"numIncidencia":"'+json.incidencias[i].numIncidencia+'"'+						    	  		
	    	  		',"fechaIng":"'+json.incidencias[i].auxFecha+'"'+
	    	  		',"tipoIngreso":"'+json.incidencias[i].tipoIngreso+'"'+
	    	  		',"nomDep":"'+json.incidencias[i].nomDep+'"'+
	    	  		',"apeNombs":"'+json.incidencias[i].apePaterno+' '
	  				   				   +json.incidencias[i].apeMaterno+' '
	  				  				   +json.incidencias[i].nombres+'"'+
	    	  		',"idSolicitante":"'+json.incidencias[i].idSolicitante+'"'+
	    	  		',"detIncidencia":"'+json.incidencias[i].detIncidencia+'"'+
	    	  		',"detIncidencia":"'+json.incidencias[i].detIncidencia+'"'+
	    	  		',"apeNombsU":"'+json.incidencias[i].apePaternoU+' '
	   				   			    +json.incidencias[i].apeMaternoU+' '
	  				   				+json.incidencias[i].nombresU+'"'+
	  				',"estado":"'+json.incidencias[i].estado+'"'+
	    	  		',"accion":'+btn+'}';						      	
			if (i==0){
		    	dataSet=fil;  
			}else{
				dataSet=dataSet+','+fil;
			}						      
		  }
		  dataSet='['+dataSet+']';
	      var obj=JSON.parse(dataSet);
	      $('#dataTables-example').DataTable( {
				 processing: false,
			     serverSide: false,
			     info : false,
				 bJQueryUI:true,
   		         data: obj,
   		         bDestroy: true,
   		 		 columns: [	{ "data": "idIncidencia","visible": false },
							{ "data": "numIncidencia" },			
							{ "data": "fechaIng" },
							{ "data": "tipoIngreso" },
							{ "data": "nomDep" },
							{ "data": "apeNombs" },
							{ "data": "detIncidencia" },
							{ "data": "apeNombsU" },
							{ "data": "estado" },
							{ "data": "accion",
						      "bSortable": false}],
				"order": [8, "asc"],
				"language": {"url": "js/Spanish.json"}
	      		
		     });   		 
	     if ( console && console.log ) {
	    	console.log( "La solicitud se ha completado correctamente." );
	         
	     }
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	
}
			
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////CARGAR DATOS//////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////CARGAR_TIPO_SERVICIOS////////////////////////////////////

function cargarTipoServicios(){	
	
	//alert("Entro");
	$.ajax({
	    data: {accion:"recuperarPorIdTabla",IdTabla:'TIPO_SERVICIO'},
	    type: "GET",
	    dataType: "json",
	    url:"./../TabItems.do"

	})
	 .done(function( json, textStatus, jqXHR ) {
		 for (i in json.tipItems ){				 
			 $("#mTipoServicio").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}

function cargarTipoAtencion(){	
	
	//alert("Entro");
	$.ajax({
	    data: {accion:"recuperarPorIdTabla",IdTabla:'TIPO_ATENCION'},
	    type: "GET",
	    dataType: "json",
	    url:"./../TabItems.do"

	})
	 .done(function( json, textStatus, jqXHR ) {    		
		 for (i in json.tipItems ){				 
			 $("#mTipoAtencion").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}
	
////////////////////////////////CARGAR_ATENCIONES////////////////////////////////////


function cargarAtenciones(id,anio,num){	
	
	//alert("Entro");
	$('#atIdAtencion').html('');
	$('#atIdAtencion').append('<option value="">Selecciona Atencion...</option>');
	$.ajax({
	    data: {accion:"recuperarAtenciones",IdIncidencia:id},
	    type: "GET",
	    dataType: "json",
	    url:"./../Atencion.do"

	})
	 .done(function( json, textStatus, jqXHR ) {
		 for (i in json.atenciones ){				 
			 $("#atIdAtencion").append('<option value="' + json.atenciones[i].idAtencion + '">' + json.atenciones[i].idAtencion + '</option>');
		 }   
		 
		 //alert($("#atIdAtencion").find("option").length);
		 var numAtencion = $("#atIdAtencion").find("option").length;
		 
		 if(numAtencion!=1){
			 
			 	var idIncidencia=$('#atIdIncidencia').val(id);
			   	var idNumIncidencia=$('#atNumIncidencia').val(num);
			    var anioIncidencia=$('#atAnioIncidencia').val(anio);
	
			   	$("#atenciones").modal("show");
		 }
		 else{
				$("#noAtencion").modal("show");
		 }
		 
	 })
	 
	 
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}

////////////////////////////////CARGAR_TIPO_ESTADO////////////////////////////////////


function cargarTipoEstado(){	
		
		//alert("Entro");
		$.ajax({
    	    data: {accion:"recuperarPorIdTabla",IdTabla:'TIPO_ESTADO'},
    	    type: "GET",
    	    dataType: "json",
    	    url:"./../TabItems.do"
   
    	})
    	 .done(function( json, textStatus, jqXHR ) {    		
			 for (i in json.tipItems ){				 
				 $("#mTipoEstado").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
			 }   	   
    	 })
    	 .fail(function( jqXHR, textStatus, errorThrown ) {
    	     if ( console && console.log ) {
    	         console.log( "La solicitud a fallado: " +  textStatus);
    	     }
    	});
		    
	}

////////////////////////////////CARGAR_TIPO_EQUIPO////////////////////////////////////

function cargarTipoEquipo(){	
	
	$.ajax({
	    data: {accion:"recuperarPorIdTabla",IdTabla:'TIPO_EQUIPO'},
	    type: "GET",
	    dataType: "json",
	    url:"./../TabItems.do"

	})
	 .done(function( json, textStatus, jqXHR ) {    		
		 for (i in json.tipItems ){				 
			 $("#mTipoEquipo").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}

////////////////////////////////CARGAR_ESTADO////////////////////////////////////

function cargarEstado(id,num){
	
	$.ajax({
	    data: {accion:"recuperarEstadoPorIncidencia",idIncidencia:id},
	    type: "GET",
	    dataType: "json",
	    url:"./../Incidencia.do"

	 
	})
	 .done(function( json, textStatus, jqXHR ) {
 		var estado= json.incidencias[0].estado;
		 
 		if(estado == null || estado == ""){
 			var idIncidencia=$('#mIdIncidencia').val(id);
 			var numIncidencia=$('#mNumIncidencia').val(num);
 			$("#addData").modal("show");
 		}
 		else{
 			$("#incAtendida").modal("show");
 		}
	 } 
	 )
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
}

/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////MVC_ATENCION///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////GUARDAR/////////////////////////////////////////////

function saveData() {	
	var idIncidencia = $('#mIdIncidencia').val();
	var idAtencion=$('#mIdAtencion').val();
    var numIncidencia=$('#mNumIncidencia').val();
    var fechaTerm=$('#mFechaTerm').val();
   	var tipoAtencion=$('#mTipoAtencion').val();
   	var tipoServicio=$('#mTipoServicio').val();
   	var tipoEquipo=$('#mTipoEquipo').val();
	var marca=$('#mMarca').val();
	var modelo=$('#mModelo').val();
   	var serie=$('#mSerie').val();	  
   	var estado=$('#mTipoEstado').val();		
   	var detServicio=$('#mDetServicio').val();	
   	var observaciones=$('#mObservaciones').val();
   	var activo = "N" //$('#mActivo').val();
   	var fechaIng = fechaTerm.replace("T", " ");
    fechaIng = fechaIng+":00";
   	if (document.mfrmDatosAtencion.mActivo.checked) {
            activo = "S"
    }
    $.ajax({
        type: "POST",
        url: "./../Atencion.do",
        dataType: 'json',
        data: {
        	mIdIncidencia: idIncidencia,
        	mIdAtencion: idAtencion,
        	mNumIncidencia: numIncidencia,
        	mFechaTerm: fechaIng,
        	mTipoAtencion: tipoAtencion,
        	mTipoServicio: tipoServicio,
        	mTipoEquipo: tipoEquipo,
        	mMarca: marca,
        	mModelo: modelo,
        	mSerie: serie,
        	mTipoEstado: estado,
        	mDetServicio: detServicio,
        	mObservaciones: observaciones,
        	mActivo: activo,
            "accion": "grabar"
        },
        success: function(msg) {
            alert('Success insert data' + msg);
        }
    });
}

/////////////////////////////////////ANULAR/////////////////////////////////////////////

function anularData() {
	var idIncidencia = $('#aIdIncidencia').val();
    var motivoAnular = $('#aMotivoAnular').val();
    var idAtencion = $('#aIdAtencion').val();
    var activo = "N";
    $.ajax({
        type: "POST",
        url: "./../Atencion.do",
        dataType: 'json',
        data: {
        	mIdIncidencia: idIncidencia,
        	mMotivoAnular: motivoAnular,
        	mIdAtencion: idAtencion,
            mActivo: activo,
            "accion": "anular"
        },
        success: function(msg) {
            alert('Success insert data' + msg);
        }
    });
}

/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
	
function cargarAtencionesAnular(id){	
	
	$('#atIdAtencion').html('');
	$('#atIdAtencion').append('<option value="">Selecciona Atencion...</option>');
	
	$.ajax({
	    data: {accion:"recuperarAtenciones",IdIncidencia:id},
	    type: "GET",
	    dataType: "json",
	    url:"./../Atencion.do"

	})
	 .done(function( json, textStatus, jqXHR ) {
		 for (i in json.atenciones ){				 
			 $("#aIdAtencion").append('<option value="' + json.atenciones[i].idAtencion + '">' + json.atenciones[i].idAtencion + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}
		
function fncLlenarAnulacion(id,num,anio){
	cargarAtencionesAnular(id);
   	var activo="N";
   	$("#anular").modal("show");
			$('#aIdIncidencia').val(id);
			$('#aNumIncidencia').val(num);
			$('#aAnioIncidencia').val(anio);
			
}
	


function validarEditar(id,anio,num){
	cargarAtenciones(id)
	var atencion =$("#atIdAtencion").val() ;
	//if()
}

function cargarDatosAtencion(){
	//alert(id);
var idIncidencia=$('#atIdIncidencia').val();
var idAtencion=$('#atIdAtencion').val();
var idNumIncidencia=$('#atNumIncidencia').val();
$("#atenciones").modal("hide");
	$("#addData").modal("show");
	$.get("./../Atencion.do",{accion:"recuperarPorIdAtencion",IdAtencion:idAtencion,IdIncidencia:idIncidencia},
	function(data){
		//alert(data);
		var p=JSON.parse(data);
		//alert(p.usuarios[0].idUsuario);
		var fechaTerm= p.atenciones[0].auxFecha;
		var fecha = fechaTerm.replace(" ", "T");
		var fe= fecha.substr(0,16);
		$('#mIdIncidencia').val(idIncidencia);
		$('#mIdAtencion').val(p.atenciones[0].idAtencion);
		$('#mNumIncidencia').val(idNumIncidencia);
		$('#mFechaTerm').val(fe);
		$('#mTipoAtencion').val(p.atenciones[0].idTipAtencion);
		$('#mTipoServicio').val(p.atenciones[0].idTipServicio);
		$('#mTipoEquipo').val(p.atenciones[0].detIncidencia);
		$('#mIdMarca').val(p.atenciones[0].idMarca);
		$('#mIdModelo').val(p.atenciones[0].idModelo);
		$('#mSerie').val(p.atenciones[0].numIncidencia);
		$('#mTipoEstado').val(p.atenciones[0].idEstadoServicio);
		$('#mDetServicio').val(p.atenciones[0].detalleServicio);
		$('#mObservaciones').val(p.atenciones[0].observaciones);
		$('#mTipoEquipo').val(p.atenciones[0].idTipEquipo);
		$('#mMarca').val(p.atenciones[0].idMarca);
		$('#mModelo').val(p.atenciones[0].idModelo);
		$('#mSerie').val(p.atenciones[0].serie);
				
	});
}
		

function generarPdf(id){	
	//alert("este es el id "+id);
		$.ajax({
		type: "GET",
		url: "./../Atencion.do",
		dataType: 'pdf',
		data: {
		    mIdIncidencia: id,
		    "accion": "pdf"
		      },
		 success: function(msg) {
		 alert('Success insert data' + msg);
		 }
		 });
}
	
////////////////////////////////CARGAR_MARCA////////////////////////////////////

function cargarMarca(){	
	
	$.ajax({
	    data: {accion:"recuperarPorIdTabla",IdTabla:'MARCA'},
	    type: "GET",
	    dataType: "json",
	    url:"./../TabItems.do"

	})
	 .done(function( json, textStatus, jqXHR ) {    		
		 for (i in json.tipItems ){				 
			 $("#mIdMarca").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}

////////////////////////////////CARGAR_MODELO////////////////////////////////////

function cargarModelo(){	
	
	$.ajax({
	    data: {accion:"recuperarPorIdTabla",IdTabla:'MODELO'},
	    type: "GET",
	    dataType: "json",
	    url:"./../TabItems.do"

	})
	 .done(function( json, textStatus, jqXHR ) {    		
		 for (i in json.tipItems ){				 
			 $("#mIdModelo").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}