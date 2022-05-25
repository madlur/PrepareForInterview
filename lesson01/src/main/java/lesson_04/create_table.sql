-- Server version	10.6.4-MariaDB-1:10.6.4+maria~focal

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
CREATE TABLE `film` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(100) NOT NULL,
                        `duration` int(3) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
INSERT INTO `film` VALUES
                       (1,'Фильм 1',120),
                       (2,'Фильм 2',120),
                       (3,'Фильм 3',90),
                       (4,'Фильм 4',120),
                       (5,'Мультфильм',60),
                       (6,'Мультфильм 1',120),
                       (7,'Мультфильм 2',60),
                       (8,'Мультфильм 3',60),
                       (9,'Мультфильм 4',90),
                       (10,'Мультфильм 5',90);
UNLOCK TABLES;

--
-- Table structure for table `poster`
--

DROP TABLE IF EXISTS `poster`;
CREATE TABLE `poster` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `datetime` datetime NOT NULL,
                          `film_id` int(11) NOT NULL,
                          `price` bigint(15) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_film_id_idx` (`film_id`),
                          CONSTRAINT `fk_film_id` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `poster`
--

LOCK TABLES `poster` WRITE;
INSERT INTO `poster` VALUES
                         (1,'2022-01-10 09:00:00',1,100),
                         (2,'2022-01-10 11:00:00',5,200),
                         (3,'2022-01-10 12:30:00',2,300),
                         (4,'2022-01-10 15:00:00',6,400),
                         (5,'2022-01-10 17:30:00',3,600),
                         (6,'2022-01-10 19:30:00',7,600),
                         (7,'2022-01-10 20:30:00',5,700),
                         (8,'2022-01-10 22:00:00',4,800);
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `poster_id` int(11) NOT NULL,
                           `amount` int(4) NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fk_poster_id_idx` (`poster_id`),
                           CONSTRAINT `fk_poster_id` FOREIGN KEY (`poster_id`) REFERENCES `poster` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
INSERT INTO `session` VALUES
                          (1,1,2),(2,1,4),(3,1,2),(4,1,1),(5,1,3),(6,1,10),
                          (7,2,3),(8,2,5),(9,2,10),(10,2,4),(11,2,5),(12,2,2),(13,3,2),
                          (14,3,3),(15,3,5),
                          (16,4,5),(17,4,4),(18,4,2),
                          (19,5,2),(20,5,3),(21,5,5),(22,5,3),(23,5,1),(24,5,1),
                          (25,6,2),(26,6,3),(27,6,4),(28,6,2),(29,6,2),(30,6,4),(31,6,5),(32,6,3),(33,6,1),(34,6,1),(35,6,1),(36,6,3),(37,6,2),(38,6,1),(39,6,5),(40,6,2),
                          (41,7,2),(42,7,5),(43,7,2),(44,7,2),(45,7,2),(46,7,4),(47,7,3),(48,7,1),(49,7,1),(50,7,2),(51,7,5),(52,7,4),(53,7,2),(54,7,2),(55,7,4),
                          (56,8,2),(57,8,4),(58,8,2),(59,8,5),(60,8,3),(61,8,2),(62,8,4),(63,8,5),(64,8,6),(65,8,2);
UNLOCK TABLES;