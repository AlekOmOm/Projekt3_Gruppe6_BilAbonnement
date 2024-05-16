package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NavigationController {

    @GetMapping("/LejeAftale")
    public String LejeAftale() {
        return "LejeAftale";
    }

    @GetMapping("/LejeInput")
    public String LejeInput() {
        return "LejeInput";
    }

    @PostMapping("/LejeInput")
    public String LejeInputPost() {
        return "LejeInput";
    }
}