-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: final
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address_table`
--

DROP TABLE IF EXISTS `address_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address_table` (
  `addressID` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `streetName` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `hotel_hotelID` bigint(20) DEFAULT NULL,
  `userID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`addressID`),
  KEY `FKn2b2snml0adrix57o808v7yh` (`hotel_hotelID`),
  KEY `FKm31d05ah4s5h2f15nt95tp4x7` (`userID`),
  CONSTRAINT `FKm31d05ah4s5h2f15nt95tp4x7` FOREIGN KEY (`userID`) REFERENCES `user_table` (`userID`),
  CONSTRAINT `FKn2b2snml0adrix57o808v7yh` FOREIGN KEY (`hotel_hotelID`) REFERENCES `hotel_table` (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_table`
--

LOCK TABLES `address_table` WRITE;
/*!40000 ALTER TABLE `address_table` DISABLE KEYS */;
INSERT INTO `address_table` VALUES (1,'Boston','MA','St Germain St','02115',NULL,1),(2,'Boston','MA','Newbury St','02115',NULL,2),(3,'Boston','MA','Newbury','02115',1,NULL),(4,'Boston','MA','Mass Ave','02115',2,NULL),(5,'Boston','MA','Bolyston St','02115',3,NULL);
/*!40000 ALTER TABLE `address_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_table`
--

DROP TABLE IF EXISTS `admin_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_table` (
  `adminID` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_table`
--

LOCK TABLES `admin_table` WRITE;
/*!40000 ALTER TABLE `admin_table` DISABLE KEYS */;
INSERT INTO `admin_table` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `admin_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_table`
--

DROP TABLE IF EXISTS `cart_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_table` (
  `quantity` int(11) DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  `foodId` bigint(20) NOT NULL,
  PRIMARY KEY (`userID`,`foodId`),
  KEY `FKj3ul1q39g9posbr76k2xfhwpd` (`foodId`),
  CONSTRAINT `FKe8ocy5199n0rkl12sitvukvsb` FOREIGN KEY (`userID`) REFERENCES `user_table` (`userID`),
  CONSTRAINT `FKj3ul1q39g9posbr76k2xfhwpd` FOREIGN KEY (`foodId`) REFERENCES `fooditem_table` (`foodId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_table`
--

LOCK TABLES `cart_table` WRITE;
/*!40000 ALTER TABLE `cart_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_table`
--

DROP TABLE IF EXISTS `email_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_table` (
  `emailID` bigint(20) NOT NULL AUTO_INCREMENT,
  `email_address` varchar(255) DEFAULT NULL,
  `hotel_hotelID` bigint(20) DEFAULT NULL,
  `userID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`emailID`),
  KEY `FK468dpsd9opsqar22n1e1n7p3q` (`hotel_hotelID`),
  KEY `FK2uls8oihv3n3rg8m13f026n7c` (`userID`),
  CONSTRAINT `FK2uls8oihv3n3rg8m13f026n7c` FOREIGN KEY (`userID`) REFERENCES `user_table` (`userID`),
  CONSTRAINT `FK468dpsd9opsqar22n1e1n7p3q` FOREIGN KEY (`hotel_hotelID`) REFERENCES `hotel_table` (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_table`
--

LOCK TABLES `email_table` WRITE;
/*!40000 ALTER TABLE `email_table` DISABLE KEYS */;
INSERT INTO `email_table` VALUES (1,'bhumikaskhatri@gmail.com',NULL,1),(2,'aadeshranderia26@gmail.com',NULL,2),(3,'ms@ms.com',1,NULL),(4,'pb@pb.com',2,NULL),(5,'cc@cc.cm',3,NULL);
/*!40000 ALTER TABLE `email_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fooditem_table`
--

DROP TABLE IF EXISTS `fooditem_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fooditem_table` (
  `foodId` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `foodName` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `hotel_hotelID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`foodId`),
  KEY `FKbhu21hxeu56104s4n3rnymo75` (`hotel_hotelID`),
  CONSTRAINT `FKbhu21hxeu56104s4n3rnymo75` FOREIGN KEY (`hotel_hotelID`) REFERENCES `hotel_table` (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fooditem_table`
--

LOCK TABLES `fooditem_table` WRITE;
/*!40000 ALTER TABLE `fooditem_table` DISABLE KEYS */;
INSERT INTO `fooditem_table` VALUES (1,'lentils','Dal Tadka',9,1),(2,'wheat flour','Roti',2.99,1),(3,'cocunut ','Veg Curry',9,2),(4,'stuffed veggies','Veg Triangles',7.99,2);
/*!40000 ALTER TABLE `fooditem_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_table`
--

DROP TABLE IF EXISTS `hotel_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_table` (
  `hotelID` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuisines` varchar(255) DEFAULT NULL,
  `hotelName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_table`
--

LOCK TABLES `hotel_table` WRITE;
/*!40000 ALTER TABLE `hotel_table` DISABLE KEYS */;
INSERT INTO `hotel_table` VALUES (1,'Indian','MumbaiSpice','ms',100,'Approved','ms'),(2,'Thai','Pho Basil','pb',90,'Approved','pb'),(3,'American','Cheese Cake','cc',50,'Approved','cc');
/*!40000 ALTER TABLE `hotel_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_table` (
  `orderID` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  `foodId` bigint(20) NOT NULL,
  PRIMARY KEY (`userID`,`orderID`,`foodId`),
  KEY `FK4yru4uykuql5qnlt3n9jvtq7w` (`foodId`),
  CONSTRAINT `FK4yru4uykuql5qnlt3n9jvtq7w` FOREIGN KEY (`foodId`) REFERENCES `fooditem_table` (`foodId`),
  CONSTRAINT `FKdk3q7wo0ki17pp1p13xwjnb13` FOREIGN KEY (`userID`) REFERENCES `user_table` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (1,'2017-04-26 12:50:21',7,1,1),(1,'2017-04-26 12:50:21',1,1,4),(2,'2017-04-26 14:43:09',2,1,1);
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_table`
--

DROP TABLE IF EXISTS `person_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_table` (
  `personID` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_table`
--

LOCK TABLES `person_table` WRITE;
/*!40000 ALTER TABLE `person_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phoneno_table`
--

DROP TABLE IF EXISTS `phoneno_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phoneno_table` (
  `phoneNoID` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone_No` varchar(255) DEFAULT NULL,
  `hotel_hotelID` bigint(20) DEFAULT NULL,
  `userID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`phoneNoID`),
  KEY `FKfdcopd87srjrohh0k8mhiijyo` (`hotel_hotelID`),
  KEY `FKcryhn25f6iymc6s460of9xwdy` (`userID`),
  CONSTRAINT `FKcryhn25f6iymc6s460of9xwdy` FOREIGN KEY (`userID`) REFERENCES `user_table` (`userID`),
  CONSTRAINT `FKfdcopd87srjrohh0k8mhiijyo` FOREIGN KEY (`hotel_hotelID`) REFERENCES `hotel_table` (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phoneno_table`
--

LOCK TABLES `phoneno_table` WRITE;
/*!40000 ALTER TABLE `phoneno_table` DISABLE KEYS */;
INSERT INTO `phoneno_table` VALUES (1,'6173195421',NULL,1),(2,'6173195421',NULL,2),(3,'6175431234',1,NULL),(4,'9870987654',2,NULL),(5,'1234567890',3,NULL);
/*!40000 ALTER TABLE `phoneno_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photohotel_table`
--

DROP TABLE IF EXISTS `photohotel_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photohotel_table` (
  `photoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL,
  `hotelID` bigint(20) DEFAULT NULL,
  `userID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`photoId`),
  KEY `FK7kk7w8kv7qru0pf8158ewn5i6` (`hotelID`),
  KEY `FKmmpod8pi92jxklyl6ad1xpojf` (`userID`),
  CONSTRAINT `FK7kk7w8kv7qru0pf8158ewn5i6` FOREIGN KEY (`hotelID`) REFERENCES `hotel_table` (`hotelID`),
  CONSTRAINT `FKmmpod8pi92jxklyl6ad1xpojf` FOREIGN KEY (`userID`) REFERENCES `user_table` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photohotel_table`
--

LOCK TABLES `photohotel_table` WRITE;
/*!40000 ALTER TABLE `photohotel_table` DISABLE KEYS */;
INSERT INTO `photohotel_table` VALUES (1,'C:\\Users\\Bhumika\\Documents\\SpringProjects\\Final\\src\\main\\resources\\images (1).jpg',1,NULL),(2,'C:\\Users\\Bhumika\\Documents\\SpringProjects\\Final\\src\\main\\resources\\images.jpg',1,NULL),(3,'C:\\Users\\Bhumika\\Documents\\SpringProjects\\Final\\src\\main\\resources\\images (3).jpg',2,NULL),(4,'C:\\Users\\Bhumika\\Documents\\SpringProjects\\Final\\src\\main\\resources\\images (2).jpg',2,NULL);
/*!40000 ALTER TABLE `photohotel_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_table` (
  `userID` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table`
--

LOCK TABLES `user_table` WRITE;
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` VALUES (1,'Bhumika','Khatri','bk','bk'),(2,'Aadesh','Randeria','ar','ar');
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-18 22:24:27
