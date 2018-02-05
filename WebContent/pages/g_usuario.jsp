<%@ include file="head.jsp"%>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="menu.jsp">Menu Principal</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="g_usuario.jsp">Gestion de Usuarios</a>
        </li>
        
      </ol>
      <!-- Icon Cards-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Tabla de Usuarios</div>
          
        <div class="card-body">
        
		<div>
        <button class="btn btn-primary btn-sm active" id="btnNuevoUsuario" data-toggle="modal" data-target="#addData"><i class="fa fa-user-plus fa-lg"></i>&nbsp Nuevo Usuario</button>
		<button class="btn btn-success btn-sm active" onclick="tableToExcel('dataTables-example', 'Tabla de Usuarios')" ><i class="fa fa-file-excel-o fa-lg"></i>&nbsp Exportar a Excel</button>
		</div>
		<hr>
          <div class="table-responsive">
            <table class="table table-bordered table-striped" cellspacing="0" cellpadding="0" id="dataTables-example" width="100%" style="text-transform:uppercase;font-size:14px;">
              <thead class="thead-inverse">
                <tr>
					<th>ID</th>
					<th>USUARIO</th>
					<th>APELLIDOS Y NOMBRES</th>
					<th>ROL</th>
					<th>DEPENDENCIA</th>
					<th>ESTADO</th>
					<th>ACCION</th>
                </tr>
               </thead> 
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Sistema de Soporte Tecnico</div>
      </div>
      <!-- Area Chart Example-->
      <!-- Example DataTables Card-->
      <!-- Modal -->
	<%@ include file="modal/g_usuario.jsp"%>
	<!-- FIN DE MODAL -->
      
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Todos los Derechos Reservados © Soporte Tecnico 2018  |  Desarrollados por el Ing. Walter Zapana Sotomayor</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>


<!--  
        <div class="container">
        <h1>Validación de formularios con Bootstrap 4</h1>
    <form class="container" id="needs-validation" novalidate>
  <div class="row">
    <div class="col-md-6 mb-3">
      <label for="validationCustom01">Nombre</label>
      <input type="text" class="form-control" id="validationCustom01" placeholder="Nombres" value="" required>
    </div>
    <div class="col-md-6 mb-3">
      <label for="validationCustom02">Apellidos</label>
      <input type="text" class="form-control" id="validationCustom02" placeholder="Apellidos" value="" required>
    </div>
  </div>
  <div class="row">
    <div class="col-md-6 mb-3">
      <label for="validationCustom03">Ciudad</label>
      <input type="text" class="form-control" id="validationCustom03" placeholder="Ciudad" required>
      <div class="invalid-feedback">
        Ingresa datos
      </div>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">Estado</label>
      <input type="text" class="form-control" id="validationCustom04" placeholder="Estado" required>
      <div class="invalid-feedback">
        Ingresa datos
      </div>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationCustom05">CP</label>
      <input type="text" class="form-control" id="validationCustom05" placeholder="CP" required>
      <div class="invalid-feedback">
        Ingresa datos
      </div>
    </div>
  </div>
  <button class="btn btn-primary" type="submit">Submit form</button>
</form>

<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';

  window.addEventListener('load', function() {
    var form = document.getElementById('needs-validation');
    form.addEventListener('submit', function(event) {
      if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
      }
      form.classList.add('was-validated');
    }, false);
  }, false);
})();
</script>    
        
    </div>
          
-->
    
    
    
    
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
    <script type="text/javascript" src="js/funciones/g_usuario.js" class="init"></script>
  </div>
</body>

</html>


