/*
SQLyog Ultimate - MySQL GUI v8.22 
MySQL - 5.6.11 : Database - mysample
*********************************************************************
*/ 
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE `sipercar`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sipercarx`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `No_client` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `NCI` varchar(20) NOT NULL,
  `Adresse` varchar(70) NOT NULL,
  `AdresseEmail` varchar(50) NOT NULL,
  `No_contact` int(11) NOT NULL,
  PRIMARY KEY (`No_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `commandes`
--

DROP TABLE IF EXISTS `commandes`;
CREATE TABLE IF NOT EXISTS `commandes` (
  `No_cmde` int(11) NOT NULL AUTO_INCREMENT,
  `NoEmploye` int(11) NOT NULL,
  `NoClient` int(11) NOT NULL,
  `date_cmde` date NOT NULL,
  `date_requise` date NOT NULL,
  `date_trans` date NOT NULL,
  `No_employe` int(11) DEFAULT NULL,
  `No_client` int(11) NOT NULL,
  PRIMARY KEY (`No_cmde`),
  KEY `Commandes_Employes_FK` (`No_employe`),
  KEY `Commandes_clients0_FK` (`No_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `constituer`
--

DROP TABLE IF EXISTS `constituer`;
CREATE TABLE IF NOT EXISTS `constituer` (
  `Id_option` int(11) NOT NULL,
  `No_voiture` int(11) NOT NULL,
  PRIMARY KEY (`Id_option`,`No_voiture`),
  KEY `constituer_voitures0_FK` (`No_voiture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `departement`
--

DROP TABLE IF EXISTS `departement`;
CREATE TABLE IF NOT EXISTS `departement` (
  `No_dept` int(11) NOT NULL ,
  `Nom_dept` varchar(40) NOT NULL,
  PRIMARY KEY (`No_dept`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detailscommandes`
--

DROP TABLE IF EXISTS `detailscommandes`;
CREATE TABLE IF NOT EXISTS `detailscommandes` (
  `NoDetCmde` int(11) NOT NULL AUTO_INCREMENT,
  `PrixUnitaire` double NOT NULL,
  `Quantite` int(11) NOT NULL,
  `No_cmde` int(11) NOT NULL,
  `No_voiture` int(11) NOT NULL,
  PRIMARY KEY (`NoDetCmde`),
  KEY `DetailsCommandes_Commandes_FK` (`No_cmde`),
  KEY `DetailsCommandes_voitures0_FK` (`No_voiture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employes`
--

DROP TABLE IF EXISTS `employes`;
CREATE TABLE IF NOT EXISTS `employes` (
  `No_employe` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `NIC` varchar(15) NOT NULL,
  `DOB` date NOT NULL,
  `Sexe` varchar(7) NOT NULL,
  `Adresse` varchar(50) NOT NULL,
  `AdresseEmail` varchar(50) NOT NULL,
  `No_contact` int(11) NOT NULL,
  `Titre` varchar(50) NOT NULL,
  `Salaire` double NOT NULL,
  `DateDembauche` date NOT NULL,
  `Comission` double NOT NULL,
  `Nodept` int(11) NOT NULL,
  PRIMARY KEY (`No_employe`),
  KEY `Employes_Departement_FK` (`No_dept`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE IF NOT EXISTS `fournisseur` (
  `IdFournisseur` int(11) NOT NULL AUTO_INCREMENT,
  `NomFournisseur` varchar(50) NOT NULL,
  `NomContacte` varchar(50) NOT NULL,
  `Adresse` varchar(50) NOT NULL,
  PRIMARY KEY (`IdFournisseur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
CREATE TABLE IF NOT EXISTS `options` (
  `Id_option` int(11) NOT NULL,
  `Nom_option` varchar(40) NOT NULL,
  `Prix` double NOT NULL,
  PRIMARY KEY (`Id_option`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `voitures`
--

DROP TABLE IF EXISTS `voitures`;
CREATE TABLE IF NOT EXISTS `voitures` (
  `No_voiture` int(11) NOT NULL AUTO_INCREMENT,
  `Marque` varchar(50) NOT NULL,
  `Modele` varchar(50) NOT NULL,
  `Pays_dorigine` varchar(30) NOT NULL,
  `Intervalle_de_service` varchar(30) NOT NULL,
  `Couleur` varchar(30) NOT NULL,
  `Garantie` varchar(30) NOT NULL,
  `Transmission` varchar(30) NOT NULL,
  `Moteur` varchar(30) NOT NULL,
  `Prix` double NOT NULL,
  `DateMiseEnVente` date NOT NULL,
  `IdFournisseur` int(11) NOT NULL,
  PRIMARY KEY (`No_voiture`),
  KEY `voitures_Fournisseur_FK` (`IdFournisseur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `Commandes_Employes_FK` FOREIGN KEY (`No_employe`) REFERENCES `employes` (`No_employe`),
  ADD CONSTRAINT `Commandes_clients0_FK` FOREIGN KEY (`No_client`) REFERENCES `clients` (`No_client`);

--
-- Constraints for table `constituer`
--
ALTER TABLE `constituer`
  ADD CONSTRAINT `constituer_options_FK` FOREIGN KEY (`Id_option`) REFERENCES `options` (`Id_option`),
  ADD CONSTRAINT `constituer_voitures0_FK` FOREIGN KEY (`No_voiture`) REFERENCES `voitures` (`No_voiture`);

--
-- Constraints for table `detailscommandes`
--
ALTER TABLE `detailscommandes`
  ADD CONSTRAINT `DetailsCommandes_Commandes_FK` FOREIGN KEY (`No_cmde`) REFERENCES `commandes` (`No_cmde`),
  ADD CONSTRAINT `DetailsCommandes_voitures0_FK` FOREIGN KEY (`No_voiture`) REFERENCES `voitures` (`No_voiture`);

--
-- Constraints for table `employes`
--
ALTER TABLE `employes`
  ADD CONSTRAINT `Employes_Departement_FK` FOREIGN KEY (`No_dept`) REFERENCES `departement` (`No_dept`);

--
-- Constraints for table `voitures`
--
ALTER TABLE `voitures`
  ADD CONSTRAINT `voitures_Fournisseur_FK` FOREIGN KEY (`IdFournisseur`) REFERENCES `fournisseur` (`IdFournisseur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
