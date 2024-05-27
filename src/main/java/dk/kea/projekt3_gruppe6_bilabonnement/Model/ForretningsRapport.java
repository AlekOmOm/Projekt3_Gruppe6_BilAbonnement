package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import java.time.LocalDate;

public class ForretningsRapport {

    private int id;
    private LocalDate datoGenereret;
    private int totalBilerUdlejet;
    private int samletPris;

    public ForretningsRapport(int id, LocalDate datoGenereret, int totalBilerUdlejet, int samletPris) {
        this.id = id;
        this.datoGenereret = datoGenereret;
        this.totalBilerUdlejet = totalBilerUdlejet;
        this.samletPris = samletPris;
    }

    //empty constructor
    public ForretningsRapport() {
    }

    public int getId() {
        return id;
    }

    //getter and setters
    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatoGenereret() {
        return datoGenereret;
    }

    public void setDatoGenereret(LocalDate datoGenereret) {
        this.datoGenereret = datoGenereret;
    }

    public int getTotalBilerUdlejet() {
        return totalBilerUdlejet;
    }

    public void setTotalBilerUdlejet(int totalBilerUdlejet) {
        this.totalBilerUdlejet = totalBilerUdlejet;
    }

    public int gettotalPris() {
        return samletPris;
    }
    public void setSamletPris(int samletPris) {
        this.samletPris = samletPris;
    }
}