<?php
require_once '../proses/connect.php';
$response = array();
if (isset($_GET['apicall'])) {
	switch ($_GET['apicall']) {
        case 'alltour':
            $stmt = $conn->prepare("SELECT `id`,`name`,`price`, id_tour, `desc`, `image` FROM tb_tour_package");
            $stmt->execute();
            $stmt->store_result();
            if ($stmt->num_rows > 0) {
                $stmt->bind_result($id, $name, $price, $id_tour, $desc, $image);
                while ($stmt->fetch()) {
                    $tour_package [] = array(
                        'id' => $id,
                        'name' => $name,
                        'price' => $price,
                        'id_tour' => $id_tour,
                        'desc' => $desc,
                        'image' => $image,
                    );
                    $response['error'] = false;
                    $response['message'] = 'Successfull';
                    $response['tour_package'] = $tour_package;
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