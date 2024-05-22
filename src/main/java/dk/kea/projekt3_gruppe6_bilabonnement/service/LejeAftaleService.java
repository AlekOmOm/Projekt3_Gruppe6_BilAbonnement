package dk.kea.projekt3_gruppe6_bilabonnement.service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LejeAftaleService {

    private LejeAftaleRepository lejeAftaleRepository;

    @Autowired
    public LejeAftaleService(LejeAftaleRepository lejeAftaleRepository) {
        this.lejeAftaleRepository = lejeAftaleRepository;
    }

    public void saveLejeAftale(LejeAftale lejeAftale) {
        lejeAftaleRepository.save(lejeAftale);
    }
}
