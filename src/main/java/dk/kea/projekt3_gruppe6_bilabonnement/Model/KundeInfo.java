package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class KundeInfo {

    int id;
    private String cprNr;
    private String fornavn;
    private String efternavn;
    private String adresse;
    private int postNummer;
    private String email;
    private int mobilNummer;

    //empty constructor
    public KundeInfo() {
    }

    public KundeInfo(int id, String cprNr, String fornavn, String efternavn, String adresse, int postNummer, String email, int mobilNummer) {
        this.id = id;
        this.cprNr = cprNr;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.postNummer = postNummer;
        this.email = email;
        this.mobilNummer = mobilNummer;
    }

    public KundeInfo(String cprNr, String fornavn, String efternavn, String adresse, int postNummer, String email, int mobilNummer) {
        this.cprNr = cprNr;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.postNummer = postNummer;
        this.email = email;
        this.mobilNummer = mobilNummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCprNr() {
        return cprNr;
    }

    public void setCprNr(String cprNr) {
        this.cprNr = cprNr;
    }

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

    public int getPostNummer() {
        return postNummer;
    }

    public void setPostNummer(int postNummer) {
        this.postNummer = postNummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobilNummer() {
        return mobilNummer;
    }

    public void setMobilNummer(int mobilNummer) {
        this.mobilNummer = mobilNummer;
    }

    @Override
    public String toString() {
        return "KundeInfo{" + "id=" + id + ", cprNr=" + cprNr + ", fornavn=" + fornavn + ", efternavn=" + efternavn + ", adresse=" + adresse + ", postNummer=" + postNummer + ", email=" + email + ", mobilNummer=" + mobilNummer + '}';
    }
}


/*
CREATE TABLE KundeInfo
(
    ID          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cpr_nr      VARCHAR(255) NOT NULL UNIQUE,
    Fornavn     VARCHAR(255) NOT NULL,
    Efternavn   VARCHAR(255) NOT NULL,
    Adresse     VARCHAR(255) NOT NULL,
    PostNummer  VARCHAR(255) NOT NULL,
    Email       VARCHAR(255) NOT NULL,
    MobilNummer VARCHAR(255) NOT NULL
);
 */
