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
                    $boat [] = array(
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