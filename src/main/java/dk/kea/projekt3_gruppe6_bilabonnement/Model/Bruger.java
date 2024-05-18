package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import java.util.Arrays;

public class Bruger {
    private int id;
    private String brugerNavn;
    private String password;
    private Rolle rolle; // dataregistrering, skade- og udbedring eller forretningsudvikling



    public enum Rolle {
        DATA_REGISTRERING,
        SKADE_OG_UDBEDRING,
        FORRETNINGS_UDVIKLING
    }

    // ------------------- Constructors -------------------
    public Bruger() {
    }

    public Bruger(int id, String brugerNavn, String password, String rolle) {
        this.id = id;
        this.brugerNavn = brugerNavn;
        this.password = password;
        setRolle(rolle);
    }

    public Bruger(String brugerNavn, String password, String rolle) {
        this.brugerNavn = brugerNavn;
        this.password = password;
        setRolle(rolle);
    }

    // ------------------- Get & Set -------------------

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

    public void setRolle(String rolle) {
        try {
            this.rolle = Rolle.valueOf(rolle.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ikke valid rolle: " + rolle + ". De valide roller er: " + Arrays.toString(Rolle.values()));
        }
    }

    public String getRolle() {
        return rolle.toString();
    }



    // ------------------- service -------------------

    public boolean equals(Bruger bruger) {
        Bruger thisBruger = this;

        return thisBruger.getId() == bruger.getId()
                && thisBruger.getBrugerNavn().equals(bruger.getBrugerNavn())
                && thisBruger.getPassword().equals(bruger.getPassword())
                && thisBruger.getRolle().equals(bruger.getRolle());

    }

    public boolean equalsWithoutId(Bruger bruger) {
        Bruger thisBruger = this;

        return thisBruger.getBrugerNavn().equals(bruger.getBrugerNavn())
                && thisBruger.getPassword().equals(bruger.getPassword())
                && thisBruger.getRolle().equals(bruger.getRolle());

    }

    @Override
    public String toString() {
        return "Bruger{" +
                "brugerNavn='" + brugerNavn + '\'' +
                ", password='" + password + '\'' +
                ", rolle=" + rolle +
                '}';
    }

}
