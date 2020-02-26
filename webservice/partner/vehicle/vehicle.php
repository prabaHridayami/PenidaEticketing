<?php
session_start();
$id_user = $_SESSION['id'];
include_once '../../proses/connect.php';
if (!isset($_SESSION['id'])) {
  header("Location:../../index.php");
}
if ($_SESSION['role'] != "partner" || $_SESSION['role'] == "admin") {
  header("Location:../../index.php");
}

if (isset($_POST['submit'])) {
  $name = mysqli_escape_string($conn, $_POST['name']);
  $phone = mysqli_escape_string($conn, $_POST['phone']);
  $description = mysqli_escape_string($conn, $_POST['description']);
  $ekstensi_diperbolehkan    = array('png', 'jpg');
  $nama = $_FILES['files']['name'];
  $x = explode('.', $nama);
  $ekstensi = strtolower(end($x));
  $ukuran    = $_FILES['files']['size'];
  $file_tmp = $_FILES['files']['tmp_name'];
  if (in_array($ekstensi, $ekstensi_diperbolehkan) === true) {
    if (move_uploaded_file($file_tmp, 'image/' . $nama) || $ukuran < 844070) {
      $inserting_data = mysqli_query($conn, "INSERT INTO tb_rent_vehicle VALUES ('','$name','$id_user','$nama','$description','$phone','1')");
      if ($inserting_data) {
        // echo "Sukses";
        echo "<script type='text/javascript'>alert('Data Succes');window.location.href='vehicle.php';</script>";
      } else {
        // echo "Gagal";
        echo "<script type='text/javascript'>alert('Data Failed');window.location.href='vehicle.php';</script>";
      }
    } else {
      // echo "Kurang";
      echo "<script type='text/javascript'>alert('Data Failed');window.location.href='vehicle.php';</script>";
    }
  } else {
    echo "<script type='text/javascript'>alert('Vehicle Failed');window.location.href='vehicle.php';</script>";
  }
}
?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Partner | Dashboard Vehicle</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../../plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bbootstrap 4 -->
  <link rel="stylesheet" href="../../plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../../plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- JQVMap -->
  <link rel="stylesheet" href="../../plugins/jqvmap/jqvmap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../../dist/css/adminlte.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="../../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="../../plugins/daterangepicker/daterangepicker.css">
  <!-- summernote -->
  <link rel="stylesheet" href="../../plugins/summernote/summernote-bs4.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini layout-fixed">
  <div class="wrapper">
    <?php
    include "navbar.php";
    ?>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            <div class="col-sm-6">
              <h1 class="m-0 text-dark">Dashboard Boat</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="../../partner/">Home</a></li>
                <li class="breadcrumb-item active">Vehicle</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->
      <!-- Main content -->
      <section class="content">
        <div class="container-fluid">
          <button type="button" class="btn btn-success my-2" data-toggle="modal" data-target="#exampleModalCenter">
            Request Vehicle
          </button>
          <form action="boat.php" method="post" enctype="multipart/form-data">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
              <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Request Vehicle</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <div class="row">
                      <div class="col-6">
                        <label for="">Vehicle Name</label>
                        <input class="form-control" type="text" name="name" id="" placeholder="Vehicle Name">
                      </div>
                      <div class="col-6">
                        <label for="">Phone</label>
                        <input class="form-control" type="tel" name="phone" id="" placeholder="Phone Number">
                      </div>
                    </div>
                    <div class="">
                      <label for="">Description</label>
                      <textarea class="form-control" name="description" id="" cols="10" rows="5" placeholder="Description"></textarea>
                    </div>
                    <div><label for=""></label></div>
                    <div class="col-lg">
                      <input type="file" class="custom-file-input" name="files" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" multiple>
                      <label class="custom-file-label" for="inputGroupFile01">Choose Image</label>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" name="submit" name="submit" class="btn btn-primary" value="Save changes">
                    <!-- <button type="button" name="submit" class="btn btn-primary">Save changes</button> -->
                  </div>
                </div>
              </div>
            </div>
          </form>
          <div class="row">
            <?php
            $selecthotel = mysqli_query($conn, "SELECT * FROM tb_rent_vehicle WHERE id_user = $id_user AND status = '2'");
            if (mysqli_num_rows($selecthotel) > 0) {
              while ($fetch = mysqli_fetch_assoc($selecthotel)) { ?>
                <div class="col-lg-3 col-6">
                  <div class="small-box bg-warning">
                    <div class="inner">
                      <h3><?php echo $fetch['name'] ?></h3>
                      <p><?php echo $fetch['phone'] ?></p>
                    </div>
                    <div class="icon">
                      <i class="nav-icon fas fa-car"></i>
                    </div>
                    <a href="getvehicle.php?id=<?php echo $fetch['id'] ?>" class="small-box-footer">Add Boat <i class="fas fa-arrow-circle-right"></i></a>
                  </div>
                </div>
              <?php
              } ?>
          </div>
        <?php
            } else {
              echo "<div class='col-lg-12 alert alert-danger' align='center' role='alert'>Boat Tidak Tersedia</div>";
            }
        ?>
        <!-- /.row -->
        <!-- Main row -->
        <div class="row">

        </div>
        <!-- /.row (main row) -->
        </div><!-- /.container-fluid -->
      </section>
      <!-- /.content -->
    </div>
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
  <script src="../../plugins/jquery/jquery.min.js"></script>
  <!-- jQuery UI 1.11.4 -->
  <script src="../../plugins/jquery-ui/jquery-ui.min.js"></script>
  <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
  <script>
    $.widget.bridge('uibutton', $.ui.button)
  </script>
  <!-- Bootstrap 4 -->
  <script src="../../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- ChartJS -->
  <script src="../../plugins/chart.js/Chart.min.js"></script>
  <!-- Sparkline -->
  <script src="../../plugins/sparklines/sparkline.js"></script>
  <!-- JQVMap -->
  <script src="../../plugins/jqvmap/jquery.vmap.min.js"></script>
  <script src="../../plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
  <!-- jQuery Knob Chart -->
  <script src="../../plugins/jquery-knob/jquery.knob.min.js"></script>
  <!-- daterangepicker -->
  <script src="../../plugins/moment/moment.min.js"></script>
  <script src="../../plugins/daterangepicker/daterangepicker.js"></script>
  <!-- Tempusdominus Bootstrap 4 -->
  <script src="../../plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
  <!-- Summernote -->
  <script src="../../plugins/summernote/summernote-bs4.min.js"></script>
  <!-- overlayScrollbars -->
  <script src="../../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
  <!-- AdminLTE App -->
  <script src="../../dist/js/adminlte.js"></script>
  <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
  <script src="../../dist/js/pages/dashboard.js"></script>
  <!-- AdminLTE for demo purposes -->
  <script src="../../dist/js/demo.js"></script>
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