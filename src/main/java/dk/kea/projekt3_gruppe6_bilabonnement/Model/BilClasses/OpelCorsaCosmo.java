package dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses;

public class OpelCorsaCosmo extends Bil {


    public OpelCorsaCosmo() {
        setModel("OpelCorsaCosmo");
    }

    public OpelCorsaCosmo(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
        setModel("OpelCorsaCosmo");
    }

    public OpelCorsaCosmo(int id, String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(id, vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);
        setModel("OpelCorsaCosmo");
    }

}
