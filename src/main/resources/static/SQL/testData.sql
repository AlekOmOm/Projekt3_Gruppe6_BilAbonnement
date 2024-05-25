USE Bil_Abonnement;

INSERT INTO kundeinfo (CPR_NR, Fornavn, Efternavn, Adresse, PostNummer, Email, MobilNummer)
VALUES ('123456-7890', 'John', 'Doe', '123 Main St', '1000', 'john.doe@example.com', '12345678');

INSERT INTO Bruger (ID, BrugerNavn, Password, Rolle)
VALUES (1, 'john_doe', 'password123', 'standard');

INSERT INTO Bil (ID, Vognnummer, Stelnummer, Model, UdstyrsNiveau, KilometerKoert, Status)
VALUES (1, 'ABC123', '1234567890', 'Toyota Corolla', 'Standard', 10000, 'Available');

INSERT INTO Skader (SkadeRapportID, Type, Pris) VALUES
                                                    (NULL, 'Bilrude', 5000),
                                                    (NULL, 'Bilhjul', 2000),
                                                    (NULL, 'Bilkarosseri', 10000),
                                                    (NULL, 'Bilbatteri', 3000),
                                                    (NULL, 'Bilbremser', 4000),
                                                    (NULL, 'Bilgearkasse', 8000),
                                                    (NULL, 'Bilforrude', 5000),
                                                    (NULL, 'Bilfjedre', 2000),
                                                    (NULL, 'Rensning af bil', 1000),
                                                    (NULL, 'Bilbelysning', 1000);

INSERT INTO LejeAftale (
    brugerID,
    bilID,
    kundeInfoID,
    skadeRapportID,
    abonnementsType,
    prisoverslag,
    afhentningssted,
    leveringssted,
    startDato,
    slutDato
) VALUES (
             1,              -- brugerID
             1,              -- bilID
             1,              -- kundeInfoID
             NULL,           -- skadeRapportID (set to NULL)
             'Standard',     -- abonnementsType
             5000,           -- prisoverslag
             'Copenhagen',   -- afhentningssted
             'By',			-- afleveringssted
             '2024-05-22',   -- startDato
             '2024-06-22'    -- slutDato
         );


INSERT INTO kundeinfo (CPR_NR, Fornavn, Efternavn, Adresse, PostNummer, Email, MobilNummer)
VALUES ('123456-7890', 'John', 'Doe', '123 Main St', '1000', 'john.doe@example.com', '12345678');

INSERT INTO Bruger (ID, BrugerNavn, Password, Rolle)
VALUES (2, 'john_doe', 'password123', 'standard');

INSERT INTO Bil (ID, Vognnummer, Stelnummer, Model, UdstyrsNiveau, KilometerKoert, Status)
VALUES (2, 'ABC123', '1234567890', 'Toyota Corolla', 'Standard', 10000, 'Available');

INSERT INTO LejeAftale (
    brugerID,
    bilID,
    kundeInfoID,
    skadeRapportID,
    abonnementsType,
    prisoverslag,
    afhentningssted,
    leveringssted,
    startDato,
    slutDato
) VALUES (
             2,              -- brugerID
             2,              -- bilID
             2,              -- kundeInfoID
             NULL,           -- skadeRapportID (set to NULL)
             'Standard',     -- abonnementsType
             5000,           -- prisoverslag
             'Copenhagen',   -- afhentningssted
             'By',			-- afleveringssted
             '2024-05-22',   -- startDato
             '2024-06-22'    -- slutDato
         );