package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import java.util.List;

public class SkadeRapport {
    int ID;
    int brugerID;
    int kilometerKoertOver;
    int reparationsomkostninger;
    List<Skade> skader;

    public SkadeRapport(int ID, int brugerID, int kilometerKoertOver, int reparationsomkostninger){
        this.ID = ID;
        this.brugerID = brugerID;
        this.kilometerKoertOver = kilometerKoertOver;
        this.reparationsomkostninger = reparationsomkostninger;
    }

    public SkadeRapport(int brugerID, int kilometerKoertOver, int reparationsomkostninger){
        this.brugerID = brugerID;
        this.kilometerKoertOver = kilometerKoertOver;
        this.reparationsomkostninger = reparationsomkostninger;
    }

    public SkadeRapport(int brugerID, int kilometerKoertOver, int reparationsomkostninger, List<Skade> valgteSkader){
        this.brugerID = brugerID;
        this.kilometerKoertOver = kilometerKoertOver;
        this.reparationsomkostninger = reparationsomkostninger;
    }

    public SkadeRapport(int ID, int brugerID,  int kilometerKoertOver, int reparationsomkostninger, List<Skade> skader){
        this.ID = ID;
        this.brugerID = brugerID;

        this.kilometerKoertOver = kilometerKoertOver;
        this.reparationsomkostninger = reparationsomkostninger;
        this.skader = skader;
    }

    public SkadeRapport() {
    }

    public int getID() {
        return ID;
    }

    public List<Skade> getSkader(){
        return skader;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public int getKilometerKoertOver() {
        return kilometerKoertOver;
    }

    public int getReparationsomkostninger() {
        return reparationsomkostninger;
    }
    public void setID(int skadeRapportID) {
        this.ID = ID;
    }

    public void setSkader(List<Skade> skader){
        this.skader =  skader;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public void setKilometerKoertOver(int kilometerKoertOver) {
        this.kilometerKoertOver = kilometerKoertOver;
    }

    public void setReparationsomkostninger(int reparationsomkostninger) {
        this.reparationsomkostninger = reparationsomkostninger;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "ID = " + ID + ", " +
                "brugerID = " + brugerID + ", " +
                "kilometerKoertOver = " + kilometerKoertOver + ", " +
                "reparationsomkostninger = " + reparationsomkostninger + ", " +
                "skader = " + skader + ")";
    }
}
