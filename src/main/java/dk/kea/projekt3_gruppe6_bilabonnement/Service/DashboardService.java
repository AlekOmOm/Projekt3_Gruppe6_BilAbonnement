package dk.kea.projekt3_gruppe6_bilabonnement.Service;// DashboardService.java
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BilRepository;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<Integer> bilIdList = udlejedeBiler.stream().map(Bil::getId).toList();
        List<LejeAftale> lejeAftaler = new ArrayList<>();
        for (Integer id : bilIdList) {
            lejeAftaler.add(lejeaftaleRepository.findByBilID(id));
        }

        //List<LejeAftale> lejeAftaler = lejeaftaleRepository.findByBilId(bilIdList);

        int totalIndkomst = sumTotalIncome(lejeAftaler);

        System.out.println("DEBUG: DashboardService.seTotalIndkomst");
        System.out.println(" - udlejedeBiler: " + udlejedeBiler.size());
        System.out.println(" - bilIdList: " + bilIdList.size());
        System.out.println(" - lejeAftaler: " + lejeAftaler.size());
        System.out.println(" - totalIndkomst: " + totalIndkomst);

        return totalIndkomst;
    }

    private int sumTotalIncome(List<LejeAftale> lejeAftaler) {
        int sum = 0;
        for (LejeAftale lejeAftale : lejeAftaler) {
            sum += lejeAftale.getTotalPris();
        }
        return sum;
    }
}