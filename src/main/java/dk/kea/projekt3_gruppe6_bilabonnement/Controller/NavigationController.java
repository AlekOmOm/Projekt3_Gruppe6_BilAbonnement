package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerValgDTO;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BilService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.LejeAftaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/LejeAftale")
public class NavigationController {

    // pages
    private static final String START_PAGE = "LejeAfale";
    private static final String VAELGBIL_PAGE = "Dataregistrering/VaelgBil"; // url: /LejeAftale/VaelgBil
    private static final String ABONNEMENT_PAGE = "Dataregistrering/Abonnement";
    private static final String PRISOVERSLAG_PAGE = "Dataregistrering/PrisOverslag";
    private static final String KUNDEINFO_PAGE = "Dataregistrering/Kunde";
    private static final String AFHENTNINGSSTED_PAGE = "LejeAfhentningsSted";
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

        // ------------- gemmer data i session -------------
        session.setAttribute("valgtAbonnement", nyBrugerValgDTO);





        // ------------------- Load data -------------------
        model.addAttribute("nyBrugerValgDTO", nyBrugerValgDTO);
        BrugerValgDTO oldBrugerValgDTO = (BrugerValgDTO) session.getAttribute("BrugerValgDTO");
        session.setAttribute("BrugerValgDTO", load(oldBrugerValgDTO, nyBrugerValgDTO));
        return ABONNEMENT_PAGE;
    }


    @GetMapping("/PrisOverslag")
    public String LejeKundeInfo(@ModelAttribute BrugerValgDTO nyBrugerValgDTO, HttpSession session, Model model) {
        // henter data fra session
        session.setAttribute("valgtPrisOverslag", nyBrugerValgDTO);



        // ------------------- Load data -------------------
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
    public String opret( HttpSession session) {

        // gemmer lejeaftalen i databasen via service
        lejeAftaleService.opret((BrugerValgDTO) session.getAttribute("BrugerValgDTO"));

        return REDIRECT_TO_START;
    }

    private BrugerValgDTO load(BrugerValgDTO oldBrugerValgDTO, BrugerValgDTO nyBugerValgDTO){

        // logic for loading


        // steps: check values in nyBrugerValgDTO
        //       1. if value is null, then dont load int oldBrugerValgDTO




        return oldBrugerValgDTO;

    }

}