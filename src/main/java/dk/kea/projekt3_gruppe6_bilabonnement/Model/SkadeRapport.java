package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class SkadeRapport {
    int id;
    int brugerID;
    String koeretoejsNummer;
    String kundeID;
    int kilometerKoert;
    int reparationsomkostninger;

    public SkadeRapport(int id, int brugerID, String koeretoejsNummer, String kundeID, int kilometerKoert, int reparationsomkostninger) {
        this.id = id;
        this.brugerID = brugerID;
        this.kundeID = kundeID;
        this.koeretoejsNummer = koeretoejsNummer;
        this.kilometerKoert = kilometerKoert;
        this.reparationsomkostninger = reparationsomkostninger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public String getKoeretoejsNummer() {
        return koeretoejsNummer;
    }

    public void setKoeretoejsNummer(String koeretoejsNummer) {
        this.koeretoejsNummer = koeretoejsNummer;
    }

    public String getKundeID() {
        return kundeID;
    }

    public void setKundeID(String kundeID) {
        this.kundeID = kundeID;
    }

    public int getKilometerKoert() {
        return kilometerKoert;
    }

    public void setKilometerKoert(int kilometerKoert) {
        this.kilometerKoert = kilometerKoert;
    }

    public int getReparationsomkostninger() {
        return reparationsomkostninger;
    }

    public void setReparationsomkostninger(int reparationsomkostninger) {
        this.reparationsomkostninger = reparationsomkostninger;
    }
}
