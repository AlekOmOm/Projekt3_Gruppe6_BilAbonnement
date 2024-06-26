package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerValgDTO;
import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerDto;
import dk.kea.projekt3_gruppe6_bilabonnement.DTO.PackageDeal;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BilService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.LejeAftaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/LejeAftale")
public class LejeAftaleController {

    // pages
    private static final String START_PAGE = "LejeAftale";
    private static final String VAELGBIL_PAGE = "Dataregistrering/VaelgBil"; // url: /LejeAftale/VaelgBil
    private static final String ABONNEMENT_PAGE = "Dataregistrering/Abonnement";
    private static final String PRISOVERSLAG_PAGE = "Dataregistrering/PrisOverslag";
    private static final String KUNDEINFO_PAGE = "Dataregistrering/KundeInfo";
    private static final String AFHENTNINGSSTED_PAGE = "Dataregistrering/Afhentning";
    private static final String REDIRECT_TO_START = "redirect:/LejeAftale/";
    private final LejeAftaleService lejeAftaleService;
    private final BilService bilService;

    public LejeAftaleController(LejeAftaleService lejeAftaleService, BilService bilService) {
        this.lejeAftaleService = lejeAftaleService;
        this.bilService = bilService;
    }

    @GetMapping("/")
    public String index(HttpSession session) {

        nyLejeAftaleSession(session);

        return START_PAGE;
    }


    @GetMapping("/VaelgBil")
    public String getVaelgbilPage(BrugerValgDTO brugerValgDTO,HttpSession session, Model model) {
        // ------------------- Start på ny LejeAftale session -------------------
        nyLejeAftaleSession(session);

        // ------------------- data for View -------------------

        model.addAttribute("biler", bilService.getBilTyper()); // TODO: ændre til getAlleTilgaengeligeBiler senere
        model.addAttribute("citroen", bilService.getBilTyper().get(0));
        model.addAttribute("peugeot", bilService.getBilTyper().get(1));
        model.addAttribute("opel", bilService.getBilTyper().get(2));



        // ------------------- Load, Save and return next -------------------

        loadOgSave(brugerValgDTO, session, model);

        return VAELGBIL_PAGE;
    }


    @GetMapping("/Abonnement")
    public String getAbonnementPage(BrugerValgDTO nyBrugerValgDTO, HttpSession session, Model model) {
        // ------------------- data for view -------------------
        List<PackageDeal> packageDeals = lejeAftaleService.getPackageDeals();

        setModelAttributes(model, packageDeals);


        // ------------------- Load, Save and return next -------------------

        loadOgSave(nyBrugerValgDTO, session, model);

        return ABONNEMENT_PAGE;
    }



    @GetMapping("/PrisOverslag")
    public String getPrisoverslagPage(BrugerValgDTO nyBrugerValgDTO, HttpSession session, Model model) {


        // ------------------- data for view -------------------

        List<Integer> monthSequence = Arrays.asList(3, 36);
        List<Integer> kmSequnce = Arrays.asList(1000, 2500, 5000, 10000);
        model.addAttribute("monthSequence", monthSequence);
        model.addAttribute("kmSequence", kmSequnce);


        // ------------------- Load, Save and return -------------------
        loadOgSave(nyBrugerValgDTO, session, model);

        return PRISOVERSLAG_PAGE;
    }

    @GetMapping("/KundeInfo")
    public String getKundeinfoPage(HttpSession session, Model model, BrugerValgDTO nyBrugerValgDTO) {


        // ------------------- Load, Save and return -------------------
        loadOgSave(nyBrugerValgDTO, session, model);

        return KUNDEINFO_PAGE;
    }

    @GetMapping("/AfhentningsSted")
    public String getAfhentningsstedPage(HttpSession session, Model model, BrugerValgDTO nyBrugerValgDTO) {

        // ------------------- data for view -------------------


        List<String> afhentningssteder = lejeAftaleService.getAfhentningssteder();
        model.addAttribute("afhentningssteder", afhentningssteder);




        // ------------------- Load, Save and return -------------------
        loadOgSave(nyBrugerValgDTO, session, model);

        return AFHENTNINGSSTED_PAGE;
    }



