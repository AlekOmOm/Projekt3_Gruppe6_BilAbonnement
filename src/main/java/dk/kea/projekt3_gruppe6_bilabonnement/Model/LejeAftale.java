package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "lejeaftale")
public class LejeAftale {

    @Id
    int id;

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
    public LejeAftale(int id, int brugerID, String koeretoejsNummer, String abonnementsType, String kundeID, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
        this.id = id;
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
    public LejeAftale() {
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public String getVognNummer() {
        return koeretoejsNummer;
    }

    public void setVognNummer(String koeretoejsNummer) {
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