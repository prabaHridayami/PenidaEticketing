<?php
session_start();
require_once 'connect.php';
if (isset($_POST['login'])) {
    $email = mysqli_real_escape_string($conn, $_POST['email']);
    $password = mysqli_real_escape_string($conn, md5($_POST['password']));
    $login = mysqli_query($conn, "SELECT * FROM tb_user WHERE email = '$email' OR username = '$email' AND `password` = '$password'");
    $cek = mysqli_num_rows($login);
    if ($cek > 0) {
        $data = mysqli_fetch_assoc($login);
        if ($data['role'] == "partner") {
            $_SESSION['id'] = $data['id'];
            $_SESSION['role'] = "partner";
            header("location:../partner/");
        } else if ($data['role'] == "admin") {
            $_SESSION['id'] = $data['id'];
            $_SESSION['role'] = "admin";
            header("location:../admin/");
        } else {
            header("location:../index.php?pesan=gagal");
        }
    } else {
        header("location:../index.php?pesan=gagal");
    }
}
