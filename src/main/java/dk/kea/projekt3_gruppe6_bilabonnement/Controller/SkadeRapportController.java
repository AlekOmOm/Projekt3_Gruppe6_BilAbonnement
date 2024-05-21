package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SkadeRapport")
public class SkadeRapportController {

    // web sider:
    private static final String SKADERAPPORT_PAGE = "SkadeRapport";
    private static final String OPRET_RAPPORT_PAGE = "GenererSkadeRapport";
    private static final String REDIRECT_OPRET = "redirect:/SkadeRapport/Opret";
    private static final String SE_RAPPORT_PAGE = "SeSkadeRapport";
    private static final String REDIRECT_SE = "redirect:/SkadeRapport/Se";

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
        //List<LejeAftaleKortInfoDto> lejeAftalerUdenRapport = lejeAftaleService.getLejeAftalerUdenRapport();

        // TODO: lejeAftaler med Manglende SkadeRapporter (LejeAftaleService.getLejeAftalerUdenRapporter())
        // model.addAttribute("ManglendeSkadeRapporter", manglendeSkadeRapporter); data der skal vises: BilID, SlutDato (måske opret LejeAftaleDTO med disse to felter)

        model.addAttribute("SkadeRapporter", skadeRappoter);
        //model.addAttribute("LejeAftalerUdenRapport", lejeAftalerUdenRapport);

        return SKADERAPPORT_PAGE;
    }


    @GetMapping("/opret")
    public String opretRapport(Model model, HttpSession session, @RequestParam int lejeAftaleID){ // input *hidden* for LejeAftaleID
        Map<String, Integer> skadeCheckliste = skadeService.getSkadeCheckliste();

        // hvis == null -> ny session
        if (session.getAttribute("skaderValgt") == null) {
            session.setAttribute("skaderValgt", new ArrayList<>());
            session.setAttribute("skaderIkkeValgt", new ArrayList<>(skadeCheckliste.keySet()));
        }

        model.addAttribute("skadeChecklisteNavne", skadeCheckliste.keySet());
        model.addAttribute("skadeChecklisteVærdier", skadeCheckliste.values());

        session.setAttribute("lejeAftaleID", lejeAftaleID);

        return OPRET_RAPPORT_PAGE;
    }

    // purpose of refresh method: opdater session med valgte data fra form submit
    @GetMapping("/refresh")
    public String refresh(HttpSession session, Model model, @RequestParam(required = false) List<String> skaderValgt){
        Map<String, Integer> skadeCheckliste = skadeService.getSkadeCheckliste();
        List<String> skadeTyper = new ArrayList<>(skadeCheckliste.keySet());

        // hvis == null, så er der ingen valg -> restart side
        if(skaderValgt == null){
            return REDIRECT_OPRET;
        }

        // hvis != null, så er der ændringer -> opdater session
        List<String> skaderIkkeValgt = new ArrayList<>(skadeTyper);
        skaderIkkeValgt.removeAll(skaderValgt);

        // session opdateres
        session.setAttribute("skaderValgt", skaderValgt);
        session.setAttribute("skaderIkkeValgt", skaderIkkeValgt);

        return REDIRECT_OPRET;
    }

    @PostMapping("/opret")
    public String opretSkadeRapport(HttpSession session, @RequestParam int kilometerKoertOver, @RequestParam List<String> skaderValgt){
        // constructor kræver: brugerID, lejeAftaleID, kilometerKørtOver, reparationsomkostninger
            //valgteSkader form input fra html
            //brugerID = session get attribute bruger
            //lejeAftaleID = Udløbet lejeaftaler
            //kilometerKørtOver = input felt
            //reparationsomkostninger = smalet pris for skader & kilometerKoertOver SkadeRapporter


        List<Skade> valgteSkader = skadeService.genererSkadeListe(skaderValgt);
        int brugerID = (int) session.getAttribute("brugerID");


        // opret skadeRapport
        SkadeRapport skadeRapport = new SkadeRapport(); // lacks the parameters
        SkadeRapport gemtRapport = skadeRapportService.gem(skadeRapport);

        if (gemtRapport == null) {
            return REDIRECT_OPRET;
        }

        // slet session variabler
        session.removeAttribute("lejeAftaleID");
        session.removeAttribute("skaderValgt");
        session.removeAttribute("skaderIkkeValgt");

        return REDIRECT_SE + gemtRapport.getID();
    }


    @GetMapping("/se/{id}")
    public String seRapport(Model model, @PathVariable int id){
        SkadeRapport skadeRapport = skadeRapportService.findVedID(id);

        model.addAttribute("SkadeRapport", skadeRapport);

        return SE_RAPPORT_PAGE;
    }

}

