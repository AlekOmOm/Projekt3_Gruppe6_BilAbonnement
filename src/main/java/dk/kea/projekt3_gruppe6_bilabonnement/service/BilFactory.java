package dk.kea.projekt3_gruppe6_bilabonnement.service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.CitroenC1;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.OpelCorsaCosmo;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Peugeot108;
import dk.kea.projekt3_gruppe6_bilabonnement.config.BilConfig;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class BilFactory {

    private final BilConfig bilConfig;

    @Autowired
    public BilFactory(BilConfig bilConfig) {
        this.bilConfig = bilConfig;
    }

    public Bil createCitroenC1() {
        return bilConfig.initializeModelConfig(new CitroenC1());
    }

    public Bil createPeugeot108() {
        return bilConfig.initializeModelConfig(new Peugeot108());
    }

    public Bil createOpelCorsaCosmo() {
        return bilConfig.initializeModelConfig(new OpelCorsaCosmo());
    }
}