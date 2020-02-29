<?php
require_once '../proses/connect.php';
$response = array();
if (isset($_GET['apicall'])) {
	switch ($_GET['apicall']) {
        case 'alltour':
            if (isTheseParametersAvailable(array('tour_date','guest'))) {
                $tour_date = $_POST['tour_date'];
                $guest = $_POST['guest'];
                $stmt = $conn->prepare("SELECT `id`,`name`,`price`, id_tour,`max_qty`, `desc`, `image`
                                        FROM tb_tour_package 
                                        WHERE tb_tour_package.`max_qty` >= ? 
                                        AND tb_tour_package.`id` NOT IN 
                                        (SELECT tb_trans_tour.`id_tour_package` 
                                        FROM tb_trans_tour 
                                        WHERE tb_trans_tour.`tour_date` = ?); ");
                $stmt->bind_param("ss", $guest, $tour_date);
                $stmt->execute();
                $stmt->store_result();
                if ($stmt->num_rows > 0) {
                    $stmt->bind_result($id, $name, $price, $id_tour, $max_qty, $desc, $image);
                    while ($stmt->fetch()) {
                        $tour_package [] = array(
                            'id' => $id,
                            'name' => $name,
                            'price' => $price,
                            'id_tour' => $id_tour,
                            'max_qty' => $max_qty,
                            'desc' => $desc,
                            'image' => $image,
                        );
                        $response['error'] = false;
                        $response['message'] = 'Successfull';
                        $response['tour_package'] = $tour_package;
                    }
                } else {
                    $response['error'] = false;
                    $response['message'] = 'No Tour Package left in that date !!';
                }
            }
            break;

            case 'TransTour':
                if (isTheseParametersAvailable(array('tour_date', 'total_price', 'id_user', 'id_tour_package', 'qty', 'reserve_date'))) {
                    $tour_date = $_POST['tour_date'];
                    $total_price = $_POST['total_price'];
                    $id_user = $_POST['id_user'];
                    $id_tour_package = $_POST['id_tour_package'];
                    $qty = $_POST['qty'];
                    $reserve_date = $_POST['reserve_date'];
    
                    $stmt = $conn->prepare("INSERT INTO tb_trans_tour (`tour_date`, total_price, id_user, `id_tour_package`, qty, reserve_date) VALUES (?, ?, ?, ?, ?, ?)");
                    // var_dump($stmt);
                    // die();
                    $stmt->bind_param("ssssss", $tour_date, $total_price, $id_user, $id_tour_package, $qty, $reserve_date);
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
    
            case 'getTransTour':
                if (isTheseParametersAvailable(array('id_user'))) {
                    $id_user = $_POST['id_user'];
                    $stmt = $conn->prepare("SELECT tb_tour_package.`name`, tb_tour_package.`desc`, tb_tour_package.`image`, tb_trans_tour.* FROM tb_trans_tour 
                                                JOIN tb_tour_package ON tb_trans_tour.`id_tour_package` = tb_tour_package.`id`
                                                WHERE tb_trans_tour.`id_user` = ?
                                                ORDER BY tb_trans_tour.`id` DESC");
                    // var_dump($id_user);
                    // die();
                    $stmt->bind_param("s", $id_user);
                    if ($stmt->execute()) {
                        $stmt->bind_result($name, $desc, $image, $id, $tour_date, $total_price, $id_user, $id_tour_package, $qty, $reserve_date, $status, $proof);
                        while ($stmt->fetch()) {
                            $trans_tour[] = array(
                                'id' => $id,
                                'name' => $name,
                                'desc' => $desc,
                                'image' => $image,
                                'tour_date' => $tour_date,
                                'total_price' => $total_price,
                                'id_user' => $id_user,
                                'id_tour_package' => $id_tour_package,
                                'qty' => $qty,
                                'reserve_date' => $reserve_date,
                                'status' => $status,
                                'proof' =>$proof,
                            );
                        }
                        $stmt->close();
                        $response['error'] = false;
                        $response['message'] = 'Transaksi Success';
                        $response['trans_tour'] = $trans_tour;
                    } else {
                        $response['error'] = false;
                        $response['message'] = 'Invalid !!';
                    }
                }
                break;

                case 'proofTour':
                    if (isset($_POST['id_transaksi']) and isset($_FILES['image']['name'])) {
                        $upload_path = 'transaksi/';
                        $server_ip = gethostbyname(gethostname());
                        // $upload_url = 'http://' . $server_ip . '/webservice/admin/boat/' . $upload_path;
                        $upload_url = '../admin/tour/' . $upload_path;
                        $id_transaksi = $_POST['id_transaksi'];
                        $nama = uniqid('uploaded-', true) . '.' . strtolower(pathinfo($_FILES['image']['name'], PATHINFO_EXTENSION));
                        $tmp_image = $_FILES['image']['tmp_name'];
                        $fileinfo = pathinfo($_FILES['image']['name']);
                        $extension = $fileinfo['extension'];
                        $file_url = $upload_url . $nama;
                        // $file_path = 'webservice/admin/tour/' . $upload_path . $nama;
                        // var_dump(move_uploaded_file($tmp_image, $file_url));
                        // die();
                        try {
                            //saving the file 
                            if (move_uploaded_file($tmp_image, $file_url)) {
                                $sql = "UPDATE tb_trans_tour SET proof = '$nama' WHERE id = '$id_transaksi';";
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

?>