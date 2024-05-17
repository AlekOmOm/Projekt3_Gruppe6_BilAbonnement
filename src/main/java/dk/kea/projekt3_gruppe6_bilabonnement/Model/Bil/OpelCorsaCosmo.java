package dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.config.BilConfig;

import java.util.Arrays;
import java.util.List;

public class OpelCorsaCosmo extends Bil {

    public OpelCorsaCosmo() {
    }

    public OpelCorsaCosmo(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
    }

    public OpelCorsaCosmo(int id, String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(id, vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
    }


}
