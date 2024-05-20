package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BrugerDto;
import dk.kea.projekt3_gruppe6_bilabonnement.service.BrugerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BrugerController {

    private final BrugerService brugerService;

    @Autowired
    public BrugerController(BrugerService brugerService) {
        this.brugerService = brugerService;
    }

    // ------------------- Login -------------------
    @GetMapping("/")
    public String index() {
        return "Home";
    }

    @GetMapping("/login")
    public String login() {
        return "Home";
    }

    @PostMapping("/login")
    public String loginBruger(HttpSession session, BrugerDto brugerDTO, Model model) {

        // tjek om allerede logged in
        if(session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn")) {
            BrugerDto loggedInBruger = (BrugerDto) session.getAttribute("loggedInBruger");

            // TODO: Sprint 2 - tilføj notification for UI "Allerede logget in som "+ loggedInBruger.getBrugerNavn +", Log out før du kan logge ind.

            return "Home";
        }

        BrugerDto loginBruger = brugerService.login(brugerDTO); // TODO: Sprint 2 - exception handling for NullPointerException

        // login fejlet (loginBruger == null) -> return "Login"
        if (loginBruger == null) {
            session.setAttribute("loggedIn", false);
            model.addAttribute("bruger", brugerDTO); // for at vise brugernavn i form

            return "Home";
        }

        // login lykkedes
        session.invalidate(); // session reset
        session.setAttribute("loggedIn", true);
        session.setAttribute("loggedInBrugerNavn", loginBruger.getBrugerNavn()); // brugernavn og rolle gemmes separat, i stedet for hele Dto objektet, for at undgå at password gemmes i Session
        session.setAttribute("loggedInBrugerRolle", loginBruger.getRolle());

        return omdirigerBruger(loginBruger.getRolle());
    }

    // ------------------- Register -------------------
    @GetMapping("/registrer")
    public String registrer(Model model) {

        // boolean for rendering af Home: login eller registrer vises.
        model.addAttribute("visRegistrering", true);

        return "Home";
    }

    @PostMapping("/registrer")
    public String registrerBruger(HttpSession session, BrugerDto brugerDTO, Model model) {
        BrugerDto brugerRegistrer = brugerService.registrer(brugerDTO); // TODO: exception handling for NullPointerException

        // fejlet -> Home
        if (brugerRegistrer == null) {
            model.addAttribute(brugerDTO); // for at vise data i form

            return "Home";
        }

        // lykkedes
        model.addAttribute("brugerRegistreret", brugerRegistrer); // registreret bruger
        model.addAttribute("visRegistrering", false); // overskriver registrer attribute -> false

        return "Home";
    }



    // ------------------- Logout -------------------

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate(); // sletter session

        return "Home"; // TODO: logout knap på bruger ikon i Header
    }



    // ------------------- services -------------------

    private String omdirigerBruger(String rolle) {
        switch (rolle) {
            case "DATA_REGISTRERING":
                return "redirect:/dataregistrering";
            case "SKADE_UDBEDRING":
                return "redirect:/skadeudbedring";
            case "FORRETNINGS_UDVIKLING":
                return "redirect:/forretningsudvikling";
            default:
                return "Login";
        }
    }
}
