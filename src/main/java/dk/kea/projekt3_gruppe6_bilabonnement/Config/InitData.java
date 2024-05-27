package dk.kea.projekt3_gruppe6_bilabonnement.Config;


import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BilRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BrugerRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRapportRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class InitData implements ApplicationRunner {

    private final BilFactory bilFactory;
    private final BilRepository bilRepository;
    private final BrugerRepository brugerRepository;
    private final LejeAftaleRepository lejeaftaleRepository;
    private final SkadeRapportRepository skadeRapportRepository;
    private final BrugerService brugerService;
    private final LejeAftaleService lejeAftaleService;
    private final KundeInfoService kundeInfoService;

    private static List<Bruger> brugere = new ArrayList<>();
    private static List<Bil> biler = new ArrayList<>();
    private static List<KundeInfo> kundeInfos = new ArrayList<>();
    private static List<LejeAftale> lejeAftaler = new ArrayList<>();
    private static List<SkadeRapport> skadeRapporter = new ArrayList<>();
    private final BilService bilService;
//    List<ForretningsRapport> forretningsRapporter = new ArrayList<>();

    @Autowired
    public InitData(BilFactory bilFactory, BilRepository bilRepository, BrugerRepository brugerRepository, LejeAftaleRepository lejeaftaleRepository, SkadeRapportRepository skadeRapportRepository, BilService bilService, BrugerService brugerService, LejeAftaleService lejeAftaleService, KundeInfoService kundeInfoService) {
        this.bilFactory = bilFactory;
        this.bilRepository = bilRepository;
        this.brugerRepository = brugerRepository;
        this.lejeaftaleRepository = lejeaftaleRepository;
        this.skadeRapportRepository = skadeRapportRepository;
        this.bilService = bilService;
        this.brugerService = brugerService;
        this.lejeAftaleService = lejeAftaleService;
        this.kundeInfoService = kundeInfoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // ------------------- Bil test data -------------------

        bilData(); // Bil test data - tre biler: Citroen, Peugeot og Opel

        // ------------------- Bruger test data -------------------

        brugerTestData(); // Bruger test data - tre brugere: dataregistrering, skade- og udbedring og forretningsudvikling

        // ------------------- KundeInfo test data -------------------
        kundeInfoTestData();

        // ------------------- LejeAftale test data -------------------

        lejeAftaleTestData();

        // ------------------- SkadeRapport test data -------------------


    }



    private void brugerTestData() {

        // tilfældige værdier er givet ved oprettelse af brugere
        brugere.addAll(Arrays.asList(
                new Bruger("dataregistrering", "1234", "DATA_REGISTRERING"),
                new Bruger("skade-og-udbedring", "1234", "SKADE_OG_UDBEDRING"),
                new Bruger("forretningsudvikling", "1234", "FORRETNINGS_UDVIKLING")
        ));

        // tjek om brugere allerede findes i database og fjern dem fra listen
        for(Iterator<Bruger> iterator = brugere.iterator(); iterator.hasNext();) {

            Bruger bruger = iterator.next();
            if (brugerRepository.exists(bruger)) {
                iterator.remove();
            }
        }

        for (Bruger bruger : brugere) {
            brugerRepository.save(bruger);
        }
    }

    private void bilData() {
        // 15 biler - 5 af hver type
        addBilerTilListe();


        // tilfældige værdier for hver bil
        addRandomValues();

        // tjek om biler allerede findes i database og fjern dem fra listen
        checkIfExistsInDB();


        // Save biler (hvis nye) til database
        saveAll();
    }



    private void kundeInfoTestData() {
        kundeInfos.addAll(Arrays.asList(
                new KundeInfo("212286-1234", "Test", "Testesen", "Testvej 1", 1234, "test@mail.com", 12345678),
                new KundeInfo("212286-1235", "Test2", "Testesen2", "Testvej 2", 1235, "test2@mail.com", 12345679),
                new KundeInfo("212286-1236", "Test3", "Testesen3", "Testvej 3", 1236, "test3@mail.com", 12345680)
        ));

        // tjek om kundeInfo allerede findes i database og fjern dem fra listen
        for (Iterator<KundeInfo> iterator = kundeInfos.iterator(); iterator.hasNext();) {

            KundeInfo kundeInfo = iterator.next();
            if (kundeInfoService.exists(kundeInfo)) {
                iterator.remove();
            }
        }

        // Save kundeInfo (hvis nye) til database
        for (int i = 0; i<kundeInfos.size(); i++) {
            KundeInfo kundeInfo = kundeInfos.get(i);
            if (kundeInfo != null) {
                kundeInfoService.save(kundeInfo);
            }
        }
    }

    private void lejeAftaleTestData() {
        brugere = brugerRepository.findAll();
        biler = bilRepository.findAll();
        kundeInfos = kundeInfoService.findAll();

        lejeAftaler.addAll(Arrays.asList(
                // LejeAftale fields: Bruger bruger, KundeInfo kundeInfo, Bil bil, SkadeRapport skadeRapport, String farve, boolean afleveringsforsikring, boolean selvrisiko, boolean daekpakke, boolean vejhjaelp, boolean udleveringVedFDM, int abonnementslaengde, int kmPrMdr, String afhentningssted, LocalDate startDato, LocalDate slutDato
                new LejeAftale(brugere.get(0), kundeInfos.get(0), biler.get(0), null, "Red", true, true, true, true, true, 12, 10000, "Testvej 1", LocalDate.now(), LocalDate.now().plusMonths(12), 10000),
                new LejeAftale(brugere.get(1), kundeInfos.get(1), biler.get(1), null, "Blue", true, true, true, true, true, 12, 10000, "Testvej 2", LocalDate.now(), LocalDate.now().plusMonths(12), 10000),
                new LejeAftale(brugere.get(2), kundeInfos.get(2), biler.get(2), null, "Green", true, true, true, true, true, 12, 10000, "Testvej 3", LocalDate.now(), LocalDate.now().plusMonths(12), 10000)
        ));

        // tjek om biler allerede findes i database og fjern dem fra listen
        for (Iterator<LejeAftale> iterator = lejeAftaler.iterator(); iterator.hasNext();) {

            LejeAftale lejeAftale = iterator.next();
            if (lejeAftaleService.exists(lejeAftale)) {
                iterator.remove();
            }
        }


        // Save biler (hvis nye) til database
        for (int i = 0; i<lejeAftaler.size(); i++) {
            LejeAftale lejeAftale = lejeAftaler.get(i);
            if (lejeAftale != null) {
                lejeaftaleRepository.save(lejeAftale);
            }
        }

    }

    // ------------------- get Data objects -------------------
    public static List<Bruger> getBrugere() {
        return brugere;
    }

    public List<Bil> getBiler() {
        return biler;
    }

    public List<LejeAftale> getLejeAftaler() {
        return lejeAftaler;
    }

    public List<SkadeRapport> getSkadeRapporter() {
        return skadeRapporter;
    }



    // ------------------- Bil Data - Helper methods -------------------


    private void addBilerTilListe() {
        for (int i = 0; i<5; i++) {
            biler.addAll(Arrays.asList(
                    bilFactory.createCitroenC1(),
                    bilFactory.createPeugeot108(),
                    bilFactory.createOpelCorsaCosmo()
            ));
        }
    }


    private void addRandomValues() {
        for (int i = 0; i<biler.size(); i++) {
            Bil bil = biler.get(i);
            if (bil != null) {
                bil.setVognNummer("VognNummer" + i);
                bil.setStelNummer("StelNummer" + i);
                bil.setUdstyrsNiveau("UdstyrsNiveau" + i);
                bil.setKilometerKoert(1000 * i);
                bil.setSomTilgaengelig();
            }
        }
    }



    private void checkIfExistsInDB() {
        for (Iterator<Bil> iterator = biler.iterator(); iterator.hasNext();) {
            // Iterator bruges på biler for at undgå ConcurrentModificationException, dvs. listen håndteres samtidig med at elementer fjernes
            // iterator med for loop fungerer her ved at iterator.remove() kaldes, når et element fjernes

            Bil bil = iterator.next();
            if (bilService.exists(bil)) {
                iterator.remove();
            }
        }
    }


    private void saveAll() {
        for (int i = 0; i<biler.size(); i++) {
            Bil bil = biler.get(i);
            bil.setSomTilgaengelig();
            if (bil != null) {
                bilRepository.save(bil);
            }
        }
    }

}