package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRapportRepo;
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

        return skadeRapportTom;

    }



    public SkadeRapport gem(SkadeRapport skadeRapport){
        if (skadeRapport == null) {
            return null;
        }

        SkadeRapport gemtSkadeRapport = skadeRapportRepo.gem(skadeRapport);

        if (gemtSkadeRapport == null) {
            return null;
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
