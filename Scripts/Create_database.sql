# CREATE DATABASE
CREATE DATABASE race_management_db;

# CREATE USER
CREATE USER 'racemgmt'@'%' IDENTIFIED BY 'RaceManagement';
GRANT EXECUTE, PROCESS, SELECT, SHOW DATABASES, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE TABLESPACE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE, CREATE USER, FILE, LOCK TABLES, RELOAD, REPLICATION CLIENT, REPLICATION SLAVE, SHUTDOWN, SUPER  ON *.* TO 'racemgmt'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

# CREATE TABLES
USE race_management_db;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS sancion;
DROP TABLE IF EXISTS vuelta;
DROP TABLE IF EXISTS resultado;
DROP TABLE IF EXISTS inscripcion;
DROP TABLE IF EXISTS equipo;
DROP TABLE IF EXISTS piloto;
DROP TABLE IF EXISTS sesion;
DROP TABLE IF EXISTS gran_premio;
DROP TABLE IF EXISTS campeonato;
DROP TABLE IF EXISTS puntuacion;
DROP TABLE IF EXISTS tipo_sesion;
DROP TABLE IF EXISTS reglamento;

CREATE TABLE `reglamento` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`n_entrenamientos` TINYINT UNSIGNED NOT NULL DEFAULT 0,
	`n_clasificaciones` TINYINT UNSIGNED NOT NULL DEFAULT 0,
	`n_carreras` TINYINT UNSIGNED NOT NULL DEFAULT 0,
	`n_pilotos` TINYINT UNSIGNED NOT NULL DEFAULT 0,
	`n_equipos` TINYINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`)
)
COMMENT='Tabla de gestion del reglamento de los campeonatos'
COLLATE='latin1_spanish_ci';

CREATE TABLE `tipo_sesion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`descripcion` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='Tabla para gestionar sesiones (Entrenamiento, Clasificacion, Carrera)'
COLLATE='latin1_spanish_ci';



CREATE TABLE `puntuacion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_reglamento` INT(11) NOT NULL,
	`posicion` TINYINT UNSIGNED NOT NULL,
	`puntos` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	`id_tipo_sesion` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `KEY` (`puntos`, `posicion`, `id_reglamento`, `id_tipo_sesion`),
	CONSTRAINT `FK__reglamento_puntuacion` FOREIGN KEY (`id_reglamento`) REFERENCES `reglamento` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT `FK__tiposesion_puntuacion` FOREIGN KEY (`id_tipo_sesion`) REFERENCES `tipo_sesion` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci';


CREATE TABLE `campeonato` (
	`id` INT(11) NOT NULL,
	`nombre` VARCHAR(255) NOT NULL,
	`descripcion` VARCHAR(255) NULL DEFAULT NULL,
	`temporada` VARCHAR(255) NOT NULL,
	`id_reglamento` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `KEY` (`nombre`, `temporada`),
	CONSTRAINT `FK__reglamento_campeonato` FOREIGN KEY (`id_reglamento`) REFERENCES `reglamento` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci';


CREATE TABLE `gran_premio` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_campeonato` INT(11) NOT NULL,
	`ubicacion` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK__campeonato` FOREIGN KEY (`id_campeonato`) REFERENCES `campeonato` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci';


CREATE TABLE `sesion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	`id_gp` INT(11) NOT NULL,
	`id_tipo_sesion` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_gran_premio_sesion` FOREIGN KEY (`id_gp`) REFERENCES `gran_premio` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FK_tipo_sesion_sesion` FOREIGN KEY (`id_tipo_sesion`) REFERENCES `tipo_sesion` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci';


CREATE TABLE `piloto` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(255) NOT NULL,
	`apellido` VARCHAR(255) NOT NULL,
	`apodo` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_spanish_ci';


CREATE TABLE `equipo` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(255) NOT NULL,
	`Alias` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_spanish_ci';

CREATE TABLE `inscripcion` (
	`id_campeonato` INT NOT NULL,
	`id_piloto` INT NOT NULL,
	`id_equipo` INT NOT NULL,
	CONSTRAINT `FK__campeonato_inscipcion` FOREIGN KEY (`id_campeonato`) REFERENCES `campeonato` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT `FK__piloto_inscripcion` FOREIGN KEY (`id_piloto`) REFERENCES `piloto` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT `FK__equipo_inscripcion` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci';

CREATE TABLE `resultado` (
	`id` INT(11) NOT NULL,
	`id_piloto` INT(11) NOT NULL,
	`id_sesion` INT(11) NOT NULL,
	`n_vueltas` SMALLINT UNSIGNED NOT NULL,
	`posicion` TINYINT UNSIGNED NOT NULL,
	`tiempo` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK__piloto` FOREIGN KEY (`id_piloto`) REFERENCES `piloto` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT `FK__sesion` FOREIGN KEY (`id_sesion`) REFERENCES `sesion` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci';

CREATE TABLE `vuelta` (
	`id` INT(11) NOT NULL,
	`id_resultado` INT(11) NOT NULL,
	`n_vuelta` SMALLINT UNSIGNED NOT NULL,
	`tiempo` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK__resultado` FOREIGN KEY (`id_resultado`) REFERENCES `resultado` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci';

CREATE TABLE `sancion` (
	`id` INT(11) NOT NULL,
	`id_resultado` INT(11) NOT NULL,
	`descripcion` TEXT NOT NULL,
	`puntos` TINYINT UNSIGNED NULL DEFAULT NULL,
	`tiempo` INT UNSIGNED NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK__resultado_sancion` FOREIGN KEY (`id_resultado`) REFERENCES `resultado` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci';

INSERT INTO `race_management_db`.`tipo_sesion` (`descripcion`) VALUES ('Entrenamiento');
INSERT INTO `race_management_db`.`tipo_sesion` (`descripcion`) VALUES ('Clasificacion');
INSERT INTO `race_management_db`.`tipo_sesion` (`descripcion`) VALUES ('Carrera');