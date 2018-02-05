<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Corte Superior de Justicia</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
  <link href="css/styles.css" rel="stylesheet">

</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top navi" id="mainNav">
    <a class="navbar-brand" href="index.html">Soporte Tecnico </a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse navi" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu Principal">
          <a class="nav-link" href="menu.jsp">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Menu Principal</span>
          </a>
        </li>
        
        <li id="g_usuario" class="nav-item" data-toggle="tooltip" data-placement="right" title="Gestion de Usuarios">
          <a class="nav-link" href="g_usuario.jsp">
            <i class="fa fa-fw fa-users"></i>
            <span class="nav-link-text">Gestion de Usuarios</span>
          </a>
        </li>
        <li id="centro_llamadas" class="nav-item" data-toggle="tooltip" data-placement="right" title="Centro de Llamadas">
          <a class="nav-link" href="centro_llamadas.jsp">
            <i class="fa fa-fw fa-phone"></i>
            <span class="nav-link-text">Centro de Llamadas</span>
          </a>
        </li>
        <li id="soporte_tecnico" class="nav-item" data-toggle="tooltip" data-placement="right" title="Soporte Tecnico">
          <a class="nav-link" href="soporte_tecnico.jsp">
            <i class="fa fa-fw fa-hand-paper-o"></i>
            <span class="nav-link-text">Soporte Tecnico</span>
          </a>
        </li>
        <li id="g_equipo" class="nav-item" data-toggle="tooltip" data-placement="right" title="Gestion de Equipo">
          <a class="nav-link" href="g_equipo.jsp">
            <i class="fa fa-fw fa-desktop"></i>
            <span class="nav-link-text">Gestión de Equipos</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Salir">
          <a class="nav-link" href="../login.html">
            <i class="fa fa-fw fa-sign-out"></i>
            <span class="nav-link-text">Salir</span>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Login</a>
        </li>
      </ul>
    </div>
  </nav>
  <div class="content-wrapper">