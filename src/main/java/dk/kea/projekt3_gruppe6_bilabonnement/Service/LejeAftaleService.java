package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LejeAftaleService {

    @Autowired
    LejeAftaleRepository lejeAftaleRepository;


    public List<LejeAftale> getLejeAftaleUdenRapport(){
        return lejeAftaleRepository.getLejeAftalerUdenRapport();
    }

    public List<LejeAftale> getLejeAftaleMedRapport(){
        return lejeAftaleRepository.getLejeAftaleMedRapport();
    }

    public LejeAftale findMedID(int id){
        return lejeAftaleRepository.findMedID(id);
    }
}
