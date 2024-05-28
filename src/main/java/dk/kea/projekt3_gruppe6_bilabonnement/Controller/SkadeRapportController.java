package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerDto;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BrugerService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.LejeAftaleService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.SkadeRapportService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.SkadeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SkadeRapport")
public class SkadeRapportController {

    // web sider:
    private static final String SKADERAPPORT_PAGE = "SkadeRapport"; // http://localhost:8080/SkadeRapport/
    private static final String OPRET_RAPPORT_PAGE = "OpretSkadeRapport";
    private static final String REDIRECT_OPRET_MED_LEJEAFTALEID = "redirect:/SkadeRapport/opret?lejeAftaleID=";
    private static final String REDIRECT_OPRET = "redirect:/SkadeRapport/opret";
    private static final String REDIRECT_SE = "redirect:/SkadeRapport/se/lejeAftaleID=";
    private static final String REDIRECT_SE_UDEN_VALUES = "redirect:/SkadeRapport/se/";
    private static final String SE_RAPPORT_PAGE = "SeSkadeRapport";


    // Autowired services
    private final SkadeRapportService skadeRapportService;
    private final SkadeService skadeService;
    private final LejeAftaleService lejeAftaleService;
    private BrugerService brugerService;

    @Autowired
    public SkadeRapportController(SkadeRapportService skadeRapportService, SkadeService skadeService, LejeAftaleService lejeAftaleService, BrugerService brugerService) {
        this.skadeRapportService = skadeRapportService;
        this.skadeService = skadeService;
        this.lejeAftaleService = lejeAftaleService;
        this.brugerService = brugerService;
    }


    // ------------------- SkadeRapport Mappings -------------------

    @GetMapping("/")
    public String visStartSide(HttpSession session, Model model) {

        //Data for View: skadeRapporter & lejeAftaler med manglende SkadeRapporter (lejeAftaler = udløbet + mangler SkadeRapport)

        List<SkadeRapport> skadeRappoter = skadeRapportService.findAlle();
        List<LejeAftale> lejeAftalerUdenRapport = lejeAftaleService.getLejeAftaleUdenRapport();
        List<LejeAftale> lefeAftaleMedRapport = lejeAftaleService.getLejeAftaleMedRapport();


        model.addAttribute("SkadeRapporter", skadeRappoter);
        model.addAttribute("LejeAftalerUdenRapport", lejeAftalerUdenRapport);
        model.addAttribute("LejeAftaleMedRapport", lefeAftaleMedRapport);

        return SKADERAPPORT_PAGE;
    }


    @GetMapping("/opret")
    public String opretRapport(Model model, HttpSession session, @RequestParam int lejeAftaleID){

        // ------------------- form -> session -------------------
        session.setAttribute("lejeAftaleID", lejeAftaleID);



        // ------------------- data opdateres -------------------
        setSkadeAttributes(model, session); // model and session opdateres

        return OPRET_RAPPORT_PAGE;
    }


    @GetMapping("/refresh") // purpose of refresh method: opdater session med valgte data fra form submit
    public String refresh(HttpSession session, Model model, @RequestParam(required = false) List<String> skaderValgt){

        // ------------------- form -> session -------------------
        session.setAttribute("skaderValgt", skaderValgt); // if null, then empty list



        // ------------------- data opdateres -------------------
        setSkadeAttributes(model, session);

        return OPRET_RAPPORT_PAGE;
    }


