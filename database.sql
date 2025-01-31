-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: admin
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS admins;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE admins (
  admin_id int NOT NULL AUTO_INCREMENT,
  adminname varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (admin_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES admins WRITE;
/*!40000 ALTER TABLE admins DISABLE KEYS */;
INSERT INTO admins VALUES (1,'admin','admin');
/*!40000 ALTER TABLE admins ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rides`
--

DROP TABLE IF EXISTS rides;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE rides (
  ride_id int NOT NULL AUTO_INCREMENT,
  rideName varchar(45) DEFAULT NULL,
  minHeight varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  capacity int DEFAULT NULL,
  ticketPrice int DEFAULT NULL,
  PRIMARY KEY (ride_id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rides`
--

LOCK TABLES rides WRITE;
/*!40000 ALTER TABLE rides DISABLE KEYS */;
INSERT INTO rides VALUES (1,'Sky Flyer','48 inches','Stopped',30,2500),(2,'Water Slide','42 inches','Stopped',25,1800),(3,'Haunted Mansion','None','Stopped',10,2200),(4,'Swing Ride','36 inches','Stopped',40,1500),(5,'Thunder Loop','52 inches','Stopped',20,4000),(6,'Mega Drop','54 inches','Stopped',25,3500),(7,'Space Explorer','50 inches','Maintenance',30,4000),(8,'Wild River Rapids','42 inches','Stopped',40,2800),(9,'Haunted Maze','None','Stopped',15,2200),(10,'Bumper Cars','None','Stopped',20,1500),(11,'Extreme Spinner','48 inches','Stopped',12,3200),(12,'Giant Ferris Wheel','36 inches','Running',50,1800),(13,'Laser Tag Arena','None','Stopped',15,2500),(14,'Dragon Coaster','52 inches','Running',24,3800),(15,'Tornado Twister','50 inches','Stopped',18,4100);
/*!40000 ALTER TABLE rides ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_values`
--

DROP TABLE IF EXISTS ticket_values;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ticket_values (
  id int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  phone varchar(45) DEFAULT NULL,
  unique_id varchar(45) DEFAULT NULL,
  pass_type varchar(45) DEFAULT NULL,
  creation_date date DEFAULT NULL,
  lockers int DEFAULT NULL,
  adult_num int DEFAULT NULL,
  child_num int DEFAULT NULL,
  selected_rides varchar(1000) DEFAULT NULL,
  total_cost int DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_values`
--

LOCK TABLES ticket_values WRITE;
/*!40000 ALTER TABLE ticket_values DISABLE KEYS */;
INSERT INTO ticket_values VALUES (1,'Shaila','sdas','213','TICK25012920412651c1','VIP Pass','2025-01-29',2,4,0,'Sky Flyer',3000),(2,'asdas','asda','sd','TICK250129204143aff2','Standard Pass','2025-01-29',2,2,2,'Water Slide',5000),(3,'Dibbajothy Sarker','dibbajothy2@gmail.com','015xxxxxxxxxx','TICK250130005755804c','Standard Pass','2025-01-30',2,3,4,'Sky Flyer, Water Slide, Mega Drop, Wild River Rapids, Haunted Maze',15230),(4,'Shaila','shaila@gmail.com','013xxxxxxxxxxxx','TICK250130005821e42c','VIP Pass','2025-01-30',2,6,2,'Sky Flyer, Water Slide',6560),(5,'jouy','s@gmail.com','6789','TICK2501300220526c0c','VIP Pass','2024-12-30',23,1,3,'Sky Flyer, Water Slide, Mega Drop, Wild River Rapids',14720),(6,'adasd','asdasd','asd','TICK250131182901df68','VIP Pass','2025-01-31',312,123,123,'Giant Ferris Wheel, Dragon Coaster',41370);
/*!40000 ALTER TABLE ticket_values ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS users;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE users (
  user_id int NOT NULL AUTO_INCREMENT,
  employeeID varchar(60) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  phone varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  jobRole varchar(45) DEFAULT NULL,
  salary int DEFAULT NULL,
  username varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES users WRITE;
/*!40000 ALTER TABLE users DISABLE KEYS */;
INSERT INTO users VALUES (1,'EMP2501290233154441','Alice Johnson','New York','9876543210','alice@example.com','Ticket Manager',45000,'usr','usr'),(2,'EMP2501290233154442','Michael Smith','California','9123456789','michael@example.com','Ride Manager',47000,'dd','dd'),(3,'EMP2501290233154443','Emily Davis','Texas','9234567890','emily@example.com','Ticket Manager',46000,'emily.davis','pass345'),(4,'EMP2501290233154444','James Brown','Florida','9345678901','james@example.com','Ticket Manager',48000,'james.brown','pass456'),(5,'EMP2501290233154445','Sophia Miller','Nevada','9456789012','sophia@example.com','Ticket Manager',45000,'sophia.miller','pass567'),(6,'EMP2501290233154446','Daniel Wilson','Ohio','9567890123','daniel@example.com','Ticket Manager',47000,'daniel.wilson','pass678'),(7,'EMP2501290233154447','Emma Thompson','Washington','9678901234','emma@example.com','Ticket Manager',46000,'emma.thompson','pass789'),(8,'EMP2501290233154448','Olivia Martinez','Oregon','9789012345','olivia@example.com','Ticket Manager',48000,'olivia.martinez','pass890'),(9,'EMP2501290233154449','Liam Taylor','Arizona','9890123456','liam@example.com','Ticket Manager',47000,'liam.taylor','pass901'),(10,'EMP2501290233154450','Noah White','Colorado','9901234567','noah@example.com','Ticket Manager',45000,'noah.white','pass012'),(11,'EMP2501290233154451','Isabella Harris','Georgia','9012345678','isabella@example.com','Ticket Manager',46000,'isabella.harris','pass123'),(12,'EMP2501290233154452','Mason Martin','Kentucky','9123456780','mason@example.com','Ticket Manager',47000,'mason.martin','pass234'),(13,'EMP2501290233154453','Ethan Lee','Michigan','9234567891','ethan@example.com','Ticket Manager',46000,'ethan.lee','pass345'),(14,'EMP2501290233154454','Ava Walker','Alaska','9345678902','ava@example.com','Ticket Manager',45000,'ava.walker','pass456'),(15,'EMP2501290233154455','Lucas Hall','Hawaii','9456789013','lucas@example.com','Ticket Manager',48000,'lucas.hall','pass567'),(16,'EMP2501290233154456','Charlotte Young','Indiana','9567890124','charlotte@example.com','Ticket Manager',46000,'charlotte.young','pass678'),(17,'EMP2501290233154457','Harper King','Kansas','9678901235','harper@example.com','Ticket Manager',47000,'harper.king','pass789'),(18,'EMP2501290233154458','Benjamin Scott','Texas','9789012346','benjamin@example.com','Ride Manager',55000,'benjamin.scott','pass890'),(19,'EMP2501290233154459','Amelia Green','Florida','9890123457','amelia@example.com','Ride Manager',53000,'amelia.green','pass901'),(20,'EMP2501290233154460','Evelyn Adams','Ohio','9901234568','evelyn@example.com','Ride Manager',52000,'evelyn.adams','pass012'),(21,'EMP2501290233154461','William Carter','Georgia','9012345679','william@example.com','Ride Manager',54000,'william.carter','pass123'),(22,'EMP2501290233154462','Jack Anderson','Kentucky','9123456781','jack@example.com','Ride Manager',55000,'jack.anderson','pass234'),(23,'EMP2501290233154463','Henry Thomas','Michigan','9234567892','henry@example.com','Ride Manager',53000,'henry.thomas','pass345'),(24,'EMP2501290233154464','Lucas Hernandez','Alaska','9345678903','lucas@example.com','Ride Manager',56000,'lucas.hernandez','pass456'),(25,'EMP2501290233154465','Sebastian Robinson','Hawaii','9456789014','sebastian@example.com','Ride Manager',52000,'sebastian.robinson','pass567'),(26,'EMP2501290233154466','Nathan White','Indiana','9567890125','nathan@example.com','Ride Manager',54000,'nathan.white','pass678'),(27,'EMP2501290233154467','Elijah Baker','Kansas','9678901236','elijah@example.com','Ride Manager',55000,'elijah.baker','pass789'),(28,'EMP2501290233154468','Mia Gonzalez','New York','9789012347','mia@example.com','Shop Manager',60000,'mia.gonzalez','pass890'),(29,'EMP2501290233154469','Jacob Hall','California','9890123458','jacob@example.com','Shop Manager',62000,'jacob.hall','pass901'),(30,'EMP2501290233154470','Michael Young','Texas','9901234569','michael@example.com','Shop Manager',61000,'michael.young','pass012'),(31,'EMP2501290233154471','Emily King','Florida','9012345680','emily@example.com','Shop Manager',63000,'emily.king','pass123'),(32,'EMP2501290233154472','Sophia Adams','Ohio','9123456782','sophia@example.com','Shop Manager',64000,'sophia.adams','pass234'),(35,'EMP250130021803d73f','jotish','dasd','2213213','sadasdasd','Ticket Manager',21312,'jotish','jotish'),(36,'EMP250131182615114f','Dibbajothy','ads','asd','djdasd','Ticket Manager',213,'dsa','das');
/*!40000 ALTER TABLE users ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-31 19:09:55