    @PostMapping("/Opret")
    public String opretLejeAftale(HttpSession session) {
        BrugerValgDTO fuldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");

        // ------------------- Get loggedInBruger -------------------

        BrugerDto loggedInBruger = (BrugerDto) session.getAttribute("loggedInBruger");

        fuldBrugerValgDTO.setBrugerID(loggedInBruger.getId());

        System.out.println("DEBUG: opret()");
        System.out.println(" - afhentningssted i session: "+fuldBrugerValgDTO.getAfhentningssted());
        // ------------------- -> Opret LejeAftale -------------------
        lejeAftaleService.opret(fuldBrugerValgDTO);

        return REDIRECT_TO_START;
    }



    // ------------------- Helper methods -------------------

    private void loadOgSave(BrugerValgDTO nyBrugerValgDTO, HttpSession session, Model model) {

        try {

            BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
            BrugerValgDTO loadedDTO = load(oldBrugerValgDTO, nyBrugerValgDTO);

            session.setAttribute("BrugerValgDTO", loadedDTO);
            model.addAttribute("nyBrugerValgDTO", loadedDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private BrugerValgDTO load(BrugerValgDTO oldBrugerValgDTO, BrugerValgDTO nyBugerValgDTO){

        // ------------------- null check -------------------

        if (oldBrugerValgDTO == null){  // 1. tjekker om oldBrugerValg er null, hvis det ikke er null, så opdateres oldBrugerValgDTO
            oldBrugerValgDTO = new BrugerValgDTO();
        }

        // ------------------- Load data -------------------

        // Logic for Loading
        // steps: check værdier i nyBrugerValgDTO
        //       - hvis værdi er sat, så load i oldBrugerValgDTO

        if (nyBugerValgDTO.getBilModel() != null){
            oldBrugerValgDTO.setBilModel(nyBugerValgDTO.getBilModel());
        }
        if (nyBugerValgDTO.getAbonnementsSideBoolean() != null){
            oldBrugerValgDTO.setAbonnementsSideBoolean(nyBugerValgDTO.getAbonnementsSideBoolean());
            oldBrugerValgDTO.setAbonnementsSide();
        }
        if (nyBugerValgDTO.getKundeInfo() != null){
            oldBrugerValgDTO.setKundeInfo(nyBugerValgDTO.getKundeInfo());
        }
        if (nyBugerValgDTO.getAbonnementslaengde() != 0){
            oldBrugerValgDTO.setAbonnementslaengde(nyBugerValgDTO.getAbonnementslaengde());
        }
        if (nyBugerValgDTO.getKmPrMdr() != 0){
            oldBrugerValgDTO.setKmPrMdr(nyBugerValgDTO.getKmPrMdr());
        }
        if (nyBugerValgDTO.getAfhentningssted() != null){
            oldBrugerValgDTO.setAfhentningssted(nyBugerValgDTO.getAfhentningssted());
        }

        return oldBrugerValgDTO;
    }

    private void nyLejeAftaleSession(HttpSession session) {
        session.removeAttribute("BrugerValgDTO");
        session.removeAttribute("nyBrugerValgDTO");
    }

    private void setModelAttributes(Model model, List<PackageDeal> packageDeals) {
        for (PackageDeal packageDeal : packageDeals) {
            switch (packageDeal.getPackageName()) {
                case "Daekpakke" -> model.addAttribute("daekpakke", packageDeal);
                case "Aflveringsforsikring" -> model.addAttribute("vejhjaelp", packageDeal);
                case "Lav selvrisiko" -> model.addAttribute("selvrisiko", packageDeal); // name: "Lav selvrisiko"
                case "Vejhjaelp" -> model.addAttribute("afleveringsforsikring", packageDeal); // name: "Afleveringsforsikring"
                case "Udlevering ved FDM" -> model.addAttribute("udleveringVedFDM", packageDeal);
            }
        }
    }
}