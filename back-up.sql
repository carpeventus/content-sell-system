/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 8.0.11 : Database - neteasedemo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`neteasedemo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `neteasedemo`;

/*Table structure for table `cart_detail` */

DROP TABLE IF EXISTS `cart_detail`;

CREATE TABLE `cart_detail` (
  `cart_detail_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content_id` varchar(32) NOT NULL,
  `quantity` int(11) NOT NULL COMMENT '数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`cart_detail_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart_detail` */

insert  into `cart_detail`(`cart_detail_id`,`user_id`,`content_id`,`quantity`,`create_time`,`update_time`) values 
('1552622342342414348','1549943331789719310','1549944682399919852',1,'2019-03-15 11:59:02','2019-03-15 14:37:58');

/*Table structure for table `content_info` */

DROP TABLE IF EXISTS `content_info`;

CREATE TABLE `content_info` (
  `content_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '内容发布者',
  `content_title` varchar(100) NOT NULL COMMENT '内容标题',
  `content_stock` int(11) NOT NULL COMMENT '内容库存',
  `content_summary` varchar(150) NOT NULL COMMENT '内容摘要',
  `content_price` decimal(10,2) NOT NULL COMMENT '内容单价',
  `content_description` varchar(1024) DEFAULT NULL COMMENT '内容详细信息/正文',
  `content_icon` varchar(512) DEFAULT NULL COMMENT '内容图标',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`content_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `content_info` */

insert  into `content_info`(`content_id`,`user_id`,`content_title`,`content_stock`,`content_summary`,`content_price`,`content_description`,`content_icon`,`create_time`,`update_time`) values 
('1549943745633330443','1549943343592886554','网易云音乐黑胶VIP',994,'SVIP',9.00,'网易云音乐黑胶VIP”服务的十几项专属权益，可以为用户提供更优质的音乐体验，包括百万曲库付费歌曲下 载、无损音质畅享、免广告等；更多精选付费内容和推荐，包括编辑精选、个性化推荐内容等；更丰富强大的线上线下权益，包括商城折扣、专属电台，线下演出、电影票务特权及各类生活福利，游戏特权等；以及更个性化的增值服务，如头像挂件、主题皮肤、歌词海报等。','/image/1549943656878314382.jpg','2019-02-12 11:55:45','2019-03-18 17:20:08'),
('1549943901816580892','1549943343592886554','MacBook',49,'苹果电脑',10200.00,'MacBook Pro 让笔记本电脑的性能和便携性提升到一个新的境界。它拥有高性能的处理器和内存、先进的图形处理器、高速存储设备以及众多强大配置，帮助你加速实现心中创想，任它天马行空。','/image/1549943846939690440.jpg','2019-02-12 11:58:21','2019-03-15 14:37:35'),
('1549944166979831496','1549943343592886554','狗子表情包',100,'搞笑',1.00,'一套关于猫咪、狗子的表情包，共100张。','/image/1549944124466100189.jpg','2019-02-12 12:02:46','2019-03-15 12:09:36'),
('1549944353140670397','1549943343592886554','三只松鼠大礼包',100000,'坚果大礼包',188.00,'巴旦木，夏威夷果，板栗，核桃，开心果，腰果，东北红松。','/image/1549944290589534310.jpg','2019-02-12 12:05:53','2019-03-15 12:09:37'),
('1549944521526813962','1549943343592886554','HUAWEI P20 Pro',997,'华为手机',4488.00,'4000万像素（彩色，f/1.8光圈）＋2000万像素（黑白，f/1.6光圈）+800万像素（长焦，f/2.4光圈），徕卡镜头，支持自动对焦（激光对焦、深度对焦、相位对焦、反差对焦），支持华为AI防抖 （备注：不同拍照模式的照片像素可能有差异，请以实际为准。）','/image/1549944463517517778.png','2019-02-12 12:08:41','2019-03-18 17:02:35'),
('1549944682399919852','1549943343592886554','NINTENDO SWITCH',100,'任天堂Switch',2419.00,'NS，全名NINTENDO SWITCH，是任天堂游戏公司于2017年3月首发的旗舰产品，主机采用家用机掌机一体化设计。新机不锁区，支持1920*1080电视输出和1280*720掌上输出。','/image/1549944588152208541.jpg','2019-02-12 12:11:22','2019-03-15 12:09:40'),
('1551747822940741268','1549943343592886554','蛋糕',98,'水果蛋糕',128.00,'富含草莓、蓝莓、黄桃等','https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=d55eea50ad4bd11310c0bf603bc6cf6a/023b5bb5c9ea15ce2b18ee2ab6003af33a87b21a.jpg','2019-03-05 09:03:42','2019-03-15 14:37:35'),
('1552623648237651760','1549943343592886554','索尼PS',0,'绝版',3000.00,'PlayStation 4是索尼电脑娱乐公司推出的家用游戏机。是PlayStation游戏机系列的第四代游戏主机，采用AMD Jaguar 8core处理器。','https://2c.zol-img.com.cn/product/150_320x240/206/cebShCGbF6OBI.jpg','2019-03-15 12:20:48','2019-03-15 12:41:04');

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `order_detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `content_id` varchar(32) NOT NULL,
  `content_title` varchar(100) NOT NULL COMMENT '内容标题',
  `content_price` decimal(10,2) NOT NULL COMMENT '内容单价',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `content_icon` varchar(512) DEFAULT NULL COMMENT '内容图标',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_detail` */

insert  into `order_detail`(`order_detail_id`,`order_id`,`content_id`,`content_title`,`content_price`,`quantity`,`content_icon`,`create_time`,`update_time`) values 
('1552623940147613243','1552623940140326517','1552623648237651760','索尼PS4',3000.00,9,'https://2c.zol-img.com.cn/product/150_320x240/206/cebShCGbF6OBI.jpg','2019-03-15 12:25:40','2019-03-15 12:25:40'),
('1552631855426793882','1552631855419857701','1551747822940741268','蛋糕',128.00,2,'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=d55eea50ad4bd11310c0bf603bc6cf6a/023b5bb5c9ea15ce2b18ee2ab6003af33a87b21a.jpg','2019-03-15 14:37:35','2019-03-15 14:37:35'),
('1552631855433479725','1552631855419857701','1549943901816580892','MacBook',10200.00,1,'/image/1549943846939690440.jpg','2019-03-15 14:37:35','2019-03-15 14:37:35'),
('1552899755912534985','1552899755907393024','1549944521526813962','HUAWEI P20 Pro',4488.00,2,'/image/1549944463517517778.png','2019-03-18 17:02:35','2019-03-18 17:02:35'),
('1552899783179160251','1552899783176957767','1549943745633330443','网易云音乐黑胶VIP',9.00,4,'/image/1549943656878314382.jpg','2019-03-18 17:03:03','2019-03-18 17:03:03'),
('1552899834921359922','1552899834919628540','1549943745633330443','网易云音乐黑胶VIP',9.00,1,'/image/1549943656878314382.jpg','2019-03-18 17:03:54','2019-03-18 17:03:54'),
('1552900808717594631','1552900808711273860','1549943745633330443','网易云音乐黑胶VIP',9.00,1,'/image/1549943656878314382.jpg','2019-03-18 17:20:08','2019-03-18 17:20:08');

/*Table structure for table `order_master` */

DROP TABLE IF EXISTS `order_master`;

CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `order_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_master` */

insert  into `order_master`(`order_id`,`user_id`,`username`,`order_amount`,`order_status`,`pay_status`,`create_time`,`update_time`) values 
('1552623940140326517','1549943331789719310','buyer',27000.00,1,1,'2019-03-15 12:25:40','2019-03-15 12:25:40'),
('1552631855419857701','1549943331789719310','buyer',10456.00,1,1,'2019-03-15 14:37:35','2019-03-15 14:37:35'),
('1552899755907393024','1549943331789719310','buyer',8976.00,1,1,'2019-03-18 17:02:35','2019-03-18 17:02:35'),
('1552899783176957767','1549943331789719310','buyer',36.00,1,1,'2019-03-18 17:03:03','2019-03-18 17:03:03'),
('1552899834919628540','1549943331789719310','buyer',9.00,1,1,'2019-03-18 17:03:54','2019-03-18 17:03:54'),
('1552900808711273860','1549943331789719310','buyer',9.00,1,1,'2019-03-18 17:20:08','2019-03-18 17:20:08');

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_seller` tinyint(4) DEFAULT NULL COMMENT '是否为卖家,0表示非卖家（买家）1表示卖家',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='卖家/买家信息表';

/*Data for the table `user_info` */

insert  into `user_info`(`user_id`,`username`,`password`,`is_seller`,`create_time`,`update_time`) values 
('1549943331789719310','buyer','89220b4835cd6e61163e3616c1c279f3',0,'2019-02-12 11:48:51','2019-02-12 11:48:51'),
('1549943343592886554','seller','4da313af117056335cda6eb794470085',1,'2019-02-12 11:49:03','2019-02-12 11:49:03');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
