CREATE SCHEMA IF NOT EXISTS gestionhopital;
USE gestionhopital;

CREATE TABLE IF NOT EXISTS Stock (
  nombre_element INT PRIMARY KEY NOT NULL  
);

CREATE TABLE IF NOT EXISTS Vaccin (
	nom_vaccin varchar(100) PRIMARY KEY NOT NULL,
	stock_vaccin INT NOT NULL,
	date_reception date NOT NULL
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

CREATE TABLE IF NOT EXISTS LiaisonMasquePersonne (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	id_personne INT NOT NULL,
	nom_masque varchar(100) NOT NULL,
	nombre INT NOT NULL,
	FOREIGN KEY (id) REFERENCES Personne(id) ON DELETE CASCADE,
    FOREIGN KEY (nom_masque) REFERENCES Masque(nom_masque) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS LiaisonGelPersonne (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	id_personne INT NOT NULL,
	nom_gel varchar(100) NOT NULL,
	nombre INT NOT NULL,
	FOREIGN KEY (id_personne) REFERENCES Personne(id) ON DELETE CASCADE,
    FOREIGN KEY (nom_gel) REFERENCES Gel(nom_gel) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS LiaisonTestPersonne (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	id_personne INT NOT NULL,
	nom_test varchar(100) NOT NULL,
	date_test date NOT NULL,
	resultat boolean NOT NULL,
	FOREIGN KEY (id_personne) REFERENCES Personne(id) ON DELETE CASCADE,
    FOREIGN KEY (nom_test) REFERENCES Test(nom_test) ON DELETE CASCADE
);

INSERT INTO Gel VALUES ('Hydroalcoolique',10,CURDATE());
INSERT INTO Masque VALUES ('FFP1',15,CURDATE());
INSERT INTO Masque VALUES ('FFP2',20,CURDATE());
INSERT INTO Masque VALUES ('FFP3',15,CURDATE());
INSERT INTO Masque VALUES ('Tissu',20,CURDATE());
INSERT INTO Vaccin VALUES ('Pfizer',30,CURDATE());
INSERT INTO Vaccin VALUES ('AstraZeneca',35,CURDATE());
INSERT INTO Vaccin VALUES ('Moderna',30,CURDATE());
INSERT INTO Test VALUES ('PCR',20,CURDATE());
INSERT INTO Test VALUES ('Antigenique',15,CURDATE());
INSERT INTO Stock VALUES (0);
