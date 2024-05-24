package dk.kea.projekt3_gruppe6_bilabonnement.DTO;

import java.util.List;

public class BrugerValgDTO {
    // 0. bil valg
    String bilModel; // bil modeller: CitroenC1, Peugeot108, OpelCorsaCosmo

    // 1. abonnements side
//    String farve;
//    boolean afleveringsforsikring = false; // default
//    boolean selvrisiko = false; // default
//    boolean daekpakke = false; // default
//    boolean vejhjaelp = false; // default
//    boolean udleveringVedFDM = false; // default
    private List<String> selectedPackageDeals;

    // 2. Prisoverslag
    int abonnementslaengde = 0;
    int kmPrMdr = 0;

    // 4. afhentningssted
    String afhentningssted;

    int totalPris;



    // ------------------- Constructors -------------------

    public BrugerValgDTO() {
    }

//    public BrugerValgDTO(String bilModel, String farve, boolean afleveringsforsikring, boolean selvrisiko, boolean daekpakke, boolean vejhjaelp, boolean udleveringVedFDM, int abonnementslaengde, int kmPrMdr, String afhentningssted, int totalPris) {
//        this.bilModel = bilModel;
//        this.farve = farve;
//        this.afleveringsforsikring = afleveringsforsikring;
//        this.selvrisiko = selvrisiko;
//        this.daekpakke = daekpakke;
//        this.vejhjaelp = vejhjaelp;
//        this.udleveringVedFDM = udleveringVedFDM;
//        this.abonnementslaengde = abonnementslaengde;
//        this.kmPrMdr = kmPrMdr;
//        this.afhentningssted = afhentningssted;
//        this.totalPris = totalPris;
//    }

    public BrugerValgDTO(String bilModel, List<String> selectedPackageDeals, int abonnementslaengde, int kmPrMdr, String afhentningssted, int totalPris) {
        this.bilModel = bilModel;
        this.selectedPackageDeals = selectedPackageDeals;
        this.abonnementslaengde = abonnementslaengde;
        this.kmPrMdr = kmPrMdr;
        this.afhentningssted = afhentningssted;
        this.totalPris = totalPris;
    }


    // ------------------- Getters & Setters -------------------

    public String getBilModel() {
        return bilModel;
    }

    public void setBilModel(String bilModel) {
        this.bilModel = bilModel;
    }

//    public String getFarve() {
//        return farve;
//    }
//
//    public void setFarve(String farve) {
//        this.farve = farve;
//    }
//
//    public boolean isAfleveringsforsikring() {
//        return afleveringsforsikring;
//    }
//
//    public void setAfleveringsforsikring(boolean afleveringsforsikring) {
//        this.afleveringsforsikring = afleveringsforsikring;
//    }
//
//    public boolean isSelvrisiko() {
//        return selvrisiko;
//    }
//
//    public void setSelvrisiko(boolean selvrisiko) {
//        this.selvrisiko = selvrisiko;
//    }
//
//    public boolean isDaekpakke() {
//        return daekpakke;
//    }
//
//    public void setDaekpakke(boolean daekpakke) {
//        this.daekpakke = daekpakke;
//    }
//
//    public boolean isVejhjaelp() {
//        return vejhjaelp;
//    }
//
//    public void setVejhjaelp(boolean vejhjaelp) {
//        this.vejhjaelp = vejhjaelp;
//    }
//
//    public boolean isUdleveringVedFDM() {
//        return udleveringVedFDM;
//    }
//
//    public void setUdleveringVedFDM(boolean udleveringVedFDM) {
//        this.udleveringVedFDM = udleveringVedFDM;
//    }

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

    public int getTotalPris() {
        return totalPris;
    }

    public void setTotalPris(int totalPris) {
        this.totalPris = totalPris;
    }

    public List<String> getSelectedPackageDeals() {
        return selectedPackageDeals;
    }
    public void setSelectedPackageDeals(List<String> selectedPackageDeals) {
        this.selectedPackageDeals = selectedPackageDeals;
    }

    // ------------------- toString -------------------

    @Override
    public String toString() {
        return "BrugerValgDTO{" +
                "bilModel='" + bilModel + '\'' +
//                ", farve='" + farve + '\'' +
//                ", afleveringsforsikring=" + afleveringsforsikring +
//                ", selvrisiko=" + selvrisiko +
//                ", daekpakke=" + daekpakke +
//                ", vejhjaelp=" + vejhjaelp +
//                ", udleveringVedFDM=" + udleveringVedFDM +
                ", abonnementslaengde=" + abonnementslaengde +
                ", kmPrMdr=" + kmPrMdr +
                ", afhentningssted='" + afhentningssted + '\'' +
                ", totalPris=" + totalPris +
                '}';
    }

    // ------------------- test til packagedeals -------------------



}
