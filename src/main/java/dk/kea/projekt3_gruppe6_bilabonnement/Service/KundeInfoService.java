package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.DTO.BrugerValgDTO;
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

    // ------------------- Main Operations -------------------

    public KundeInfo getInstanceIfNew(BrugerValgDTO brugerValgDTO) {
        if (brugerValgDTO.getKundeInfoID() != 0) {
            System.out.println("KundeInfoService.getInstanceIfNew() - KundeInfo already exists");
            System.out.println(" - brugerValgDTO: " + brugerValgDTO.getKundeInfo());
            System.out.println();
            return new KundeInfo(brugerValgDTO.getKundeInfoID(), brugerValgDTO.getCprNr(), brugerValgDTO.getFornavn(), brugerValgDTO.getEfternavn(), brugerValgDTO.getAdresse(), brugerValgDTO.getPostNummer(), brugerValgDTO.getEmail(), brugerValgDTO.getMobilNummer());
        }
        return new KundeInfo(brugerValgDTO.getCprNr(), brugerValgDTO.getFornavn(), brugerValgDTO.getEfternavn(), brugerValgDTO.getAdresse(), brugerValgDTO.getPostNummer(), brugerValgDTO.getEmail(), brugerValgDTO.getMobilNummer());
    }


    // ------------------- Operations CRUD -------------------

    public KundeInfo save(KundeInfo kundeInfo) {

        if (kundeInfo.getId() != 0) {
            System.out.println("KundeInfoService.save() - KundeInfo already exists");
            return update(kundeInfo);
        }
        if (notHaveNecessaryVariables(kundeInfo)) {
            System.out.println("KundeInfoService.save() - Missing necessary variables");
            System.out.println(kundeInfo);
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
            System.out.println(kundeInfo);
            System.out.println();
            return kundeInfoRepository.findByID(kundeInfo.getId());
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

        return kundeInfo.getCprNr() == null || kundeInfo.getFornavn() == null || kundeInfo.getEfternavn() == null || kundeInfo.getAdresse() == null || kundeInfo.getPostNummer() == 0 || kundeInfo.getEmail() == null || kundeInfo.getMobilNummer() == 0;
    }


}

