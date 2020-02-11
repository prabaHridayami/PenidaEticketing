<?php
session_start();
include_once '../../proses/connect.php';
$id_user = $_SESSION['id'];

if (!isset($_SESSION['id'])) {
  header("Location:../../index.php");
}
if ($_SESSION['role'] != "partner" || $_SESSION['role'] == "admin") {
  header("Location:../../index.php");
}

if (isset($_POST['submit'])) {
  $room = mysqli_real_escape_string($conn, $_POST['room']);
  $category = mysqli_real_escape_string($conn, $_POST['category']);
  if (!$room || !$category) {
    header("location:gethotel.php?id=$id_hotel&pesan=kosong");
  } else {
    $insertroom = mysqli_query($conn, "INSERT INTO tb_room VALUES ('','$room','$category')");
    if ($insertroom) {
      header("location:gethotel.php?id=$id_hotel&pesan=sukses");
    } else {
      header("location:gethotel.php?id=$id_hotel&pesan=gagal");
    }
  }
}

if (isset($_POST['update'])) {
  $id = mysqli_real_escape_string($conn, $_POST['id']);
  // $id_hotel = mysqli_real_escape_string($conn, $_POST['id_hotel']);
  $room = mysqli_real_escape_string($conn, $_POST['room']);
  $category = mysqli_real_escape_string($conn, $_POST['category']);
  if (!$room || !$category || !$id) {
    echo "<script type='text/javascript'>alert('Data Still Empty');window.location.href='gethotel.php?id=$id_hotel';</script>";
  } else {
    $updateroom = mysqli_query($conn, "UPDATE tb_room SET `name` = '$room', id_category = '$category' WHERE id = '$id'");
    if ($updateroom) {
      echo "<script type='text/javascript'>alert('Update Data Succes');window.location.href='gethotel.php?id=$id_hotel';</script>";
    } else {
      echo "<script type='text/javascript'>alert('Update Data Failed');window.location.href='gethotel.php?id=$id_hotel';</script>";
    }
  }
}

if (isset($_GET['id']) && isset($_GET['option']) && isset($_GET['id_room'])) :
  if ($_GET['option'] == 'delete') {
    $flag = $_GET['id_room'];
    $deleteUser = mysqli_query($conn, "DELETE FROM tb_room WHERE id = '$flag'");
    if ($deleteUser) {
      echo "<script type='text/javascript'>alert('Succes Delete Room !!');window.location.href='gethotel.php?id=$id_hotel';</script>";
    } else {
      echo "<script type='text/javascript'>alert('Failed To Delete Room !!');window.location.href='gethotel.php?id=$id_hotel';</script>";
    }
  }
endif;
?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Partner | Dashboard Pelanggan</title>
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
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
  <style media="screen">
    html {
      scroll-behavior: smooth;
    }
  </style>
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
              <h1 class="m-0 text-dark">Dashboard Pelanggan Hotel</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="../../partner/">Home</a></li>
                <li class="breadcrumb-item active">Pelanggan Hotel</li>
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
          $result = mysqli_query($conn, "SELECT tb_user.`id`, tb_user.`name`, tb_user.`phone`, tb_user.`email` FROM tb_trans_hotel JOIN tb_room ON tb_trans_hotel.id_room = tb_room.id JOIN tb_category_room ON tb_category_room.`id` = tb_room.`id_category`JOIN tb_hotel ON tb_hotel.`id` = tb_category_room.`id_hotel`JOIN tb_user ON tb_trans_hotel.id_user = tb_user.id WHERE tb_hotel.`id_user` = '$id_user' ORDER BY id DESC");
          ?>
          <div class="dataTables_wrapper">
            <table id="example" class="text-center table table-striped display nowrap" style="width:100%">
              <thead>
                <tr class="bg-dark text-white">
                  <th>No</th>
                  <th>Nama</th>
                  <th>Phone</th>
                  <th>Email</th>
                </tr>
              </thead>
              <tbody>
                <?php
                $i = 1;
                while ($row = mysqli_fetch_assoc($result)) {
                ?>
                  <tr>
                    <td><?php echo $i++; ?></td>
                    <td><?php echo $row['name']; ?></td>
                    <td><?php echo $row['phone']; ?></td>
                    <td><?php echo $row['email']; ?></td>
                  </tr>
                <?php } ?>
              </tbody>
            </table>
          </div>
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
  <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      $('#example').DataTable({
        "scrollY": 200,
        "scrollX": 100
      });
    });
  </script>
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