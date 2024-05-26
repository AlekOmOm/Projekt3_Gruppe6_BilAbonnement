DROP DATABASE IF EXISTS Bil_Abonnement;
CREATE DATABASE Bil_Abonnement;
USE Bil_Abonnement;

CREATE TABLE Bruger
(
    ID         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Brugernavn VARCHAR(255) NOT NULL,
    Password   VARCHAR(255) NOT NULL,
    Rolle      VARCHAR(255) NOT NULL
);

CREATE TABLE Bil
(
    ID             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    VognNummer     VARCHAR(255) NOT NULL UNIQUE,
    StelNummer     VARCHAR(255) NOT NULL UNIQUE,
    Model          VARCHAR(255) NOT NULL,
    UdstyrsNiveau  VARCHAR(255),
    KilometerKoert INT,
    Status         VARCHAR(255) NOT NULL
);

CREATE TABLE KundeInfo
(
    ID          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CPR_NR      VARCHAR(255) NOT NULL UNIQUE,
    Fornavn     VARCHAR(255) NOT NULL,
    Efternavn   VARCHAR(255) NOT NULL,
    Adresse     VARCHAR(255) NOT NULL,
    PostNummer  VARCHAR(255) NOT NULL,
    Email       VARCHAR(255) NOT NULL,
    MobilNummer VARCHAR(255) NOT NULL
);

CREATE TABLE SkadeRapport
(
    ID                      INT AUTO_INCREMENT PRIMARY KEY,
    BrugerID                INT,
    KilometerKoertOver      INT,
    Reparationsomkostninger INT,
    FOREIGN KEY (BrugerID) REFERENCES Bruger (ID)
);

CREATE TABLE Skader
(
    ID             INT AUTO_INCREMENT PRIMARY KEY,
    SkadeRapportID INT NOT NULL,
    Type           VARCHAR(255),
    Pris           INT,
    FOREIGN KEY (SkadeRapportID) REFERENCES SkadeRapport (ID)
);

CREATE TABLE Forretningsrapport
(
    ID                INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DatoGenereret     DATE NOT NULL,
    TotalBilerUdlejet INT  NOT NULL,
    SamletPris        INT  NOT NULL
);

CREATE TABLE LejeAftale
(
    ID                    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    BrugerID              INT, -- foreign key
    KundeInfoID           INT, -- foreign key TODO: rækkefølge her byttet rundt, før: 3. BilID 4 KundeInfoID
    BilID                 INT, -- foreign key
    SkadeRapportID        INT, -- foreign key

    Farve                 VARCHAR(255),
    Afleveringsforsikring BOOLEAN,
    Selvrisiko            BOOLEAN,
    Daekpakke             BOOLEAN,
    Vejhjaelp             BOOLEAN,
    UdleveringVedFDM      BOOLEAN,
    Abonnementslaengde    INT,
    KmPrMdr               INT,
    Afhentningssted       VARCHAR(255),
    StartDato             DATE,
    SlutDato              DATE,
    TotalPris             INT,

    FOREIGN KEY (BrugerID) REFERENCES Bruger (ID),
    FOREIGN KEY (KundeInfoID) REFERENCES KundeInfo (ID),
    FOREIGN KEY (BilID) REFERENCES Bil (ID),
    FOREIGN KEY (SkadeRapportID) REFERENCES SkadeRapport (ID)
);