package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import java.time.LocalDate;

public class LejeAftaleKortInfoDto {
    private final int ID;
    private final int bilID;
    private final LocalDate slutDato;

    public LejeAftaleKortInfoDto(int ID, int bilID, LocalDate slutDato) {
        this.ID = ID;
        this.bilID = bilID;
        this.slutDato = slutDato;
    }

    public int getID() {
        return ID;
    }

    public int getBilID() {
        return bilID;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "ID = " + ID + ", " +
                "bilID = " + bilID + ", " +
                "slutDato = " + slutDato + ")";
    }
}