package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import java.time.LocalDate;

public class ModelLejeaftale {

    int lejeaftaleID;
    int brugerID;
    String koeretoejsNummer;
    String abonnementsType;
    String kundeID;
    int prisoverslag;
    String afhentningssted;
    String afleveringssted;
    LocalDate startDato;
    LocalDate slutDato;

    // constructor lejeaftale
    public ModelLejeaftale(int lejeaftaleID, int brugerID, String koeretoejsNummer, String abonnementsType, String kundeID, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
        this.lejeaftaleID = lejeaftaleID;
        this.brugerID = brugerID;
        this.koeretoejsNummer = koeretoejsNummer;
        this.abonnementsType = abonnementsType;
        this.kundeID = kundeID;
        this.prisoverslag = prisoverslag;
        this.afhentningssted = afhentningssted;
        this.afleveringssted = afleveringssted;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }

    //empty constructor
    public ModelLejeaftale() {
    }

    // getters and setters
    public int getLejeaftaleID() {
        return lejeaftaleID;
    }

    public void setLejeaftaleID(int lejeaftaleID) {
        this.lejeaftaleID = lejeaftaleID;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public String getKoeretoejsNummer() {
        return koeretoejsNummer;
    }

    public void setKoeretoejsNummer(String koeretoejsNummer) {
        this.koeretoejsNummer = koeretoejsNummer;
    }

    public String getAbonnementsType() {
        return abonnementsType;
    }

    public void setAbonnementsType(String abonnementsType) {
        this.abonnementsType = abonnementsType;
    }

    public String getKundeID() {
        return kundeID;
    }

    public void setKundeID(String kundeID) {
        this.kundeID = kundeID;
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

}