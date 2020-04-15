-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tenis
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (11,'r','r','r',_binary ''),(33,'mail@vlad','Vlad','vlad',_binary ''),(34,'mail@admin','Admin','admin',_binary ''),(35,'mail@andreea','Andreea','andreea',_binary '');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (177),(177),(177),(177),(177);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tennis_game`
--

DROP TABLE IF EXISTS `tennis_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tennis_game` (
  `id` int NOT NULL,
  `player1_score` varchar(255) DEFAULT NULL,
  `player2_score` varchar(255) DEFAULT NULL,
  `tennisSet_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK20nl6xv357iyf56e1sfd9mods` (`tennisSet_id`),
  CONSTRAINT `FK20nl6xv357iyf56e1sfd9mods` FOREIGN KEY (`tennisSet_id`) REFERENCES `tennis_set` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tennis_game`
--

LOCK TABLES `tennis_game` WRITE;
/*!40000 ALTER TABLE `tennis_game` DISABLE KEYS */;
INSERT INTO `tennis_game` VALUES (56,'6','1',53),(57,'3','6',54),(58,'8','6',55),(144,'6','1',143),(155,'6','4',154),(157,'6','1',156),(159,'6','5',158),(161,'3','5',160),(166,'3','2',165);
/*!40000 ALTER TABLE `tennis_game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tennis_match`
--

DROP TABLE IF EXISTS `tennis_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tennis_match` (
  `id` int NOT NULL,
  `player1_id` int DEFAULT NULL,
  `player2_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKad05dnd8bxx7eebpt3noasko3` (`player1_id`),
  KEY `FKhx076yjd8jntjjvq8au3tui0t` (`player2_id`),
  CONSTRAINT `FKad05dnd8bxx7eebpt3noasko3` FOREIGN KEY (`player1_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKhx076yjd8jntjjvq8au3tui0t` FOREIGN KEY (`player2_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tennis_match`
--

LOCK TABLES `tennis_match` WRITE;
/*!40000 ALTER TABLE `tennis_match` DISABLE KEYS */;
INSERT INTO `tennis_match` VALUES (38,30,29),(142,64,63),(153,28,31);
/*!40000 ALTER TABLE `tennis_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tennis_set`
--

DROP TABLE IF EXISTS `tennis_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tennis_set` (
  `id` int NOT NULL,
  `tennisMatch_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKey4vne06cq7tvjj1jvttqb1t7` (`tennisMatch_id`),
  CONSTRAINT `FKey4vne06cq7tvjj1jvttqb1t7` FOREIGN KEY (`tennisMatch_id`) REFERENCES `tennis_match` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tennis_set`
--

LOCK TABLES `tennis_set` WRITE;
/*!40000 ALTER TABLE `tennis_set` DISABLE KEYS */;
INSERT INTO `tennis_set` VALUES (53,38),(54,38),(55,38),(160,38),(143,142),(165,142),(154,153),(156,153),(158,153);
/*!40000 ALTER TABLE `tennis_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (28,'mail@nadal','Nadal','nadal',_binary '\0'),(29,'mail@murray','Murray','murray',_binary '\0'),(30,'mail@djokovic','Djokovic','djokovic',_binary '\0'),(31,'mail@thiem','Thiem','thiem',_binary '\0'),(59,'mail@williams','Williams','williams',_binary '\0'),(63,'mail@federer','Federer','federer',_binary '\0'),(64,'mail@halep','Halep','halep',_binary '\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-21 18:58:12
