create database USER_DB;

CREATE USER 'karsmarasUsers'@'%' IDENTIFIED BY 'karsmaras';
GRANT ALL PRIVILEGES  ON *.* TO 'karsmarasUsers'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE TABLE `ROLES` (
	`id_rol` INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(50) NOT NULL,
	`Description` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id_rol`)
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

INSERT INTO ROLES (Name,Description) VALUES ('PLATFORM_ADMIN','Administrador de la plataforma de Karsmaras');
INSERT INTO ROLES (Name,Description) VALUES ('CHAMPIONSHIP_ADMIN','Administrador de un campeonato en la plataforma Karsmaras');
INSERT INTO ROLES (Name,Description) VALUES ('DRIVER','Perfil de piloto en la plataforma Karsmaras');


CREATE TABLE `USERS` (
	`user_id` INT(10) NOT NULL AUTO_INCREMENT,
	`Username` VARCHAR(50) NOT NULL,
	`Password` VARCHAR(50) NOT NULL,
	`Driver_id` INT(10) NULL,
	`Championship_id` INT(10) NULL,
	`role_id` INT(10) NOT NULL,
	PRIMARY KEY (`user_id`),
	INDEX `USERNAME` (`Username`),
	INDEX `DRIVER` (`Driver_id`),
	INDEX `CHAMPIONSHIP` (`Championship_id`),
	CONSTRAINT `FK__roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id_rol`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;

CREATE TABLE `FRIENDS` (
	`friend1_id` INT(10) NOT NULL,
	`friend2_id` INT(10) NOT NULL,
	INDEX `friends1` (`friend1_id`),
	INDEX `friends2` (`friend2_id`),
	CONSTRAINT `FK_Friends1` FOREIGN KEY (`friend1_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FK_Friends2` FOREIGN KEY (`friend2_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='latin1_spanish_ci'
ENGINE=InnoDB;
