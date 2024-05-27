package dk.kea.projekt3_gruppe6_bilabonnement.Repository;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class BilRepository {

    // ------------------- SQL operations -------------------
        // SQL scripts --- CRUD operations = insert, select, update, delete

    private static final String INSERT = "INSERT INTO Bil (VognNummer, Stelnummer, Model, UdstyrsNiveau, KilometerKoert, Status) VALUES (?,?,?,?,?,?)";
    // SELECT * FROM Bil WHERE Model = ? AND Status = 'Tilgængelig' LIMIT 1

    private static final String SELECT = "SELECT * FROM Bil WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Bil";
    private static final String SELECT_BY_SECONDARY_KEY = "SELECT * FROM Bil WHERE VognNummer = ?";
    private static final String SELECT_BY_STATUS = "SELECT * FROM Bil WHERE Status = ?";
    private static final String SELECT_COUNT_BY_SECONDARY_KEY = "SELECT COUNT(*) FROM Bil WHERE VognNummer = ?";
    private static final String SELECT_BY_MODEL = "SELECT * FROM Bil WHERE Model = ?";
    private static final String UPDATE = "UPDATE Bil SET VognNummer = ?, StelNummer = ?, Model = ?, UdstyrsNiveau = ?, KilometerKoert = ?, Status = ? WHERE ID = ?";
    // UPDATE Bil SET Status = 'Udlejet' WHERE ID = ?
    private static final String UPDATE_STATUS_UDLEJET = "UPDATE Bil SET Status = 'Udlejet' WHERE ID = ?";
    private static final String DELETE = "DELETE FROM Bil WHERE ID = ?";




    // ------------------- Dependencies -------------------
    private final JdbcTemplate template;

    @Autowired
    public BilRepository (JdbcTemplate template) {
        this.template = template;
    }


    // ------------------- Business Operations -------------------

    public Bil book(Bil bil) {
        bil.setSomUdlejet();
        return update(bil);
    }

    public Bil setSomTilgaengelig(Bil bil) {
        bil.setSomTilgaengelig();
        return update(bil);
    }

    public Bil setSomTilService(Bil bil) {
        bil.setSomTilService();
        return update(bil);
    }





    // ------------------- CRUD Operations -------------------

    public Bil save(Bil bil) {

        if (exists(bil)) {
            return update(bil);
        }

        template.update(INSERT, bil.getVognNummer(), bil.getStelNummer(), bil.getModel(), bil.getUdstyrsNiveau(), bil.getKilometerKoert(), bil.getStatus());

        return findByVognNummer(bil.getVognNummer());
    }

    public Bil find(Bil bil) {

        if (bil.getId() == 0) {
            return findByVognNummer(bil.getVognNummer());
        }

        List<Bil> biler = template.query(SELECT, this::mapRow, bil.getId());

        return biler.isEmpty() ? null : biler.get(0);

    }

    public List<Bil> findAll() {
        return template.query(SELECT_ALL, this::mapRow);
    }

    public Bil findByID(int bilID) {
        List<Bil> biler = template.query(SELECT, this::mapRow, bilID);

        return biler.isEmpty() ? null : biler.get(0);
    }

    public Bil findByVognNummer(String vognNummer) {
        List<Bil> biler = (template.query(SELECT_BY_SECONDARY_KEY, this::mapRow, vognNummer));

        return biler.isEmpty() ? null : biler.get(0);
    }

    public List<Bil> findByStatus(String status) {
        return template.query(SELECT_BY_STATUS, this::mapRow, status);
    }


    public Bil update (Bil bil) {
        template.update(UPDATE, bil.getVognNummer(), bil.getStelNummer(), bil.getModel(), bil.getUdstyrsNiveau(), bil.getKilometerKoert(), bil.getStatus(), bil.getId());

        return find(bil);
    }


    public void delete(Bil bil) throws EmptyResultDataAccessException {
        int antalRowsSlettet = template.update(DELETE, bil.getId());
        if (antalRowsSlettet == 0) {
            throw new EmptyResultDataAccessException("Deletion failed: No such bil exists", 1);
        }
    }



    // ------------------- Helper methods -------------------


    public boolean exists(Bil bil) {

        try {
            Integer result = template.queryForObject(SELECT_COUNT_BY_SECONDARY_KEY, Integer.class, bil.getVognNummer());
            return result != null && result > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }



    private Bil mapRow (ResultSet rs, int rowNum) throws SQLException {
        Bil bil = new Bil();
        bil.setId(rs.getInt("ID"));
        bil.setVognNummer(rs.getString("VognNummer"));
        bil.setStelNummer(rs.getString("StelNummer"));
        bil.setModel(rs.getString("Model"));
        bil.setUdstyrsNiveau(rs.getString("UdstyrsNiveau"));
        bil.setKilometerKoert(rs.getInt("KilometerKoert"));
        bil.setStatus(rs.getString("Status"));
        return bil;
    }

    public Bil bookAvailableOfType(Bil bilTypeValgt) {
        String bilModel = bilTypeValgt.getModel();

        System.out.println("Booking available car of model: " + bilModel);

        List<Bil> bilList = template.query(SELECT_BY_MODEL, this::mapRow, bilModel);

        if (bilList.isEmpty()) {
            System.out.println("bilList: " + bilList);
            throw new NoSuchElementException("No available car for the given model.");
        }

        List<Bil> tilgaengeligeBiler = new ArrayList<>();

        Bil tilgaengeligBil = new Bil();
        tilgaengeligBil.setSomTilgaengelig();

        for (Bil bil : bilList) {
            if (bil.getStatus().equals(tilgaengeligBil.getStatus())) {
                tilgaengeligeBiler.add(bil);
            }
        }

        Bil bilToBook = tilgaengeligeBiler.get(0);

        template.update(UPDATE_STATUS_UDLEJET, bilToBook.getId());

        return bilToBook;
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