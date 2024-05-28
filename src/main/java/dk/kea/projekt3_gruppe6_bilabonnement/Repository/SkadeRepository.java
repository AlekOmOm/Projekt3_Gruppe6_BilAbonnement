package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.Skade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private static final String SELECT_ALL_WITHOUT_ID = "SELECT * FROM Skader WHERE SkadeRapportID = ? AND Type = ? AND Pris = ?";
    private static final String UPDATE = "UPDATE Skader SET SkadeRapportID = ?, Type = ?, Pris = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM Skader WHERE ID = ?";



    // ------------------- Operations (CRUD) -------------------


    public List<Skade> saveAll(List<Skade> skader) {
        System.out.println("DEBUG: SkadeRepository.saveAll()");
        System.out.println(" - skader: " + skader);
        List<Skade> savedSkader = new ArrayList<>();
        for (Skade skade : skader) {
            System.out.println(" - skade: " + skade);
            savedSkader.add(save(skade));
        }
        System.out.println(" - savedSkader: " + savedSkader);
        System.out.println();
        return savedSkader;
    }

    private Skade save(Skade skade) {

        if (exists(skade)) {
            return update(skade);
        }

        System.out.println("DEBUG: SkadeRepository.save()");
        System.out.println(" skade doesn't exist in database");
        System.out.println(" - skade: " + skade);

        jdbcTemplate.update(INSERT, skade.getSkadeRapportID(), skade.getType(), skade.getPris());

        return find(skade);
    }

    public List<Skade> findAlleSkader(int SkadeRapportID){
        return jdbcTemplate.query(SELECT_ALL_FRA_SKADERAPPORT, this::MapRowToSkade, SkadeRapportID);
    }

    public Skade find(Skade skade) {
        if (skade.getId() == 0) {
            System.out.println("DEBUG: SkadeRepository.find() - skade.getId() == 0");
            System.out.println(" - skade: " + skade);
            return findNew(skade);
        } else {
            return find(skade.getId());
        }
    }

    private Skade find(int id) {

        return jdbcTemplate.queryForObject(SELECT_ALL, this::MapRowToSkade, id);
    }

    private Skade findNew(Skade skade) {
        // query bruges, da der kan være flere rækker med samme data eller ingen rækker
            // queryForObject kunnes ikke bruges, da den ikke kan håndtere returnering af 0
            // queryForObject smider exception i stedet for at kunne returnerer null
            // -> query kan returnerer null ved hjælp af list og '? :'-operator (ternary operator)

        List<Skade> skader = jdbcTemplate.query(SELECT_WITHOUT_ID, this::MapRowToSkade, skade.getSkadeRapportID(), skade.getType(), skade.getPris());
        System.out.println("DEBUG: SkadeRepository.findNew()");
        System.out.println(" - skader: " + skader);
        return skader.isEmpty() ? null : skader.get(0);
    }

    public Skade update(Skade skade) {
        jdbcTemplate.update(UPDATE, skade.getSkadeRapportID(), skade.getType(), skade.getPris(), skade.getId());
        return find(skade);
    }

    public void delete(Skade skade) {
        jdbcTemplate.update(DELETE, skade.getId());
    }

    // ------------------- Service methods -------------------

    private boolean exists(Skade skade) {
        Skade skadeFound = find(skade);

        if (skadeFound == null) {
            return false;
        }

        return skade.equals(skadeFound);
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
