CREATE DATABASE  IF NOT EXISTS `dblp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dblp`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: dblp
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
#/*!40014 SET @OLD_typeFOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `cite` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `committee`
--

DROP TABLE IF EXISTS `committee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `committee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conferenceName` varchar(255) DEFAULT NULL,
  `memberName` varchar(255) DEFAULT NULL,
`memberType` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee`
--

LOCK TABLES `committee` WRITE;
/*!40000 ALTER TABLE `committee` DISABLE KEYS */;
/*!40000 ALTER TABLE `committee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incollection`
--

DROP TABLE IF EXISTS `incollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incollection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) NOT NULL,
  `authorName` varchar(255) NOT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `startPageNo` int(11) DEFAULT NULL,
  `endPageNo` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `crossref` varchar(255) DEFAULT NULL,
  `bookTitle` varchar(255) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `ee` varchar(1000) DEFAULT NULL,
  /*PRIMARY KEY (`key`,`authorName`)*/
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incollection`
--

LOCK TABLES `incollection` WRITE;
/*!40000 ALTER TABLE `incollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `incollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`keyVal` varchar(255) NOT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `pages` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `volume` varchar(100) DEFAULT NULL,
  `journal` varchar(255) DEFAULT NULL,
  `number` varchar(100) DEFAULT NULL,
  `crossRef` varchar(255) DEFAULT NULL,
  `bookTitle` varchar(255) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `ee` varchar(1000) DEFAULT NULL,
  /*PRIMARY KEY (`keyVal`,`type`)*/
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proceedings`
--

DROP TABLE IF EXISTS `proceedings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proceedings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `editorName` varchar(255) NOT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `volume` int(11) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `ee` varchar(1000) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `bookTitle` varchar(255) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  /*PRIMARY KEY (`editorName`)*/
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proceedings`
--

LOCK TABLES `proceedings` WRITE;
/*!40000 ALTER TABLE `proceedings` DISABLE KEYS */;
/*!40000 ALTER TABLE `proceedings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shortlistcommittee`
--

DROP TABLE IF EXISTS `shortlistcommittee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shortlistcommittee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authorname` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `ownerusername` varchar(255) DEFAULT NULL,
  `keyval` varchar(500) DEFAULT NULL,
  `papertype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shortlistcommittee`
--

LOCK TABLES `shortlistcommittee` WRITE;
/*!40000 ALTER TABLE `shortlistcommittee` DISABLE KEYS */;
/*!40000 ALTER TABLE `shortlistcommittee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemuser`
--

DROP TABLE IF EXISTS `systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `systemuser` WRITE;
/*!40000 ALTER TABLE `systemuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `systemuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis`
--

DROP TABLE IF EXISTS `thesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thesis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyVal` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `pages` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `ee` varchar(1000) DEFAULT NUll,
  `school` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `paperauthor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paperauthor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyVal` varchar(255) DEFAULT NULL,
  `authorName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `authorID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shortlistcommittee`
--

LOCK TABLES `shortlistcommittee` WRITE;
/*!40000 ALTER TABLE `shortlistcommittee` DISABLE KEYS */;
/*!40000 ALTER TABLE `shortlistcommittee` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Dumping data for table `thesis`
--

LOCK TABLES `thesis` WRITE;
/*!40000 ALTER TABLE `thesis` DISABLE KEYS */;
/*!40000 ALTER TABLE `thesis` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
#/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-18 13:17:02

DROP TABLE IF EXISTS `www`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `www` (
 /*`id` int(11) NOT NULL AUTO_INCREMENT,*/
  `keyVal` varchar(50) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `title` varchar(2000) DEFAULT NULL,
  `year` int(10) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`keyVal`)
/*PRIMARY KEY (`id`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `www` WRITE;
/*!40000 ALTER TABLE `www` DISABLE KEYS */;
/*!40000 ALTER TABLE `www` ENABLE KEYS */;
UNLOCK TABLES;


#SET GLOBAL character_set_client = utf8mb4;

#SET GLOBAL character_set_connection = utf8mb4;

#SET GLOBAL character_set_server = utf8mb4;

#SET GLOBAL character_set_database = utf8mb4;

#SET GLOBAL character_set_results = utf8mb4;

#SET GLOBAL collation_connection = utf8mb4_unicode_ci;
#SET GLOBAL collation_database = utf8mb4_unicode_ci;
#SET GLOBAL collation_server = utf8mb4_unicode_ci;

#alter table author convert to character set utf8mb4 collate utf8mb4_unicode_ci;
#alter table paper convert to character set utf8mb4 collate utf8mb4_unicode_ci;
#alter table paperauthor convert to character set utf8mb4 collate utf8mb4_unicode_ci;
#alter table thesis convert to character set utf8mb4 collate utf8mb4_unicode_ci;
#alter table www convert to character set utf8mb4 collate utf8mb4_unicode_ci;


#adding indexes to table paper
ALTER TABLE `paper` ADD INDEX `KEY_VAL` (`keyVal`);
ALTER TABLE `paper` ADD INDEX `TYPE_IND` (`type`);
ALTER TABLE `paper` ADD INDEX `YEAR_IND` (`year`);

#adding indexes to table paper
ALTER TABLE `paperauthor` ADD INDEX `K_VAL` (`keyVal`);
ALTER TABLE `paperauthor` ADD INDEX `NAME_IND` (`authorName`);
ALTER TABLE `paperauthor` ADD INDEX `TYPE_VAL` (`type`);

#adding indexes to table thesis
ALTER TABLE `thesis` ADD INDEX `V_TYPE` (`type`);
ALTER TABLE `thesis` ADD INDEX `KEY` (`keyVal`);
ALTER TABLE `thesis` ADD INDEX `SCHOOL_IND` (`school`);
ALTER TABLE `thesis` ADD INDEX `Y_IND` (`year`);

#adding indexes to table committee
ALTER TABLE `committee` ADD INDEX `MEMBERNAME_IND` (`memberName`);
ALTER TABLE `committee` ADD INDEX `MEMBERTYPE_IND` (`memberType`);
ALTER TABLE `committee` ADD INDEX `COM_YEAR_IND` (`year`);
ALTER TABLE `committee` ADD INDEX `CONF_NAME_IND` (`conferenceName`);
