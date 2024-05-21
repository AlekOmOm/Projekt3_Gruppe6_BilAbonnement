package dk.kea.projekt3_gruppe6_bilabonnement.Service;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftaleDto;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftaleKortInfoDto;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.LejeAftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LejeAftaleService {

    private final LejeAftaleRepository lejeAftaleRepository;

    @Autowired
    public LejeAftaleService(LejeAftaleRepository lejeAftaleRepository) {
        this.lejeAftaleRepository = lejeAftaleRepository;
    }

    public List<LejeAftale> getLejeAftalerUdenRapport() {
        return null;
    }

    public List<LejeAftaleKortInfoDto> getInfoForLejeAftalerUdenRapport() { // info = bilId, slutDato
        // repo getLejeAftalerUdenRapport
        // konverter
        return konverterKortInfo(lejeAftaleRepository.getLejeAftalerUdenRapport());
    }


    // ------------------- service -------------------

    private List<LejeAftaleKortInfoDto> konverterKortInfo(List<LejeAftale> lejeAftaler) {
        List<LejeAftaleKortInfoDto> lejeAftaleDtos = new ArrayList<>();

        for (LejeAftale lejeAftale : lejeAftaler) {
            lejeAftaleDtos.add(konverterKortInfo(lejeAftale));
        }

        return lejeAftaleDtos;
    }

    private LejeAftaleKortInfoDto konverterKortInfo(LejeAftale lejeAftale) {
        return new LejeAftaleKortInfoDto(
                lejeAftale.getID(),
                lejeAftale.getBilID(),
                lejeAftale.getSlutDato()
        );
    }



    private LejeAftaleDto konverter(LejeAftale lejeAftale) {
        return new LejeAftaleDto(
                lejeAftale.getID(),
                lejeAftale.getBrugerID(),
                lejeAftale.getBilID(),
                lejeAftale.getKundeInfoID(),
                lejeAftale.getSkadeRapportID(),

                lejeAftale.getAbonnementsType(),
                lejeAftale.getPrisoverslag(),
                lejeAftale.getAfhentningssted(),
                lejeAftale.getAfleveringssted(),
                lejeAftale.getStartDato(),
                lejeAftale.getSlutDato()
        );
    }

    private List<LejeAftaleDto> konverter(List<LejeAftale> lejeAftaler) {
        List<LejeAftaleDto> lejeAftaleDtos = new ArrayList<>();

        for (LejeAftale lejeAftale : lejeAftaler) {
            lejeAftaleDtos.add(konverter(lejeAftale));
        }

        return lejeAftaleDtos;
    }

    private LejeAftale konverter(LejeAftaleDto dto) {
        return new LejeAftale(
                dto.getID() == 0 ? 0 : dto.getID(),
                dto.getBrugerID(),
                dto.getBilID(),
                dto.getKundeID(),

                dto.getSkadeRapportID(),
                dto.getAbonnementsType(),
                dto.getPrisoverslag(),
                dto.getAfhentningssted(),
                dto.getAfleveringssted(),
                dto.getStartDato(),
                dto.getSlutDato()
        );
    }


}
