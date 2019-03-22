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
-- Table structure for table `admininfo`
--

DROP TABLE IF EXISTS `admininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admininfo`
--

LOCK TABLES `admininfo` WRITE;
/*!40000 ALTER TABLE `admininfo` DISABLE KEYS */;
INSERT INTO `admininfo` VALUES (7,'admin','35C09AB3702B91D0C9D78F8ED624EF98','5f64a47e-fbbd-4fb4-bdaf-a4d4db1c9ecd','da9f6');
/*!40000 ALTER TABLE `admininfo` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlebody`
--

LOCK TABLES `articlebody` WRITE;
/*!40000 ALTER TABLE `articlebody` DISABLE KEYS */;
INSERT INTO `articlebody` VALUES (41,42,'![pc页面设计.png](http://localhost:8000/59159c52-25b0-422c-bdc0-81b3f390d03d_pc页面设计.png)\n','<p><img src=\"http://localhost:8000/59159c52-25b0-422c-bdc0-81b3f390d03d_pc%E9%A1%B5%E9%9D%A2%E8%AE%BE%E8%AE%A1.png\" alt=\"pc页面设计.png\" /></p>\n'),(42,43,'永远永远','<p>永远永远</p>\n'),(43,44,'1111111111111','<p>1111111111111</p>\n'),(44,45,'vue。js','<p>vue。js</p>\n'),(45,46,'222222222222222','<p>222222222222222</p>\n'),(47,48,'![blogbg.png](http://localhost:8000/d0ce9440-16b6-487f-a72e-aa815775fc77_blog-bg.png)','<p><img src=\"http://localhost:8000/d0ce9440-16b6-487f-a72e-aa815775fc77_blog-bg.png\" alt=\"blogbg.png\" /></p>\n'),(48,49,'111','<p>111</p>\n'),(49,50,'3333333333![blogname.png](http://localhost:8000/9a7b7ebb-bf74-44b8-92bc-847edcbf966b_blog-name.png)','<p>3333333333<img src=\"http://localhost:8000/9a7b7ebb-bf74-44b8-92bc-847edcbf966b_blog-name.png\" alt=\"blogname.png\" /></p>\n'),(50,51,'5555555555','<p>5555555555</p>\n');
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
  PRIMARY KEY (`id`),
  KEY `fkarticlescomment` (`articleid`),
  CONSTRAINT `fkarticlescomment` FOREIGN KEY (`articleid`) REFERENCES `articles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlecomment`
--

