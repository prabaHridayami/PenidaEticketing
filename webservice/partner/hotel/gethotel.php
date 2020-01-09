<?php
session_start();
include_once '../../proses/connect.php';
$id_user = $_SESSION['id'];
$id_hotel = $_GET['id'];
$selecthotel = mysqli_query($conn, "SELECT * FROM tb_hotel WHERE id_user = $id_user AND id = $id_hotel");
$selectcateg = mysqli_query($conn, "SELECT * FROM tb_category_room WHERE id_hotel = $id_hotel");
$fetch = mysqli_fetch_assoc($selecthotel);
$name = $fetch['name'];
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
          <div id="ModalEdit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

          </div>
          <button type="button" class="btn btn-success my-2" data-toggle="modal" data-target="#exampleModalCenter">
            Add Room
          </button>
          <form action="gethotel.php?id=<?php echo $id_hotel ?>" method="post" enctype="multipart/form-data">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
              <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Add Room</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <?php
                    if (isset($_GET['pesan'])) {
                      if ($_GET['pesan'] == "kosong") {
                        echo "<div class='alert alert-danger' role='alert'>Data Masih Ada Yang Kosong</div>";
                      } elseif ($_GET['pesan'] == "sukses") {
                        echo "<div class='alert alert-success' role='alert'>Data Berhasil Di Tambahkan</div>";
                      } elseif ($_GET['pesan'] == "gagal") {
                        # code...
                      }
                    }
                    ?>
                    <div class="">
                      <label for="">Room Name</label>
                      <input class="form-control" type="text" name="room" id="" placeholder="Title">
                    </div>
                    <div>
                      <label for=""></label>
                      <select name="category" class="form-control" id="">
                        <option value="">Pilih Category</option>
                        <?php
                        if (mysqli_num_rows($selectcateg) > 0) {
                          while ($a = mysqli_fetch_assoc($selectcateg)) { ?>
                            <option value="<?php echo $a['id'] ?>"><?php echo $a['name'] ?></option>
                        <?php
                          }
                        }
                        ?>
                      </select>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" name="submit" name="submit" class="btn btn-primary" value="Save">
                    <!-- <button type="button" name="submit" class="btn btn-primary">Save changes</button> -->
                  </div>
                </div>
              </div>
            </div>
          </form>
          <?php
          $result = mysqli_query($conn, "SELECT tb_room.`id` AS 'Id Room', tb_category_room.`id`, tb_room.`name` AS 'Nama Kamar', tb_category_room.`name` AS 'Nama Category', price, `desc` FROM tb_room INNER JOIN tb_category_room ON tb_room.id_category = tb_category_room.id WHERE tb_category_room.id_hotel = $id_hotel");
          ?>
          <div class="dataTables_wrapper">
            <table id="example" class="text-center table table-striped display nowrap" style="width:100%">
              <thead>
                <tr class="bg-dark text-white">
                  <th>No</th>
                  <th>Nama Kamar</th>
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
                    <td><?php echo $row['Nama Kamar']; ?></td>
                    <td><?php echo $row['Nama Category']; ?></td>
                    <td><?php echo $row['price']; ?></td>
                    <td><?php echo $row['desc']; ?></td>
                    <td><a href="#" class="open_modal" id="<?php echo $row['Id Room'] ?>">Edit</a> | <a href="#" class="link-delete" data-id="<?php echo $row['Id Room'] ?>">Delete</a></td>
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
        window.location = "gethotel.php?id=<?php echo $id_hotel ?>&option=delete&id_room=" + GetAttr;
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
          url: "../../proses/editroom.php?id=<?php echo $id_hotel ?>",
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
</body>

</html>