package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;

import java.sql.*;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BilRepository {

    private final JdbcTemplate template;

    @Autowired
    public BilRepository (JdbcTemplate template) {
        this.template = template;
    }

    public Bil save(Bil bil) {
        String sqlStatement = "INSERT INTO Bil (VognNummer, Stelnummer, Model, Maerke, UdstyrsNiveau, KilometerKoert, Status) VALUES (?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlStatement, new String[] {"ID"});
                ps.setString(1, bil.getVognNummer());
                ps.setString(2, bil.getStelNummer());
                ps.setString(3, bil.getModel());
                ps.setString(4, bil.getMaerke());
                ps.setString(5, bil.getUdstyrsNiveau());
                ps.setInt(6, bil.getKilometerKoert());
                ps.setString(7, bil.getStatus());



                return ps;
            }
        };

        template.update(psc, keyHolder);

        if (keyHolder.getKey() != null) {
            // Get the BigInteger
            BigInteger bigIntId = (BigInteger) keyHolder.getKey();

            // Convert it to an Integer
            Integer id = bigIntId.intValue();

            bil.setId(id);
        }

        return bil;
    }


    public Bil findByVognNummer(String vognNummer) {


        String sql = "SELECT * FROM Bil WHERE VognNummer = ?";

        List<Bil> biler = template.query(sql, new RowMapper<Bil>() {
            @Override
            public Bil mapRow(ResultSet resultSet, int i) throws SQLException {
                Bil bil = new Bil();
                bil.setId(resultSet.getInt("ID"));
                bil.setVognNummer(resultSet.getString("VognNummer"));
                bil.setStelNummer(resultSet.getString("StelNummer"));
                bil.setModel(resultSet.getString("Model"));
                bil.setMaerke(resultSet.getString("Maerke"));
                bil.setUdstyrsNiveau(resultSet.getString("UdstyrsNiveau"));
                bil.setKilometerKoert(resultSet.getInt("KilometerKoert"));
                bil.setStatus(resultSet.getString("Status"));
                return bil;
            }
        }, vognNummer);

        return biler.isEmpty() ? null : biler.get(0);


    }
}

/*
CREATE TABLE Bil (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    VognNummer VARCHAR(255) NOT NULL,
    StelNummer VARCHAR(255) NOT NULL UNIQUE,
    Model VARCHAR(255) NOT NULL,
    Maerke VARCHAR(255) NOT NULL,
    UdstyrsNiveau VARCHAR(255),
    KilometerKoert INT,
    Status VARCHAR(255) NOT NULL
);
 */