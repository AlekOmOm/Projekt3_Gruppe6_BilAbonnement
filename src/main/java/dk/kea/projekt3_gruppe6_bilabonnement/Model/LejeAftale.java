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
    int totalPris;

    //empty constructor
    public LejeAftale() {
    }

    public LejeAftale(Bruger bruger, KundeInfo kundeInfo, Bil bil, SkadeRapport skadeRapport, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato, int totalPris) {
        this.bruger = bruger;
        this.kundeInfo = kundeInfo;
        this.bil = bil;
        this.skadeRapport = skadeRapport;
        this.abonnementsType = abonnementsType;
        this.prisoverslag = prisoverslag;
        this.afhentningssted = afhentningssted;
        this.afleveringssted = afleveringssted;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.totalPris = totalPris;
    }

    public LejeAftale(Bruger bruger, KundeInfo kundeInfo, Bil bil, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato, int totalPris) {
        this(bruger, kundeInfo, bil, null, abonnementsType, prisoverslag, afhentningssted, afleveringssted, startDato, slutDato, totalPris);
    }

    public LejeAftale(Bruger bruger, KundeInfo kundeInfo, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato, int totalPris) {
        this(bruger, kundeInfo, null, null, abonnementsType, prisoverslag, afhentningssted, afleveringssted, startDato, slutDato, totalPris);
    }

    public LejeAftale(int id, Bruger bruger, Bil bil, KundeInfo kundeInfo, SkadeRapport skadeRapport, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato, int totalPris) {
        this(bruger, kundeInfo, bil, skadeRapport, abonnementsType, prisoverslag, afhentningssted, afleveringssted, startDato, slutDato, totalPris);
        this.id = id;
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


    public int getTotalPris() {
        return totalPris;
    }

    public void setTotalPris(int totalPris) {
        this.totalPris = totalPris;
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