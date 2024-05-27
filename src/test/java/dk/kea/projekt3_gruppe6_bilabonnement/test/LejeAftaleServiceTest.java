package dk.kea.projekt3_gruppe6_bilabonnement.test;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BilRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BrugerRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BilFactory;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BilService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.KundeInfoService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.LejeAftaleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LejeAftaleServiceTest {

    private final LejeAftaleRepository lejeAftaleRepository;
    private final LejeAftaleService lejeAftaleService;
    private final BilFactory bilFactory;
    private final BilRepository bilRepository;
    private final BrugerRepository brugerRepository;
    @Autowired
    private KundeInfoService kundeInfoService;
    @Autowired
    private BilService bilService;

    @Autowired
    public LejeAftaleServiceTest(LejeAftaleRepository lejeAftaleRepository, LejeAftaleService lejeAftaleService, BilFactory bilFactory, BilRepository bilRepository, BrugerRepository brugerRepository) {
        this.lejeAftaleRepository = lejeAftaleRepository;
        this.lejeAftaleService = lejeAftaleService;
        this.bilFactory = bilFactory;
        this.bilRepository = bilRepository;
        this.brugerRepository = brugerRepository;
    }

    private static List<LejeAftale> lejeAftaler = new ArrayList<>();
    private LejeAftale nyLejeAftale;
    // String CPR_NR, String Fornavn, String Efternavn, String Adresse, int PostNummer, String Email, int MobilNummer) {
    private final KundeInfo kundeInfo = new KundeInfo("212286-1234", "Test", "Testesen", "Testvej 1", 1234, "test@mail.com", 12345678);

    private List<Bruger> brugere;
    private List<Bil> biler;


    // Tests af alle operationer i BrugerService
        // 1. testOpretLejeAftale

    @BeforeEach
    public void setUp() {
        brugere = brugerRepository.findAll();
        biler = bilRepository.findAll();
    }

    @Test
    public void testOpretLejeAftale() {

        // public LejeAftale(Bruger bruger, KundeInfo kundeInfo, Bil bil, SkadeRapport skadeRapport, String farve, boolean afleveringsforsikring, boolean selvrisiko, boolean daekpakke, boolean vejhjaelp, boolean udleveringVedFDM, int abonnementslaengde, int kmPrMdr, String afhentningssted, LocalDate startDato, LocalDate slutDato, int totalPris) {
        nyLejeAftale = new LejeAftale(brugere.get(0), kundeInfo, bilService.getInstance(biler.get(0).getModel()), null, "Roed", true, true, true, true, true, 12, 1000, "Testvej 1", LocalDate.now(), LocalDate.now().plusMonths(12), 1);

        LejeAftale oprettetLejeAftale = lejeAftaleService.save(nyLejeAftale);

        assertNotNull(oprettetLejeAftale);
    }


}

/*
LejeAftale fields:
    private Bruger bruger;      // Composition
    private KundeInfo kundeInfo; // Composition
    private SkadeRapport skadeRapport = null; // Aggregation

    // 1. Bil valg
    private Bil bil;            // Composition

    // 2. Abonnement side
    private String farve;
    private boolean afleveringsforsikring;
    private boolean selvrisiko;
    private boolean daekpakke;
    private boolean vejhjaelp;
    private boolean udleveringVedFDM;

    // 3. Prisoverslag
    private int abonnementslaengde;
    private int kmPrMdr;

    // 4. Afhentningssted
    private String afhentningssted;

    // Instance variabler sat ved Save
    private LocalDate startDato;
    private LocalDate slutDato;
    private int totalPris;
 */