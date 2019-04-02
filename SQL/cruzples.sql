-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-04-2019 a las 18:16:20
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cruzples`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoriaintentos`
--

CREATE TABLE `categoriaintentos` (
  `codintentos` int(2) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoriaintentos`
--

INSERT INTO `categoriaintentos` (`codintentos`, `nombre`) VALUES
(1, 'nombre'),
(2, 'Perfil actualizado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consolidatorpo`
--

CREATE TABLE `consolidatorpo` (
  `codigocon` int(5) NOT NULL,
  `codigoPO` int(5) NOT NULL,
  `physicalgoal` int(5) NOT NULL,
  `trimestre` int(1) NOT NULL,
  `year` int(4) NOT NULL,
  `planificado` int(5) NOT NULL,
  `ejecutado` int(5) DEFAULT NULL,
  `comentario` varchar(150) DEFAULT NULL,
  `rowspan` int(3) DEFAULT NULL,
  `rowspan2` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `consolidatorpo`
--

INSERT INTO `consolidatorpo` (`codigocon`, `codigoPO`, `physicalgoal`, `trimestre`, `year`, `planificado`, `ejecutado`, `comentario`, `rowspan`, `rowspan2`) VALUES
(5, 3, 2, 0, 2014, 50, 12, 'Comentariodeprueba', 1, 1),
(6, 2, 2, 3, 2019, 200, 120, 'heheeh', 1, 2),
(7, 2, 2, 2, 2018, 90, 75, 'hola', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `codgenero` int(1) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`codgenero`, `nombre`) VALUES
(1, 'Masculino'),
(2, 'Femenino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `indicatorpo`
--

CREATE TABLE `indicatorpo` (
  `codigoind` int(3) NOT NULL,
  `detalle` varchar(150) NOT NULL,
  `tipou` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `indicatorpo`
--

INSERT INTO `indicatorpo` (`codigoind`, `detalle`, `tipou`) VALUES
(1, 'Cantidad de  planes elaborados e implementados del sistema de planificación a desastres ', 0),
(2, 'Cantidad de documentos que fortalezcan la coordinación y el trabajo en gestión de riesgos con otras instituciones publicas y privadas', 0),
(3, 'Cantidad de vehículos (Ambulancias 4x4) puestos a disposición a las seccionales para responder a las emergencias de sus zonas de cobertura', 0),
(4, 'Cantidad de radios digitales con los que cuentan las seccionales para conectarse con el sistema nacional', 0),
(5, 'Cantidad de nuevas repetidoras disponibles para CRS y tener una cobertura del 90% del territorio nacional.', 0),
(6, 'Cantidad de informes oportunos y con calidad que se emiten de situación a nivel nacional, informes especiales de desastres y crisis o en forma previa ', 0),
(7, 'Cantidad de Informes de tendencias estadísticas oportunos y de calidad que se emiten mensuales de tendencias estadísticas sobre emergencias y desastre', 0),
(8, 'Inventario de insumos humanitarios  para 1500 familias están disponibles y reemplazados cuando estos son usados.', 0),
(9, 'Cantidad de comunidades vulnerables que crean o actualizan sus planes comunales de preparación y respuesta a emergencias y desastres', 0),
(10, 'Cantidad de escuelas  vulnerables crean o actualizan sus planes escolares de preparación y respuesta a emergencias y desastres con el apoyo de las Sec', 0),
(11, '# de voluntarios/personal capacitados.', 0),
(12, '# de familias intervenidas', 0),
(13, '# de seccionales ejecutan acciones de protección al medioambiente.', 0),
(14, '# de servicios de calidad brindados                                                               ', 0),
(15, 'Incremento porcentual de la satisfacción de la atención a los usuarios.', 0),
(16, '# personal capacitado', 0),
(17, '# de paramédicos contratados de planta en horas diurnas.', 0),
(18, '# de VICITIS cuentan con personal capacitado, con recursos de información y educación para la salud y con dotación de condones en cantidad y calidad n', 0),
(19, 'Cantidad de nuevas empresas e instituciones que donan', 0),
(20, 'Cantidad de nuevos donantes', 0),
(21, 'Porcentaje de incremento de la satisfacción de donantes respecto a la atención recibida', 0),
(22, 'Unidades de sangre colocadas en la red nacional', 0),
(23, 'Cantidad de Pruebas de laboratorio', 0),
(24, 'Participación con la red de hospitales nacionales', 0),
(25, 'Cantidad de voluntarios sensibilizados en inclusión social  ', 0),
(26, 'Cantidad de voluntarios capacitados y fortalecidos en temáticas de reducción de violencia y cultura de paz', 0),
(27, 'Cantidad de comunidades resilientes, fortalecidas en cultura de paz ', 0),
(28, 'Cantidad de proyectos que implementan componentes del Programa con énfasis en los grupos vulnerables prioritarios ', 0),
(29, 'Número de programas definidos por cuerpo filial y órganos de gobierno.', 0),
(30, '%  de voluntarios formados/capacitados      ', 0),
(31, 'Numero de seccionales con mejoras en su infraestructura/ mobiliario/equipo ', 0),
(32, '% de incremento real del voluntariado', 0),
(33, '% de voluntarios evaluados', 0),
(34, '% de voluntarios que participan activamente en actividades de riesgo, salud e inclusión social', 0),
(35, '% de voluntarios que se les ha dado reconocimiento', 0),
(36, '% de incremento  de los ingresos de Miembros Contribuyentes, Eventos y Capacitación ', 0),
(37, '% de implementación', 0),
(38, '% de reducción de costos', 0),
(39, '% de incremento de los recursos por las acciones del plan', 0),
(40, 'Unidad creada', 0),
(41, '% de la sistematización ', 0),
(42, '% de implementación de la Intranet ', 0),
(43, '% de inversión en infraestructura, mobiliario y equipo; según plan de mercadeo', 0),
(44, '% de implementación del plan ', 0),
(45, '% actualización de los procesos ', 0),
(46, 'Actualización del decreto', 0),
(47, 'Cantidad de casos resueltos a traves de intervención institucional con otros actores ', 0),
(48, 'Cantidad de propuestas comerciales', 0),
(49, 'Politica elaborada', 0),
(50, 'Porcentaje de recuperación de costos en cada proyecto', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intentos`
--

CREATE TABLE `intentos` (
  `codintentos` int(3) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `userintentos` varchar(6) NOT NULL,
  `categoria` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `intentos`
--

INSERT INTO `intentos` (`codintentos`, `nombre`, `userintentos`, `categoria`) VALUES
(1, 'Cambio de contraseña', 'ADM123', 2),
(2, 'Cambio de contraseña', 'ADM123', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `measurementunits`
--

CREATE TABLE `measurementunits` (
  `codigomea` int(3) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `especificaciones` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `measurementunits`
--

INSERT INTO `measurementunits` (`codigomea`, `nombre`, `especificaciones`) VALUES
(1, 'planes', NULL),
(2, 'convenios', NULL),
(3, 'Vehículos  4x4 ambulancias', NULL),
(4, 'Radios digitales', NULL),
(5, 'Cantidad de nuevas repetidoras', NULL),
(6, '% de Informes oportunos y de calidad', NULL),
(7, 'Cantidad de informes estadísticos oportu', NULL),
(8, 'kits', NULL),
(9, 'comunidades', NULL),
(10, 'escuelas', NULL),
(11, 'voluntarios/personal  capacitados', NULL),
(12, 'Familias', NULL),
(13, 'seccionales', NULL),
(14, 'Servicios', NULL),
(15, '% de incremento en la satisfacción en at', NULL),
(16, 'Personas capacitadas', NULL),
(17, 'Personal de planta en horas diurnas', NULL),
(18, 'VICITS', NULL),
(19, 'Cantidad de empresas', NULL),
(20, 'Numero de nuevos donantes', NULL),
(21, 'Porcentaje de incremento de satisfacción', NULL),
(22, 'Numero de unidades', NULL),
(23, 'Numero de pruebas realizadas', NULL),
(24, 'Porcentaje de participación en el Comité', NULL),
(25, 'Voluntarios ', NULL),
(26, 'NNAJ', NULL),
(27, 'Proyectos ', NULL),
(28, 'programa', NULL),
(29, '% de voluntarios', NULL),
(30, 'Seccionales ', NULL),
(31, '%', NULL),
(32, 'Cantidad', NULL),
(33, 'Decreto', NULL),
(34, 'Cantidad de casos', NULL),
(35, 'Propuesta', NULL),
(36, 'Politica', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notifications`
--

CREATE TABLE `notifications` (
  `codigonoti` int(7) NOT NULL,
  `descripcion` varchar(40) NOT NULL,
  `tipo` int(2) NOT NULL,
  `usuario` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `physical_goal`
--

CREATE TABLE `physical_goal` (
  `codigo` int(5) NOT NULL,
  `meta` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `physical_goal`
--

INSERT INTO `physical_goal` (`codigo`, `meta`) VALUES
(2, '100 AUTOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `po_table`
--

CREATE TABLE `po_table` (
  `codigopo` int(5) NOT NULL,
  `actividad` varchar(80) NOT NULL,
  `indicatores` int(3) NOT NULL,
  `meta_global` int(5) NOT NULL,
  `unidad` int(3) NOT NULL,
  `encargado` varchar(6) NOT NULL,
  `areaest` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `po_table`
--

INSERT INTO `po_table` (`codigopo`, `actividad`, `indicatores`, `meta_global`, `unidad`, `encargado`, `areaest`) VALUES
(2, 'UNO', 1, 100, 1, 'ADM123', 1),
(3, 'dos', 23, 300, 16, 'ADM123', 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `privilegesuser`
--

CREATE TABLE `privilegesuser` (
  `codigopri` int(2) NOT NULL,
  `accesoA` int(3) NOT NULL,
  `user` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `results`
--

CREATE TABLE `results` (
  `codigo` int(5) NOT NULL,
  `resultadoEsperado` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `strategicareas`
--

CREATE TABLE `strategicareas` (
  `codigostr` int(2) NOT NULL,
  `nombre` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `strategicareas`
--

INSERT INTO `strategicareas` (`codigostr`, `nombre`) VALUES
(1, 'Gestión de Riesgos y Desastres'),
(2, 'SALUD, AGUA Y SANEAMIENTO'),
(3, 'INCLUSIÓN SOCIAL'),
(4, 'FORTALECIMIENTO DEL VOLUNTARIADO Y SECCIONALES'),
(5, 'Fortalecimiento y Modernización Institucional'),
(6, 'Sostenibilidad '),
(7, 'DIRECCIÓN GENERAL'),
(8, 'Garantizar el óptimo funcionamiento y desarrollo institucional a través de la ap'),
(9, 'PLANIFICACIÓN Y DESARROLLO INSTITUCIONAL'),
(10, 'Generar información y herramientas útiles y oportunas que contribuya al desarrol'),
(11, 'Mejorar la gestión de seguridad para minimizar los riesgos enfrentados por los m'),
(12, 'CENTRO DE SANGRE'),
(13, 'DEPARTAMENTO JURÍDICO'),
(14, 'ASESORIA ESTRATÉGICA'),
(15, 'RELACIONES PUBLICAS Y COMUNICACIONES'),
(16, 'COMUNICACIONES '),
(17, 'DIRECCIÓN DE GESTIÓN DE RECURSOS HUMANOS'),
(18, 'DEPARTAMENTO GESTIÓN DEL TALENTO HUMANO'),
(19, 'AUDITORIA INTERNA'),
(20, 'Disminuir los riesgos institucionales para el logro de los objetivos estratégico'),
(21, 'DIRECCIÓN DE OPERACIONES DE EMERGENCIAS'),
(22, 'fdfsdfsfsfd____');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `typenoti`
--

CREATE TABLE `typenoti` (
  `codigotypenoti` int(2) NOT NULL,
  `descripcion` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `codigouser` varchar(6) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `pass` varchar(20) NOT NULL,
  `tipou` int(1) NOT NULL,
  `correo` varchar(40) NOT NULL,
  `genero` int(1) DEFAULT NULL,
  `telefono` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`codigouser`, `nombre`, `apellidos`, `pass`, `tipou`, `correo`, `genero`, `telefono`) VALUES
('ADM123', 'JUAN', 'CARLOS', '12345', 1, 'correo@correo.com', 1, 71208113);

--
-- Disparadores `users`
--
DELIMITER $$
CREATE TRIGGER `after_changeusertry` AFTER UPDATE ON `users` FOR EACH ROW BEGIN INSERT INTO intentos VALUES (NULL,'Cambio de contraseña',new.codigouser,2);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usertype`
--

CREATE TABLE `usertype` (
  `codigousertype` int(1) NOT NULL,
  `tipo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usertype`
--

INSERT INTO `usertype` (`codigousertype`, `tipo`) VALUES
(1, 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoriaintentos`
--
ALTER TABLE `categoriaintentos`
  ADD PRIMARY KEY (`codintentos`);

--
-- Indices de la tabla `consolidatorpo`
--
ALTER TABLE `consolidatorpo`
  ADD PRIMARY KEY (`codigocon`),
  ADD KEY `pk_consolidatorpo` (`codigoPO`),
  ADD KEY `pk_physicalgoalcon` (`physicalgoal`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`codgenero`);

--
-- Indices de la tabla `indicatorpo`
--
ALTER TABLE `indicatorpo`
  ADD PRIMARY KEY (`codigoind`);

--
-- Indices de la tabla `intentos`
--
ALTER TABLE `intentos`
  ADD PRIMARY KEY (`codintentos`),
  ADD KEY `pk_intentoscategoria` (`categoria`),
  ADD KEY `pk_intentosUser` (`userintentos`);

--
-- Indices de la tabla `measurementunits`
--
ALTER TABLE `measurementunits`
  ADD PRIMARY KEY (`codigomea`);

--
-- Indices de la tabla `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`codigonoti`),
  ADD KEY `pk_notitypenoti` (`tipo`),
  ADD KEY `pk_notiuser` (`usuario`);

--
-- Indices de la tabla `physical_goal`
--
ALTER TABLE `physical_goal`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `po_table`
--
ALTER TABLE `po_table`
  ADD PRIMARY KEY (`codigopo`),
  ADD KEY `pk_pomeasurement` (`unidad`),
  ADD KEY `pk_poindicator` (`indicatores`),
  ADD KEY `pk_pomandated` (`encargado`),
  ADD KEY `´pk_postrategicAreas´` (`areaest`);

--
-- Indices de la tabla `privilegesuser`
--
ALTER TABLE `privilegesuser`
  ADD PRIMARY KEY (`codigopri`),
  ADD KEY `pk_privileges` (`user`);

--
-- Indices de la tabla `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `strategicareas`
--
ALTER TABLE `strategicareas`
  ADD PRIMARY KEY (`codigostr`);

--
-- Indices de la tabla `typenoti`
--
ALTER TABLE `typenoti`
  ADD PRIMARY KEY (`codigotypenoti`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`codigouser`),
  ADD KEY `pk_userusertype` (`tipou`),
  ADD KEY `pk_usergenero` (`genero`);

--
-- Indices de la tabla `usertype`
--
ALTER TABLE `usertype`
  ADD PRIMARY KEY (`codigousertype`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoriaintentos`
--
ALTER TABLE `categoriaintentos`
  MODIFY `codintentos` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `consolidatorpo`
--
ALTER TABLE `consolidatorpo`
  MODIFY `codigocon` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `codgenero` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `indicatorpo`
--
ALTER TABLE `indicatorpo`
  MODIFY `codigoind` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `intentos`
--
ALTER TABLE `intentos`
  MODIFY `codintentos` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `measurementunits`
--
ALTER TABLE `measurementunits`
  MODIFY `codigomea` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `notifications`
--
ALTER TABLE `notifications`
  MODIFY `codigonoti` int(7) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `physical_goal`
--
ALTER TABLE `physical_goal`
  MODIFY `codigo` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `po_table`
--
ALTER TABLE `po_table`
  MODIFY `codigopo` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `privilegesuser`
--
ALTER TABLE `privilegesuser`
  MODIFY `codigopri` int(2) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `results`
--
ALTER TABLE `results`
  MODIFY `codigo` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `strategicareas`
--
ALTER TABLE `strategicareas`
  MODIFY `codigostr` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `typenoti`
--
ALTER TABLE `typenoti`
  MODIFY `codigotypenoti` int(2) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usertype`
--
ALTER TABLE `usertype`
  MODIFY `codigousertype` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `consolidatorpo`
--
ALTER TABLE `consolidatorpo`
  ADD CONSTRAINT `pk_consolidatorpo` FOREIGN KEY (`codigoPO`) REFERENCES `po_table` (`codigopo`),
  ADD CONSTRAINT `pk_physicalgoalcon` FOREIGN KEY (`physicalgoal`) REFERENCES `physical_goal` (`codigo`);

--
-- Filtros para la tabla `intentos`
--
ALTER TABLE `intentos`
  ADD CONSTRAINT `pk_intentosUser` FOREIGN KEY (`userintentos`) REFERENCES `users` (`codigouser`),
  ADD CONSTRAINT `pk_intentoscategoria` FOREIGN KEY (`categoria`) REFERENCES `categoriaintentos` (`codintentos`);

--
-- Filtros para la tabla `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `pk_notitypenoti` FOREIGN KEY (`tipo`) REFERENCES `typenoti` (`codigotypenoti`),
  ADD CONSTRAINT `pk_notiuser` FOREIGN KEY (`usuario`) REFERENCES `users` (`codigouser`);

--
-- Filtros para la tabla `po_table`
--
ALTER TABLE `po_table`
  ADD CONSTRAINT `pk_poindicator` FOREIGN KEY (`indicatores`) REFERENCES `indicatorpo` (`codigoind`),
  ADD CONSTRAINT `pk_pomandated` FOREIGN KEY (`encargado`) REFERENCES `users` (`codigouser`),
  ADD CONSTRAINT `pk_pomeasurement` FOREIGN KEY (`unidad`) REFERENCES `measurementunits` (`codigomea`),
  ADD CONSTRAINT `´pk_postrategicAreas´` FOREIGN KEY (`areaest`) REFERENCES `strategicareas` (`codigostr`);

--
-- Filtros para la tabla `privilegesuser`
--
ALTER TABLE `privilegesuser`
  ADD CONSTRAINT `pk_privileges` FOREIGN KEY (`user`) REFERENCES `users` (`codigouser`);

--
-- Filtros para la tabla `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `pk_resultscon` FOREIGN KEY (`codigo`) REFERENCES `consolidatorpo` (`codigocon`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `pk_usergenero` FOREIGN KEY (`genero`) REFERENCES `genero` (`codgenero`),
  ADD CONSTRAINT `pk_userusertype` FOREIGN KEY (`tipou`) REFERENCES `usertype` (`codigousertype`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
