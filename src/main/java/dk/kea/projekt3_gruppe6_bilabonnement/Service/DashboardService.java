package dk.kea.projekt3_gruppe6_bilabonnement.Service;// DashboardService.java
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Repo.BilRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repo.LejeAftaleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardService {

    public final BilRepository bilRepository;
    public final LejeAftaleRepository lejeaftaleRepository;

    public DashboardService(BilRepository bilRepository, LejeAftaleRepository lejeaftaleRepository) {
        this.bilRepository = bilRepository;
        this.lejeaftaleRepository = lejeaftaleRepository;
    }

    public long countRentedCars() {
        return bilRepository.findByStatus("Udlejet").size();
    }

    public int calculateTotalRentalIncome() {
        List<Bil> rentedCars = bilRepository.findByStatus("Udlejet");
        List<String> vognNummer = rentedCars.stream().map(Bil::getVognNummer).toList();
        return lejeaftaleRepository.findByvognNummer(vognNummer).stream().mapToInt(LejeAftale::getPrisoverslag).sum();
    }
}