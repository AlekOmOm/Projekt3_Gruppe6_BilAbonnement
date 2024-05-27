package dk.kea.projekt3_gruppe6_bilabonnement.Service;// DashboardService.java
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BilRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardService {

    public final BilService bilService;
    public final LejeAftaleRepository lejeaftaleRepository;
    public final BilRepository bilRepository;

    public DashboardService(BilService bilService, LejeAftaleRepository lejeaftaleRepository, BilRepository bilRepository){
        this.bilService = bilService;
        this.lejeaftaleRepository = lejeaftaleRepository;
        this.bilRepository = bilRepository;
    }

    //Antal udlejde biler
    public int seAntalUdlejdeBiler() {
        return bilRepository.findByStatus("Udlejet").size();
    }

    // Samlet indkomst fra udlejede biler
    public int seTotalIndkomst(){
        List<Bil> udlejedeBiler = bilService.getBilerByStatus("Udlejet");
        List<String> vognNummer = udlejedeBiler.stream().map(Bil::getVognNummer).toList();
        int totalIndkomst = lejeaftaleRepository.findByVognNummer(vognNummer).stream().mapToInt(LejeAftale::getTotalPris).sum();
        return totalIndkomst;
    }
}