package dk.kea.projekt3_gruppe6_bilabonnement.Model;


import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;

import java.time.LocalDate;


public class LejeAftale {

    int id;

    // Instance relations:
        // 1. composition instances er 'nødvendigt' forbundet med LejeAftale livscyklysen
    Bruger bruger; // Composition
    KundeInfo kundeInfo; // Composition
        // 2. aggregation instances er 'ikke nødvendigt' forbundet med LejeAftale livscyklysen
    Bil bil; // Aggregation
    SkadeRapport skadeRapport = null; // Aggregation

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

    public LejeAftale(Bruger bruger, KundeInfo kundeInfo, Bil bil, SkadeRapport skadeRapport, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
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

    // uden skadeRapport med bil
    public LejeAftale(Bruger bruger, KundeInfo kundeInfo, Bil bil, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
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

    public LejeAftale(Bruger bruger, KundeInfo kundeInfo, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
        this.bruger = bruger;
        this.kundeInfo = kundeInfo;
        this.abonnementsType = abonnementsType;
        this.prisoverslag = prisoverslag;
        this.afhentningssted = afhentningssted;
        this.afleveringssted = afleveringssted;
        this.startDato = startDato;
        this.slutDato = slutDato;
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

    public void setBruger(Bruger bruger) {
        this.bruger = bruger;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public KundeInfo getKundeInfo() {
        return kundeInfo;
    }

    public void setKundeInfo(KundeInfo kundeInfo) {
        this.kundeInfo = kundeInfo;
    }

    public SkadeRapport getSkadeRapport() {
        return skadeRapport;
    }

    public void setSkadeRapport(SkadeRapport skadeRapport) {
        this.skadeRapport = skadeRapport;
    }

    public String getAbonnementsType() {
        return abonnementsType;
    }

    public void setAbonnementsType(String abonnementsType) {
        this.abonnementsType = abonnementsType;
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


    public boolean equalsWithoutId(LejeAftale nyLejeAftale) {
        return  this.bruger.equals(nyLejeAftale.getBruger()) &&
                this.bil.equals(nyLejeAftale.getBil()) &&
                this.kundeInfo.equals(nyLejeAftale.getKundeInfo()) &&
                this.skadeRapport.equals(nyLejeAftale.getSkadeRapport()) &&
                this.abonnementsType.equals(nyLejeAftale.getAbonnementsType()) &&
                this.prisoverslag == nyLejeAftale.getPrisoverslag() &&
                this.afhentningssted.equals(nyLejeAftale.getAfhentningssted()) &&
                this.afleveringssted.equals(nyLejeAftale.getAfleveringssted()) &&
                this.startDato.equals(nyLejeAftale.getStartDato()) &&
                this.slutDato.equals(nyLejeAftale.getSlutDato());
    }
}