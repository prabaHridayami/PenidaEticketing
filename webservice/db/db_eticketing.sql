/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.4.6-MariaDB : Database - ulakanvi_eticketing
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ulakanvi_eticketing` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ulakanvi_eticketing`;

/*Table structure for table `tb_attraction` */

DROP TABLE IF EXISTS `tb_attraction`;

CREATE TABLE `tb_attraction` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id_watersport` bigint(20) unsigned NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_watersport` (`id_watersport`),
  CONSTRAINT `tb_attraction_ibfk_1` FOREIGN KEY (`id_watersport`) REFERENCES `tb_watersport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `tb_attraction` */

insert  into `tb_attraction`(`id`,`name`,`price`,`id_watersport`,`desc`,`image`) values (1,'Eka',100000,1,'Krisna Putra ','images.png'),(8,'Banana Boat',200000,2,'Halo','Pas Photo_190426_0001.jpg');

/*Table structure for table `tb_boat` */

DROP TABLE IF EXISTS `tb_boat`;

CREATE TABLE `tb_boat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `image` varchar(200) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` enum('1','2') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_boat_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `tb_boat` */

insert  into `tb_boat`(`id`,`name`,`id_user`,`image`,`desc`,`phone`,`status`) values (1,'Cantik',10,'Halooo','Apa Kabar','01209381031','2'),(2,'Genteng',10,'Halooo','Baik Saja','89089079090','2'),(3,'Jelek',11,'Halooo','Bagaimana Denganmu ?','08984337749','2'),(5,'Eka',10,'maxresdefault.jpg','Jalan Nangka Selatan','08984337749','1');

/*Table structure for table `tb_category_room` */

DROP TABLE IF EXISTS `tb_category_room`;

CREATE TABLE `tb_category_room` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id_hotel` bigint(20) unsigned NOT NULL,
  `desc` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_hotel` (`id_hotel`),
  CONSTRAINT `tb_category_room_ibfk_1` FOREIGN KEY (`id_hotel`) REFERENCES `tb_hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `tb_category_room` */

insert  into `tb_category_room`(`id`,`name`,`price`,`id_hotel`,`desc`) values (2,'VVIP',200000,3,'   Bacot Banget   '),(4,'Primer',2000000,2,'Halo'),(5,'Primer Sakura',400000,1,'Halo');

/*Table structure for table `tb_det_boat` */

DROP TABLE IF EXISTS `tb_det_boat`;

CREATE TABLE `tb_det_boat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_boat` bigint(20) unsigned NOT NULL,
  `quota` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_boat` (`id_boat`),
  CONSTRAINT `tb_det_boat_ibfk_1` FOREIGN KEY (`id_boat`) REFERENCES `tb_boat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `tb_det_boat` */

insert  into `tb_det_boat`(`id`,`id_boat`,`quota`,`price`,`image`,`desc`) values (5,1,20,500000,'lambang1.png',' Halo\r\n '),(6,2,30,500000,'Unud.png','Ganteng'),(7,3,20,100000,'download.png','Haloo');

/*Table structure for table `tb_det_package` */

DROP TABLE IF EXISTS `tb_det_package`;

CREATE TABLE `tb_det_package` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_temple` bigint(20) unsigned NOT NULL,
  `id_tour_package` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_temple` (`id_temple`),
  KEY `id_tour_package` (`id_tour_package`),
  CONSTRAINT `tb_det_package_ibfk_1` FOREIGN KEY (`id_temple`) REFERENCES `tb_temple` (`id`),
  CONSTRAINT `tb_det_package_ibfk_2` FOREIGN KEY (`id_tour_package`) REFERENCES `tb_tour_package` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `tb_det_package` */

insert  into `tb_det_package`(`id`,`id_temple`,`id_tour_package`) values (17,12,4);

/*Table structure for table `tb_det_trans_watersport` */

DROP TABLE IF EXISTS `tb_det_trans_watersport`;

