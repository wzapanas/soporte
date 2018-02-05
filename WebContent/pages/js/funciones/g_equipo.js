	$(document).ready(function(){
		restringir()
		loadData();
		fechaActual()
		cargarTipoEquipo();
		cargarMarca();
		cargarModelo();
		cargarDependencias();
		cargarCargo();
		equiposNoAsignado();
		cargarSoli() ;
		cargarUltimoGrupo();
		cargarTipoEstado();
		cargarLocal();
		$("#editAsignadoEquipo").on('hidden.bs.modal', function () {
			$("#asignar").modal({backdrop: "true"});
			limpiarAsigEq();
    });
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
	
	function fechaActual(){
		var now = new Date();
		var month = (now.getMonth() + 1);
		var day = now.getDate();
		//var day1 = (now.getDate()+1);
		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;

		var fe = now.getFullYear() + '-' + month + '-'
				+ day;
		var today = now.getFullYear() + '-' + month
				+ '-' + day + 'T' + now.getHours()
				+ ':' + now.getMinutes();
		//var today1 = now.getFullYear() + '-' + month + '-' + day1;
		var fecha = today.replace("T", " ");
		$('#mFecAsig').val(today);
	}
	
	function loadData(){
    	$.ajax({
    	    data: {accion:"recuperarTodo",IdEquipo:0},
    	    type: "GET",
    	    dataType: "json",
    	    url:"./../Equipo.do"
    	})
    	 .done(function( json, textStatus, jqXHR ) {
    		 var btn;						          
		     var dataSet=''					      
			 var fil;
			 for (i in json.equipos ){						  		
			 	btn='\"<button type=\'button\' id=\'btnEditar\' onClick=\'editarEquipo('+json.equipos[i].idEquipo+')\' title=\'Editar Equipo\''+'><i class=\'fa fa-edit fa-lg\'></i></button>\ <button type=\'button\' id=\'btnAnular\' onClick=\'muestraFormAnulEq('+json.equipos[i].idEquipo+')\' title=\'Eliminar Equipo\''+'><i class=\'fa fa-trash fa-lg\' ></i></button>\ <button type=\'button\' id=\'btnAnular\' onClick=\'asignar('+json.equipos[i].idAsignado+','+json.equipos[i].grupo+')\' title=\'Asignar Equipo\''+'><i class=\'fa fa-mail-reply fa-lg\'></i></button>\"';
			 	if(json.equipos[i].grupo=="0" || json.equipos[i].grupo==undefined){
			 		json.equipos[i].grupo = '-';
			 	}
			 	if(json.equipos[i].asignado==undefined){
			 		json.equipos[i].asignado = "<p class='text-danger'><b>No Asignado</b></p>";
			 	}else{
			 		json.equipos[i].asignado = "<p class='text-success'><b>Asignado</b></p>";
			 	}
			 	
			 	fil = '{"grupo":"'+json.equipos[i].grupo+'"'+
			 			',"idTipEq":"'+json.equipos[i].idTipEq+'"'+	
			 			',"tipEquipo":"'+json.equipos[i].tipEquipo+'"'+	
			    	  	',"idMarca":"'+json.equipos[i].idMarca+'"'+
			    	  	',"marca":"'+json.equipos[i].marca+'"'+	
			    	  	',"idModelo":"'+json.equipos[i].idModelo+'"'+
			    	  	',"modelo":"'+json.equipos[i].modelo+'"'+
			    	  	',"serie":"'+json.equipos[i].serie+'"'+
			    	  	',"codPatrimonial":"'+json.equipos[i].codPatrimonial+'"'+
			    	  	',"idAsignado":"'+json.equipos[i].idAsignado+'"'+
			    	  	',"asignado":"'+json.equipos[i].asignado+'"'+
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
				 bJQueryUI:true,
				 info : false,
   		         data: obj,
   		         bDestroy: true,
   		 		 columns: [
		            { "data": "grupo"},
					{ "data": "tipEquipo" },
		           	{ "data": "marca" },
					{ "data": "modelo" },
					{ "data": "serie" },					
		            { "data": "codPatrimonial" },
		            { "data": "asignado" },
					{ "data": "accion",
			          "bSortable": false}],
			          "order": [0, "desc"],
			          "language": {
			                "url": "js/Spanish.json"
			            }
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
	
	function cargarPorIdAsignado(idAsignado){
    	$.ajax({
    	    data: {accion:"recuperarPorIdAsignado",IdAsignado:idAsignado},
    	    type: "GET",
    	    dataType: "json",
    	    url:"./../Asignado.do"
    	})
    	 .done(function( json, textStatus, jqXHR ) {
    		 var btn,btn1;						          
		     var dataSet=''					      
			 var fil;
			 for (i in json.equipos ){						  		
			 	btn='\"<button type=\'button\' id=\''+json.equipos[i].idEquipo+'\' onclick=\'editAsignadoEquipo('+json.equipos[i].idEquipo+');\' title=\'Editar Equipo\''+'><i class=\'fa fa-edit fa-lg\'></i></button>\ <button type=\'button\' id=\'btn-desasignar\' \' title=\'Desasignar Equipo\''+'><i class=\'fa fa-mail-forward fa-lg\'></i></button>\"';
			 	btn1='\"<button type=\'button\' id=\'btn-asignar\' title=\'Asignar Equipo\''+'><i class=\'fa fa-mail-reply fa-lg\'></i></button>\"';
			 	if(json.equipos[i].nomEquipo==undefined){
			 		json.equipos[i].nomEquipo = '-';
			 	}
			 	if(json.equipos[i].dirIp==undefined){
			 		json.equipos[i].dirIp = '-';
			 	}
			 	fil = '{"grupo":"'+json.equipos[i].grupo+'"'+
			 			',"idTipEq":"'+json.equipos[i].idTipEq+'"'+	
			 			',"idEquipo":"'+json.equipos[i].idEquipo+'"'+	
			 			',"tipEquipo":"'+json.equipos[i].tipEquipo+'"'+	
			    	  	',"idMarca":"'+json.equipos[i].idMarca+'"'+
			    	  	',"marca":"'+json.equipos[i].marca+'"'+	
			    	  	',"idModelo":"'+json.equipos[i].idModelo+'"'+
			    	  	',"modelo":"'+json.equipos[i].modelo+'"'+
			    	  	',"serie":"'+json.equipos[i].serie+'"'+
			    	  	',"codPatrimonial":"'+json.equipos[i].codPatrimonial+'"'+
			    	  	',"nomEquipo":"'+json.equipos[i].nomEquipo+'"'+
			    	  	',"dirIp":"'+json.equipos[i].dirIp+'"'+
			    	  	',"desasignar":'+btn+''+
			    	  	',"asignar":'+btn1+'}';						      	
				if (i==0){
			    	dataSet=fil;  
				}else{
					dataSet=dataSet+','+fil;
				}						      
			  }

			 dataSet='['+dataSet+']';
	      	 var obj=JSON.parse(dataSet);						    				      
			 $('#dataTablaEquipo').DataTable( {
				 paging: false,
			     ordering: false,
			     info: false,
				 processing: false,
				 searching: true,
			     serverSide: false,
				 bJQueryUI:true,
   		         data: obj,
   		         bDestroy: true,
   		 		 columns: [
   		 			{ "data": "idEquipo","visible": false },
   		 			{ "data": "grupo","visible": false },
					{ "data": "tipEquipo" },
					{ "data": "serie" },
					{ "data": "modelo" },				
		            { "data": "codPatrimonial" },
		            { "data": "nomEquipo" },
		            { "data": "dirIp" },
		            { "data": "desasignar","bSortable": false},
					{ "data": "asignar","visible": false, 
			          "bSortable": false}],
			          "language": {
			                "url": "js/Spanish.json"
			            }
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

    	function equiposNoAsignado(){
        	$.ajax({
        	    data: {accion:"recuperarEquiposNoAsignados",IdEquipo:0},
        	    type: "GET",
        	    dataType: "json",
        	    url:"./../Equipo.do"
        	})
        	 .done(function( json, textStatus, jqXHR ) {
        		 var btn,btn1;						          
    		     var dataSet=''					      
    			 var fil;
    			 for (i in json.equipos ){						  		
    				 btn='\"<button type=\'button\' id=\''+json.equipos[i].idEquipo+'\' onclick=\'editAsignadoEquipo('+json.equipos[i].idEquipo+');\' title=\'Editar Equipo\''+'><i class=\'fa fa-edit fa-lg\'></i></button>\ <button type=\'button\' id=\'btn-desasignar\' \' title=\'Desasignar Equipo\''+'><i class=\'fa fa-mail-forward fa-lg\'></i></button>\"';
    				 btn1='\"<button type=\'button\' id=\'btn-asignar\' title=\'Asignar Equipo\''+'><i class=\'fa fa-mail-reply fa-lg\'></i></button>\"';
    			 	if(json.equipos[i].grupo=="0" || json.equipos[i].grupo==undefined){
    			 		json.equipos[i].grupo = '-';
    			 	}
    			 	if(json.equipos[i].nomEquipo=="0" || json.equipos[i].nomEquipo==undefined){
    			 		json.equipos[i].nomEquipo = '-';
    			 	}
    			 	if(json.equipos[i].dirIp=="0" || json.equipos[i].dirIp==undefined){
    			 		json.equipos[i].dirIp = '-';
    			 	}
    			 	fil = '{"grupo":"'+json.equipos[i].grupo+'"'+
    			 			',"idTipEq":"'+json.equipos[i].idTipEq+'"'+	
    			 			',"idEquipo":"'+json.equipos[i].idEquipo+'"'+	
    			 			',"tipEquipo":"'+json.equipos[i].tipEquipo+'"'+	
    			    	  	',"idMarca":"'+json.equipos[i].idMarca+'"'+
    			    	  	',"marca":"'+json.equipos[i].marca+'"'+	
    			    	  	',"idModelo":"'+json.equipos[i].idModelo+'"'+
    			    	  	',"modelo":"'+json.equipos[i].modelo+'"'+
    			    	  	',"serie":"'+json.equipos[i].serie+'"'+
    			    	  	',"codPatrimonial":"'+json.equipos[i].codPatrimonial+'"'+
    			    	  	',"nomEquipo":"'+json.equipos[i].nomEquipo+'"'+
    			    	  	',"dirIp":"'+json.equipos[i].dirIp+'"'+
    			    	  	',"desasignar":'+btn+''+
    			    	  	',"asignar":'+btn1+'}';						      	
    				if (i==0){
    			    	dataSet=fil;  
    				}else{
    					dataSet=dataSet+','+fil;
    				}						      
    			  }

    			 dataSet='['+dataSet+']';
    	      	 var obj=JSON.parse(dataSet);						    				      
    			 $('#dataTablaEquipoNoAsignados').DataTable( {
    				 paging: false,
    			     ordering: false,
    			     info: false,
    			     scrollx: false,
    				 processing: false,
    				 searching: true,
    			     serverSide: false,
    				 bJQueryUI:true,
       		         data: obj,
       		         bDestroy: true,
       		 		 columns: [
       		 			{ "data": "idEquipo","visible": false}, 
       		 			{ "data": "grupo"},
    					{ "data": "tipEquipo" },
    					{ "data": "serie" },
    					{ "data": "modelo" },				
    		            { "data": "codPatrimonial" },
    		            { "data": "nomEquipo","visible": false},
    		            { "data": "dirIp","visible": false},
    		            { "data": "desasignar","bSortable": false,"visible": false},
    					{ "data": "asignar", 
    			          "bSortable": false}],
    			          "language": {
    			                "url": "js/Spanish.json"
    			            }
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
				 $("#mTipEquipo").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
			 }   	   
		 })
		 .fail(function( jqXHR, textStatus, errorThrown ) {
		     if ( console && console.log ) {
		         console.log( "La solicitud a fallado: " +  textStatus);
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
	
	
	function cargarDependencias() {
		console.log("Se Cargo DEP: " );
		$.ajax({
			data : {
				accion : "recuperarTodo",
				IdDependencia : 0
			},
			type : "GET",
			dataType : "json",
			url : "./../Dependencia.do"
		}).done(
				function(json, textStatus, jqXHR) {
					for (i in json.deps ){				 
						 $("#mIdDependencia").append('<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
					 } 
				}).fail(function(jqXHR, textStatus, errorThrown) {
			if (console && console.log) {
				console.log("La solicitud a fallado: " + textStatus);
			}
		});

	}
	
	
	function cargarLocal(){
		$.ajax({
			data : {
				accion :"recuperarTodo",IdLocal : 0},
			type : "GET",
			dataType : "json",
			url : "./../Local.do"
		}).done(
				function(json, textStatus, jqXHR) {
					for (i in json.locales){				 
						 $("#mIdLocal").append('<option value="' + json.locales[i].idLocal + '">' + json.locales[i].nomLocal + '</option>');
					 } 
				}).fail(function(jqXHR, textStatus, errorThrown) {
			if (console && console.log) {
				console.log("La solicitud a fallado: " + textStatus);
			}
		});

	}
	
	function cargarCargo(){	
		
		$.ajax({
		    data: {accion:"recuperarPorIdTabla",IdTabla:'CARGO'},
		    type: "GET",
		    dataType: "json",
		    url:"./../TabItems.do"

		})
		 .done(function( json, textStatus, jqXHR ) {    		
			 for (i in json.tipItems ){				 
				 $("#mIdCargo").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
			 }   	   
		 })
		 .fail(function( jqXHR, textStatus, errorThrown ) {
		     if ( console && console.log ) {
		         console.log( "La solicitud a fallado: " +  textStatus);
		     }
		});
		    
	} 
	
	function cargarSoli() {
		$.ajax({
		    data: {accion:"recuperarTodo",IdSolicitante:0},
		    type: "GET",
		    dataType: "json",
		    url:"./../Solicitante.do"
		})
		 .done(function( json, textStatus, jqXHR ) {    		
			 for (i in json.sols ){				 
				 $("#mIdSolicitante").append('<option value="' + json.sols[i].idSolicitante + '">' + json.sols[i].nombres+" "+ json.sols[i].apePaterno +" "+ json.sols[i].apeMaterno+ '</option>');
			 }   	   
		 })
		 .fail(function( jqXHR, textStatus, errorThrown ) {
		     if ( console && console.log ) {
		         console.log( "La solicitud a fallado: " +  textStatus);
		     }
		});
		    
	}
	
	function asignar(idAsignado,grupo) {
		if(grupo=="" || grupo==null){
			$("#sinGrupo").modal("show");
		}else{
			$("#asignar").modal("show");
			$('#mGrupo_').val(grupo);
			cargarPorIdAsignado(idAsignado);
			cargarAsignacion(idAsignado);
		}
		
	}
	
	function cargarAsignacion(idAsignado) {
		$.get("./../Asignado.do", {
			accion : "recuperarAsignacion",
			IdAsignado : idAsignado
		}, function(data) {
			var p = JSON.parse(data);
			var fechaAsig= p.asignados[0].auxFecAsigna;
			var fecha = fechaAsig.replace(" ", "T");
			var fe= fecha.substr(0,16);
			$('#mIdAsignado').val(p.asignados[0].idAsignado);
			$('#mIdSolicitante').val(p.asignados[0].idSolicitante);
			$('#mIdDependencia').val(p.asignados[0].idDependencia);
			$('#mIdCargo').val(p.asignados[0].idCargo);
			$('#mPiso').val(p.asignados[0].piso);
			$('#mNroOficina').val(p.asignados[0].nroOficina);
			$('#mObservacion').val(p.asignados[0].observacion);
			$('#mActivo').val(p.asignados[0].activo);
			$('#mFecAsig').val(fe);
		});
	}

$('#dataTablaEquipoNoAsignados tbody').on('click','button#btn-asignar',function(){
    var tabla1 = $('#dataTablaEquipoNoAsignados').DataTable();
    var tabla2 = $('#dataTablaEquipo').DataTable();
    //console.log(tabla1.row(this).data());
    var row = tabla1.row($(this).parents('tr'));
    var rowNode = row.data();
    //rowNode['accion'] = "<button type='button' title='Editar Equipo'><i class='fa fa-edit fa-lg'></i></button><button type='button' title='Desasignar Equipo' id='btn-desasignar'><i class='fa fa-mail-forward fa-lg'></i></button>";
    row.remove().draw();
    console.log(rowNode);
    tabla2.row.add(rowNode).draw(false);
  })
  
  $('#dataTablaEquipo tbody').on('click','button#btn-desasignar',function(){
    var tabla1 = $('#dataTablaEquipoNoAsignados').DataTable();
    var tabla2 = $('#dataTablaEquipo').DataTable();
    //console.log(tabla1.row(this).data());
    var row = tabla2.row($(this).parents('tr'));
    var rowNode = row.data();
    //rowNode['accion'] = "<button type='button' title='Desasignar Equipo' id='btn-asignar'><i class='fa fa-mail-reply fa-lg'></i></button>";
    row.remove().draw();
    console.log(rowNode);
    tabla1.row.add(rowNode).draw(false);
  })
  
  /*
    $('#dataTablaEquipo tbody').on('click','button#editEq',function(){
    var tabla1 = $('#dataTablaEquipoNoAsignados').DataTable();
    var tabla2 = $('#dataTablaEquipo').DataTable();
    //console.log(tabla1.row(this).data());
    var row = tabla2.row($(this).parents('tr'));
    var rowNode = row.data();
    //rowNode['accion'] = "<button type='button' title='Desasignar Equipo' id='btn-asignar'><i class='fa fa-mail-reply fa-lg'></i></button>";
    row.remove().draw();
    console.log(rowNode);
    tabla1.row.add(rowNode).draw(false);
  })
   */
  
function cargarUltimoGrupo(){	
	
	$.ajax({
	    data: {accion:"recuperarUltimoGrupo",IdEquipo:0},
	    type: "GET",
	    dataType: "json",
	    url:"./../Equipo.do"

	})
	 .done(function( json, textStatus, jqXHR ) {    		
		 for (i in json.equipos ){				 
			 $("#ultimoGrupo").val(json.equipos[i].ultimoGrupo);
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
} 

function cargarTipoEstado(){	
	
	//alert("Entro");
	$.ajax({
	    data: {accion:"recuperarPorIdTabla",IdTabla:'ESTADO_EQUIPO'},
	    type: "GET",
	    dataType: "json",
	    url:"./../TabItems.do"
   
    	})
    	 .done(function( json, textStatus, jqXHR ) {    		
			 for (i in json.tipItems ){				 
				 $("#mIdEstado").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}

function saveEquipo() {
	//alert("hola mundo");
	var idEquipo = $('#mIdEquipo').val();
	var grupo = $('#mGrupo').val();
	var idTipEq = $('#mTipEquipo').val();
	var idMarca = $('#mIdMarca').val();
	var idModelo = $('#mIdModelo').val();
	var serie = $('#mSerie').val();
	var patrimonio = $('#mPatrimonio').val();
	var fecCompra = $('#mFecCompra').val();
	var idEstado = $('#mIdEstado').val();
	var activo = "N"; 
	var fecCompra = fecCompra.replace("T", " ");
	fecCompra = fecCompra + ":00";
	//alert("Fecha: "+fecCompra);
	//alert(document.mfrmDatosIncidencias.mActivo.checked);
	if (document.mfrmDatosEquipo.mActivo.checked) {
		activo = "S"
	}
	$.ajax({
		type : "POST",
		url : "./../Equipo.do",
		dataType : 'json',
		data : {
			mIdEquipo : idEquipo,
			mGrupo : grupo,
			mTipEquipo : idTipEq,
			mIdMarca : idMarca,
			mIdModelo : idModelo,
			mSerie : serie,
			mPatrimonio : patrimonio,
			mFecCompra : fecCompra,
			mIdEstado : idEstado,
			mActivo : activo,
			"accion" : "grabar"
		},
		success : function(msg) {
			alert('Success insert data' + msg);
		}
	});
}

function editarEquipo(id) {
	$("#addData").modal("show");
	$.get("./../Equipo.do", {
		accion : "recuperarPorIdEquipo",
		mIdEquipo : id
	}, function(data) {
		var p = JSON.parse(data);
		var fechaCom= p.equipos[0].auxFecCompra;
		var fecha = fechaCom.replace(" ", "T");
		var fe= fecha.substr(0,16);
		$('#mIdEquipo').val(p.equipos[0].idEquipo);
		$('#mGrupo').val(p.equipos[0].grupo);
		$('#mTipEquipo').val(p.equipos[0].idTipEq);
		$('#mIdMarca').val(p.equipos[0].idMarca);
		$('#mIdModelo').val(p.equipos[0].idModelo);
		$('#mSerie').val(p.equipos[0].serie);
		$('#mPatrimonio').val(p.equipos[0].codPatrimonial);
		$('#mFecCompra').val(fe);
		$('#mIdEstado').val(p.equipos[0].idEstado);
		$('#mIdLocal').val(p.equipos[0].idLocal);
		$('#mActivo').val(p.equipos[0].activo);
	});
}



function editAsignadoEquipo(id){
	$("#editAsignadoEquipo").modal({backdrop: "true"});
	$("#asignar").modal("hide");
	cargarAsignadoEquipo(id);
}

function aparecerModalEditAsignadoEquipo(){
	$("#asignar").modal("show");
	$("#editAsignadoEquipo").modal("hide");
}

function cargarAsignadoEquipo(id){
	
	var idEquipo,nomEq,dirIp;
    var tabla2 = $('#dataTablaEquipo').DataTable();
    //console.log(tabla1.row(this).data());
    var row = tabla2.row($('#dataTablaEquipo tbody #'+id+'').parents('tr'));
    var rowNode = row.data();
    nomEq = rowNode['nomEquipo']
    dirIp = rowNode['dirIp']

    $('#asigIdEq').val(id);
	$('#asigNomEq').val(nomEq);
	$('#asigDirIp').val(dirIp);
	
}

// // // // // // // // // // Guarda Datos de Formulario Asignar // // // // // // // // // //
	
function saveAsignado(){
	var grupo = $('#mGrupo_').val();
	var idAsignado = $('#mIdAsignado').val();
	var idSolicitante = $('#mIdSolicitante').val();
	var idDependencia = $('#mIdDependencia').val();
	var idCargo = $('#mIdCargo').val();
	var fecAsig = $('#mFecAsig').val();
	var piso = $('#mPiso').val();
	var nroOficina = $('#mNroOficina').val();
	var observacion = $('#mObservacion').val();
	var activo = "N"; 
	var fechaAsig = fecAsig.replace("T", " ");
	fechaAsig = fechaAsig + ":00";
	if (document.mfrmAsignado.mActivo.checked) {
		activo = "S"
	}
    var tabla1 = $('#dataTablaEquipo').DataTable();
    var equipos = tabla1.data();
    var numElementos = equipos.length;
    var arrEquipos = jQuery.makeArray(equipos);
    var arrJSON = JSON.stringify(arrEquipos);
    console.log(arrEquipos);
    
	$.ajax({
		type : "POST",
		url : "./../Asignado.do",
		dataType : 'json',
		data : {
			mIdAsignado : idAsignado,
			mIdSolicitante : idSolicitante,
			mIdDependencia : idDependencia,
			mIdCargo : idCargo,
			mFecAsig : fechaAsig,
			mPiso : piso,
			mNroOficina : nroOficina,
			mObservacion : observacion,
			mActivo : activo,
			mGrupo:grupo,
			equipos:arrJSON,
			numArray:numElementos,
			"accion" : "grabar"
		},
		success : function(msg) {
			alert('Success insert data' + msg);
		}
	});
}

// // // // // // // // // // Carga Datos de Area- Dependencia // // // // // // // // // //

function cargarArea() {
	var area= $('#mArea').val();
	//alert("HOLA");
	console.log("Area Seleccion: "+area);
	if(area=="adm"){
		$.ajax({
			data : {
				accion : "recuperarDepAdm",
				IdDependencia : 0
			},
			type : "GET",
			dataType : "json",
			url : "./../Dependencia.do"
		}).done(
				function(json, textStatus, jqXHR) {
					$("#mIdDependencia option").remove();
					$("#mIdDependencia").append('<option value="0">Seleccione Dependencia...</option>');
					for (i in json.deps) {						
						
						$("#mIdDependencia").append(
								'<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
						console.log(json.deps[i].nomDep );
					}
				}).fail(function(jqXHR, textStatus, errorThrown) {
			if (console && console.log) {
				console.log("La solicitud a fallado: " + textStatus);
			}
		});
	}
	if(area=="juric"){
		$.ajax({
			data : {
				accion : "recuperarDepJuric",
				IdDependencia : 0
			},
			type : "GET",
			dataType : "json",
			url : "./../Dependencia.do"
		}).done(
				function(json, textStatus, jqXHR) {
					$("#mIdDependencia option").remove();		
					$("#mIdDependencia").append('<option value="0">Seleccione Dependencia...</option>');
					for (i in json.deps) {						
						$("#mIdDependencia").append(
								'<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
						console.log(json.deps[i].nomDep );
					}
				}).fail(function(jqXHR, textStatus, errorThrown) {
			if (console && console.log) {
				console.log("La solicitud a fallado: " + textStatus);
			}
		});
	}
}


// // // // // // // // // // Limpiar  Formulario Equipos Asignados // // // // // // // // // //

function limpiarAsigEq(){
	$('#mfrmDatosEquipoAsignado')[0].reset();
}

// // // // // // // // // // Edita Datos de la tabla de equipos asignados // // // // // // // // // //

function editarDatosTablaAsiEq(){
	var idEquipo = $('#asigIdEq').val();
	var nomEq = $('#asigNomEq').val();
	var dirIp = $('#asigDirIp').val();
    var tabla2 = $('#dataTablaEquipo').DataTable();
    //console.log(tabla1.row(this).data());
    var row = tabla2.row($('#dataTablaEquipo tbody #'+idEquipo+'').parents('tr'));
    var rowNode = row.data();
    rowNode['nomEquipo'] = nomEq;
    rowNode['dirIp'] = dirIp;
    row.remove().draw();
    console.log(rowNode);
    tabla2.row.add(rowNode).draw(false);
}

// // // // // // // // // // Muestra Formulario de Anulacion de Equipo // // // // // // // // // //

function muestraFormAnulEq(id){
	$('#aIdEquipo').val(id);
   	$("#anular").modal("show");
}
// // // // // // // // // // Anula Equipo // // // // // // // // // //

function anularEquipo(){
	 var idEq = $('#aIdEquipo').val();
	    var motivoAnul = $('#aMotivoAnular').val();
	    var activo = "N";
	    $.ajax({
	        type: "POST",
	        url: "./../Equipo.do",
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