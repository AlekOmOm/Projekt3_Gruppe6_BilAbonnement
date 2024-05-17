package dk.kea.projekt3_gruppe6_bilabonnement.Repo;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BilRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class BilRowMapper implements RowMapper<Bil> {
        @Override
        public Bil mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bil bil = new Bil();
            bil.setVognNummer(rs.getString("vognNummer"));
            bil.setStelNummer(rs.getString("stelNummer"));
            bil.setModel(rs.getString("model"));
            bil.setMaerke(rs.getString("maerke"));
            bil.setUdstyrsNiveau(rs.getString("udstyrsNiaveu"));
            bil.setKilometerKoert(rs.getInt("kilometer"));
            bil.setStatus(rs.getString("status"));
            return bil;
        }
    }

    public List<Bil> findByStatus(String status) {
        String sql = "SELECT * FROM BIL WHERE status = ?";
        return jdbcTemplate.query(sql, new Object[]{status}, new BilRowMapper());
    }

    public List<Bil> findAll() {
        String sql = "SELECT * FROM BIL";
        return jdbcTemplate.query(sql, new BilRowMapper());
    }
}
