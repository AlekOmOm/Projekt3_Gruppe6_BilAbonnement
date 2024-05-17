package dk.kea.projekt3_gruppe6_bilabonnement.service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilService {

    private BilFactory bilFactory;
    private BilRepository bilRepository;

    @Autowired
    public BilService(BilFactory bilFactory, BilRepository bilRepository) {
        this.bilFactory = bilFactory;
        this.bilRepository = bilRepository;
    }


    // CRUD Bil methods with BilFactory to convert Bil from Repo to create CitroenC1, Peugeot108, OpelCorsaCosmo with the instance variables



    public Bil createBil(Bil bil) {
        return bilRepository.save(bil);
    }

    public List<Bil> getAlleBiler() {
        return bilFactory.initializeList(bilRepository.findAll());
    }

    public Bil getBil(int id) {
        return bilFactory.initialize(bilRepository.findBil(id));
    }

    public Bil getBilByVognNummer(String vognNummer) {
        return bilFactory.initialize(bilRepository.findByVognNummer(vognNummer));
    }




}
