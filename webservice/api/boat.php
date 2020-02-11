<?php
require_once '../proses/connect.php';
$response = array();
if (isset($_GET['apicall'])) {
    switch ($_GET['apicall']) {
        case 'allboat':
            $stmt = $conn->prepare("SELECT `id`, `name`, id_user, `image`, `desc`, phone FROM tb_boat");
            $stmt->execute();
            $stmt->store_result();
            if ($stmt->num_rows > 0) {
                $stmt->bind_result($id, $name, $id_user, $image, $desc, $phone);
                while ($stmt->fetch()) {
                    $boat[] = array(
                        'id' => $id,
                        'name' => $name,
                        'id_user' => $id,
                        'image' => $image,
                        'desc' => $desc,
                        'phone' => $phone
                    );
                    $response['error'] = false;
                    $response['message'] = 'Successfull';
                    $response['boat'] = $boat;
                }
            } else {
                $response['error'] = false;
                $response['message'] = 'Invalid !!';
            }
            break;

        case 'TransBoat':
            if (isTheseParametersAvailable(array('depart_date', 'schedule', 'reserve_date', 'qty', 'total_price', 'id_user'))) {
                $depart_date = $_POST['depart_date'];
                $schedule = $_POST['schedule'];
                $reserve_date = $_POST['reserve_date'];
                $qty = $_POST['qty'];
                $id_user = $_POST['id_user'];
                $total_price = $_POST['total_price'];

                $stmt = $conn->prepare("INSERT INTO tb_trans_boat (`depart_date`, schedule, `reserve_date`, qty, total_price, id_user) VALUES (?, ?, ?, ?, ?, ?)");
                // var_dump($stmt);
                // die();
                $stmt->bind_param("ssssss", $depart_date, $schedule, $reserve_date, $qty, $total_price, $id_user);
                if ($stmt->execute()) {
                    $last = $conn->insert_id;
                    // $stmt->bind_result($depart_date, $return_date, $schedule, $reserve_date, $qty, $id_user);
                    $stmt->close();
                    $response['error'] = false;
                    $response['message'] = 'Transaksi Success';
                    $response['payment'] = $last;
                } else {
                    $response['error'] = false;
                    $response['message'] = 'Invalid !!';
                }
            }
            break;
        case 'getTransBoat':
            if (isTheseParametersAvailable(array('id_user'))) {
                $id_user = $_POST['id_user'];
                $stmt = $conn->prepare("SELECT tb_trans_boat.`id`,tb_trans_boat.`depart_date`,tb_trans_boat.`qty`,tb_trans_boat.`reserve_date`,
                tb_trans_boat.`return_date`,tb_schedule.`pickup_loc`,tb_schedule.`dropup_loc`,tb_schedule.`time`,tb_user.`name`,tb_det_boat.`price`,
                tb_trans_boat.`total_price`,tb_trans_boat.status FROM tb_trans_boat JOIN tb_schedule ON tb_trans_boat.schedule = tb_schedule.`id` 
                JOIN tb_det_boat ON tb_det_boat.`id` = tb_schedule.`id_det_boat`JOIN tb_boat ON tb_boat.`id` = tb_det_boat.`id_boat`
                JOIN tb_user ON tb_trans_boat.id_user = tb_user.id WHERE tb_boat.`id_user` = ? ORDER BY id DESC");

                $stmt->bind_param("s", $id_user);
                if ($stmt->execute()) {
                    $stmt->bind_result($id, $depart_date, $qty, $reserve_date, $return_date, $pickup_loc, $dropup_loc, $time, $name, $price, $total_price, $status);
                    while ($stmt->fetch()) {
                        $trans_hotel[] = array(
                            'id' => $id,
                            'depart_date' => $depart_date,
                            'qty' => $qty,
                            'reserve_date' => $reserve_date,
                            'return_date' => $return_date,
                            'pickup_loc' => $pickup_loc,
                            'dropup_loc' => $dropup_loc,
                            'time' => $time,
                            'name' => $name,
                            'price' => $price,
                            'total_price' => $total_price,
                            'status' => $status
                        );
                    }
                    $stmt->close();
                    $response['error'] = false;
                    $response['message'] = 'Transaksi Success';
                    $response['trans_hotel'] = $trans_hotel;
                } else {
                    $response['error'] = false;
                    $response['message'] = 'Invalid !!';
                }
            }
            break;
        case 'getSchedule':
            if (isTheseParametersAvailable(array('id_boat', 'departure', 'guest'))) {
                $id_boat = $_POST['id_boat'];
                $guest = $_POST['guest'];
                $departure = $_POST['departure'];
                $sum = mysqli_query($conn, "SELECT tb_schedule.`id`, SUM(qty)AS 'sum' 
                        FROM tb_schedule 
                        JOIN tb_trans_boat ON tb_trans_boat.`schedule` = tb_schedule.`id`
                        JOIN tb_det_boat ON tb_det_boat.`id` = tb_schedule.`id_det_boat` 
                        JOIN tb_boat ON tb_boat.`id` = tb_det_boat.`id_boat` 
                        WHERE tb_boat.`id` = '$id_boat' AND tb_trans_boat.`depart_date` = '$departure'
                        GROUP BY tb_schedule.`id`");


                $schedule = [];
                $scheduleid = [];
                $lol = [];

                while ($row = mysqli_fetch_array($sum)) {
                    $schedule[] = array(
                        'id' => $row['id'],
                        'sum' => $row['sum']
                    );
                }

                if ($schedule != null) {
                    $quota = mysqli_query($conn, "SELECT tb_schedule.`id`, tb_det_boat.`quota`FROM tb_det_boat 
                                                        JOIN tb_schedule ON tb_schedule.`id_det_boat` = tb_det_boat.`id`
                                                        WHERE tb_det_boat.id_boat = '$id_boat';");
                    while ($row1 = mysqli_fetch_array($quota)) {
                        $boat[] = array(
                            'id' => $row1['id'],
                            'quota' => $row1['quota']
                        );
                    }

                    for ($i = 0; $i < count($schedule); $i++) {
                        // echo $schedule[$i]['id'];
                        for ($j = 0; $j < count($boat); $j++) {
                            if ($schedule[$i]['id'] == $boat[$j]['id']) {
                                if ($schedule[$i]['sum'] >= $boat[$j]['quota']) {
                                    $scheduleid[] = $schedule[$i]['id'];
                                }
                            }
                        }
                    }
                    $stmt = $conn->prepare("SELECT tb_schedule.id, 
                                                        tb_det_boat.`desc`,
                                                        tb_det_boat.`quota`,
                                                        tb_det_boat.`price`,
                                                        tb_det_boat.`image`, 
                                                        tb_schedule.`pickup_loc`,
                                                        tb_schedule.`dropup_loc` , 
                                                        tb_schedule.`time`, 
                                                        tb_boat.`name`
                                                FROM tb_det_boat JOIN tb_boat ON tb_det_boat.`id_boat` = tb_boat.`id` 
                                                JOIN tb_schedule ON tb_schedule.`id_det_boat` = tb_det_boat.`id`
                                                WHERE id_boat = ?;");
                    $stmt->bind_param("s", $id_boat);
                    if ($stmt->execute()) {
                        $stmt->bind_result($id, $desc, $quota, $price, $image, $pickup_loc, $dropup_loc, $time, $name);
                        while ($stmt->fetch()) {
                            for ($i = 0; $i < count($scheduleid); $i++) {
                                if ($id != $scheduleid[$i]) {
                                    $lol[] = array(
                                        'id' => $id,
                                        'desc' => $desc,
                                        'quota' => $quota,
                                        'image' => $image,
                                        'price' => $price,
                                        'time' => $time,
                                        'pickup_loc' => $pickup_loc,
                                        'dropup_loc' => $dropup_loc,
                                        'name' => $name
                                    );
                                }
                            }
                        }
                        $stmt->close();
                        $response['error'] = false;
                        $response['message'] = 'Search Schedule Success';
                        $response['schedule'] = $lol;
                    } else {
                        $response['error'] = false;
                        $response['message'] = 'Invalid !!';
                    }
                } else {
                    $stmt = $conn->prepare("SELECT tb_schedule.id, 
                                                        tb_det_boat.`desc`,
                                                        tb_det_boat.`quota`,
                                                        tb_det_boat.`price`,
                                                        tb_det_boat.`image`, 
                                                        tb_schedule.`pickup_loc`,
                                                        tb_schedule.`dropup_loc` , 
                                                        tb_schedule.`time`, 
                                                        tb_boat.`name`
                                                FROM tb_det_boat JOIN tb_boat ON tb_det_boat.`id_boat` = tb_boat.`id` 
                                                JOIN tb_schedule ON tb_schedule.`id_det_boat` = tb_det_boat.`id`
                                                WHERE id_boat = ?;");
                    $stmt->bind_param("s", $id_boat);
                    if ($stmt->execute()) {
                        $stmt->bind_result($id, $desc, $quota, $price, $image, $pickup_loc, $dropup_loc, $time, $name);
                        while ($stmt->fetch()) {

                            $lol[] = array(
                                'id' => $id,
                                'desc' => $desc,
                                'quota' => $quota,
                                'image' => $image,
                                'price' => $price,
                                'time' => $time,
                                'pickup_loc' => $pickup_loc,
                                'dropup_loc' => $dropup_loc,
                                'name' => $name
                            );
                        }
                        $stmt->close();
                        $response['error'] = false;
                        $response['message'] = 'Search Schedule Success';
                        $response['schedule'] = $lol;
                    } else {
                        $response['error'] = false;
                        $response['message'] = 'Invalid !!';
                    }
                }
            }
            break;
        case 'proofBoat':
            if (isset($_POST['id_transaksi']) and isset($_FILES['image']['name'])) {
                $upload_path = 'transaksi/';
                $server_ip = gethostbyname(gethostname());
                // $upload_url = 'http://' . $server_ip . '/webservice/admin/boat/' . $upload_path;
                $upload_url = '../admin/boat/' . $upload_path;
                $id_transaksi = $_POST['id_transaksi'];
                $nama = uniqid('uploaded-', true) . '.' . strtolower(pathinfo($_FILES['image']['name'], PATHINFO_EXTENSION));
                $tmp_image = $_FILES['image']['tmp_name'];
                $fileinfo = pathinfo($_FILES['image']['name']);
                $extension = $fileinfo['extension'];
                $file_url = $upload_url . $nama;
                $file_path = 'webservice/admin/boat/' . $upload_path . $nama;
                // var_dump(move_uploaded_file($tmp_image, $file_url));
                // die();
                try {
                    //saving the file 
                    if (move_uploaded_file($tmp_image, $file_url)) {
                        $sql = "UPDATE tb_trans_boat SET proof = '$file_url' WHERE id = '$id_transaksi';";
                        //adding the path and name to database 
                        if (mysqli_query($conn, $sql)) {
                            //filling response array with values 
                            $response['error'] = false;
                            $response['url'] = $file_url;
                            $response['name'] = $nama;
                            $response['message'] = 'Success To Upload Image';
                        }
                    } else {
                        $response['error'] = true;
                        $response['message'] = 'Failed To Upload Image';
                    }
                } catch (Exception $e) {
                    $response['error'] = true;
                    $response['message'] = $e->getMessage();
                }
                //displaying the response 
                echo json_encode($response);
                //closing the connection 
                mysqli_close($conn);
            } else {
                $response['error'] = true;
                $response['message'] = 'Please choose a file';
            }
            break;
        default:
            $response['error'] = true;
            $response['message'] = 'Invalid Operation Called';
    }
} else {
    $response['error'] = true;
    $response['message'] = 'Invalid API Call';
}

echo json_encode($response);
function isTheseParametersAvailable($params)
{
    foreach ($params as $param) {
        if (!isset($_POST[$param])) {
            return false;
        }
    }
    return true;
}

function getQty($id_schedule, $depart)
{
    if (isTheseParametersAvailable(array('schedule'))) {
        $id_schedule = $_POST['schedule'];
        $depart = $_POST['depart'];
        $stmt = $conn->prepare(" SELECT SUM(tb_trans_boat.`qty`) AS qty FROM tb_trans_boat
                                    JOIN tb_schedule ON tb_schedule.`id` = tb_trans_boat.`schedule`
                                    JOIN tb_det_boat ON tb_det_boat.`id` = tb_schedule.`id_det_boat`
                                    WHERE tb_trans_boat.`schedule` = ? AND tb_trans_boat.`depart_date` = ? ");

        $stmt->bind_param("ss", $id_schedule, $depart);
        $stmt->execute();
        $stmt->store_result();
        if ($stmt->num_rows > 0) {
            $stmt->bind_result($qty);
            $stmt->fetch();

            var_dump($qty);

            return $response = $qty;
        }
    }
}
