package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class Skade {

    private int id;
    private String type;
    private int pris;

    public Skade(int id, String type, int pris){
        this.id = id;
        this.type = type;
        this.pris = pris;
    }
    public Skade(String type, int pris){
        this.id = id;
        this.type = type;
        this.pris = pris;
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
}
