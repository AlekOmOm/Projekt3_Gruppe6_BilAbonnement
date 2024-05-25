package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SkadeRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public SkadeRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    // ------------------- Operations (CRUD) -------------------


    public List<Skade> saveAll(List<Skade> skader) {
        for (Skade skade : skader) {
            save(skade);
        }
        return skader;
    }

    private void save(Skade skade) {
        String sql = "INSERT INTO Skader (Type, Pris) VALUES (?, ?)";
        jdbcTemplate.update(sql, skade.getType(), skade.getPris());
    }

    public List<Skade> findAlleSkader(int SkadeRapportID){
        String sql = "SELECT * FROM Skader WHERE SkadeRapportID = ?";
        return jdbcTemplate.query(sql, this::MapRowToSkade, SkadeRapportID);
    }


    // ------------------- Other Methods -------------------

    private Skade MapRowToSkade(ResultSet rs, int rowNum) throws SQLException{
        return new Skade(
                rs.getInt("ID"),
                rs.getString("Type"),
                rs.getInt("Pris")
        );
    }
}
