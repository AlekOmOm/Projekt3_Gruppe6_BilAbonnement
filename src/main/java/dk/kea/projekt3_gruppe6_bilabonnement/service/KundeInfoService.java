package dk.kea.projekt3_gruppe6_bilabonnement.service;


import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;
import dk.kea.projekt3_gruppe6_bilabonnement.Repository.KundeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class KundeInfoService {

        private KundeInfoRepository kundeInfoRepository;

        @Autowired
        public KundeInfoService(KundeInfoRepository kundeInfoRepository) {
            this.kundeInfoRepository = kundeInfoRepository;
        }

        public void saveKundeInfo(KundeInfo kundeInfo) {
            kundeInfoRepository.save(kundeInfo);
        }

        public KundeInfo getKundeInfoById(int id) {
            return kundeInfoRepository.findKundeInfo(id);
        }
}
