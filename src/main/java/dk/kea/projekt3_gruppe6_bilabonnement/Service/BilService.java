package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilService {

    private final BilFactory bilFactory;
    private final BilRepository bilRepository;

    @Autowired
    public BilService(BilFactory bilFactory, BilRepository bilRepository) {
        this.bilFactory = bilFactory;
        this.bilRepository = bilRepository;
    }

    // ------------------- Bil CRUD -------------------

    public Bil createBilInstance(String model) {
        return bilFactory.create(model);
    }


    // ------------------- Business Operations -------------------

    public Bil book(Bil bil) {
        if (exists(bil)) {
            return bilRepository.book(bil);
        }

        return saveBil(bil);
    }

    public Bil setSomTilgaengelig(Bil bil) {
        return bilRepository.setSomTilgaengelig(bil);
    }

    public Bil setSomTilService(Bil bil) {
        return bilRepository.setSomTilService(bil);
    }


    // ------------------- DB CRUD -------------------

    public Bil saveBil(Bil nyBil) {

        if (bilRepository.exists(nyBil)) {
            return bilRepository.update(nyBil);
        }

        return bilRepository.save(nyBil);
    }

    public Bil getBil(Bil bil) {
        return bilFactory.initialize(bilRepository.find(bil));
    }
    public Bil getBil(int id) {
        return bilFactory.initialize(bilRepository.findByID(id));
    }

    public List<Bil> getAlleBiler() {
        return bilFactory.initializeList(bilRepository.findAll());
    }

    public List<Bil> getBilerByStatus(String status) {
        return bilFactory.initializeList(bilRepository.findByStatus(status));
    }

    public Bil getBilByVognNummer(String vognNummer) {
        return bilFactory.initialize(bilRepository.findByVognNummer(vognNummer));
    }

    public Bil update(Bil bil) {
        return bilRepository.update(bil);
    }

    public boolean delete(Bil bil) {
        try {
            bilRepository.delete(bil);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return false;
    }



    // ------------------- Helper methods -------------------

    public boolean exists(Bil bil) {
        if (bil != null && (bil.getVognNummer() != null || bil.getId() != 0)){
            return bilRepository.exists(bil);
        }
        return false;
    }


}
