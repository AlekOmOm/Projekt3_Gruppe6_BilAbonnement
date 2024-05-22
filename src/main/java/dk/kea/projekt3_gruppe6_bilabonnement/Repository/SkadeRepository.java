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

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Skade MapRowToSkade(ResultSet rs, int rowNum) throws SQLException{
        return new Skade(
                rs.getInt("ID"),
                rs.getString("Type"),
                rs.getInt("Pris")
        );
    }

    public List<Skade> findAlleSkader(int SkadeRapportID){
        String sql = "SELECT * FROM Skader WHERE SkadeRapportID = ?";
        return jdbcTemplate.query(sql, this::MapRowToSkade, SkadeRapportID);
    }


}
