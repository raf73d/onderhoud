package be.vdab.onderhoud.werknemers;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WerknemerRepository {
    private final JdbcClient jdbcClient;
    public WerknemerRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    List<Werknemer> findByFamilieNaamBevat (String woord){
        var sql = """
                select id, voornaam,familienaam, locatieId
                from werknemers
                where familienaam like ?
                order by familienaam
                """;
        return jdbcClient.sql(sql)
                .param("%"+woord+"%")
                .query(Werknemer.class).list();
    }


}
