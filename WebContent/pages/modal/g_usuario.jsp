    <div class="modal fade" id="addData" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  		<div class="modal-dialog" role="document">
   		 	<div class="modal-content">
	      		<div class="modal-header badge-secondary">
        			<h5 class="modal-title">Registro de Usuarios</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<!-- Modal Body -->
      			<div class="modal-body">
      				<div class="card mb-3">
      				<div class="card-header">
							<i class="fa fa-user-o"></i> Formulario de Usuario
						</div>
						<div class="card-body">
						<form name="mfrmDatosUsuario" id="mfrmDatosUsuario" class="form-horizontal">				
							<input type="hidden" id="mIdUsuario" value="0">
							<div class="form-group row">
								<label class="input-group col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">USUARIO</span></label>				
								<div class="col-md-12 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-user-o fa-lg"></i></div>
										<input type="text" class="form-control" id="mUsuario" name="mUsuario" placeholder="Usuario" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();" placeholder="Usuario" >
									</div>			
								</div>	
							</div>
							<div class="form-group row">			  
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">APELLIDO PATERNO</span></label>
								<div class="col-md-12 col-md-offset-4">
							   		<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></div>
										<input type="text" class="form-control" id="mApePaterno" name="mApePaterno" placeholder="Apellidos Paterno" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();">
									</div>	
							   	</div>
							</div>
							<div class="form-group row">			  
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">APELLIDO MATERNO</span></label>
								<div class="col-md-12 col-md-offset-4">
							   		<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></div>
										<input type="text" class="form-control" id="mApeMaterno" name="mApeMaterno" placeholder="Apellidos Materno" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();">
									</div>
							   	</div>	
							</div>
							<div class="form-group row">			  
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">NOMBRES:</span></label>
								<div class="col-md-12 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-user-circle-o fa-lg"></i></div>
							   			<input id="mNombres" name="mNombres" type="text" class="form-control" placeholder="Nombres" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();" onkeyup="validacion('mNombres');"><span class="help-block"></span>
							   		</div>
							   	</div>
							</div>
							<div class="form-group row">			  
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">DNI</span></label>
								<div class="col-md-12 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-vcard fa-lg"></i></div>
							   				<input id="mDni" name="mDni" type="text" class="form-control" placeholder="DNI" style="text-transform:uppercase;font-size:14px;">
							   		</div>
							   	</div>
							</div>	
			                <div class="form-group row">
							   	<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">CORREO</span> </label>	
								<div class="col-md-12 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-envelope fa-lg"></i></div>
							   				<input id="mEmail" name="mEmail" type="text" class="form-control" placeholder="Correo Electronico" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toLowerCase();" onkeyup="validacion('mEmail');">
			                        </div>    
							   	</div>			   	
							</div>
							<div class="form-group row">
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">CONTRASEÑA</span> </label>
								<div class="col-md-12 col-md-offset-4">
									<div class="input-group mb-2 mr-sm-2 mb-sm-0">
										<div class="input-group-addon"><i class="fa fa-unlock-alt fa-lg"></i></div>
							   				<input id="mtxtClave" name="mtxtClave" type="password" class="form-control" placeholder="CONTRASEÑA" style="text-transform:uppercase;font-size:14px;">
			                        </div>     
							   	</div>	
							</div>		
							<div class="form-group row">
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">PERFIL</span> </label>
								<div class="col-md-12 col-md-offset-4">
					                 <select id="mIdRol" name="mIdRol" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
			                                <option value="0">SELECCIONA PERFIL</option>
			                         </select>
							    </div>
							</div>
							<div class="form-group row">
								<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">DEPENDENCIA</span> </label>
								<div class="col-md-12 col-md-offset-4">
					                 <select id="mIdDependencia" name="mIdDependencia" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
			                                <option value="0">SELECCIONA DEPENDENCIA</option>
			                            </select>
							   	</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">ACTIVO</span> </label>
								<div class="col-md-6 col-md-offset-4 text-center">
							   		<input type="checkbox" class="form-check-input" id="mActivo" checked>
							   	</div>	
							</div>	
							</div>
						<div class="card-footer small text-muted"></div>
					</div>	
							          
						    <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						        <button type="submit" onclick="saveData();" class="btn btn-primary">Grabar</button>
						    </div>
					      </form>
		    </div>	
    		</div>
 		 </div>
	</div>
	
	<div class="modal fade" id="anular" data-backdrop="static">
	  <div class="modal-dialog modal-md" role="document">
	    <div class="modal-content">
	      <div class="modal-header bg-primary">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="addLabel">Anular Este Usuario</h4>
	      </div>	      
	      <div class="modal-body">
		      <form name="mfrmAnular" class="form-horizontal">				
				<input type="hidden" id="aIdIncidencia" value="0">
				<div class="form-group">
					<label class="control-label col-sm-3" >ID. Usuario:</label>				
					<div class="col-sm-5">
						<input id="aIdUsuario" name="aIdUsuario" type="text" class="form-control" disabled="disabled">
					</div>					  	
				</div>
				<div class="form-group">			  
					<label class="control-label col-sm-3" >Usuario:</label>
					<div class="col-sm-7">
				   		<input id="aUsuario" name="aUsuario" type="text" class="form-control" disabled="disabled">
				   	</div>
				</div>
				<div class="form-group">
                    <label class="control-label col-sm-3">Motivo: </label>
                    <div class="col-sm-7">
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
	
	
	
	<!-- ANULAR USUARIO -->
	
	<div class="modal fade" id="anularUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header badge-danger">
            <h5 class="modal-title" id="exampleModalLabel">Desea Anular Usuario</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body" id="mensaje">
          <input type="hidden" id="idEliminar" name="idEliminar">
          Si desea anular este usuario denle en "Aceptar".</div>
          <div class="modal-footer">
           	<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-danger" href="g_usuario.jsp" onclick="confirmaEliminacion();">Aceptar</a>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Logout Modal-->
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
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-danger" href="../login.html">Cerrar Sesión</a>
          </div>
        </div>
      </div>
    </div>    