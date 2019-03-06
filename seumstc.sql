-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: seumstc
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articlebody`
--

DROP TABLE IF EXISTS `articlebody`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articlebody` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `contenthtml` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlebody`
--

LOCK TABLES `articlebody` WRITE;
/*!40000 ALTER TABLE `articlebody` DISABLE KEYS */;
INSERT INTO `articlebody` VALUES (18,19,'![boglogo.png](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlx7P9iAFIzMAAEynFqffTs295.png)\n\n学习使我快乐呀！','<p><img src=\"http://47.100.58.57:9999/group1/M00/00/00/rBNWFlx7P9iAFIzMAAEynFqffTs295.png\" alt=\"boglogo.png\" /></p>\n<p>学习使我快乐呀！</p>\n'),(28,29,'真的','<p>真的</p>\n'),(29,30,'超级难用','<p>超级难用</p>\n'),(30,31,'系呀系呀','<p>系呀系呀</p>\n'),(31,32,'什么水平','<p>什么水平</p>\n'),(32,33,'![logo.png](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlx8mI-ANxKFAAAJZNTcgWE420.png)','<p><img src=\"http://47.100.58.57:9999/group1/M00/00/00/rBNWFlx8mI-ANxKFAAAJZNTcgWE420.png\" alt=\"logo.png\" /></p>\n'),(33,34,'22222222222222','<p>22222222222222</p>\n'),(34,35,'再次测试![btnright.png](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlx-UVKAMCB_AAAFq8bP78s977.png)','<p>再次测试<img src=\"http://47.100.58.57:9999/group1/M00/00/00/rBNWFlx-UVKAMCB_AAAFq8bP78s977.png\" alt=\"btnright.png\" /></p>\n');
/*!40000 ALTER TABLE `articlebody` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articlecomment`
--

DROP TABLE IF EXISTS `articlecomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articlecomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `createdate` datetime DEFAULT NULL,
  `articleid` int(11) NOT NULL,
  `authorid` int(11) NOT NULL,
  `parentid` int(11) DEFAULT NULL,
  `toid` int(11) DEFAULT NULL,
  `atlevel` int(11) NOT NULL,
  `likecount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlecomment`
--

LOCK TABLES `articlecomment` WRITE;
/*!40000 ALTER TABLE `articlecomment` DISABLE KEYS */;
INSERT INTO `articlecomment` VALUES (21,'第一','2019-03-05 18:15:43',34,12,NULL,NULL,0,0),(22,'第二','2019-03-05 18:15:49',34,12,21,NULL,1,0),(23,'四三','2019-03-05 18:15:57',34,12,21,22,2,0),(24,'哈哈哈 搞定','2019-03-05 18:18:24',34,12,NULL,NULL,0,0),(25,'厉害','2019-03-05 18:18:31',34,12,24,NULL,1,0),(26,'厉害','2019-03-05 18:18:36',34,12,24,25,2,0),(27,'嘻嘻','2019-03-05 18:27:45',34,12,NULL,NULL,0,0),(28,'66','2019-03-05 18:38:05',34,11,24,26,2,0);
/*!40000 ALTER TABLE `articlecomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) NOT NULL,
  `publishtime` datetime DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `category` int(11) NOT NULL,
  `categorydescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (19,11,'2019-03-03 10:46:37','这是关于文章测试','vue是最好的前端开发框架，Java世界上最优秀的语言。',1,'Java'),(29,11,'2019-03-04 11:08:49','武天元很帅','111',1,'Java'),(30,11,'2019-03-04 11:14:12','Vue真的很难用啊','哎',2,'Vue'),(31,11,'2019-03-04 11:15:34','疯狂发文章中','永远永远',3,'Python'),(32,11,'2019-03-04 11:16:13','继续向继续','湖人总冠军',1,'Java'),(33,11,'2019-03-04 11:16:39','go on','有有有由于',1,'Java'),(34,11,'2019-03-04 11:17:05','1111111111111111','3333333333333333333',2,'Vue'),(35,11,'2019-03-05 18:37:14','测试','嘻嘻嘻',1,'Java');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articletag`
