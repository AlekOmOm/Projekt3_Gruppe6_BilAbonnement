package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.ForretningsRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.DashboardService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.ForretningsRapportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        int antalUdlejedeBiler = dashboardService.seAntalUdlejdeBiler();
        int samletIndkomstForBiler = dashboardService.seTotalIndkomst();
        List<ForretningsRapport> alleRapporter = forretningsRapportService.seAlleRapporter();
        model.addAttribute("AntalUdlejedeBiler", antalUdlejedeBiler);
        model.addAttribute("SamletIndkomstForBiler", samletIndkomstForBiler);
        model.addAttribute("AlleRapporter", alleRapporter);
    }

    @GetMapping("/dashboard")
    public String seDashboard(Model model) {
        // This method already calls addAttributes to set model attributes
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

    @GetMapping("/se/{id}")
    public String SeRapportmedid(Model model){
        return "redirect:/forretningsrapport";
    }
}
