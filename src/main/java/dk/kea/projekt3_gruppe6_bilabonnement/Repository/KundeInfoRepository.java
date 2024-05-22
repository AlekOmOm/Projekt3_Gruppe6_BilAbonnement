package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KundeInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public KundeInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // metode til at gemme kundeinfo i databasen
    public void save(KundeInfo kundeInfo) {
        String sql = "INSERT INTO kundeinfo (fornavn, efternavn, adresse, postnummer, by, mobilnummer, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, kundeInfo.getFornavn(), kundeInfo.getEfternavn(), kundeInfo.getAdresse(), kundeInfo.getPostnummer(), kundeInfo.getBy(), kundeInfo.getMobilnummer(), kundeInfo.getEmail());
    }

}

