create database USER_DB;

use USER_DB;

CREATE USER 'karsmarasUsers'@'%' IDENTIFIED BY 'karsmaras';
GRANT ALL PRIVILEGES  ON *.* TO 'karsmarasUsers'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE TABLE role (
	id INT(11) NOT NULL,
	description VARCHAR(255) NULL DEFAULT NULL,
	name VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (id)
)
COLLATE='latin1_swedish_ci'
ENGINE=MyISAM;

INSERT INTO role (id,Name,Description) VALUES (1,'PLATFORM_ADMIN','Administrador de la plataforma de Karsmaras');
INSERT INTO role (id,Name,Description) VALUES (2,'CHAMPIONSHIP_ADMIN','Administrador de un campeonato en la plataforma Karsmaras');
INSERT INTO role (id,Name,Description) VALUES (3,'DRIVER','Perfil de piloto en la plataforma Karsmaras');


CREATE TABLE user (
	id INT(11) NOT NULL,
	championship_id INT(11) NOT NULL,
	driver_id INT(11) NOT NULL,
	password VARCHAR(255) NULL DEFAULT NULL,
	username VARCHAR(255) NULL DEFAULT NULL,
	role_id INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (id),
	INDEX FKn82ha3ccdebhokx3a8fgdqeyy (role_id)
)
COLLATE='latin1_swedish_ci'
ENGINE=MyISAM;

CREATE TABLE friends (
	id INT(11) NOT NULL,
	user INT(11) NOT NULL,
	friend INT(11) NOT NULL,
	INDEX FKh7gkrkxduciup4pa9p00wpf8 (user),
	INDEX FKho491jbra6shcbrjej3jqtn18 (id),
	INDEX FKbxj3owiw2d777m0gkhp6a3rv3 (friend)
)
COLLATE='latin1_swedish_ci'
ENGINE=MyISAM;
