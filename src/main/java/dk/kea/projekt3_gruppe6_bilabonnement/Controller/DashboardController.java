package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("AntalUdlejedeBiler", dashboardService.seAntalUdlejdeBiler());
        model.addAttribute("SamletIndkomstForBiler", dashboardService.seTotalIndkomst());
    }

    @GetMapping("/dashboard")
    public String seDashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("/forretningsrapport")
    public String seForretningsRapport(Model model) {
    return "forretningsrapport";
    }

}
