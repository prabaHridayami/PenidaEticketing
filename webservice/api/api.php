<?php
require_once '../proses/connect.php';
$response = array();
if (isset($_GET['apicall'])) {
	switch ($_GET['apicall']) {
		case 'signup':
			if (isTheseParametersAvailable(array('name', 'username', 'email', 'password', 'phone'))) {
				$name = $_POST['name'];
				$username = $_POST['username'];
				$email = $_POST['email'];
				$password = md5($_POST['password']);
				$phone = $_POST['phone'];

				$stmt = $conn->prepare("SELECT * FROM tb_user WHERE username = ? OR email = ?");
				$stmt->bind_param("ss", $username, $email);
				$stmt->execute();
				$stmt->store_result();

				if ($stmt->num_rows > 0) {
					$response['error'] = true;
					$response['message'] = 'User already registered';
					$stmt->close();
				} else {
					$stmt = $conn->prepare("INSERT INTO tb_user (`name`, username, email, `password`, phone, created_at, updated_at) VALUES (?, ?, ?, ?, ?, NOW(), NOW())");
					$stmt->bind_param("sssss", $name, $username, $email, $password, $phone);
					if ($stmt->execute()) {
						$stmt = $conn->prepare("SELECT `name`,username, email, phone FROM tb_user WHERE username = ?");
						$stmt->bind_param("s", $username);
						$stmt->execute();
						$stmt->bind_result($name, $username, $email, $phone);
						$stmt->fetch();
						$user = array(
							'name' => $name,
							'username' => $username,
							'email' => $email,
							'phone' => $phone
						);
						$stmt->close();
						$response['error'] = false;
						$response['message'] = 'User registered successfully';
						$response['user'] = $user;
					}
				}
			} else {
				$response['error'] = true;
				$response['message'] = 'required parameters are not available';
			}

			break;

		case 'login':
			if (isTheseParametersAvailable(array('username', 'password'))) {
				$username = $_POST['username'];
				$password = md5($_POST['password']);
				$stmt = $conn->prepare("SELECT `name`,username,email,phone FROM tb_user WHERE username = ? AND `password` = ?");
				$stmt->bind_param("ss", $username, $password);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($name, $username, $email, $phone);
					$stmt->fetch();
					$user = array(
						'name' => $name,
						'username' => $username,
						'email' => $email,
						'phone' => $phone
					);
					$response['error'] = false;
					$response['message'] = 'Login successfull';
					$response['user'] = $user;
				} else {
					$response['error'] = false;
					$response['message'] = 'Invalid username or password';
				}
			}
			break;
			// GET ID BOAT
		case 'getboat':
			if (isTheseParametersAvailable(array('id'))) {
				$id_boat = $_POST['id'];
				$stmt = $conn->prepare("SELECT `name`, id_user, `image`, `desc`, phone FROM tb_boat WHERE id = ?");
				$stmt->bind_param("s", $id_boat);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id, $name, $image, $desc, $phone);
					$stmt->fetch();
					$user = array(
						'name' => $name,
						'id_user' => $id,
						'image' => $image,
						'desc' => $desc,
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
			// GET ID HOTEL
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
			// GET ID VEHICLE
		case 'getvehicle':
			if (isTheseParametersAvailable(array('id'))) {
				$id_vehicle = $_POST['id'];
				$stmt = $conn->prepare("SELECT `name`, id_user, `desc`, `image`, `address`, phone FROM tb_rent_vehicle WHERE id = ?");
				$stmt->bind_param("s", $id_vehicle);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id, $name, $image, $address, $desc, $phone);
					$stmt->fetch();
					$user = array(
						'name' => $name,
						'id_user' => $id,
						'image' => $image,
						'address' => $address,
						'desc' => $desc,
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
			// GET ID WATERSPORT
		case 'getwatersport':
			if (isTheseParametersAvailable(array('id'))) {
				$id_sport = $_POST['id'];
				$stmt = $conn->prepare("SELECT `name`, id_user, `image`, `address`, phone FROM tb_watersport WHERE id = ?");
				$stmt->bind_param("s", $id_sport);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id, $name, $image, $address, $phone);
					$stmt->fetch();
					$user = array(
						'id_user' => $id,
						'name' => $name,
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
			// GET ID TOUR
		case 'gettour':
			if (isTheseParametersAvailable(array('id'))) {
				$id_tour = $_POST['id'];
				$stmt = $conn->prepare("SELECT `name`, id_user, `address`, phone FROM tb_tour WHERE id = ?");
				$stmt->bind_param("s", $id_tour);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($name, $id, $address, $phone);
					$stmt->fetch();
					$user = array(
						'name' => $name,
						'id_user' => $id,
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
			// GET ID Attraction
		case 'getAttraction':
			if (isTheseParametersAvailable(array('id'))) {
				$id_attraction = $_POST['id'];
				$stmt = $conn->prepare("SELECT id, `name`, id_watersport, `price`, `desc`, `image` FROM tb_attraction WHERE id_watersport = ?");
				$stmt->bind_param("s", $id_attraction);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id, $name, $id_watersport, $price, $desc, $image);
					while ($stmt->fetch()) {
						$user[] = array(
							'id' => $id,
							'name' => $name,
							'id_watersport' => $id_watersport,
							'price' => $price,
							'desc' => $desc,
							'image' => $image,
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

		case 'getAttraction':
			if (isTheseParametersAvailable(array('id'))) {
				$id_attraction = $_POST['id'];
				$stmt = $conn->prepare("SELECT id, `name`, id_watersport, `price`, `desc`, `image` FROM tb_attraction WHERE id_watersport = ?");
				$stmt->bind_param("s", $id_attraction);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id, $name, $id_watersport, $price, $desc, $image);
					while ($stmt->fetch()) {
						$user[] = array(
							'id' => $id,
							'name' => $name,
							'id_watersport' => $id_watersport,
							'price' => $price,
							'desc' => $desc,
							'image' => $image,
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

			case 'getRoom':
				if (isTheseParametersAvailable(array('id'))) {
					$id_hotel = $_POST['id'];
					$stmt = $conn->prepare("SELECT tb_room.`id`, tb_category_room.`id`, tb_room.`name`, tb_category_room.`name`, price, `desc` FROM tb_room INNER JOIN tb_category_room ON tb_room.id_category = tb_category_room.id WHERE tb_category_room.id_hotel = ?");					
					$stmt->bind_param("s", $id_hotel);
					$stmt->execute();
					$stmt->store_result();
					if ($stmt->num_rows > 0) {
						$stmt->bind_result($id_room, $id_cate, $name_room, $name_cate, $price, $desc);
						while ($stmt->fetch()) {
							$user[] = array(
								'id_room' => $id_room,
								'id_cate' => $id_cate,
								'name_room' => $name_room,
								'name_cate' => $name_cate,
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

		case 'allhotel':
			// if (isTheseParametersAvailable(array('id'))) {					
			$stmt = $conn->prepare("SELECT id, id_user, `name`, `address`, `phone`, `image` FROM tb_hotel");
			$stmt->execute();
			$stmt->store_result();
			if ($stmt->num_rows > 0) {
				$stmt->bind_result($id, $id_user, $name, $address, $phone, $image);
				while ($stmt->fetch()) {
					$user[] = array(
						'id' => $id,
						'id_user' => $id_user,
						'name' => $name,
						'address' => $address,
						'phone' => $phone,
						'image' => $image,
					);
				}
				$response['error'] = false;
				$response['message'] = 'Successfull';
				$response['user'] = $user;
			} else {
				$response['error'] = false;
				$response['message'] = 'Invalid !!';
			}
			// }
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
