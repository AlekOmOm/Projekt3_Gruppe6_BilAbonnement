package dk.kea.projekt3_gruppe6_bilabonnement.service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.BrugerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrugerService {

    private BrugerRepository brugerRepository;

    @Autowired
    public BrugerService(BrugerRepository brugerRepository) {
        this.brugerRepository = brugerRepository;
    }

    // ------------------- CRUD -------------------

    public Bruger opretBruger(Bruger bruger) {
        System.out.println("DEBUG - BrugerService - opretBruger - bruger: " + bruger.getBrugerNavn());

        if (bruger.getBrugerNavn() == null) {
            System.out.println("Bruger er null");
            return null;
        }

        if (brugerRepository.exists(bruger)) {
            System.out.println("Bruger eksisterer allerede");
            return null;
        }

        if (bruger.getId()>0) {
            if (brugerRepository.idOccupied(bruger)) {
                System.out.println("Bruger id er allerede i brug");
                return null;
            }
            System.out.println(" bruger oprettet");
            return brugerRepository.saveWithId(bruger);
        }


        System.out.println(" bruger oprettet");
        return brugerRepository.save(bruger);
    }

    public Bruger hentBruger(int id) {
        return brugerRepository.findById(id);
    }

    public Bruger hentBruger(String brugerNavn) {
        return brugerRepository.findByBrugernavn(brugerNavn);
    }



    public boolean brugerEksisterer(Bruger bruger) {
        return brugerRepository.exists(bruger);
    }

    public List<Bruger> hentAlleBrugere() {
        return brugerRepository.findAll();
    }

    public Bruger opdaterBruger(Bruger bruger) {
        System.out.println("DEBUG - BrugerService - opdaterBruger - bruger: " + bruger.getBrugerNavn());

        if (brugerRepository.exists(bruger)) {
            System.out.println(" exists");
            System.out.println(" update called");
            return brugerRepository.update(bruger);
        }

        return brugerRepository.save(bruger);
    }

    public void sletBruger(Bruger bruger) {
        System.out.println("DEBUG - BrugerService - sletBruger - bruger: " + bruger.getBrugerNavn());

        if (bruger == null) {
            return;
        }

        if (brugerRepository.delete(bruger)) {
            System.out.println("Bruger slettet");
        } else if (brugerRepository.deleteByBrugerNavn(bruger.getBrugerNavn())) {
            System.out.println("Bruger slettet");
        } else {
            return;
        }
    }


}
