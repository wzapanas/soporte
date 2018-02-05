<%@ include file="head.jsp"%>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item active">
          <a href="menu.jsp">Menu Principal</a>
        </li>
        
      </ol>
      <!-- Icon Cards-->
      <div class="row">
        <div id="gestionUsuarios" class="col-xl-6 col-sm-6 mb-3">
          <div class="card text-white bg-info o-hidden h-100">
          <img class="card-img-top" src="img/fondo.jpg" alt="Card image cap">
            <div class="card-body">
              <div class="card-body-icon">
              </div>
              <div class="mr-5">Gestion de Usuarios</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="g_usuario.jsp">
              <span class="float-left">Ver Detalles</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        
        <div id="centroLlamadas" class="col-xl-6 col-sm-6 mb-3">
          <div class="card text-white bg-warning o-hidden h-100">
            <img class="card-img-top" src="img/fondo.jpg" alt="Card image cap">
            <div class="card-body">
              <div class="card-body-icon">
              </div>
              <div class="mr-5">Centro de Llamadas</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="centro_llamadas.jsp">
              <span class="float-left">Ver Detalles</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div id="soporteTecnico" class="col-xl-6 col-sm-6 mb-3">
          <div class="card text-white bg-success o-hidden h-100">
            <img class="card-img-top" src="img/fondo.jpg" alt="Card image cap">
            <div class="card-body">
              <div class="card-body-icon">
              </div>
              <div class="mr-5">Soporte Tecnico</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="soporte_tecnico.jsp">
              <span class="float-left">Ver Detalles</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div id="gestionEquipo" class="col-xl-6 col-sm-6 mb-3">
          <div class="card text-white bg-danger o-hidden h-100">
            <img class="card-img-top" src="img/fondo.jpg" alt="Card image cap">
            <div class="card-body">
              <div class="card-body-icon">
              </div>
              <div class="mr-5">Gestion de Equipos</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="g_equipo.jsp">
              <span class="float-left">Ver Detalles</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
      </div>
      <!-- Area Chart Example-->
      <!-- Example DataTables Card-->

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
            <a class="btn btn-danger" href="login.html">Cerrar Sesión</a>
          </div>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
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
    <script type="text/javascript" src="js/funciones/menu.js" class="init"></script>
  </div>
</body>

</html>

    