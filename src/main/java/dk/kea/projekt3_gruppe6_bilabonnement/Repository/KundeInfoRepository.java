package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import dk.kea.projekt3_gruppe6_bilabonnement.Model.KundeInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class KundeInfoRepository {

    // SQL scripts --- CRUD operations = instert, select, update, delete
    private static final String INSERT = "INSERT INTO KundeInfo (CPR_NR, Fornavn, Efternavn, Adresse, PostNummer, Email, MobilNummer) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_PRIMARYKEY = "SELECT * FROM KundeInfo WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM KundeInfo";
    private static final String SELECT_BY_CPR_NR = "SELECT * FROM KundeInfo WHERE CPR_NR = ?";
    private static final String UPDATE = "UPDATE KundeInfo SET CPR_NR = ?, Fornavn = ?, Efternavn = ?, Adresse = ?, PostNummer = ?, Email = ?, MobilNummer = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM KundeInfo WHERE ID = ?";

    private final JdbcTemplate template;

    public KundeInfoRepository(JdbcTemplate template) {
        this.template = template;
    }

    // ------------------- Operations -------------------

    public KundeInfo save(KundeInfo kundeInfo) {
        template.update(INSERT, kundeInfo.getCPR_NR(), kundeInfo.getFornavn(), kundeInfo.getEfternavn(), kundeInfo.getAdresse(), kundeInfo.getPostNummer(), kundeInfo.getEmail(), kundeInfo.getMobilNummer());
        return find(kundeInfo);
    }

    public KundeInfo find(KundeInfo kundeInfo) {
        if (kundeInfo.getId() == 0) {
            return template.queryForObject(SELECT_BY_CPR_NR, this::mapRow, kundeInfo.getCPR_NR());
        }
        return template.queryForObject(SELECT_BY_PRIMARYKEY, this::mapRow, kundeInfo.getId());
    }

    public KundeInfo findByID(int kundeInfoID) {
        return template.queryForObject(SELECT_BY_PRIMARYKEY, this::mapRow, kundeInfoID);
    }

    public KundeInfo update(KundeInfo kundeInfo) {
        template.update(UPDATE, kundeInfo.getCPR_NR(), kundeInfo.getFornavn(), kundeInfo.getEfternavn(), kundeInfo.getAdresse(), kundeInfo.getPostNummer(), kundeInfo.getEmail(), kundeInfo.getMobilNummer(), kundeInfo.getId());
        return find(kundeInfo);
    }

    public boolean delete(KundeInfo kundeInfo) {
        template.update(DELETE, kundeInfo.getId());
        return find(kundeInfo) == null;
    }




    // ---- private ---

    private KundeInfo mapRow (ResultSet rs, int rowNum) throws SQLException {
        KundeInfo kundeInfo = new KundeInfo();
        kundeInfo.setId(rs.getInt("ID"));
        kundeInfo.setCPR_NR(rs.getString("CPR_NR"));
        kundeInfo.setFornavn(rs.getString("Fornavn"));
        kundeInfo.setEfternavn(rs.getString("Efternavn"));
        kundeInfo.setAdresse(rs.getString("Adresse"));
        kundeInfo.setPostNummer(rs.getInt("PostNummer"));
        kundeInfo.setEmail(rs.getString("Email"));
        kundeInfo.setMobilNummer(rs.getInt("MobilNummer"));
        return kundeInfo;
    }


    public boolean exists(KundeInfo kundeInfo) {
        List<KundeInfo> result;
        if (kundeInfo.getId() == 0) {
            result = template.query(SELECT_BY_CPR_NR, this::mapRow, kundeInfo.getCPR_NR());
        } else {
            result = template.query(SELECT_BY_PRIMARYKEY, this::mapRow, kundeInfo.getId());
        }
        return !result.isEmpty();
    }


}



/*
CREATE TABLE KundeInfo
(
    ID          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CPR_NR      VARCHAR(255) NOT NULL UNIQUE,
    Fornavn     VARCHAR(255) NOT NULL,
    Efternavn   VARCHAR(255) NOT NULL,
    Adresse     VARCHAR(255) NOT NULL,
    PostNummer  VARCHAR(255) NOT NULL,
    Email       VARCHAR(255) NOT NULL,
    MobilNummer VARCHAR(255) NOT NULL
);
 */