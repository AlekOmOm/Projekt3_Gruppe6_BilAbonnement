package dk.kea.projekt3_gruppe6_bilabonnement.Config;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import org.springframework.stereotype.Service;

@Service
public class BilConfig {

    public Bil loadModelConfigData(Bil bil) {
        if (bil == null) {
            return null;
        }

        String model = bil.getModel();

        return load(model, bil);
    }

    private Bil load(String model, Bil bil) {
        switch (model) {
            case "Citroen C1 Triumph":
                return CitroenC1Config.initialize(bil);
            case "Peugeot 108":
                return Peugeot108Config.initialize(bil);
            case "Opel Corsa Cosmo":
                return OpelCorsaCosmoConfig.initialize(bil);
        }
        return null;
    }

    static class CitroenC1Config {

        protected static Bil initialize(Bil bil) {

            bil.setGearType(GEAR_TYPE);
            bil.setFuelType(FUEL_TYPE);
            bil.setCo2Emission(CO2_EMISSION);
            bil.setFuelConsumption(FUEL_CONSUMPTION);
            bil.setEquipmentHighlightText(EQUIPMENT_HIGHLIGHT_TEXT);
            bil.setUdbetaling(UDBETALING);
            bil.setMdlYdelse(MDL_YDELSE);
            bil.setBindingMdr(BINDING_MDR);
            bil.setSamletYdelse36Maaneder(SAMLET_YDELSE_36_MAANEDER);
            bil.setLeveringTid(LEVERING_TID);
            bil.setLeveringsstedStandard(LEVERINGSSTED_STANDARD);
            bil.setKoerselPrMd(KOERSEL_PR_MD);
            bil.setSpecifikkeEgenskaber(SPECIFIKKE_EGENSKABER);
            bil.setBagagerumsStoerrelse(BAGAGERUMS_STOERRELSE);
            bil.setSelvrisiko(SELVRISIKO);
            return bil;
        }


        private static final String GEAR_TYPE = "Manuelt";
        private static final String FUEL_TYPE = "Benzin";
        private static final double CO2_EMISSION = 99;
        private static final double FUEL_CONSUMPTION = 20.8;
        private static final String EQUIPMENT_HIGHLIGHT_TEXT =
                "Kommer bl.a. med Apple CarPlay, fuldaut. klima og LEDKoerelys.";
        private static final int UDBETALING = 0;
        private static final int MDL_YDELSE = 2599;
        private static final int BINDING_MDR = 36;
        private static final int SAMLET_YDELSE_36_MAANEDER = 93564;
        private static final String LEVERING_TID = "2 dage";
        private static final String LEVERINGSSTED_STANDARD = "Vibeholmsvej 31 - 2605 Broendby";
        private static final int KOERSEL_PR_MD = 1500;
        private static final String SPECIFIKKE_EGENSKABER =
                "Citroen C1 er udstyret med bl.a. Apple CarPlay, klimaanlaeg, nedfoeldeligt splitbagsaede, varme i forsaeder, mv.";
        private static final int BAGAGERUMS_STOERRELSE = 168;
        private static final int SELVRISIKO = 5000;
    }

    static class Peugeot108Config {

        protected static Bil initialize(Bil bil) {
            bil.setGearType(GEAR_TYPE);
            bil.setFuelType(FUEL_TYPE);
            bil.setCo2Emission(CO2_EMISSION);
            bil.setFuelConsumption(FUEL_CONSUMPTION);
            bil.setEquipmentHighlightText(EQUIPMENT_HIGHLIGHT_TEXT);
            bil.setUdbetaling(UDBETALING);
            bil.setMdlYdelse(MDL_YDELSE);
            bil.setBindingMdr(BINDING_MDR);
            bil.setSamletYdelse36Maaneder(SAMLET_YDELSE_36_MAANEDER);
            bil.setLeveringTid(LEVERING_TID);
            bil.setLeveringsstedStandard(LEVERINGSSTED_STANDARD);
            bil.setKoerselPrMd(KOERSEL_PR_MD);
            bil.setSpecifikkeEgenskaber(SPECIFIKKE_EGENSKABER);
            return bil;
        }



