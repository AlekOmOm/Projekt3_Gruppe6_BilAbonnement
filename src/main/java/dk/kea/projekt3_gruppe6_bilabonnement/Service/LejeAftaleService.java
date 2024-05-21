package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LejeAftaleService {

    private final LejeAftaleRepository lejeAftaleRepository;

    @Autowired
    public LejeAftaleService(LejeAftaleRepository lejeAftaleRepository) {
        this.lejeAftaleRepository = lejeAftaleRepository;
    }

}
