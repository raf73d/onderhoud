package be.vdab.onderhoud.locaties;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LocatieRepository {
    private final JdbcClient jdbcClient;

    public LocatieRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<Locatie> getLocatie(int id) {
        var sql = """
                select id, naam
                from locaties
                where id = ?
                """;
        return jdbcClient.sql(sql).param(id).query(Locatie.class).optional();
    }


}
