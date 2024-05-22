package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Service.BilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BilController {
    @Autowired
    private BilFactory bilFactory;

    @GetMapping("/LejeInput")
    public String showForm(Model model) {
        List<Bil> cars = new ArrayList<>();
        // Add Bil objects to the list
        cars.add(bilFactory.createCitroenC1("Vogn1", "Stel1", "UdstyrsNiveau1", 10000, "Status1"));
        cars.add(bilFactory.createPeugeot108("Vogn2", "Stel2", "UdstyrsNiveau2", 20000, "Status2"));
        cars.add(bilFactory.createOpelCorsaCosmo("Vogn3", "Stel3", "UdstyrsNiveau3", 30000, "Status3"));
        model.addAttribute("cars", cars);
        return "VaelgBil";


    }
}