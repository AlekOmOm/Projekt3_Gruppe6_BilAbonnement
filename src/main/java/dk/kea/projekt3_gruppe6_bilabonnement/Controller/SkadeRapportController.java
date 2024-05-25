package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.LejeAftaleService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.SkadeRapportService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.SkadeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SkadeRapport")
public class SkadeRapportController {

    // web sider:
    private static final String SKADERAPPORT_PAGE = "SkadeRapport";
    private static final String OPRET_RAPPORT_PAGE = "GenererSkadeRapport";
    private static final String REDIRECT_OPRET = "redirect:/SkadeRapport/opret";
    private static final String SE_RAPPORT_PAGE = "SeSkadeRapport";
    private static final String REDIRECT_SE = "redirect:/Se/";

    // Autowired services
    private final SkadeRapportService skadeRapportService;
    private final SkadeService skadeService;
    private final LejeAftaleService lejeAftaleService;

    @Autowired
    public SkadeRapportController(SkadeRapportService skadeRapportService, SkadeService skadeService, LejeAftaleService lejeAftaleService) {
        this.skadeRapportService = skadeRapportService;
        this.skadeService = skadeService;
        this.lejeAftaleService = lejeAftaleService;
    }


    // ------------------- SkadeRapport Mappings -------------------

    @GetMapping("/")
    public String visStartSide(Model model) {
        //Data for View: skadeRapporter & lejeAftaler med manglende SkadeRapporter (lejeAftaler = udløbet + mangler SkadeRapport)

        List<SkadeRapport> skadeRappoter = skadeRapportService.findAlle();
        List<LejeAftale> lejeAftalerUdenRapport = lejeAftaleService.getLejeAftaleUdenRapport();
        List<LejeAftale> lefeAftaleMedRapport = lejeAftaleService.getLejeAftaleMedRapport();
        //List<LejeAftaleKortInfoDto> lejeAftalerUdenRapport = lejeAftaleService.getLejeAftalerUdenRapport();

        // TODO: lejeAftaler med Manglende SkadeRapporter (LejeAftaleService.getLejeAftalerUdenRapporter())


        // model.addAttribute("ManglendeSkadeRapporter", manglendeSkadeRapporter); data der skal vises: BilID, SlutDato (måske opret LejeAftaleDTO med disse to felter)

        model.addAttribute("SkadeRapporter", skadeRappoter);
        model.addAttribute("LejeAftalerUdenRapport", lejeAftalerUdenRapport);
        model.addAttribute("LejeAftaleMedRapport", lefeAftaleMedRapport);

        return SKADERAPPORT_PAGE;
    }


    @GetMapping("/opret")
    public String opretRapport(Model model, HttpSession session, @RequestParam int lejeAftaleID){ // input *hidden* for LejeAftaleID
        Map<String, Integer> skadeCheckliste = skadeService.getSkadeCheckliste();
        List<String> skaderValgt = (List<String>) session.getAttribute("skaderValgt");

        Integer brugerID = (Integer) session.getAttribute("brugerID");


        //hvis brugerID er null er er man ikke logged ind. Skal nok opdateres efter merge med login for at kunne få loggedInUser ordenligt
        if (brugerID == null){
            brugerID = 1;
            session.setAttribute("brugerID", brugerID);
        }
           // return "redirect:/login";

        if (skaderValgt == null) {
            skaderValgt = new ArrayList<>();
            session.setAttribute("skaderValgt", skaderValgt);
        }
        List<String> skaderIkkeValgt = new ArrayList<>(skadeCheckliste.keySet());
        skaderIkkeValgt.removeAll(skaderValgt);

        model.addAttribute("skadeService", skadeService);

        model.addAttribute("skadeCheckliste", skadeCheckliste);
        model.addAttribute("skaderValgt", skaderValgt);
        model.addAttribute("skaderIkkeValgt", skaderIkkeValgt);
        model.addAttribute("lejeAftaleID", lejeAftaleID);

        session.setAttribute("lejeAftaleID", lejeAftaleID);

        return OPRET_RAPPORT_PAGE;
    }

    // purpose of refresh method: opdater session med valgte data fra form submit
    @GetMapping("/refresh")
    public String refresh(HttpSession session, Model model, @RequestParam int lejeAftaleID, @RequestParam(required = false) List<String> skaderValgt){
        Map<String, Integer> skadeCheckliste = skadeService.getSkadeCheckliste();
        //List<String> skadeTyper = new ArrayList<>(skadeCheckliste.keySet());
        model.addAttribute("skadeCheckliste", skadeCheckliste);

        // hvis == null, så er der ingen valg -> restart side
        if(skaderValgt == null){
            skaderValgt = new ArrayList<>();
        }

        // hvis != null, så er der ændringer -> opdater session
        List<String> skaderIkkeValgt = new ArrayList<>(skadeCheckliste.keySet());
        skaderIkkeValgt.removeAll(skaderValgt);

        // session opdateres
        session.setAttribute("skaderValgt", skaderValgt);
        session.setAttribute("skaderIkkeValgt", skaderIkkeValgt);
        model.addAttribute("skaderValgt", skaderValgt);
        model.addAttribute("skaderIkkeValgt", skaderIkkeValgt);
        model.addAttribute("lejeAftaleID", lejeAftaleID);

        model.addAttribute("skadeService", skadeService);

        session.setAttribute("lejeAftaleID", lejeAftaleID);


        return REDIRECT_OPRET + "?lejeAftaleID=" + lejeAftaleID;
    }

    @PostMapping("/opret")
    public String opretSkadeRapport(HttpSession session, @RequestParam int kilometerKoertOver, @RequestParam List<String> skaderValgt){
        // constructor kræver: brugerID, lejeAftaleID, kilometerKørtOver, reparationsomkostninger
            //valgteSkader form input fra html
            //brugerID = session get attribute bruger
            //lejeAftaleID = Udløbet lejeaftaler
            //kilometerKørtOver = input felt
            //reparationsomkostninger = smalet pris for skader & kilometerKoertOver SkadeRapporter

        //Henter valgte skader fra service
        List<Skade> valgteSkader = skadeService.genererSkadeListe(skaderValgt);

        //Henter burgerID fra session
        Integer brugerID = (Integer) session.getAttribute("brugerID");

        //Henter lejeAftaleID fra session
        Integer lejeAftaleID = (Integer) session.getAttribute("lejeAftaleID");
        int reparationsomkostionger = skadeService.udregnReparationsomkostninger(kilometerKoertOver, valgteSkader);

        //opret skadeRapport
        SkadeRapport skadeRapport = new SkadeRapport(brugerID, lejeAftaleID, kilometerKoertOver, reparationsomkostionger, valgteSkader);
        SkadeRapport gemtSkadeRapport = skadeRapportService.gem(skadeRapport);

        if(gemtSkadeRapport == null){
            return REDIRECT_OPRET + "?lejeAftaleID=" + lejeAftaleID;
        }

        lejeAftaleService.opdaterSkadeRapportID(lejeAftaleID, gemtSkadeRapport.getID());

        //Sletter session variabler
        session.removeAttribute("lejeAftaleID");
        session.removeAttribute("skaderValgt");
        session.removeAttribute("skaderIkkeValgt");

        return REDIRECT_SE + gemtSkadeRapport.getID();

    }
    @GetMapping("/se/{lejeAftaleID}")
    public String seRapport(Model model, @PathVariable int lejeAftaleID) {
        SkadeRapport skadeRapport = skadeRapportService.findMedLejeAftaleID(lejeAftaleID);
        model.addAttribute("SkadeRapport", skadeRapport);
        return SE_RAPPORT_PAGE;
    }
}

