DROP DATABASE IF EXISTS Bil_Abonnement;
CREATE DATABASE Bil_Abonnement;
USE Bil_Abonnement;

CREATE TABLE Bruger (
                        ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        Brugernavn VARCHAR(255) NOT NULL,
                        Rolle VARCHAR(255) NOT NULL
);

CREATE TABLE Bil (
                     Koeretoejsnummer VARCHAR(255) NOT NULL PRIMARY KEY,
                     Stelnummer VARCHAR(255) NOT NULL UNIQUE,
                     Model VARCHAR(255) NOT NULL,
                     Maerke VARCHAR(255) NOT NULL,
                     Udstyrsniveau VARCHAR(255),
                     Kilometer INT,
                     Status VARCHAR(255) NOT NULL
);

CREATE TABLE KundeInfo (
                           ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           CPR_NR VARCHAR(255) NOT NULL UNIQUE,
                           Fornavn VARCHAR(255) NOT NULL,
                           Efternavn VARCHAR(255) NOT NULL,
                           Adresse VARCHAR(255) NOT NULL,
                           PostNummer VARCHAR(255) NOT NULL,
                           Email VARCHAR(255) NOT NULL,
                           MobilNummer VARCHAR(255) NOT NULL
);

CREATE TABLE Lejeaftale (
                            ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            BrugerID INT,
                            Koeretoejsnummer VARCHAR(255),
                            Abonnementstype VARCHAR(255) NOT NULL,
                            KundeID INT,
                            Prisoverslag INT,
                            Afhentningssted VARCHAR(255),
                            Afleveringssted VARCHAR(255),
                            StartDato DATE,
                            SlutDato DATE,
                            FOREIGN KEY (BrugerID) REFERENCES Bruger(ID),
                            FOREIGN KEY (Koeretoejsnummer) REFERENCES Bil(Koeretoejsnummer),
                            FOREIGN KEY (KundeID) REFERENCES KundeInfo(ID)
);

CREATE TABLE SkadeRapport (
                              ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              BrugerID INT,
                              Koeretoejsnummer VARCHAR(255),
                              KundeID INT,
                              KilometerKoert INT,
                              Reparationsomkostninger INT,
                              FOREIGN KEY (BrugerID) REFERENCES Bruger(ID),
                              FOREIGN KEY (Koeretoejsnummer) REFERENCES Bil(Koeretoejsnummer),
                              FOREIGN KEY (KundeID) REFERENCES KundeInfo(ID)
);

CREATE TABLE Forretningsrapport (
                                    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                    DatoGenereret DATE NOT NULL,
                                    TotalBilerUdlejet INT NOT NULL,
                                    SamletPris INT NOT NULL
);
