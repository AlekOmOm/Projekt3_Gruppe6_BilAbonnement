package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerValgDTO;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LejeAftaleService {

    // ------------------- Dependency Injections -------------------

    private final LejeAftaleRepository lejeAftaleRepository;
    private final BilService bilService;
    private final KundeInfoService kundeInfoService;
    private final SkadeRapportService skadeRapportService;

    @Autowired
    public LejeAftaleService(LejeAftaleRepository lejeAftaleRepository, BilService bilService, KundeInfoService kundeInfoService, SkadeRapportService skadeRapportService) {
        this.lejeAftaleRepository = lejeAftaleRepository;
        this.bilService = bilService;
        this.kundeInfoService = kundeInfoService;
        this.skadeRapportService = skadeRapportService;
    }

    // ------------------- Operations (CRUD) -------------------


    public LejeAftale save(LejeAftale nyLejeAftale) {
        if (exists(nyLejeAftale)) {
            return update(nyLejeAftale);
        }

        // Save Bil
        Bil savedBil = bilService.book(nyLejeAftale.getBil());
        if (savedBil == null) {
            throw new RuntimeException("Failed to save Bil");
        }

        // Save KundeInfo
        KundeInfo savedKundeInfo = kundeInfoService.save(nyLejeAftale.getKundeInfo());
        if (savedKundeInfo == null) {
            throw new RuntimeException("Failed to save KundeInfo");
        }

        // Set saved KundeInfo with id generated from database into LejeAftale


        // Save SkadeRapport if exists
        SkadeRapport savedSkadeRapport = null;
        if (nyLejeAftale.getSkadeRapport() != null) {
            savedSkadeRapport = skadeRapportService.gem(nyLejeAftale.getSkadeRapport());
            if (savedSkadeRapport == null) {
                throw new RuntimeException("Failed to save SkadeRapport");
            }

        }

        nyLejeAftale.setBil(savedBil);
        nyLejeAftale.setKundeInfo(savedKundeInfo);
        nyLejeAftale.setSkadeRapport(savedSkadeRapport);

        // Save LejeAftale after all dependencies were saved and set appropriately
        LejeAftale savedLejeAftale = lejeAftaleRepository.save(nyLejeAftale);
        if (savedLejeAftale == null) {
            throw new RuntimeException("Failed to save LejeAftale");
        }

        return savedLejeAftale;
    }

    public LejeAftale find(LejeAftale lejeAftale) {
        return lejeAftaleRepository.find(lejeAftale);
    }

    public LejeAftale update(LejeAftale lejeAftale) {
        if (!exists(lejeAftale)) {
            return save(lejeAftale);
        }

        // objects to be updated before update(lejeAftale)
                // 1. bruger
                // 2. bil  --------> update needed
                // 3. kundeInfo ----> update needed
                // 4. skadeRapport --> update needed

        try {

            if (bilService.update(lejeAftale.getBil()) == null) {
                return null;
            }

            if (kundeInfoService.update(lejeAftale.getKundeInfo()) == null) {
                return null;
            }

            skadeRapportService.opdater(lejeAftale.getSkadeRapport());

        } catch (Exception e) {
            return null;
        }

        return lejeAftaleRepository.update(lejeAftale);
    }

    public boolean delete(LejeAftale lejeAftale) {
        try {
            return lejeAftaleRepository.delete(lejeAftale);
        } catch (Exception e) {
            return false;
        }
    }


    // ------------------- Service -------------------

    public boolean exists(LejeAftale lejeAftale) {
        return lejeAftaleRepository.exists(lejeAftale);
    }


    // ------------------- Helper methods -------------------

    private LejeAftale integrate(BrugerValgDTO brugerValgDTO, LejeAftale lejeAftale) {
        Bil bil = bilService.createBilInstance(brugerValgDTO.getBilModel());




        return lejeAftale;

    }

}
