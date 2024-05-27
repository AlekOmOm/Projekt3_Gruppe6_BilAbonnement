package dk.kea.projekt3_gruppe6_bilabonnement.DTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

@Component
public class BrugerValgDTO {

    // 0. Bruger
    private int brugerID;

    // 1. abonnements side
//    String farve;
//    boolean afleveringsforsikring = false; // default
//    boolean selvrisiko = false; // default
//    boolean daekpakke = false; // default
//    boolean vejhjaelp = false; // default
//    boolean udleveringVedFDM = false; // default
    private List<String> selectedPackageDeals;

    // 1. bil valg
    private String bilModel; // bil modeller: CitroenC1, Peugeot108, OpelCorsaCosmo

    // 2. abonnements side
    private String farve;
    private boolean afleveringsforsikring;
    private boolean selvrisiko;
    private boolean daekpakke;
    private boolean vejhjaelp;
    private boolean udleveringVedFDM;
    private List<String> abonnementsSide;


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

    int totalPris;



    // ------------------- Constructors -------------------

    public BrugerValgDTO() {
    }



    public BrugerValgDTO(int brugerID, String bilModel, String farve, boolean afleveringsforsikring, boolean selvrisiko, boolean daekpakke, boolean vejhjaelp, boolean udleveringVedFDM, int abonnementslaengde, int kmPrMdr, String cprNr, String fornavn, String efternavn, String adresse, int postNummer, String email, int mobilNummer, String afhentningssted) {
        this.brugerID = brugerID;
        this.bilModel = bilModel;

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

    public int getTotalPris() {
        return totalPris;
    }

    public void setTotalPris(int totalPris) {
        this.totalPris = totalPris;
    }

    // ------------------- get for each page -------------------

    public String getBilValg(){
        return bilModel;
    }

    public List<String> getAbonnementsSide(){
        setAbonnementsSide();

        return abonnementsSide;
    }

    public void setAbonnementsSide () {
        abonnementsSide = new ArrayList<>();

        if (afleveringsforsikring){
            abonnementsSide.add("Afleveringsforsikring");
        }
        if (selvrisiko){
            abonnementsSide.add("Selvrisiko");
        }
        if (daekpakke){
            abonnementsSide.add("Daekpakke");
        }
        if (vejhjaelp){
            abonnementsSide.add("Vejhjaelp");
        }
        if (udleveringVedFDM){
            abonnementsSide.add("Udlevering ved FDM");
        }
    }

    public List<Boolean> getAbonnementsSideBoolean(){
        List<Boolean> abonnementsSide = new ArrayList<>();
        abonnementsSide.add(afleveringsforsikring);
        abonnementsSide.add(selvrisiko);
        abonnementsSide.add(daekpakke);
        abonnementsSide.add(vejhjaelp);
        abonnementsSide.add(udleveringVedFDM);
        return abonnementsSide;
    }

    public void setAbonnementsSideBoolean(List<Boolean> abonnementsSide){
        // only set if value is true;
        if (abonnementsSide.get(0)){
            this.afleveringsforsikring = true;
        }
        if (abonnementsSide.get(1)){
            this.selvrisiko = true;
        }
        if (abonnementsSide.get(2)){
            this.daekpakke = true;
        }
        if (abonnementsSide.get(3)){
            this.vejhjaelp = true;
        }
        if (abonnementsSide.get(4)){
            this.udleveringVedFDM = true;
        }
    }

    public List<String> getKundeInfo() {
        List<String> kundeInfo = new ArrayList<>();
        kundeInfo.add(cprNr);
        kundeInfo.add(fornavn);
        kundeInfo.add(efternavn);
        kundeInfo.add(adresse);
        kundeInfo.add(String.valueOf(postNummer));
        kundeInfo.add(email);
        kundeInfo.add(String.valueOf(mobilNummer));
        return kundeInfo;
    }

    public void setKundeInfo(List<String> kundeInfo) {
        this.cprNr = kundeInfo.get(0);
        this.fornavn = kundeInfo.get(1);
        this.efternavn = kundeInfo.get(2);
        this.adresse = kundeInfo.get(3);
        this.postNummer = Integer.parseInt(kundeInfo.get(4));
        this.email = kundeInfo.get(5);
        this.mobilNummer = Integer.parseInt(kundeInfo.get(6));
    }




    // ------------------- print for each page -------------------

    public void printBilValg(){
        System.out.println(" bilModel: " + bilModel);
    }

    public void printAbonnementsSide(){
        System.out.println(" farve: " + farve);
        System.out.println(" afleveringsforsikring: " + afleveringsforsikring);
        System.out.println(" selvrisiko: " + selvrisiko);
        System.out.println(" daekpakke: " + daekpakke);
        System.out.println(" vejhjaelp: " + vejhjaelp);
        System.out.println(" udleveringVedFDM: " + udleveringVedFDM);
    }

    public void printPrisoverslag(){
        System.out.println(" abonnementslaengde: " + abonnementslaengde);
        System.out.println(" kmPrMdr: " + kmPrMdr);
    }

    public void printKundeInfo(){
        System.out.println(" cprNr: " + cprNr);
        System.out.println(" fornavn: " + fornavn);
        System.out.println(" efternavn: " + efternavn);
        System.out.println(" adresse: " + adresse);
        System.out.println(" postNummer: " + postNummer);
        System.out.println(" email: " + email);
        System.out.println(" mobilNummer: " + mobilNummer);
    }

    public void printAfhentningssted(){
        System.out.println(" afhentningssted: " + afhentningssted);
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
                ", totalPris=" + totalPris +
                '}';
    }
}
