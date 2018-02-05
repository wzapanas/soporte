    <div class="modal fade" id="addData" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  		<div class="modal-dialog" role="document">
   		 	<div class="modal-content">
	      		<div class="modal-header badge-secondary">
        			<h5 class="modal-title">Registro de Equipos</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<!-- Modal Body -->
      			<div class="modal-body">
      			<div class="card mb-3">
			        <div class="card-header"><i class="fa fa-user-plus"></i> Formulario Equipo</div>
			        <div class="card-body">
			        <form name="mfrmDatosEquipo" class="form-horizontal">				
						<input type="hidden" id="mIdEquipo" value="0">
						<div class="form-group row">
							<label class="input-group col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Grupo</span></label>			
							<div class="col-md-12 col-md-offset-4">
								<div class="input-group mb-2 mr-sm-2 mb-sm-0">
									<div class="input-group-addon"><i class="fa fa-cubes fa-lg"></i></div>
									<input id="mGrupo" name="mGrupo" type="number" class="form-control" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" placeholder="Grupo" onkeyup="validacion('mUsuario');">			
								</div>
							</div>					  	
						</div>
						<div class="form-group row">
							<label class="input-group col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Ultimo Grupo</span></label>			
							<div class="col-md-12 col-md-offset-4">
								<div class="input-group mb-2 mr-sm-2 mb-sm-0">
									<div class="input-group-addon"><i class="fa fa-cubes fa-lg"></i></div>
									<input id="ultimoGrupo" type="text" title="Ultimo Grupo" value="0" class="form-control" disabled="disabled">	
								</div>
							</div>					  	
						</div>
						<div class="form-group row">
							<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Local</span> </label>
							<div class="col-md-12 col-md-offset-4">
				                 <select id="mIdLocal" name="mIdLocal" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
		                                <option value="0">Seleccione Local </option>
		                            </select>
						   	</div>
						</div>
						<div class="form-group row">
							<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Tipo De Equipo</span> </label>
							<div class="col-md-12 col-md-offset-4">
				                 <select id="mTipEquipo" name="mTipEquipo" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
		                                <option value="0">Seleccione Tipo de Equipo </option>
		                            </select>
						   	</div>
						</div>
						<div class="form-group row">
							<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Marca</span> </label>
							<div class="col-md-12 col-md-offset-4">
				                 <select id="mIdMarca" name="mIdMarca" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
		                                <option value="0">Seleccione Marca</option>
		                            </select>
						   	</div>
						</div>
						<div class="form-group row">
							<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Modelo</span> </label>
							<div class="col-md-12 col-md-offset-4">
				                 <select id="mIdModelo" name="mIdModelo" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
		                                <option value="0">Seleccione Modelo </option>
		                            </select>
						   	</div>
						</div>
						<div class="form-group row">
						   	<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Serie</span> </label>	
							<div class="col-md-12 col-md-offset-4">
								<div class="input-group mb-2 mr-sm-2 mb-sm-0">
									<div class="input-group-addon"><i class="fa fa-imdb fa-lg"></i></div>
								<input id="mSerie" name="mSerie" type="text" class="form-control" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();" placeholder="Serie" onkeyup="validacion('mUsuario');">			
		                        </div>			
							</div>					  	
						</div>
						<div class="form-group row">
						   	<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Codigo Patrimonial</span> </label>	
							<div class="col-md-12 col-md-offset-4">
								<div class="input-group mb-2 mr-sm-2 mb-sm-0">
									<div class="input-group-addon"><i class="fa fa-imdb fa-lg"></i></div>
						   		<input id="mPatrimonio" name="mPatrimonio" type="text" placeholder="Patrimonio" class="form-control" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();" placeholder="Codigo Patrimonial" onkeyup="validacion('mUsuario');">			
						   		<span class="help-block"></span>
						   		</div>
						   	</div>
						</div>	
								
						<div class="form-group row">
						   	<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Fecha de Compra</span> </label>	
							<div class="col-md-12 col-md-offset-4">
								<div class="input-group mb-2 mr-sm-2 mb-sm-0">
									<div class="input-group-addon"><i class="fa fa-calendar-times-o fa-lg"></i></div>
						   		<input id="mFecCompra" name="mFecCompra" type="datetime-local" placeholder="Patrimonio" class="form-control" style="text-transform:uppercase;font-size:14px;" class="form-control" style="text-transform:uppercase;" placeholder="Grupo" onkeyup="validacion('mUsuario');">			
						   		</div>
						   	</div>
						</div>
						
						<div class="form-group row">
							<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Estado de Equipo</span> </label>
							<div class="col-md-12 col-md-offset-4">
				                 <select id="mIdEstado" name="mIdEstado" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
		                                <option value="0">Seleccione Estado</option>
		                            </select>
						   	</div>
						</div>
									
						<div class="form-group row">
							<label class="col-md-4 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Activo</span> </label>
							<div class="col-md-6 col-md-offset-4 text-center">
						   		<input type="checkbox" class="form-check-input" id="mActivo" checked>
						   	</div>	
						</div>	
						</div>
			       			<div class="card-footer small text-muted"></div>
					</div>	
					    <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					        <button type="submit" onclick="saveEquipo();" class="btn btn-primary active">Grabar</button>
					    </div>
				      </form>
		    </div>	
    		</div>
 		 </div>
	</div>
	
	
	<div class="modal fade bd-example-modal-lg" id="asignar" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  		<div class="modal-dialog modal-lg" role="document">
   		 	<div class="modal-content">
	      		<div class="modal-header badge-secondary">
        			<h5 class="modal-title">Asignar a</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<!-- Modal Body -->
      			<div class="modal-body">
		      <div class="card mb-3">
			        <div class="card-header"><i class="fa fa-user-plus"></i> Formulario de Asignacion</div>
			        <div class="card-body">
			        	<form name="mfrmAsignado" class="form-horizontal">				
						<input type="hidden" id="mIdAsignado" value="0">
						<input type="hidden" id="mGrupo_" value="0">
						<div class="form-group row">
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Asignar a</span> </label>
								<div class="col-md-12 col-md-offset-4">
		                        <select id="mIdSolicitante" name="mIdSolicitante" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
		                                <option value="0">Seleccione una persona</option>
		                            </select>			
							</div>					  	
						</div>
                        <div class="form-group row">
                            <label class="col-md-12 col-form-label"><span style="text-transform:uppercase;font-size:14px;">Area</span> </label>
	                            <div class="col-md-12 col-md-offset-4">
	                                <select id="mArea" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;" onchange="cargarArea();">
								        <option value="0">Seleccione Area...</option>
								        <option value="adm">Administrativo</option>
								        <option value="juric">Juridiccional</option>
								    </select>
	                            </div>
                        </div>
						<div class="form-group row">
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Dependencia</span> </label>
								<div class="col-md-12 col-md-offset-4">
				                 <select id="mIdDependencia" name="mIdDependencia" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
		                                <option value="0">Seleccione una dependencia</option>
		                            </select>
						   	</div>
						</div>
						<div class="form-group row">
								<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Cargo</span></label>
								<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Fecha</span></label>
							   	<div class="col-md-6 col-md-offset-4">
					                 <select id="mIdCargo" name="mIdCargo" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
			                                <option value="0">Seleccione un Cargo </option>
			                            </select>
							   	</div>
							   	<div class="col-md-6 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-calendar-times-o fa-lg"></i></div>
							   		<input id="mFecAsig" name="mFecAsig" type="datetime-local" class="form-control" style="text-transform:uppercase;font-size:14px;" class="form-control" style="text-transform:uppercase;">			
							   		</div>
							   	</div>
						</div>
						<div class="form-group row">
							   	<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Piso</span> </label>	
							   	<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Nro Oficina</span> </label>	
								<div class="col-md-6 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-envelope fa-lg"></i></div>
								<input id="mPiso" name="mPiso" type="text" class="form-control" placeholder="Serie" style="text-transform:uppercase;font-size:14px;">
									</div>	
								</div>
								<div class="col-md-6 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-envelope fa-lg"></i></div>
						   		<input id="mNroOficina" name="mNroOficina" type="text" class="form-control" placeholder="Nro Oficina" style="text-transform:uppercase;font-size:14px;">
						   			</div>
						   		</div>				  	
						</div>
						
						<div class="form-group row">
							   	<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Observacion</span> </label>	
								<div class="col-md-12 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
								<textarea class="form-control" name="mObservacion" id="mObservacion" cols="30" rows="5" placeholder="Observacion" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();"></textarea>
								<span class="help-block"></span>
									</div>
								</div>
						</div>
									
						<div class="form-group row">
							<label class="col-md-4 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Activo</span> </label>	
							<div class="col-md-6 col-md-offset-4 text-center">
						   		<input type="checkbox" class="form-check-input" id="mActivo" checked>
						   	</div>	
						</div>	
						</div>
			        		<div class="card-footer small text-muted"></div>
					</div>
						
						<div class="card mb-3">
					        <div class="card-header"><i class="fa fa-user-plus"></i> Equipo asignados</div>
					        <div class="card-body">
					        <div class="table-responsive">
							<table class="table table-bordered table-striped" id="dataTablaEquipo" width="100%" cellspacing="0" cellpadding="0" style="text-transform:uppercase;font-size:14px;">
								<thead class="thead-inverse">
									<tr>
										<th>ID EQUIPO</th>
										<th>GRUPO</th>
										<th>EQUIPO</th>
										<th>SERIE</th>
										<th>MODELO</th>
										<th>PATRIMONIO</th>
										<th>NOM_EQ</th>
										<th>IP</th>
										<th>ACCION</th>
										<th>ACCION</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
							</div>
					        </div>
					        <div class="card-footer small text-muted"></div>
						</div>
						
						<div class="card mb-3">
					        <div class="card-header"><i class="fa fa-user-plus"></i> Equipo no asignados</div>
					        <div class="card-body">
					        	<div class="table-responsive">
								<table class="table table-bordered table-striped" id="dataTablaEquipoNoAsignados" width="100%" cellspacing="0" cellpadding="0" style="text-transform:uppercase;font-size:14px;">
									<thead class="thead-inverse">
										<tr>
											<th>ID EQUIPO</th>
											<th>GRUPO</th>
											<th>EQUIPO</th>
											<th>SERIE</th>
											<th>MODELO</th>
											<th>PATRIMONIO</th>
											<th>NOMBRE EQUIPO</th>
											<th>DIRECCION IP</th>
											<th>ACCION</th>
											<th>ACCION</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
								</div>
					        </div>
					        <div class="card-footer small text-muted"></div>
						</div>
						
					    <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					        <button type="submit" onclick="saveAsignado();" class="btn btn-primary">Grabar</button>
					    </div>
				      </form>
		    </div>	
    		</div>
 		 </div>
	</div>
	
	<!-- Start Modal - Asignado Equipo -->
	<div class="modal fade" id="editAsignadoEquipo" tabindex="-1" role="dialog" aria-labelledby="editAsignadoEquipo" aria-hidden="true">
  		<div class="modal-dialog" role="document">
   		 	<div class="modal-content">
	      		<div class="modal-header badge-secondary">
        			<h5 class="modal-title">Registro de Equipo Asignado</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<!-- Modal Body -->
      			<div class="modal-body">
      				<div class="card mb-3">
      				<div class="card-header">
							<i class="fa fa-user-o"></i> Formulario de Equipo Asignado
						</div>
						<div class="card-body">
						<form name="mfrmDatosEquipoAsignado" id="mfrmDatosEquipoAsignado" class="form-horizontal">	
							<input type="hidden" id="asigIdEq" value="0">			
							<div class="form-group row">
								<label class="input-group col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Nombre Equipo</span></label>				
								<div class="col-md-12 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-user-o fa-lg"></i></div>
										<input type="text" class="form-control" id="asigNomEq" name="asigNomEq" placeholder="Usuario" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();" placeholder="Usuario" >
									</div>			
								</div>	
							</div>
							<div class="form-group row">			  
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Direccion Ip</span></label>
								<div class="col-md-12 col-md-offset-4">
							   		<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></div>
										<input type="text" class="form-control" id="asigDirIp" name="asigDirIp" placeholder="Apellidos Paterno" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();">
									</div>	
							   	</div>
							</div>
							</div>
						</div>	
							          
						    <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						        <button type="button" onclick="editarDatosTablaAsiEq();" class="btn btn-primary" data-dismiss="modal">Grabar</button>
						    </div>
					     </form>
		    	</div>	
    		</div>
 		 </div>
	</div>
	<!-- End Modal - Asignado Equipo -->
	
	<!-- Star Modal - Sin Grupo -->
	<div class="modal fade" id="sinGrupo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header badge-danger">
            <h5 class="modal-title" id="exampleModalLabel">No tiene Grupo</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body" id="mensaje">Por favor Asigne un grupo al Equipo</div>
          <div class="modal-footer">
            <a class="btn btn-danger" href="g_equipo.jsp">Aceptar</a>
          </div>
        </div>
      </div>
    </div>
    <!-- End Modal - Sin Grupo -->
    
    <!-- Start Modal - Salir -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">¿Listo para salir?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Seleccione "Cerrar sesión" a continuación si está listo para finalizar su sesión actual.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-toggle="modal" href="#asignar">Cancelar</button>
            <a class="btn btn-danger" href="../login.html">Cerrar Sesión</a>
          </div>
        </div>
      </div>
    </div>  
    <!-- End Modal - Salir -->  
    
	<!-- Start Modal Anular Equipo -->    
    
    	<div class="modal fade" id="anular" data-backdrop="static">
	  <div class="modal-dialog modal-md" role="document">
	    <div class="modal-content">
	      <div class="modal-header badge-primary">
              <h5 class="modal-title">Motivo de Anulacion</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
         </div>
	      <div class="modal-body">
		<div class="card mb-3">
		     <div class="card-header"><i class="fa fa-user-plus"></i> Formulario de Anulacion</div>
		     <div class="card-body">	      
		      <form name="mfrmAnular" class="form-horizontal">				
				<input type="hidden" id="aIdIncidencia" value="0">
				<div class="form-group row">
					<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Nro Equipo</span></label>
				   	<div class="col-md-12 col-md-offset-4">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon"><i class="fa fa-envelope fa-lg"></i></div>
		   					<input id="aIdEquipo" name="aIdEquipo" type="text" class="form-control" placeholder="Num Incidencia" style="text-transform:uppercase;font-size:14px;" disabled="disabled">
		   				</div>
			   		</div>
				</div> 
             	<div class="form-group row">
				   	<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Motivo</span> </label>	
					<div class="col-md-12 col-md-offset-4">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
					<textarea class="form-control" name="aMotivoAnular" id="aMotivoAnular" cols="30" rows="5" placeholder="Motivo de Anulacion" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();"></textarea>
					<span class="help-block"></span>
						</div>
					</div>
				</div>
			</div>
		</div>		
			    <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			        <button type="submit" onclick="anularEquipo();" class="btn btn-primary">Anular</button>
			    </div>
		      </form>
		    </div>	     
	    </div>
	  </div>
	</div>