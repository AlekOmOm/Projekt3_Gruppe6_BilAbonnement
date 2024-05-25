package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SkadeRapportRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List<SkadeRapport> findAlle(){
        String sql = "SELECT * FROM SkadeRapport";
        return jdbcTemplate.query(sql, this::mapRowToSkadeRapport);
    }

    public SkadeRapport findVedID(int id){
        String sql = "SELECT* FROM SkadeRapport WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRowToSkadeRapport);
    }

   /* public SkadeRapport findMedLejeAftaleID(int id){
        String sql = "SELECT* FROM SkadeRapport WHERE LejeAftaleID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRowToSkadeRapport);
    }*/

    public void gem(SkadeRapport skadeRapport){
        String sql = "INSERT INTO SkadeRapport (brugerID, kilometerKoertOver, reparationsomkostninger) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, skadeRapport.getBrugerID(), skadeRapport.getKilometerKoertOver(), skadeRapport.getReparationsomkostninger());
    }

    public void opdater(SkadeRapport skadeRapport) {
        String sql = "UPDATE SkadeRapport SET brugerID = ?, kilometerKoertOver = ?, reparationsomkostninger = ? WHERE ID = ?";
        jdbcTemplate.update(sql, skadeRapport.getBrugerID(), skadeRapport.getKilometerKoertOver(), skadeRapport.getReparationsomkostninger(), skadeRapport.getID());
    }

    public void sletVedID(int id){
        String sql = "DELETE FROM SkadeRapport WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private SkadeRapport mapRowToSkadeRapport(ResultSet rs, int rowNum) throws SQLException{
        return new SkadeRapport(
                rs.getInt("ID"),
                rs.getInt("brugerID"),
                rs.getInt("kilometerKoertOver"),
                rs.getInt("reparationsomkostninger")
        );
    }
}