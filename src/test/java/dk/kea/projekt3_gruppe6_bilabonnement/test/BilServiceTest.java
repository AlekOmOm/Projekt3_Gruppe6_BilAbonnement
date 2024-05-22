package dk.kea.projekt3_gruppe6_bilabonnement.test;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BilServiceTest {

    @Autowired
    private BilService bilService;

    @Autowired
    private BilFactory bilFactory;

    private Bil testBil;

    @BeforeEach
    public void init() {
        testBil = bilFactory.createTestCitroenC1();
    }

    @Test
    public void testCreateBil() {
        Bil createdBil = bilService.saveBil(testBil);

        assertNotNull(createdBil);

        assertEquals("Citroen C1 Triumph", createdBil.getModel());
    }

    @Test
    public void testGetAlleBiler() {
        List<Bil> biler = bilService.getAlleBiler();
        assertFalse(biler.isEmpty());
    }

    @Test
    public void testGetBil() {
        Bil createdBil = bilService.saveBil(testBil);
        Bil result = bilService.getBil(createdBil.getId());
        assertEquals(createdBil.getId(), result.getId());
    }

    @Test
    public void testGetBilByVognNummer() {
        Bil createdBil = bilService.saveBil(testBil);
        Bil result = bilService.getBilByVognNummer(createdBil.getVognNummer());
        assertEquals(createdBil.getId(), result.getId());
    }

    @Test
    public void testUpdateBil() {
        Bil createdBil = bilService.saveBil(testBil);
        createdBil.setKilometerKoert(2000);
        Bil updatedBil = bilService.update(createdBil);
        assertEquals(2000, updatedBil.getKilometerKoert());
    }

    @Test
    public void testDeleteBil() {
        System.out.println("DEBUG: BilServiceTest.testDeleteBil");
        System.out.println(" 1: saveBil");
        Bil createdBil = bilService.saveBil(testBil);
        System.out.println(" createdBil: "+createdBil);
        System.out.println(" testBil: "+testBil);
        assertEquals(testBil, createdBil);

        System.out.println(" 2: delete");
        int id = createdBil.getId();
        bilService.delete(createdBil);
        System.out.println(" id: "+id);
        System.out.println(" createdBil.getId(): "+createdBil.getId());

        System.out.println(" 3: getBil");
        Bil deletedBil = bilService.getBil(id);

        System.out.println(" createdBil: "+createdBil);
        System.out.println(" deletedBil: "+deletedBil);
        assertNull(deletedBil);
        System.out.println("-----DEBUG end: BilServiceTest.testDeleteBil");
    }
}