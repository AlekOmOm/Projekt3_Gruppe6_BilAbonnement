package dk.kea.projekt3_gruppe6_bilabonnement.DTO;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;

import java.io.Serializable;

/**
 * DTO for {@link Bruger}
 */

// TODO: Sprint 2 - Password Security: securely hashing og comparing passwords, når brugerService.login(brugerDTO) og brugerService.registrer(brugerDTO).

public class BrugerDto implements Serializable {

    private int id;
    private String brugerNavn;
    private String password;
    private String rolle;

    public BrugerDto() {
    }

    public BrugerDto(String brugerNavn, String password, String rolle) {
        this.brugerNavn = brugerNavn;
        this.password = password;
        this.rolle = rolle;
    }

    public BrugerDto(int id, String brugerNavn, String password, String rolle) {
        this.id = id;
        this.brugerNavn = brugerNavn;
        this.password = password;
        this.rolle = rolle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrugerNavn() {
        return brugerNavn;
    }

    public void setBrugerNavn(String brugerNavn) {
        this.brugerNavn = brugerNavn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }


    // ------------------- Services -------------------

    public void clearPassword() {
        this.password = null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "brugerNavn = " + brugerNavn + ", " +
                "password = " + password + ", " +
                "rolle = " + rolle + ")";
    }


}