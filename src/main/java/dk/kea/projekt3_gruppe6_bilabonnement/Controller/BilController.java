//package dk.kea.projekt3_gruppe6_bilabonnement.Controller;
//import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerValgDTO;
//import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
//import dk.kea.projekt3_gruppe6_bilabonnement.Model.PackageDeals;
//import dk.kea.projekt3_gruppe6_bilabonnement.Service.BilFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//@Controller
//public class BilController {
//    @Autowired
//    private BilFactory bilFactory;
//    private BrugerValgDTO brugerValgDTO;
//
//
//    @GetMapping("/VaelgBil")
//    public String showForm(Model model) {
//        List<Bil> cars = new ArrayList<>();
//        // Add Bil objects to the list
//        cars.add(bilFactory.createCitroenC1("Vogn1", "Stel1", "UdstyrsNiveau1", 10000, "Status1"));
//        cars.add(bilFactory.createPeugeot108("Vogn2", "Stel2", "UdstyrsNiveau2", 20000, "Status2"));
//        cars.add(bilFactory.createOpelCorsaCosmo("Vogn3", "Stel3", "UdstyrsNiveau3", 30000, "Status3"));
//        model.addAttribute("cars", cars);
//        return "VaelgBil";
//
//    }
//
//    @GetMapping("/Abonnement")
//    public String showAbonnement(Model model) {
//        List<String> abonnementTyper = Arrays.asList("Månedlig", "Kvartal", "Årlig");
//        model.addAttribute("abonnementTypes", abonnementTyper);
//        return "Abonnement";
//    }
//
//    @GetMapping("/Abonnement")
//    public String showPackageDeals(Model model) {
//        List<PackageDeals> packageDeals = new ArrayList<>(Arrays.asList(                          // PACKAGE DESCRIPTION ER NÆSTEN DET SAMME SOM DET DER STÅR PÅ DERES SIDE UNDER ABONNEMENT
//                new PackageDeals("Daekpakke", 300, "Daekpakke"),
//                new PackageDeals("Aflveringsforsikring", 119, "Tilvalg af afleveringsforsikring"),
//                new PackageDeals("Lav selvrisiko", 89, ""),
//                new PackageDeals("Vejhjaelp", 49, "I samarbejde med Viking kan du få vejhjaelp til kun 49 kr. pr. maaned. Som Bilabonnement-kunde er du daekket under de vilkaar du finder under spoergsmaal og svar."),
//                new PackageDeals("Udlevering ved FDM", 599, "Udlevering til FDM er et engangsgebyr på 599 kr.")
//                ));
//        model.addAttribute("packageDeals", packageDeals);
//        return "Abonnement";
//    }
//}