CREATE TABLE `tb_det_trans_watersport` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_attraction` bigint(20) unsigned NOT NULL,
  `price` double DEFAULT NULL,
  `reserve_date` date DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `id_trans_watersport` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_trans_watersport` (`id_trans_watersport`),
  KEY `id_attraction` (`id_attraction`),
  CONSTRAINT `tb_det_trans_watersport_ibfk_1` FOREIGN KEY (`id_trans_watersport`) REFERENCES `tb_trans_watersport` (`id`),
  CONSTRAINT `tb_det_trans_watersport_ibfk_2` FOREIGN KEY (`id_attraction`) REFERENCES `tb_attraction` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tb_det_trans_watersport` */

insert  into `tb_det_trans_watersport`(`id`,`id_attraction`,`price`,`reserve_date`,`qty`,`id_trans_watersport`) values (1,8,20000,'2020-01-03',4,1),(2,8,20000,'2020-01-03',4,1);

/*Table structure for table `tb_hotel` */

DROP TABLE IF EXISTS `tb_hotel`;

CREATE TABLE `tb_hotel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) unsigned NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `check_out` time DEFAULT NULL,
  `check_in` time DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `status` enum('1','2') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_hotel_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `tb_hotel` */

insert  into `tb_hotel`(`id`,`id_user`,`name`,`address`,`phone`,`check_out`,`check_in`,`image`,`status`) values (1,11,'Hotel Kaswari','Jalan Arjuna','089812938193','13:00:00','16:00:00','aksnd','2'),(2,10,'Hotal Kapal','Jalan Surga','098193891','13:00:00','17:00:00','PNG','2'),(3,10,'Primer','Jalan Antasura','+628984337749','13:00:00','20:00:00','maxresdefault.jpg','1');

/*Table structure for table `tb_rent_vehicle` */

DROP TABLE IF EXISTS `tb_rent_vehicle`;

CREATE TABLE `tb_rent_vehicle` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) unsigned NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` enum('1','2') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_rent_vehicle_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_rent_vehicle` */

insert  into `tb_rent_vehicle`(`id`,`id_user`,`name`,`address`,`desc`,`image`,`phone`,`status`) values (1,10,'Eka Rent Car','Jalan Nangka','halo','Qwerty','089854127318','2');

/*Table structure for table `tb_room` */

DROP TABLE IF EXISTS `tb_room`;

CREATE TABLE `tb_room` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `id_category` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `tb_room_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `tb_category_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `tb_room` */

insert  into `tb_room`(`id`,`name`,`id_category`) values (14,'Primer 1',4),(18,'Ninja',2),(19,'Ninja Warior',2);

/*Table structure for table `tb_schedule` */

DROP TABLE IF EXISTS `tb_schedule`;

CREATE TABLE `tb_schedule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pickup_loc` varchar(200) DEFAULT NULL,
  `dropup_loc` varchar(200) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `id_det_boat` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_det_boat` (`id_det_boat`),
  CONSTRAINT `tb_schedule_ibfk_1` FOREIGN KEY (`id_det_boat`) REFERENCES `tb_det_boat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `tb_schedule` */

insert  into `tb_schedule`(`id`,`pickup_loc`,`dropup_loc`,`time`,`id_det_boat`) values (2,'Klungkung','Nusa Penida','00:08:00',5),(3,'Sanur','Nusa Penida','00:08:00',7),(6,'Lombok','Klungkung','08:00:00',6);

/*Table structure for table `tb_temple` */

DROP TABLE IF EXISTS `tb_temple`;

CREATE TABLE `tb_temple` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `tb_temple` */

insert  into `tb_temple`(`id`,`name`,`location`,`desc`,`image`) values (10,'Uluwatu Bersatu','Uluwatu','Uluwatu ','lambang1.png'),(12,'Besakih','Besakih','Besakih','images.png');

/*Table structure for table `tb_tour` */

DROP TABLE IF EXISTS `tb_tour`;

CREATE TABLE `tb_tour` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` enum('1','2') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_tour_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tb_tour` */

insert  into `tb_tour`(`id`,`name`,`id_user`,`address`,`phone`,`status`) values (1,'Eka Tour',10,'Halo','0898433774','2'),(2,'namabelakang',10,'Kaswari Bersatu','+628984337749','1');

/*Table structure for table `tb_tour_package` */

DROP TABLE IF EXISTS `tb_tour_package`;

