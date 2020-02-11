<!-- <?php
        include "connect.php";
        $flag = $_GET['flag'];
        ?>
<div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="myModalLabel">Show Attraction</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <form action="schedule.php" name="modal_popup" enctype="multipart/form-data" method="POST">
            <div class="modal-body">
                <?php
                $result = mysqli_query($conn, "SELECT tb_attraction.`name`, tb_det_trans_watersport.`price`,tb_det_trans_watersport.`qty`,tb_det_trans_watersport.`reserve_date`,tb_det_trans_watersport.`id` FROM tb_det_trans_watersport JOIN tb_attraction ON tb_det_trans_watersport.`id_attraction` = tb_attraction.`id` WHERE tb_det_trans_watersport.`id_trans_watersport` = $flag");
                ?>
                <div class="dataTables_wrapper">
                    <table id="example" class="text-center table table-striped display nowrap" style="width:100%">
                        <thead>
                            <tr class="bg-dark text-white">
                                <th>No</th>
                                <th>Nama Pelanggan</th>                                
                                <th>Harga</th>
                                <th>Qty</th>
                                <th>Date</th>                                
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
                                    <td><?php echo $row['price']; ?></td>
                                    <td><?php echo $row['qty']; ?></td>
                                    <td><?php echo $row['reserve_date']; ?></td>                                    
                                </tr>
                            <?php } ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div> -->