
	
		
	$(document).ready(function(){
		restringir();
		loadData();
		cargarRoles();
		cargarDependencias();
		user();
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
			 if(rol!="1"){
				 window.location.href = "menu.jsp";
			 }
		 })
		 .fail(function( jqXHR, textStatus, errorThrown ) {
		     if ( console && console.log ) {
		         console.log( "La solicitud a fallado: " +  textStatus);
		     }
		});
		    
	}
	
	function cargarRoles(){		
		$.ajax({
    	    data: {accion:"recuperarTodo",IdRol:0},
    	    type: "GET",
    	    dataType: "json",
    	    url:"./../Rol.do"
    	})
    	 .done(function( json, textStatus, jqXHR ) {    		
			 for (i in json.rols ){				 
				 $("#mIdRol").append('<option value="' + json.rols[i].idRol + '">' + json.rols[i].nomRol + '</option>');
			 }   	   
    	 })
    	 .fail(function( jqXHR, textStatus, errorThrown ) {
    	     if ( console && console.log ) {
    	         console.log( "La solicitud a fallado: " +  textStatus);
    	     }
    	});
		    
	}
	
	function cargarDependencias(){		
		$.ajax({
    	    data: {accion:"recuperarTodo",IdDependencia:0},
    	    type: "GET",
    	    dataType: "json",
    	    url:"./../Dependencia.do"
    	})
    	 .done(function( json, textStatus, jqXHR ) {    		
			 for (i in json.deps ){				 
				 $("#mIdDependencia").append('<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
			 }   	   
    	 })
    	 .fail(function( jqXHR, textStatus, errorThrown ) {
    	     if ( console && console.log ) {
    	         console.log( "La solicitud a fallado: " +  textStatus);
    	     }
    	});
		    
	}
	
	
	function user(){		
		$.ajax({
    	    data: {accion:"recuperarUser",IdUsuario:0},
    	    type: "GET",
    	    dataType: "json",
    	    url:"./../Usuario.do"
    	})
    	 .done(function( json, textStatus, jqXHR ) {    		
			 for (i in json.deps ){				 
				 $("#mIdUser").append( json.deps[i].nomDep );
			 }   	   
    	 })
    	 .fail(function( jqXHR, textStatus, errorThrown ) {
    	     if ( console && console.log ) {
    	         console.log( "La solicitud a fallado: " +  textStatus);
    	     }
    	});
		    
	}
	
	function loadData(){
    	$.ajax({
    	    data: {accion:"recuperarTodo",IdUsuario:0},
    	    type: "GET",
    	    dataType: "json",
    	    url:"./../Usuario.do"
    	})
    	 .done(function( json, textStatus, jqXHR ) {
    		 var btn;						          
		     var dataSet=''					      
			 var fil;
			 for (i in json.usuarios ){						  		
			 	btn='\"<button type=\'button\' id=\'btnEditar\' onClick=\'fncEditar('+json.usuarios[i].idUsuario+')\' title=\'Editar Usuario\''+'><i class=\'fa fa-edit fa-lg\'></i></button>\ <button type=\'button\' id=\'btnAnular\' onClick=\'anular('+json.usuarios[i].idUsuario+')\' title=\'Eliminar Usuario\''+'><i class=\'fa fa-trash fa-lg\' ></i></button>\"';
			 	fil = '{"idUsuario":"'+json.usuarios[i].idUsuario+'"'+
			    	  	',"usuario":"'+json.usuarios[i].usuario+'"'+						    	  		
			    	  	',"apeNombs":"'+json.usuarios[i].apePaterno+' '
			    	  				   +json.usuarios[i].apeMaterno+' '
			    	  				   +json.usuarios[i].nombres+'"'+
			    	  	',"nomRol":"'+json.usuarios[i].nomRol+'"'+
			    	  	',"nomDep":"'+json.usuarios[i].nomDep+'"'+
			    	  	',"activo":"'+json.usuarios[i].activo+'"'+
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
				 info: false,
			     serverSide: false,
				 bJQueryUI:true,
   		         data: obj,
   		         bDestroy: true,
   		 		 columns: [
		            { "data": "idUsuario","visible": false },
					{ "data": "usuario" },
		           	{ "data": "apeNombs" },
					//{ "data": "dni" },
					//{ "data": "email" },
					{ "data": "nomRol" },
					{ "data": "nomDep" },					
		            { "data": "activo","visible" : false },
					{ "data": "accion",
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
		
	function saveData(){
    	var idUsuario=$('#mIdUsuario').val();
    	var usuario=$('#mUsuario').val();
    	var apePaterno=$('#mApePaterno').val();
    	var apeMaterno=$('#mApeMaterno').val();
    	var nombres=$('#mNombres').val();
    	var dni=$('#mDni').val();
    	var email=$('#mEmail').val();
    	var idRol=$('#mIdRol').val();
    	var idDependencia=$('#mIdDependencia').val();	    	
    	var activo="N"//$('#mActivo').val();
    	var clave=$('#mtxtClave').val();
    	if (document.mfrmDatosUsuario.mActivo.checked){activo="S"}
    	    //alert(clave);	
    	$.ajax({
    		type: "POST",
    		url:"./../Usuario.do",
    		dataType: 'json',
    		data: { mIdUsuario: idUsuario,
    				mUsuario: usuario,
    				mApePaterno: apePaterno,
    				mApeMaterno: apeMaterno,
    				mNombres: nombres,
    				mDni: dni,
    				mEmail: email,
    				mIdRol: idRol,
    				mIdDependencia: idDependencia,
    				mActivo: activo,
    				mtxtClave: clave,
    				"accion": "grabar" },    		
    		success: function(msg){
    			alert('Success insert data'+msg);
    		}
    	});    	
    }
	
	function fncEditar(id){
		//alert(id);
    	var idUsuario=$('#mIdUsuario').val();
    	var usuario=$('#mUsuario').val();
    	var apePaterno=$('#mApePaterno').val();
    	var apeMaterno=$('#mApeMaterno').val();
    	var nombres=$('#mNombres').val();
    	var dni=$('#mDni').val();
    	var email=$('#mEmail').val();
    	var idRol=$('#mIdRol').val();
    	var idDependencia=$('#mIdDependencia').val();	    	
    	var activo="N"//$('#mActivo').val();
    	
    	$("#addData").modal("show");
		$.get("./../Usuario.do",{accion:"recuperarPorIdUsuario",IdUsuario:id},
			function(data){
				//alert(data);
				var p=JSON.parse(data);
				//alert(p.usuarios[0].idUsuario);
				$('#mIdUsuario').val(p.usuarios[0].idUsuario);
				$('#mUsuario').val(p.usuarios[0].usuario);
				$('#mApePaterno').val(p.usuarios[0].apePaterno);
				$('#mApeMaterno').val(p.usuarios[0].apeMaterno);
				$('#mNombres').val(p.usuarios[0].nombres);
				$('#mDni').val(p.usuarios[0].dni);
				$('#mEmail').val(p.usuarios[0].email);
				$('#mIdRol').val(p.usuarios[0].idRol);
				$('#mIdDependencia').val(p.usuarios[0].idDependencia);
				$('#mtxtClave').val(p.usuarios[0].clave);
		
		});
		
     	
    }
	
	
	function anular(id){
		$('#idEliminar').val(id);
		$("#anularUser").modal("show");
    }
	
	function confirmaEliminacion(){
		var idUsuario = $('#idEliminar').val();
        var activo = "N";
		 $.ajax({
	            type: "POST",
	            url: "./../Usuario.do",
	            dataType: 'json',
	            data: {
	            	mIdUsuario: idUsuario,
	                mActivo: activo,
	                "accion": "anular"
	            },
	            success: function(msg) {
	                alert('Success insert data' + msg);
	            }
	        });
	}
	
	// // // // // // // // // // Limpiar  Formulario de Usuario // // // // // // // // // //
	
	$('#btnNuevoUsuario').click(function(){
		$('#mfrmDatosUsuario')[0].reset();
	})
	