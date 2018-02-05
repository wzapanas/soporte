<%@ include file="head.jsp"%>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="menu.jsp">Menu Principal</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="g_usuario.jsp">Gestion de Equipos</a>
        </li>
        
      </ol>
      <!-- Icon Cards-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-user-o"></i> Tabla de Equipos</div>
          
        <div class="card-body">
        
		<div>
        <button class="btn btn-primary btn-sm active" data-toggle="modal" data-target="#addData"><i class="fa fa-desktop fa-lg"></i>&nbsp Nuevo Equipo</button>
        <button class="btn btn-success btn-sm active" onclick="tableToExcel('dataTables-example', 'Tabla de Usuarios')" ><i class="fa fa-file-excel-o fa-lg"></i>&nbsp Exportar a Excel</button>
		</div>
		<hr>
          <div class="table-responsive">
            <table class="table table-bordered table-striped" id="dataTables-example" width="100%" cellspacing="0" cellpadding="0" style="text-transform:uppercase;font-size:14px;">
              <thead class="thead-inverse">
                <tr>
					<th>GRUPO</th>
					<th>EQUIPO</th>
					<th>MARCA</th>
					<th>MODELO</th>
					<th>SERIE</th>
					<th>PATRIMONIO</th>
					<th>ASIGNADO</th>
					<th>ACCION</th>
                </tr>
              </thead>  
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Soporte Tecnico</div>
      </div>
      <!-- Area Chart Example-->
      <!-- Example DataTables Card-->
      <!-- Modal -->
	<%@ include file="modal/g_equipo.jsp"%>
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
    <script type="text/javascript" src="js/funciones/g_equipo.js" class="init"></script>
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
  </div>
</body>

</html>
