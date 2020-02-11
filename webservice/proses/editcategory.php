<?php
include "connect.php";
$id_user = $_GET['id_user'];
$flag = $_GET['flag'];
$modal = mysqli_query($conn, "SELECT * FROM tb_category_room WHERE id = '$flag'");
$r = mysqli_fetch_array($modal);
$id_hotel = $r['id_hotel'];
$hotel_cat = mysqli_query($conn,"SELECT * FROM tb_hotel WHERE id = '$id_hotel' AND id_user = '$id_user'");
$row = mysqli_fetch_assoc($hotel_cat);
$name = $row['name'];
$hotel = mysqli_query($conn, "SELECT * FROM tb_hotel WHERE id_user = '$id_user' ORDER BY id DESC");
?>
<div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="myModalLabel">Edit Boat</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <form action="category.php" name="modal_popup" enctype="multipart/form-data" method="POST">
            <div class="modal-body">
                <div class="mb-3">
                    <input class="form-control" type="hidden" name="id" id="" value="<?php echo $r['id']; ?>" required="true">
                    <div class="row">
                        <div class="col-6">
                            <label for="">Name</label>
                            <input class="form-control" type="text" name="name" id="" value="<?php echo $r['name']; ?>" required="true">
                        </div>
                        <div class="col-6">
                            <label for="">Harga</label>
                            <input class="form-control" type="text" name="price" id="" value="<?php echo $r['price']; ?>" required="true">
                        </div>
                    </div>
                    <div class="">
                        <label for="">Hotel</label>
                        <select class="form-control" name="hotel" id="">
                            <option value="<?php echo $r['id_hotel']; ?>"><?php echo $name; ?></option>
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
                    <div class="mb-3">
                        <label for="">Deskripsi</label>
                        <textarea class="form-control" name="deskripsi" id="" cols="10" rows="5"> <?php echo $r['desc']; ?> </textarea>
                    </div>
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