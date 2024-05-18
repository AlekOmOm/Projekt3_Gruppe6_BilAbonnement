package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BrugerRepository {

    private JdbcTemplate template;

    public BrugerRepository(JdbcTemplate template) {
        this.template = template;
    }

    public Bruger save(Bruger bruger) {
        String sql = "INSERT INTO bruger (brugerNavn, password, rolle) VALUES (?, ?, ?)";

        template.update(sql, bruger.getBrugerNavn(), bruger.getPassword(), bruger.getRolle());


        return findByBrugernavn(bruger.getBrugerNavn());
    }

    public Bruger findById(int id) {
        String sql = "SELECT * FROM bruger WHERE id = ?";

        return template.queryForObject(sql, getBrugerRowMapper(), id);
    }

    public Bruger findByBrugernavn(String brugerNavn) {
        String sql = "SELECT * FROM bruger WHERE brugerNavn = ?";

        return template.queryForObject(sql, getBrugerRowMapper(), brugerNavn);
    }

    public List<Bruger> findAll() {
        String sql = "SELECT * FROM bruger";

        return template.query(sql, getBrugerRowMapper());
    }

    public boolean delete(Bruger bruger) {
        String sql = "DELETE FROM bruger WHERE id = ?";

        return template.update(sql, bruger.getId()) > 0; // returnerer true hvis der er blevet slettet en bruger
    }

    public boolean deleteByBrugerNavn(String brugerNavn) {
        String sql = "DELETE FROM bruger WHERE brugerNavn = ?";

        return template.update(sql, brugerNavn) > 0; // returnerer true hvis der er blevet slettet en bruger
    }

    // ------------------- service -------------------

    public boolean exists(Bruger bruger) {
        String sql = "SELECT COUNT(*) FROM bruger WHERE brugerNavn = ?";

        return template.queryForObject(sql, Integer.class, bruger.getBrugerNavn()) > 0;
    }


    private RowMapper<Bruger> getBrugerRowMapper() {
        return new RowMapper<Bruger>() {
            @Override
            public Bruger mapRow(ResultSet resultSet, int i) throws SQLException {
                Bruger bruger = new Bruger();

                if (resultSet == null) {
                    return null;
                }
                bruger.setId(resultSet.getInt("ID"));
                bruger.setBrugerNavn(resultSet.getString("brugerNavn"));
                bruger.setPassword(resultSet.getString("password"));
                bruger.setRolle(resultSet.getString("rolle"));

                return bruger;
            }
        };
    }


    public Bruger update(Bruger bruger) {
        String sql = "UPDATE bruger SET brugerNavn = ?, password = ?, rolle = ? WHERE id = ?";

        template.update(sql, bruger.getBrugerNavn(), bruger.getPassword(), bruger.getRolle(), bruger.getId());

        return findByBrugernavn(bruger.getBrugerNavn());
    }

    public Bruger saveWithId(Bruger bruger) {
        String sql = "INSERT INTO bruger (id, brugerNavn, password, rolle) VALUES (?, ?, ?, ?)";

        template.update(sql, bruger.getId(), bruger.getBrugerNavn(), bruger.getPassword(), bruger.getRolle());

        return findByBrugernavn(bruger.getBrugerNavn());
    }

    // ------------------- service -------------------

    // id occupied
    public boolean idOccupied(Bruger bruger) {
        String sql = "SELECT COUNT(*) FROM bruger WHERE id = ?";

        return template.queryForObject(sql, Integer.class, bruger.getId()) > 0;
    }
}
