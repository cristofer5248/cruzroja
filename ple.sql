create database cruzples;
	use cruzples;

create table StrategicAreas(
codigostr int (2) PRIMARY KEY,
nombre varchar(80)NOT NULL
);

create table measurementUnits(
codigomea int (3)PRIMARY KEY,
nombre varchar (40)NOT NULL,
especificaciones varchar(100)
);

create table indicator(
codigoind int (3)PRIMARY KEY,
detalle varchar (150)NOT NULL,
tipou int (1)NOT NULL
);

create table privileges(
codigopri int (2)PRIMARY KEY,
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

create table PO(
codigopo int (5)PRIMARY KEY,
actividad varchar(80)NOT NULL,
indicatores int (3)NOT NULL,
meta_global int (5)NOT NULL,
unidad int (3)NOT NULL,
encargado varchar (6)NOT NULL
);

create table consolidator(
codigocon int (5)PRIMARY KEY,
codigoPO int (5)NOT NULL,
trimestre int (1)NOT NULL,
year int (4)NOT NULL,
planificado int (5)NOT NULL,
ejecutado int (5),
comentario varchar (150)
);

create table physical_goal (
codigo int (5)PRIMARY KEY,
meta varchar (80)NOT NULL
);

create table results(
codigo int (5)PRIMARY KEY,
resultadoEsperado varchar (40)
);


ALTER TABLE cruzples.po
 ADD CONSTRAINT pk_pomeasurement FOREIGN KEY (unidad) REFERENCES cruzples.measurementunits (codigomea) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.po
ADD CONSTRAINT pk_poindicator FOREIGN KEY (indicatores) REFERENCES cruzples.indicator (codigoind) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.po
ADD CONSTRAINT pk_pomandated FOREIGN KEY (encargado) REFERENCES users(codigouser) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.po
ADD CONSTRAINT pk_postrategicAreas FOREIGN KEY (unidad) REFERENCES cruzples.StrategicAreas (codigostr) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.consolidator
ADD CONSTRAINT pk_consolidatorpo FOREIGN KEY (codigoPO) REFERENCES cruzples.po (codigopo) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.physical_goal
ADD CONSTRAINT pk_physicalgoalcon FOREIGN KEY (codigo) REFERENCES consolidator (codigopo) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.results
ADD CONSTRAINT pk_resultscon FOREIGN KEY (codigo) REFERENCES consolidator (codigocon) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE TABLE usertype (
codigousertype int (1) PRIMARY KEY,
tipo varchar (15) NOT NULL
);

CREATE TABLE notifications(
codigonoti int (7) PRIMARY KEY,
descripcion varchar (40) NOT NULL,
tipo int (2) NOT NULL,
usuario varchar(6) NOT NULL
);

CREATE TABLE typenoti (
codigotypenoti int (2) PRIMARY KEY,
descripcion varchar (40) NOT NULL
);

ALTER TABLE cruzples.users
ADD CONSTRAINT pk_userusertype FOREIGN KEY (tipou) REFERENCES usertype (codigousertype) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.privileges
ADD CONSTRAINT pk_privileges FOREIGN KEY (user) REFERENCES users (codigouser) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE cruzples.notifications
ADD CONSTRAINT pk_notitypenoti FOREIGN KEY (tipo) REFERENCES typenoti (codigotypenoti) ON UPDATE RESTRICT ON DELETE RESTRICT;  

ALTER TABLE cruzples.notifications
ADD CONSTRAINT pk_notiuser FOREIGN KEY (usuario) REFERENCES users (codigouser) ON UPDATE RESTRICT ON DELETE RESTRICT;