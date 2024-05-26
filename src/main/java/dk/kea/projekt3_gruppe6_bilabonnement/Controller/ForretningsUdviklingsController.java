package dk.kea.projekt3_gruppe6_bilabonnement.Controller;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.ForretningsRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.DashboardService;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.ForretningsRapportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
// getmapping til dashboard
@GetMapping("/dashboard")
public String seDashboard(Model model) {
    int antalUdlejedeBiler = dashboardService.seAntalUdlejdeBiler();
    int samletIndkomstForBiler = dashboardService.seTotalIndkomst();
    List<ForretningsRapport> alleRapporter = forretningsRapportService.seAlleRapporter();
    model.addAttribute("AntalUdlejedeBiler", antalUdlejedeBiler);
    model.addAttribute("SamletIndkomstForBiler", samletIndkomstForBiler);
    model.addAttribute("alleRapporter", alleRapporter);
    return "dashboard";
}

    @GetMapping("/forretningsrapport")
    public String seForretningsRapport(Model model) {
        List<ForretningsRapport> alleRapporter = forretningsRapportService.seAlleRapporter();
        if (!alleRapporter.isEmpty()) {
            ForretningsRapport latestRapport = alleRapporter.get(alleRapporter.size() - 1);
            model.addAttribute("rapport", latestRapport);
        }
        return "forretningsrapport";
    }

    @PostMapping("/forretningsrapport")
    public String genererForretningsRapport(Model model) {
        int totalBilerUdlejet = dashboardService.seAntalUdlejdeBiler();
        int samletPris = dashboardService.seTotalIndkomst();
        forretningsRapportService.NyRapport(totalBilerUdlejet, samletPris);
        return "redirect:/forretningsrapport";
    }

    @GetMapping("/forretningsrapport/se/{id}")
    public String seForretningsRapport(@PathVariable int id, Model model) {
        ForretningsRapport rapport = forretningsRapportService.findRapportMedID(id);
        if (rapport == null) {
            return "Fejl";
        }
        model.addAttribute("rapport", rapport);
        return "forretningsrapport";
    }

    @GetMapping("/dashboard/search")
    public String searchRapporter(@RequestParam("date") String date, Model model) {
        LocalDate searchDate = LocalDate.parse(date);
        List<ForretningsRapport> rapporter = forretningsRapportService.findRapporterByDate(searchDate);
        model.addAttribute("AntalUdlejedeBiler", dashboardService.seAntalUdlejdeBiler());
        model.addAttribute("SamletIndkomstForBiler", dashboardService.seTotalIndkomst());
        model.addAttribute("alleRapporter", rapporter);
        return "dashboard";
    }

}