CREATE TABLE `tb_tour_package` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id_tour` bigint(20) unsigned NOT NULL,
  `max_qty` tinyint(2) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tour` (`id_tour`),
  CONSTRAINT `tb_tour_package_ibfk_1` FOREIGN KEY (`id_tour`) REFERENCES `tb_tour` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tb_tour_package` */

insert  into `tb_tour_package`(`id`,`name`,`price`,`id_tour`,`max_qty`,`desc`,`image`) values (4,'Primer Naruto',1200000,1,25,'  Primer Sakura  ','Unud.png');

/*Table structure for table `tb_trans_boat` */

DROP TABLE IF EXISTS `tb_trans_boat`;

CREATE TABLE `tb_trans_boat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `depart_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `reserve_date` datetime DEFAULT NULL,
  `schedule` bigint(20) unsigned NOT NULL,
  `qty` int(11) unsigned DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `status` enum('prossed','success') DEFAULT 'prossed',
  PRIMARY KEY (`id`),
  KEY `schedule` (`schedule`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_trans_boat_ibfk_1` FOREIGN KEY (`schedule`) REFERENCES `tb_schedule` (`id`),
  CONSTRAINT `tb_trans_boat_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_boat` */

insert  into `tb_trans_boat`(`id`,`depart_date`,`return_date`,`reserve_date`,`schedule`,`qty`,`total_price`,`id_user`,`status`) values (3,'2020-01-03','2020-01-03','2020-01-03 19:29:14',3,5,250000,2,'prossed'),(6,'2020-01-03','2020-01-03','2020-01-03 19:29:14',3,5,250000,2,'prossed');

/*Table structure for table `tb_trans_hotel` */

DROP TABLE IF EXISTS `tb_trans_hotel`;

CREATE TABLE `tb_trans_hotel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_room` bigint(20) unsigned NOT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `reserve_date` datetime DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `status` enum('proceed','success') DEFAULT 'proceed',
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_room` (`id_room`),
  CONSTRAINT `tb_trans_hotel_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_trans_hotel_ibfk_2` FOREIGN KEY (`id_room`) REFERENCES `tb_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_hotel` */

insert  into `tb_trans_hotel`(`id`,`id_room`,`check_in`,`check_out`,`reserve_date`,`total_price`,`id_user`,`status`) values (2,14,'2020-01-03','2020-01-03','2020-01-03 14:55:29',2000000,1,'proceed'),(10,14,'2020-01-03','2020-01-03','2020-01-03 14:55:29',200000,1,NULL),(11,14,'2020-01-03','2020-01-03','2020-01-03 14:55:29',200000,2,NULL),(12,14,'2020-01-03','2020-01-03','2020-01-03 14:55:29',200000,2,NULL),(13,14,'2020-01-03','2020-01-03','2020-01-03 14:55:29',200000,2,NULL),(14,14,'2020-01-03','2020-01-03','2020-01-03 14:55:29',200000,1,NULL),(15,14,'2020-01-03','2020-01-03','2020-01-03 14:55:29',200000,1,NULL),(16,14,'2020-01-03','2020-01-03','2020-01-03 14:55:29',200000,1,NULL);

/*Table structure for table `tb_trans_rent` */

DROP TABLE IF EXISTS `tb_trans_rent`;

CREATE TABLE `tb_trans_rent` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) unsigned NOT NULL,
  `take` datetime DEFAULT NULL,
  `return` datetime DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `id_vehicle` bigint(20) unsigned NOT NULL,
  `trans_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_vehicle` (`id_vehicle`),
  CONSTRAINT `tb_trans_rent_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_trans_rent_ibfk_2` FOREIGN KEY (`id_vehicle`) REFERENCES `tb_vehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_rent` */

insert  into `tb_trans_rent`(`id`,`id_user`,`take`,`return`,`total_price`,`id_vehicle`,`trans_date`) values (1,6,'2020-01-03 22:14:49','2020-01-04 22:14:51',800000,5,'2020-01-03 22:15:10');

/*Table structure for table `tb_trans_tour` */

DROP TABLE IF EXISTS `tb_trans_tour`;

CREATE TABLE `tb_trans_tour` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tour_date` date DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `id_tour_package` bigint(20) unsigned NOT NULL,
  `qty` tinyint(2) DEFAULT NULL,
  `reserve_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_tour_package` (`id_tour_package`),
  CONSTRAINT `tb_trans_tour_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_trans_tour_ibfk_2` FOREIGN KEY (`id_tour_package`) REFERENCES `tb_tour_package` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_tour` */

insert  into `tb_trans_tour`(`id`,`tour_date`,`total_price`,`id_user`,`id_tour_package`,`qty`,`reserve_date`) values (1,'2020-01-03',5000000,5,4,25,'2020-01-03 21:35:37');

/*Table structure for table `tb_trans_watersport` */

DROP TABLE IF EXISTS `tb_trans_watersport`;

CREATE TABLE `tb_trans_watersport` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_trans_watersport_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_watersport` */

