package dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;

public class OpelCorsaCosmo extends Bil {

    private static final String MODEL = "Opel Corsa Cosmo";

    public OpelCorsaCosmo() {
        setModel(MODEL);
    }

    public OpelCorsaCosmo(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
        setModel(MODEL);
    }

    public OpelCorsaCosmo(int id, String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(id, vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
        setModel(MODEL);
    }

}
