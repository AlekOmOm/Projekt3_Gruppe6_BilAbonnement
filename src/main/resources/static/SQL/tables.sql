DROP DATABASE IF EXISTS Bil_Abonnement;
CREATE DATABASE Bil_Abonnement;
USE Bil_Abonnement;

CREATE TABLE Bruger (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Brugernavn VARCHAR(255) NOT NULL,
    Rolle VARCHAR(255) NOT NULL
);

CREATE TABLE Bil (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    VognNummer VARCHAR(255) NOT NULL,
    StelNummer VARCHAR(255) NOT NULL UNIQUE,
    Model VARCHAR(255) NOT NULL,
    Maerke VARCHAR(255) NOT NULL,
    UdstyrsNiveau VARCHAR(255),
    KilometerKoert INT,
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

    BrugerID INT, -- foreign key
    BilID BIGINT, -- foreign key
    KundeInfoID INT, -- foreign key

    Abonnementstype VARCHAR(255) NOT NULL,
    Prisoverslag INT,
    Afhentningssted VARCHAR(255),
    leveringssted VARCHAR(255),
    StartDato DATE,
    SlutDato DATE,
    FOREIGN KEY (BrugerID) REFERENCES Bruger(ID),
    FOREIGN KEY (BilID) REFERENCES Bil(ID),
    FOREIGN KEY (KundeInfoID) REFERENCES KundeInfo(ID)
);

CREATE TABLE SkadeRapport (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    BrugerID INT,
    BilID BIGINT,
    KundeInfoID INT,
    KilometerKoert INT,
    Reparationsomkostninger INT,
    FOREIGN KEY (BrugerID) REFERENCES Bruger(ID),
    FOREIGN KEY (BilID) REFERENCES Bil(ID),
    FOREIGN KEY (KundeInfoID) REFERENCES KundeInfo(ID)
);

CREATE TABLE Forretningsrapport (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DatoGenereret DATE NOT NULL,
    TotalBilerUdlejet INT NOT NULL,
    SamletPris INT NOT NULL
);