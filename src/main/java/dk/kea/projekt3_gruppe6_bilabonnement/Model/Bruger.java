package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class Bruger {
    private int id = 0;
    private String brugerNavn;
    private String password;
    private Rolle rolle; // dataregistrering, skade- og udbedring eller forretningsudvikling

    public enum Rolle {
        DATA_REGISTRERING,
        SKADE_OG_UDBEDRING,
        FORRETNINGS_UDVIKLING;

        boolean equals(Rolle rolle) { // tjek om korrekt rolle
            return this.name().equals(rolle.name());
        }
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
        if (rolle == null) {
            throw new IllegalArgumentException("Rolle ikke gyldig");
        }
        switch (rolle) {
            case "DATA_REGISTRERING":
                this.rolle = Rolle.DATA_REGISTRERING;
                break;
            case "SKADE_OG_UDBEDRING":
                this.rolle = Rolle.SKADE_OG_UDBEDRING;
                break;
            case "FORRETNINGS_UDVIKLING":
                this.rolle = Rolle.FORRETNINGS_UDVIKLING;
                break;
            default:
                throw new IllegalArgumentException("Rolle ikke gyldig");
        }
    }

    public Rolle getRolle() {
        return rolle;
    }

    public String getRolleString() {
        return rolle.name();
    }



    // ------------------- service -------------------

    public boolean equals(Bruger bruger) {
        Bruger thisBruger = this;

        if (bruger == null) { return false; }

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
