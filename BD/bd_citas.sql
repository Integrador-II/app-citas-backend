-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-12-2021 a las 04:55:15
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_citas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `idcita` int(11) NOT NULL,
  `estado` char(1) NOT NULL,
  `fecha_reserva` datetime NOT NULL,
  `idtipo_atencion` int(11) NOT NULL,
  `idpaciente` int(11) NOT NULL,
  `idmedico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`idcita`, `estado`, `fecha_reserva`, `idtipo_atencion`, `idpaciente`, `idmedico`) VALUES
(56, 'P', '2021-10-21 08:00:00', 2, 9, 1),
(57, 'P', '2021-10-22 08:00:00', 1, 9, 1),
(60, 'P', '2021-11-04 08:00:00', 1, 14, 8),
(61, 'P', '2021-11-05 08:00:00', 1, 14, 8),
(62, 'P', '2021-11-08 08:00:00', 1, 14, 8),
(63, 'P', '2021-12-02 08:00:00', 1, 15, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `idempleado` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(45) NOT NULL,
  `tipo_documento` char(2) NOT NULL,
  `numero_documento` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`idempleado`, `nombre`, `apellido_paterno`, `apellido_materno`, `tipo_documento`, `numero_documento`) VALUES
(1, 'Edwin', 'Quispe', 'Ramos', '01', '43459267'),
(2, 'Edwin', 'Quispe', 'Ramos', '01', '43459267'),
(3, 'Edwin', 'Quispe', 'Ramos', '01', '43459267'),
(4, 'Ronald', 'Torres', 'Ubillus', '01', '41239267');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad`
--

