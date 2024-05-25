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
    private static final String SKADERAPPORT_PAGE = "SkadeRapport"; // http://localhost:8080/SkadeRapport/
    private static final String OPRET_RAPPORT_PAGE = "GenererSkadeRapport";
    private static final String REDIRECT_OPRET = "redirect:/SkadeRapport/opret?lejeAftaleID=";
    private static final String REDIRECT_OPRET_UDEN_VALUES = "redirect:/SkadeRapport/opret";
    private static final String REDIRECT_SE = "redirect:/SkadeRapport/se/lejeAftaleID=";
    private static final String REDIRECT_SE_UDEN_VALUES = "redirect:/SkadeRapport/se/";
    private static final String SE_RAPPORT_PAGE = "SeSkadeRapport";


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


        //hvis brugerID er null er man ikke logged ind. Skal nok opdateres efter merge med login for at kunne få loggedInUser ordenligt
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
    public String refresh(HttpSession session, Model model, @RequestParam(required = false) List<String> skaderValgt){
        Map<String, Integer> skadeCheckliste = skadeService.getSkadeCheckliste();
        int lejeAftaleID = (int) session.getAttribute("lejeAftaleID");

        //List<String> skadeTyper = new ArrayList<>(skadeCheckliste.keySet());

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
        model.addAttribute("skadeCheckliste", skadeCheckliste);
        model.addAttribute("skaderValgt", skaderValgt);
        model.addAttribute("skaderIkkeValgt", skaderIkkeValgt);
        model.addAttribute("lejeAftaleID", lejeAftaleID);
        model.addAttribute("skadeService", skadeService);


        return REDIRECT_OPRET + lejeAftaleID;
    }

    @PostMapping("/opret")
    public String opretSkadeRapport(HttpSession session, @RequestParam (required = false) int kilometerKoertOver, @RequestParam List<String> skaderValgt){

        // ------------------- generer SkadeRapport -------------------
            //1. valgteSkader -- data: form input fra html
            //2. brugerID -- data: session
            //3. lejeAftaleID -- data: session
            //4. kilometerKørtOver -- data: form input fra html
            //5. reparationsomkostninger -- data: udregnes i service

        // 1. valgteSkader
        List<Skade> valgteSkader = skadeService.genererSkadeListe(skaderValgt);

        // 2. brugerID
        Integer brugerID = (Integer) session.getAttribute("brugerID");

        // 3. lejeAftaleID
        Integer lejeAftaleID = (Integer) session.getAttribute("lejeAftaleID");

        // 4. & 5 kilometerKørtOver + reparationsomkostninger
        int reparationsomkostionger = skadeService.udregnReparationsomkostninger(kilometerKoertOver, valgteSkader);


        // ------------------- gem Skader og SkadeRapport -------------------
        SkadeRapport skadeRapport = new SkadeRapport(brugerID, lejeAftaleID, kilometerKoertOver, reparationsomkostionger, valgteSkader);
        SkadeRapport gemtSkadeRapport = skadeRapportService.gem(skadeRapport); // gemmer også List<Skader>, da de er composed i SkadeRapport

        if(gemtSkadeRapport == null){
            return REDIRECT_OPRET + lejeAftaleID;
        }

        lejeAftaleService.opdaterSkadeRapportID(lejeAftaleID, gemtSkadeRapport.getID());


        // ------------------- skabt SkadeRapport -> clean up og redirect -------------------

        return REDIRECT_SE + lejeAftaleID+ "/skadeRapportID=" + gemtSkadeRapport.getID();
    }

    @GetMapping("/se/lejeAftaleID={lejeAftaleID}/skadeRapportID={skadeRapportID}")
    public String seRapport(HttpSession session, Model model, @PathVariable int lejeAftaleID, @PathVariable int skadeRapportID) {
        SkadeRapport skadeRapport = skadeRapportService.findVedID(skadeRapportID);

        if (skadeRapport == null) {

            return REDIRECT_OPRET + lejeAftaleID;
        }



        model.addAttribute("skadeRapport", skadeRapport);


        // ------------------- clean up session -------------------

        session.removeAttribute("lejeAftaleID");
        session.removeAttribute("skaderValgt");
        session.removeAttribute("skaderIkkeValgt");

        return SE_RAPPORT_PAGE;
    }
}

