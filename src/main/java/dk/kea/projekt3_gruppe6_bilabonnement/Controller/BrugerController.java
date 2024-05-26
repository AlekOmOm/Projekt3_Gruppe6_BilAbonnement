package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerDto;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BrugerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BrugerController {

    // Web sider:
    private static final String HOME_PAGE = "Home";
    private static final String REDIRECT_DATA_REGISTRERING = "redirect:/LejeAftale/";
    private static final String REDIRECT_SKADE_UDBEDRING = "redirect:/SkadeRapport/";
    private static final String REDIRECT_FORRETNINGS_UDVIKLING = "redirect:/Dashboard/";

    private final BrugerService brugerService;

    @Autowired
    public BrugerController(BrugerService brugerService) {
        this.brugerService = brugerService;
    }


    // ------------------- Login -------------------

    @GetMapping("")
    public String index(HttpSession session) {
        session.setAttribute("loggedIn", false);
        return HOME_PAGE;
    }
    @GetMapping("/home")
    public String home(HttpSession session) {
        session.setAttribute("loggedIn", false);
        System.out.println("DEBUG - BrugerController - home");
        System.out.println(" session data: ");
        System.out.println(" - loggedIn: "+ session.getAttribute("loggedIn"));
        System.out.println(" - loggedInBruger: "+ session.getAttribute("loggedInBruger"));
        System.out.println(" - loggedInBrugerNavn: "+ session.getAttribute("loggedInBrugerNavn"));
        return HOME_PAGE;
    }
    @GetMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("loggedIn", false);
        return HOME_PAGE;
    }

    @PostMapping("/login")
    public String loginBruger(HttpSession session, BrugerDto brugerDTO, Model model) {

        // tjek om allerede logged in
        if (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn")) {
            BrugerDto loggedInBruger = (BrugerDto) session.getAttribute("loggedInBruger");

            // TODO: Sprint 2 - tilføj notification for UI "Allerede logget in som "+ loggedInBruger.getBrugerNavn +", Log out før du kan logge ind.

            return HOME_PAGE;
        }

        // -> login
            System.out.println("DEBUG - BrugerController - loginBruger - brugerDTO: " + brugerDTO.getBrugerNavn());
            System.out.println(" bruger:"+brugerDTO);
        BrugerDto loginBruger = brugerService.login(brugerDTO); // TODO: Sprint 2 - exception handling for NullPointerException

        // login fejlet: (hvis (loginBruger == null) -> return Home)
        if (loginBruger == null) {
            session.setAttribute("loggedIn", false);
            model.addAttribute("bruger", brugerDTO); // for at vise brugernavn i form

            return HOME_PAGE;
        }



        // login lykkedes
        // -> clean up
        clearSessionAttributes(session);
        loginBruger.clearPassword();

        // -> set new session state
        session.setAttribute("loggedInBruger", loginBruger);
        session.setAttribute("loggedInBrugerNavn", loginBruger.getBrugerNavn());
        session.setAttribute("loggedInBrugerRolle", loginBruger.getRolle());
            System.out.println("DEBUG - BrugerController - loginBruger - loginBruger: " + loginBruger);
            System.out.println(" - loggedInBruger: " + session.getAttribute("loggedInBruger"));
            System.out.println(" - loggedInBrugerNavn: " + session.getAttribute("loggedInBrugerNavn"));
        session.setAttribute("loggedIn", true);

        return omdirigerBruger(loginBruger.getRolle());
    }



    // ------------------- Register -------------------

    @GetMapping("/registrer")
    public String registrer(Model model) {

        // boolean for rendering af Home: login eller registrer vises.
        model.addAttribute("visRegistrering", true);

        return HOME_PAGE;
    }

    @PostMapping("/registrer")
    public String registrerBruger(HttpSession session, BrugerDto brugerDTO, Model model) {
        BrugerDto brugerRegistrer = brugerService.registrer(brugerDTO); // TODO: exception handling for NullPointerException

        // fejlet -> Home
        if (brugerRegistrer == null) {
            model.addAttribute(brugerDTO); // for at vise data i form

            return HOME_PAGE;
        }

        // lykkedes
        model.addAttribute("brugerRegistreret", brugerRegistrer); // registreret bruger
        model.addAttribute("visRegistrering", false); // overskriver registrer attribute -> false

        return HOME_PAGE;
    }

    // ------------------- Logout -------------------

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate(); // sletter session

        return HOME_PAGE; // TODO: logout knap på bruger ikon i Header
    }


    // ------------------- services -------------------

    private String omdirigerBruger(String rolle) {
        return switch (rolle) {
            case "DATA_REGISTRERING" -> REDIRECT_DATA_REGISTRERING;
            case "SKADE_OG_UDBEDRING" -> REDIRECT_SKADE_UDBEDRING;
            case "FORRETNINGS_UDVIKLING" -> REDIRECT_FORRETNINGS_UDVIKLING;
            default -> HOME_PAGE;
        };
    }

    private void clearSessionAttributes(HttpSession session) {
        session.removeAttribute("bruger");
        session.removeAttribute("brugerRegistreret");
        session.removeAttribute("visRegistrering");

    }
}
