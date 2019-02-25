

create table strategicAreas(
codigostr int (2) PRIMARY KEY AUTO_INCREMENT,
nombre varchar(80)NOT NULL
);

create table measurementUnits(
codigomea int (3)PRIMARY KEY AUTO_INCREMENT,
nombre varchar (40)NOT NULL,
especificaciones varchar(100)
);

create table indicatorpo(
codigoind int (3)PRIMARY KEY AUTO_INCREMENT,
detalle varchar (150)NOT NULL,
tipou int (1)NOT NULL
);

create table privilegesuser(
codigopri int (2)PRIMARY KEY AUTO_INCREMENT,
accesoA int (3)NOT NULL,
user varchar (6) NOT NULL
);

create table users(
codigouser varchar(6)PRIMARY KEY,
nombre varchar (50)NOT NULL,
apellidos varchar (50),
pass varchar(20)NOT NULL,
tipou int (1)NOT NULL
);

create table po_table(
codigopo int (5)PRIMARY KEY AUTO_INCREMENT,
actividad varchar(80)NOT NULL,
indicatores int (3)NOT NULL,
meta_global int (5)NOT NULL,
unidad int (3)NOT NULL,
encargado varchar (6)NOT NULL,
areaest int(2) NOT NULL
);

create table consolidatorpo(
codigocon int (5)PRIMARY KEY AUTO_INCREMENT,
codigoPO int (5)NOT NULL,
trimestre int (1)NOT NULL,
year int (4)NOT NULL,
planificado int (5)NOT NULL,
ejecutado int (5),
comentario varchar (150)
);

create table physical_goal (
codigo int (5)PRIMARY KEY AUTO_INCREMENT,
meta varchar (80)NOT NULL
);

create table results(
codigo int (5)PRIMARY KEY AUTO_INCREMENT,
resultadoEsperado varchar (40)
);


ALTER TABLE cruzples.po_table
 ADD CONSTRAINT pk_pomeasurement FOREIGN KEY (unidad) REFERENCES cruzples.measurementunits (codigomea) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.po_table
ADD CONSTRAINT pk_poindicator FOREIGN KEY (indicatores) REFERENCES cruzples.indicatorpo (codigoind) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.po_table
ADD CONSTRAINT pk_pomandated FOREIGN KEY (encargado) REFERENCES users(codigouser) ON UPDATE RESTRICT ON DELETE RESTRICT;


ALTER TABLE cruzples.po_table
ADD CONSTRAINT pk_postrategicAreas FOREIGN KEY (areaest) REFERENCES cruzples.StrategicAreas (codigostr) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.consolidatorpo
ADD CONSTRAINT pk_consolidatorpo FOREIGN KEY (codigoPO) REFERENCES cruzples.po_table (codigopo) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.physical_goal
ADD CONSTRAINT pk_physicalgoalcon FOREIGN KEY (codigo) REFERENCES consolidatorpo (codigopo) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.results
ADD CONSTRAINT pk_resultscon FOREIGN KEY (codigo) REFERENCES consolidatorpo (codigocon) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE TABLE usertype (
codigousertype int (1) PRIMARY KEY AUTO_INCREMENT,
tipo varchar (15) NOT NULL
);

CREATE TABLE notifications(
codigonoti int (7) PRIMARY KEY AUTO_INCREMENT,
descripcion varchar (40) NOT NULL,
tipo int (2) NOT NULL,
usuario varchar(6) NOT NULL
);

CREATE TABLE typenoti (
codigotypenoti int (2) PRIMARY KEY AUTO_INCREMENT,
descripcion varchar (40) NOT NULL
);

ALTER TABLE cruzples.users
ADD CONSTRAINT pk_userusertype FOREIGN KEY (tipou) REFERENCES usertype (codigousertype) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.privilegesuser
ADD CONSTRAINT pk_privileges FOREIGN KEY (user) REFERENCES users (codigouser) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.notifications
ADD CONSTRAINT pk_notitypenoti FOREIGN KEY (tipo) REFERENCES typenoti (codigotypenoti) ON UPDATE RESTRICT ON DELETE RESTRICT;  

ALTER TABLE cruzples.notifications
ADD CONSTRAINT pk_notiuser FOREIGN KEY (usuario) REFERENCES users (codigouser) ON UPDATE RESTRICT ON DELETE RESTRICT;


insert into usertype values (null,'admin');


ALTER TABLE `users` ADD `correo` VARCHAR(40) NULL AFTER `tipou`;
insert into users values ('ADM123','JUAN','CARLOS','12345',1,'correo@correo.com');
ALTER TABLE `users` ADD `genero` INT(1) NULL AFTER `correo`;
UPDATE `users` SET `genero` = '1' WHERE `users`.`codigouser` = 'ADM123';
ALTER TABLE `users` CHANGE `correo` `correo` VARCHAR(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;

create table genero(
codgenero int(1) PRIMARY KEY AUTO_INCREMENT,
nombre varchar (20) NOT NULL
);

insert into genero values(NULL,'Masculino');
insert into genero values(null,'Femenino');

alter table cruzples.users
ADD CONSTRAINT pk_usergenero FOREIGN KEY (genero) REFERENCES genero (codgenero);

create table categoriaIntentos(
codintentos int(2) PRIMARY KEY NOT NULL AUTO_INCREMENT,
nombre varchar (30) NOT NULL
);

INSERT INTO categoriaIntentos VALUES (null,'nombre');

CREATE table intentos(
codintentos int(3)PRIMARY KEY NOT NULL AUTO_INCREMENT,
nombre varchar(20)NOT NULL,
userintentos varchar(6) NOT NULL,
categoria int(2) NOT NULL
);

ALTER TABLE cruzples.intentos
ADD CONSTRAINT pk_intentoscategoria FOREIGN KEY (categoria) REFERENCES categoriaintentos (codintentos);
ALTER TABLE cruzples.intentos
ADD CONSTRAINT pk_intentosUser FOREIGN KEY (userintentos) REFERENCES users (codigouser);


ALTER TABLE `users` ADD `telefono` INT(8) NULL AFTER `genero`;
UPDATE `users` SET `telefono` = '71208113' WHERE `users`.`codigouser` = 'ADM123';

#triggers
INSERT INTO `categoriaintentos` (`codintentos`, `nombre`) VALUES ('2', 'Perfil actualizado');
DELIMITER //
CREATE TRIGGER `after_changeusertry` AFTER UPDATE ON `users` FOR EACH ROW BEGIN INSERT INTO intentos VALUES (NULL,'Cambio de contraseña',new.codigouser,2);
END //
DELIMITER ;


ALTER TABLE `intentos` CHANGE `codintentos` `codintentos` INT(3) NOT NULL;
ALTER TABLE `intentos` CHANGE `codintentos` `codintentos` INT(3) NOT NULL AUTO_INCREMENT;