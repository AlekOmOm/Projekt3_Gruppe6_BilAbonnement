package dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses;

public class Peugeot108 extends Bil {

    private static final String MODEL = "Peugeot 108";

    public Peugeot108() {

    }

    public Peugeot108(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);

    }

    public Peugeot108(int id, String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(id, vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);

    }

    @Override
    public String getModel() {
        return MODEL;
    }

}
