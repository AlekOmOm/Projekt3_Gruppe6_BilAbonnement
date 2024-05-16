package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BilController {
    @GetMapping("/LejeAftale")
    public String showForm(Model model) {
        List<String> cars = new ArrayList<>();
        cars.add("Bil 1");
        cars.add("Bil 2");
        cars.add("Bil 3");
        model.addAttribute("cars", cars);
        return "LejeAftale";
    }
}

