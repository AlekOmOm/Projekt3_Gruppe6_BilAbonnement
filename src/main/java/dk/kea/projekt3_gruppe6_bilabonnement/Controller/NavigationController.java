package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.service.LejeAftaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/LejeAftale")
public class NavigationController {

    // pages
    private static final String START_PAGE = "DATAREGISTRERING";
    private static final String VAELGBIL_PAGE = "LejeAfale";
    private static final String ABONNEMENT_PAGE = "Abonnement";
    private static final String PRISOVERSLAG_PAGE = "PrisOverslag";
    private static final String KUNDEINFO_PAGE = "Kunde";
    private static final String AFHENTNINGSSTED_PAGE = "LejeAfhentningsSted";
    private static final String REDIRECT_TO_START = "redirect:/LejeAftale/";
    private final LejeAftaleService lejeAftaleService;

    public NavigationController(LejeAftaleService lejeAftaleService) {
        this.lejeAftaleService = lejeAftaleService;
    }

    @GetMapping("/")
    public String index() {

        return START_PAGE;
    }


    @GetMapping("/VaelgBil")
    public String getVaelgbilPage(@ModelAttribute("bil") String bil, HttpSession session) {
        // data til siden:
        session.setAttribute("valgtBil", bil);

        return VAELGBIL_PAGE;
    }

    @GetMapping("/Abonnement")
    public String LejeAbonnement(@ModelAttribute("Abonnement") String abonnemment, HttpSession session, Model model) {
        //
        session.setAttribute("valgtAbonomment", abonnemment);

        return ABONNEMENT_PAGE;
    }


    @GetMapping("/PrisOverslag")
    public String LejeKundeInfo(@ModelAttribute("PrisOverslag") String prisOverslag, HttpSession session, Model model) {
        // henter data fra session
        session.setAttribute("valgtPrisOverslag", prisOverslag);

        return PRISOVERSLAG_PAGE;
    }


    @GetMapping("/KundeInfo")
    public String getKundeinfoPage(@ModelAttribute("KundeInfo") String kundeInfo, HttpSession session, Model model) {
        session.setAttribute("valgtKundeInfo", kundeInfo);

        return KUNDEINFO_PAGE;
    }


    @GetMapping("/AfhentningsSted")
    public String getAfhentningsstedPage(@ModelAttribute("AfhentningsSted") String afhentningsSted, HttpSession session, Model model) {
        session.setAttribute("valgtAfhentningsSted", afhentningsSted);

        return AFHENTNINGSSTED_PAGE;
    }



    @PostMapping("/Opret")
    public String opret( HttpSession session) {
        // henter ALT data fra session
        String valgtBil = (String) session.getAttribute("valgtBil");
        String valgtAbonnement = (String) session.getAttribute("valgtAbonnement");
        String valgtPrisOverslag = (String) session.getAttribute("valgtPrisOverslag");
        String valgtKundeInfo = (String) session.getAttribute("valgtKundeInfo");
        String valgtAfhentningsSted = (String) session.getAttribute("valgtAfhentningsSted");

        // opretter en lejeaftale med den indsamlede data
        LejeAftale lejeAftale = new LejeAftale(); // TODO: constructor parameters missing
        lejeAftale.setBil(valgtBil); // er object
        lejeAftale.setAbonnement(valgtAbonnement); // blir gemt i leje aftale
        lejeAftale.setPrisOverslag(valgtPrisOverslag); // blir gemt i leje aftale
        lejeAftale.setKundeInfo(valgtKundeInfo); // er object
        lejeAftale.setAfhentningsSted(valgtAfhentningsSted); // blir gemt i leje aftale

        // gem obejekter i databasen først og tag deres ID og smid det i en lejeaftale


        // gemmer lejeaftalen i databasen via service
        lejeAftaleService.saveLejeAftale(lejeAftale);

        return REDIRECT_TO_START;
    }


}