<?php
session_start();
$id_user = $_SESSION['id'];
include_once '../../proses/connect.php';
if (!isset($_SESSION['id'])) {
  header("Location:../index.php");
}
if ($_SESSION['role'] != "admin" || $_SESSION['role'] == "partner") {
  header("Location:../index.php");
}

?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin | Dashboard Hotel</title>
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
  <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
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
              <h1 class="m-0 text-dark">Dashboard Admin Hotel</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="../">Home</a></li>
                <li class="breadcrumb-item active">Hotel</li>
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
          if (isset($_GET['gethotel'])) {
            $id_user = $_GET['gethotel'];
            $select = mysqli_query($conn, "SELECT tb_hotel.* FROM tb_hotel JOIN tb_user ON tb_user.`id` = tb_hotel.`id_user` WHERE tb_user.`id`='$id_user'");
            $select_name = mysqli_query($conn, "SELECT * FROM tb_user WHERE id = '$id_user'");
            $row = mysqli_fetch_assoc($select_name);
          ?>
            <h5 class="m-0 text-dark">Partner Name : <?php echo $row['name']; ?></h5>
            <br>
            <div class="dataTables_wrapper">
              <table id="example" class="text-center table table-striped display nowrap" style="width:100%">
                <thead>
                  <tr class="bg-dark text-white">
                    <th>No</th>
                    <th>Nama</th>
                    <th>Alamat</th>
                    <th>Phone</th>
                    <th>Status</th>
                    <th>Option</th>
                  </tr>
                </thead>
                <tbody>
                  <?php
                  $i = 1;
                  while ($row = mysqli_fetch_assoc($select)) {
                  ?>
                    <tr>
                      <td><?php echo $i++; ?></td>
                      <td><?php echo $row['name']; ?></td>
                      <td><?php echo $row['address']; ?></td>
                      <td><?php echo $row['phone']; ?></td>
                      <td>
                        <?php if ($row['status'] == '1') {
                        ?>
                          <input type="checkbox" class="toggle" name="toggle" id="toggle" value="<?php echo $row['id']; ?>" data-toggle="toggle" data-off="Disabled" data-on="Enabled">
                        <?php
                        } ?>
                        <?php if ($row['status'] == '2') {
                        ?>
                          <input type="checkbox" class="toggle" name="toggle" id="toggle" value="<?php echo $row['id']; ?>" data-toggle="toggle" data-off="Disabled" data-on="Enabled" checked>
                        <?php
                        } ?>
                      </td>
                      <td><a href="hotel.php?getroom=<?php echo $row['id'] ?>">Check Hotel Room</a></td>
                    </tr>
                  <?php } ?>
                </tbody>
              </table>
            </div>
          <?php
          } else {
            $result = mysqli_query($conn, "SELECT tb_user.`id`,tb_user.`name`,tb_user.`role`,tb_user.email FROM tb_user JOIN tb_hotel ON tb_user.`id` = tb_hotel.`id_user` WHERE tb_user.`role` = 'partner' GROUP BY `name`");
          ?>
            <div class="dataTables_wrapper">
              <table id="example" class="text-center table table-striped display nowrap" style="width:100%">
                <thead>
                  <tr class="bg-dark text-white">
                    <th>No</th>
                    <th>Nama</th>
                    <th>Role</th>
                    <th>Email</th>
                    <th>Option</th>
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
                      <td><?php echo $row['role']; ?></td>
                      <td><?php echo $row['email']; ?></td>
                      <td><a href="hotel.php?gethotel=<?php echo $row['id'] ?>">Check Hotel</a></td>
                    </tr>
                  <?php } ?>
                </tbody>
              </table>
            </div>
          <?php
          }
          ?>
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
  <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      $('#example').DataTable({
        "scrollY": 200,
        "scrollX": 100
      });
    });
  </script>
  <script>
    $('input[name=toggle]').change(function() {
      var mode = $(this).prop('checked');
      var id = $(this).val();
      // console.log(mode, id);
      $.ajax({
        type: 'POST',
        dataType: 'JSON',
        url: 'checkbox.php',
        data: {
          mode: mode,
          id: id
        },
        success: function(data) {
          // console.log(data, mode, id);
          var data = eval(data);
          message = data.message;
          success = data.success;
          $("#heading").html(success);
          $("#body").html(message);
        }
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