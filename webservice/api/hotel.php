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
			}

			break;
		case 'getTransHotel':
			if (isTheseParametersAvailable(array('id_user'))) {
				$id_user = $_POST['id_user'];
				$stmt = $conn->prepare("SELECT tb_hotel.`id`, tb_hotel.`name`,tb_hotel.`address`,tb_hotel.`phone`, tb_hotel.`image`,
											tb_category_room.`id`,tb_category_room.`name`,tb_category_room.`price`,tb_category_room.`desc`,
											tb_room.`id`,tb_room.`name`,tb_trans_hotel.`id`,tb_trans_hotel.`check_in`,tb_trans_hotel.`check_out`,tb_trans_hotel.`reserve_date`,
											tb_trans_hotel.`total_price`,tb_trans_hotel.`status`
											FROM tb_trans_hotel
											INNER JOIN tb_room ON tb_trans_hotel.`id_room` = tb_room.`id`
											INNER JOIN tb_category_room ON tb_room.`id_category`= tb_category_room.`id`
											INNER JOIN tb_hotel ON tb_category_room.`id_hotel`=tb_hotel.`id`
											WHERE tb_trans_hotel.`id_user`= ?");

				$stmt->bind_param("s", $id_user);
				if ($stmt->execute()) {
					$stmt->bind_result($id, $name, $address, $phone, $image, $id_cat, $cat_name, $cat_price, $cat_desc, $id_room, $room_name, $id_trans, $check_in, $check_out, $reserve_date, $total_price, $status);
					while ($stmt->fetch()) {
						$trans_hotel[] = array(
							'id' => $id,
							'name' => $name,
							'address' => $address,
							'phone' => $phone,
							'image' => $image,
							'id_cat' => $id_cat,
							'cat_name' => $cat_name,
							'cat_price' => $cat_price,
							'cat_desc' => $cat_desc,
							'id_trans' => $id_trans,
							'check_in' => $check_in,
							'check_out' => $check_out,
							'reserve_date' => $reserve_date,
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
		case 'searchRoom':
			if (isTheseParametersAvailable(array('check_in', 'check_out', 'guest'))) {
				// $id_hotel = $_POST['id'];
				$check_out = $_POST['check_in'];
				$check_in = $_POST['check_out'];
				$room = $_POST['room'];
				$guest = $_POST['guest'];
				$stmt = $conn->prepare("SELECT tb_category_room.`id_hotel`, tb_hotel.`name`, tb_hotel.`address`, tb_hotel.`phone`, tb_hotel.`image`, tb_hotel.`check_in`, tb_hotel.`check_out`, tb_category_room.`id`,tb_category_room.`name`,tb_category_room.`price`,tb_category_room.`desc`, COUNT(tb_room.`id_category`) AS num_room FROM tb_room
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
					$stmt->bind_result($id_hotel, $hotel_name, $hotel_address, $hotel_phone, $hotel_image, $check_in, $check_out, $id_cate, $name_cate, $price, $desc, $num_room);
					$hotel = [];
					while ($stmt->fetch()) {
						if ($num_room >= $room) {
							$hotel[] = array(
								'id_hotel' => $id_hotel,
								'hotel_name' => $hotel_name,
								'hotel_address' => $hotel_address,
								'hotel_phone' => $hotel_phone,
								'hotel_image' => $hotel_image,
								'checkin_time' => $check_in,
								'checkout_time' => $check_out,
								'id_cate' => $id_cate,
								'name_cate' => $name_cate,
								'price' => $price,
								'desc' => $desc,
								'num_room' => $num_room,
							);
						}
					}
					if ($hotel != []) {
						$response['error'] = false;
						$response['message'] = 'Successfull';
						$response['hotel'] = $hotel;
					} else {
						$response['error'] = false;
						$response['message'] = 'No room left';
					}
				} else {
					$response['error'] = false;
					$response['message'] = 'Invalid !!';
				}
			}
			break;
		case 'getRoom':
			if (isTheseParametersAvailable(array('id'))) {
				$id_hotel = $_POST['id'];
				$check_out = $_POST['check_in'];
				$check_in = $_POST['check_out'];

				$stmt = $conn->prepare("SELECT tb_category_room.`id`,tb_category_room.`name`,tb_category_room.`price`,tb_room.`id`,tb_room.`name`
					   FROM tb_room
					   INNER JOIN tb_category_room ON tb_category_room.`id` = tb_room.`id_category`
					   INNER JOIN tb_hotel ON tb_category_room.`id` = tb_hotel.id
					   WHERE tb_category_room.`id_hotel`=? AND tb_room.`id` NOT IN
					   ( SELECT tb_room.`id` 
					   FROM tb_room 
					   INNER JOIN tb_trans_hotel ON tb_room.`id` = tb_trans_hotel.`id_room`
					   WHERE tb_trans_hotel.`check_in` <= ? AND tb_trans_hotel.`check_out`>= ?)
					   GROUP BY tb_room.`id`
					   ORDER BY tb_category_room.`price` ASC;
					   ");
				$stmt->bind_param("sss", $id_hotel, $check_in, $check_out);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id_cat, $category, $price, $id_room, $room_name);
					while ($stmt->fetch()) {
						$room[] = array(
							'id_cat' => $id_cat,
							'category' => $category,
							'price' => $price,
							'id_room' => $id_room,
							'room_name' => $room_name,
						);
					}
					$response['error'] = false;
					$response['message'] = 'Successfull';
					$response['room'] = $room;
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
