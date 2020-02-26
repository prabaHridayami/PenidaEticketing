    <?php
    include_once '../proses/connect.php';
    $id = $_SESSION['id'];
    $select = mysqli_query($conn, "SELECT * FROM tb_user WHERE id = $id");
    $row = mysqli_fetch_assoc($select);
    ?>

    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="index.php" class="nav-link">Home</a>
            </li>
            <!-- <li class="nav-item d-none d-sm-inline-block">
          <a href="#" class="nav-link">Contact</a>
        </li> -->
        </ul>

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Notifications Dropdown Menu -->
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-bell"></i>
                    <span class="badge badge-warning navbar-badge">15</span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="dropdown-item dropdown-header">15 Notifications</span>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-envelope mr-2"></i> 4 new messages
                        <span class="float-right text-muted text-sm">3 mins</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-users mr-2"></i> 8 friend requests
                        <span class="float-right text-muted text-sm">12 hours</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-file mr-2"></i> 3 new reports
                        <span class="float-right text-muted text-sm">2 days</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link link-logout" href="#">
                    <i class="fas fa-sign-out-alt"></i>
                </a>
            </li>
        </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="/partner" class="brand-link">
            <img src="../dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
            <span class="brand-text font-weight-light">E-Booking</span>
        </a>
        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="../dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                </div>
                <div class="info">
                    <a href="../partner/index.php?id_user=<?php echo $row['id']; ?>" class="d-block"><?php echo $row['name']; ?></a>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-hotel"></i>
                            <p>
                                Dashboard Hotel
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="hotel/hotel.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Hotel</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="hotel/pelanggan.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Pelanggan</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="hotel/transaksi.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Transaksi</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-ship"></i>
                            <p>
                                Dashboard Boat
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="boat/boat.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Boat</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="boat/pelanggan.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Pelanggan</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="boat/transaksi.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Transaksi</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-swimmer"></i>
                            <p>
                                Dashboard Watersport
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="watersport/watersport.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Watersport</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="watersport/pelanggan.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Pelanggan</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="watersport/transaksi.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Transaksi</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-globe-americas"></i>
                            <p>
                                Dashboard Tour
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="tour/tour.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Tour</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="tour/pelanggan.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Pelanggan</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="tour/transaksi.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Transaksi</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fas fa-car"></i>
                            <p>
                                Dashboard Vehicle
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="vehicle/vehicle.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Vehicle</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="vehicle/pelanggan.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Pelanggan</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="vehicle/transaksi.php" class="nav-link">
                                    <i class="far fa-circle nav-icon"></i>
                                    <p>Transaksi</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>