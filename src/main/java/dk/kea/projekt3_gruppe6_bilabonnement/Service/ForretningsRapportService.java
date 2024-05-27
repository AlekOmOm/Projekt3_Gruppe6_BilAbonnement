package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.ForretningsRapport;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.ForretningsRapportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForretningsRapportService {

    private final ForretningsRapportRepository forretningsRapportRepository;

    public ForretningsRapportService(ForretningsRapportRepository forretningsRapportRepository) {
        this.forretningsRapportRepository = forretningsRapportRepository;
    }

    public void NyRapport(int totalBilerUdlejet, int samletPris) {
        forretningsRapportRepository.NyRapport(totalBilerUdlejet, samletPris);
    }

    public List<ForretningsRapport> findAlleRapporter() {
        return forretningsRapportRepository.seAlleRapporter();
    }

    public ForretningsRapport findRapportMedID(int id) {
        return forretningsRapportRepository.VaelgRapportMedId(id);
    }

    public List<ForretningsRapport> findRapporterByDate(LocalDate date) {
        return forretningsRapportRepository.findRapporterByDate(date);
    }
}