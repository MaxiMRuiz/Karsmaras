-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.28 - MySQL Community Server (GPL)
-- SO del servidor:              Linux
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para race_management_db
DROP DATABASE IF EXISTS `race_management_db`;
CREATE DATABASE IF NOT EXISTS `race_management_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `race_management_db`;

-- Volcando estructura para tabla race_management_db.campeonato
DROP TABLE IF EXISTS `campeonato`;
CREATE TABLE IF NOT EXISTS `campeonato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `temporada` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `id_reglamento` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `KEY` (`nombre`,`temporada`),
  KEY `FK__reglamento_campeonato` (`id_reglamento`),
  CONSTRAINT `FK__reglamento_campeonato` FOREIGN KEY (`id_reglamento`) REFERENCES `reglamento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.campeonato: ~3 rows (aproximadamente)
DELETE FROM `campeonato`;
/*!40000 ALTER TABLE `campeonato` DISABLE KEYS */;
INSERT INTO `campeonato` (`id`, `nombre`, `descripcion`, `temporada`, `id_reglamento`) VALUES
	(1, 'F1', 'Campeonato Oficial de Formula 1 2020', '2020', 1),
	(2, 'F2', 'Campeonato Oficial de Formula 2 2019', '2019', 1),
	(4, 'F1', 'Campeonato Oficial de Formula 1 2019', '2019', 1),
	(5, 'F1', 'Campeonato Oficial de Formula 1 2018', '2018', 1);
/*!40000 ALTER TABLE `campeonato` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.equipo
DROP TABLE IF EXISTS `equipo`;
CREATE TABLE IF NOT EXISTS `equipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `Alias` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.equipo: ~9 rows (aproximadamente)
DELETE FROM `equipo`;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` (`id`, `Nombre`, `Alias`) VALUES
	(1, 'Scuderia Ferrari', 'Ferrari'),
	(2, 'Renault F1 Team', 'Renault'),
	(3, 'Mercedes-AMG Petronas Motorsport', 'Mercedes'),
	(4, 'McLaren F1 Team', 'McLaren'),
	(5, 'Rich Energy Haas F1 Team', 'Haas'),
	(6, 'Aston Martin Red Bull Racing', 'Red Bull'),
	(7, 'Red Bull Toro Rosso Honda', 'Toro Rosso'),
	(8, 'ROKiT Williams Racing', 'Williams'),
	(9, 'SportPesa Racing Point F1 Team', 'Racing Point'),
	(11, 'Alfa Romeo Racing', 'Alfa Romeo Sauber');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.gran_premio
DROP TABLE IF EXISTS `gran_premio`;
CREATE TABLE IF NOT EXISTS `gran_premio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_campeonato` int(11) NOT NULL,
  `ubicacion` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__campeonato` (`id_campeonato`),
  CONSTRAINT `FK__campeonato` FOREIGN KEY (`id_campeonato`) REFERENCES `campeonato` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.gran_premio: ~0 rows (aproximadamente)
