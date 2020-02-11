<?php
include "connect.php";
$flag = $_GET['flag'];
?>
<div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="myModalLabel">Show Place Temple</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <form action="schedule.php" name="modal_popup" enctype="multipart/form-data" method="POST">
            <div class="modal-body">
                <?php
                $result = mysqli_query($conn, "SELECT tb_temple.`name` FROM tb_det_package JOIN tb_temple ON tb_det_package.`id_temple` = tb_temple.`id` WHERE tb_det_package.`id_tour_package` = $flag");
                ?>
                <div class="dataTables_wrapper">
                    <table id="example" class="text-center table table-striped display nowrap" style="width:100%">
                        <thead>
                            <tr class="bg-dark text-white">
                                <th>No</th>
                                <th>Temple Tour</th>                                                                
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            $i = 1;
                            while ($row = mysqli_fetch_assoc($result)) {
                            ?>
                                <tr>
                                    <td><?php echo $i++; ?></td>
                                    <td><?php echo $row['name']; ?></td>                                
                                </tr>
                            <?php } ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>