package be.vdab.onderhoud.loggingen;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class LoggingenRepository {
    private final JdbcClient jdbcClient;

    public LoggingenRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
     int create (Logging logging) {
        var sql= """
                insert into loggingen (datumEnTijd,logging,persoon)
                values (?,?,?)
                """;
        var keyholder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .params(logging.getDatumEnTijd(), logging.getLogging(), logging.getPersoon())
                .update(keyholder);
        return Objects.requireNonNull(keyholder.getKey()).intValue();
     }
     List<Logging> findAll() {
        var sql= """
                select id,datumEnTijd,logging,persoon
                from loggingen
                order by datumEnTijd desc
                """;
        return jdbcClient.sql(sql).query(Logging.class).list();
     }
}