LOCK TABLES `articlecomment` WRITE;
/*!40000 ALTER TABLE `articlecomment` DISABLE KEYS */;
INSERT INTO `articlecomment` VALUES (37,'11111111111111','2019-03-18 13:55:31',49,11,NULL,NULL,0,0),(38,'12','2019-03-18 13:56:30',50,11,NULL,NULL,0,0);
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
  `cover` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (42,11,'2019-03-08 12:44:28','这是一个测试','vue很难用啊',2,'后端开发','http://localhost:8000/24db0138-3f77-4d4b-a6ab-9c9e32c364b9_java.jpg'),(43,11,'2019-03-11 15:53:53','江南','圈圈',3,'数据库','http://localhost:8000/24db0138-3f77-4d4b-a6ab-9c9e32c364b9_java.jpg'),(44,11,'2019-03-06 18:49:33','加一','222222',1,'前端开发','http://localhost:8000/24db0138-3f77-4d4b-a6ab-9c9e32c364b9_java.jpg'),(45,11,'2019-03-11 15:54:41','vue','234',1,'前端开发','http://localhost:8000/24db0138-3f77-4d4b-a6ab-9c9e32c364b9_java.jpg'),(46,11,'2019-03-11 15:55:05','00000000000','3333333333',4,'服务器','http://localhost:8000/24db0138-3f77-4d4b-a6ab-9c9e32c364b9_java.jpg'),(48,11,'2019-03-14 09:23:57','测试封面','213',1,'前端开发','http://localhost:8000/d0ce9440-16b6-487f-a72e-aa815775fc77_blog-bg.png'),(49,11,'2019-03-14 10:02:22','测试','23',1,'前端开发',''),(50,11,'2019-03-18 13:56:17','2222222','1',1,'前端开发','http://localhost:8000/9a7b7ebb-bf74-44b8-92bc-847edcbf966b_blog-name.png'),(51,11,'2019-03-18 15:35:09','445','12',1,'前端开发','');
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
  PRIMARY KEY (`id`),
  KEY `fkarticles` (`articleid`),
  KEY `fktag` (`tagid`),
  CONSTRAINT `fkarticles` FOREIGN KEY (`articleid`) REFERENCES `articles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fktag` FOREIGN KEY (`tagid`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articletag`
--

LOCK TABLES `articletag` WRITE;
/*!40000 ALTER TABLE `articletag` DISABLE KEYS */;
INSERT INTO `articletag` VALUES (78,42,3,'Vue'),(79,42,2,'Java'),(80,42,5,'前端'),(81,42,6,'后端开发'),(82,43,3,'Vue'),(83,43,8,'机器学习'),(84,44,3,'Vue'),(85,44,8,'机器学习'),(86,45,3,'Vue'),(87,45,8,'机器学习'),(88,46,3,'Vue'),(89,46,8,'机器学习'),(92,48,5,'前端'),(93,48,4,'JavaScript'),(94,49,3,'Vue'),(95,49,8,'机器学习'),(96,50,1,'C++'),(97,50,2,'Java'),(104,51,3,'Vue'),(105,51,8,'机器学习');
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articleviewinfo`
--

LOCK TABLES `articleviewinfo` WRITE;
/*!40000 ALTER TABLE `articleviewinfo` DISABLE KEYS */;
INSERT INTO `articleviewinfo` VALUES (17,42,2,0),(18,43,1,0),(19,44,1,0),(20,45,2,0),(21,46,1,0),(23,48,1,0),(24,49,3,1),(25,50,4,1),(26,51,2,0);
/*!40000 ALTER TABLE `articleviewinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articleweight`
--

DROP TABLE IF EXISTS `articleweight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articleweight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articleweight`
--

LOCK TABLES `articleweight` WRITE;
/*!40000 ALTER TABLE `articleweight` DISABLE KEYS */;
INSERT INTO `articleweight` VALUES (1,42,4),(2,43,2),(3,44,2),(4,45,4),(5,46,2),(7,48,2),(8,49,14),(9,50,16),(10,51,4);
/*!40000 ALTER TABLE `articleweight` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'前端开发'),(2,'后端开发'),(3,'数据库'),(4,'服务器'),(5,'机器学习');
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
INSERT INTO `user_information` VALUES (4,'wanghuan2018@seu.edu.cn','78B0EF01C747B41BFAB669CD0248C951',NULL,NULL,'b4a13',NULL,'2019-01-04 13:55:41',NULL,'wanghuan2018@seu.edu.cn',NULL,NULL,NULL,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL),(10,'744640112@qq.com','60BD49603CB50A26D8B699F59960DBCC',NULL,'7f4edd79-7f02-4760-846d-5ab198f3d5aa','21918',NULL,'2019-02-28 12:34:49',NULL,'744640112@qq.com',NULL,NULL,NULL,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL),(11,'njustwh2014@163.com','9885361D64C98C580DC3AF9AFBEFC55E',NULL,'9c0715b5-a85d-42dc-85e8-6eee61ad5fb9','abb41',NULL,'2019-02-28 12:41:45',NULL,'一天只赢一把',_binary '','1996-04-17 00:00:00','东南大学','篮球','http://localhost:8000/bcb1636b-5caa-46af-9f42-464ae0b5ed57_DPJB4260.JPG',NULL),(12,'seuwh2018@foxmail.com','3387409D70CF3131B88ED4780988E60C',NULL,NULL,'0885c',NULL,'2019-03-04 04:50:29',NULL,'seuwh2018@foxmail.com',NULL,NULL,NULL,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL);
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

-- Dump completed on 2019-03-22 11:07:49
