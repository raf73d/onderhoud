package be.vdab.onderhoud.taken;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class BackupTakenRepository {
    private final JdbcClient jdbcClient;
    public BackupTakenRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    //taken toevoegen
  void maakBackupTaak (BackupTaak backupTaak){
        var sql= """
                 insert into backuptaken (taakId, onderhoudsDatum, teller, lastPerson)
                 values (?, ?, ?, ?)
                """;
        jdbcClient
                .sql(sql)
                .params(backupTaak.taakId(),backupTaak.onderhoudsDatum(),backupTaak.teller(),backupTaak.lastPerson())
                .update();
  }

  //backuptaak terugkrijgen van een taakid die laatst toegevoegd is

    Optional<BackupTaak> getBackupTaak(int taakId){
        var sql= """
                SELECT taakId, onderhoudsDatum, teller, lastPerson
                        FROM backuptaken
                        WHERE taakId=?
                        ORDER BY id DESC
                        LIMIT 1;
        """;
        return jdbcClient.sql(sql).param(taakId).query(BackupTaak.class).optional();

    }




}
