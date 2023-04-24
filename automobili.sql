-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 24, 2023 alle 19:40
-- Versione del server: 10.4.24-MariaDB
-- Versione PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `automobili`
--
CREATE DATABASE IF NOT EXISTS `automobili` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `automobili`;

-- --------------------------------------------------------

--
-- Struttura della tabella `auto`
--

CREATE TABLE `auto`
(
    `ID_AUTO`       int(10) UNSIGNED NOT NULL,
    `Marca`         varchar(45)                                             DEFAULT NULL,
    `Modello`       varchar(45)                                             DEFAULT NULL,
    `Prezzo`        int(11)                                                 DEFAULT NULL,
    `Colore`        varchar(45)                                             DEFAULT NULL,
    `Alimentazione` enum ('Benzina','Diesel','Metano','Ibrida','Elettrica') DEFAULT NULL,
    `p_Motore`      int(10) UNSIGNED NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Trigger `auto`
--
DELIMITER $$
CREATE TRIGGER `backup_auto_delete`
    AFTER DELETE
    ON `auto`
    FOR EACH ROW INSERT INTO `auto_bak`
                 VALUES (OLD.ID_AUTO, OLD.Marca, OLD.Modello, OLD.Prezzo, OLD.Colore, OLD.Alimentazione, OLD.p_Motore,
                         CURRENT_TIMESTAMP())
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `backup_auto_update`
    AFTER UPDATE
    ON `auto`
    FOR EACH ROW INSERT INTO `auto_bak`
                 VALUES (OLD.ID_AUTO, OLD.Marca, OLD.Modello, OLD.Prezzo, OLD.Colore, OLD.Alimentazione, OLD.p_Motore,
                         CURRENT_TIMESTAMP())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `auto_bak`
--

CREATE TABLE `auto_bak`
(
    `ID_AUTO`       int(10) UNSIGNED NOT NULL,
    `Marca`         varchar(45)                                             DEFAULT NULL,
    `Modello`       varchar(45)                                             DEFAULT NULL,
    `Prezzo`        int(11)                                                 DEFAULT NULL,
    `Colore`        varchar(45)                                             DEFAULT NULL,
    `Alimentazione` enum ('Benzina','Diesel','Metano','Ibrida','Elettrica') DEFAULT NULL,
    `p_Motore`      int(10) UNSIGNED NOT NULL,
    `data_modifica` timestamp        NOT NULL                               DEFAULT current_timestamp()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Struttura della tabella `motore`
--

CREATE TABLE `motore`
(
    `ID_MOTORE`   int(10) UNSIGNED NOT NULL,
    `Descrizione` varchar(10) DEFAULT NULL,
    `Cilindrata`  int(11)     DEFAULT NULL,
    `Cavalli`     int(11)     DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Dump dei dati per la tabella `motore`
--

INSERT INTO `motore` (`ID_MOTORE`, `Descrizione`, `Cilindrata`, `Cavalli`)
VALUES (1, '1000/13', 1000, 13),
       (2, '1100/13', 1100, 13),
       (3, '1200/15', 1200, 15),
       (4, '1300/15', 1300, 15),
       (5, '1400/16', 1400, 16),
       (6, '1500/16', 1500, 16),
       (7, '1600/17', 1600, 17),
       (8, '1700/18', 1700, 18),
       (9, '1800/19', 1800, 19),
       (10, '1900/19', 1900, 19),
       (11, '2000/20', 2000, 20),
       (12, '2100/21', 2100, 21),
       (13, '2200/21', 2200, 21),
       (14, '2300/22', 2300, 22),
       (15, '2400/23', 2400, 23),
       (16, '2500/23', 2500, 23),
       (17, '2600/24', 2600, 24),
       (18, '2700/24', 2700, 24),
       (19, '2800/25', 2800, 25),
       (20, '2900/26', 2900, 26),
       (21, '3000/26', 3000, 26),
       (22, '3100/27', 3100, 27),
       (23, '3200/27', 3200, 27),
       (24, '3300/28', 3300, 28),
       (25, '3400/28', 3400, 28),
       (26, '3500/29', 3500, 29),
       (27, '3600/30', 3600, 30),
       (28, '3700/30', 3700, 30),
       (29, '3800/31', 3800, 31),
       (30, '3900/31', 3900, 31),
       (31, '4000/32', 4000, 32);

--
-- Indici per le tabelle `auto`
--
ALTER TABLE `auto`
    ADD PRIMARY KEY (`ID_AUTO`),
    ADD KEY `p_Motore` (`p_Motore`);

--
-- Indici per le tabelle `auto_bak`
--
ALTER TABLE `auto_bak`
    ADD KEY `p_Motore` (`p_Motore`),
    ADD KEY `ID_AUTO` (`ID_AUTO`) USING BTREE;

--
-- Indici per le tabelle `motore`
--
ALTER TABLE `motore`
    ADD PRIMARY KEY (`ID_MOTORE`),
    ADD UNIQUE KEY `xd_Auto_Motore___Cilindrata_Cavalli` (`Cilindrata`, `Cavalli`);

--
-- AUTO_INCREMENT per la tabella `auto`
--
ALTER TABLE `auto`
    MODIFY `ID_AUTO` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 1;

--
-- AUTO_INCREMENT per la tabella `motore`
--
ALTER TABLE `motore`
    MODIFY `ID_MOTORE` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 32;

--
-- Limiti per la tabella `auto`
--
ALTER TABLE `auto`
    ADD CONSTRAINT `auto_ibfk_1` FOREIGN KEY (`p_Motore`) REFERENCES `motore` (`ID_MOTORE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `auto_bak`
--
ALTER TABLE `auto_bak`
    ADD CONSTRAINT `auto_bak_ibfk_1` FOREIGN KEY (`p_Motore`) REFERENCES `motore` (`ID_MOTORE`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

--
-- Dump dei dati per la tabella `auto`
--

INSERT INTO `auto` (`Marca`, `Modello`, `Prezzo`, `Colore`, `Alimentazione`, `p_Motore`)
VALUES ('Fiat', 'Punto Evo', 13000, 'Nera', 'Benzina', 3),
       ('BMW', 'X3', 20000, 'Bianca', 'Ibrida', 19),
       ('Citroen', 'Saxo', 1300, 'Blu Elettrico', 'Benzina', 1),
       ('Fiat', 'Panda', 18000, 'Rossa', 'Benzina', 3),
       ('Fiat', '500L', 25000, 'Grigia', 'Diesel', 4),
       ('Audi', 'A3', 30000, 'Blu Notte', 'Metano', 5),
       ('Toyota', 'Aygo', 44999, 'Rossa', 'Diesel', 17),
       ('BMW', 'Serie 5', 34000, 'Nera', 'Diesel', 27),
       ('Tesla', 'Model 3', 17000, 'Verde', 'Elettrica', 8),
       ('Suzuki', 'Swift', 12000, 'Bianca', 'Metano', 7),
       ('Ford', 'Fiesta', 50000, 'Grigia', 'Ibrida', 11),
       ('Wolkswagen', 'Golf', 500, 'Gialla', 'Benzina', 3),
       ('Kia', 'Picanto', 9000, 'Rosa', 'Metano', 7),
       ('Lancia', 'Lybra', 21000, 'Blu Notte', 'Diesel', 14),
       ('Lancia', 'Elefantino', 700, 'Blu Elettrico', 'Elettrica', 2),
       ('Tesla', 'Model 2', 27400, 'Marrone', 'Elettrica', 24),
       ('Super', 'Car', 99999, 'Rossa', 'Benzina', 31),
       ('Alfa Romeo', 'Tonale', 39700, 'Azzurra', 'Ibrida', 28),
       ('Ferrari', '812 Superfast', 350000, 'Rossa', 'Benzina', 31),
       ('Hyundai', 'i30', 7000, 'Viola', 'Metano', 9),
       ('Fiat', 'Grande Punto', 1300, 'Rossa', 'Benzina', 1),
       ('BMW', 'X5', 23000, 'Bianca', 'Diesel', 21),
       ('BMW', 'X3', 16850, 'Nera', 'Diesel', 20),
       ('Fiat', 'Brava', 9900, 'Verde', 'Benzina', 5),
       ('Fiat', 'Bravo', 8500, 'Rosa', 'Benzina', 5),
       ('Fiat', 'Bravo', 8500, 'Rosa', 'Benzina', 5),
       ('Lamborghini', 'Diablo', 55000, 'Viola', 'Benzina', 31);

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
