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

import java.time.LocalDate;


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


    // ------------------- Main Operations -------------------


    public LejeAftale opret(BrugerValgDTO brugerValgDTO) { // opret() bliver kaldt ved slutningen af processen for LejeAftale skabelse (View <-> Controller -> nu til -> Service)
        // LejeAftale initialiseres med alle nødvendige informationer og gemmes i databasen

        // ------------------- Initialization -------------------
        LejeAftale lejeAftale = initialize(brugerValgDTO);

        // ------------------- Save and Return -------------------
        if (lejeAftale != null) {
            return save(lejeAftale); // return gemte LejeAftale instance, hvis den blev initialiseret korrekt og gemt korrekt
        }
        return null; // return null hvis lejeAftale ikke blev initialiseret korrekt
    }




    // ------------------- Operations (CRUD) -------------------


    public LejeAftale save(LejeAftale nyLejeAftale) {
        if (exists(nyLejeAftale)) {
            return update(nyLejeAftale);
        }

        // Save Bil
        Bil savedBil = bilService.saveBil(nyLejeAftale.getBil()); // TODO: tjek book() metoden, måske bare brug save() i stedet
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
        bilService.book(savedBil); // TODO: book() ?

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

    private LejeAftale initialize(BrugerValgDTO brugerValgDTO) {
        LejeAftale lejeAftale = new LejeAftale();

        // ------------------- Instances -------------------

            // 1. Bruger 2. Bil 3. KundeInfo 4. SkadeRapport
        Bruger bruger = brugerService.hent(brugerValgDTO.getBrugerID()); // Bruger instance findes allerede i DB, da bruger er logget ind // TODO: tilføj BrugerID til BrugerValgDTO
        Bil bil = bilService.getInstance(brugerValgDTO.getBilModel());
        KundeInfo kundeInfo = kundeInfoService.getInstance(brugerValgDTO);
        SkadeRapport nullSkadeRapport = null; // SkadeRapport skabes efter LejeAftale perioden er ovre

            // Check for null
        if (isNull(bruger, bil, kundeInfo)) {
            return null; // return null hvis nogen af Instances er null
        }

        // ------------------- Instance variables -------------------
        LocalDate startDato = LocalDate.now();
        LocalDate slutDato = startDato.plusMonths(brugerValgDTO.getAbonnementslaengde());
        int totalPris = 0; // TODO: implement totalPris udregning

        // ------------------- initialize LejeAftale med Instances og Instance variable -------------------
        lejeAftale.setAllInstances(bruger, kundeInfo, bil, nullSkadeRapport);
        lejeAftale.setAllInstanceVariables(brugerValgDTO.getFarve(), brugerValgDTO.isAfleveringsforsikring(), brugerValgDTO.isSelvrisiko(), brugerValgDTO.isDaekpakke(), brugerValgDTO.isVejhjaelp(), brugerValgDTO.isUdleveringVedFDM(), brugerValgDTO.getAbonnementslaengde(), brugerValgDTO.getKmPrMdr(), brugerValgDTO.getAfhentningssted(), startDato, slutDato, totalPris);

        // ------------------- return fully initialized LejeAftale -------------------
        return lejeAftale;
    }


    // ------------------- Check -------------------

    private boolean isNull(Bruger bruger, Bil bil, KundeInfo kundeInfo) {

        if (bruger == null || bil == null || kundeInfo == null) {
            return true;
        }

        return false;
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

    public int beregnTotalPris(BrugerValgDTO brugerValgDTO) {
        // detAlekLaver // TODO: fjern tag
        List<String> selectedPackageDeals = brugerValgDTO.getSelectedPackageDeals();
        List<PackageDeal> packageDealTypes = getPackageDeals();

        int totalPris = 0;

        for (PackageDeal packageDealType : packageDealTypes) {
            if (selectedPackageDeals.contains(packageDealType.getPackageName())) {
                totalPris += packageDealType.getPackagePrice();
            }
        }
        return totalPris;
    }



    public List<LejeAftale> getLejeAftaleUdenRapport(){
        return lejeAftaleRepository.getLejeAftalerUdenRapport();
    }

    public List<LejeAftale> getLejeAftaleMedRapport(){
        return lejeAftaleRepository.getLejeAftaleMedRapport();
    }

    public LejeAftale findMedID(int id){
        return lejeAftaleRepository.findMedID(id);
    }

    public LejeAftale opdaterSkadeRapportID(int lejeAftaleID, int skadeRapportID) {
        return lejeAftaleRepository.updaterSkadeRapportID(lejeAftaleID, skadeRapportID);
    }
}
