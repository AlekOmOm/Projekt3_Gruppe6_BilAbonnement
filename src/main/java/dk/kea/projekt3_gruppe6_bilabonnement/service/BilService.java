package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
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



    // ------------------- CRUD -------------------

    public Bil saveBil(Bil nyBil) {
        System.out.println("DEBUG: BilService.saveBil");

        System.out.println(" nyBil: "+nyBil);
        Bil existingBil = bilRepository.findByVognNummer(nyBil.getVognNummer());
        System.out.println(" existingBil: "+existingBil);

        if (existingBil != null) {
            System.out.println(" 1: exists");

            // opdater existingBil (DB bilen) med nyBil
            update(nyBil, existingBil);

            // opdater DB bilen
            return bilRepository.update(existingBil);
        } else {
            System.out.println(" 2: does not exist");
            System.out.println("----DEBUG: saveBil end");
            return bilRepository.save(nyBil);
        }
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

    public Bil update(Bil bil) {
        return bilRepository.update(bil);
    }

    public void delete(Bil bil) {
        bilRepository.delete(bil);
    }



    // ------------------- Helper methods -------------------

    private boolean exists(Bil bil) {
        return bilRepository.exists(bil);
    }

    private Bil update(Bil bil, Bil existingBil) {
        existingBil.setVognNummer(bil.getVognNummer());
        existingBil.setStelNummer(bil.getStelNummer());
        existingBil.setModel(bil.getModel());

        existingBil.setUdstyrsNiveau(bil.getUdstyrsNiveau());
        existingBil.setKilometerKoert(bil.getKilometerKoert());
        existingBil.setStatus(bil.getStatus());
        return existingBil;
    }




}
