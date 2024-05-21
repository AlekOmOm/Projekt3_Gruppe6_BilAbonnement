package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BrugerDto;
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

    // ------------------- Services -------------------

    public BrugerDto login(BrugerDto brugerDTO) {
        if (brugerDTO == null) {
            return null;
        }

        Bruger brugerLogin = konverter(brugerDTO);
        Bruger brugerDB = brugerRepository.findByBrugernavn(brugerDTO.getBrugerNavn());

        // Tjek om: 1. brugerDB findes, 2. brugerDB er lig brugerLogin
        if (brugerDB == null || !brugerDB.equalsWithoutId(brugerLogin)) {
            return null;
        }

        // returner brugerDTO
        return konverter(brugerDB);
    }


    public BrugerDto registrer(BrugerDto brugerDTO) {
        if (brugerDTO == null) {
            return null;
        }

        Bruger brugerRegistrer = konverter(brugerDTO);
        Bruger brugerDB = opretBruger(brugerRegistrer);

        // Tjek om: 1. brugerDB oprettet, 2. brugerDB er lig brugerLogin
        if (brugerDB == null || !brugerDB.equalsWithoutId(brugerRegistrer)) {
            return null;
        }

        return konverter(brugerDB);
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
            if (brugerRepository.idAlreadyInUse(bruger)) {
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
        boolean deleted = brugerRepository.delete(bruger) ||
                brugerRepository.deleteByBrugerNavn(bruger.getBrugerNavn());

        // TODO: omdan sout til notifaction message for user
        System.out.println("DEBUG - BrugerService - sletBruger - bruger: " + bruger.getBrugerNavn() + (deleted ? " slettet" : " ikke slettet"));
    }


    // ------------------- DTO -------------------

    public BrugerDto konverter(Bruger bruger) {
        return new BrugerDto(bruger.getId(), bruger.getBrugerNavn(), bruger.getPassword(), bruger.getRolle().name());
    }

    public Bruger konverter(BrugerDto brugerDto) {
        return new Bruger(brugerDto.getId(), brugerDto.getBrugerNavn(), brugerDto.getPassword(), brugerDto.getRolle());
    }

}
