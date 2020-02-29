<?php
require_once '../proses/connect.php';
$response = array();
if (isset($_GET['apicall'])) {
	switch ($_GET['apicall']) {
        case 'allatt':
            if (isTheseParametersAvailable(array('id'))) {
                $id = $_POST['id'];
                $stmt = $conn->prepare("SELECT id, `name`, id_watersport, `price`, `desc`, `image` FROM tb_attraction WHERE `id_watersport`=?");
                $stmt->bind_param("s", $id);
                $stmt->execute();
                $stmt->store_result();
                $attraction = [];
                if ($stmt->num_rows > 0) {
                    $stmt->bind_result($id_att, $name, $id_watersport, $price, $desc, $image);
                    while ($stmt->fetch()) {
                        $attraction [] = array(
                            'id' => $id_att,
                            'name' => $name,
                            'id_watersport' => $id_watersport,
                            'price' => $price,
                            'desc' => $desc,
                            'image' => "http://192.168.43.79/webservice/partner/watersport/image/".$image,
                        );
                        $response['error'] = false;
                        $response['message'] = 'Successfull';
                        $response['attraction'] = $attraction;
                    }
                } else {
                    $response['error'] = false;
                    $response['message'] = 'No Attraction Available !!';
                    $response['attraction'] = $attraction;
                }
            }
            break;
        
            case 'allwatersport':
                $stmt = $conn->prepare("SELECT id, `name`, `id_user`, `address`, `phone`, `image` FROM tb_watersport");
                $stmt->execute();
                $stmt->store_result();
                if ($stmt->num_rows > 0) {
                    $stmt->bind_result($id, $name, $id_user, $address, $phone, $image);
                    while ($stmt->fetch()) {
                        $watersport [] = array(
                            'id' => $id,
                            'name' => $name,
                            'id_user' => $id_user,
                            'address' => $address,
                            'phone' => $phone,
                            'image' => "http://192.168.43.79/webservice/partner/watersport/image/".$image,
                        );
                        $response['error'] = false;
                        $response['message'] = 'Successfull';
                        $response['watersport'] = $watersport;
                    }
                } else {
                    $response['error'] = false;
                    $response['message'] = 'Invalid !!';
                }
                break;

            case 'TransWatersport':
                if (isTheseParametersAvailable(array('id_attraction', 'reserve_date', 'qty', 'date', 'total_price', 'id_user'))) {
                    $id_attraction = $_POST['id_attraction'];
                    $reserve_date = $_POST['reserve_date'];
                    $qty = $_POST['qty'];
                    $date = $_POST['date'];
                    $total_price = $_POST['total_price'];
                    $id_user = $_POST['id_user'];
    
                    $stmt = $conn->prepare("INSERT INTO tb_trans_watersport (`id_attraction`, `reserve_date`, `qty`, `date`, total_price, id_user) VALUES (?, ?, ?, ?, ?, ?)");
                    // var_dump($stmt);
                    // die();
                    $stmt->bind_param("ssssss", $id_attraction, $reserve_date, $qty, $date, $total_price, $id_user);
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
            
            case 'getTransWatersport': 
                if (isTheseParametersAvailable(array('id_user'))) {
                $id_user=$_POST['id_user'];
                $stmt=$conn->prepare("SELECT tb_watersport.`id`, tb_watersport.`name`, 
                                        tb_watersport.`address`, tb_watersport.`phone`, 
                                        tb_watersport.`image`, tb_attraction.`id`, 
                                        tb_attraction.`name`, tb_attraction.`price`, 
                                        tb_attraction.`desc`, tb_attraction.`image`,
                                        tb_trans_watersport.`id`, tb_trans_watersport.`reserve_date`, 
                                        tb_trans_watersport.`qty`, tb_trans_watersport.`date`, 
                                        tb_trans_watersport.`total_price`, tb_trans_watersport.`status`
                                        FROM tb_trans_watersport INNER JOIN tb_attraction ON tb_trans_watersport.`id_attraction`=tb_attraction.`id` 
                                        INNER JOIN tb_watersport ON tb_watersport.`id`=tb_attraction.`id_watersport`
                                        WHERE tb_trans_watersport.`id_user`=?
                                        ORDER BY tb_trans_watersport.`id` DESC");
    
                    $stmt->bind_param("s", $id_user);
    
                    if ($stmt->execute()) {
                        $stmt->bind_result($id, $name, $address, $phone, $image, $id_att, $att_name, $att_price, $att_desc, $att_image, $id_trans, $reserve_date, $qty, $play_date, $total_price, $status);
                        $trans_watersport = [];
                        while ($stmt->fetch()) {
                            $trans_watersport[]=array(
                                'id'=> $id,
                                'name'=> $name,
                                'address'=> $address,
                                'phone'=> $phone,
                                'image'=> "http://192.168.43.79/webservice/partner/watersport/image/".$image,
                                'id_att'=> $id_att,
                                'att_name'=> $att_name,
                                'att_price'=> $att_price,
                                'att_desc'=> $att_desc,
                                'att_image'=> $att_image,
                                'id_trans'=> $id_trans,
                                'reserve_date'=> $reserve_date,
                                'qty'=> $qty,
                                'play_date'=> $play_date,
                                'total_price'=> $total_price,
                                'status'=> $status);
                        }
    
                        $stmt->close();
    
    
                        if($trans_watersport !=null) {
                            $response['error']=false;
                            $response['message']='Transaksi Success';
                            $response['trans_watersport']=$trans_watersport;
                        }
    
                        else {
                            $response['error']=false;
                            $response['message']='Transaksi Success';
                            $response['trans_watersport']=null;
                        }
    
    
                    }
    
                    else {
                        $response['error']=false;
                        $response['message']='Invalid !!';
                    }
                }
    
                break;

                case 'proofWatersport':
                    if (isset($_POST['id_transaksi']) and isset($_FILES['image']['name'])) {
                        $upload_path = 'transaksi/';
                        $server_ip = gethostbyname(gethostname());
                        // $upload_url = 'http://' . $server_ip . '/webservice/admin/boat/' . $upload_path;
                        $upload_url = '../admin/watersport/' . $upload_path;
                        $id_transaksi = $_POST['id_transaksi'];
                        $nama = uniqid('uploaded-', true) . '.' . strtolower(pathinfo($_FILES['image']['name'], PATHINFO_EXTENSION));
                        $tmp_image = $_FILES['image']['tmp_name'];
                        $fileinfo = pathinfo($_FILES['image']['name']);
                        $extension = $fileinfo['extension'];
                        $file_url = $upload_url . $nama;
                        $file_path = 'webservice/admin/watersport/' . $upload_path . $nama;
                        // var_dump(move_uploaded_file($tmp_image, $file_url));
                        // die();
                        try {
                            //saving the file 
                            if (move_uploaded_file($tmp_image, $file_url)) {
                                $sql = "UPDATE tb_trans_watersport SET proof = '$nama' WHERE id = '$id_transaksi';";
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