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


    // ------------------- SQL Queries -------------------
    private static final String INSERT = "INSERT INTO Skader (SkadeRapportID, Type, Pris) VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM Skader WHERE ID = ?";
    private static final String SELECT_ALL_FRA_SKADERAPPORT = "SELECT * FROM Skader WHERE SkadeRapportID = ?";
    private static final String SELECT_WITHOUT_ID = "SELECT * FROM Skader WHERE SkadeRapportID = ? AND Type = ? AND Pris = ?";
    private static final String UPDATE = "UPDATE Skader SET SkadeRapportID = ?, Type = ?, Pris = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM Skader WHERE ID = ?";



    // ------------------- Operations (CRUD) -------------------


    public List<Skade> saveAll(List<Skade> skader) {
        for (Skade skade : skader) {
            save(skade);
        }
        return skader;
    }

    private Skade save(Skade skade) {
        jdbcTemplate.update(INSERT, skade.getSkadeRapportID(), skade.getType(), skade.getPris());
        return find(skade);
    }

    public List<Skade> findAlleSkader(int SkadeRapportID){
        return jdbcTemplate.query(SELECT_ALL_FRA_SKADERAPPORT, this::MapRowToSkade, SkadeRapportID);
    }

    public Skade find(Skade skade) {
        if (skade.getId() == 0) {
            return findNew(skade);
        } else {
            return find(skade.getId());
        }
    }

    private Skade find(int id) {

        return jdbcTemplate.queryForObject(SELECT_ALL, this::MapRowToSkade, id);
    }

    private Skade findNew(Skade skade) {
        return jdbcTemplate.queryForObject(SELECT_WITHOUT_ID, this::MapRowToSkade, skade.getSkadeRapportID(), skade.getType(), skade.getPris());
    }

    public Skade update(Skade skade) {
        jdbcTemplate.update(UPDATE, skade.getSkadeRapportID(), skade.getType(), skade.getPris(), skade.getId());
        return find(skade);
    }

    public void delete(Skade skade) {
        jdbcTemplate.update(DELETE, skade.getId());
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

/*
CREATE TABLE Skader
(
    ID             INT AUTO_INCREMENT PRIMARY KEY,
    SkadeRapportID INT NOT NULL,
    Type           VARCHAR(255),
    Pris           INT,
    FOREIGN KEY (SkadeRapportID) REFERENCES SkadeRapport (ID)
);
 */
