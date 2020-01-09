<?php
require_once '../proses/connect.php';
$response = array();
if (isset($_GET['apicall'])) {
	switch ($_GET['apicall']) {
		case 'gethotel':
			if (isTheseParametersAvailable(array('id'))) {
				$id_hotel = $_POST['id'];
				$stmt = $conn->prepare("SELECT `name`, id_user, `image`, `address`, phone FROM tb_hotel WHERE id = ?");
				$stmt->bind_param("s", $id_hotel);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id, $name, $image, $address, $phone);
					$stmt->fetch();
					$user = array(
						'name' => $name,
						'id_user' => $id,
						'image' => $image,
						'address' => $address,
						'phone' => $phone
					);
					$response['error'] = false;
					$response['message'] = 'Successfull';
					$response['user'] = $user;
				} else {
					$response['error'] = false;
					$response['message'] = 'Invalid !!';
				}
			}
			break;

		case 'getRoom':
			if (isTheseParametersAvailable(array('id'))) {
				$id_hotel = $_POST['id'];
				$check_in = $_POST['check_in'];
				$check_out = $_POST['check_out'];
				$stmt = $conn->prepare("SELECT tb_room.`id`, tb_room.`name`, tb_category_room.`id`, tb_category_room.`name`,tb_category_room.`price`,tb_category_room.`desc`
											FROM tb_room
											INNER JOIN tb_category_room ON tb_category_room.`id` = tb_room.`id_category`
											WHERE tb_category_room.`id_hotel`= ? AND tb_room.`id` NOT IN
											(SELECT tb_room.`id` 
											FROM tb_room 
											INNER JOIN tb_trans_hotel ON tb_room.`id` = tb_trans_hotel.`id_room`
											WHERE tb_trans_hotel.`check_in` <= ? AND tb_trans_hotel.`check_out`>= ?)");
				$stmt->bind_param("sss", $id_hotel, $check_in, $check_out);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id_room, $name_room, $id_cate, $cate_name, $price, $desc);
					while ($stmt->fetch()) {
						$user[] = array(
							'id_room' => $id_room,
							'name_room' => $name_room,
							'id_cate' => $id_cate,
							'cate_name' => $cate_name,
							'price' => $price,
							'desc' => $desc


						);
					}
					$response['error'] = false;
					$response['message'] = 'Successfull';
					$response['user'] = $user;
				} else {
					$response['error'] = false;
					$response['message'] = 'Invalid !!';
				}
			}
			break;
		case 'getRoom1':
			if (isTheseParametersAvailable(array('id'))) {
				// $id_hotel = $_POST['id'];
				$check_in = $_POST['check_in'];
				$check_out = $_POST['check_out'];
				$room = $_POST['room'];
				$guest = $_POST['guest'];
				$stmt = $conn->prepare("SELECT tb_category_room.`id_hotel`, tb_hotel.`name`, tb_hotel.`address`, tb_hotel.`phone`, tb_hotel.`image`, tb_category_room.`id`,tb_category_room.`name`,tb_category_room.`price`,tb_category_room.`desc`, COUNT(tb_room.`id_category`) AS num_room FROM tb_room
											INNER JOIN tb_category_room ON tb_category_room.`id` = tb_room.`id_category`
											INNER JOIN tb_hotel ON tb_category_room.`id` = tb_hotel.id
											WHERE tb_room.`guest` >=? AND tb_room.`id` NOT IN
											( SELECT tb_room.`id` 
											FROM tb_room 
											INNER JOIN tb_trans_hotel ON tb_room.`id` = tb_trans_hotel.`id_room`
											WHERE tb_trans_hotel.`check_in` <= ? AND tb_trans_hotel.`check_out`>= ?)
											GROUP BY tb_category_room.`id`
											ORDER BY tb_category_room.`price` ASC");
				$stmt->bind_param("sss", $guest, $check_in, $check_out);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id_hotel, $hotel_name, $hotel_address, $hotel_phone, $hotel_image, $id_cate, $name_cate, $price, $desc, $num_room);
					while ($stmt->fetch()) {
						$room = null;
						$stmt1 = $conn->prepare("SELECT tb_room.`id`, tb_room.`name`
													FROM tb_room
													WHERE tb_room.`id_category`= ? AND tb_room.`id` NOT IN
													(SELECT tb_room.`id` 
													FROM tb_room 
													INNER JOIN tb_trans_hotel ON tb_room.`id` = tb_trans_hotel.`id_room`
													WHERE tb_trans_hotel.`check_in` <= ? AND tb_trans_hotel.`check_out`>= ? )");
						$stmt1->bind_param("sss", $id_cate, $check_in, $check_out);
						$stmt1->execute();
						$stmt1->store_result();
						if ($stmt1->num_rows > 0) {
							$stmt1->bind_result($id_room, $name_room);
							while ($stmt1->fetch()) {
								$room[] = array(
									'id_room' => $id_room,
									'name_room' => $name_room
								);
							}
						}

						$user[] = array(
							'id_hotel' => $id_hotel,
							'hotel_name' => $hotel_name,
							'hotel_address' => $hotel_address,
							'hotel_phone' => $hotel_phone,
							'hotel_image' => $hotel_image,
							'id_cate' => $id_cate,
							'name_cate' => $name_cate,
							'price' => $price,
							'desc' => $desc,
							'num_room' => $num_room,
							'rooms' => $room
						);
					}
					$response['error'] = false;
					$response['message'] = 'Successfull';
					$response['user'] = $user;
				} else {
					$response['error'] = false;
					$response['message'] = 'Invalid !!';
				}
			}
			break;
		case 'getRoomlow':
			if (isTheseParametersAvailable(array('id'))) {
				// $id_hotel = $_POST['id'];
				$check_in = $_POST['check_in'];
				$check_out = $_POST['check_out'];
				$room = $_POST['room'];
				$guest = $_POST['guest'];
				$stmt = $conn->prepare("SELECT tb_category_room.`id_hotel`, tb_hotel.`name`, tb_hotel.`address`, tb_hotel.`phone`, tb_hotel.`image`, tb_category_room.`id`,tb_category_room.`name`,tb_category_room.`price`,tb_category_room.`desc`, COUNT(tb_room.`id_category`) AS num_room FROM tb_room
											INNER JOIN tb_category_room ON tb_category_room.`id` = tb_room.`id_category`
											INNER JOIN tb_hotel ON tb_category_room.`id` = tb_hotel.id
											WHERE tb_room.`guest` >=? AND tb_room.`id` NOT IN
											( SELECT tb_room.`id` 
											FROM tb_room 
											INNER JOIN tb_trans_hotel ON tb_room.`id` = tb_trans_hotel.`id_room`
											WHERE tb_trans_hotel.`check_in` <= ? AND tb_trans_hotel.`check_out`>= ?)
											GROUP BY tb_category_room.`id_hotel`
											ORDER BY tb_category_room.`price` ASC");
				$stmt->bind_param("sss", $guest, $check_in, $check_out);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id_hotel, $hotel_name, $hotel_address, $hotel_phone, $hotel_image, $id_cate, $name_cate, $price, $desc, $num_room);
					while ($stmt->fetch()) {
						$room = null;
						$stmt1 = $conn->prepare("SELECT tb_room.`id`, tb_room.`name`
													FROM tb_room
													WHERE tb_room.`id_category`= ? AND tb_room.`id` NOT IN
													(SELECT tb_room.`id` 
													FROM tb_room 
													INNER JOIN tb_trans_hotel ON tb_room.`id` = tb_trans_hotel.`id_room`
													WHERE tb_trans_hotel.`check_in` <= ? AND tb_trans_hotel.`check_out`>= ? )");
						$stmt1->bind_param("sss", $id_cate, $check_in, $check_out);
						$stmt1->execute();
						$stmt1->store_result();
						if ($stmt1->num_rows > 0) {
							$stmt1->bind_result($id_room, $name_room);
							while ($stmt1->fetch()) {
								$room[] = array(
									'id_room' => $id_room,
									'name_room' => $name_room
								);
							}
						}

						$hotel[] = array(
							'id_hotel' => $id_hotel,
							'hotel_name' => $hotel_name,
							'hotel_address' => $hotel_address,
							'hotel_phone' => $hotel_phone,
							'hotel_image' => $hotel_image,
							'id_cate' => $id_cate,
							'name_cate' => $name_cate,
							'price' => $price,
							'desc' => $desc,
							'num_room' => $num_room,
							'rooms' => $room
						);
					}
					$response['error'] = false;
					$response['message'] = 'Successfull';
					$response['hotel'] = $hotel;
				} else {
					$response['error'] = false;
					$response['message'] = 'Invalid !!';
				}
			}
			break;
		case 'getRoomlow1':
			if (isTheseParametersAvailable(array('id'))) {
				// $id_hotel = $_POST['id'];
				$check_in = $_POST['check_in'];
				$check_out = $_POST['check_out'];
				$room = $_POST['room'];
				$guest = $_POST['guest'];
				$stmt = $conn->prepare("SELECT tb_category_room.`id_hotel`, tb_hotel.`name`, tb_hotel.`address`, tb_hotel.`phone`, tb_hotel.`image`, tb_category_room.`id`,tb_category_room.`name`,tb_category_room.`price`,tb_category_room.`desc`, COUNT(tb_room.`id_category`) AS num_room FROM tb_room
												INNER JOIN tb_category_room ON tb_category_room.`id` = tb_room.`id_category`
												INNER JOIN tb_hotel ON tb_category_room.`id` = tb_hotel.id
												WHERE tb_room.`guest` >=? AND tb_room.`id` NOT IN
												( SELECT tb_room.`id` 
												FROM tb_room 
												INNER JOIN tb_trans_hotel ON tb_room.`id` = tb_trans_hotel.`id_room`
												WHERE tb_trans_hotel.`check_in` <= ? AND tb_trans_hotel.`check_out`>= ?)
												GROUP BY tb_category_room.`id_hotel`
												ORDER BY tb_category_room.`price` ASC");
				$stmt->bind_param("sss", $guest, $check_in, $check_out);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id_hotel, $hotel_name, $hotel_address, $hotel_phone, $hotel_image, $id_cate, $name_cate, $price, $desc, $num_room);
					while ($stmt->fetch()) {
						$room = null;

						$hotel[] = array(
							'id_hotel' => $id_hotel,
							'hotel_name' => $hotel_name,
							'hotel_address' => $hotel_address,
							'hotel_phone' => $hotel_phone,
							'hotel_image' => $hotel_image,
							'id_cate' => $id_cate,
							'name_cate' => $name_cate,
							'price' => $price,
							'desc' => $desc,
							'num_room' => $num_room,
						);
					}
					$response['error'] = false;
					$response['message'] = 'Successfull';
					$response['hotel'] = $hotel;
				} else {
					$response['error'] = false;
					$response['message'] = 'Invalid !!';
				}
			}
			break;
		case 'allhotel':
			// if (isTheseParametersAvailable(array('id'))) {					
			$stmt = $conn->prepare("SELECT tb_category_room.`id_hotel`, tb_hotel.`name`, tb_hotel.`address`, tb_hotel.`phone`, tb_hotel.`image`, tb_category_room.`id`,tb_category_room.`name`,tb_category_room.`price`,tb_category_room.`desc`, COUNT(tb_room.`id_category`) AS num_room FROM tb_room
				INNER JOIN tb_category_room ON tb_category_room.`id` = tb_room.`id_category`
				INNER JOIN tb_hotel ON tb_category_room.`id` = tb_hotel.id
				GROUP BY tb_category_room.`id_hotel`
				ORDER BY tb_category_room.`price` ASC");
			$stmt->execute();
			$stmt->store_result();
			if ($stmt->num_rows > 0) {
				$stmt->bind_result($id_hotel, $hotel_name, $hotel_address, $hotel_phone, $hotel_image, $id_cate, $name_cate, $price, $desc, $num_room);
				while ($stmt->fetch()) {
					$room = null;

					$hotel[] = array(
						'id_hotel' => $id_hotel,
						'hotel_name' => $hotel_name,
						'hotel_address' => $hotel_address,
						'hotel_phone' => $hotel_phone,
						'hotel_image' => $hotel_image,
						'id_cate' => $id_cate,
						'name_cate' => $name_cate,
						'price' => $price,
						'desc' => $desc,
						'num_room' => $num_room,
					);
				}
				$response['error'] = false;
				$response['message'] = 'Successfull';
				$response['hotel'] = $hotel;
			} else {
				$response['error'] = false;
				$response['message'] = 'Invalid !!';
			}

			break;

		case 'TransHotel':
			if (isTheseParametersAvailable(array('id_room', 'check_in', 'check_out', 'reserve_date', 'total_price', 'id_user'))) {
				$id_room = $_POST['id_room'];
				$check_in = $_POST['check_in'];
				$check_out = $_POST['check_out'];
				$reserve_date = $_POST['reserve_date'];
				$total_price = $_POST['total_price'];
				$id_user = $_POST['id_user'];

				$stmt = $conn->prepare("INSERT INTO tb_trans_hotel (`id_room`, check_in, check_out, `reserve_date`, total_price, id_user) VALUES (?, ?, ?, ?, ?, ?)");
				// var_dump($stmt);
				// die();
				$stmt->bind_param("ssssss", $id_room, $check_in, $check_out, $reserve_date, $total_price, $id_user);
				if ($stmt->execute()) {
					$last = $conn->insert_id;
					// $stmt->bind_result($id_room, $check_in, $check_out, $reserve_date, $total_price, $id_user);
					$user = array(
						'id' => $last,
						'id_room' => $id_room,
						'check_in' => $check_in,
						'check_out' => $check_out,
						'reserve_date' => $reserve_date,
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
				break;
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
