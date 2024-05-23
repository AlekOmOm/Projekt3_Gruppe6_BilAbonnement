package dk.kea.projekt3_gruppe6_bilabonnement.Model;


import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;

import java.time.LocalDate;


public class LejeAftale {

    int id;

    Bruger bruger;
    Bil bil;
    KundeInfo kundeInfo;
    SkadeRapport skadeRapport;

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

    public LejeAftale(int id, Bruger bruger, Bil bil, KundeInfo kundeInfo, SkadeRapport skadeRapport, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
        this.id = id;
        this.bruger = bruger;
        this.bil = bil;
        this.kundeInfo = kundeInfo;
        this.skadeRapport = skadeRapport;

        this.abonnementsType = abonnementsType;
        this.prisoverslag = prisoverslag;
        this.afhentningssted = afhentningssted;
        this.afleveringssted = afleveringssted;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }


    public LejeAftale(int id, Bruger bruger, Bil bil, KundeInfo kundeInfo, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
        this.id = id;
        this.bruger = bruger;
        this.bil = bil;
        this.kundeInfo = kundeInfo;


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

    public Bruger getBruger() {
        return bruger;
    }

    public void setBrugerID(Bruger bruger) {
        this.bruger = bruger;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public String getAbonnementsType() {
        return abonnementsType;
    }

    public void setAbonnementsType(String abonnementsType) {
        this.abonnementsType = abonnementsType;
    }

    public KundeInfo getKundeInfo() {
        return kundeInfo;
    }

    public void setKundeInfo(KundeInfo kundeInfo) {
        this.kundeInfo = kundeInfo;
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

    public SkadeRapport getSkadeRapport() {
        return skadeRapport;
    }

    public void setSkadeRapport(SkadeRapport skadeRapport) {
        this.skadeRapport = skadeRapport;
    }

    // -----------------------------------------
//    public void setBruger(Bruger bruger) {
//        this.bruger = bruger;
//    }

//    public void setBil(Bil bil) {
//        this.bil = bil;
//    }

//    public void setKundeInfo(KundeInfo kundeInfo) {
//        this.kundeInfo = kundeInfo;
//    }

    // skal den være her?
//    public void setSkadeRapport(SkadeRapport skadeRapport) {
//        this.skadeRapport = skadeRapport;
//    }
    // -----------------------------------------

}