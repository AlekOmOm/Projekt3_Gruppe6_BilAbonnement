package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;

import java.time.LocalDate;

public class LejeAftale {

    private int id;

    private Bruger bruger;      // Composition
    private KundeInfo kundeInfo; // Composition
    private SkadeRapport skadeRapport = null; // Aggregation

    // 1. Bil valg
    private Bil bil;            // Composition

    // 2. Abonnement side
    private String farve;
    private boolean afleveringsforsikring;
    private boolean selvrisiko;
    private boolean daekpakke;
    private boolean vejhjaelp;
    private boolean udleveringVedFDM;

    // 3. Prisoverslag
    private int abonnementslaengde;
    private int kmPrMdr;

    // 4. Afhentningssted
    private String afhentningssted;

    // Instance variabler sat ved Save
    private LocalDate startDato;
    private LocalDate slutDato;
    private int totalPris;


    // ------------------- Constructors -------------------
    public LejeAftale() {
    }

    public LejeAftale(int id, Bruger bruger, KundeInfo kundeInfo, Bil bil, SkadeRapport skadeRapport) {
        this.id = id;
        this.bruger = bruger;
        this.kundeInfo = kundeInfo;
        this.bil = bil;
        this.skadeRapport = skadeRapport;
    }

    public LejeAftale(Bruger bruger, KundeInfo kundeInfo, Bil bil, SkadeRapport skadeRapport, String farve, boolean afleveringsforsikring, boolean selvrisiko, boolean daekpakke, boolean vejhjaelp, boolean udleveringVedFDM, int abonnementslaengde, int kmPrMdr, String afhentningssted, LocalDate startDato, LocalDate slutDato, int totalPris) {
        setAllInstances(bruger, kundeInfo, bil, skadeRapport);
        setAllInstanceVariables(farve, afleveringsforsikring, selvrisiko, daekpakke, vejhjaelp, udleveringVedFDM, abonnementslaengde, kmPrMdr, afhentningssted, startDato, slutDato, totalPris);
    }


    // set all instances and set all instance variables
    public void setAllInstances(Bruger bruger, KundeInfo kundeInfo, Bil bil, SkadeRapport skadeRapport) {
        this.bruger = bruger;
        this.kundeInfo = kundeInfo;
        this.bil = bil;
        this.skadeRapport = skadeRapport;
    }

    public void setAllInstanceVariables(String farve, boolean afleveringsforsikring, boolean selvrisiko, boolean daekpakke, boolean vejhjaelp, boolean udleveringVedFDM, int abonnementslaengde, int kmPrMdr, String afhentningssted, LocalDate startDato, LocalDate slutDato, int totalPris) {
        this.farve = farve;
        this.afleveringsforsikring = afleveringsforsikring;
        this.selvrisiko = selvrisiko;
        this.daekpakke = daekpakke;
        this.vejhjaelp = vejhjaelp;
        this.udleveringVedFDM = udleveringVedFDM;
        this.abonnementslaengde = abonnementslaengde;
        this.kmPrMdr = kmPrMdr;
        this.afhentningssted = afhentningssted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.totalPris = totalPris;
    }




    // ------------------- Getters & Setters -------------------


    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Bruger getBruger() {
        return bruger;
    }

    public void setBruger(Bruger bruger) {
        this.bruger = bruger;
    }

    public KundeInfo getKundeInfo() {
        return kundeInfo;
    }

    public void setKundeInfo(KundeInfo kundeInfo) {
        this.kundeInfo = kundeInfo;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public SkadeRapport getSkadeRapport() {
        return skadeRapport;
    }

    public void setSkadeRapport(SkadeRapport skadeRapport) {
        this.skadeRapport = skadeRapport;
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

    public String getAfhentningssted() {
        return afhentningssted;
    }

    public void setAfhentningssted(String afhentningssted) {
        this.afhentningssted = afhentningssted;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
    }

    public int getTotalPris() {
        return totalPris;
    }

    public void setTotalPris(int totalPris) {
        this.totalPris = totalPris;
    }

    // ------------------- toString -------------------
    @Override
    public String toString() {
        return "LejeAftale{" +
                "id=" + id +
                ", bruger=" + bruger +
                ", kundeInfo=" + kundeInfo +
                ", skadeRapport=" + skadeRapport +
                ", bil=" + bil +
                ", farve='" + farve + '\'' +
                ", afleveringsforsikring=" + afleveringsforsikring +
                ", selvrisiko=" + selvrisiko +
                ", daekpakke=" + daekpakke +
                ", vejhjaelp=" + vejhjaelp +
                ", udleveringVedFDM=" + udleveringVedFDM +
                ", abonnementslaengde=" + abonnementslaengde +
                ", kmPrMdr=" + kmPrMdr +
                ", afhentningssted='" + afhentningssted + '\'' +
                ", startDato=" + startDato +
                ", slutDato=" + slutDato +
                ", totalPris=" + totalPris +
                '}';
    }

}