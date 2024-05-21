package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.CitroenC1;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.OpelCorsaCosmo;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Peugeot108;
import dk.kea.projekt3_gruppe6_bilabonnement.config.BilConfig;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class BilFactory {

    private final BilConfig bilConfig;

    @Autowired
    public BilFactory(BilConfig bilConfig) {
        this.bilConfig = bilConfig;
    }

    // convert Bil to CitroenC1, Peugeot108, OpelCorsaCosmo with the instance variables



    public Bil initialize(Bil bil) {
        if (bil == null) {
            return null;
        }

//        System.out.println("DEBUG: BilFactory.initialize");
//        System.out.println(" bil: "+bil);
        Bil initBil = bilConfig.loadModelConfigData(bil);
//        System.out.println(" initBil: "+initBil);

        initBil.setId(bil.getId());
//        System.out.println(" initBil.getId(): "+initBil.getId());
        return initBil;
    }

    // create for each Bil model med empty og fuld instans variabler
    public Bil createCitroenC1() {
        return bilConfig.loadModelConfigData(new CitroenC1());
    }


    public Bil createCitroenC1(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        return bilConfig.loadModelConfigData(new CitroenC1(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status));
    }


    public Bil createPeugeot108() {
        return bilConfig.loadModelConfigData(new Peugeot108());
    }

    public Bil createPeugeot108(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        return bilConfig.loadModelConfigData(new Peugeot108(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status));
    }


    public Bil createOpelCorsaCosmo() {
        return bilConfig.loadModelConfigData(new OpelCorsaCosmo());
    }

    public Bil createOpelCorsaCosmo(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        return bilConfig.loadModelConfigData(new OpelCorsaCosmo(vognNummer, stelNummer, udstyrsNiveau, kilometerKoert, status));
    }

    public List<Bil> initializeList(List<Bil> all) {

        for (Bil bil : all) {
            initialize(bil);
        }
        return all;

    }

    // ------------------- Create Test Biler -------------------
    private static int testCounter = 0;

    public Bil createTestCitroenC1() {
        testCounter++;
        System.out.println();
//        System.out.println(" testCounter: "+testCounter);
        return bilConfig.loadModelConfigData(new CitroenC1("VognNummer1"+testCounter, "StelNummer1"+testCounter, "UdstyrsNiveau1"+testCounter, 1000+testCounter, "Tilgaengelig"));
    }

    public Bil createTestPeugeot108() {
        testCounter++;
        return bilConfig.loadModelConfigData(new Peugeot108("VognNummer2"+testCounter, "StelNummer2"+testCounter, "UdstyrsNiveau2"+testCounter, 2000+testCounter, "Udlejet"));
    }

    public Bil createTestOpelCorsaCosmo() {
        testCounter++;
        return bilConfig.loadModelConfigData(new OpelCorsaCosmo("VognNummer3"+testCounter, "StelNummer3"+testCounter, "UdstyrsNiveau3"+testCounter, 3000+testCounter, "Til service"));
    }

}
