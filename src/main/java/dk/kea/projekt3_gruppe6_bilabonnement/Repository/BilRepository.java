package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.sql.PreparedStatement;
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
            bil.setUdstyrsNiveau(rs.getString("udstyrsNiveau"));
            bil.setKilometerKoert(rs.getInt("kilometer"));
            bil.setStatus(rs.getString("status"));
            return bil;
        }
    }

    public Bil save(Bil bil) {
        String sqlStatement = "INSERT INTO Bil (VognNummer, Stelnummer, Model, Maerke, UdstyrsNiveau, KilometerKoert, Status) VALUES (?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, new String[] {"ID"});
            ps.setString(1, bil.getVognNummer());
            ps.setString(2, bil.getStelNummer());
            ps.setString(3, bil.getModel());
            ps.setString(4, bil.getMaerke());
            ps.setString(5, bil.getUdstyrsNiveau());
            ps.setInt(6, bil.getKilometerKoert());
            ps.setString(7, bil.getStatus());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);

        if (keyHolder.getKey() != null) {
            BigInteger bigIntId = (BigInteger) keyHolder.getKey();
            Integer id = bigIntId.intValue();
            bil.setId(id);
        }
        return bil;
    }

    public Bil findByVognNummer(String vognNummer) {
        String sql = "SELECT * FROM Bil WHERE VognNummer = ?";
        List<Bil> biler = jdbcTemplate.query(sql, new BilRowMapper(), vognNummer);
        return biler.isEmpty() ? null : biler.get(0);
    }

    public List<Bil> findByStatus(String status) {
        String sql = "SELECT * FROM BIL WHERE status = ?";
        return jdbcTemplate.query(sql, new BilRowMapper(), status);
    }

    public List<Bil> findAll() {
        String sql = "SELECT * FROM BIL";
        return jdbcTemplate.query(sql, new BilRowMapper());
    }
}