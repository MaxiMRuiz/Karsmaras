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
DROP TABLE IF EXISTS sesion_gp;
DROP TABLE IF EXISTS gran_premio;
DROP TABLE IF EXISTS campeonato;
DROP TABLE IF EXISTS puntuacion;
DROP TABLE IF EXISTS sesion;
DROP TABLE IF EXISTS tipo_sesion;
DROP TABLE IF EXISTS reglamento;

CREATE TABLE `reglamento` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`descripcion` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_spanish_ci',
	`n_entrenamientos` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
	`n_clasificaciones` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
	`n_carreras` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
	`n_pilotos` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
	`n_equipos` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`descripcion`)
)
COMMENT='Tabla de gestion del reglamento de los campeonatos'
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `tipo_sesion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`descripcion` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	PRIMARY KEY (`id`)
)
COMMENT='Tabla para gestionar sesiones (Entrenamiento, Clasificacion, Carrera)'
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `sesion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`descripcion` VARCHAR(255) NOT NULL,
	`id_reglamento` INT(11) NOT NULL,
	`id_tipo_sesion` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`descripcion`, `id_reglamento`),
	INDEX `FK_sesion_tipo_sesion` (`id_tipo_sesion`),
	INDEX `FK_reglamento_puntuacion` (`id_reglamento`),
	CONSTRAINT `FK_sesion_reglamento` FOREIGN KEY (`id_reglamento`) REFERENCES `reglamento` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT `FK_sesion_tipo_sesion` FOREIGN KEY (`id_tipo_sesion`) REFERENCES `tipo_sesion` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE `puntuacion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`posicion` TINYINT(3) UNSIGNED NOT NULL,
	`puntos` SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
	`id_sesion` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`puntos`, `posicion`, `id_sesion`),
	INDEX `FK_puntuacion_sesion` (`id_sesion`),
	CONSTRAINT `FK_puntuacion_sesion` FOREIGN KEY (`id_sesion`) REFERENCES `sesion` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `campeonato` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	`descripcion` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_spanish_ci',
	`temporada` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	`id_reglamento` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`nombre`, `temporada`),
	INDEX `FK__reglamento_campeonato` (`id_reglamento`),
	CONSTRAINT `FK__reglamento_campeonato` FOREIGN KEY (`id_reglamento`) REFERENCES `reglamento` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;


CREATE TABLE `gran_premio` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_campeonato` INT(11) NOT NULL,
	`ubicacion` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`id_campeonato`, `ubicacion`),
	INDEX `FK__campeonato` (`id_campeonato`),
	CONSTRAINT `FK__campeonato` FOREIGN KEY (`id_campeonato`) REFERENCES `campeonato` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;


CREATE TABLE `sesion_gp` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`id_gp` INT(11) NOT NULL,
	`id_sesion` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_gran_premio_sesion_gp` (`id_gp`),
	INDEX `FK_tipo_sesion_sesion_gp` (`id_sesion`),
	CONSTRAINT `FK_gran_premio_sesion_gp` FOREIGN KEY (`id_gp`) REFERENCES `gran_premio` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FK_sesion_sesion_gp` FOREIGN KEY (`id_sesion`) REFERENCES `sesion` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `piloto` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	`apellido` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	`apodo` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	`password` VARCHAR(4096) NOT NULL COLLATE 'latin1_spanish_ci',
	`admin` TINYINT(1) NOT NULL DEFAULT '0',
	`jwk` VARCHAR(4096) NOT NULL COLLATE 'latin1_spanish_ci',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`apodo`)
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `equipo` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	`Alias` VARCHAR(255) NOT NULL COLLATE 'latin1_spanish_ci',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`Alias`)
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `inscripcion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_campeonato` INT(11) NOT NULL,
	`id_piloto` INT(11) NOT NULL,
	`id_equipo` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`id_campeonato`, `id_piloto`),
	INDEX `FK__campeonato_inscipcion` (`id_campeonato`),
	INDEX `FK__piloto_inscripcion` (`id_piloto`),
	INDEX `FK__equipo_inscripcion` (`id_equipo`),
	CONSTRAINT `FK__campeonato_inscipcion` FOREIGN KEY (`id_campeonato`) REFERENCES `campeonato` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT `FK__equipo_inscripcion` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT `FK__piloto_inscripcion` FOREIGN KEY (`id_piloto`) REFERENCES `piloto` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `resultado` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_inscripcion` INT(11) NOT NULL,
	`id_sesion_gp` INT(11) NOT NULL,
	`n_vueltas` SMALLINT(5) UNSIGNED NOT NULL,
	`tiempo` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`id_inscripcion`, `id_sesion_gp`),
	INDEX `FK_inscripcion` (`id_inscripcion`),
	INDEX `FK_sesion` (`id_sesion_gp`),
	CONSTRAINT `FK__inscripcion` FOREIGN KEY (`id_inscripcion`) REFERENCES `inscripcion` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT `FK_sesion_gp` FOREIGN KEY (`id_sesion_gp`) REFERENCES `sesiongp` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `vuelta` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_resultado` INT(11) NOT NULL,
	`n_vuelta` SMALLINT(5) UNSIGNED NOT NULL,
	`tiempo` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK__resultado` (`id_resultado`),
	CONSTRAINT `FK__resultado` FOREIGN KEY (`id_resultado`) REFERENCES `resultado` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `sancion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_resultado` INT(11) NOT NULL,
	`descripcion` TEXT NOT NULL COLLATE 'latin1_spanish_ci',
	`puntos` TINYINT(4) NULL DEFAULT NULL,
	`tiempo` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `KEY` (`id_resultado`, `descripcion`(100)),
	INDEX `FK__resultado_sancion` (`id_resultado`),
	CONSTRAINT `FK__resultado_sancion` FOREIGN KEY (`id_resultado`) REFERENCES `resultado` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

INSERT INTO `race_management_db`.`tipo_sesion` (`descripcion`) VALUES ('Entrenamiento');
INSERT INTO `race_management_db`.`tipo_sesion` (`descripcion`) VALUES ('Clasificacion');
INSERT INTO `race_management_db`.`tipo_sesion` (`descripcion`) VALUES ('Carrera');
INSERT INTO `race_management_db`.`equipo` (`Nombre`,`Alias`) VALUES ('Libre','LIBRE');