<?php
session_start();
include_once '../../proses/connect.php';
$id_user = $_SESSION['id'];
$id_vehicle = $_GET['id'];
$selecthotel = mysqli_query($conn, "SELECT * FROM tb_rent_vehicle WHERE id_user = $id_user AND id = $id_vehicle");
$fetch = mysqli_fetch_assoc($selecthotel);
$name = $fetch['name'];
if (!isset($_SESSION['id'])) {
  header("Location:../index.php");
}
if ($_SESSION['role'] != "admin" || $_SESSION['role'] == "partner") {
  header("Location:../index.php");
}

if (isset($_POST['submit'])) {
  $name = mysqli_escape_string($conn, $_POST['name']);
  $plat = mysqli_escape_string($conn, $_POST['plat']);
  $category = mysqli_escape_string($conn, $_POST['category']);
  $seat = mysqli_escape_string($conn, $_POST['seat']);
  $harga = mysqli_escape_string($conn, $_POST['harga']);
  $deskripsi = mysqli_escape_string($conn, $_POST['deskripsi']);
  $inserting_data = mysqli_query($conn, "INSERT INTO tb_vehicle VALUES ('','$name','$plat','$category','$harga','$seat','$id_vehicle','$deskripsi')");
  if ($inserting_data) {
    echo "<script type='text/javascript'>alert('Data Succes');window.location.href='getvehicle.php?id=$id_vehicle';</script>";
  } else {
    echo "<script type='text/javascript'>alert('Data Failed');window.location.href='getvehicle.php?id=$id_vehicle';</script>";
  }
}

if (isset($_POST['update'])) {
  $id = mysqli_escape_string($conn, $_POST['id']);
  $name = mysqli_escape_string($conn, $_POST['name']);
  $plat = mysqli_escape_string($conn, $_POST['plat']);
  $category = mysqli_escape_string($conn, $_POST['category']);
  $seat = mysqli_escape_string($conn, $_POST['seat']);
  $harga = mysqli_escape_string($conn, $_POST['harga']);
  $deskripsi = mysqli_escape_string($conn, $_POST['deskripsi']);
  $inserting_data = mysqli_query($conn, "UPDATE tb_vehicle SET name = '$name', price = '$harga', category = '$category', plat = '$plat', seat = '$seat', `desc` = '$deskripsi' WHERE id = '$id'");
  if ($inserting_data) {
    echo "<script type='text/javascript'>alert('Update Data Succes');window.location.href='getvehicle.php?id=$id_vehicle';</script>";
  } else {
    echo "<script type='text/javascript'>alert('Update Data Failed');window.location.href='getvehicle.php?id=$id_vehicle';</script>";
  }
}

if (isset($_GET['id']) && isset($_GET['option']) && isset($_GET['id_chair'])) :
  if ($_GET['option'] == 'delete') {
    $flag = $_GET['id_chair'];
    $checkUser = mysqli_query($conn, "SELECT * FROM tb_vehicle WHERE id = '$flag'");
    if (mysqli_num_rows($checkUser) > 0) {
      $deleteUser = mysqli_query($conn, "DELETE FROM tb_vehicle WHERE id = '$flag'");
      if ($deleteUser) {
        echo "<script type='text/javascript'>alert('Succes Delete Vehicle !!');window.location.href='getvehicle.php?id=$id_vehicle';</script>";
      } else {
        echo "<script type='text/javascript'>alert('Failed To Delete Vehicle !!');window.location.href='getvehicle.php?id=$id_vehicle';</script>";
      }
    }
  }
endif;
?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Partner | Dashboard <?php echo $name ?></title>
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
              <h1 class="m-0 text-dark">Dashboard <?php echo $name ?></h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
              <ol class="breadcrumb float-sm-right">
                <li class="breadcrumb-item"><a href="../../partner/">Home</a></li>
                <li class="breadcrumb-item active">Boat</li>
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
          <button type="button" class="btn btn-success my-2" data-toggle="modal" data-target="#exampleModalCenter">
            Add Vehicle
          </button>
          <form action="getvehicle.php?id=<?php echo $id_vehicle ?>" method="post" enctype="multipart/form-data">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
              <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Add Vehicle</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <div class="row">
                      <div class="col-6">
                        <label for="">Name Vehicle</label>
                        <input class="form-control" type="text" name="name" id="" placeholder="Name Vehicle">
                      </div>
                      <div class="col-6">
                        <label for="">Plat</label>
                        <input class="form-control" type="text" name="plat" id="" placeholder="Plat">
                      </div>
                    </div>
                    <div class="">
                      <label for="">Harga</label>
                      <input class="form-control" type="text" name="harga" id="" placeholder="Harga">
                    </div>
                    <div class="row">
                      <div class="col-6">
                        <label for="">category Kendaraan</label>
                        <select class="form-control" name="category" id="">
                          <option value="">Pilih Salah Satu</option>
                          <option value="Motor">Motor</option>
                          <option value="Mobil">Mobil</option>
                        </select>
                      </div>
                      <div class="col-6">
                        <label for="">Seat</label>
                        <input class="form-control" type="number" name="seat" id="" placeholder="Jumlah Seat">
                      </div>
                    </div>
                    <div class="">
                      <label for="">Deskripsi</label>
                      <textarea class="form-control" name="deskripsi" id="" cols="10" rows="5"></textarea>
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
          <?php
          $result = mysqli_query($conn, "SELECT * FROM tb_vehicle WHERE id_rent_vehicle = $id_vehicle");
          ?>
          <div class="dataTables_wrapper">
            <table id="example" class="text-center table table-striped display nowrap" style="width:100%">
              <thead>
                <tr class="bg-dark text-white">
                  <th>No</th>
                  <th>Nama</th>
                  <th>Plat</th>
                  <th>Category</th>
                  <th>Harga</th>
                  <th>Deskripsi</th>
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
                    <td><?php echo $row['plat']; ?></td>
                    <td><?php echo $row['category']; ?></td>
                    <td><?php echo $row['price']; ?></td>
                    <td><?php echo $row['desc']; ?></td>
                    <td><a href="#" class="open_modal" id="<?php echo $row['id'] ?>">Edit</a> | <a href="#" class="link-delete" data-id="<?php echo $row['id'] ?>">Delete</a></td>
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
    $(".link-delete").click(function() {
      var r = confirm("Are You Sure You Want To Delete?");
      var GetAttr = $(this).attr("data-id");
      if (r == true) {
        window.location = "getvehicle.php?id=<?php echo $id_vehicle ?>&option=delete&id_chair=" + GetAttr;
      } else {
        return false;
      }
    });
    $(document).ready(function() {
      $('#example').DataTable({
        "scrollY": 200,
        "scrollX": 100
      });
    });
  </script>
  <script type="text/javascript">
    $(document).ready(function() {
      $(".open_modal").click(function(e) {
        var m = $(this).attr("id");
        $.ajax({
          url: "../../proses/editvehicle.php?id=<?php echo $id_vehicle ?>",
          type: "GET",
          data: {
            flag: m,
          },
          success: function(ajaxData) {
            console.log(ajaxData);
            $("#ModalEdit").html(ajaxData);
            $("#ModalEdit").modal('show', {
              backdrop: 'true'
            });
          }
        });
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