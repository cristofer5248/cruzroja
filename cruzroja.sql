-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: cruzroja
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB

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
-- Table structure for table `actividades`
--

DROP TABLE IF EXISTS `actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actividades` (
  `idactividad` int(10) NOT NULL AUTO_INCREMENT,
  `lugarproyectoPadre` varchar(6) DEFAULT NULL,
  `detalleactividad` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`idactividad`),
  KEY `detallesactividades_pki` (`detalleactividad`),
  KEY `lugarproyectoPadre_actividades_pki` (`lugarproyectoPadre`),
  CONSTRAINT `detallesactividades_pki` FOREIGN KEY (`detalleactividad`) REFERENCES `actividadesdetalles` (`idactividadesdetalles`) ON DELETE CASCADE,
  CONSTRAINT `lugarproyectoPadre_actividades_pki` FOREIGN KEY (`lugarproyectoPadre`) REFERENCES `lugarproyecto` (`idl`)
) ENGINE=InnoDB AUTO_INCREMENT=959 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividades`
--

LOCK TABLES `actividades` WRITE;
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
INSERT INTO `actividades` VALUES (949,'REC992','1'),(952,'LA2575','3'),(953,'LA2575','1'),(954,'REC992','4'),(955,'REC992','2'),(956,'LA3REC','2'),(957,'LA3REC','1'),(958,'REC992','3');
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividadesdetalles`
--

