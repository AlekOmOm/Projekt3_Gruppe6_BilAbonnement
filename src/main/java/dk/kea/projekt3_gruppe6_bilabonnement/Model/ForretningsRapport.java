package dk.kea.projekt3_gruppe6_bilabonnement.Model;

import java.time.LocalDate;

public class ForretningsRapport {

    private int id;
    private LocalDate datoGenereret;
    private int totalBilerUdlejet;
    private int samletPris; // Make sure this variable name matches the one you use in your Thymeleaf template

    // Constructor
    public ForretningsRapport(int id, LocalDate datoGenereret, int totalBilerUdlejet, int samletPris) {
        this.id = id;
        this.datoGenereret = datoGenereret;
        this.totalBilerUdlejet = totalBilerUdlejet;
        this.samletPris = samletPris;
    }

    // Empty constructor
    public ForretningsRapport() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

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

    public int getSamletPris() { // Make sure this getter is named correctly
        return samletPris;
    }

    public void setSamletPris(int samletPris) {
        this.samletPris = samletPris;
    }
}