<?php
require_once '../proses/connect.php';
$response = array();
if (isset($_GET['apicall'])) {
    switch ($_GET['apicall']) {
        case 'allowner':
            $stmt = $conn->prepare("SELECT `id`,`name`, id_user, `desc`, `image`, `address`, phone FROM tb_rent_vehicle");
            $stmt->execute();
            $stmt->store_result();
            if ($stmt->num_rows > 0) {
                $stmt->bind_result($id, $name, $id_user, $desc, $image, $address, $phone);
                while ($stmt->fetch()) {
                    $owner[] = array(
                        'id' => $id,
                        'name' => $name,
                        'id_user' => $id,
                        'image' => $image,
                        'address' => $address,
                        'desc' => $desc,
                        'phone' => $phone
                    );
                    $response['error'] = false;
                    $response['message'] = 'Successfull';
                    $response['owner'] = $owner;
                }
            } else {
                $response['error'] = false;
                $response['message'] = 'Invalid !!';
            }
            break;


        case 'searchRental':
            if (isTheseParametersAvailable(array('pick_up', 'return', 'cat'))) {
                $pick_up = $_POST['pick_up'];
                $return = $_POST['return'];
                $cat = $_POST['cat'];

                $stmt = $conn->prepare("SELECT tb_rent_vehicle.`id`,tb_rent_vehicle.`name`, tb_rent_vehicle.`id_user`, tb_rent_vehicle.`address`, tb_rent_vehicle.`desc`, tb_rent_vehicle.`image`,tb_rent_vehicle.`phone`
                                                FROM tb_rent_vehicle
                                                INNER JOIN tb_vehicle ON tb_vehicle.`id_rent_vehicle` = tb_rent_vehicle.`id`
                                                WHERE tb_vehicle.`category` = ? AND tb_vehicle.`id` NOT IN 
                                                (SELECT tb_trans_rent.`id_vehicle` FROM tb_trans_rent
                                                WHERE tb_trans_rent.`take` <= ? AND tb_trans_rent.`return` >= ?)
                                                GROUP BY tb_rent_vehicle.`id`");
                $stmt->bind_param("sss", $cat, $return, $pick_up);
                $stmt->execute();
                $stmt->store_result();
                if ($stmt->num_rows > 0) {
                    $stmt->bind_result($id, $name, $id_user, $address, $desc, $image, $phone);
                    $rental = [];
                    while ($stmt->fetch()) {

                        $owner[] = array(
                            'id' => $id,
                            'name' => $name,
                            'id_user' => $id,
                            'image' => $image,
                            'address' => $address,
                            'desc' => $desc,
                            'phone' => $phone
                        );
                    }
                    if ($owner != []) {
                        $response['error'] = false;
                        $response['message'] = 'Successfull';
                        $response['owner'] = $owner;
                    } else {
                        $response['error'] = false;
                        $response['message'] = 'No vehicle left in that date';
                        $response['owner'] = null;
                    }
                } else {
                    $response['error'] = false;
                    $response['message'] = 'Invalid !!';
                }
            }
            break;

        case 'searchVehicle':
            if (isTheseParametersAvailable(array('pick_up', 'return', 'id_rental'))) {
                $pick_up = $_POST['pick_up'];
                $return = $_POST['return'];
                $id_rental = $_POST['id_rental'];

                $stmt = $conn->prepare("SELECT tb_vehicle.`id`,tb_vehicle.`name`,tb_vehicle.`price`,tb_vehicle.`category`,tb_vehicle.`desc`
                                            FROM tb_vehicle
                                            WHERE tb_vehicle.`id_rent_vehicle` = ? AND tb_vehicle.`id` NOT IN 
                                            (SELECT tb_trans_rent.`id_vehicle` FROM tb_trans_rent
                                            WHERE tb_trans_rent.`take` <= ? AND tb_trans_rent.`return` >= ?)");
                $stmt->bind_param("sss", $id_rental, $return, $pick_up);
                $stmt->execute();
                $stmt->store_result();
                if ($stmt->num_rows > 0) {
                    $stmt->bind_result($id_vehicle, $name, $price, $category, $desc);
                    $vehicle = [];
                    while ($stmt->fetch()) {

                        $vehicle[] = array(
                            'id_vehicle' => $id_vehicle,
                            'name' => $name,
                            'price' => $price,
                            'category' => $category,
                            'desc' => $desc,
                        );
                    }
                    if ($vehicle != []) {
                        $response['error'] = false;
                        $response['message'] = 'Successfull';
                        $response['vehicle'] = $vehicle;
                    } else {
                        $response['error'] = false;
                        $response['message'] = 'No room left';
                        $response['vehicle'] = null;
                    }
                } else {
                    $response['error'] = false;
                    $response['message'] = 'Invalid !!';
                }
            }
            break;

        case 'TransVehicle':
            if (isTheseParametersAvailable(array('name', 'plat', 'category', 'price', 'seat', 'id_rent_vehicle', 'desc', 'status'))) {
                $name = $_POST['name'];
                $plat = $_POST['plat'];
                $category = $_POST['category'];
                $price = $_POST['price'];
                $seat = $_POST['seat'];
                $id_rent_vehicle = $_POST['id_rent_vehicle'];
                $desc = $_POST['desc'];
                $status = $_POST['status'];

                $stmt = $conn->prepare("INSERT INTO tb_vehicle (`name`, plat, category, `price`, seat, id_rent_vehicle, `desc`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                // var_dump($stmt);
                // die();
                $stmt->bind_param("ssssssss", $name, $plat, $category, $price, $seat, $id_rent_vehicle, $desc, $status);
                if ($stmt->execute()) {
                    $last = $conn->insert_id;
                    $user = array(
                        'id' => $last,
                        'name' => $name,
                        'plat' => $plat,
                        'category' => $category,
                        'price' => $price,
                        'seat' => $seat,
                        'id_rent_vehicle' => $id_rent_vehicle,
                        'desc' => $desc,
                        'status' => $status
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

        case 'TransRent':
            if (isTheseParametersAvailable(array('id_user', 'take', 'return', 'total_price', 'id_vehicle', 'transdate'))) {
                $id_user = $_POST['id_user'];
                $take = $_POST['take'];
                $return = $_POST['return'];
                $total_price = $_POST['total_price'];
                $id_vehicle = $_POST['id_vehicle'];
                $transdate = $_POST['transdate'];

                $stmt = $conn->prepare("INSERT INTO tb_trans_rent (`id_user`, `take`, `return`, `total_price`, id_vehicle, trans_date) VALUES (?, ?, ?, ?, ?, ?)");
                // var_dump($stmt);
                // die();
                $stmt->bind_param("ssssss", $id_user, $take, $return, $total_price, $id_vehicle, $transdate);
                if ($stmt->execute()) {
                    $last = $conn->insert_id;
                    // $stmt->bind_result($id_room, $check_in, $check_out, $reserve_date, $total_price, $id_user);
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
        case 'proofVehicle':
            if (isset($_POST['id_transaksi']) and isset($_FILES['image']['name'])) {
                $upload_path = 'transaksi/';
                $server_ip = gethostbyname(gethostname());
                // $upload_url = 'http://' . $server_ip . '/webservice/admin/boat/' . $upload_path;
                $upload_url = '../admin/vehicle/' . $upload_path;
                $id_transaksi = $_POST['id_transaksi'];
                $nama = uniqid('uploaded-', true) . '.' . strtolower(pathinfo($_FILES['image']['name'], PATHINFO_EXTENSION));
                $tmp_image = $_FILES['image']['tmp_name'];
                $fileinfo = pathinfo($_FILES['image']['name']);
                $extension = $fileinfo['extension'];
                $file_url = $upload_url . $nama;
                $file_path = 'webservice/admin/vehicle/' . $upload_path . $nama;
                // var_dump(move_uploaded_file($tmp_image, $file_url));
                // die();
                try {
                    //saving the file 
                    if (move_uploaded_file($tmp_image, $file_url)) {
                        $sql = "UPDATE tb_trans_rent SET proof = '$file_url' WHERE id = '$id_transaksi';";
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
