package dk.kea.projekt3_gruppe6_bilabonnement.Model;


import java.time.LocalDate;


public class LejeAftale {

    int id;

    int brugerID;
    int bilID;
    int kundeInfoID;
    int skadeRapportID;

    String abonnementsType;
    int prisoverslag;
    String afhentningssted;
    String afleveringssted;
    LocalDate startDato;
    LocalDate slutDato;

    // constructor lejeaftale
    //empty constructor
    public LejeAftale() {
    }

    public LejeAftale(int id, int brugerID, int bilID, int kundeInfoID, int skadeRapportID, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
        this.id = id;
        this.brugerID = brugerID;
        this.bilID = bilID;
        this.kundeInfoID = kundeInfoID;
        this.skadeRapportID = skadeRapportID;

        this.abonnementsType = abonnementsType;
        this.prisoverslag = prisoverslag;
        this.afhentningssted = afhentningssted;
        this.afleveringssted = afleveringssted;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }


    public LejeAftale(int id, int brugerID, int bilID, int kundeID, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
        this.id = id;
        this.brugerID = brugerID;
        this.bilID = bilID;
        this.kundeInfoID = kundeID;

        this.abonnementsType = abonnementsType;
        this.prisoverslag = prisoverslag;
        this.afhentningssted = afhentningssted;
        this.afleveringssted = afleveringssted;
        this.startDato = startDato;
        this.slutDato = slutDato;

    }




    // ------------------- Get & Set -------------------

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public int getBilID() {
        return bilID;
    }

    public void setBilID(int bilID) {
        this.bilID = bilID;
    }

    public String getAbonnementsType() {
        return abonnementsType;
    }

    public void setAbonnementsType(String abonnementsType) {
        this.abonnementsType = abonnementsType;
    }

    public int getKundeInfoID() {
        return kundeInfoID;
    }

    public void setKundeInfoID(int kundeInfoID) {
        this.kundeInfoID = kundeInfoID;
    }

    public int getPrisoverslag() {
        return prisoverslag;
    }

    public void setPrisoverslag(int prisoverslag) {
        this.prisoverslag = prisoverslag;
    }

    public String getAfhentningssted() {
        return afhentningssted;
    }

    public void setAfhentningssted(String afhentningssted) {
        this.afhentningssted = afhentningssted;
    }

    public String getAfleveringssted() {
        return afleveringssted;
    }

    public void setAfleveringssted(String afleveringssted) {
        this.afleveringssted = afleveringssted;
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

    public int getSkadeRapportID() {
        return skadeRapportID;
    }

    public void setSkadeRapportID(int skadeRapportID) {
        this.skadeRapportID = skadeRapportID;
    }
}