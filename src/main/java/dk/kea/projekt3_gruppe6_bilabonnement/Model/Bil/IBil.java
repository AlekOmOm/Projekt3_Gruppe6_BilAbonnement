package dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil;

import dk.kea.projekt3_gruppe6_bilabonnement.config.BilConfig;

public interface IBil {
    void setSomTilgaengelig();
    void setSomUdlejet();
    void setSomTilService();
    String getStatus();



}

