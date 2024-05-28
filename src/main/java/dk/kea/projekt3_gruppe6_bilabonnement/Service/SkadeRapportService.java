package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRapportRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkadeRapportService {

    private static final double PRIS_PR_KILOMETER_KOERT_OVER = 0.75;


    private final SkadeRapportRepository skadeRapportRepo;
    private final SkadeService skadeService;

    @Autowired
    public SkadeRapportService(SkadeRapportRepository skadeRapportRepo, SkadeService skadeService) {
        this.skadeRapportRepo = skadeRapportRepo;
        this.skadeService = skadeService;
    }


    public List<SkadeRapport> findAlle(){
        return skadeRapportRepo.findAlle();
    }

    public SkadeRapport findVedID(int id){
        SkadeRapport skadeRapportTom = skadeRapportRepo.findVedID(id);

        List<Skade> valgteSkader = skadeService.getSkader(skadeRapportTom);

        skadeRapportTom.setSkader(valgteSkader);

        return skadeRapportTom;
    }


    // Composition forhold mellem SkadeRapport og Skade
    // implementerings effekt:
        // - gem() i SkadeRapportService skal OGSÅ gemme Skader, fordi SkadeRapport ikke skal kunne gemmes uden Skader (note: @Transactional mulig løsning)
        // - findVedID() i SkadeRapportService skal OGSÅ finde Skader, fordi SkadeRapport ikke skal kunne findes uden Skader
        // - Skade table: SKAL have not null constraint på SkadeRapportID

    // @Transactional denne annotation kunne bruges
    public SkadeRapport gem(SkadeRapport skadeRapport){

        // process Parent and Child tables (Composition forhold):
            // SkadeRapport (parent) og Skade (child) skal gemmes i samme transaction
            // SkadeRapport skal ikke gemmes, hvis Skade ikke kan gemmes
            // SkadeRapport skal derfor gemmes først, hvorefter Skade forsøges gemt, og hvis dette ikke lykkes, slettes SkadeRapport

        if (skadeRapport == null) {
            return null;
        }

        SkadeRapport gemtSkadeRapport = skadeRapportRepo.gem(skadeRapport);
        // gemtSkadeRapport er tom for skader, da de først gemmes efter SkadeRapport er gemt
        // -> opdateres med skader fra skadeRapport

        gemtSkadeRapport.setSkader(skadeRapport.getSkader());

        SkadeRapport opdateretSkadeRapport = setSkadeRapportIDForSkader(gemtSkadeRapport);

        List<Skade> valgteSkader = skadeService.gemSkader(opdateretSkadeRapport.getSkader()); // SkadeService bruges i stedet for Repository, da dette er bedre praksis ift. GRASP

        if (valgteSkader == null) {
            // dette giver transactional rollback, hvis valgteSkader == null
            skadeRapportRepo.sletVedID(opdateretSkadeRapport.getID());
        }

        return opdateretSkadeRapport;
    }

    private SkadeRapport setSkadeRapportIDForSkader(SkadeRapport gemtSkadeRapport) {
        List<Skade> skader = gemtSkadeRapport.getSkader();
        if (skader != null) {
            for (Skade skade : skader) {
                skade.setSkadeRapportID(gemtSkadeRapport.getID());
            }
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
