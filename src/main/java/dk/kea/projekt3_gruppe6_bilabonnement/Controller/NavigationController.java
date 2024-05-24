package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerValgDTO;
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
public class NavigationController {

    // pages
    private static final String START_PAGE = "LejeAftale";
    private static final String VAELGBIL_PAGE = "Dataregistrering/VaelgBil"; // url: /LejeAftale/VaelgBil
    private static final String ABONNEMENT_PAGE = "Dataregistrering/Abonnement";
    private static final String PRISOVERSLAG_PAGE = "Dataregistrering/PrisOverslag";
    private static final String KUNDEINFO_PAGE = "Dataregistrering/Kunde";
    private static final String AFHENTNINGSSTED_PAGE = "Dataregistrering/Afhentning";
    private static final String REDIRECT_TO_START = "redirect:/LejeAftale/";
    private final LejeAftaleService lejeAftaleService;
    private final BilService bilService;

    public NavigationController(LejeAftaleService lejeAftaleService, BilService bilService) {
        this.lejeAftaleService = lejeAftaleService;
        this.bilService = bilService;
    }

    @GetMapping("/")
    public String index() {

        return START_PAGE;
    }


    @GetMapping("/VaelgBil")
    public String getVaelgbilPage(BrugerValgDTO brugerValgDTO,HttpSession session, Model model) {
        // reset session
        resetLejeAftaleSession(session);

        // data til siden:

        model.addAttribute("biler", bilService.getBilTyper()); // TODO: ændre til getAlleTilgaengeligeBiler senere
        model.addAttribute("citroen", bilService.getBilTyper().get(0));
        model.addAttribute("peugeot", bilService.getBilTyper().get(1));
        model.addAttribute("opel", bilService.getBilTyper().get(2));






        // ------------------- Load, Save and return next -------------------


        session.setAttribute("BrugerValgDTO", brugerValgDTO);
        model.addAttribute("nyBrugerValgDTO", brugerValgDTO);
        System.out.println("VaelgBil BrugerValgDTO: "+ brugerValgDTO.toString());

        return VAELGBIL_PAGE;
    }


    @GetMapping("/Abonnement")
    public String LejeAbonnement(BrugerValgDTO nyBrugerValgDTO, HttpSession session, Model model) {
        // ------------------- data for view -------------------
        List<PackageDeal> packageDeals = lejeAftaleService.getPackageDeals();

        model.addAttribute("packageDeals", packageDeals);








        // ------------------- Load, Save and return next -------------------
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        BrugerValgDTO loadedDTO = load(oldBrugerValgDTO, nyBrugerValgDTO);

        session.setAttribute("BrugerValgDTO", loadedDTO);
        model.addAttribute("nyBrugerValgDTO", loadedDTO);

        System.out.println("Abonnement Session data:");
        System.out.println(" - oldBrugerValgDTO: "+ oldBrugerValgDTO.toString());
        System.out.println(" - nyBrugerValgDTO: "+ nyBrugerValgDTO.toString());
        System.out.println(" - loadedDTO: "+ loadedDTO.toString());

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
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        BrugerValgDTO loadedDTO = load(oldBrugerValgDTO, nyBrugerValgDTO);

        session.setAttribute("BrugerValgDTO", loadedDTO);
        model.addAttribute("nyBrugerValgDTO", loadedDTO);
        System.out.println("Prisoverslag BrugerValgDTO: "+ loadedDTO.toString());

        return PRISOVERSLAG_PAGE;
    }

    @GetMapping("/KundeInfo")
    public String getKundeinfoPage(HttpSession session, Model model, BrugerValgDTO nyBrugerValgDTO) {







        // ------------------- Load, Save and return -------------------
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        BrugerValgDTO loadedDTO = load(oldBrugerValgDTO, nyBrugerValgDTO);

        session.setAttribute("BrugerValgDTO", loadedDTO);
        model.addAttribute("nyBrugerValgDTO", loadedDTO);
        System.out.println("KundeInfo BrugerValgDTO: "+ loadedDTO.toString());

        return KUNDEINFO_PAGE;
    }

    @GetMapping("/AfhentningsSted")
    public String getAfhentningsstedPage(HttpSession session, Model model, BrugerValgDTO nyBrugerValgDTO) {






        // ------------------- Load, Save and return -------------------
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        BrugerValgDTO loadedDTO = load(oldBrugerValgDTO, nyBrugerValgDTO);

        session.setAttribute("BrugerValgDTO", loadedDTO);
        model.addAttribute("nyBrugerValgDTO", loadedDTO);

        return AFHENTNINGSSTED_PAGE;
    }



    @PostMapping("/Opret")
    public String opretLejeAftale(HttpSession session) {

        BrugerValgDTO fuldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");

        // totalPris beregnes og gemmes i BrugerValgDTO
        int totalPris = lejeAftaleService.beregnTotalPris(fuldBrugerValgDTO);
        fuldBrugerValgDTO.setTotalPris(totalPris);

        // ------------------- Save data -------------------
        lejeAftaleService.opret(fuldBrugerValgDTO);

        return REDIRECT_TO_START;
    }


    // ------------------- Helper methods -------------------


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
        if (nyBugerValgDTO.getSelectedPackageDeals() != null){
            oldBrugerValgDTO.setSelectedPackageDeals(nyBugerValgDTO.getSelectedPackageDeals());
        }
        if (nyBugerValgDTO.getAbonnementslaengde() != 0){
            oldBrugerValgDTO.setAbonnementslaengde(nyBugerValgDTO.getAbonnementslaengde());
        }
        if (nyBugerValgDTO.getKmPrMdr() != 0){
            oldBrugerValgDTO.setKmPrMdr(nyBugerValgDTO.getKmPrMdr());
        }



        return oldBrugerValgDTO;

    }


    private void resetLejeAftaleSession(HttpSession session) {
        session.removeAttribute("BrugerValgDTO");
        session.removeAttribute("nyBrugerValgDTO");
    }
}