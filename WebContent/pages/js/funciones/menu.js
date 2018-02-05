$(document).ready(function(){
	restringir();
})

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
		 })
		 .fail(function( jqXHR, textStatus, errorThrown ) {
		     if ( console && console.log ) {
		         console.log( "La solicitud a fallado: " +  textStatus);
		     }
		});
		    
}