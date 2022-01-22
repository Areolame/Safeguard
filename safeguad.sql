--CREATE DATABASE  IF NOT EXISTS gestionhopital;
CREATE SCHEMA IF NOT EXISTS gestionhopital;
USE gestionhopital;

-- A MODIFIER
-- CREER DES TABLES DE LIAISON
CREATE TABLE IF NOT EXISTS Stock (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  nombre_masque INT NOT NULL,
  type_masque varchar(100) NOT NULL,
  nombre_vaccin INT NOT NULL,
  type_vaccin varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Vaccin (
	nom_vaccin varchar(100) PRIMARY KEY NOT NULL,
	stock_vaccin INT NOT NULL,
	date_expiration date NOT NULL
);

CREATE TABLE IF NOT EXISTS Masque (
	nom_masque varchar(100) PRIMARY KEY NOT NULL,
	stock_masque INT NOT NULL,
	date_reception date NOT NULL
);

CREATE TABLE IF NOT EXISTS Gel (
	nom_gel varchar(100) PRIMARY KEY NOT NULL,
	stock_gel INT NOT NULL,
	date_reception date NOT NULL
);

CREATE TABLE IF NOT EXISTS Test (
	nom_test varchar(100) PRIMARY KEY NOT NULL,
	stock_test INT NOT NULL,
	date_reception date NOT NULL
);

CREATE TABLE IF NOT EXISTS Personne (
	id INT PRIMARY KEY NOT NULL,
	nom varchar(100) NOT NULL,
	prenom varchar(100) NOT NULL,
	naissance date NOT NULL,
	positif boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS LaisonMasquePersonne (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	id_personne INT NOT NULL,
	nom_masque varchar(100) NOT NULL,
	nombre INT NOT NULL,
	FOREIGN KEY (id) REFERENCES Personne(id) ON DELETE CASCADE,
    FOREIGN KEY (nom_masque) REFERENCES Masque(nom_masque) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS LaisonGelPersonne (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	id_personne INT NOT NULL,
	nom_gel varchar(100) NOT NULL,
	nombre INT NOT NULL,
	FOREIGN KEY (id_personne) REFERENCES Personne(id) ON DELETE CASCADE,
    FOREIGN KEY (nom_gel) REFERENCES Gel(nom_gel) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS LaisonTestPersonne (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	id_personne INT NOT NULL,
	nom_test varchar(100) NOT NULL,
	date_test date NOT NULL,
	resultat boolean NOT NULL,
	FOREIGN KEY (id_personne) REFERENCES Personne(id) ON DELETE CASCADE,
    FOREIGN KEY (nom_test) REFERENCES Tel(nom_test) ON DELETE CASCADE
);

--INSERT INTO test VALUES ('PCR',20,2022-01-21);
