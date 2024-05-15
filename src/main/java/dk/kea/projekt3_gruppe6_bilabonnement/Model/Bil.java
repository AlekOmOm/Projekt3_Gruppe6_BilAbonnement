package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class Bil {

    private String vognNummer;
    private String stelNummer;
    private String model;
    private String maerke;
    private String udstyrsNiaveu;
    private int kilometer;
    private String status;

   // constructor

    public Bil(String vognNummer, String stelNummer, String model, String maerke, String udstyrsNiaveu, int kilometer, String status) {
        this.vognNummer = vognNummer;
        this.stelNummer = stelNummer;
        this.model = model;
        this.maerke = maerke;
        this.udstyrsNiaveu = udstyrsNiaveu;
        this.kilometer = kilometer;
        this.status = status;
    }

    //empty constructor
    public Bil() {
    }

    // set and get methods for all variables
    public String getVognNummer() {
        return vognNummer;
    }
    public void setVognNummer(String vognNummer) {
        this.vognNummer = vognNummer;
    }
    public String getStelNummer() {
        return stelNummer;
    }
    public void setStelNummer(String stelNummer) {
        this.stelNummer = stelNummer;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getMaerke() {
        return maerke;
    }
    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }
    public String getUdstyrsNiaveu() {
        return udstyrsNiaveu;
    }
    public void setUdstyrsNiaveu(String udstyrsNiaveu) {
        this.udstyrsNiaveu = udstyrsNiaveu;
    }
    public int getKilometer() {
        return kilometer;
    }
    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}
