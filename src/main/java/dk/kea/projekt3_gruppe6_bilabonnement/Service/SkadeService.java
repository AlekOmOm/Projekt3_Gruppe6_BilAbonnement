package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.SkadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SkadeService {

    private static final Map<String, Integer> skadeCheckliste = Map.of(
            "Bilrude", 5000,
            "Bilhjul", 2000,
            "Bilkarosseri", 10000,
            "Bilbatteri", 3000,
            "Bilbremser", 4000,
            "Bilgearkasse", 8000,
            "Bilforrude", 5000,
            "Bilfjedre", 2000,
            "Rensning af bil", 1000,
            "Bilbelysning", 1000
    );

    // ------------------- Field Injections -------------------
    SkadeRepository skadeRepository;

    @Autowired
    public SkadeService (SkadeRepository skadeRepository){
        this.skadeRepository = skadeRepository;
    }


    // ------------------- Methods for Controller -------------------


    // ------------------- Operations (CRUD) from Repo -------------------

    public List<Skade> gemSkader(List<Skade> skader){
        System.out.println("DEBUG: SkadeService.gemSkader()");
        System.out.println(" - skader: " + skader);
        return skadeRepository.saveAll(skader);
    }

    public List<Skade> getSkader(SkadeRapport skadeRapport){
        return skadeRepository.findAlleSkader(skadeRapport.getID());
    }


    // ------------------- Other Methods -------------------

    public List<Skade> genererSkadeListe(List<String> skaderValgt) {
        List<Skade> skader = new ArrayList<>();
        for (String type : skaderValgt) {

            int pris = skadeCheckliste.get(type);

            skader.add(new Skade(0, type, pris));
        }
        return skader;
    }

    public Map<String, Integer> getSkadeCheckliste(){
        return skadeCheckliste;
    }


    public int udregnReparationsomkostninger(int kilometerKoertOver, List<Skade> skader){
        int totalPris = skader.stream().mapToInt(Skade::getPris).sum();
        int kilometerPris = kilometerKoertOver * 1;
        return totalPris + kilometerPris;
    }


}
