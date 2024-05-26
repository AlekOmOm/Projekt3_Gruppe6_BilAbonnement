package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerValgDTO;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.DTO.PackageDeal;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LejeAftaleService {

    // ------------------- Dependency Injections -------------------

    private final LejeAftaleRepository lejeAftaleRepository;
    private final BilService bilService;
    private final KundeInfoService kundeInfoService;
    private final SkadeRapportService skadeRapportService;
    private final BrugerService brugerService;

    @Autowired
    public LejeAftaleService(LejeAftaleRepository lejeAftaleRepository, BilService bilService, KundeInfoService kundeInfoService, SkadeRapportService skadeRapportService, BrugerService brugerService) {
        this.lejeAftaleRepository = lejeAftaleRepository;
        this.bilService = bilService;
        this.kundeInfoService = kundeInfoService;
        this.skadeRapportService = skadeRapportService;
        this.brugerService = brugerService;
    }

    // ------------------- Operations (CRUD) -------------------

    public void opret(BrugerValgDTO brugerValgDTO) {
        LejeAftale lejeAftale = new LejeAftale();

        lejeAftale = integrate(brugerValgDTO, lejeAftale);

        System.out.println("DEBUG - LejeAftaleService - opret - lejeAftale: " + lejeAftale.toString());
        System.out.println(" - bruger: " + lejeAftale.getBruger().toString());

        save(lejeAftale);
    }


    // ------------------- Operations (CRUD) -------------------


    public LejeAftale save(LejeAftale nyLejeAftale) {
        if (exists(nyLejeAftale)) {
            return update(nyLejeAftale);
        }

        // Save Bil
        Bil savedBil = bilService.saveBil(nyLejeAftale.getBil());
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

        // book bil
        bilService.book(savedBil);

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
        Bruger loggedInBruger = brugerService.hentBruger(brugerValgDTO.getBrugerID());
        Bil bil = bilService.create(brugerValgDTO.getBilModel());
        KundeInfo kundeInfo = new KundeInfo(brugerValgDTO.getCprNr(), brugerValgDTO.getFornavn(), brugerValgDTO.getEfternavn(), brugerValgDTO.getAdresse(), brugerValgDTO.getPostNummer(), brugerValgDTO.getEmail(), brugerValgDTO.getMobilNummer());
        SkadeRapport skadeRapport = null;

        lejeAftale.setBruger(loggedInBruger);
        lejeAftale.setBil(bil);
        lejeAftale.setKundeInfo(kundeInfo);
        lejeAftale.setSkadeRapport(skadeRapport);



        return lejeAftale;
    }

    public List<PackageDeal> getPackageDeals() {
        return new ArrayList<>(Arrays.asList(
                new PackageDeal("Daekpakke", 300, "Daekpakke"),
                new PackageDeal("Aflveringsforsikring", 119, "Tilvalg af afleveringsforsikring"),
                new PackageDeal("Lav selvrisiko", 89, ""),
                new PackageDeal("Vejhjaelp", 49, "I samarbejde med Viking kan du få vejhjaelp til kun 49 kr. pr. maaned. Som Bilabonnement-kunde er du daekket under de vilkaar du finder under spoergsmaal og svar."),
                new PackageDeal("Udlevering ved FDM", 599, "Udlevering til FDM er et engangsgebyr på 599 kr.")
        ));
    }
    public List<String> getAfhentningssteder() {
        return Arrays.asList("Auto-Huset A/S", "Bilhuset A/S", "Bilcenter A/S");
    }

    public int beregnTotalPris(BrugerValgDTO brugerValgDTO) {

        List<String> selectedPackageDeals = brugerValgDTO.getAbonnementsSide();

        List<PackageDeal> packageDealTypes = getPackageDeals();

        int totalPris = 0;

        for (String selectedPackageDeal : selectedPackageDeals) {
            for (PackageDeal packageDeal : packageDealTypes) {
                if (packageDeal.getPackageName().equals(selectedPackageDeal)) {
                    totalPris += packageDeal.getPackagePrice();
                }
            }
        }

        return totalPris;
    }
}
