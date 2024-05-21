package dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil;

import dk.kea.projekt3_gruppe6_bilabonnement.config.BilConfig;

public class Peugeot108 extends Bil {


    public Peugeot108() {
        setModel("Peugeot 108");
    }

    public Peugeot108(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
        setModel("Peugeot 108");
    }

    public Peugeot108(int id, String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(id, vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
        setModel("Peugeot 108");
    }


}
