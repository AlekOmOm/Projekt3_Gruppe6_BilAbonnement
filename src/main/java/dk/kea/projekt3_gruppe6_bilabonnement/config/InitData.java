package dk.kea.projekt3_gruppe6_bilabonnement.config;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BilRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRapportRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.service.BilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class InitData implements ApplicationRunner {

    private final BilFactory bilFactory;
    private final BilRepository bilRepository;
    private final LejeAftaleRepository lejeaftaleRepository;
    private final SkadeRapportRepository skadeRapportRepository;

    @Autowired
    public InitData(BilFactory bilFactory, BilRepository bilRepository, LejeAftaleRepository lejeaftaleRepository, SkadeRapportRepository skadeRapportRepository){
        this.bilFactory = bilFactory;
        this.bilRepository = bilRepository;
        this.lejeaftaleRepository = lejeaftaleRepository;
        this.skadeRapportRepository = skadeRapportRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Bil test data: tre biler Citroen, Peugeot og Opel
        List<Bil> biler = new ArrayList<>();

        biler.addAll(Arrays.asList(
                bilFactory.createCitroenC1(),
                bilFactory.createPeugeot108(),
                bilFactory.createOpelCorsaCosmo()
        ));


        // set random values for each bil
        for (int i = 0; i<biler.size(); i++) {
            biler.get(i).setVognNummer("VognNummer" + i);
            biler.get(i).setStelNummer("StelNummer" + i);
            biler.get(i).setUdstyrsNiveau("UdstyrsNiveau" + i);
            biler.get(i).setKilometerKoert(1000 * i);
            biler.get(i).setSomTilgaengelig();
        }

        // Iterator bruges på biler for at undgå ConcurrentModificationException, dvs. listen håndteres samtidig med at elementer fjernes
        // iterator med for loop fungerer her ved at iterator.remove() kaldes, når et element fjernes
        for (Iterator<Bil> iterator = biler.iterator(); iterator.hasNext();) {
            Bil bil = iterator.next();
            if (bilRepository.findByVognNummer(bil.getVognNummer()) != null) {
                iterator.remove();
            }
        }
        // iterator.remove removes from list, and list is 'automatically' updated

        // Save biler (if any remaining) to database
        for (int i = 0; i<biler.size(); i++) {
            biler.set(i, bilRepository.save(biler.get(i)));
        }

        // LejeAftale og SkadeRapport test data kan tilføjes når nødvendigt (kræver repo og klar instantiering)

//        // Create Lejeaftale objects
//        LejeAftale lejeaftale1 = new Lejeaftale();
//        // set properties for lejeaftale1
//        lejeaftaleRepository.save(lejeaftale1);
//
//        // Create SkadeRapport objects
//        SkadeRapport skadeRapport1 = new SkadeRapport();
//        // set properties for skadeRapport1
//        skadeRapportRepository.save(skadeRapport1);
    }
}