        private static final String GEAR_TYPE = "Manuelt";
        private static final String FUEL_TYPE = "Benzin";
        private static final double CO2_EMISSION = 111;
        private static final double FUEL_CONSUMPTION = 20.4;
        private static final String EQUIPMENT_HIGHLIGHT_TEXT =
                "LED Koerelys, Aircondition og Radio 'High'";
        private static final int UDBETALING = 0;
        private static final int MDL_YDELSE = 2599;
        private static final int BINDING_MDR = 36;
        private static final int SAMLET_YDELSE_36_MAANEDER = 93564;
        private static final String LEVERING_TID = "2 dage";
        private static final String LEVERINGSSTED_STANDARD = "Vibeholmsvej 31 - 2605 Broendby";
        private static final int KOERSEL_PR_MD = 1500;
        private static final String SPECIFIKKE_EGENSKABER =
                "LED koerelys, LED baglygter, Aircondition, \"High\" Radio, Foerersaede, hoejdejusterbart, El-ruder, for, Oplukkelige vinduer, bag, Centrallaas, fjernbetjent, Hill Start Assist";
        private static final int BAGAGERUMS_STOERRELSE = 168;
        private static final int SELVRISIKO = 5000;
    }

    static class OpelCorsaCosmoConfig {



        protected static Bil initialize(Bil bil) {

            bil.setGearType(GEAR_TYPE);
            bil.setFuelType(FUEL_TYPE);
            bil.setCo2Emission(CO2_EMISSION);
            bil.setFuelConsumption(FUEL_CONSUMPTION);
            bil.setEquipmentHighlightText(EQUIPMENT_HIGHLIGHT_TEXT);
            bil.setUdbetaling(UDBETALING);
            bil.setMdlYdelse(MDL_YDELSE);
            bil.setBindingMdr(BINDING_MDR);
            bil.setSamletYdelse36Maaneder(SAMLET_YDELSE_36_MAANEDER);
            bil.setLeveringTid(LEVERING_TID);
            bil.setLeveringsstedStandard(LEVERINGSSTED_STANDARD);
            bil.setKoerselPrMd(KOERSEL_PR_MD);
            bil.setSpecifikkeEgenskaber(SPECIFIKKE_EGENSKABER);
            return bil;
        }



        private static final String GEAR_TYPE = "Manuelt";
        private static final String FUEL_TYPE = "Benzin";
        private static final double CO2_EMISSION = 123;
        private static final double FUEL_CONSUMPTION = 18.2;
        private static final String EQUIPMENT_HIGHLIGHT_TEXT =
                "Kommer bl.a. med Apple CarPlay, 7\" Touchskaerm og DAB-radio.";
        private static final int UDBETALING = 0;
        private static final int MDL_YDELSE = 2599;
        private static final int BINDING_MDR = 36;
        private static final int SAMLET_YDELSE_36_MAANEDER = 115164;
        private static final String LEVERING_TID = "2 dage";
        private static final String LEVERINGSSTED_STANDARD = "Vibeholmsvej 31 - 2605 Broendby";
        private static final int KOERSEL_PR_MD = 1500;
        private static final String SPECIFIKKE_EGENSKABER =
                "16\" foelge, 6 airbags, 7\" touchsk�rm m. farve, Active Safety Brake, Aircondition, Apple Carplay�, Bluetooth�, DAB-radio, Doereger i bilens farve, E-luder foran, ESP, Fjernbetjent centrallaas, Hill Assist, Hoejdejustering af foerersaede, ISOFIX-beslag p� yderpladser bag, Manuel delaktivering af passagerfrontairbag, Ratbetjent radio, Styl�b�k og h�kspoiler, T�geprojekt�rer, Tonede bagruder";
    }

}
