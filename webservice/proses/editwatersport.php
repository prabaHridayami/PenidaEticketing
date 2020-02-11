<?php
include "connect.php";
$id_boat = $_GET['id'];
$flag = $_GET['flag'];
$modal = mysqli_query($conn, "SELECT * FROM tb_attraction WHERE id = '$flag'");
$r = mysqli_fetch_array($modal);
?>
<div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="myModalLabel">Edit Watersport Attraction</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <form action="getwatersport.php?id=<?php echo $id_boat ?>" name="modal_popup" enctype="multipart/form-data" method="POST">
            <div class="modal-body">
                <div class="mb-3">
                    <input class="form-control" type="hidden" name="id" id="" value="<?php echo $r['id']; ?>" required="true">
                    <div class="row">
                        <div class="col-6">
                            <label for="">Kuota</label>
                            <input class="form-control" type="text" name="kuota" id="" value="<?php echo $r['name']; ?>" required="true">
                        </div>
                        <div class="col-6">
                            <label for="">Harga</label>
                            <input class="form-control" type="text" name="harga" id="" value="<?php echo $r['price']; ?>" required="true">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="">Deskripsi</label>
                        <textarea class="form-control" name="deskripsi" id="" cols="10" rows="5"> <?php echo $r['desc']; ?> </textarea>
                    </div>
                    <div class="col-lg">
                      <input type="file" class="custom-file-input" name="files" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" multiple>
                      <label class="custom-file-label" for="inputGroupFile01">Choose Image</label>
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