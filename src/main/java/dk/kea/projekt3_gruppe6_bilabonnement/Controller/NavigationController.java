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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getVaelgbilPage(BrugerValgDTO nyBrugerValgDTO,HttpSession session, Model model) {
        // data til siden:


        model.addAttribute("biler", bilService.getBilTyper()); // TODO: ændre til getAlleTilgaengeligeBiler senere
        model.addAttribute("citroen", bilService.getBilTyper().get(0));
        model.addAttribute("peugeot", bilService.getBilTyper().get(1));
        model.addAttribute("opel", bilService.getBilTyper().get(2));

        session.setAttribute("nyBrugerValgDTO", nyBrugerValgDTO);





        // ------------------- Load data -------------------
        model.addAttribute("nyBrugerValgDTO", nyBrugerValgDTO);
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        session.setAttribute("BrugerValgDTO", load(oldBrugerValgDTO, nyBrugerValgDTO));
        return VAELGBIL_PAGE;
    }

    @GetMapping("/Abonnement")
    public String LejeAbonnement(BrugerValgDTO nyBrugerValgDTO, HttpSession session, Model model) {

        System.out.println("nyBrugerValgDTO: " + nyBrugerValgDTO);


        List<PackageDeal> packageDeals = lejeAftaleService.getPackageDeals();
        for (PackageDeal packageDeal : packageDeals) {
            model.addAttribute(packageDeal);
        }

        model.addAttribute(packageDeals.get(1));
        model.addAttribute(packageDeals.get(2));
        model.addAttribute(packageDeals.get(3));
        model.addAttribute(packageDeals.get(4));
        model.addAttribute("packageDeals", packageDeals);


        // ------------- gemmer data i session -------------
        session.setAttribute("valgtAbonnement", nyBrugerValgDTO);





        // ------------------- Load and Save data -------------------
        model.addAttribute("nyBrugerValgDTO", nyBrugerValgDTO);
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        session.setAttribute("BrugerValgDTO", load(oldBrugerValgDTO, nyBrugerValgDTO));

        return ABONNEMENT_PAGE;
    }


    @GetMapping("/PrisOverslag")
    public String getPrisoverslagPage(BrugerValgDTO nyBrugerValgDTO, HttpSession session, Model model) {

        System.out.println("nyBrugerValgDTO: " + nyBrugerValgDTO);






        // ------------------- Load and Save data -------------------
        model.addAttribute("nyBrugerValgDTO", nyBrugerValgDTO);
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        session.setAttribute("BrugerValgDTO", load(oldBrugerValgDTO, nyBrugerValgDTO));

        return PRISOVERSLAG_PAGE;
    }


    @GetMapping("/KundeInfo")
    public String getKundeinfoPage(HttpSession session, Model model, BrugerValgDTO nyBrugerValgDTO) {
        session.setAttribute("valgtKundeInfo", nyBrugerValgDTO);



        // ------------------- Load data -------------------
        model.addAttribute("nyBrugerValgDTO", nyBrugerValgDTO);
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        session.setAttribute("BrugerValgDTO", load(oldBrugerValgDTO, nyBrugerValgDTO));
        return KUNDEINFO_PAGE;
    }


    @GetMapping("/AfhentningsSted")
    public String getAfhentningsstedPage(HttpSession session, Model model, BrugerValgDTO nyBrugerValgDTO) {
        session.setAttribute("valgtAfhentningsSted", nyBrugerValgDTO);



        // ------------------- Load data -------------------
        model.addAttribute("nyBrugerValgDTO", nyBrugerValgDTO);
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        session.setAttribute("BrugerValgDTO", load(oldBrugerValgDTO, nyBrugerValgDTO));
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

        if (nyBugerValgDTO.getBilModel() == null){
            oldBrugerValgDTO.setBilModel(nyBugerValgDTO.getBilModel());
        }
        if (nyBugerValgDTO.getFarve() == null){
            oldBrugerValgDTO.setFarve(nyBugerValgDTO.getFarve());
        }
        if (!nyBugerValgDTO.isAfleveringsforsikring()){
            oldBrugerValgDTO.setAfleveringsforsikring(nyBugerValgDTO.isAfleveringsforsikring());
        }
        if (!nyBugerValgDTO.isSelvrisiko()){
            oldBrugerValgDTO.setSelvrisiko(true);
        }
        if (!nyBugerValgDTO.isDaekpakke()){
            oldBrugerValgDTO.setDaekpakke(true);
        }
        if (!nyBugerValgDTO.isVejhjaelp()){
            oldBrugerValgDTO.setVejhjaelp(true);
        }
        if (!nyBugerValgDTO.isUdleveringVedFDM()){
            oldBrugerValgDTO.setUdleveringVedFDM(true);
        }
        if (nyBugerValgDTO.getAbonnementslaengde() == 0){
            oldBrugerValgDTO.setAbonnementslaengde(nyBugerValgDTO.getAbonnementslaengde());
        }
        if (nyBugerValgDTO.getKmPrMdr() == 0){
            oldBrugerValgDTO.setKmPrMdr(nyBugerValgDTO.getKmPrMdr());
        }



        return oldBrugerValgDTO;

    }

}