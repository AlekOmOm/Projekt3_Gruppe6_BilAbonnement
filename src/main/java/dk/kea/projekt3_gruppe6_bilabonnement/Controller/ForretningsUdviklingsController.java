package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.DashboardService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.ForretningsRapportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForretningsUdviklingsController {

    private final DashboardService dashboardService;
    private final ForretningsRapportService forretningsRapportService;

    public ForretningsUdviklingsController(DashboardService dashboardService, ForretningsRapportService forretningsRapportService) {
        this.dashboardService = dashboardService;
        this.forretningsRapportService = forretningsRapportService;
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


    @PostMapping("/forretningsrapport")
    public String genererForretningsRapport(Model model) {
        int totalBilerUdlejet = dashboardService.seAntalUdlejdeBiler();
        int samletPris = dashboardService.seTotalIndkomst();
        forretningsRapportService.NyRapport(totalBilerUdlejet, samletPris);
        return "redirect:/forretningsrapport";
    }
}

