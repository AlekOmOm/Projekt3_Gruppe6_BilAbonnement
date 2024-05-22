package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class KundeInfo {
    private String fornavn;
    private String efternavn;
    private String adresse;
    private String postnummer;
    private String by;
    private String mobilnummer;
    private String email;
    private int kundeId;


    public KundeInfo(String fornavn, String efternavn, String adresse, String postnummer, String by, String mobilnummer, String email, int kundeId) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.postnummer = postnummer;
        this.by = by;
        this.mobilnummer = mobilnummer;
        this.email = email;
        this.kundeId = kundeId;
    }



    // ------------------- Getters and Setters -------------------

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getMobilnummer() {
        return mobilnummer;
    }

    public void setMobilnummer(String mobilnummer) {
        this.mobilnummer = mobilnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKundeId() {
        return kundeId;
    }
}
