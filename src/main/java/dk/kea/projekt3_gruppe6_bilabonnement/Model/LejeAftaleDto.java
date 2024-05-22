package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import java.time.LocalDate;
import java.util.List;

public class LejeAftaleDto {
    private int ID = 0; // default
    private final int brugerID;
    private final int bilID;
    private final int kundeID;
    private int skadeRapportID = 0; // default

    private final String abonnementsType;
    private final int prisoverslag;
    private final String afhentningssted;
    private final String afleveringssted;
    private final LocalDate startDato;
    private final LocalDate slutDato;


    public LejeAftaleDto(int ID, int brugerID, int bilID, int kundeID, int skadeRapportID, String abonnementsType, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato) {
        this.ID = ID;
        this.brugerID = brugerID;
        this.bilID = bilID;
        this.kundeID = kundeID;
        this.skadeRapportID = skadeRapportID;

        this.abonnementsType = abonnementsType;
        this.prisoverslag = prisoverslag;
        this.afhentningssted = afhentningssted;
        this.afleveringssted = afleveringssted;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }
    public LejeAftaleDto(int ID, int brugerID, int bilID, String abonnementsType, int kundeID, int prisoverslag, String afhentningssted, String afleveringssted, LocalDate startDato, LocalDate slutDato, List<SkadeRapport> skadeRapporter) {
        this.ID = ID;
        this.brugerID = brugerID;
        this.bilID = bilID;
        this.kundeID = kundeID;

        this.abonnementsType = abonnementsType;
        this.prisoverslag = prisoverslag;
        this.afhentningssted = afhentningssted;
        this.afleveringssted = afleveringssted;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }


    // ------------------- Get & Set -------------------

    public int getID() {
        return ID;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public int getBilID() {
        return bilID;
    }

    public String getAbonnementsType() {
        return abonnementsType;
    }

    public int getKundeID() {
        return kundeID;
    }

    public int getPrisoverslag() {
        return prisoverslag;
    }

    public String getAfhentningssted() {
        return afhentningssted;
    }

    public String getAfleveringssted() {
        return afleveringssted;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public int getSkadeRapportID() {
        return skadeRapportID;
    }

    public void setSkadeRapportID(int skadeRapportID) {
        this.skadeRapportID = skadeRapportID;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "ID = " + ID + ", " +
                "brugerID = " + brugerID + ", " +
                "bilID = " + bilID + ", " +
                "kundeID = " + kundeID + ", " +
                "skadeRapportID = " + skadeRapportID + ", " +
                "abonnementsType = " + abonnementsType + ", " +
                "prisoverslag = " + prisoverslag + ", " +
                "afhentningssted = " + afhentningssted + ", " +
                "afleveringssted = " + afleveringssted + ", " +
                "startDato = " + startDato + ", " +
                "slutDato = " + slutDato + ")";
    }


}