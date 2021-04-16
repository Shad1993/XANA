-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 15, 2021 at 05:58 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moloto`
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
  `Produit` varchar(80) NOT NULL,
  `NoEmploye` int(11) NOT NULL,
  `date_cmde` date NOT NULL,
  `date_requise` date NOT NULL,
  `Quantite` int(11) NOT NULL,
  `No_employe` int(11) DEFAULT NULL,
  `IdFournisseur` int(11) NOT NULL,
  PRIMARY KEY (`No_cmde`),
  KEY `Commandes_Employes_FK` (`No_employe`),
  KEY `Commandes_Fournisseur0_FK` (`IdFournisseur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `compteutilisateur`
--

DROP TABLE IF EXISTS `compteutilisateur`;
CREATE TABLE IF NOT EXISTS `compteutilisateur` (
  `Id_User` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(90) NOT NULL,
  `mdp` varchar(40) NOT NULL,
  `No_employe` int(11) NOT NULL,
  PRIMARY KEY (`Id_User`),
  UNIQUE KEY `CompteUtilisateur_Employes_AK` (`No_employe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `departement`
--

DROP TABLE IF EXISTS `departement`;
CREATE TABLE IF NOT EXISTS `departement` (
  `No_dept` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_dept` varchar(40) NOT NULL,
  PRIMARY KEY (`No_dept`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departement`
--

INSERT INTO `departement` (`No_dept`, `Nom_dept`) VALUES
(10, 'Comptabilité'),
(18, 'Garage'),
(20, 'Vente'),
(30, 'Administration');

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
  `Comission` double(8,2) NOT NULL,
  `Nodept` int(11) NOT NULL,
  PRIMARY KEY (`No_employe`),
  KEY `Employes_Departement_FK` (`Nodept`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employes`
--

INSERT INTO `employes` (`No_employe`, `Nom`, `Prenom`, `NIC`, `DOB`, `Sexe`, `Adresse`, `AdresseEmail`, `No_contact`, `Titre`, `Salaire`, `DateDembauche`, `Comission`, `Nodept`) VALUES
(2, 'Toto', 'Tuchi', 'I1952432521451', '1998-04-13', 'Homme', 'Moka', 'Toto@yahoo.com', 52125425, 'Vendeur', 20000, '2021-04-09', 3500.00, 20),
(3, 'Perrine', 'Lionel', 'P124585412414L', '1995-05-10', 'Homme', 'Pailles', 'lio@gmail.com', 58596681, 'Comptable', 800000, '2021-04-09', 0.00, 10),
(4, 'Ànna-Laure', 'Corneilles', 'L232514251452L', '2003-04-18', 'Femme', 'Pailles Street Saint Jean', 'Neila@hotmail.com', 52563524, 'Administrateur', 20000, '2021-04-12', 0.00, 18),
(5, 'Del Casimeîro', 'Ànna Luizà', 'D326325412541A', '1999-04-28', 'Femme', 'Rue de la case', 'Maria@gmail.com', 1236521, 'Vendeur', 12000, '2021-04-12', 3500.00, 20),
(6, 'Jean Deric', 'Laval', 'J320121452141L', '1998-06-30', 'Homme', 'Seychelles', 'tonLaval@yahoo.com', 5232620, 'Mécanicien', 15000, '2021-04-09', 0.00, 20);

-- --------------------------------------------------------

--
-- Table structure for table `entrepot`
--

DROP TABLE IF EXISTS `entrepot`;
CREATE TABLE IF NOT EXISTS `entrepot` (
  `Id_Entrepot` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(80) NOT NULL,
  `Adresse` varchar(80) NOT NULL,
  `NoVoiture` int(4) DEFAULT NULL,
  `Quantite` int(11) NOT NULL,
  PRIMARY KEY (`Id_Entrepot`),
  KEY `NoVoiture` (`NoVoiture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fch_de_paie`
--

DROP TABLE IF EXISTS `fch_de_paie`;
CREATE TABLE IF NOT EXISTS `fch_de_paie` (
  `Id_fch` int(11) NOT NULL AUTO_INCREMENT,
  `Mois` varchar(10) NOT NULL,
  `HeureSup` double NOT NULL,
  `Bonus` double NOT NULL,
  `Commision` double NOT NULL,
  `Deduction` double NOT NULL,
  `No_Emp` int(11) NOT NULL,
  PRIMARY KEY (`Id_fch`),
  KEY `No_Emp` (`No_Emp`)
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
  `Email` varchar(80) NOT NULL,
  PRIMARY KEY (`IdFournisseur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vente`
--

DROP TABLE IF EXISTS `vente`;
CREATE TABLE IF NOT EXISTS `vente` (
  `No_vente` int(11) NOT NULL AUTO_INCREMENT,
  `Quantite` int(11) NOT NULL,
  `NoVoiture` int(11) DEFAULT NULL,
  `No_Emp` int(11) DEFAULT NULL,
  `NoClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`No_vente`),
  KEY `Vente_voitures_FK` (`NoVoiture`),
  KEY `Vente_Employes0_FK` (`No_Emp`),
  KEY `NoClient` (`NoClient`)
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
  PRIMARY KEY (`No_voiture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `Commandes_Employes_FK` FOREIGN KEY (`No_employe`) REFERENCES `employes` (`No_employe`),
  ADD CONSTRAINT `Commandes_Fournisseur0_FK` FOREIGN KEY (`IdFournisseur`) REFERENCES `fournisseur` (`IdFournisseur`);

--
-- Constraints for table `compteutilisateur`
--
ALTER TABLE `compteutilisateur`
  ADD CONSTRAINT `CompteUtilisateur_Employes_FK` FOREIGN KEY (`No_employe`) REFERENCES `employes` (`No_employe`);

--
-- Constraints for table `employes`
--
ALTER TABLE `employes`
  ADD CONSTRAINT `Employes_Departement_FK` FOREIGN KEY (`Nodept`) REFERENCES `departement` (`No_dept`);

--
-- Constraints for table `entrepot`
--
ALTER TABLE `entrepot`
  ADD CONSTRAINT `entrepot_ibfk_1` FOREIGN KEY (`NoVoiture`) REFERENCES `voitures` (`No_voiture`);

--
-- Constraints for table `fch_de_paie`
--
ALTER TABLE `fch_de_paie`
  ADD CONSTRAINT `No_Emp` FOREIGN KEY (`No_Emp`) REFERENCES `employes` (`No_employe`);

--
-- Constraints for table `vente`
--
ALTER TABLE `vente`
  ADD CONSTRAINT `Vente_Employes0_FK` FOREIGN KEY (`No_Emp`) REFERENCES `employes` (`No_employe`),
  ADD CONSTRAINT `Vente_voitures_FK` FOREIGN KEY (`NoVoiture`) REFERENCES `voitures` (`No_voiture`),
  ADD CONSTRAINT `vente_ibfk_1` FOREIGN KEY (`NoClient`) REFERENCES `clients` (`No_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
