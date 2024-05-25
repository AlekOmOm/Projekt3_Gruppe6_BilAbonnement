package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRapportRepo;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkadeRapportService {

    private static final double PRIS_PR_KILOMETER_KOERT_OVER = 0.75;

    @Autowired
    private SkadeRapportRepo skadeRapportRepo;

    @Autowired
    SkadeService skadeService;

    @Autowired
    LejeAftaleService lejeAftaleService;

    public List<SkadeRapport> findAlle(){
        return skadeRapportRepo.findAlle();
    }

    public SkadeRapport findVedID(int id){
        SkadeRapport skadeRapportTom = skadeRapportRepo.findVedID(id);

        List<Skade> valgteSkader = skadeService.getSkader(skadeRapportTom);



        skadeRapportTom.setSkader(valgteSkader);

        System.out.println(" - skadeRapport: " + skadeRapportTom);
        System.out.println();
        return skadeRapportTom;

    }


    // Composition forhold mellem SkadeRapport og Skader
    // implementerings effekt:
    // - gem() i SkadeRapportService skal OGSÅ gemme Skader, fordi SkadeRapport ikke skal kunne gemmes uden Skader (note: @Transactional mulig løsning)
    // - findVedID() i SkadeRapportService skal OGSÅ finde Skader, fordi SkadeRapport ikke skal kunne findes uden Skader
    // - Skade table: SKAL have not null constraint på SkadeRapportID

    // @Transactional
    public SkadeRapport gem(SkadeRapport skadeRapport){
        if (skadeRapport == null) {
            return null;
        }

        SkadeRapport gemtSkadeRapport = skadeRapportRepo.gem(skadeRapport);

        if (gemtSkadeRapport == null) {
            return null;
        }

        // SkadeService bruges i stedet for Repository, da dette er bedre praksis ift. GRASP

        List<Skade> valgteSkader = skadeService.gemSkader(skadeRapport.getSkader());

        if (valgteSkader == null) {
            // transactional rollback
            skadeRapportRepo.sletVedID(gemtSkadeRapport.getID()); // Slettes da der ikke er gemt Skader -> SkadeRapport skal ikke gemmes pga. Composition forhold
        }

        return gemtSkadeRapport;
    }

    public void opdater(SkadeRapport skadeRapport){
        skadeRapportRepo.opdater(skadeRapport);
    }

    public void sletVedID(int id){
        skadeRapportRepo.sletVedID(id);
    }

    public int udregnReparationsomkostininger(int kilometerkoertOver, List<Skade> valgteSkader){
        int sum = 0;
        // TODO: sum = valgteSkader sum

        int value = (int) (kilometerkoertOver * PRIS_PR_KILOMETER_KOERT_OVER);

        return value + sum;
    }


}