--

DROP TABLE IF EXISTS `articletag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articletag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `tagid` int(11) NOT NULL,
  `tagdescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articletag`
--

LOCK TABLES `articletag` WRITE;
/*!40000 ALTER TABLE `articletag` DISABLE KEYS */;
INSERT INTO `articletag` VALUES (37,19,1,'C++'),(38,19,2,'Java'),(39,19,3,'Vue'),(40,19,5,'前端'),(41,19,6,'后端开发'),(48,29,3,'Vue'),(49,29,8,'机器学习'),(50,29,4,'JavaScript'),(51,30,3,'Vue'),(52,30,5,'前端'),(53,31,3,'Vue'),(54,31,4,'JavaScript'),(55,31,8,'机器学习'),(56,32,3,'Vue'),(57,32,8,'机器学习'),(58,33,3,'Vue'),(59,33,8,'机器学习'),(60,34,4,'JavaScript'),(61,34,8,'机器学习'),(62,35,1,'C++'),(63,35,2,'Java'),(64,35,8,'机器学习');
/*!40000 ALTER TABLE `articletag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articleviewinfo`
--

DROP TABLE IF EXISTS `articleviewinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articleviewinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `viewcount` int(11) NOT NULL,
  `commentcount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articleviewinfo`
--

LOCK TABLES `articleviewinfo` WRITE;
/*!40000 ALTER TABLE `articleviewinfo` DISABLE KEYS */;
INSERT INTO `articleviewinfo` VALUES (1,19,100,0),(4,29,0,0),(5,30,0,0),(6,31,0,0),(7,32,0,0),(8,33,0,0),(9,34,0,0),(10,35,0,0);
/*!40000 ALTER TABLE `articleviewinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_comment`
--

DROP TABLE IF EXISTS `blog_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blog_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `headUrl` varchar(255) DEFAULT NULL,
  `content` text,
  `createTime` datetime DEFAULT NULL,
  `dislikeCount` int(11) DEFAULT NULL,
  `likeCount` int(11) DEFAULT NULL,
  `entityId` int(11) DEFAULT NULL,
  `entityType` int(11) DEFAULT NULL,
  `replyCount` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `toCommentId` int(11) DEFAULT NULL,
  `toUserId` int(11) DEFAULT NULL,
  `toNickName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_comment`
--

LOCK TABLES `blog_comment` WRITE;
/*!40000 ALTER TABLE `blog_comment` DISABLE KEYS */;
INSERT INTO `blog_comment` VALUES (1,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','1','2019-02-08 13:46:11',0,0,1,0,6,0,0,NULL,NULL),(2,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','2','2019-02-08 14:00:26',0,0,1,1,1,0,1,NULL,NULL),(3,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','3','2019-02-08 14:21:50',0,0,1,1,1,0,1,NULL,NULL),(4,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','4','2019-02-08 14:24:46',0,0,1,1,1,0,1,NULL,NULL),(5,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','5','2019-02-08 14:26:44',0,0,1,1,0,0,1,NULL,NULL),(6,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','6','2019-02-08 14:42:00',0,0,1,1,0,0,1,NULL,NULL),(7,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','7','2019-02-08 14:44:34',0,0,1,1,0,0,1,NULL,NULL),(8,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','21','2019-02-08 14:45:11',0,0,1,1,0,0,2,NULL,NULL),(9,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','31','2019-02-08 14:46:58',0,0,1,1,0,0,3,NULL,NULL),(10,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','41','2019-02-08 14:48:36',0,0,1,1,0,0,4,NULL,NULL),(11,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','a','2019-02-08 14:49:09',0,0,1,0,1,0,0,NULL,NULL),(12,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','a1','2019-02-08 14:49:19',0,0,11,1,0,0,11,NULL,NULL);
/*!40000 ALTER TABLE `blog_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_information`
--

DROP TABLE IF EXISTS `blog_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blog_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `keyword` varchar(255) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `likeCount` int(11) DEFAULT NULL,
  `dislikeCount` int(11) DEFAULT NULL,
  `viewCount` int(11) DEFAULT NULL,
  `recommended` bit(1) DEFAULT NULL,
  `headportraiturl` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `hotScore` int(11) DEFAULT NULL,
  `commentCount` int(11) DEFAULT NULL,
  `entityType` int(11) DEFAULT NULL,
  `collectCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_information`
--

LOCK TABLES `blog_information` WRITE;
/*!40000 ALTER TABLE `blog_information` DISABLE KEYS */;
INSERT INTO `blog_information` VALUES (1,3,'njustwh2014@163.com','再见青春','![](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIGiaAaT7fAAE0D2ntZAo311.jpg)\n哈哈哈哈','1,2,3',1,_binary '\0','2019-01-23 15:39:27','2019-01-23 15:39:27',0,0,0,_binary '','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,3,9,0,0),(2,3,'njustwh2014@163.com','我不曾爱过你','123','1,2,3',1,_binary '\0','2019-01-23 16:26:50','2019-01-23 16:26:50',0,0,0,_binary '','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,8,5,0,0),(3,3,'njustwh2014@163.com','123','面对岁月不惜','2,1,0',1,_binary '\0','2019-01-23 17:03:27','2019-01-23 17:03:27',0,0,0,_binary '','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,5,1,0,0),(4,3,'njustwh2014@163.com','哈哈哈哈','![](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIOniAXbpMAAB77upUEmQ365.png)\n\n丫丫哎呀呀呀','5,4,2',1,_binary '\0','2019-01-23 17:57:26','2019-01-23 17:57:26',0,0,0,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,4,0,0,0),(5,3,'njustwh2014@163.com','头像默认图片url','![](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg)','1,2,3',1,_binary '\0','2019-01-23 18:08:39','2019-01-23 18:08:39',0,0,0,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,3,0,0,0);
/*!40000 ALTER TABLE `blog_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorydescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Java'),(2,'Vue'),(3,'Python'),(4,'C++');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagdescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'C++'),(2,'Java'),(3,'Vue'),(4,'JavaScript'),(5,'前端'),(6,'后端开发'),(7,'Python'),(8,'机器学习');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_information`
--

DROP TABLE IF EXISTS `user_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registerstatus` char(1) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `IPaddress` int(10) unsigned DEFAULT NULL,
  `lasttime` timestamp NULL DEFAULT NULL,
  `authority` bit(1) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `hobby` text,
  `headportraiturl` varchar(255) DEFAULT NULL,
  `contributiondegree` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_information`
--

LOCK TABLES `user_information` WRITE;
/*!40000 ALTER TABLE `user_information` DISABLE KEYS */;
INSERT INTO `user_information` VALUES (4,'wanghuan2018@seu.edu.cn','78B0EF01C747B41BFAB669CD0248C951',NULL,NULL,'b4a13',NULL,'2019-01-04 13:55:41',NULL,'wanghuan2018@seu.edu.cn',NULL,NULL,NULL,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL),(10,'744640112@qq.com','60BD49603CB50A26D8B699F59960DBCC',NULL,'7f4edd79-7f02-4760-846d-5ab198f3d5aa','21918',NULL,'2019-02-28 12:34:49',NULL,'744640112@qq.com',NULL,NULL,NULL,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL),(11,'njustwh2014@163.com','9885361D64C98C580DC3AF9AFBEFC55E',NULL,'8ca8664e-3786-4bf6-99e6-76a738056c04','abb41',NULL,'2019-02-28 12:41:45',NULL,'njustwh2014@163.com',NULL,NULL,NULL,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL),(12,'seuwh2018@foxmail.com','3387409D70CF3131B88ED4780988E60C',NULL,NULL,'0885c',NULL,'2019-03-04 04:50:29',NULL,'seuwh2018@foxmail.com',NULL,NULL,NULL,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL);
/*!40000 ALTER TABLE `user_information` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-05 18:59:07
