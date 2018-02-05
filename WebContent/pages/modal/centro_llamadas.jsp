
<!--Agregar Incidencia -->
<div class="modal fade bd-example-modal-lg" id="addData" tabindex="-1" role="dialog" aria-labelledby="addData" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header badge-secondary">
                <h5 class="modal-title">Registro de Incidencia</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
				</button>
            </div>
            <div class="modal-body">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-table"></i> Formulario de Incidencia
                    </div>
                    <div class="card-body">
                        <form name="mfrmDatosIncidencias" id="mfrmDatosIncidencias">
                            <input type="hidden" class="form-control" name="mIdIncidencia" id="mIdIncidencia" value="0">
                            <div class="form-group row">
                                <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Nro Incidencia</span></label>
                                <div class="col-md-12 col-md-offset-4">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></div>
                                        <input type="text" class="form-control" name="mNumIncidencia" id="mNumIncidencia" disabled="disabled" value="0">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-6 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Fecha de Ingreso</span></label>
                                <label class="col-md-6 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Tipo de Ingreso</span></label>
                                <div class="col-md-6 col-md-offset-4">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></div>
                                        <input type="datetime-local" class="form-control" name="mFechaIng" id="mFechaIng">
                                    </div>
                                </div>
                                <div class="col-md-6 col-md-offset-4">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></div>
                                        <select id="mIdTipIng" name="mIdTipIng" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
								            <option value="0">Seleccione Tipo de Ingreso</option>
								        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Area</span> </label>
                                <div class="col-md-12 col-md-offset-4">
                                    <select id="mArea" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;" onchange="cargarArea();">
								        <option value="0">Seleccione Area...</option>
								        <option value="adm">ADMINISTRATIVO</option>
								        <option value="juric">JURIDICCIONAL</option>
								    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Dependencia</span> </label>
                                <div class="col-md-12 col-md-offset-4">
                                    <select id="mIdDependencia" name="mIdDependencia" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
								        <option value="0">Seleccione Dependencia...</option>
								    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Ubicación</span> </label>
                                <div class="col-md-12 col-md-offset-4">
                                    <select id="mIdUbicacion" name="mIdUbicacion" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
											<option value="0">Seleccione Ubicacion...</option>
										</select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Solicitante</span> </label>
                                <div class="col-md-9 col-md-offset-4">
                                    <select id="mIdSolicitante" name="mIdSolicitante" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
								    </select>
                                </div>
                                <div class="col-md-3 col-md-offset-4">
                                    <a class="btn btn-success btn-sm" onclick="addSoli();" class="btn btn-primary"> <i
											class="fa fa-plus-square fa-lg"></i>
								    </a>
                                    <a onclick="modSoli();" href="#modSolicitante" onmouseover="editarSoli()" class="btn btn-primary btn-sm"> <i class="fa fa-pencil"></i>
										</a> <a class="btn btn-danger btn-sm" href="#anularSoli" onclick="anulSoli();" class="btn btn-primary"> <i
											class="fa fa-trash-o"></i>
										</a>
                                    <!--  
										<div class="col-md-3 col-md-offset-4">
										<button onclick="addSoli();"><i class="fa fa-plus fa-lg"></i></button>
										<button onclick="modSoli();" onmouseover="editarSoli()"><i class="fa fa-pencil fa-lg"></i></button>
										<button onclick="anulSoli();"><i class="fa fa-trash-o fa-lg"></i></button>
										</div>
										-->
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Incidencia</span> </label>
                                <div class="col-md-12 col-md-offset-4">
                                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                        <textarea class="form-control" name="mDetIncidencia" id="mDetIncidencia" cols="30" rows="5" placeholder="Detalle de Incidencia " style="text-transform:uppercase;font-size:14px;"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Asignado a</span> </label>
                                <div class="col-md-12 col-md-offset-4">
                                    <select id="mIdUsuario" name="mIdUsuario" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
											<option value="0">Selecciona Usuario</option>
										</select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Situacion Ticket</span> </label>
                                <div class="col-md-12 col-md-offset-4">
                                    <select id="mIdSiTicket" name="mTdSiTicket" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
											<option value="0">Selecciona Situacion</option>
									</select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-4 col-form-label"><span style="text-transform:uppercase;font-size:14px;">ACTIVO</span> </label>
                                <div class="col-md-6 col-md-offset-4 text-center">
                                    <input type="checkbox" class="form-check-input" id="mActivo" checked>
                                </div>
                            </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button type="submit" onclick="saveData()" class="btn btn-primary">Grabar</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Anular Incidencia -->
