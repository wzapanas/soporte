    <div class="modal fade" id="addData" data-backdrop="static">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	    
	      <div class="modal-header badge-secondary">
              <h5 class="modal-title">Ficha de Atencion</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
         </div>
	      <div class="modal-body">
	      <div class="card mb-3">
		     <div class="card-header"><i class="fa fa-user-plus"></i> Formulario de Atencion</div>
		     <div class="card-body">
		      <form name="mfrmDatosAtencion" class="form-horizontal" id="mfrmDatosAtencion">				
					<input type="hidden" class="form-control" name="mIdIncidencia" id="mIdIncidencia">
				<div class="form-group row">
					<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Nro Incidencia</span></label>
					<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Nro Atencion</span></label>
				   	<div class="col-md-6 col-md-offset-4">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon"><i class="fa fa-envelope fa-lg"></i></div>
		   					<input id="mNumIncidencia" name="mNumIncidencia" type="text" class="form-control" placeholder="Num Incidencia" style="text-transform:uppercase;font-size:14px;" disabled="disabled">
		   				</div>
			   		</div>
				   	<div class="col-md-6 col-md-offset-4">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon"><i class="fa fa-calendar-times-o fa-lg"></i></div>
				   		<input id="mIdAtencion" name="mIdAtencion" type="text" class="form-control" style="text-transform:uppercase;font-size:14px;" class="form-control" style="text-transform:uppercase;" value="0" disabled="disabled">			
				   		</div>
				   	</div>
				</div>   
				<div class="form-group row">
					<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Fecha Termino</span> </label>
					<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Tipo de Atencion</span> </label>
					<div class="col-md-6 col-md-offset-4">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon"><i class="fa fa-calendar-times-o fa-lg"></i></div>
				   			<input id="mFechaTerm" name="mFechaTerm" type="datetime-local" class="form-control" style="text-transform:uppercase;font-size:14px;" class="form-control" style="text-transform:uppercase;">			
				   		</div>
				    </div>
				    <div class="col-md-6 col-md-offset-4">
						<select id="mTipoAtencion" name="mTipoAtencion" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
                               <option value="0">Selecciona Atencion</option>
                        </select>
				    </div>
				</div>    
				<div class="form-group row">
					<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Tipo Servicio</span> </label>
					<div class="col-md-12 col-md-offset-4">
						<select id="mTipoServicio" name="mTipoServicio" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
                               <option value="0">Selecciona Servicio</option>
                        </select>
				    </div>
				</div>
				<div class="form-group row">
					<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Equipo</span> </label>
					<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Marca</span> </label>
					<div class="col-md-6 col-md-offset-4">
						<select id="mTipoEquipo" name="mTipoEquipo" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
                               <option value="0">Selecciona Servicio</option>
                        </select>
				    </div>
				    <div class="col-md-6 col-md-offset-4">
						<select id="mIdMarca" name="mMarca" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
                               <option value="0">Seleccione Marca</option>
                        </select>
				    </div>
				</div>   
				<div class="form-group row">
					<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Modelo</span> </label>
					<label class="col-md-6 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Serie</span> </label>
					<div class="col-md-6 col-md-offset-4">
						<select id="mIdModelo" name="mModelo" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
                               <option value="0">Selecciona Modelo</option>
                        </select>
				    </div>
				    <div class="col-md-6 col-md-offset-4">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon"><i class="fa fa-calendar-times-o fa-lg"></i></div>
				   		<input id="mSerie" name="mSerie" type="text" class="form-control" placeholder="Serie" style="text-transform:uppercase;font-size:14px;" class="form-control" style="text-transform:uppercase;" value="0">			
				   		</div>
				    </div>
				</div>       		
             	<div class="form-group row">
				   	<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Detalle Servicio</span> </label>	
					<div class="col-md-12 col-md-offset-4">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
					<textarea class="form-control" name="mDetServicio" id="mDetServicio" cols="30" rows="5" placeholder="Detalle del Servicio" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();"></textarea>
					<span class="help-block"></span>
						</div>
					</div>
				</div>
				<div class="form-group row">
				   	<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Observaciones</span> </label>	
					<div class="col-md-12 col-md-offset-4">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
					<textarea class="form-control" name="mObservaciones" id="mObservaciones" cols="30" rows="5" placeholder="Observaciones" style="text-transform:uppercase;font-size:14px;" onkeyup="javascript:this.value=this.value.toUpperCase();"></textarea>
					<span class="help-block"></span>
						</div>
					</div>
				</div>
				<div class="form-group row">
						<label class="col-md-12 col-form-label" ><span style="text-transform:uppercase;font-size:14px;">Estado</span> </label>
						<div class="col-md-12 col-md-offset-4">
		                 <select id="mTipoEstado" name="mTipoEstado" class="form-control custom-select" style="text-transform:uppercase;font-size:14px;">
                                <option value="0">Seleccione una Estado</option>
                         </select>
				   	</div>
				</div>				
				   		<input type="hidden" class="form-control hidden" id="mActivo" name="mActivo" checked>						     
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
	
	<!-- Modal Solicitante -->
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
		      <form name="mfrmAnular" class="form-horizontal">				
				<input type="hidden" id="aIdIncidencia" value="0">
				<div class="form-group row">
					<label class="control-label col-sm-3" ><b>NRO. Incidencia:</b></label>				
					<div class="col-sm-5">
						<input id="aNumIncidencia" name="aNumIncidencia" type="text" class="form-control" disabled="disabled">
					</div>					  	
				</div>
				<div class="form-group row">			  
					<label class="control-label col-sm-3" ><b>Año:</b></label>
					<div class="col-sm-7">
				   		<input id="aAnioIncidencia" name="aAnioIncidencia" type="text" class="form-control" disabled="disabled">
				   	</div>
				</div>
				<div class="form-group row">
                    <label class="control-label col-sm-3"><b>Atenciones:</b></label>
                    <div class="col-sm-7">
                    	<select id="aIdAtencion" name="aIdAtencion" class="form-control" onchange="validacion('mIdUsuario');">
                              <option value="0">Selecciona Atencion...</option>
                        </select>
                    </div>
                </div>
				<div class="form-group row">
                    <label class="control-label col-sm-3"><b>Motivo:</b></label>
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
	
	<!-- Atenciones -->
	
	<div class="modal" id="atenciones" data-backdrop="static">
	  <div class="modal-dialog modal-md" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header badge-primary">
              <h5 class="modal-title">Atenciones</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
         </div>
	      	      
	      <div class="modal-body">
		      <form name="mfrmAnular" class="form-horizontal">				
				<input type="hidden" id="atIdIncidencia" value="0">
				<div class="form-group row">
					<label class="control-label col-sm-3" ><b>NRO. Incidencia:</b></label>				
					<div class="col-sm-5">
						<input id="atNumIncidencia" name="atNumIncidencia" type="text" class="form-control" disabled="disabled">
					</div>					  	
				</div>
				<div class="form-group row">			  
					<label class="control-label col-sm-3" ><b>Año:</b></label>
					<div class="col-sm-7">
				   		<input id="atAnioIncidencia" name="atAnioIncidencia" type="text" class="form-control" disabled="disabled">
				   	</div>
				</div>
				<div class="form-group row">
                    <label class="control-label col-sm-3"><b>Atenciones:</b></label>
                    <div class="col-sm-7">
                    	<select id="atIdAtencion" name="atIdAtencion" class="form-control" onchange="validacion('mIdUsuario');">
                              <option value="0">Selecciona Atencion...</option>
                        </select>
                    </div>
                </div>	
			    <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<button type="button" onclick="cargarDatosAtencion();" class="btn btn-primary">Aceptar</button>
			    </div>
		      </form>
		    </div>	     
	    </div>
	  </div>
	</div>
    
    
    
    <!-- NO HAY ATENCION -->
    
    <div class="modal fade" id="noAtencion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header badge-danger">
            <h5 class="modal-title" id="exampleModalLabel">No tiene Ninguna Atencion</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body" id="mensaje">Esta incidencia, no tiene una atencion registrada.</div>
          <div class="modal-footer">
            <a class="btn btn-danger" href="soporte_tecnico.jsp">Aceptar</a>
          </div>
        </div>
      </div>
    </div>
    
    
    <!-- YA HA SIDO ATENDIDA -->
    
    <div class="modal fade" id="incAtendida" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header badge-success">
            <h5 class="modal-title" id="exampleModalLabel">Esta Incidencia ya ha sido Atendida</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body" id="mensaje">Esta Incidencia esta resulta o pendiente</div>
          <div class="modal-footer">
            <a class="btn btn-success" href="soporte_tecnico.jsp">Aceptar</a>
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
          <div class="modal-body" id="mensaje">Seleccione "Cerrar sesión" a continuación si está listo para finalizar su sesión actual.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-danger" href="../login.html">Cerrar Sesión</a>
          </div>
        </div>
      </div>
    </div>