<%@ include file="head.jsp"%>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="menu.jsp">Menu Principal</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="soporte_tecnico.jsp">Soporte Tecnico</a>
        </li>
        
      </ol>
      <!-- Icon Cards-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Tabla de Atenciones</div>
          
        <div class="card-body">
		<div>
			<!-- <button class="btn btn-primary btn-sm active" data-toggle="modal" data-target="#addData"><i class="fa fa-phone"></i>&nbsp Nueva Incidencia</button> -->
			<button class="btn btn-success btn-sm active" onclick="tableToExcel('dataTables-example', 'Tabla de Usuarios')" ><i class="fa fa-file-excel-o "></i>&nbsp Exportar a Excel</button>
		</div>
		<hr>
			<form name="mfrmFechas" method="get" class="form-vertical">
					<div class="form-group row">			  
					<div class="col-md-5 col-md-offset-4">
				   		<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon"><i class="fa fa-calendar-times-o fa-lg"></i></div>
							<div class="input-group-addon"><label>&nbspDesde</label></div>
							<input type="date" class="form-control" name="mFechaInicio" id="mFechaInicio" placeholder="Fecha desde">
						</div>	
				   	</div>
				   	<div class="col-md-5 col-md-offset-4">
				   		<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon"><i class="fa fa-calendar-times-o fa-lg"></i></div>
							<div class="input-group-addon"><label>&nbspHasta</label></div>
							<input type="date" class="form-control" name="mFechaFin" id="mFechaFin" placeholder="Fecha hasta">
						</div>	
				   	</div>
				   	<div class="col-md-2 col-md-offset-4">
				   		<button type="button" class="btn btn-primary form-control active" id="btnFecha" onClick="loadIncidencias()"><i class="fa fa-search fa-lg"></i> Buscar</button>	
				   	</div>
				</div>
			</form>
	
		<hr>
		
          <div class="table-responsive">
            <table class="table table-bordered table-striped" id="dataTables-example" width="100%" cellspacing="0" cellpadding="0" style="text-transform:uppercase;font-size:14px;">
              <thead class="thead-inverse">
                <tr>
					<th>ID</th>
					<th>NRO.</th>
			        <th>FECHA</th>
					<th>INGRESO</th>
					<th>DEPENDENCIA</th>
					<th>SOLICITANTE</th>
					<th>INCIDENCIA</th>
					<th>RESPONSABLE</th>
					<th>ESTADO</th>
					<th>ACCION</th>
                </tr>
                
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div>
      <!-- Area Chart Example-->
      <!-- Example DataTables Card-->
      <!-- Modal -->
    <%@ include file="modal/soporte_tecnico.jsp"%>
	<!-- FIN DE MODAL -->
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Derechos de Autor © Sistema de Soporte Tecnico 2017</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <!-- CODIGO INSERTADO -->
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
    <script src="js/sb-admin-charts.min.js"></script>
    <script src="js/tableToExcel.js"></script>
    <script type="text/javascript" class="init" src="js/funciones/soporte_tecnico.js"></script>
  </div>
</body>
</html>
