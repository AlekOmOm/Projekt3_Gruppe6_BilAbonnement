package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkadeRapportService {

    private static final double PRIS_PR_KILOMETER_KOERT_OVER = 0.75;

    @Autowired
    private SkadeRapportRepository skadeRapportRepository;

    @Autowired
    SkadeService skadeService;

    public List<SkadeRapport> findAlle(){
        return skadeRapportRepository.findAlle();
    }

    public SkadeRapport findVedID(int id){
        SkadeRapport skadeRapportTom = skadeRapportRepository.findVedID(id);

        List<Skade> valgteSkader = skadeService.getSkader(skadeRapportTom);


        skadeRapportTom.setSkader(valgteSkader);

        return skadeRapportTom;

    }

    public SkadeRapport findMedLejeAftaleID(int id){
        return skadeRapportRepository.findMedLejeAftaleID(id);
    }

    public SkadeRapport gem(SkadeRapport skadeRapport){
        skadeRapportRepository.gem(skadeRapport);

        return findMedLejeAftaleID(skadeRapport.getLejeAftaleID());
    }

    public void opdater(SkadeRapport skadeRapport){
        skadeRapportRepository.opdater(skadeRapport);
    }

    public void sletVedID(int id){
        skadeRapportRepository.sletVedID(id);
    }

    public int udregnReparationsomkostininger(int kilometerkoertOver, List<Skade> valgteSkader){
        int sum = 0;
        // TODO: sum = valgteSkader sum

        int value = (int) (kilometerkoertOver * PRIS_PR_KILOMETER_KOERT_OVER);

        return value + sum;
    }


}