CREATE TABLE `especialidad` (
  `idespecialidad` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `especialidad`
--

INSERT INTO `especialidad` (`idespecialidad`, `nombre`) VALUES
(1, 'Medicina General'),
(2, 'Cirugía'),
(3, 'Cardiología'),
(4, 'Dermatología'),
(5, 'Neumología');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `idmedico` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `tipo_documento` char(2) NOT NULL,
  `numero_documento` varchar(12) NOT NULL,
  `idespecialidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`idmedico`, `nombre`, `apellido_paterno`, `apellido_materno`, `tipo_documento`, `numero_documento`, `idespecialidad`) VALUES
(1, 'Eduardo', 'Soto', 'Padilla', '01', '134549267', 1),
(2, 'Javier', 'Fuentes', 'Santiago', '01', '465549147', 2),
(3, 'Melva', 'Torres', 'Aguilar', '01', '495511140', 1),
(4, 'Edwin', 'Quispe', 'Ramos', '01', '43459267', 4),
(5, 'Siaden', 'Karina', 'Huiman', '01', '459454545', 2),
(6, 'Karina', 'Huiman', 'Siaden', '01', '495511140', 1),
(7, 'Karina', 'Huiman', 'Siaden', '01', '495511140', 1),
(8, 'Rodolfo', 'Espinoza', 'Siaden', '01', '78454789', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico_horario`
--

CREATE TABLE `medico_horario` (
  `idmedico_horario` int(11) NOT NULL,
  `hora_inicio` varchar(10) NOT NULL,
  `hora_fin` varchar(10) NOT NULL,
  `fecha` date NOT NULL,
  `idmedico` int(11) NOT NULL,
  `cantidad_atenciones` int(11) NOT NULL DEFAULT 0,
  `tienda_atencion` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `medico_horario`
--

INSERT INTO `medico_horario` (`idmedico_horario`, `hora_inicio`, `hora_fin`, `fecha`, `idmedico`, `cantidad_atenciones`, `tienda_atencion`) VALUES
(24, '08:00', '12:00', '2021-10-21', 1, 5, 15),
(25, '08:00', '12:00', '2021-10-21', 2, 5, 20),
(26, '08:00', '12:00', '2021-10-22', 1, 10, 20),
(27, '08:00', '12:00', '2021-10-22', 1, 10, 20),
(28, '08:00', '12:00', '2021-10-22', 1, 10, 20),
(29, '08:00', '12:00', '2021-11-04', 8, 10, 20),
(30, '08:00', '12:00', '2021-11-05', 8, 10, 20),
(31, '08:00', '12:00', '2021-11-06', 8, 10, 20),
(32, '08:00', '12:00', '2021-12-06', 8, 10, 20),
(33, '08:00', '12:00', '2021-11-08', 8, 10, 20),
(34, '08:00', '12:00', '2021-12-02', 8, 10, 20),
(35, '08:00', '12:00', '2021-12-02', 1, 10, 20),
(36, '08:00', '12:00', '2021-12-03', 1, 10, 20),
(37, '08:00', '12:00', '2021-12-03', 8, 10, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `idpaciente` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `tipo_documento` char(2) NOT NULL,
  `numero_documento` varchar(12) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `celular` char(9) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`idpaciente`, `nombre`, `apellido_paterno`, `apellido_materno`, `tipo_documento`, `numero_documento`, `direccion`, `celular`, `correo`) VALUES
(9, 'Edwin', 'Quispe', 'Ramos', '01', '43459267', NULL, '973868825', 'edwinqramos@gmail.com'),
(11, 'Eduardo', 'Sotil', 'Ramos', '01', '45459267', 'Los Ingenieros', NULL, NULL),
(14, 'Alberto', 'Rojas', 'Quiroz', '01', '479959267', 'Proceres', '98959599', 'rojas@yopmail.com'),
(15, 'Arturo', 'Contreras', 'Solis', '01', '43459260', NULL, '973868825', 'csolis@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_atencion`
--

CREATE TABLE `tipo_atencion` (
  `idtipo_atencion` int(11) NOT NULL,
  `tipo_atencion` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_atencion`
--

INSERT INTO `tipo_atencion` (`idtipo_atencion`, `tipo_atencion`) VALUES
(1, 'Presencial'),
(2, 'En línea'),
(3, 'Virtual'),
(4, 'Virtual');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`idcita`),
  ADD KEY `fk_cita_tipo_atencion1_idx` (`idtipo_atencion`),
  ADD KEY `fk_cita_paciente1_idx` (`idpaciente`),
  ADD KEY `fk_cita_medico1_idx` (`idmedico`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`idempleado`);

--
-- Indices de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  ADD PRIMARY KEY (`idespecialidad`);

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`idmedico`),
  ADD KEY `fk_medico_especialidad_idx` (`idespecialidad`);

--
-- Indices de la tabla `medico_horario`
--
ALTER TABLE `medico_horario`
  ADD PRIMARY KEY (`idmedico_horario`),
  ADD KEY `fk_medico_horario_medico1_idx` (`idmedico`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`idpaciente`),
  ADD UNIQUE KEY `tipo_num_doc_UNIQUE` (`tipo_documento`,`numero_documento`);

--
-- Indices de la tabla `tipo_atencion`
--
ALTER TABLE `tipo_atencion`
  ADD PRIMARY KEY (`idtipo_atencion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `idcita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `idempleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  MODIFY `idespecialidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `medico`
--
ALTER TABLE `medico`
  MODIFY `idmedico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `medico_horario`
--
ALTER TABLE `medico_horario`
  MODIFY `idmedico_horario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `idpaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `tipo_atencion`
--
ALTER TABLE `tipo_atencion`
  MODIFY `idtipo_atencion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `fk_cita_medico1` FOREIGN KEY (`idmedico`) REFERENCES `medico` (`idmedico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cita_paciente1` FOREIGN KEY (`idpaciente`) REFERENCES `paciente` (`idpaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cita_tipo_atencion1` FOREIGN KEY (`idtipo_atencion`) REFERENCES `tipo_atencion` (`idtipo_atencion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `medico`
--
ALTER TABLE `medico`
  ADD CONSTRAINT `fk_medico_especialidad` FOREIGN KEY (`idespecialidad`) REFERENCES `especialidad` (`idespecialidad`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `medico_horario`
--
ALTER TABLE `medico_horario`
  ADD CONSTRAINT `fk_medico_horario_medico1` FOREIGN KEY (`idmedico`) REFERENCES `medico` (`idmedico`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