DELETE FROM `gran_premio`;
/*!40000 ALTER TABLE `gran_premio` DISABLE KEYS */;
/*!40000 ALTER TABLE `gran_premio` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.inscripcion
DROP TABLE IF EXISTS `inscripcion`;
CREATE TABLE IF NOT EXISTS `inscripcion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_campeonato` int(11) NOT NULL,
  `id_piloto` int(11) NOT NULL,
  `id_equipo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__campeonato_inscipcion` (`id_campeonato`),
  KEY `FK__piloto_inscripcion` (`id_piloto`),
  KEY `FK__equipo_inscripcion` (`id_equipo`),
  CONSTRAINT `FK__campeonato_inscipcion` FOREIGN KEY (`id_campeonato`) REFERENCES `campeonato` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK__equipo_inscripcion` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK__piloto_inscripcion` FOREIGN KEY (`id_piloto`) REFERENCES `piloto` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.inscripcion: ~0 rows (aproximadamente)
DELETE FROM `inscripcion`;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
INSERT INTO `inscripcion` (`id`, `id_campeonato`, `id_piloto`, `id_equipo`) VALUES
	(2, 4, 2, 3),
	(3, 4, 3, 11),
	(4, 4, 4, 1),
	(5, 4, 5, 4),
	(6, 4, 7, 3),
	(7, 4, 9, 1),
	(8, 4, 10, 6),
	(9, 4, 16, 6),
	(10, 4, 14, 2),
	(11, 4, 15, 2),
	(12, 4, 11, 4),
	(13, 4, 12, 5),
	(14, 4, 13, 5),
	(15, 4, 17, 7),
	(16, 4, 18, 7),
	(17, 4, 19, 8),
	(18, 4, 22, 8),
	(19, 4, 21, 9),
	(20, 4, 20, 9),
	(21, 4, 23, 11);
/*!40000 ALTER TABLE `inscripcion` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.piloto
DROP TABLE IF EXISTS `piloto`;
CREATE TABLE IF NOT EXISTS `piloto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `apellido` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `apodo` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.piloto: ~5 rows (aproximadamente)
DELETE FROM `piloto`;
/*!40000 ALTER TABLE `piloto` DISABLE KEYS */;
INSERT INTO `piloto` (`id`, `nombre`, `apellido`, `apodo`) VALUES
	(1, 'Fernando', 'Alonso', 'ALO'),
	(2, 'Lewis', 'Hamilton', 'HAM'),
	(3, 'Kimi', 'Raikonen', 'RAI'),
	(4, 'Sebastian', 'Vettel', 'VET'),
	(5, 'Carlos', 'Sainz', 'SAI'),
	(7, 'Valteri', 'Bottas', 'BOT'),
	(9, 'Charles', 'Leclerc', 'LEC'),
	(10, 'Max', 'Verstappen', 'VER'),
	(11, 'Lando', 'Norris', 'NOR'),
	(12, 'Kevin', 'Magnussen', 'MAG'),
	(13, 'Romain', 'Grosgean', 'GRO'),
	(14, 'Niko', 'Hulkenberg', 'HUL'),
	(15, 'Daniel', 'Ricciardo', 'RIC'),
	(16, 'Alexander', 'Albon', 'ALB'),
	(17, 'Daniil', 'Kvyat', 'KVY'),
	(18, 'Pierre', 'Gasly', 'GAS'),
	(19, 'Robert', 'Kubica', 'KUB'),
	(20, 'Sergio', 'Perez', 'PER'),
	(21, 'Lance', 'Stroll', 'STR'),
	(22, 'George', 'Russell', 'RUS'),
	(23, 'Antonio', 'Giovinazzi', 'GIO');
/*!40000 ALTER TABLE `piloto` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.puntuacion
DROP TABLE IF EXISTS `puntuacion`;
CREATE TABLE IF NOT EXISTS `puntuacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_reglamento` int(11) NOT NULL,
  `posicion` tinyint(3) unsigned NOT NULL,
  `puntos` smallint(5) unsigned NOT NULL DEFAULT '0',
  `id_tipo_sesion` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `KEY` (`puntos`,`posicion`,`id_reglamento`,`id_tipo_sesion`),
  KEY `FK__reglamento_puntuacion` (`id_reglamento`),
  KEY `FK__tiposesion_puntuacion` (`id_tipo_sesion`),
  CONSTRAINT `FK__reglamento_puntuacion` FOREIGN KEY (`id_reglamento`) REFERENCES `reglamento` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK__tiposesion_puntuacion` FOREIGN KEY (`id_tipo_sesion`) REFERENCES `tipo_sesion` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.puntuacion: ~0 rows (aproximadamente)
DELETE FROM `puntuacion`;
/*!40000 ALTER TABLE `puntuacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `puntuacion` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.reglamento
DROP TABLE IF EXISTS `reglamento`;
CREATE TABLE IF NOT EXISTS `reglamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `n_entrenamientos` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `n_clasificaciones` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `n_carreras` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `n_pilotos` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `n_equipos` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla de gestion del reglamento de los campeonatos';

-- Volcando datos para la tabla race_management_db.reglamento: ~0 rows (aproximadamente)
DELETE FROM `reglamento`;
/*!40000 ALTER TABLE `reglamento` DISABLE KEYS */;
INSERT INTO `reglamento` (`id`, `n_entrenamientos`, `n_clasificaciones`, `n_carreras`, `n_pilotos`, `n_equipos`) VALUES
	(1, 3, 1, 1, 20, 10);
