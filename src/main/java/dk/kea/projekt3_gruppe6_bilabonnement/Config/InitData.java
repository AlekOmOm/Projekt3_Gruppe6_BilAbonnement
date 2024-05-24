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
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BilFactory;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BilService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BrugerService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.LejeAftaleService;
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

    private static List<Bruger> brugere = new ArrayList<>();
    private static List<Bil> biler = new ArrayList<>();
    private static List<LejeAftale> lejeAftaler = new ArrayList<>();
    private static List<SkadeRapport> skadeRapporter = new ArrayList<>();
    private final BilService bilService;
//    List<ForretningsRapport> forretningsRapporter = new ArrayList<>();

    @Autowired
    public InitData(BilFactory bilFactory, BilRepository bilRepository, BrugerRepository brugerRepository, LejeAftaleRepository lejeaftaleRepository, SkadeRapportRepository skadeRapportRepository, BilService bilService, BrugerService brugerService, LejeAftaleService lejeAftaleService) {
        this.bilFactory = bilFactory;
        this.bilRepository = bilRepository;
        this.brugerRepository = brugerRepository;
        this.lejeaftaleRepository = lejeaftaleRepository;
        this.skadeRapportRepository = skadeRapportRepository;
        this.bilService = bilService;
        this.brugerService = brugerService;
        this.lejeAftaleService = lejeAftaleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // ------------------- Bil test data -------------------

        bilTestData(); // Bil test data - tre biler: Citroen, Peugeot og Opel

        // ------------------- Bruger test data -------------------

        brugerTestData(); // Bruger test data - tre brugere: dataregistrering, skade- og udbedring og forretningsudvikling


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

    private void bilTestData() {
        biler.addAll(Arrays.asList(
                bilFactory.createCitroenC1(),
                bilFactory.createPeugeot108(),
                bilFactory.createOpelCorsaCosmo()
        ));


        // tilfældige værdier for hver bil
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

        // tjek om biler allerede findes i database og fjern dem fra listen
        for (Iterator<Bil> iterator = biler.iterator(); iterator.hasNext();) {
            // Iterator bruges på biler for at undgå ConcurrentModificationException, dvs. listen håndteres samtidig med at elementer fjernes
            // iterator med for loop fungerer her ved at iterator.remove() kaldes, når et element fjernes

            Bil bil = iterator.next();
            if (bilService.exists(bil)) {
                iterator.remove();
            }
        }


        // Save biler (hvis nye) til database
        for (int i = 0; i<biler.size(); i++) {
            Bil bil = biler.get(i);
            if (bil != null) {
                bilRepository.save(bil);
            }
        }
    }

    private void lejeAftaleTestData() {
        brugere = brugerRepository.findAll();
        biler = bilRepository.findAll();

        lejeAftaler.addAll(Arrays.asList(
                new LejeAftale(brugere.get(0), new KundeInfo(), biler.get(0), "AbonnementsType1", 1000, "Afhentningssted1", "Afleveringssted1", LocalDate.now(), LocalDate.now().plusDays(7), 0),
                new LejeAftale(brugere.get(1), new KundeInfo(), biler.get(1), "AbonnementsType2", 2000, "Afhentningssted2", "Afleveringssted2", LocalDate.now(), LocalDate.now().plusDays(7), 0),
                new LejeAftale(brugere.get(2), new KundeInfo(), biler.get(2), "AbonnementsType3", 3000, "Afhentningssted3", "Afleveringssted3", LocalDate.now(), LocalDate.now().plusDays(7), 0)
        ));

        // tjek om biler allerede findes i database og fjern dem fra listen
        for (Iterator<LejeAftale> iterator = lejeAftaler.iterator(); iterator.hasNext();) {

            LejeAftale lejeAftale = iterator.next();
            if (lejeAftaleService.exists(lejeAftale)) {
                iterator.remove();
            }
        }


        // Save biler (hvis nye) til database
        for (int i = 0; i<biler.size(); i++) {
            Bil bil = biler.get(i);
            if (bil != null) {
                bilRepository.save(bil);
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



}