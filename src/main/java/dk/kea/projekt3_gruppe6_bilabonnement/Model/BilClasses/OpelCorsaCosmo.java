package dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses;

public class OpelCorsaCosmo extends Bil {

    private static final String MODEL = "Opel Corsa Cosmo";

    public OpelCorsaCosmo() {
        modelClass = "OpelCorsaCosmo";
    }

    public OpelCorsaCosmo(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
        modelClass = "OpelCorsaCosmo";
    }

    public OpelCorsaCosmo(int id, String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(id, vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
    }

    @Override
    public String getModel() {
        return MODEL;
    }
}
