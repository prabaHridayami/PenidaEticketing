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
            if (isTheseParametersAvailable(array('depart_date', 'return_date', 'schedule', 'reserve_date', 'qty', 'total_price', 'id_user'))) {
                $depart_date = $_POST['depart_date'];
                $return_date = $_POST['return_date'];
                $schedule = $_POST['schedule'];
                $reserve_date = $_POST['reserve_date'];
                $qty = $_POST['qty'];
                $id_user = $_POST['id_user'];
                $total_price = $_POST['total_price'];

                $stmt = $conn->prepare("INSERT INTO tb_trans_boat (`depart_date`, return_date, schedule, `reserve_date`, qty, total_price, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)");
                // var_dump($stmt);
                // die();
                $stmt->bind_param("sssssss", $depart_date, $return_date, $schedule, $reserve_date, $qty, $total_price, $id_user);
                if ($stmt->execute()) {
                    $last = $conn->insert_id;
                    // $stmt->bind_result($depart_date, $return_date, $schedule, $reserve_date, $qty, $id_user);
                    $user = array(
                        'id' => $last,
                        'depart_date' => $depart_date,
                        'return_date' => $return_date,
                        'schedule' => $schedule,
                        'reserve_date' => $reserve_date,
                        'qty' => $qty,
                        'total_price' => $total_price,
                        'id_user' => $id_user
                    );
                    $stmt->close();
                    $response['error'] = false;
                    $response['message'] = 'Transaksi Success';
                    $response['user'] = $user;
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
            if (isTheseParametersAvailable(array('id_boat'))) {
                $id_boat = $_POST['id_boat'];
                $stmt = $conn->prepare(" SELECT tb_schedule.id AS 'ID Schedule', tb_det_boat.id AS 'ID Boat', tb_schedule.`dropup_loc`, 
                                            tb_schedule.`pickup_loc`, tb_schedule.`time`, tb_det_boat.`price`
                                            FROM tb_schedule JOIN tb_det_boat ON tb_det_boat.`id` = tb_schedule.`id_det_boat`
                                            JOIN tb_boat ON tb_boat.`id` = tb_det_boat.`id_boat` WHERE tb_boat.`id` = ? ORDER BY tb_det_boat.`id` DESC");

                $stmt->bind_param("s", $id_boat);
                if ($stmt->execute()) {
                    $stmt->bind_result($id_schedule, $boat, $dropup_loc, $pickup_loc, $time, $price);
                    while ($stmt->fetch()) {
                        $schedule[] = array(
                            'id_schedule' => $id_schedule,
                            'id_boat' => $boat,
                            'pickup_loc' => $pickup_loc,
                            'dropup_loc' => $dropup_loc,
                            'time' => $time,
                            'price' => $price
                        );
                    }
                    $stmt->close();
                    $response['error'] = false;
                    $response['message'] = 'Tampil Success';
                    $response['boat'] = $schedule;
                } else {
                    $response['error'] = false;
                    $response['message'] = 'Invalid !!';
                }
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
