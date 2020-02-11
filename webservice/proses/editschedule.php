<?php
include "connect.php";
$id_user = $_GET['id_user'];
$flag = $_GET['flag'];
$modal = mysqli_query($conn, "SELECT * FROM tb_schedule WHERE id = '$flag'");
$r = mysqli_fetch_array($modal);
$id_boat = $r['id_det_boat'];
$hotel_cat = mysqli_query($conn, "SELECT tb_schedule.id, tb_boat.`name`, tb_schedule.pickup_loc, tb_schedule.`dropup_loc`,tb_schedule.`time`, tb_schedule.`id_det_boat` FROM tb_schedule INNER JOIN tb_det_boat ON tb_det_boat.`id` = tb_schedule.`id_det_boat` INNER JOIN tb_boat ON tb_boat.`id` = tb_det_boat.`id_boat`WHERE tb_schedule.`id_det_boat` = '$id_boat'");
$row = mysqli_fetch_assoc($hotel_cat);
$name = $row['name'];
$hotel = mysqli_query($conn, "SELECT tb_det_boat.`id`, tb_boat.`name` FROM tb_det_boat INNER JOIN tb_boat ON tb_boat.`id` = tb_det_boat.`id_boat`WHERE tb_boat.`id_user` = '$id_user'");
?>
<div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="myModalLabel">Edit Schedule</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <form action="schedule.php" name="modal_popup" enctype="multipart/form-data" method="POST">
            <div class="modal-body">
                <div class="mb-3">
                    <input class="form-control" type="hidden" name="id" id="" value="<?php echo $r['id']; ?>" required="true">
                    <div class="row">
                        <div class="col-6">
                            <label for="">Keberangkatan</label>
                            <input class="form-control" type="text" name="keberangkatan" id="" value="<?php echo $r['pickup_loc']; ?>" required="true">
                        </div>
                        <div class="col-6">
                            <label for="">Kedatangan</label>
                            <input class="form-control" type="text" name="kedatangan" id="" value="<?php echo $r['dropup_loc']; ?>" required="true">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <label for="">Boat</label>
                            <select class="form-control" name="boat" id="">
                                <option value="<?php echo $r['id_det_boat']; ?>"><?php echo $name; ?></option>
                                <?php
                                if (mysqli_num_rows($hotel) > 0) {
                                    while ($a = mysqli_fetch_assoc($hotel)) { ?>
                                        <option value="<?php echo $a['id']; ?>"><?php echo $a['name']; ?></option>
                                <?php
                                    }
                                }
                                ?>
                            </select>
                        </div>
                        <div class="col-lg-6">
                            <label for="">Time</label>
                            <input class="form-control" type="text" name="time" id="" value="<?php echo $r['time']; ?>" required="true">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" name="update" class="btn btn-primary" value="Save changes">                        
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>