/*!40000 ALTER TABLE `reglamento` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.resultado
DROP TABLE IF EXISTS `resultado`;
CREATE TABLE IF NOT EXISTS `resultado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_piloto` int(11) NOT NULL,
  `id_sesion` int(11) NOT NULL,
  `n_vueltas` smallint(5) unsigned NOT NULL,
  `posicion` tinyint(3) unsigned NOT NULL,
  `tiempo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__piloto` (`id_piloto`),
  KEY `FK__sesion` (`id_sesion`),
  CONSTRAINT `FK__piloto` FOREIGN KEY (`id_piloto`) REFERENCES `piloto` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK__sesion` FOREIGN KEY (`id_sesion`) REFERENCES `sesion` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.resultado: ~0 rows (aproximadamente)
DELETE FROM `resultado`;
/*!40000 ALTER TABLE `resultado` DISABLE KEYS */;
/*!40000 ALTER TABLE `resultado` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.sancion
DROP TABLE IF EXISTS `sancion`;
CREATE TABLE IF NOT EXISTS `sancion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_resultado` int(11) NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci NOT NULL,
  `puntos` tinyint(3) unsigned DEFAULT NULL,
  `tiempo` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__resultado_sancion` (`id_resultado`),
  CONSTRAINT `FK__resultado_sancion` FOREIGN KEY (`id_resultado`) REFERENCES `resultado` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.sancion: ~0 rows (aproximadamente)
DELETE FROM `sancion`;
/*!40000 ALTER TABLE `sancion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sancion` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.sesion
DROP TABLE IF EXISTS `sesion`;
CREATE TABLE IF NOT EXISTS `sesion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_gp` int(11) NOT NULL,
  `id_tipo_sesion` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gran_premio_sesion` (`id_gp`),
  KEY `FK_tipo_sesion_sesion` (`id_tipo_sesion`),
  CONSTRAINT `FK_gran_premio_sesion` FOREIGN KEY (`id_gp`) REFERENCES `gran_premio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tipo_sesion_sesion` FOREIGN KEY (`id_tipo_sesion`) REFERENCES `tipo_sesion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.sesion: ~0 rows (aproximadamente)
DELETE FROM `sesion`;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.tipo_sesion
DROP TABLE IF EXISTS `tipo_sesion`;
CREATE TABLE IF NOT EXISTS `tipo_sesion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla para gestionar sesiones (Entrenamiento, Clasificacion, Carrera)';

-- Volcando datos para la tabla race_management_db.tipo_sesion: ~2 rows (aproximadamente)
DELETE FROM `tipo_sesion`;
/*!40000 ALTER TABLE `tipo_sesion` DISABLE KEYS */;
INSERT INTO `tipo_sesion` (`id`, `descripcion`) VALUES
	(1, 'Entrenamiento'),
	(2, 'Clasificacion'),
	(3, 'Carrera');
/*!40000 ALTER TABLE `tipo_sesion` ENABLE KEYS */;

-- Volcando estructura para tabla race_management_db.vuelta
DROP TABLE IF EXISTS `vuelta`;
CREATE TABLE IF NOT EXISTS `vuelta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_resultado` int(11) NOT NULL,
  `n_vuelta` smallint(5) unsigned NOT NULL,
  `tiempo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__resultado` (`id_resultado`),
  CONSTRAINT `FK__resultado` FOREIGN KEY (`id_resultado`) REFERENCES `resultado` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla race_management_db.vuelta: ~0 rows (aproximadamente)
DELETE FROM `vuelta`;
/*!40000 ALTER TABLE `vuelta` DISABLE KEYS */;
/*!40000 ALTER TABLE `vuelta` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
