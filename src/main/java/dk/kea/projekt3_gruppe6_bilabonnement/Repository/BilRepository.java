package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
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
            bil.setUdstyrsNiveau(rs.getString("udstyrsNiveau"));
            bil.setKilometerKoert(rs.getInt("kilometer"));
            bil.setStatus(rs.getString("status"));
            return bil;
        }
    }

    public Bil save(Bil bil) {
        String sqlStatement = "INSERT INTO Bil (VognNummer, Stelnummer, Model, UdstyrsNiveau, KilometerKoert, Status) VALUES (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, new String[] {"ID"});
            ps.setString(1, bil.getVognNummer());
            ps.setString(2, bil.getStelNummer());
            ps.setString(3, bil.getModel());
            ps.setString(4, bil.getUdstyrsNiveau());
            ps.setInt(5, bil.getKilometerKoert());
            ps.setString(6, bil.getStatus());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            bil.setId(keyHolder.getKey().intValue());
        }

        return bil;
    }

    public Bil update (Bil bil) {
        System.out.println("DEBUG: BilRepository.update");
        String sql = "UPDATE Bil SET VognNummer = ?, StelNummer = ?, Model = ?, UdstyrsNiveau = ?, KilometerKoert = ?, Status = ? WHERE ID = ?";
        jdbcTemplate.update(sql, bil.getVognNummer(), bil.getStelNummer(), bil.getModel(), bil.getUdstyrsNiveau(), bil.getKilometerKoert(), bil.getStatus(), bil.getId());
        System.out.println(" updatedBil: "+bil);
        return bil;
    }

    public List<Bil> findAll() {
        String sql = "SELECT * FROM Bil";
        return jdbcTemplate.query(sql, getBilRowMapper());
    }

    public Bil findBil(int id) {
        String sql = "SELECT * FROM Bil WHERE ID = ?";

        List<Bil> biler = jdbcTemplate.query(sql, getBilRowMapper(), id);

        return biler.isEmpty() ? null : biler.get(0);

    }

    public Bil findByVognNummer(String vognNummer) {
        System.out.println("DEBUG: BilRepository.findByVognNummer");
        System.out.println(" vognNummer: "+vognNummer);
        String sql = "SELECT * FROM Bil WHERE VognNummer = ?";
        List<Bil> biler = jdbcTemplate.query(sql, new BilRowMapper(), vognNummer);
        return biler.isEmpty() ? null : biler.get(0);
    }


    public void delete(Bil bil) {
        String sql = "DELETE FROM Bil WHERE ID = ?";

        jdbcTemplate.update(sql, bil.getId());
    }

    private RowMapper<Bil> getBilRowMapper() {
        return new RowMapper<Bil>() {
            @Override
            public Bil mapRow(ResultSet resultSet, int i) throws SQLException {
                Bil bil = new Bil();

                if (resultSet == null) {
                    return null;
                }

                bil.setId(resultSet.getInt("ID"));
                bil.setVognNummer(resultSet.getString("VognNummer"));
                bil.setStelNummer(resultSet.getString("StelNummer"));
                bil.setModel(resultSet.getString("Model"));
                bil.setUdstyrsNiveau(resultSet.getString("UdstyrsNiveau"));
                bil.setKilometerKoert(resultSet.getInt("KilometerKoert"));
                bil.setStatus(resultSet.getString("Status"));
                return bil;
            }
        };
    }
    public List<Bil> findByStatus(String status) {
        String sql = "SELECT * FROM BIL WHERE status = ?";
        return jdbcTemplate.query(sql, new BilRowMapper(), status);
    }

    public boolean exists(Bil bil) {
        String sql = "SELECT COUNT(*) FROM Bil WHERE VognNummer = ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, bil.getVognNummer()) > 0;
    }

}



/*
CREATE TABLE Bil (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    VognNummer VARCHAR(255) NOT NULL UNIQUE,
    StelNummer VARCHAR(255) NOT NULL UNIQUE,
    Model VARCHAR(255) NOT NULL,
    Maerke VARCHAR(255) NOT NULL,
    UdstyrsNiveau VARCHAR(255),
    KilometerKoert INT,
    Status VARCHAR(255) NOT NULL
);
 */