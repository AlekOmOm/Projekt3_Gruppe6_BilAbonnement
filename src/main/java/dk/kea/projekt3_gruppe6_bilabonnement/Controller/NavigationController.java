package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/LejeAftale")
    public String LejeAftale() {
        return "LejeAftale";
    }

    @GetMapping("/LejeAbonnement")
    public String LejeAbonnement() { return "LejeAbonnement"; }

    @GetMapping("/LejeKundeInfo")
    public String LejeKundeInfo() { return "LejeKunde"; }

    @GetMapping("/LejePris")
    public String LejePris() { return "LejePris"; }

}