package be.vdab.onderhoud.users;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    Optional<String> getName(int id) {
        var sql = """
                select naam from usernames
                where id = ?
                """;
        return jdbcClient.sql(sql).param(id).query(String.class).optional();
    }
}
