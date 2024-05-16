package dk.kea.projekt3_gruppe6_bilabonnement.test;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.CitroenC1;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Peugeot108;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.OpelCorsaCosmo;
import dk.kea.projekt3_gruppe6_bilabonnement.config.BilConfig;
import dk.kea.projekt3_gruppe6_bilabonnement.service.BilFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BilConfigTest {

    @Autowired
    private BilFactory bilFactory;

    @Autowired
    private BilConfig bilConfig;

    @BeforeEach
    void setUp() {
        bilConfig = new BilConfig();
    }

    @Test
    void shouldInitializeCitroenC1ModelConfig() {
        Bil bil = bilFactory.createCitroenC1();
        assertEquals("C1 Triumph", bil.getModel());
        assertEquals("Citroen", bil.getMaerke());
        assertEquals("Manuelt", bil.getGearType());
        assertEquals("Benzin", bil.getFuelType());
        assertEquals(99, bil.getCo2Emission());
        assertEquals(20.8, bil.getFuelConsumption());
        // Add more assertions for other fields
    }

    @Test
    void shouldInitializePeugeot108ModelConfig() {
        Bil bil = bilFactory.createPeugeot108();
        assertEquals("108", bil.getModel());
        assertEquals("Peugeot", bil.getMaerke());
        assertEquals("Manuelt", bil.getGearType());
        assertEquals("Benzin", bil.getFuelType());
        assertEquals(111, bil.getCo2Emission());
        assertEquals(20.4, bil.getFuelConsumption());
        // Add more assertions for other fields
    }

    @Test
    void shouldInitializeOpelCorsaCosmoModelConfig() {
        Bil bil = bilFactory.createOpelCorsaCosmo();
        assertEquals("Corsa Cosmo", bil.getModel());
        assertEquals("Opel", bil.getMaerke());
        assertEquals("Manuelt", bil.getGearType());
        assertEquals("Benzin", bil.getFuelType());
        assertEquals(123, bil.getCo2Emission());
        assertEquals(18.2, bil.getFuelConsumption());
        // Add more assertions for other fields
    }
}
