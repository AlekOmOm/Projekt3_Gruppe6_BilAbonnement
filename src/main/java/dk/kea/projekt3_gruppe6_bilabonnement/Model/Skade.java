package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class Skade {

    private int id;
    private int skadeRapportID; // composition
    private String type;
    private int pris;

    // ------------------- Constructors -------------------
    public Skade() {
    }

    public Skade(int skadeRapportID, String type, int pris){
        this.skadeRapportID = skadeRapportID;
        this.type = type;
        this.pris = pris;
    }

    public Skade(int id, int skadeRapportID, String type, int pris){
        this.id = id;
        this.skadeRapportID = skadeRapportID;
        this.type = type;
        this.pris = pris;
    }

    // ------------------- Getters & Setters -------------------
    public int getSkadeRapportID() {
        return skadeRapportID;
    }

    public void setSkadeRapportID(int skadeRapportID) {
        this.skadeRapportID = skadeRapportID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "ID = " + id + ", " +
                "skadeRapportID = " + skadeRapportID + ", " +
                "type = " + type + ", " +
                "pris = " + pris + ")";
    }
}