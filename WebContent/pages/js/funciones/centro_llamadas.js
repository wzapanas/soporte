$(document).ready(function(){
	restringir();
	buscarIncidencia();
	loadIncidencias();
	cargarDependencias();
	cargarSoli();
	cargarUsuarios();
	cargarUbicacion();
	cargarTipoIngreso();
	cargarSituacionTicket();
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
		 if(rol!="2" && rol!="1"){
			 window.location.href = "menu.jsp";
		 }
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
}

function buscarIncidencia(){
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

	$('#mFechaInicio').val(fe);
	$('#mFechaFin').val(fe);
	$('#mFechaIng').val(today);
}

function loadIncidencias() {
	var fInicio = $('#mFechaInicio').val();
	var fFin = $('#mFechaFin').val();
	//alert(fInicio);
	$.ajax({
		data : {
			accion : "recuperarPorFechas",
			fInicio : fInicio,
			fFin : fFin
		},
		type : "GET",
		dataType : "json",
		url : "./../Incidencia.do"
	})
	.done(
			function(json, textStatus, jqXHR) {
				var btn;
				var dataSet = ''
				var fil;
				for (i in json.incidencias) {
					switch(json.incidencias[i].situacionTicket){
			  		case "NORMAL": json.incidencias[i].situacionTicket="<p class='text-success'><b>"+json.incidencias[i].situacionTicket+"</b></p>";
			  		break;
			  		case "ESCALADO": json.incidencias[i].situacionTicket="<p class='text-warning'><b>"+json.incidencias[i].situacionTicket+"</b></p>";
			  		break;
			  		case "REASIGNADO": json.incidencias[i].situacionTicket="<p class='text-warning'><b>"+json.incidencias[i].situacionTicket+"</b></p>";
			  		break;
			  		}
					
					btn = '\"<button type=\'button\' id=\'btnEditar\' onClick=\'fncEditar('+ json.incidencias[i].idIncidencia+ ')\' title=\'Editar Incidencia\''+'><i class=\'fa fa-edit fa-lg\'></i></button>\ <button type=\'button\' id=\'btnAnular\' onClick=\'fncLlenarAnulacion('+ json.incidencias[i].idIncidencia+ ','+ json.incidencias[i].numIncidencia+ ','+ json.incidencias[i].anioIncidencia+ ')\' title=\'Eliminar Incidencia\''+'><i class=\'fa fa-trash fa-lg\' ></i></button>\"';
					fil = '{"idIncidencia":"'+ json.incidencias[i].idIncidencia+ '"'+ 
							',"numIncidencia":"'+ json.incidencias[i].numIncidencia+ '"'+ 
							',"fechaIng":"'+ json.incidencias[i].auxFecha+ '"'+ 
							',"tipoIngreso":"'+ json.incidencias[i].tipoIngreso+ '"'+ 
							',"nomDep":"'+ json.incidencias[i].nomDep+ '"'+ 
							',"apeNombs":"'+ json.incidencias[i].apePaterno+ ' '
										   + json.incidencias[i].apeMaterno+ ' '
										   + json.incidencias[i].nombres+ '"'+ 
							',"detIncidencia":"'+ json.incidencias[i].detIncidencia+ '"'+ 
							',"apeNombsU":"'+ json.incidencias[i].apePaternoU+ ' '
											+ json.incidencias[i].apeMaternoU
											+ ' '+ json.incidencias[i].nombresU+ '"' + 
							',"idSitTicket":"'+ json.incidencias[i].idSituacionTicket+ '"' + 
							',"sitTicket":"'+ json.incidencias[i].situacionTicket+ '"' + 
							',"accion":' + btn+ '}';
					if (i == 0) {
						dataSet = fil;
					} else {
						dataSet = dataSet + ',' + fil;
					}
				}
				dataSet = '[' + dataSet + ']';
				var obj = JSON.parse(dataSet);
				$('#dataTables-example').DataTable({
					processing : false,
					serverSide : false,
					info : false,
					bJQueryUI : true,
					data : obj,
					bDestroy : true,
					columns : [ {
						"data" : "idIncidencia","visible" : false
					}, {"data" : "numIncidencia"
					}, {"data" : "fechaIng"
					}, {"data" : "tipoIngreso"
					}, {"data" : "nomDep"
					}, {"data" : "apeNombs"
					}, {"data" : "detIncidencia"
					}, {"data" : "apeNombsU"
					}, {"data" : "sitTicket"
					}, {"data" : "accion",
						"bSortable" : false
					} ],
					"language" : {
						"url" : "js/Spanish.json"
					}
	
				});
				if (console && console.log) {
					console
							.log("La solicitud se ha completado correctamente.");
	
				}
			}).fail(
			function(jqXHR, textStatus, errorThrown) {
				if (console && console.log) {
					console.log("La solicitud a fallado: "
							+ textStatus);
				}
			});

}

function cargarDependencias() {
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
				for (i in json.deps) {
					$("#mIdDependencia").append(
							'<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
					$("#aIdDepSol").append(
							'<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
					$("#modIdDepSol").append(
							'<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep+ '</option>');
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
		if (console && console.log) {
			console.log("La solicitud a fallado: " + textStatus);
		}
	});

}

function cargarUbicacion() {
	$.ajax({
		data : {
			accion : "recuperarUbicacion",
			IdDependencia : 0
		},
		type : "GET",
		dataType : "json",
		url : "./../Dependencia.do"
	}).done(
			function(json, textStatus, jqXHR) {
				for (i in json.deps) {
					$("#mIdUbicacion").append(
							'<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
		if (console && console.log) {
			console.log("La solicitud a fallado: " + textStatus);
		}
	});
}

function cargarArea() {
	var area= $('#mArea').val();
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
					for (i in json.deps) {
						$("#mIdDependencia option").remove();
						$("#mIdDependencia").append('<option value="0">Seleccione Dependencia...</option>');
						$("#mIdDependencia").append(
								'<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
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
					for (i in json.deps) {
						$("#mIdDependencia option").remove();
						$("#mIdDependencia").append('<option value="0">Seleccione Dependencia...</option>');
						$("#mIdDependencia").append(
								'<option value="' + json.deps[i].idDependencia + '">' + json.deps[i].nomDep + '</option>');
					}
				}).fail(function(jqXHR, textStatus, errorThrown) {
			if (console && console.log) {
				console.log("La solicitud a fallado: " + textStatus);
			}
		});
	}
}

function cargarSoli() {
	$('#mIdSolicitante').html('');
	$('#mIdSolicitante').append(
			'<option value="">Selecciona Solicitante...</option>');
	$.ajax({
		data : {
			accion : "recuperarTodo",
			IdSolicitante : 0
		},
		type : "GET",
		dataType : "json",
		url : "./../Solicitante.do"
	}).done(
			function(json, textStatus, jqXHR) {
				for (i in json.sols) {
					$("#mIdSolicitante").append(
							'<option value="' + json.sols[i].idSolicitante + '">'
									+ json.sols[i].nombres + " "+ json.sols[i].apePaterno+" "+ json.sols[i].apeMaterno+ 
							'</option>');
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
		if (console && console.log) {
			console.log("La solicitud a fallado: " + textStatus);
		}
	});

}

function cargarUsuarios() {
	$.ajax({
		data : {
			accion : "recuperarTodo",
			IdUsuario : 0
		},
		type : "GET",
		dataType : "json",
		url : "./../Usuario.do"
	}).done(
			function(json, textStatus, jqXHR) {
				for (i in json.usuarios) {
					$("#mIdUsuario").append(
							'<option value="' + json.usuarios[i].idUsuario + '">'
									+ json.usuarios[i].nombres+" "+ json.usuarios[i].apePaterno+" "+ json.usuarios[i].apeMaterno+
									'</option>');
				}
			}).fail(function(jqXHR, textStatus, errorThrown) {
		if (console && console.log) {
			console.log("La solicitud a fallado: " + textStatus);
		}
	});

}


function cargarTipoIngreso(){	
	
	//alert("Entro");
	$.ajax({
	    data: {accion:"recuperarPorIdTabla",IdTabla:'TIPO_INGRESO'},
	    type: "GET",
	    dataType: "json",
	    url:"./../TabItems.do"

	})
	 .done(function( json, textStatus, jqXHR ) {
		 for (i in json.tipItems ){				 
			 $("#mIdTipIng").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}

function cargarSituacionTicket(){	
	
	//alert("Entro");
	$.ajax({
	    data: {accion:"recuperarPorIdTabla",IdTabla:'SITUACION_TICKET'},
	    type: "GET",
	    dataType: "json",
	    url:"./../TabItems.do"

	})
	 .done(function( json, textStatus, jqXHR ) {
		 for (i in json.tipItems ){				 
			 $("#mIdSiTicket").append('<option value="' + json.tipItems[i].idItem + '">' + json.tipItems[i].nomCorto + '</option>');
		 }   	   
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
	    
}

function fncEditar(id) {
	
	$("#addData").modal("show");
	$.get("./../Incidencia.do", {
		accion : "recuperarPorIdIncidencia",
		IdIncidencia : id
	}, function(data) {
		var p = JSON.parse(data);
		var fechaIng= p.incidencias[0].auxFecha;
		var fecha = fechaIng.replace(" ", "T");
		var fe= fecha.substr(0,16);
		$('#mIdIncidencia').val(p.incidencias[0].idIncidencia);
		$('#mNumIncidencia').val(p.incidencias[0].numIncidencia);
		$('#mFechaIng').val(fe);
		$('#mIdTipIng').val(p.incidencias[0].idTipIng);
		$('#mIdDependencia').val(p.incidencias[0].idDependencia);
		$('#mIdUbicacion').val(p.incidencias[0].idUbicacion);
		$('#mIdSolicitante').val(p.incidencias[0].idSolicitante);
		$('#mDetIncidencia').val(p.incidencias[0].detIncidencia);
		$('#mIdUsuario').val(p.incidencias[0].idUsuario);
		$('#mIdSiTicket').val(p.incidencias[0].idSituacionTicket);
	});
}

function fncLlenarAnulacion(id, num, anio) {
	var activo = "N";
	$("#anular").modal("show");
	$('#aIdIncidencia').val(id);
	$('#aNumIncidencia').val(num);
	$('#aAnioIncidencia').val(anio);
}

function saveData() {
	var idIncidencia = $('#mIdIncidencia').val();
	var numIncidencia = $('#mNumIncidencia').val();
	var idSolicitante = $('#mIdSolicitante').val();
	var idDependencia = $('#mIdDependencia').val();
	var idUbicacion = $('#mIdUbicacion').val();
	var fechaIng = $('#mFechaIng').val();
	var idTipIng = $('#mIdTipIng').val();
	var detIncidencia = $('#mDetIncidencia').val();
	var idSiTicket = $('#mIdSiTicket').val();
	var idUsuario = $('#mIdUsuario').val();
	var activo = "N"; //$('#mActivo').val();
	var fechaIng = fechaIng.replace("T", " ");
	fechaIng = fechaIng + ":00";

	//alert(document.mfrmDatosIncidencias.mActivo.checked);
	if (document.mfrmDatosIncidencias.mActivo.checked) {
		activo = "S"
	}

	$.ajax({
		type : "POST",
		url : "./../Incidencia.do",
		dataType : 'json',
		data : {
			mIdIncidencia : idIncidencia,
			mNumIncidencia : numIncidencia,
			mFecha : fechaIng,
			mIdTipIng : idTipIng,
			mIdDependencia : idDependencia,
			mIdUbicacion : idUbicacion,
			mIdSolicitante : idSolicitante,
			mDetIncidencia : detIncidencia,
			mIdUsuario : idUsuario,
			mIdSiTicket : idSiTicket,
			mActivo : activo,
			"accion" : "grabar"
		},
		success : function(msg) {
			alert('Success insert data' + msg);
		}
	});

}

function anularData() {
	var idIncidencia = $('#aIdIncidencia').val();
	var motivoAnular = $('#aMotivoAnular').val();
	var activo = "N";

	$.ajax({
		type : "POST",
		url : "./../Incidencia.do",
		dataType : 'json',
		data : {
			mIdIncidencia : idIncidencia,
			mMotivoAnular : motivoAnular,
			mActivo : activo,
			"accion" : "anular"
		},
		success : function(msg) {
			alert('Success insert data' + msg);
		}
	});
}


function addSoli(){
$("#addData").modal("hide");
$("#addSolicitante").modal("show");
}

function modSoli(){
	$("#addData").modal("hide");
	$("#modSolicitante").modal("show");
}

function anulSoli(){
	$("#addData").modal("hide");
	$("#anularSoli").modal("show");
}

function cerrar(){
$("#addSolicitante").modal("hide");
$("#addData").modal("show");
}

function cerrar1(){
	$("#modSolicitante").modal("hide");
	$("#addData").modal("show");
	}

function cerrar2(){
	$("#anularSoli").modal("hide");
	$("#addData").modal("show");
	}

function saveSol() {

	var aIdSoli = $('#aIdSol').val();
	var aApePaterno = $('#aApePaSol').val();
	var aApeMaterno = $('#aApeMaSol').val();
	var aNombres = $('#aNomSol').val();
	var aIdDependencia = $('#aIdDepSol').val();
	var aActivo = "S";//$('#mActivo').val();
	$.ajax({
		type : "POST",
		url : "./../Solicitante.do",
		dataType : 'json',
		data : {
			sIdSol : aIdSoli,
			sApePaterno : aApePaterno,
			sApeMaterno : aApeMaterno,
			sNombres : aNombres,
			sIdDependencia : aIdDependencia,
			sActivo : aActivo,
			"accion" : "grabar"
		},
		success : function(msg) {
			alert('Success insert data' + msg);

		}
	});
	
	$("#addSolicitante").modal("hide");
	$("#addData").modal("show");
	cargarSoli();
}

function modSol() {
	var aIdSoli = $('#mIdSolicitante').val();
	var aApePaterno = $('#modApePaSol').val();
	var aApeMaterno = $('#modApeMaSol').val();
	var aNombres = $('#modNomSol').val();
	var aIdDependencia = $('#modIdDepSol').val();
	var aActivo = "S";//$('#mActivo').val();
	$.ajax({
		type : "POST",
		url : "./../Solicitante.do",
		dataType : 'json',
		data : {
			sIdSol : aIdSoli,
			sApePaterno : aApePaterno,
			sApeMaterno : aApeMaterno,
			sNombres : aNombres,
			sIdDependencia : aIdDependencia,
			sActivo : aActivo,
			"accion" : "grabar"
		},
		success : function(msg) {
			alert('Success insert data' + msg);
		}
	})

	.done(function(data) {
		cargarDependencias();
	});
	$("#modSolicitante").modal("hide");
	$("#addData").modal("show");
	cargarSoli();

}

function editarSoli() {
	var idSoli = $('#mIdSolicitante').val();
	//alert("este "+idSoli);
	$.get("./../Solicitante.do", {
		accion : "recuperarPorIdSolicitante",
		IdSolicitante : idSoli
	}, function(data) {
		//alert(data);
		var p = JSON.parse(data);
		//alert(p.usuarios[0].idUsuario);
		$('#modIdSol').val(p.solicitantes[0].idSolicitante);
		$('#modNomSol').val(p.solicitantes[0].nombres);
		$('#modApePaSol').val(p.solicitantes[0].apePaterno);
		$('#modApeMaSol').val(p.solicitantes[0].apeMaterno);
		$('#modIdDepSol').val(p.solicitantes[0].idDependencia);
		$('#modActivoSol').val(p.solicitantes[0].activo);
	});
}

function confirmaEliminacion() {
	var idSoli = $('#mIdSolicitante').val();
	var activo = "N";
	//alert("este "+idSoli);
	$.ajax({
		type : "POST",
		url : "./../Solicitante.do",
		dataType : 'json',
		data : {
			sIdSol : idSoli,
			anulActivo : activo,
			"accion" : "anular"
		},
		success : function(msg) {
			alert('Success insert data' + msg);
		}
	});
	$("#anularSoli").modal("hide");
	$("#addData").modal("show");
	cargarSoli();
}

// // // // // // // // // // Limpiar  Formulario de Incidencia // // // // // // // // // //

$('#btnNuevoInc').click(function(){
	var now = new Date();
	var month = (now.getMonth() + 1);
	var day = now.getDate();
	if (month < 10)
		month = "0" + month;
	if (day < 10)
		day = "0" + day;

	var fe = now.getFullYear() + '-' + month + '-'
			+ day;
	var today = now.getFullYear() + '-' + month
			+ '-' + day + 'T' + now.getHours()
			+ ':' + now.getMinutes();
	var fecha = today.replace("T", " ");
	$('#mFechaIng').val(today);
})