<div class="modal fade" id="anular" data-backdrop="static">
    <div class="modal-dialog modal-md" role="document">
        <div class="modal-content">

            <div class="modal-header badge-primary">
                <h5 class="modal-title">Anular Registro de Incidencia</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				</button>
            </div>

            <div class="modal-body">
                <form name="mfrmAnular" class="form-horizontal">
                    <input type="hidden" id="aIdIncidencia" value="0">
                    <div class="form-group row">
                        <label class="col-md-3 col-form-label"><b>Nro. Incidencia:</b></label>
                        <div class="col-md-3 col-md-offset-4">
                            <input id="aNumIncidencia" name="aNumIncidencia" type="text" class="form-control" disabled="disabled">
                        </div>
                        <label class="col-md-2 col-form-label"><b>Año:</b></label>
                        <div class="col-md-3 col-md-offset-4">
                            <input id="aAnioIncidencia" name="aAnioIncidencia" type="text" class="form-control" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 col-form-label"><b>Motivo:</b></label>
                        <div class="col-md-8 col-md-offset-4">
                            <textarea class="form-control" name="aMotivoAnular" id="aMotivoAnular" cols="30" rows="5" placeholder="Motivo de Anulacion"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <button type="submit" onclick="anularData();" class="btn btn-primary">Anular</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!--Agregar Solicitante -->
<div class="modal fade" id="addSolicitante" tabindex="-1" role="dialog" aria-labelledby="addData" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header badge-primary">
                <h5 class="modal-title">Datos del Solicitante</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				</button>
            </div>
            <div class="modal-body">
                <form name="mfrmDatosSolicitante" class="form-horizontal">
                    <input type="hidden" id="aIdSol" value="0">
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Nombres:</b></label>
                        <div class="col-md-6 col-md-offset-4">
                            <input id="aNomSol" name="aNomSol" type="text" class="form-control" placeholder="Nombres">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Apellido Paterno:</b></label>
                        <div class="col-md-6 col-md-offset-4">
                            <input id="aApePaSol" name="aApePaSol" type="text" class="form-control" placeholder="Apellidos Paterno">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Apellido Materno:</b></label>
                        <div class="col-md-6 col-md-offset-4">
                            <input id="aApeMaSol" name="aApeMaSol" type="text" class="form-control" placeholder="Apellido Materno">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Dependencia:</b></label>
                        <div class="col-md-8 col-md-offset-4">
                            <select id="aIdDepSol" name="aIdDepSol" class="form-control">
								<option value="0">Seleccione Dependencia...</option>
				            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Activo:</b></label>
                        <div class="col-md-6 col-md-offset-4">
                            <input type="checkbox" id="aActivoSol" name="aActivoSol" checked="checked">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" onclick="cerrar();">Cancelar</button>
                        <button type="button" onclick="saveSol();" class="btn btn-primary">Grabar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Editar Solicitante -->
<div class="modal fade" id="modSolicitante">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header badge-primary">
                <h5 class="modal-title">Datos del Solicitante</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
            </div>
            <!-- Modal Body -->
            <div class="modal-body">
                <form name="mfrmDatosSolicitante" class="form-horizontal">
                    <input type="hidden" value="0">

                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Nombres:</b></label>
                        <div class="col-md-6 col-md-offset-4">
                            <input id="modNomSol" name="modNomSol" type="text" class="form-control" placeholder="Nombres" style="text-transform: uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" onkeyup="validacion('mNombres');"><span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Apellido
											Paterno:</b></label>
                        <div class="col-md-6 col-md-offset-4">
                            <input id="modApePaSol" name="modApePaSol" type="text" class="form-control" placeholder="Apellidos Paterno" style="text-transform: uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" onkeyup="validacion('mApePaterno');"> <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Apellido Materno:</b></label>
                        <div class="col-md-6 col-md-offset-4">
                            <input id="modApeMaSol" name="modApeMaSol" type="text" class="form-control" placeholder="Apellido Materno" style="text-transform: uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" onkeyup="validacion('mApeMaterno');"> <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Dependencia:</b></label>
                        <div class="col-md-8 col-md-offset-4">
                            <select id="modIdDepSol" name="modIdDepSol" class="form-control" onchange="validacion('mIdDependencia');">
											<option value="0">Seleccione Dependencia...</option>
										</select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-form-label"><b>Activo:</b></label>
                        <div class="col-md-6 col-md-offset-4 text-center">
                            <input type="checkbox" class="form-check-input" id="modActivoSol" checked>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" onclick="cerrar1();">Cancelar</button>
                        <button type="button" onclick="modSol();" class="btn btn-primary">Grabar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Anular Solicitante -->
<div class="modal fade" id="anularSoli" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header badge-danger">
                <h5 class="modal-title" id="exampleModalLabel">Desea Anular Solicitante
                </h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
            </div>
            <div class="modal-body" id="mensaje">
                <input type="hidden" id="anulSolicitante" name="anulSolicitante"> Si desea anular este solicitante denle en "Aceptar".
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="cerrar2();">Cancelar</button>
                <button type="button" class="btn btn-danger" onclick="confirmaEliminacion();">Aceptar </button>
            </div>
        </div>
    </div>
</div>

<!-- Logout Modal-->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">¿Listo para salir?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">Seleccione "Cerrar sesión" a continuación si está listo para finalizar su sesión actual.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancelar</button>
						<a class="btn btn-danger" href="../login.html">Cerrar Sesión</a>
					</div>
				</div>
			</div>
		</div>