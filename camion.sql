-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-05-2019 a las 14:05:41
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
-- Base de datos: `camion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `combustible`
--

CREATE TABLE `combustible` (
  `idCombustible` int(11) NOT NULL,
  `idViaje` int(11) NOT NULL,
  `litros` double NOT NULL,
  `kilometros` int(11) NOT NULL,
  `precio` double NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugar`
--

CREATE TABLE `lugar` (
  `idLugar` int(11) NOT NULL,
  `ciudad` varchar(30) COLLATE utf8_bin NOT NULL,
  `direccion` varchar(50) COLLATE utf8_bin NOT NULL,
  `nDireccion` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `lugar`
--

INSERT INTO `lugar` (`idLugar`, `ciudad`, `direccion`, `nDireccion`) VALUES
(1, 'Valencia', 'cami reial', 118),
(4, 'manises', 'micasa', 123);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peaje`
--

CREATE TABLE `peaje` (
  `idPeaje` int(11) NOT NULL,
  `idViaje` int(11) NOT NULL,
  `costo` double NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `idViaje` int(11) NOT NULL,
  `tipo` varchar(11) COLLATE utf8_bin NOT NULL,
  `duracion` int(11) NOT NULL,
  `duracionTotal` int(11) NOT NULL,
  `idSalida` int(11) NOT NULL,
  `idLlegada` int(11) NOT NULL,
  `kilometos` int(11) NOT NULL,
  `fechaLlegada` datetime NOT NULL,
  `fechaSalida` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Disparadores `viaje`
--
DELIMITER $$
CREATE TRIGGER `deletePeajeCascade` BEFORE DELETE ON `viaje` FOR EACH ROW BEGIN
    DELETE FROM peaje WHERE	peaje.idViaje = idViaje;
    DELETE FROM combustible WHERE combustible.idViaje = idViaje;
END
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `combustible`
--
ALTER TABLE `combustible`
  ADD PRIMARY KEY (`idCombustible`),
  ADD KEY `fkViaje` (`idViaje`);

--
-- Indices de la tabla `lugar`
--
ALTER TABLE `lugar`
  ADD PRIMARY KEY (`idLugar`);

--
-- Indices de la tabla `peaje`
--
ALTER TABLE `peaje`
  ADD PRIMARY KEY (`idPeaje`),
  ADD KEY `fkViajePeaje` (`idViaje`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`idViaje`),
  ADD KEY `fkSalida` (`idSalida`),
  ADD KEY `fkLlegada` (`idLlegada`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `combustible`
--
ALTER TABLE `combustible`
  MODIFY `idCombustible` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `lugar`
--
ALTER TABLE `lugar`
  MODIFY `idLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `peaje`
--
ALTER TABLE `peaje`
  MODIFY `idPeaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `viaje`
--
ALTER TABLE `viaje`
  MODIFY `idViaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `combustible`
--
ALTER TABLE `combustible`
  ADD CONSTRAINT `fkViaje` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`);

--
-- Filtros para la tabla `peaje`
--
ALTER TABLE `peaje`
  ADD CONSTRAINT `fkViajePeaje` FOREIGN KEY (`idViaje`) REFERENCES `viaje` (`idViaje`);

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `fkLlegada` FOREIGN KEY (`idLlegada`) REFERENCES `lugar` (`idLugar`),
  ADD CONSTRAINT `fkSalida` FOREIGN KEY (`idSalida`) REFERENCES `lugar` (`idLugar`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
