package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("rentedCarsCount", dashboardService.seAntalUdlejdeBiler());
        model.addAttribute("totalRentalIncome", dashboardService.seTotalIndkomst());
        return "dashboard";
    }
}

// antal udlejede biler - stream alle biler og sorter after status
// samlet intjening - stream alle leje aftaler og sort efter status og get alle prisoverlag.
