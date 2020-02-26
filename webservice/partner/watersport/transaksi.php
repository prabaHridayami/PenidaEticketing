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

?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Partner | Dashboard Transaksi</title>
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
              <h1 class="m-0 text-dark">Dashboard Transaksi Watersport</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="../../partner/">Home</a></li>
                <li class="breadcrumb-item active">Transaksi Watersport</li>
              </ol>
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.container-fluid -->
      </div>
      <!-- /.content-header -->
      <!-- Main content -->
      <section class="content">
        <div class="container-fluid">
          <div id="ModalEdit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          </div>
          <?php
          $result = mysqli_query($conn, "SELECT tb_trans_watersport.`status`,tb_trans_watersport.`proof`,tb_trans_watersport.`id`, tb_trans_watersport.`reserve_date`,tb_trans_watersport.qty, tb_trans_watersport.`date`,tb_user.`name`, tb_trans_watersport.`total_price` FROM tb_trans_watersport 
          JOIN tb_attraction ON tb_trans_watersport.`id_attraction` =  tb_attraction.`id` 
          JOIN tb_watersport ON tb_watersport.`id` = tb_attraction.`id_watersport`            
          JOIN tb_user ON tb_user.`id`= tb_trans_watersport.`id_user` WHERE tb_watersport.`id_user` = '$id_user' ORDER BY id DESC");
          ?>
          <div class="dataTables_wrapper">
            <table id="example" class="text-center table table-striped display nowrap" style="width:100%">
              <thead>
                <tr class="bg-dark text-white">
                  <th>No</th>
                  <th>Nama Pelanggan</th>
                  <th>Reserve Date</th>
                  <th>Date</th>
                  <th>Total Harga</th>
                  <th>Qty</th>
                  <th>Bukti Pembayaran</th>
                  <th>Status</th>
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
                    <td><?php echo $row['reserve_date']; ?></td>
                    <td><?php echo $row['date']; ?></td>
                    <td><?php echo $row['total_price']; ?></td>
                    <td><?php echo $row['qty']; ?></td>
                    <td><a href="#" class="pop"><img style="border-radius:8px;" src="../../admin/watersport/transaksi/<?php echo $row['proof']; ?>" width="50" height="50"></a></td>
                    <td>
                      <?php if ($row['status'] == 'proceed') {
                      ?>
                        <input type="checkbox" class="toggle" name="toggle" id="toggle" value="<?php echo $row['id']; ?>" data-toggle="toggle" data-off="Disabled" data-on="Enabled">
                      <?php
                      } ?>
                      <?php if ($row['status'] == 'success') {
                      ?>
                        <input type="checkbox" class="toggle" name="toggle" id="toggle" value="<?php echo $row['id']; ?>" data-toggle="toggle" data-off="Disabled" data-on="Enabled" checked>
                      <?php
                      } ?>
                    </td>
                    <!-- <td><a href="#" class="open_modal" id="<?php echo $row['id'] ?>">View Atraction</a></td> -->
                  </tr>
                <?php } ?>
              </tbody>
            </table>
          </div>
        </div><!-- /.container-fluid -->
      </section>
      <!-- /.content -->
      <!-- MODAL GAMBAR -->
      <div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-body">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              <img src="" class="imagepreview" style="width: 100%;">
            </div>
          </div>
        </div>
      </div>
      <!-- MODAL GAMBAR -->
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
</body>
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
      "scrollY": 400,
      "scrollX": 50
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
  $(function() {
    $('.pop').on('click', function() {
      $('.imagepreview').attr('src', $(this).find('img').attr('src'));
      $('#imagemodal').modal('show');
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

</html>