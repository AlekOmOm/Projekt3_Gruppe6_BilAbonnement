package dk.kea.projekt3_gruppe6_bilabonnement.DTO;

import java.time.LocalDate;

public class BrugerValgDTO {

    // 0. Bruger
    private int brugerID;

    // 1. bil valg
    private String bilModel; // bil modeller: CitroenC1, Peugeot108, OpelCorsaCosmo

    // 2. abonnements side
    private String farve;
    private boolean afleveringsforsikring;
    private boolean selvrisiko;
    private boolean daekpakke;
    private boolean vejhjaelp;
    private boolean udleveringVedFDM;

    // 3. Prisoverslag
    private int abonnementslaengde;
    private int kmPrMdr;

    // 4. Kunde Info
    private String cprNr;
    private String fornavn;
    private String efternavn;
    private String adresse;
    private int postNummer;
    private String email;
    private int mobilNummer;

    // 5. afhentningssted
    private String afhentningssted;

    // ------------------- Constructors -------------------

    public BrugerValgDTO() {
    }

    public BrugerValgDTO(int brugerID, String bilModel, String farve, boolean afleveringsforsikring, boolean selvrisiko, boolean daekpakke, boolean vejhjaelp, boolean udleveringVedFDM, int abonnementslaengde, int kmPrMdr, String cprNr, String fornavn, String efternavn, String adresse, int postNummer, String email, int mobilNummer, String afhentningssted) {
        this.brugerID = brugerID;
        this.bilModel = bilModel;
        this.farve = farve;
        this.afleveringsforsikring = afleveringsforsikring;
        this.selvrisiko = selvrisiko;
        this.daekpakke = daekpakke;
        this.vejhjaelp = vejhjaelp;
        this.udleveringVedFDM = udleveringVedFDM;
        this.abonnementslaengde = abonnementslaengde;
        this.kmPrMdr = kmPrMdr;
        this.cprNr = cprNr;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.postNummer = postNummer;
        this.email = email;
        this.mobilNummer = mobilNummer;
        this.afhentningssted = afhentningssted;
    }



    // ------------------- Getters & Setters -------------------

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public String getBilModel() {
        return bilModel;
    }

    public void setBilModel(String bilModel) {
        this.bilModel = bilModel;
    }

    public String getFarve() {
        return farve;
    }

    public void setFarve(String farve) {
        this.farve = farve;
    }

    public boolean isAfleveringsforsikring() {
        return afleveringsforsikring;
    }

    public void setAfleveringsforsikring(boolean afleveringsforsikring) {
        this.afleveringsforsikring = afleveringsforsikring;
    }

    public boolean isSelvrisiko() {
        return selvrisiko;
    }

    public void setSelvrisiko(boolean selvrisiko) {
        this.selvrisiko = selvrisiko;
    }

    public boolean isDaekpakke() {
        return daekpakke;
    }

    public void setDaekpakke(boolean daekpakke) {
        this.daekpakke = daekpakke;
    }

    public boolean isVejhjaelp() {
        return vejhjaelp;
    }

    public void setVejhjaelp(boolean vejhjaelp) {
        this.vejhjaelp = vejhjaelp;
    }

    public boolean isUdleveringVedFDM() {
        return udleveringVedFDM;
    }

    public void setUdleveringVedFDM(boolean udleveringVedFDM) {
        this.udleveringVedFDM = udleveringVedFDM;
    }

    public int getAbonnementslaengde() {
        return abonnementslaengde;
    }

    public void setAbonnementslaengde(int abonnementslaengde) {
        this.abonnementslaengde = abonnementslaengde;
    }

    public int getKmPrMdr() {
        return kmPrMdr;
    }

    public void setKmPrMdr(int kmPrMdr) {
        this.kmPrMdr = kmPrMdr;
    }

    //  4. Kunde Info
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

    // 5. afhentningssted
    public String getAfhentningssted() {
        return afhentningssted;
    }

    public void setAfhentningssted(String afhentningssted) {
        this.afhentningssted = afhentningssted;
    }

    // ------------------- toString -------------------

    @Override
    public String toString() {
        return "BrugerValgDTO{" +
                "bilModel='" + bilModel + '\'' +
                ", farve='" + farve + '\'' +
                ", afleveringsforsikring=" + afleveringsforsikring +
                ", selvrisiko=" + selvrisiko +
                ", daekpakke=" + daekpakke +
                ", vejhjaelp=" + vejhjaelp +
                ", udleveringVedFDM=" + udleveringVedFDM +
                ", abonnementslaengde=" + abonnementslaengde +
                ", kmPrMdr=" + kmPrMdr +
                ", afhentningssted='" + afhentningssted + '\'' +
                '}';
    }

}