insert  into `tb_trans_watersport`(`id`,`date`,`total_price`,`id_user`) values (1,'2020-01-03',50000,7);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `role` enum('member','partner','admin') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`name`,`username`,`email`,`password`,`phone`,`created_at`,`updated_at`,`role`) values (1,'ekakrisna','ekakrisna','kwayan624@gmail.com','e3ba20c6ca250caaedeb5385cf64a248','08984337749',NULL,NULL,'member'),(2,'praba jelek','praba jelek','prabajelek@gmail.com','bc01207694afd774a9968c13319fa132','08984337749',NULL,NULL,'member'),(3,'praba cantik','praba cantik','prabacantik@gmail.com','bc01207694afd774a9968c13319fa132','08984337749',NULL,NULL,'member'),(4,'cantik','cantik','cantik@gmail.com','bc01207694afd774a9968c13319fa132','08984337749',NULL,NULL,'member'),(5,'mcddelivery','mcddelivery','mcddelivery@gmail.com','65f0e88cf0fd5a302d38a1a0ccf8f53d','08984337749','2019-12-08 16:28:13','2019-12-08 16:28:13','member'),(6,'manasayatahu123','manasayatahu123','manasayatahu123@gmail.com','e3ba20c6ca250caaedeb5385cf64a248','08984337749','2019-12-08 16:53:54','2019-12-08 16:53:54','member'),(7,'jubahmerah','jubahmerah','jubahmerah@gmail.com','11a9ea5ec53ab0ebef31cc00f3b077f2','08984337749','2019-12-08 16:59:05','2019-12-08 16:59:05','member'),(8,'jubahmerah','jubahmerah','jubahmerah@gmail.com','11a9ea5ec53ab0ebef31cc00f3b077f2','08984337749','2019-12-08 16:59:06','2019-12-08 16:59:06','member'),(9,'jubahhitam','jubahhitam','jubahhitam@gmail.com','0a486e7eec65c426d0d8b98349961336','08984337749','2019-12-08 17:01:30','2019-12-08 17:01:30','admin'),(10,'tzuyu','tzuyu','tzuyu@gmail.com','4718e63ec3601a9ebf83da264de25cfc','08984337749','2019-12-08 17:25:34','2019-12-08 17:25:34','partner'),(11,'pigura','pigura','pigura@gmail.com','6f5fb2193f253b944d4fa29ded35a156','08984337749','2019-12-18 15:12:51','2019-12-18 15:12:51','partner');

/*Table structure for table `tb_vehicle` */

DROP TABLE IF EXISTS `tb_vehicle`;

CREATE TABLE `tb_vehicle` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `plat` varchar(20) DEFAULT NULL,
  `category` enum('motor','mobil') DEFAULT NULL,
  `price` double DEFAULT NULL,
  `seat` int(4) DEFAULT NULL,
  `id_rent_vehicle` bigint(20) unsigned NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_rent_vehicle` (`id_rent_vehicle`),
  CONSTRAINT `tb_vehicle_ibfk_1` FOREIGN KEY (`id_rent_vehicle`) REFERENCES `tb_rent_vehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `tb_vehicle` */

insert  into `tb_vehicle`(`id`,`name`,`plat`,`category`,`price`,`seat`,`id_rent_vehicle`,`desc`) values (5,'BRIO','DK802LL','mobil',800000,5,1,'Halo');

/*Table structure for table `tb_watersport` */

DROP TABLE IF EXISTS `tb_watersport`;

CREATE TABLE `tb_watersport` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `status` enum('1','2') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_watersport_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `tb_watersport` */

insert  into `tb_watersport`(`id`,`name`,`id_user`,`address`,`phone`,`image`,`status`) values (1,'Eka Watersport',10,'Jalan Nangka Selatan','08984337749','HALO','2'),(2,'Praba Watersport',11,'Jalan Belimbing','0898123123','Hai','2'),(3,'Primer',10,'Jalan Nangka','+628984337749','maxresdefault.jpg','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
