-- MySQL dump 10.13  Distrib 9.0.1, for macos14.7 (arm64)
--
-- Host: localhost    Database: restaurant_sys
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Category` (
  `categoryId` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `restaurantId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categoryId`),
  KEY `restaurantId` (`restaurantId`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`restaurantId`) REFERENCES `Restaurant` (`restaurantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (1,'Jason','xxx@ee.com'),(2,'Mike','xxx@ee.com');
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `userId` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Item` (
  `itemId` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `categoryId` varchar(255) DEFAULT NULL,
  `image` text,
  PRIMARY KEY (`itemId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `Category` (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES ('32f90eb8-b534-4965-8c43-2d6d04744391','Vegettt',23.45,'1lb',NULL,'sss'),('aa0b6330-979f-448d-99cb-3641e94cbf7c','ttt',34.00,'rr',NULL,'gg'),('b8631693-0249-464d-b26f-28fcd538dbc4','Bread',3.00,'222',NULL,'data:image/jpeg;base64,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'),('d1188f91-5c5e-490d-97d0-9b886fb6c1be','Egg',3.44,'12',NULL,'sssss'),('e0397cf4-759b-4676-a62d-13519a5f6c05','Chicken',23.20,'1lb',NULL,'https://www.allrecipes.com/thmb/q-IfK20zxeyp1DgKWhrVp6CQ43w=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/AR-89268-triple-dipped-fried-chicken-beauty-4x3-3961ac838ddd41958e7cb9f49376cd68.jpg');
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order`
--

DROP TABLE IF EXISTS `Order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Order` (
  `orderId` varchar(36) NOT NULL,
  `orderDate` datetime NOT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  `status` enum('Pending','Processing','Finished') NOT NULL DEFAULT 'Pending',
  `customerId` varchar(45) NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

LOCK TABLES `Order` WRITE;
/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
INSERT INTO `Order` VALUES ('f113b1aa-334e-43f6-a3fe-86f60fbc76c4','2024-11-29 22:41:03',23.45,'Processing','customer123');
/*!40000 ALTER TABLE `Order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderItem`
--

DROP TABLE IF EXISTS `OrderItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OrderItem` (
  `orderItemId` varchar(36) NOT NULL,
  `orderId` varchar(36) DEFAULT NULL,
  `itemId` varchar(36) DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `name` varchar(36) NOT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `orderId` (`orderId`),
  KEY `itemId` (`itemId`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `Order` (`orderId`) ON DELETE CASCADE,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`itemId`) REFERENCES `Item` (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderItem`
--

LOCK TABLES `OrderItem` WRITE;
/*!40000 ALTER TABLE `OrderItem` DISABLE KEYS */;
INSERT INTO `OrderItem` VALUES ('1bdaa2ba-e4fc-4ffa-a965-f63f343f048b','f113b1aa-334e-43f6-a3fe-86f60fbc76c4','32f90eb8-b534-4965-8c43-2d6d04744391',1,23.45,'Vegettt');
/*!40000 ALTER TABLE `OrderItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Restaurant`
--

DROP TABLE IF EXISTS `Restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Restaurant` (
  `restaurantId` varchar(255) NOT NULL,
  PRIMARY KEY (`restaurantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Restaurant`
--

LOCK TABLES `Restaurant` WRITE;
/*!40000 ALTER TABLE `Restaurant` DISABLE KEYS */;
/*!40000 ALTER TABLE `Restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RestaurantStaff`
--

DROP TABLE IF EXISTS `RestaurantStaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RestaurantStaff` (
  `userId` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `restaurantstaff_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RestaurantStaff`
--

LOCK TABLES `RestaurantStaff` WRITE;
/*!40000 ALTER TABLE `RestaurantStaff` DISABLE KEYS */;
/*!40000 ALTER TABLE `RestaurantStaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(250) NOT NULL,
  `unit` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `userId` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `contactInfo` varchar(255) DEFAULT NULL,
  `userType` varchar(50) NOT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `user_chk_1` CHECK ((`userType` in (_utf8mb4'Customer',_utf8mb4'Staff')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('101','Alice','alice@example.com','Staff');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(250) NOT NULL,
  `lastname` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `username` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-29 17:02:01
