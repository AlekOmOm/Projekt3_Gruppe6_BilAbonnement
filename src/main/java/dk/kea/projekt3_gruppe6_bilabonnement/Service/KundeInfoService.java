package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.KundeInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundeInfoService {


    // ------------------- Dependency Injections -------------------

    private final KundeInfoRepository kundeInfoRepository;

    public KundeInfoService(KundeInfoRepository kundeInfoRepository) {
        this.kundeInfoRepository = kundeInfoRepository;
    }

    // ------------------- Operations CRUD -------------------

    public KundeInfo save(KundeInfo kundeInfo) {
        if (notHaveNecessaryVariables(kundeInfo)) {
            System.out.println("KundeInfoService.save() - Missing necessary variables");
            System.out.println(kundeInfo.toString());
            System.out.println();
            return null;
        }
        if (exists(kundeInfo)) {
            System.out.println("KundeInfoService.save() - KundeInfo already exists");
            return update(kundeInfo);
        }

        return kundeInfoRepository.save(kundeInfo);
    }


    public KundeInfo find(KundeInfo kundeInfo) {
        return kundeInfoRepository.find(kundeInfo);
    }

    public List<KundeInfo> findAll () {
        return kundeInfoRepository.findAll();
    }

    public KundeInfo update(KundeInfo kundeInfo) {
        if (notHaveNecessaryVariables(kundeInfo)) {
            System.out.println("KundeInfoService.update() - Missing necessary variables");
            return null;
        }
        return kundeInfoRepository.update(kundeInfo);
    }

    public boolean delete(KundeInfo kundeInfo) {
        return kundeInfoRepository.delete(kundeInfo);
    }

    // ------------------- Helper methods -------------------

    public boolean exists(KundeInfo kundeInfo) {
        return kundeInfoRepository.exists(kundeInfo);
    }


    private boolean notHaveNecessaryVariables(KundeInfo kundeInfo) {

        return kundeInfo.getCPR_NR() == null || kundeInfo.getFornavn() == null || kundeInfo.getEfternavn() == null || kundeInfo.getAdresse() == null || kundeInfo.getPostNummer() == 0 || kundeInfo.getEmail() == null || kundeInfo.getMobilNummer() == 0;
    }


}

