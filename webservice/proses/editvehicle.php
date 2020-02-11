<?php
include "connect.php";
$id_boat = $_GET['id'];
$flag = $_GET['flag'];
$modal = mysqli_query($conn, "SELECT * FROM tb_vehicle WHERE id = '$flag'");
$r = mysqli_fetch_array($modal);
?>
<div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="myModalLabel">Edit Vehicle</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <form action="getvehicle.php?id=<?php echo $id_boat ?>" name="modal_popup" enctype="multipart/form-data" method="POST">
            <div class="modal-body">
                <div class="mb-3">
                    <input class="form-control" type="hidden" name="id" id="" value="<?php echo $r['id']; ?>" required="true">
                    <div class="row">
                        <div class="col-6">
                            <label for="">Name Vehicle</label>
                            <input class="form-control" type="text" name="name" id="" value="<?php echo $r['name']; ?>" placeholder="Name Vehicle">
                        </div>
                        <div class="col-6">
                            <label for="">Plat</label>
                            <input class="form-control" type="text" name="plat" id="" value="<?php echo $r['plat']; ?>" placeholder="Plat">
                        </div>
                    </div>
                    <div class="">
                      <label for="">Harga</label>
                      <input class="form-control" type="text" name="harga" id="" value="<?php echo $r['price']; ?>" placeholder="Harga">
                    </div>
                    <div class="row">
                      <div class="col-6">
                        <label for="">Category Kendaraan</label>
                        <select class="form-control" name="category" id="">
                          <option value="<?php echo $r['category']; ?>"><?php echo $r['category']; ?></option>
                          <option value="Motor">Motor</option>
                          <option value="Mobil">Mobil</option>
                        </select>
                      </div>
                      <div class="col-6">
                        <label for="">Seat</label>
                        <input class="form-control" type="number" name="seat" id="" value="<?php echo $r['seat']; ?>" placeholder="Jumlah Seat">
                      </div>
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