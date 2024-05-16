package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class SkadeRapport {
    int skadeRapportID;
    int brugerID;
    String køretøjsNummer;
    String kundeID;
    int kilometerKørt;
    int reparationsomkostninger;



    public SkadeRapport(int skadeRapportID, int brugerID, String køretøjsNummer, String kundeID, int kilometerKørt, int reparationsomkostninger){
        this.skadeRapportID = skadeRapportID;
        this.brugerID = brugerID;
        this.kundeID = kundeID;
        this.køretøjsNummer = køretøjsNummer;
        this.kilometerKørt = kilometerKørt;
        this.reparationsomkostninger = reparationsomkostninger;
    }

    public int getSkadeRapportID() {
        return skadeRapportID;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public String getKøretøjsNummer() {
        return køretøjsNummer;
    }

    public String getKundeID() {
        return kundeID;
    }

    public int getKilometerKørt() {
        return kilometerKørt;
    }

    public int getReparationsomkostninger() {
        return reparationsomkostninger;
    }
}
