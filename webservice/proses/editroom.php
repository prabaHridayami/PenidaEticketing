<?php
include "connect.php";
$id_hotel = $_GET['id'];
$flag = $_GET['flag'];
$modal = mysqli_query($conn, "SELECT * FROM tb_room WHERE id = '$flag'");
$r = mysqli_fetch_array($modal);
$category = mysqli_query($conn, "SELECT * FROM tb_category_room WHERE id_hotel = $id_hotel");
?>
<div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="myModalLabel">Edit Room</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <form action="gethotel.php?id=<?php echo $id_hotel ?>" name="modal_popup" enctype="multipart/form-data" method="POST">
            <div class="modal-body">
                <div class="mb-3">
                    <label for="">Room</label>
                    <input class="form-control" type="hidden" name="id" id="" value="<?php echo $r['id']; ?>" required="true">
                    <!-- <input class="form-control" type="hidden" name="id_hotel" id="" value="<?php echo $id_hotel; ?>" required="true"> -->
                    <div class="row">
                        <div class="col-lg-6">
                            <input class="form-control" type="text" name="room" id="" value="<?php echo $r['name']; ?>" required="true">
                        </div>
                        <div class="col-lg-6">
                            <input class="form-control" type="number" name="guest" id="" value="<?php echo $r['guest']; ?>" required="true">
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="">Room Category</label>
                    <select class="form-control" name="category" id="">
                        <?php
                        if (mysqli_num_rows($category) > 0) {
                            while ($d = mysqli_fetch_array($category)) { ?>
                                <option value="<?php echo $d['id']; ?>"><?php echo $d['name']; ?></option>
                            <?php
                            }
                        } else { ?>
                            <option value="">Data Category Tidak Ada</option>
                        <?php }
                        ?>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" name="update" class="btn btn-primary" value="Save changes">
                    <!-- <button type="button" name="submit" class="btn btn-primary">Save changes</button> -->
                </div>
            </div>
        </form>
    </div>
</div>