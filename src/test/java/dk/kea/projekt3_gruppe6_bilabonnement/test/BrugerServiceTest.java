package dk.kea.projekt3_gruppe6_bilabonnement.test;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BrugerRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.config.InitData;
import dk.kea.projekt3_gruppe6_bilabonnement.service.BrugerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BrugerServiceTest {

    private final BrugerRepository brugerRepository;
    private final BrugerService brugerService;


    private static List<Bruger> brugere = new ArrayList<>();
    private Bruger nyTestBruger;

    @Autowired
    public BrugerServiceTest (BrugerRepository brugerRepository, BrugerService brugerService) {
        this.brugerRepository = brugerRepository;
        this.brugerService = brugerService;
    }

    // Tests af alle operationer i BrugerService
        // 0. setUp (BeforeAll)
        // 1. testOpretBruger
        // 2. testHentAlleBrugere
        // 3. testHentBruger
        // 4. testHentBrugerByBrugerNavn
        // 5. testBrugerEksisterer
        // 6. testOpdaterBruger
        // 7. testSletBruger

    @BeforeAll
    public static void setUp() {
        brugere = InitData.getBrugere();

    }



    @Test
    public void testOpretBruger() {
        nyTestBruger = new Bruger("nyTestBruger1", "password1", "DATA_REGISTRERING");

        Bruger oprettetBruger = brugerService.opretBruger(nyTestBruger);

        assertTrue(oprettetBruger.equalsWithoutId(nyTestBruger));
    }

    @Test
    public void testHentAlleBrugere() {
        nyTestBruger = new Bruger("testHentAlleBrugere1", "password1", "DATA_REGISTRERING");

        // start state af DB
        List<Bruger> initialStateAfDB = brugerService.hentAlleBrugere();

        // opretter og gemmer ny bruger
        nyTestBruger = brugerService.opretBruger(nyTestBruger);

        // opdateret state af DB
        List<Bruger> opdateretStateAfDB = brugerService.hentAlleBrugere();

        // debug
        System.out.println("DEBUG: testHentAlleBrugere");
        System.out.println(" Brugere foer oprettelse: ");
        printBrugere(initialStateAfDB);
        System.out.println();
        System.out.println(" Brugere efter oprettelse: ");
        printBrugere(opdateretStateAfDB);
        System.out.println();

        System.out.println(" tests: ");
        System.out.println(" ");

        System.out.println(" 1: size:" + initialStateAfDB.size() + " -> " + opdateretStateAfDB.size());

        initialStateAfDB.add(nyTestBruger);
        System.out.println(" 2: user added ");
        System.out.println();
        System.out.println(" 3: size:" + initialStateAfDB.size() + " -> " + opdateretStateAfDB.size());

        System.out.println(" => " + initialStateAfDB.size() + " == " + opdateretStateAfDB.size());
        assertTrue(initialStateAfDB.size() == opdateretStateAfDB.size());

        System.out.println();
        System.out.println("DEBUG END - testHentAlleBrugere /n");
    }

    @Test
    public void testHentBruger() {
        // debug
        System.out.println("DEBUG: testHentBruger");

        nyTestBruger = new Bruger("testHentBruger1", "password1", "DATA_REGISTRERING");

        nyTestBruger = brugerService.opretBruger(nyTestBruger);
        assertTrue(nyTestBruger.getId() > 0);

        System.out.println(" id: " + nyTestBruger.getId());

        Bruger hentetBruger = brugerService.hentBruger(nyTestBruger.getId());

        assertTrue(hentetBruger.equalsWithoutId(nyTestBruger));
    }

    @Test
    public void testHentBrugerByBrugerNavn() {
        nyTestBruger = new Bruger("testHentBrugerByBrugerNavn1", "password1", "DATA_REGISTRERING");

        brugerService.opretBruger(nyTestBruger);

        Bruger hentetBruger = brugerService.hentBruger(nyTestBruger.getBrugerNavn());

        assertTrue(hentetBruger.equalsWithoutId(nyTestBruger));
    }

    @Test
    public void testBrugerEksisterer() {
        nyTestBruger = new Bruger("testBrugerEksisterer1", "password1", "DATA_REGISTRERING");

        brugerService.opretBruger(nyTestBruger);

        boolean brugerEksisterer = brugerService.brugerEksisterer(nyTestBruger);

        assertTrue(brugerEksisterer);
    }

    @Test
    public void testOpdaterBruger() {
        nyTestBruger = new Bruger(1000, "testOpdaterBruger1", "password1", "DATA_REGISTRERING");

        nyTestBruger = brugerService.opretBruger(nyTestBruger);

        Bruger opdaterBruger = nyTestBruger;
            assertTrue(opdaterBruger.equals(nyTestBruger));

            opdaterBruger.setBrugerNavn("testOpdaterBruger1");
            opdaterBruger.setPassword("password2");
            opdaterBruger.setRolle("SKADE_OG_UDBEDRING");


        Bruger opdateretBruger = brugerService.opdaterBruger(opdaterBruger); // testBruger burde være opdateret nu

        nyTestBruger = brugerService.hentBruger(nyTestBruger.getId()); // opdaterer testBruger for klassens andre tests

        assertTrue(opdateretBruger.equalsWithoutId(nyTestBruger));
        assert opdateretBruger.equals(nyTestBruger);
    }

    @Test
    public void testSletBruger() {
        nyTestBruger = new Bruger("testSletBruger1", "password1", "DATA_REGISTRERING");
        nyTestBruger = brugerService.opretBruger(nyTestBruger);

        List<Bruger> brugereFoerSlet = brugerService.hentAlleBrugere();

        brugerService.sletBruger(nyTestBruger);

        List<Bruger> brugereEfterSlet = brugerService.hentAlleBrugere();

        System.out.println("DEBUG: testSletBruger");
        System.out.print(" Brugere før slet: ");
        printBrugere(brugereFoerSlet);
        System.out.println();
        System.out.println(" Brugere efter slet: ");
        printBrugere(brugereEfterSlet);
        System.out.println();
        System.out.println(" size:" + brugereFoerSlet.size() + " -> " + brugereEfterSlet.size());

        assertFalse(brugereEfterSlet.contains(nyTestBruger));
        assertEquals(brugereFoerSlet.size()-1, brugereEfterSlet.size());
    }

    @AfterEach
    public void sletOprettetTestBrugerFraDB() {
        System.out.println();
        System.out.println("test ended");
        System.out.println(" clean up");
        brugerService.sletBruger(nyTestBruger);
        System.out.println(" nyTestBruger slettet");
        System.out.println();
    }



    private void printBrugere(List<Bruger> brugere) {
        for (Bruger bruger : brugere) {
            System.out.println(bruger.getBrugerNavn());
        }
    }
}
