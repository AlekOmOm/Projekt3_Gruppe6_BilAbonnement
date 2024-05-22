package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class KundeInfo {

    int id;
    String CPR_NR;
    String Fornavn;
    String Efternavn;
    String Adresse;
    int PostNummer;
    String Email;
    int MobilNummer;

    //empty constructor
    public KundeInfo() {
    }

    public KundeInfo(int id, String CPR_NR, String Fornavn, String Efternavn, String Adresse, int PostNummer, String Email, int MobilNummer) {
        this.id = id;
        this.CPR_NR = CPR_NR;
        this.Fornavn = Fornavn;
        this.Efternavn = Efternavn;
        this.Adresse = Adresse;
        this.PostNummer = PostNummer;
        this.Email = Email;
        this.MobilNummer = MobilNummer;
    }

    public KundeInfo(String CPR_NR, String Fornavn, String Efternavn, String Adresse, int PostNummer, String Email, int MobilNummer) {
        this.CPR_NR = CPR_NR;
        this.Fornavn = Fornavn;
        this.Efternavn = Efternavn;
        this.Adresse = Adresse;
        this.PostNummer = PostNummer;
        this.Email = Email;
        this.MobilNummer = MobilNummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // all get and set

    public String getCPR_NR() {
        return CPR_NR;
    }

    public void setCPR_NR(String CPR_NR) {
        this.CPR_NR = CPR_NR;
    }

    public String getFornavn() {
        return Fornavn;
    }

    public void setFornavn(String Fornavn) {
        this.Fornavn = Fornavn;
    }

    public String getEfternavn() {
        return Efternavn;
    }

    public void setEfternavn(String Efternavn) {
        this.Efternavn = Efternavn;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public int getPostNummer() {
        return PostNummer;
    }

    public void setPostNummer(int PostNummer) {
        this.PostNummer = PostNummer;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getMobilNummer() {
        return MobilNummer;
    }

    public void setMobilNummer(int MobilNummer) {
        this.MobilNummer = MobilNummer;
    }

    @Override
    public String toString() {
        return "KundeInfo{" + "id=" + id + ", CPR_NR=" + CPR_NR + ", Fornavn=" + Fornavn + ", Efternavn=" + Efternavn + ", Adresse=" + Adresse + ", PostNummer=" + PostNummer + ", Email=" + Email + ", MobilNummer=" + MobilNummer + '}';
    }

}


/*
CREATE TABLE KundeInfo
(
    ID          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CPR_NR      VARCHAR(255) NOT NULL UNIQUE,
    Fornavn     VARCHAR(255) NOT NULL,
    Efternavn   VARCHAR(255) NOT NULL,
    Adresse     VARCHAR(255) NOT NULL,
    PostNummer  VARCHAR(255) NOT NULL,
    Email       VARCHAR(255) NOT NULL,
    MobilNummer VARCHAR(255) NOT NULL
);
 */
