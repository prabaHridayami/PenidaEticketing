<?php
session_start();
include_once '../../proses/connect.php';
$id_user = $_SESSION['id'];
$selecthotel = mysqli_query($conn, "SELECT * FROM tb_trans_hotel WHERE id_user = $id_user");

if (!isset($_SESSION['id'])) {
  header("Location:../index.php");
}
if ($_SESSION['role'] != "admin" || $_SESSION['role'] == "partner") {
  header("Location:../index.php");
}

$mode = $_POST['mode'];
$pid = $_POST['id'];
// var_dump($mode, $pid);
if ($mode == 'true') {
  $str = mysqli_query($conn, "UPDATE tb_trans_hotel SET `status`= 'success' WHERE id = $pid");
  $message = 'Hey my button is enableed!!';
  $success = 'Enabled';
  echo json_encode(array('message' => $message, '$success' => $success));
} else if ($mode == 'false') {
  $str = mysqli_query($conn, "UPDATE tb_trans_hotel SET `status`= 'proceed' WHERE id = $pid");
  $message = 'Hey my button is disable!!';
  $success = 'Disabled';
  echo json_encode(array('message' => $message, 'success' => $success));
}