    @PostMapping("/opretPost")
    public String opretSkadeRapport(HttpSession session, @RequestParam (required = false) Integer kilometerKoertOver, @RequestParam List<String> skaderValgt){


        // ------------------- generer SkadeRapport -------------------
            //1. valgteSkader -- data: form input fra html
            //2. brugerID -- data: session
            //3. lejeAftaleID -- data: session
            //4. kilometerKørtOver -- data: form input fra html
            //5. reparationsomkostninger -- data: udregnes i service

        // 1. valgteSkader
        List<Skade> valgteSkader = skadeService.genererSkadeListe(skaderValgt);

        // 2. brugerID
        Integer brugerID = getLoggedInBrugerID(session); // hvis null, så deaktiveres 'Generer SkadeRapport' knap og skriver "Log ind for at oprette SkadeRapport"


        // 3. lejeAftaleID
        Integer lejeAftaleID = (Integer) session.getAttribute("lejeAftaleID");

        // 4. & 5 kilometerKørtOver + reparationsomkostninger
        int reparationsomkostionger = skadeService.udregnReparationsomkostninger(kilometerKoertOver, valgteSkader);

        brugerID = 4; // TODO: remove this line when login is implemented

        // ------------------- gem Skader og SkadeRapport -------------------
        SkadeRapport skadeRapport = new SkadeRapport(brugerID, kilometerKoertOver, reparationsomkostionger, valgteSkader);
        SkadeRapport gemtSkadeRapport = skadeRapportService.gem(skadeRapport); // gemmer også List<Skader>, da de er composed i SkadeRapport

        if(gemtSkadeRapport == null){
            System.out.println("DEBUG: SkadeRapportController.opretSkadeRapport() -> gemtSkadeRapport == null");
            return REDIRECT_OPRET_MED_LEJEAFTALEID + lejeAftaleID;
        }

        lejeAftaleService.opdaterSkadeRapportID(lejeAftaleID, gemtSkadeRapport.getID());


        // ------------------- skabt SkadeRapport -> clean up og redirect -------------------

        return REDIRECT_SE + lejeAftaleID+ "/skadeRapportID=" + gemtSkadeRapport.getID();
    }



    @GetMapping("/se/lejeAftaleID={lejeAftaleID}/skadeRapportID={skadeRapportID}")
    public String seRapport(HttpSession session, Model model, @PathVariable int lejeAftaleID, @PathVariable int skadeRapportID) {
        SkadeRapport skadeRapport = skadeRapportService.findVedID(skadeRapportID);

        if (skadeRapport == null) {
            return REDIRECT_OPRET_MED_LEJEAFTALEID + lejeAftaleID;
        }

        // ------------------- data for view -------------------


        model.addAttribute("skadeRapport", skadeRapport);


        // ------------------- end of opretRapport session -------------------

        session.removeAttribute("refresh"); // identicator for opretRapport session
            // session data
        session.removeAttribute("lejeAftaleID");
        session.removeAttribute("skaderValgt");
        session.removeAttribute("skaderIkkeValgt");

        return SE_RAPPORT_PAGE;
    }



    // ------------------- private methods -------------------

    private void setSkadeAttributes(Model model, HttpSession session) {
        Map<String, Integer> skadeCheckliste = skadeService.getSkadeCheckliste();
        List<String> skaderValgt = getSkaderValgt(session);
        List<String> skaderIkkeValgt = getSkaderIkkeValgt(skaderValgt); // skaderIkkeValgt = skadeCheckliste - skaderValgt


        model.addAttribute("skadeCheckliste", skadeCheckliste);
        model.addAttribute("skaderValgt", skaderValgt);
        model.addAttribute("skaderIkkeValgt", skaderIkkeValgt);

        session.setAttribute("skaderValgt", skaderValgt);
        session.setAttribute("skaderIkkeValgt", skaderIkkeValgt);
    }

    private List<String> getSkaderValgt (HttpSession session){
        List<String> convertedList = new ArrayList<>();
        try {
            List objectList = (List) session.getAttribute("skaderValgt");
            for (Object obj : objectList) {
                convertedList.add(String.valueOf(obj));
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return convertedList;
    }

    private List<String> getSkaderIkkeValgt(List<String> skaderValgt) {
        Map<String, Integer> skadeCheckliste = skadeService.getSkadeCheckliste();
        List<String> skaderIkkeValgt = new ArrayList<>(skadeCheckliste.keySet());
        skaderIkkeValgt.removeAll(skaderValgt);
        return skaderIkkeValgt;
    }


    // ------------------- session exception handling -------------------
    private Integer getLoggedInBrugerID(HttpSession session) {
        int loggedInBrugerID = 0;
        try {
            BrugerDto loggedInBruger = (BrugerDto) session.getAttribute("loggedInBruger");
            loggedInBrugerID = loggedInBruger.getId();
        } catch (Exception e) {
            return null;
        }

        return loggedInBrugerID;
    }

    private void testSettings(HttpSession session) {
        // ------------------- Set Session LoggedInUser for testing purposes -------------------
        session.setAttribute("loggedInBrugerID", 1); // for testing purposes
        session.setAttribute("loggedIn", true);
        session.setAttribute("loggedInBrugerNavn", "testBruger");
    }
}

