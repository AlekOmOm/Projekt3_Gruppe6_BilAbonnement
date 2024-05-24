package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.BilClasses.Bil;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.Bruger;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.LejeAftale;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.SkadeRapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LejeAftaleRepository {

    // ------------------- SQL strings -------------------

    private static final String INSERT = "INSERT INTO LejeAftale (brugerID, bilID, kundeInfoID, abonnementsType, prisoverslag, afhentningssted, afleveringssted, startDato, slutDato, totalPris) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM LejeAftale";
    private static final String SELECT_WITHOUT_REPORT = "SELECT * FROM LejeAftale WHERE SkadeRapportID IS NULL";
    private static final String SELECT_BY_PRIMARYKEY = "SELECT * FROM LejeAftale WHERE ID = ?";
    private static final String SELECT_BY_FOREIGNKEYS = "SELECT * FROM LejeAftale WHERE brugerID = ? AND bilID = ? AND kundeInfoID = ?";
    public static final String SELECT_LEJE_AFTALE_BY_IDS = "SELECT * FROM LejeAftale WHERE brugerID = ? AND bilID = ? AND kundeInfoID = ?";
    private static final String UPDATE = "UPDATE LejeAftale SET abonnementsType = ?, prisoverslag = ?, afhentningssted = ?, afleveringssted = ?, startDato = ?, slutDato = ?, totalPris = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM LejeAftale WHERE ID = ?";


    // ------------------- Dependencies Injections -------------------
    private final JdbcTemplate jdbcTemplate;
    private final BrugerRepository brugerRepository;
    private final BilRepository bilRepository;
    private final KundeInfoRepository kundeInfoRepository;
    private final SkadeRapportRepository skadeRapportRepository;

    @Autowired
    public LejeAftaleRepository(JdbcTemplate jdbcTemplate, BrugerRepository brugerRepository, BilRepository bilRepository, KundeInfoRepository kundeInfoRepository, SkadeRapportRepository skadeRapportRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.brugerRepository = brugerRepository;
        this.bilRepository = bilRepository;
        this.kundeInfoRepository = kundeInfoRepository;
        this.skadeRapportRepository = skadeRapportRepository;
    }


    // ------------------- Operations (CRUD) -------------------

    public LejeAftale save(LejeAftale nyLejeAftale) {
        if (exists(nyLejeAftale)) {
            return update(nyLejeAftale);
        }

        jdbcTemplate.update(INSERT, nyLejeAftale.getBruger().getId(), nyLejeAftale.getBil().getId(), nyLejeAftale.getKundeInfo().getId(), nyLejeAftale.getAbonnementsType(), nyLejeAftale.getPrisoverslag(), nyLejeAftale.getAfhentningssted(), nyLejeAftale.getAfleveringssted(), nyLejeAftale.getStartDato(), nyLejeAftale.getSlutDato(), nyLejeAftale.getTotalPris());

        return find(nyLejeAftale);
    }
    
    public LejeAftale find(LejeAftale lejeAftale) {
        if (lejeAftale.getID()==0) {
            return jdbcTemplate.queryForObject(SELECT_BY_FOREIGNKEYS, objForeignKeyIDs(lejeAftale), this::mapRow); // her til bruges Object[] metoden
        }

        return jdbcTemplate.queryForObject(SELECT_BY_PRIMARYKEY, objPrimaryKey(lejeAftale), this::mapRow); // her til bruges Object[] metoden
    }

    public List<LejeAftale> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::mapRow);
    }

    public List<LejeAftale> findALlUdenRapport() {
        return jdbcTemplate.query(SELECT_WITHOUT_REPORT, this::mapRow);
    }

    public LejeAftale update(LejeAftale lejeAftale) {
        jdbcTemplate.update(UPDATE, lejeAftale.getAbonnementsType(), lejeAftale.getPrisoverslag(), lejeAftale.getAfhentningssted(), lejeAftale.getAfleveringssted(), lejeAftale.getStartDato(), lejeAftale.getSlutDato(), lejeAftale.getTotalPris(), lejeAftale.getID());
        return find(lejeAftale);
    }

    public boolean delete(LejeAftale lejeAftale) {
        return jdbcTemplate.update(DELETE, objPrimaryKey(lejeAftale)) > 0; // her kunne lejeAftale objekt bruges i stedet for Object[], man for simplicity bruges obj metoden
    }

    // ------------------- special operations -------------------

    public List<LejeAftale> findByVognNummer(List<String> vognNummerList) {
        String sql = "SELECT * FROM Bil WHERE VognNummer = ?";
        String joined = String.join(",", vognNummerList);
        return jdbcTemplate.query(sql, new Object[]{joined}, this::mapRow);
    }


    // ------------------- services -------------------

    public boolean exists(LejeAftale lejeAftale) {
        String sql;
        Object[] objs;

        if (lejeAftale.getID() == 0) {
            sql = SELECT_BY_FOREIGNKEYS;
            objs = objForeignKeyIDs(lejeAftale);
        } else {
            sql = SELECT_BY_PRIMARYKEY;
            objs = objPrimaryKey(lejeAftale);
        }

        List<LejeAftale> results = jdbcTemplate.query(sql, objs, this::mapRow);

        return !results.isEmpty();
    }

    // ------------------- private methods -------------------

    private void queryAllVariablesExceptID(LejeAftale nyLejeAftale, String sql) {
        if (nyLejeAftale.getSkadeRapport() == null) {
            jdbcTemplate.update(sql, nyLejeAftale.getBruger().getId(), nyLejeAftale.getBil().getId(), nyLejeAftale.getKundeInfo().getId(), nyLejeAftale.getAbonnementsType(), nyLejeAftale.getPrisoverslag(), nyLejeAftale.getAfhentningssted(), nyLejeAftale.getAfleveringssted(), nyLejeAftale.getStartDato(), nyLejeAftale.getSlutDato());
            return;
        }
        jdbcTemplate.update(sql, nyLejeAftale.getBruger().getId(), nyLejeAftale.getBil().getId(), nyLejeAftale.getKundeInfo().getId(), nyLejeAftale.getSkadeRapport().getID(), nyLejeAftale.getAbonnementsType(), nyLejeAftale.getPrisoverslag(), nyLejeAftale.getAfhentningssted(), nyLejeAftale.getAfleveringssted(), nyLejeAftale.getStartDato(), nyLejeAftale.getSlutDato());
    }

    // ------------------- Object[] methods -------------------

    // returnering af Object[] er nødvendig for query metoderne, model klassen kan desværre ikke bruges som parameter i query metoderne
    private Object[] objForeignKeyIDs(LejeAftale lejeAftale) {
        if (lejeAftale.getSkadeRapport() == null) {
            return new Object[]{lejeAftale.getBruger().getId(), lejeAftale.getBil().getId(), lejeAftale.getKundeInfo().getId()};
        }

        return new Object[]{lejeAftale.getBruger().getId(), lejeAftale.getBil().getId(), lejeAftale.getKundeInfo().getId(), lejeAftale.getSkadeRapport().getID()};

    }


    private Object[] objPrimaryKey(LejeAftale lejeAftale) {
        return new Object[]{lejeAftale.getID()};
    }


    // ------------------- mapRow -------------------

    private LejeAftale mapRow(ResultSet rs, int rowNum) throws SQLException {
        LejeAftale lejeaftale = new LejeAftale();
        lejeaftale.setID(rs.getInt("ID"));

        int brugerID = rs.getInt("brugerID");
        Bruger bruger = brugerRepository.findById(brugerID);
        if (bruger != null) {
            lejeaftale.setBruger(bruger);
        }

        int bilID = rs.getInt("bilID");
        Bil bil = bilRepository.findByID(bilID);
        if (bil != null) {
            lejeaftale.setBil(bil);
        }

        int kundeInfoID = rs.getInt("kundeInfoID");
        KundeInfo kundeInfo = kundeInfoRepository.findByID(kundeInfoID);
        if (kundeInfo != null) {
            lejeaftale.setKundeInfo(kundeInfo);
        }

        if (rs.getObject("skadeRapportID") != null) {
            int skadeRapportID = rs.getInt("skadeRapportID");
            SkadeRapport skadeRapport = skadeRapportRepository.findVedID(skadeRapportID);
            if (skadeRapport != null) {
                lejeaftale.setSkadeRapport(skadeRapport);
            }
        }

        lejeaftale.setAbonnementsType(rs.getString("abonnementsType"));
        lejeaftale.setPrisoverslag(rs.getInt("prisoverslag"));
        lejeaftale.setAfhentningssted(rs.getString("afhentningssted"));
        lejeaftale.setAfleveringssted(rs.getString("afleveringssted"));
        lejeaftale.setStartDato(rs.getDate("startDato").toLocalDate());
        lejeaftale.setSlutDato(rs.getDate("slutDato").toLocalDate());
        lejeaftale.setTotalPris(rs.getInt("TotalPris"));
        return lejeaftale;
    }




}

/*

CREATE TABLE LejeAftale
(
    ID              INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    BrugerID        INT, -- foreign key
    BilID           INT, -- foreign key
    KundeInfoID     INT, -- foreign key
    SkadeRapportID  INT, -- foreign key

    Abonnementstype VARCHAR(255) NOT NULL,
    Prisoverslag    INT,
    Afhentningssted VARCHAR(255),
    afleveringssted   VARCHAR(255),
    StartDato       DATE,
    SlutDato        DATE,

    FOREIGN KEY (BrugerID) REFERENCES Bruger (ID),
    FOREIGN KEY (BilID) REFERENCES Bil (ID),
    FOREIGN KEY (KundeInfoID) REFERENCES KundeInfo (ID),
    FOREIGN KEY (SkadeRapportID) REFERENCES SkadeRapport (ID)
);
 */