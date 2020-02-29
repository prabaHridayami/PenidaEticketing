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
				$stmt = $conn->prepare("SELECT `id`,`name`,username,email,phone FROM tb_user WHERE username = ? AND `password` = ?");
				$stmt->bind_param("ss", $username, $password);
				$stmt->execute();
				$stmt->store_result();
				if ($stmt->num_rows > 0) {
					$stmt->bind_result($id,$name, $username, $email, $phone);
					$stmt->fetch();
					$user = array(
						'id'=>$id,
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

			case 'editprofile':
				if (isTheseParametersAvailable(array('name', 'username', 'phone','id'))) {
					$name = $_POST['name'];
					$username = $_POST['username'];
					$phone = $_POST['phone'];
					$updated_at = $_POST['updated_at'];
					$email = $_POST['email'];
					$id_user = $_POST['id'];

					$stmt = $conn->prepare("UPDATE tb_user SET `name` = ?, `username` = ?, `phone` = ?, `updated_at`=? WHERE `id` = ?;");
					$stmt->bind_param("ssssd", $name, $username,$phone,$updated_at,$id_user);
					$stmt->execute();
					
					$user = array(
						'id'=>$id_user,
						'name' => $name,
						'username' => $username,
						'phone' => $phone,
						'email' => $email
					);
				
					if ($stmt->affected_rows>0) {
						$response['error'] = false;
						$response['message'] = 'Edit successfull';
						$response['user'] = $user;
						
					} else {
						$response['error'] = false;
						$response['message'] = 'Nothing to edit';
						$response['user'] = $user;
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
