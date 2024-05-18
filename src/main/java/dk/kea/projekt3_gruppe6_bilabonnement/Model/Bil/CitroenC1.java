package dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil;

public class CitroenC1 extends Bil {

    private static final String MODEL = "Citroen C1 Triumph";



    public CitroenC1() {

    }

    public CitroenC1(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);

    }

    public CitroenC1(int id, String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        super(id, vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status);

    }

    @Override
    public String getModel() {
        return MODEL;
    }



}
