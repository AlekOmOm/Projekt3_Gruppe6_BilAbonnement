package dk.kea.projekt3_gruppe6_bilabonnement.Repository;

import dk.kea.projekt3_gruppe6_bilabonnement.Model.ForretningsRapport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ForretningsRapportRepository {

    private final JdbcTemplate jdbcTemplate;

    public ForretningsRapportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // Mapper data fra databasen til objekter
    private static class ForretningsRapportRowMapper implements RowMapper<ForretningsRapport> {
        @Override
        public ForretningsRapport mapRow(ResultSet rs, int rowNum) throws SQLException {
            ForretningsRapport rapport = new ForretningsRapport();
            rapport.setId(rs.getInt("ID"));
            rapport.setDatoGenereret(rs.getDate("DatoGenereret").toLocalDate());
            rapport.setTotalBilerUdlejet(rs.getInt("TotalBilerUdlejet"));
            rapport.setSamletPris(rs.getInt("SamletPris"));
            return rapport;
        }
    }

    // Finder rapport ud fra ID
    public ForretningsRapport VaelgRapportMedId(int id) {
        String sql = "SELECT * FROM Forretningsrapport WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new ForretningsRapportRowMapper(), id);
    }
    // Finder alle rapporter
    public List<ForretningsRapport> seAlleRapporter() {
        String sql = "SELECT * FROM Forretningsrapport ORDER BY DatoGenereret DESC";
        return jdbcTemplate.query(sql, new ForretningsRapportRowMapper());
    }

    //Indsætter rapport data i en række i databasen
    public void NyRapport(int totalBilerUdlejet, int samletPris) {
        String sql = "INSERT INTO Forretningsrapport (DatoGenereret, TotalBilerUdlejet, SamletPris) VALUES (CURDATE(), ?, ?)";
        jdbcTemplate.update(sql, totalBilerUdlejet, samletPris);
    }
    // Finder rapporter ud fra dato
    public List<ForretningsRapport> findRapporterByDate(LocalDate date) {
        String sql = "SELECT * FROM Forretningsrapport WHERE DatoGenereret = ?";
        return jdbcTemplate.query(sql, new ForretningsRapportRowMapper(), date);
    }
}