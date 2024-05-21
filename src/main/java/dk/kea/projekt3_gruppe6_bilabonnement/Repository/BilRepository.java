package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BilRepository {

    private final JdbcTemplate template;

    @Autowired
    public BilRepository (JdbcTemplate template) {
        this.template = template;
    }

    public Bil save(Bil bil) {
        String sqlStatement = "INSERT INTO Bil (VognNummer, Stelnummer, Model, UdstyrsNiveau, KilometerKoert, Status) VALUES (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
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

        template.update(sql, bil.getVognNummer(), bil.getStelNummer(), bil.getModel(), bil.getUdstyrsNiveau(), bil.getKilometerKoert(), bil.getStatus(), bil.getId());

        System.out.println(" updatedBil: "+bil);
        return bil;
    }

    public List<Bil> findAll() {
        String sql = "SELECT * FROM Bil";

        return template.query(sql, getBilRowMapper());
    }

    public Bil findBil(int id) {
        String sql = "SELECT * FROM Bil WHERE ID = ?";

        List<Bil> biler = template.query(sql, getBilRowMapper(), id);

        return biler.isEmpty() ? null : biler.get(0);

    }

    public Bil findByVognNummer(String vognNummer) {

        System.out.println("DEBUG: BilRepository.findByVognNummer");

        System.out.println(" vognNummer: "+vognNummer);

        String sql = "SELECT * FROM Bil WHERE VognNummer = ?";

        List<Bil> biler = new ArrayList<>();
        biler.addAll(template.query(sql, getBilRowMapper(), vognNummer));

        System.out.println(" biler: "+biler);
        System.out.println();

        return biler.isEmpty() ? null : biler.get(0);
    }

    public void delete(Bil bil) {
        String sql = "DELETE FROM Bil WHERE ID = ?";

        template.update(sql, bil.getId());
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



    public boolean exists(Bil bil) {
        String sql = "SELECT COUNT(*) FROM Bil WHERE VognNummer = ?";

        return template.queryForObject(sql, Integer.class, bil.getVognNummer()) > 0;
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