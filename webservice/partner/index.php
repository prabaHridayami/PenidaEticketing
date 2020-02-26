<?php
session_start();
$id_user = $_SESSION['id'];
if (!isset($_SESSION['id'])) {
  header("Location:../index.php");
}
if ($_SESSION['role'] != "partner" || $_SESSION['role'] == "admin") {
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
    echo "<script type='text/javascript'>alert('Data Empty !!');window.location.href='../partner/index.php?id_user=$id_user';</script>";
  } else {
    if (!$password || !$repassword) {
      $upadating = mysqli_query($conn, "UPDATE tb_user SET `name` = '$name', username = '$username', phone = '$phone', email = '$email' WHERE id = '$id_user'");
      if ($upadating) {
        echo "<script type='text/javascript'>alert('Data Update Succes');window.location.href='../partner/index.php?id_user=$id_user';</script>";
      } else {
        echo "<script type='text/javascript'>alert('Data Failed');window.location.href='../partner/index.php?id_user=$id_user';</script>";
      }
    } elseif ($password == $repassword) {
      $password = md5($_POST['password']);
      $inserting_data = mysqli_query($conn, "UPDATE tb_user SET `name` = '$name', username = '$username', email = '$email', phone = '$phone', `password` = '$password' WHERE id = '$id_user'");
      if ($inserting_data) {
        echo "<script type='text/javascript'>alert('Data Succes');window.location.href='../partner/index.php?id_user=$id_user';</script>";
      } else {
        echo "<script type='text/javascript'>alert('Data Failed');window.location.href='../partner/index.php?id_user=$id_user';</script>";
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
  <title>Partner | Dashboard</title>
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
                  <li class="breadcrumb-item"><a href="../partner/">Home</a></li>
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
            $queryhotel = mysqli_query($conn, "SELECT tb_hotel.`name` AS 'Nama Hotel', tb_room.`name` AS 'Nama Kamar',tb_user.`name` AS 'Nama User',tb_trans_hotel.`check_in`,tb_trans_hotel.`check_out`,tb_trans_hotel.`reserve_date`,tb_trans_hotel.`total_price`,tb_trans_hotel.`id`FROM tb_trans_hotel JOIN tb_room ON tb_trans_hotel.id_room = tb_room.id JOIN tb_category_room ON tb_category_room.`id` = tb_room.`id_category`JOIN tb_hotel ON tb_hotel.`id` = tb_category_room.`id_hotel`JOIN tb_user ON tb_trans_hotel.id_user = tb_user.id WHERE tb_hotel.`id_user` = '$id_user' ORDER BY id DESC");
            $jumlahhotel = mysqli_num_rows($queryhotel);
            $queryboat = mysqli_query($conn, "SELECT tb_trans_boat.`id`,tb_trans_boat.`depart_date`,tb_trans_boat.`qty`,tb_trans_boat.`reserve_date`, tb_schedule.`pickup_loc`,tb_schedule.`dropup_loc`,tb_schedule.`time`,tb_user.`name`,tb_trans_boat.`total_price`FROM tb_trans_boat JOIN tb_schedule ON tb_trans_boat.schedule = tb_schedule.`id` JOIN tb_det_boat ON tb_det_boat.`id` = tb_schedule.`id_det_boat`JOIN tb_boat ON tb_boat.`id` = tb_det_boat.`id_boat`JOIN tb_user ON tb_trans_boat.id_user = tb_user.id WHERE tb_boat.`id_user` = '$id_user' ORDER BY id DESC");
            $jumlahboat = mysqli_num_rows($queryboat);
            $querytour = mysqli_query($conn, "SELECT tb_trans_tour.`id`, tb_trans_tour.id_tour_package, tb_tour_package.`name` AS 'Nama Package', tb_user.`name`, tb_trans_tour.`tour_date`, tb_trans_tour.`total_price`, tb_trans_tour.`reserve_date` FROM tb_trans_tour JOIN tb_tour_package ON tb_trans_tour.`id_tour_package` = tb_tour_package.`id` JOIN tb_user ON tb_trans_tour.`id_user`= tb_user.`id` JOIN tb_tour ON tb_tour.`id` = tb_tour_package.`id_tour`WHERE tb_tour.`id_user` = '$id_user' ORDER BY id DESC");
            $jumlahtour = mysqli_num_rows($querytour);
            $queryvehicle = mysqli_query($conn, "SELECT tb_trans_rent.`id`, tb_user.`name` AS 'Penyewa', tb_vehicle.`name` AS 'Mobil Name', tb_rent_vehicle.`name` AS 'Kantor Rent', tb_vehicle.`plat`, tb_trans_rent.`take`, tb_trans_rent.`return`, tb_trans_rent.`trans_date`,tb_trans_rent.`total_price` FROM tb_trans_rent JOIN tb_user ON tb_user.`id` = tb_trans_rent.`id_user` JOIN tb_vehicle ON tb_vehicle.`id` = tb_trans_rent.`id_vehicle` JOIN tb_rent_vehicle ON tb_vehicle.`id_rent_vehicle` = tb_rent_vehicle.`id` WHERE tb_rent_vehicle.`id_user` = '$id_user' ORDER BY tb_trans_rent.id DESC");
            $jumlahvehicle = mysqli_num_rows($queryvehicle);
            $querywatersport = mysqli_query($conn, "SELECT tb_trans_watersport.`id`, tb_trans_watersport.`reserve_date`,tb_trans_watersport.qty, tb_trans_watersport.`date`,tb_user.`name`, tb_trans_watersport.`total_price` FROM tb_trans_watersport 
                  JOIN tb_attraction ON tb_trans_watersport.`id_attraction` =  tb_attraction.`id` 
                  JOIN tb_watersport ON tb_watersport.`id` = tb_attraction.`id_watersport`            
                  JOIN tb_user ON tb_user.`id`= tb_trans_watersport.`id_user` WHERE tb_watersport.`id_user` = '$id_user' ORDER BY id DESC");
            $jumlahwatersport = mysqli_num_rows($querywatersport);
            ?>
            <div class="row">
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-warning">
                  <div class="inner">
                    <h3>Boat</h3>
                    <p>Jumlah Transaksi : <?php echo $jumlahboat; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-ship"></i>
                  </div>
                  <a href="../partner/boat/boat.php" class="small-box-footer">Boat <i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
              <!-- ./col -->
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-info">
                  <div class="inner">
                    <h3>Hotel</h3>
                    <p>Jumlah Transaksi : <?php echo $jumlahhotel; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-hotel"></i>
                  </div>
                  <a href="../partner/hotel/hotel.php" class="small-box-footer">Hotel<i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-primary">
                  <div class="inner">
                    <h3>Tour</h3>
                    <p>Jumlah Transaksi : <?php echo $jumlahtour; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-bus"></i>
                  </div>
                  <a href="../partner/tour/tour.php" class="small-box-footer">Tour<i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-warning">
                  <div class="inner">
                    <h3>Vehicle</h3>
                    <p>Jumlah Transaksi : <?php echo $jumlahvehicle; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-car"></i>
                  </div>
                  <a href="../partner/vehicle/vehicle.php" class="small-box-footer">Vehicle<i class="fas fa-arrow-circle-right"></i></a>
                </div>
              </div>
              <div class="col-md-2 col-lg-3">
                <div class="small-box bg-danger">
                  <div class="inner">
                    <h3>Watersport</h3>
                    <p>Jumlah Transaksi : <?php echo $jumlahwatersport; ?></p>
                  </div>
                  <div class="icon">
                    <i class="nav-icon fas fa-swimmer"></i>
                  </div>
                  <a href="../partner/watersport/watersport.php" class="small-box-footer">Watersport <i class="fas fa-arrow-circle-right"></i></a>
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
</body>
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

</html>