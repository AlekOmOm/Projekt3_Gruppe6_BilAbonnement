package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class SkadeRapport {
    int skadeRapportID;
    int brugerID;
    String koeretoejsNummer;
    String kundeID;
    int kilometerKoert;
    int reparationsomkostninger;

    public SkadeRapport(int skadeRapportID, int brugerID, String koeretoejsNummer, String kundeID, int kilometerKoert, int reparationsomkostninger) {
        this.skadeRapportID = skadeRapportID;
        this.brugerID = brugerID;
        this.kundeID = kundeID;
        this.koeretoejsNummer = koeretoejsNummer;
        this.kilometerKoert = kilometerKoert;
        this.reparationsomkostninger = reparationsomkostninger;
    }

    public int getSkadeRapportID() {
        return skadeRapportID;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public String getKoeretoejsNummer() {
        return koeretoejsNummer;
    }

    public String getKundeID() {
        return kundeID;
    }

    public int getKilometerKoert() {
        return kilometerKoert;
    }

    public int getReparationsomkostninger() {
        return reparationsomkostninger;
    }
}
