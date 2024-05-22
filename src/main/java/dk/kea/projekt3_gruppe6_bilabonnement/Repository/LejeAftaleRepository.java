package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LejeAftaleRepository {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LejeAftaleRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class LejeaftaleRowMapper implements RowMapper<LejeAftale> {
        @Override
        public LejeAftale mapRow(ResultSet rs, int rowNum) throws SQLException {
            LejeAftale lejeaftale = new LejeAftale();
            lejeaftale.setId(rs.getInt("lejeaftaleID"));
            lejeaftale.setBrugerID(rs.getInt("brugerID"));
            lejeaftale.setVognNummer(rs.getString("vognNummer"));
            lejeaftale.setAbonnementsType(rs.getString("abonnementsType"));
            lejeaftale.setKundeID(rs.getString("kundeID"));
            lejeaftale.setPrisoverslag(rs.getInt("prisoverslag"));
            lejeaftale.setAfhentningssted(rs.getString("afhentningssted"));
            lejeaftale.setAfleveringssted(rs.getString("afleveringssted"));
            lejeaftale.setStartDato(rs.getDate("startDato").toLocalDate());
            lejeaftale.setSlutDato(rs.getDate("slutDato").toLocalDate());
            return lejeaftale;
        }
    }


    public List<LejeAftale> getLejeAftalerUdenRapport() {
        String sql = "SELECT * FROM LejeAftale WHERE SkadeRapportID IS NULL";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public LejeAftale mapRow(ResultSet rs, int rowNum) throws SQLException {
        LejeAftale lejeaftale = new LejeAftale();
        lejeaftale.setID(rs.getInt("lejeaftaleID"));
        lejeaftale.setBrugerID(rs.getInt("brugerID"));
        lejeaftale.setBilID(rs.getInt("bilID"));
        lejeaftale.setKundeInfoID(rs.getInt("kundeInfoID"));
        lejeaftale.setSkadeRapportID(rs.getInt("skadeRapportID"));
        lejeaftale.setAbonnementsType(rs.getString("abonnementsType"));
        lejeaftale.setPrisoverslag(rs.getInt("prisoverslag"));
        lejeaftale.setAfhentningssted(rs.getString("afhentningssted"));
        lejeaftale.setAfleveringssted(rs.getString("afleveringssted"));
        lejeaftale.setStartDato(rs.getDate("startDato").toLocalDate());
        lejeaftale.setSlutDato(rs.getDate("slutDato").toLocalDate());
        return lejeaftale;
    }
}

    public List<LejeAftale> findByVognNummer(List<String> vognNummerList) {
        String sql = "SELECT * FROM Bil WHERE VognNummer = ?";
        String joined = String.join(",", vognNummerList);
        return jdbcTemplate.query(sql, new Object[]{joined}, new LejeaftaleRowMapper());
    }
}