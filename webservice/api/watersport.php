<?php
require_once '../proses/connect.php';
$response = array();
if (isset($_GET['apicall'])) {
	switch ($_GET['apicall']) {
        case 'allatt':
            $stmt = $conn->prepare("SELECT id, `name`, id_watersport, `price`, `desc`, `image` FROM tb_attraction");
            $stmt->execute();
            $stmt->store_result();
            if ($stmt->num_rows > 0) {
                $stmt->bind_result($id, $name, $id_watersport, $price, $desc, $image);
                while ($stmt->fetch()) {
                    $attraction [] = array(
                        'id' => $id,
                        'name' => $name,
                        'id_watersport' => $id_watersport,
                        'price' => $price,
                        'desc' => $desc,
                        'image' => $image,
                    );
                    $response['error'] = false;
                    $response['message'] = 'Successfull';
                    $response['attraction'] = $attraction;
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