DROP TABLE IF EXISTS `actividadesdetalles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actividadesdetalles` (
  `idactividadesdetalles` varchar(6) NOT NULL,
  `titulo` varchar(40) DEFAULT NULL,
  `detalle` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idactividadesdetalles`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividadesdetalles`
--

LOCK TABLES `actividadesdetalles` WRITE;
/*!40000 ALTER TABLE `actividadesdetalles` DISABLE KEYS */;
INSERT INTO `actividadesdetalles` VALUES ('1','QUINTA activida','DETALLES'),('2','Primer actividad CAMBIO','Porque esta actividad...si es chida'),('3','Segunda actividad','Des'),('4','Tercera Actividad','sdfs');
/*!40000 ALTER TABLE `actividadesdetalles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiados`
--

DROP TABLE IF EXISTS `beneficiados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiados` (
  `idb` varchar(8) NOT NULL,
  `idbeneficiado` varchar(8) NOT NULL,
  `idproyecto` int(10) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idb`),
  KEY `beneficiados_ibfk_1` (`idbeneficiado`),
  KEY `beneficiados_ibfk_2` (`idproyecto`),
  CONSTRAINT `beneficiados_ibfk_1` FOREIGN KEY (`idbeneficiado`) REFERENCES `datosbeneficiados` (`idusuario`),
  CONSTRAINT `beneficiados_ibfk_2` FOREIGN KEY (`idproyecto`) REFERENCES `actividades` (`idactividad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiados`
--

LOCK TABLES `beneficiados` WRITE;
/*!40000 ALTER TABLE `beneficiados` DISABLE KEYS */;
INSERT INTO `beneficiados` VALUES ('MOINRE31','MOM177',949,'2018-12-23 06:07:23');
/*!40000 ALTER TABLE `beneficiados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idcategoria` int(2) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'canton'),(2,'escuela'),(3,'SALUD');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datosbeneficiados`
--

DROP TABLE IF EXISTS `datosbeneficiados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datosbeneficiados` (
  `idusuario` varchar(8) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(20) DEFAULT NULL,
  `genero` int(1) NOT NULL,
  `correo` varchar(40) DEFAULT NULL,
  `edad` int(2) NOT NULL,
  `telefono` int(8) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idusuario`),
  KEY `genero` (`genero`),
  CONSTRAINT `datosbeneficiados_ibfk_1` FOREIGN KEY (`genero`) REFERENCES `genero` (`idg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datosbeneficiados`
--

LOCK TABLES `datosbeneficiados` WRITE;
/*!40000 ALTER TABLE `datosbeneficiados` DISABLE KEYS */;
INSERT INTO `datosbeneficiados` VALUES ('MOM177','ROOOOOOOOOOOOOOOOOOOOON','RONRON',1,'nadawe',5,71232212,'2018-12-28 06:37:13');
/*!40000 ALTER TABLE `datosbeneficiados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `iddepartamento` int(2) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`iddepartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'San Salvador'),(2,'San Vicente'),(3,'Santa Ana'),(4,'Sonsonate'),(5,'Usulut?n'),(6,'San Miguel'),(7,'Moraz?n'),(8,'La Uni?n'),(9,'La Libertad'),(10,'Chalatenango'),(11,'Cuscatl?n'),(12,'Ahuachap?n'),(13,'La Paz'),(14,'Caba?as'),(21,'San salvador'),(22,'nose2'),(23,'Algo mas');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financiamiento`
--

DROP TABLE IF EXISTS `financiamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `financiamiento` (
  `idp` varchar(5) NOT NULL,
  `proyecto` varchar(6) NOT NULL,
  `sociedadN` int(5) NOT NULL,
  `financiamiento` double NOT NULL,
  `fechaIngreso` date NOT NULL,
  `Detalles` varchar(200) NOT NULL,
  PRIMARY KEY (`idp`),
  KEY `sociedadN` (`sociedadN`),
  KEY `proyecto` (`proyecto`),
  CONSTRAINT `financiamiento_ibfk_1` FOREIGN KEY (`sociedadN`) REFERENCES `sociedad` (`id`),
  CONSTRAINT `financiamiento_ibfk_2` FOREIGN KEY (`proyecto`) REFERENCES `proyectos` (`idproyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financiamiento`
--

LOCK TABLES `financiamiento` WRITE;
/*!40000 ALTER TABLE `financiamiento` DISABLE KEYS */;
INSERT INTO `financiamiento` VALUES ('AA937','REC29',1111,6653.44,'2018-03-21','CDFSFDS');
/*!40000 ALTER TABLE `financiamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero` (
  `idg` int(1) NOT NULL,
  `nombre` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'Masculino'),(2,'Femenino');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugar`
--

DROP TABLE IF EXISTS `lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lugar` (
  `idlugar` varchar(6) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `municipio` int(3) NOT NULL,
  `departamento` int(2) NOT NULL,
  `categoria` int(2) NOT NULL,
  `coordenadas` varchar(80) DEFAULT NULL,
  `latitud` varchar(40) DEFAULT NULL,
  `longitud` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`idlugar`),
  KEY `municipio` (`municipio`),
  KEY `departamento` (`departamento`),
  KEY `categoria` (`categoria`),
  CONSTRAINT `lugar_ibfk_1` FOREIGN KEY (`municipio`) REFERENCES `municipio` (`idmunicipio`),
  CONSTRAINT `lugar_ibfk_2` FOREIGN KEY (`departamento`) REFERENCES `departamento` (`iddepartamento`),
  CONSTRAINT `lugar_ibfk_3` FOREIGN KEY (`categoria`) REFERENCES `lugarcategoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugar`
--

LOCK TABLES `lugar` WRITE;
/*!40000 ALTER TABLE `lugar` DISABLE KEYS */;
INSERT INTO `lugar` VALUES ('1','Parroquial',1,1,1,'poraqui 010101122','-101010','-101010'),('10','Itsmania',1,1,1,'poraqui 010101122','-101010','-101010'),('11','Las Vegas',1,1,1,'poraqui 010101122','-101010','-101010'),('12','Saprissa',197,1,1,'poraqui 010101122','-101010','-101010'),('13','CRS',1,1,1,'poraqui 010101122','-101010','-101010'),('2','C.R Calle Real',1,1,1,'poraqui 010101122','-101010','-101010'),('3','C.E. Milingo',200,2,2,'Latitud: -101010.0 Longitud: -101010.0','-101010.0','-101010.0'),('4','Dolores Medina',1,1,1,'poraqui 010101122','-101010','-101010'),('5','C.E. San Antoni',1,1,1,'poraqui 010101122','-101010','-101010'),('6','San Fernando',1,1,1,'poraqui 010101122','-101010','-101010'),('7','San Antonio',1,1,1,'poraqui 010101122','-101010','-101010'),('8','Habitad Confien',1,1,1,'poraqui 010101122','-101010','-101010'),('9','INCD',1,1,1,'poraqui 010101122','-101010','-101010'),('CRU28','Cruz Roja',179,1,1,'Latitud: -93442.0 Longitud: 1.0','-101010','-101010'),('KOD11N','KODIGO',229,4,2,'Latitud: -88.936085 Longitud: -88.936085',NULL,NULL),('LA324','La escuelita',167,6,2,'poraqui 010101122','-101010','-101010'),('PAP26','PAx2',202,2,2,'poraqui 010101122','-101010','-101010');
/*!40000 ALTER TABLE `lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugarcategoria`
--

DROP TABLE IF EXISTS `lugarcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lugarcategoria` (
  `id` int(2) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugarcategoria`
--

LOCK TABLES `lugarcategoria` WRITE;
/*!40000 ALTER TABLE `lugarcategoria` DISABLE KEYS */;
INSERT INTO `lugarcategoria` VALUES (1,'canton'),(2,'escuela');
/*!40000 ALTER TABLE `lugarcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugarproyecto`
--

DROP TABLE IF EXISTS `lugarproyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lugarproyecto` (
  `idl` varchar(6) NOT NULL,
  `idlp` varchar(6) NOT NULL,
  `idlugar` varchar(6) NOT NULL,
  `fechainicio` date NOT NULL,
  `fechafinal` date NOT NULL,
  `encargadoproyecto` varchar(6) NOT NULL,
  PRIMARY KEY (`idl`),
  KEY `idlugar` (`idlugar`),
  KEY `lugarproyecto_ibfk_3` (`encargadoproyecto`),
  KEY `idlp` (`idlp`) USING BTREE,
  CONSTRAINT `lugarproyecto_ibfk_1` FOREIGN KEY (`idlp`) REFERENCES `proyectos` (`idproyecto`),
  CONSTRAINT `lugarproyecto_ibfk_2` FOREIGN KEY (`idlugar`) REFERENCES `lugar` (`idlugar`),
  CONSTRAINT `lugarproyecto_ibfk_3` FOREIGN KEY (`encargadoproyecto`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugarproyecto`
--

LOCK TABLES `lugarproyecto` WRITE;
/*!40000 ALTER TABLE `lugarproyecto` DISABLE KEYS */;
INSERT INTO `lugarproyecto` VALUES ('LA2575','LA223','11','2018-02-07','2018-02-01','JUA1'),('LA2868','LA223','2','2018-11-20','2018-11-22','JUA1'),('LA2882','LA223','10','2018-11-01','2018-11-15','ARM28'),('LA3REC','REC29','LA324','2018-03-01','2018-03-15','JUA1'),('REC992','REC29','11','2018-02-14','2018-03-02','WAL123');
/*!40000 ALTER TABLE `lugarproyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipio` (
  `idmunicipio` int(3) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `departamento` int(2) NOT NULL,
  PRIMARY KEY (`idmunicipio`),
  KEY `departamento` (`departamento`),
  CONSTRAINT `municipio_ibfk_1` FOREIGN KEY (`departamento`) REFERENCES `departamento` (`iddepartamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` VALUES (1,'Ahuachap?n',12),(2,'Apaneca',12),(3,'Atiquizaya',12),(4,'Concepci?n de Ataco',12),(5,'El Refugio',12),(6,'Guaymango',12),(7,'Jujutla',12),(8,'San Francisco Men?ndez',12),(9,'San Lorenzo',12),(10,'San Pedro Puxtla',12),(11,'Tacuba',12),(12,'Tur?n',12),(13,'Sensuntepeque',14),(14,'Cinquera',14),(15,'Dolores',14),(16,'Guacotecti',14),(17,'Ilobasco',14),(18,'Jutiapa',14),(19,'San Isidro',14),(20,'Tejutepeque',14),(21,'Victoria',14),(22,'Chalatenango',10),(23,'Agua Caliente',10),(24,'Arcatao',10),(25,'Azacualpa',10),(26,'Cancasque',10),(27,'Cital?',10),(28,'Comalapa',10),(29,'Concepci?n Quezaltepeque',10),(30,'Dulce Nombre de Mar?a',10),(31,'El Carrizal',10),(32,'El Para?so',10),(33,'La Laguna',10),(34,'La Palma',10),(35,'La Reina',10),(36,'Las Flores',10),(37,'Las Vueltas',10),(38,'Nombre de Jes?s',10),(39,'Nueva Concepci?n',10),(40,'Nueva Trinidad',10),(41,'Ojos de Agua',10),(42,'Potonico',10),(43,'San Antonio de la Cruz',10),(44,'San Antonio Los Ranchos',10),(45,'San Fernando',10),(46,'San Francisco Lempa',10),(47,'San Francisco Moraz?n',10),(48,'San Ignacio',10),(49,'San Isidro Labrador',10),(50,'San Luis del Carmen',10),(51,'San Miguel de Mercedes',10),(52,'San Rafael',10),(53,'Santa Rita',10),(54,'Tejutla',10),(55,'Cojutepeque',11),(56,'Candelaria',11),(57,'El Carmen',11),(58,'El Rosario',11),(59,'Monte San Juan',11),(60,'Oratorio de Concepci?n',11),(61,'San Bartolom? Perulap?a',11),(62,'San Crist?bal',11),(63,'San Jos? Guayabal',11),(64,'San Pedro Perulap?n',11),(65,'San Rafael Cedros',11),(66,'San Ram?n',11),(67,'Santa Cruz Analquito',11),(68,'Santa Cruz Michapa',11),(69,'Suchitoto',11),(70,'Tenancingo',11),(71,'Santa Tecla',9),(72,'Antiguo Cuscatl?n',9),(73,'Chiltiup?n',9),(74,'Ciudad Arce',9),(75,'Col?n',9),(76,'Comasagua',9),(77,'Huiz?car',9),(78,'Jayaque',9),(79,'Jicalapa',9),(80,'La Libertad',9),(81,'Nuevo Cuscatl?n',9),(82,'Quezaltepeque',9),(83,'San Juan Opico',9),(84,'Sacacoyo',9),(85,'San Jos? Villanueva',9),(86,'San Mat?as',9),(87,'San Pablo Tacachico',9),(88,'Talnique',9),(89,'Tamanique',9),(90,'Teotepeque',9),(91,'Tepecoyo',9),(92,'Zaragoza',9),(93,'Zacatecoluca',13),(94,'Cuyultit?n',13),(95,'El Rosario',13),(96,'Jerusal?n',13),(97,'Mercedes La Ceiba',13),(98,'Olocuilta',13),(99,'Para?so de Osorio',13),(100,'San Antonio Masahuat',13),(101,'San Emigdio',13),(102,'San Francisco Chinameca',13),(103,'San Pedro Masahuat',13),(104,'San Juan Nonualco',13),(105,'San Juan Talpa',13),(106,'San Juan Tepezontes',13),(107,'San Luis La Herradura',13),(108,'San Luis Talpa',13),(109,'San Miguel Tepezontes',13),(110,'San Pedro Nonualco',13),(111,'San Rafael Obrajuelo',13),(112,'Santa Mar?a Ostuma',13),(113,'Santiago Nonualco',13),(114,'Tapalhuaca',13),(115,'La Uni?n',8),(116,'Anamor?s',8),(117,'Bol?var',8),(118,'Concepci?n de Oriente',8),(119,'Conchagua',8),(120,'El Carmen',8),(121,'El Sauce',8),(122,'Intipuc?',8),(123,'Lislique',8),(124,'Meanguera del Golfo',8),(125,'Nueva Esparta',8),(126,'Pasaquina',8),(127,'Polor?s',8),(128,'San Alejo',8),(129,'San Jos?',8),(130,'Santa Rosa de Lima',8),(131,'Yayantique',8),(132,'Yucuaiqu?n',8),(133,'San Francisco Gotera',7),(134,'Arambala',7),(135,'Cacaopera',7),(136,'Chilanga',7),(137,'Corinto',7),(138,'Delicias de Concepci?n',7),(139,'El Divisadero',7),(140,'El Rosario',7),(141,'Gualococti',7),(142,'Guatajiagua',7),(143,'Joateca',7),(144,'Jocoaitique',7),(145,'Jocoro',7),(146,'Lolotiquillo',7),(147,'Meanguera',7),(148,'Osicala',7),(149,'Perqu?n',7),(150,'San Carlos',7),(151,'San Fernando',7),(152,'San Isidro',7),(153,'San Sim?n',7),(154,'Sensembra',7),(155,'Sociedad',7),(156,'Torola',7),(157,'Yamabal',7),(158,'Yoloaiqu?n',7),(159,'San Miguel',6),(160,'Carolina',6),(161,'Chapeltique',6),(162,'Chinameca',6),(163,'Chirilagua',6),(164,'Ciudad Barrios',6),(165,'Comacar?n',6),(166,'El Tr?nsito',6),(167,'Lolotique',6),(168,'Moncagua',6),(169,'Nueva Guadalupe',6),(170,'Nuevo Ed?n de San Juan',6),(171,'Quelepa',6),(172,'San Antonio',6),(173,'San Gerardo',6),(174,'San Jorge',6),(175,'San Luis de la Reina',6),(176,'San Rafael Oriente',6),(177,'Sesori',6),(178,'Uluazapa',6),(179,'San Salvador',1),(180,'Aguilares',1),(181,'Apopa',1),(182,'Ayutuxtepeque',1),(183,'Cuscatancingo',1),(184,'Delgado',1),(185,'El Paisnal',1),(186,'Guazapa',1),(187,'Ilopango',1),(188,'Mejicanos',1),(189,'Nejapa',1),(190,'Panchimalco',1),(191,'Rosario de Mora',1),(192,'San Marcos',1),(193,'San Mart?n',1),(194,'Santiago Texacuangos',1),(195,'Santo Tom?s',1),(196,'Soyapango',1),(197,'Tonacatepeque',1),(198,'San Vicente',2),(199,'Apastepeque',2),(200,'Guadalupe',2),(201,'San Cayetano Istepeque',2),(202,'San Esteban Catarina',2),(203,'San Ildefonso',2),(204,'San Lorenzo',2),(205,'San Sebasti?n',2),(206,'Santa Clara',2),(207,'Santo Domingo',2),(208,'Tecoluca',2),(209,'Tepetit?n',2),(210,'Verapaz',2),(211,'Santa Ana (cabecera departamental)',3),(212,'Candelaria de la Frontera',3),(213,'Chalchuapa',3),(214,'Coatepeque',3),(215,'El Congo',3),(216,'El Porvenir',3),(217,'Masahuat',3),(218,'Metap?n',3),(219,'San Antonio Pajonal',3),(220,'San Sebasti?n Salitrillo',3),(221,'Santa Rosa Guachipil?n',3),(222,'Santiago de la Frontera',3),(223,'Texistepeque',3),(224,'Sonsonate',4),(225,'Acajutla',4),(226,'Armenia',4),(227,'Caluco',4),(228,'Cuisnahuat',4),(229,'Izalco',4),(230,'Juay?a',4),(231,'Nahuizalco',4),(232,'Nahulingo',4),(233,'Salcoatit?n',4),(234,'San Antonio del Monte',4),(235,'San Juli?n',4),(236,'Santa Catarina Masahuat',4),(237,'Santa Isabel Ishuat?n',4),(238,'Santo Domingo de Guzm?n',4),(239,'Sonzacate',4),(240,'Usulut?n',5),(241,'Alegr?a',5),(242,'Berl?n',5),(243,'California',5),(244,'Concepci?n Batres',5),(245,'El Triunfo',5),(246,'Ereguayqu?n',5),(247,'Estanzuelas',5),(248,'Jiquilisco',5),(249,'Jucuapa',5),(250,'Jucuar?n',5),(251,'Mercedes Uma?a',5),(252,'Nueva Granada',5),(253,'Ozatl?n',5),(254,'Puerto El Triunfo',5),(255,'San Agust?n',5),(256,'San Buenaventura',5),(257,'San Dionisio',5),(258,'San Francisco Javier',5),(259,'Santa Elena',5),(260,'Santa Mar?a',5),(261,'Santiago de Mar?a',5),(262,'Tecap?n',5);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proyectos` (
  `idproyecto` varchar(6) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `des` varchar(120) DEFAULT NULL,
  `categoria` int(2) NOT NULL,
  `sociedadN` int(5) NOT NULL,
  `seccionales` int(2) NOT NULL,
  `encargado` varchar(6) NOT NULL,
  `objetivos` varchar(300) DEFAULT NULL,
  `indicadores` varchar(100) DEFAULT NULL,
  `hipotesis` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idproyecto`),
  KEY `proyectos_ibfk_1` (`categoria`),
  KEY `proyectos_ibfk_2` (`sociedadN`),
  KEY `proyectos_ibfk_3` (`seccionales`),
  KEY `proyectos_ibfk_4` (`encargado`),
  CONSTRAINT `proyectos_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`idcategoria`),
  CONSTRAINT `proyectos_ibfk_2` FOREIGN KEY (`sociedadN`) REFERENCES `sociedad` (`id`),
  CONSTRAINT `proyectos_ibfk_3` FOREIGN KEY (`seccionales`) REFERENCES `municipio` (`idmunicipio`),
  CONSTRAINT `proyectos_ibfk_4` FOREIGN KEY (`encargado`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos`
--

LOCK TABLES `proyectos` WRITE;
/*!40000 ALTER TABLE `proyectos` DISABLE KEYS */;
INSERT INTO `proyectos` VALUES ('LA223','Fortalecimiento de resilencia','fsdf',2,1,179,'ACD123',NULL,NULL,NULL),('REC29','Reconstruccion','dfsdf',3,1,179,'JUA1','Una mejor infraestructura','Mejoramiento en el turismo','Radica en...'),('REC30','Reconstruccion2','dfsdf',2,1,179,'JUA1',NULL,NULL,NULL);
/*!40000 ALTER TABLE `proyectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sociedad`
--

DROP TABLE IF EXISTS `sociedad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sociedad` (
  `id` int(5) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `iniciales` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sociedad`
--

LOCK TABLES `sociedad` WRITE;
/*!40000 ALTER TABLE `sociedad` DISABLE KEYS */;
INSERT INTO `sociedad` VALUES (1,'ADE000','ADE00'),(60,'El encuentro','EEE'),(80,'INSAFORD','INSA'),(1111,'ss0','ss');
/*!40000 ALTER TABLE `sociedad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipou`
--

DROP TABLE IF EXISTS `tipou`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipou` (
  `idtipou` int(1) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `idusuario` varchar(30) NOT NULL,
  PRIMARY KEY (`idtipou`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipou`
--

LOCK TABLES `tipou` WRITE;
/*!40000 ALTER TABLE `tipou` DISABLE KEYS */;
INSERT INTO `tipou` VALUES (1,'Coordinador','ACD123r'),(2,'Encargado','WAL123'),(3,'Administrador','sfdsdf');
/*!40000 ALTER TABLE `tipou` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idusuario` varchar(6) NOT NULL,
  `nombres` varchar(35) NOT NULL,
  `pass` varchar(30) NOT NULL,
  `tipousuario` int(1) NOT NULL,
  `genero` int(1) NOT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `tipousuario` (`tipousuario`),
  KEY `genero` (`genero`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`tipousuario`) REFERENCES `tipou` (`idtipou`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`genero`) REFERENCES `genero` (`idg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('ACD123','Cristofer','12345',1,1,1),('ADM123','Master','12345',3,1,0),('ARM28','Armando','123456',2,1,0),('JUA1','Juan Carlos','12345',2,1,0),('WAL123','Waldo2','12345',2,1,0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-16  7:49:57
