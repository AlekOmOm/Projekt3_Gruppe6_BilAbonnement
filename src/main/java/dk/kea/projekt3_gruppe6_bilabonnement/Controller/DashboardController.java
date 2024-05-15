package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.ModelBil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
    public class DashboardController {

    @GetMapping("/dashboard")
    public String Showdashboard(Model model) {
        model.addAttribute("bil", ModelBil);
        model.addAttribute("alleBiler", alleBiler);
        model.addAttribute("samletIntjening", samletIntjening);
        return "dashboard";
    }
}

// antal udlejede biler - stream alle biler og sorter after status
// samlet intjening - stream alle leje aftaler og sort efter status og get alle prisoverlag.
