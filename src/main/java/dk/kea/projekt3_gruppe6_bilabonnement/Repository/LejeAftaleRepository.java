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

    // 1 create
    private static final String INSERT = "INSERT INTO LejeAftale (brugerID, bilID, kundeInfoID, skadeRapportID, farve, afleveringsforsikring, selvrisiko, daekpakke, vejhjaelp, udleveringVedFDM, abonnementslaengde, kmPrMdr, afhentningssted, startDato, slutDato, totalPris) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_WITHOUT_RAPPORT = "INSERT INTO LejeAftale (brugerID, bilID, kundeInfoID, farve, afleveringsforsikring, selvrisiko, daekpakke, vejhjaelp, udleveringVedFDM, abonnementslaengde, kmPrMdr, afhentningssted, startDato, slutDato, totalPris) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        // 2. read
    private static final String SELECT_ALL = "SELECT * FROM LejeAftale";
    private static final String SELECT_WITHOUT_REPORT = "SELECT * FROM LejeAftale WHERE SkadeRapportID IS NULL";
    private static final String SELECT_BY_PRIMARYKEY = "SELECT * FROM LejeAftale WHERE ID = ?";
    private static final String SELECT_BY_FOREIGNKEYS = "SELECT * FROM LejeAftale WHERE brugerID = ? AND bilID = ? AND kundeInfoID = ?";
    public static final String SELECT_LEJE_AFTALE_BY_IDS = "SELECT * FROM LejeAftale WHERE brugerID = ? AND bilID = ? AND kundeInfoID = ? AND skadeRapportID = ?";
        // 3. update
    private static final String UPDATE = "UPDATE LejeAftale SET brugerID = ?, bilID = ?, kundeInfoID = ?, skadeRapportID = ?, farve = ?, afleveringsforsikring = ?, selvrisiko = ?, daekpakke = ?, vejhjaelp = ?, udleveringVedFDM = ?, abonnementslaengde = ?, kmPrMdr = ?, afhentningssted =?, startDato = ?, slutDato = ?, totalPris = ? WHERE ID = ?";
    private static final String UPDATE_WITHOUT_SKADERAPPORT = "UPDATE LejeAftale SET brugerID = ?, bilID = ?, kundeInfoID = ?, farve = ?, afleveringsforsikring = ?, selvrisiko = ?, daekpakke = ?, vejhjaelp = ?, udleveringVedFDM = ?, abonnementslaengde = ?, kmPrMdr = ?, afhentningssted =?, startDato = ?, slutDato = ?, totalPris = ? WHERE ID = ?";
        // 4. delete
    private static final String DELETE = "DELETE FROM LejeAftale WHERE ID = ?";


    // ------------------- Dependencies Injections -------------------
    private final JdbcTemplate jdbcTemplate;
        // de følgende Repositories bliver brugt i mapRow, når at et LejeAftale instance skal mappes med dets objekter fra foreign keys
        // dette sker, da vores Model Class ikke reflektere 1-til-1 med dets table, fordi model class har objekter i sig, hvor table har foreign keys.
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

        if (nyLejeAftale.getSkadeRapport() == null) {
            jdbcTemplate.update(INSERT_WITHOUT_RAPPORT, nyLejeAftale.getBruger().getId(), nyLejeAftale.getBil().getId(), nyLejeAftale.getKundeInfo().getId(), nyLejeAftale.getFarve(), nyLejeAftale.isAfleveringsforsikring(), nyLejeAftale.isSelvrisiko(), nyLejeAftale.isDaekpakke(), nyLejeAftale.isVejhjaelp(), nyLejeAftale.isUdleveringVedFDM(), nyLejeAftale.getAbonnementslaengde(), nyLejeAftale.getKmPrMdr(), nyLejeAftale.getAfhentningssted(), nyLejeAftale.getStartDato(), nyLejeAftale.getSlutDato(), nyLejeAftale.getTotalPris());
        } else {
            jdbcTemplate.update(INSERT, nyLejeAftale.getBruger().getId(), nyLejeAftale.getBil().getId(), nyLejeAftale.getKundeInfo().getId(), nyLejeAftale.getSkadeRapport().getID(), nyLejeAftale.getFarve(), nyLejeAftale.isAfleveringsforsikring(), nyLejeAftale.isSelvrisiko(), nyLejeAftale.isDaekpakke(), nyLejeAftale.isVejhjaelp(), nyLejeAftale.isUdleveringVedFDM(), nyLejeAftale.getAbonnementslaengde(), nyLejeAftale.getKmPrMdr(), nyLejeAftale.getAfhentningssted(), nyLejeAftale.getStartDato(), nyLejeAftale.getSlutDato(), nyLejeAftale.getTotalPris());
        }

        return find(nyLejeAftale);
    }
    
    public LejeAftale find(LejeAftale lejeAftale) {
        if (lejeAftale.getID()==0) {
            return jdbcTemplate.queryForObject(SELECT_BY_FOREIGNKEYS, objForeignKeyIDs(lejeAftale), this::mapRow);
        }

        return jdbcTemplate.queryForObject(SELECT_BY_PRIMARYKEY, objPrimaryKey(lejeAftale), this::mapRow);
        // return (SELECT_BY_PRIMARYKEY, LejeAftale.class, lejeAftale.getID());
    }

    public List<LejeAftale> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::mapRow);
    }

    public List<LejeAftale> findALlUdenRapport() {
        return jdbcTemplate.query(SELECT_WITHOUT_REPORT, this::mapRow);
    }



    public LejeAftale update(LejeAftale lejeAftale) {
        if (lejeAftale.getSkadeRapport() == null) {
            // LejeAftale fields: bruger, bil, kundeInfo, farve, afleveringsforsikring, selvrisiko, daekpakke, vejhjaelp, udleveringVedFDM, abonnementslaengde, kmPrMdr, afhentningssted, startDato, slutDato
            jdbcTemplate.update(UPDATE_WITHOUT_SKADERAPPORT, lejeAftale.getBruger().getId(), lejeAftale.getBil().getId(), lejeAftale.getKundeInfo().getId(), lejeAftale.getFarve(), lejeAftale.isAfleveringsforsikring(), lejeAftale.isSelvrisiko(), lejeAftale.isDaekpakke(), lejeAftale.isVejhjaelp(), lejeAftale.isUdleveringVedFDM(), lejeAftale.getAbonnementslaengde(), lejeAftale.getKmPrMdr(), lejeAftale.getAfhentningssted(), lejeAftale.getStartDato(), lejeAftale.getSlutDato(), lejeAftale.getID());
        } else {
            jdbcTemplate.update(UPDATE, lejeAftale.getBruger().getId(), lejeAftale.getBil().getId(), lejeAftale.getKundeInfo().getId(), lejeAftale.getSkadeRapport().getID(), lejeAftale.getFarve(), lejeAftale.isAfleveringsforsikring(), lejeAftale.isSelvrisiko(), lejeAftale.isDaekpakke(), lejeAftale.isVejhjaelp(), lejeAftale.isUdleveringVedFDM(), lejeAftale.getAbonnementslaengde(), lejeAftale.getKmPrMdr(), lejeAftale.getAfhentningssted(), lejeAftale.getStartDato(), lejeAftale.getSlutDato(), lejeAftale.getID());
        }
        return find(lejeAftale);
    }

    public boolean delete(LejeAftale lejeAftale) {
        return jdbcTemplate.update(DELETE, objPrimaryKey(lejeAftale)) > 0;
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

    private void queryAllVariablesExceptID(LejeAftale lejeAftale, String sql) {
        if (lejeAftale.getSkadeRapport() == null) {
            // LejeAftale fields: bruger, bil, kundeInfo, farve, afleveringsforsikring, selvrisiko, daekpakke, vejhjaelp, udleveringVedFDM, abonnementslaengde, kmPrMdr, afhentningssted, startDato, slutDato
            jdbcTemplate.update(sql, lejeAftale.getBruger().getId(), lejeAftale.getBil().getId(), lejeAftale.getKundeInfo().getId(),lejeAftale.getFarve(), lejeAftale.isAfleveringsforsikring(), lejeAftale.isSelvrisiko(), lejeAftale.isDaekpakke(), lejeAftale.isVejhjaelp(), lejeAftale.isUdleveringVedFDM(), lejeAftale.getAbonnementslaengde(), lejeAftale.getKmPrMdr(), lejeAftale.getAfhentningssted(), lejeAftale.getStartDato(), lejeAftale.getSlutDato(), lejeAftale.getTotalPris(), lejeAftale.getID());
            return;
        }
        jdbcTemplate.update(sql, lejeAftale.getBruger().getId(), lejeAftale.getBil().getId(), lejeAftale.getKundeInfo().getId(), lejeAftale.getSkadeRapport().getID(), lejeAftale.getFarve(), lejeAftale.isAfleveringsforsikring(), lejeAftale.isSelvrisiko(), lejeAftale.isDaekpakke(), lejeAftale.isVejhjaelp(), lejeAftale.isUdleveringVedFDM(), lejeAftale.getAbonnementslaengde(), lejeAftale.getKmPrMdr(), lejeAftale.getAfhentningssted(), lejeAftale.getStartDato(), lejeAftale.getSlutDato(), lejeAftale.getTotalPris(), lejeAftale.getID());
    }

    // ------------------- Object[] methods -------------------

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

        lejeaftale.setFarve(rs.getString("farve"));
        lejeaftale.setAfleveringsforsikring(rs.getBoolean("afleveringsforsikring"));
        lejeaftale.setSelvrisiko(rs.getBoolean("selvrisiko"));
        lejeaftale.setDaekpakke(rs.getBoolean("daekpakke"));
        lejeaftale.setVejhjaelp(rs.getBoolean("vejhjaelp"));
        lejeaftale.setUdleveringVedFDM(rs.getBoolean("udleveringVedFDM"));
        lejeaftale.setAbonnementslaengde(rs.getInt("abonnementslaengde"));
        lejeaftale.setKmPrMdr(rs.getInt("kmPrMdr"));
        lejeaftale.setAfhentningssted(rs.getString("afhentningssted"));
        lejeaftale.setStartDato(rs.getDate("startDato").toLocalDate());
        lejeaftale.setSlutDato(rs.getDate("slutDato").toLocalDate());
        lejeaftale.setTotalPris(rs.getInt("totalPris"));

        return lejeaftale;
    }




}

/*


CREATE TABLE LejeAftale
(
    ID                    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    BrugerID              INT, -- foreign key
    KundeInfoID           INT, -- foreign key
    BilID                 INT, -- foreign key
    SkadeRapportID        INT, -- foreign key

    Farve                 VARCHAR(255),
    Afleveringsforsikring BOOLEAN,
    Selvrisiko            BOOLEAN,
    Daekpakke             BOOLEAN,
    Vejhjaelp             BOOLEAN,
    UdleveringVedFDM      BOOLEAN,
    Abonnementslaengde    INT,
    KmPrMdr               INT,
    Afhentningssted       VARCHAR(255),
    StartDato             DATE,
    SlutDato              DATE,
    TotalPris             INT,

    FOREIGN KEY (BrugerID) REFERENCES Bruger (ID),
    FOREIGN KEY (KundeInfoID) REFERENCES KundeInfo (ID),
    FOREIGN KEY (BilID) REFERENCES Bil (ID),
    FOREIGN KEY (SkadeRapportID) REFERENCES SkadeRapport (ID)
);


 */