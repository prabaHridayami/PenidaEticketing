<?php
session_start();
$id_user = $_SESSION['id'];
if (!isset($_SESSION['id'])) {
  header("Location:../index.php");
}
if ($_SESSION['role'] != "admin" || $_SESSION['role'] == "partner") {
  header("Location:../index.php");
}
include_once '../proses/connect.php';
if (isset($_POST['submit'])) {
  $id_user = mysqli_escape_string($conn, $_POST['id_user']);
  $name = mysqli_escape_string($conn, $_POST['name']);
  $phone = mysqli_escape_string($conn, $_POST['phone']);
  $username = mysqli_escape_string($conn, $_POST['username']);
  $email = mysqli_escape_string($conn, $_POST['email']);
  $password = mysqli_escape_string($conn, $_POST['password']);
  $repassword = mysqli_escape_string($conn, $_POST['repassword']);
  if (!$id_user || !$name || !$phone || !$username || !$email) {
    echo "<script type='text/javascript'>alert('Data Empty !!');window.location.href='../admin/index.php?id_user=$id_user';</script>";
  } else {
    if (!$password || !$repassword) {
      $upadating = mysqli_query($conn, "UPDATE tb_user SET `name` = '$name', username = '$username', phone = '$phone', email = '$email' WHERE id = '$id_user'");
      if ($upadating) {
        echo "<script type='text/javascript'>alert('Data Update Succes');window.location.href='../admin/index.php?id_user=$id_user';</script>";
      } else {
        echo "<script type='text/javascript'>alert('Data Failed');window.location.href='../admin/index.php?id_user=$id_user';</script>";
      }
    } elseif ($password == $repassword) {
      $password = md5($_POST['password']);
      $inserting_data = mysqli_query($conn, "UPDATE tb_user SET `name` = '$name', username = '$username', email = '$email', phone = '$phone', `password` = '$password' WHERE id = '$id_user'");
      if ($inserting_data) {
        echo "<script type='text/javascript'>alert('Data Succes');window.location.href='../admin/index.php?id_user=$id_user';</script>";
      } else {
        echo "<script type='text/javascript'>alert('Data Failed');window.location.href='../admin/index.php?id_user=$id_user';</script>";
      }
    }
  }
}
?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin | Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bbootstrap 4 -->
  <link rel="stylesheet" href="../plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- JQVMap -->
  <link rel="stylesheet" href="../plugins/jqvmap/jqvmap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../dist/css/adminlte.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="../plugins/daterangepicker/daterangepicker.css">
  <!-- summernote -->
  <link rel="stylesheet" href="../plugins/summernote/summernote-bs4.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini layout-fixed">
  <div class="wrapper">
    <?php
    include "navbar.php";
    ?>
    <!-- Content Wrapper. Contains page content -->
    <?php
    if (isset($_GET['id_user'])) {
      $id_user = $_GET['id_user'];
      $select = mysqli_query($conn, "SELECT * FROM tb_user WHERE id = '$id_user'");
      $row = mysqli_fetch_assoc($select);
    ?>
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
          <div class="container-fluid">
            <div class="row mb-2">
              <div class="col-sm-6">
                <h1 class="m-0 text-dark">Profile Account</h1>
              </div><!-- /.col -->
              <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                  <li class="breadcrumb-item"><a href="../admin/">Home</a></li>
                  <li class="breadcrumb-item active">Dashboard</li>
                </ol>
              </div><!-- /.col -->
            </div><!-- /.row -->
          </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->
        <!-- Main content -->
        <section class="content">
          <div class="container-fluid">
            <!-- Main row -->
            <form action="index.php" method="post" enctype="multipart/form-data">
              <div class="row">
                <div class="col-lg-6">
                  <label for="">Profile Name</label>
                  <input class="form-control" type="text" name="name" value="<?php echo $row['name']; ?>" placeholder="Profile Name">
                  <input class="form-control" type="hidden" name="id_user" value="<?php echo $row['id']; ?>">
                </div>
                <div class="col-lg-6">
                  <label for="">Username</label>
                  <input class="form-control" type="text" name="username" value="<?php echo $row['username']; ?>" placeholder="Username">
                </div>
              </div>
              <br>
              <div class="row">
                <div class="col-lg-6">
                  <label for="">Email</label>
                  <input class="form-control" type="text" name="email" value="<?php echo $row['email']; ?>" placeholder="Email">
                </div>
                <div class="col-lg-6">
                  <label for="">Phone</label>
                  <input class="form-control" type="text" name="phone" value="<?php echo $row['phone']; ?>" placeholder="Phone">
                </div>
              </div>
              <br>
              <div class="row">
                <div class="col-lg-6">
                  <label for="">New Password</label>
                  <input class="form-control" type="password" name="password" placeholder="Password">
                </div>
                <div class="col-lg-6">
                  <label for="">Retype Password</label>
                  <input class="form-control" type="password" name="repassword" placeholder="Retype Password">
                </div>
              </div>
              <br>
              <!-- <div class=""> -->
              <input type="submit" name="submit" class="col-12 btn btn-primary btn-block btn-flat" value="Save changes">
              <!-- </div> -->
            </form>
            <!-- /.row (main row) -->
          </div><!-- /.container-fluid -->
        </section>
        <!-- /.content -->
      </div>
    <?php
    } else {
    ?>
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
          <div class="container-fluid">
            <div class="row mb-2">
              <div class="col-sm-6">
                <h1 class="m-0 text-dark">Main Dashboard</h1>
              </div><!-- /.col -->
              <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                  <li class="breadcrumb-item"><a href="#">Home</a></li>
                  <li class="breadcrumb-item active">Dashboard</li>
                </ol>
              </div><!-- /.col -->
            </div><!-- /.row -->
          </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <section class="content">
          <div class="container-fluid">
            <?php
            $queryboat = mysqli_query($conn, "SELECT tb_user.`id`,tb_user.`name`,tb_user.`role`,tb_user.email FROM tb_user JOIN tb_boat ON tb_user.`id` = tb_boat.`id_user` WHERE tb_user.`role` = 'partner' GROUP BY `name`");
            $jumlahboat = mysqli_num_rows($queryboat);
            $queryhotel = mysqli_query($conn, "SELECT tb_user.`id`,tb_user.`name`,tb_user.`role`,tb_user.email FROM tb_user JOIN tb_hotel ON tb_user.`id` = tb_hotel.`id_user` WHERE tb_user.`role` = 'partner' GROUP BY `name`");
            $jumlahhotel = mysqli_num_rows($queryhotel);
            $querytour = mysqli_query($conn, "SELECT tb_user.`id`,tb_user.`name`,tb_user.`role`,tb_user.email FROM tb_user JOIN tb_tour ON tb_user.`id` = tb_tour.`id_user` WHERE tb_user.`role` = 'partner' GROUP BY `name`");
            $jumlahtour = mysqli_num_rows($querytour);
            $queryvehicle = mysqli_query($conn, "SELECT tb_user.`id`,tb_user.`name`,tb_user.`role`,tb_user.email FROM tb_user JOIN tb_rent_vehicle ON tb_user.`id` = tb_rent_vehicle.`id_user` WHERE tb_user.`role` = 'partner' GROUP BY `name`");
            $jumlahvehicle = mysqli_num_rows($queryvehicle);
            $querywatersport = mysqli_query($conn, "SELECT tb_user.`id`,tb_user.`name`,tb_user.`role`,tb_user.email FROM tb_user JOIN tb_watersport ON tb_user.`id` = tb_watersport.`id_user` WHERE tb_user.`role` = 'partner' GROUP BY `name`");
            $jumlahwatersport = mysqli_num_rows($querywatersport);
            ?>
            <div class="row">
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-warning">
                  <div class="inner">
                    <h3>Boat</h3>
                    <p>Jumlah Partner : <?php echo $jumlahboat; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-ship"></i>
                  </div>
                  <a href="../admin/boat/boat.php" class="small-box-footer">Boat <i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
              <!-- ./col -->
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-info">
                  <div class="inner">
                    <h3>Hotel</h3>
                    <p>Jumlah Partner : <?php echo $jumlahhotel; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-hotel"></i>
                  </div>
                  <a href="../admin/hotel/hotel.php" class="small-box-footer">Hotel<i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-primary">
                  <div class="inner">
                    <h3>Tour</h3>
                    <p>Jumlah Partner : <?php echo $jumlahtour; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-bus"></i>
                  </div>
                  <a href="../admin/tour/tour.php" class="small-box-footer">Tour<i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-warning">
                  <div class="inner">
                    <h3>Vehicle</h3>
                    <p>Jumlah Partner : <?php echo $jumlahvehicle; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-car"></i>
                  </div>
                  <a href="../admin/vehicle/vehicle.php" class="small-box-footer">Vehicle<i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-danger">
                  <div class="inner">
                    <h3>Watersport</h3>
                    <p>Jumlah Partner : <?php echo $jumlahwatersport; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-swimmer"></i>
                  </div>
                  <a href="../admin/watersport/watersport.php" class="small-box-footer">Watersport <i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
            </div>
            <!-- /.row (main row) -->
          </div><!-- /.container-fluid -->
        </section>
        <!-- /.content -->
      </div>
    <?php
    }
    ?>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
      <strong>Copyright &copy; 2014-2019 <a href="http://adminlte.io">AdminLTE.io</a>.</strong>
      All rights reserved.
      <div class="float-right d-none d-sm-inline-block">
        <b>Version</b> 3.0.0-rc.3
      </div>
    </footer>
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
      <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
  </div>
  <!-- ./wrapper -->
  <!-- jQuery -->
  <script src="../plugins/jquery/jquery.min.js"></script>
  <!-- jQuery UI 1.11.4 -->
  <script src="../plugins/jquery-ui/jquery-ui.min.js"></script>
  <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
  <script>
    $.widget.bridge('uibutton', $.ui.button)
  </script>
  <!-- Bootstrap 4 -->
  <script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- ChartJS -->
  <script src="../plugins/chart.js/Chart.min.js"></script>
  <!-- Sparkline -->
  <script src="../plugins/sparklines/sparkline.js"></script>
  <!-- JQVMap -->
  <script src="../plugins/jqvmap/jquery.vmap.min.js"></script>
  <script src="../plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
  <!-- jQuery Knob Chart -->
  <script src="../plugins/jquery-knob/jquery.knob.min.js"></script>
  <!-- daterangepicker -->
  <script src="../plugins/moment/moment.min.js"></script>
  <script src="../plugins/daterangepicker/daterangepicker.js"></script>
  <!-- Tempusdominus Bootstrap 4 -->
  <script src="../plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
  <!-- Summernote -->
  <script src="../plugins/summernote/summernote-bs4.min.js"></script>
  <!-- overlayScrollbars -->
  <script src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
  <!-- AdminLTE App -->
  <script src="../dist/js/adminlte.js"></script>
  <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
  <script src="../dist/js/pages/dashboard.js"></script>
  <!-- AdminLTE for demo purposes -->
  <script src="../dist/js/demo.js"></script>
  <script type="text/javascript">
    $(".link-logout").click(function() {
      var r = confirm("Are You Sure To Logout ?");
      if (r == true) {
        window.location = "../proses/logout.php";
      } else {
        return false;
      }
    });
  </script>
</body>

</html>