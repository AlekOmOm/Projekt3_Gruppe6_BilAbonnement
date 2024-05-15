package dk.kea.projekt3_gruppe6_bilabonnement;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.ModelBil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
    public class DashboardController {

    @GetMapping("/dashboard")
    public String Showdashboard(Model model) {
        model.addAttribute("bil", bil);
        model.addAttribute("alleBiler", alleBiler);
        model.addAttribute("samletIntjening", samletIntjening);
        return "dashboard";
    }

}

