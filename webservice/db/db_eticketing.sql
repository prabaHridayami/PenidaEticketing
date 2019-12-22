/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.4.6-MariaDB : Database - db_eticketing
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_eticketing` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_eticketing`;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tb_attraction` */

insert  into `tb_attraction`(`id`,`name`,`price`,`id_watersport`,`desc`,`image`) values (1,'Eka',0,1,'Krisna Putra','PNG'),(2,'Eka Krisna',0,1,'Eka Krisna','3gp');

/*Table structure for table `tb_boat` */

DROP TABLE IF EXISTS `tb_boat`;

CREATE TABLE `tb_boat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `image` varchar(200) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_boat_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_boat` */

insert  into `tb_boat`(`id`,`name`,`id_user`,`image`,`desc`,`phone`) values (1,'Cantik',1,'Halooo','Apa Kabar','01209381031');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tb_category_room` */

insert  into `tb_category_room`(`id`,`name`,`price`,`id_hotel`,`desc`) values (1,'Deluxe',100000,1,'EKA'),(2,'VVIP',200000,1,'Bacot'),(4,'Primer',2000000,2,'Halo');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_det_boat` */

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_det_package` */

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_det_trans_watersport` */

/*Table structure for table `tb_hotel` */

DROP TABLE IF EXISTS `tb_hotel`;

CREATE TABLE `tb_hotel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) unsigned NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_hotel_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tb_hotel` */

insert  into `tb_hotel`(`id`,`id_user`,`name`,`address`,`phone`,`image`) values (1,10,'Hotel Kaswari','Jalan Arjuna','089812938193','aksnd'),(2,10,'Hotal Kapal','Jalan Surga','098193891','PNG');

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
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_rent_vehicle_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_rent_vehicle` */

insert  into `tb_rent_vehicle`(`id`,`id_user`,`name`,`address`,`desc`,`image`,`phone`) values (1,1,'Eka Rent Car','Jalan Nangka','halo','Qwerty','089854127318');

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

insert  into `tb_room`(`id`,`name`,`id_category`) values (14,'Primer 1',4),(18,'Ninja',2),(19,'Ninja Warior',2),(20,'Kaswari',1);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_schedule` */

/*Table structure for table `tb_temple` */

DROP TABLE IF EXISTS `tb_temple`;

CREATE TABLE `tb_temple` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_temple` */

/*Table structure for table `tb_tour` */

DROP TABLE IF EXISTS `tb_tour`;

CREATE TABLE `tb_tour` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_tour_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_tour` */

insert  into `tb_tour`(`id`,`name`,`id_user`,`address`,`phone`) values (1,'Eka Tour',1,'Halo','0898433774');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_tour_package` */

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
  PRIMARY KEY (`id`),
  KEY `schedule` (`schedule`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_trans_boat_ibfk_1` FOREIGN KEY (`schedule`) REFERENCES `tb_schedule` (`id`),
  CONSTRAINT `tb_trans_boat_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_boat` */

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
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_room` (`id_room`),
  CONSTRAINT `tb_trans_hotel_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_trans_hotel_ibfk_2` FOREIGN KEY (`id_room`) REFERENCES `tb_room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_hotel` */

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_rent` */

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_tour` */

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_trans_watersport` */

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
  `seat` enum('2','5','8') DEFAULT NULL,
  `id_rent_vehicle` bigint(20) unsigned NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_rent_vehicle` (`id_rent_vehicle`),
  CONSTRAINT `tb_vehicle_ibfk_1` FOREIGN KEY (`id_rent_vehicle`) REFERENCES `tb_rent_vehicle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tb_vehicle` */

/*Table structure for table `tb_watersport` */

DROP TABLE IF EXISTS `tb_watersport`;

CREATE TABLE `tb_watersport` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tb_watersport_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `tb_watersport` */

insert  into `tb_watersport`(`id`,`name`,`id_user`,`address`,`phone`,`image`) values (1,'Eka Watersport',1,'Jalan Nangka Selatan','08984337749','HALO');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
