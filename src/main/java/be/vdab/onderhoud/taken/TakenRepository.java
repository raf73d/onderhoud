package be.vdab.onderhoud.taken;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TakenRepository {
    private final JdbcClient jdbcClient;

    public TakenRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    void updateTaaktellerEnOnderhoudsdatum (long id,String lastPerson) {
        var sql = """
                update taken
                set teller = 0,
                    onderhoudsDatum = CURRENT_DATE(),
                    status = 'GEDAAN',
                    lastPerson = ?
                where id = ?
                """;
        if (jdbcClient.sql(sql).param(lastPerson).param(id).update() == 0) {
            throw new TaakNietGevondenException();
        }
    }
    void updateTaakOnderhoudsdatum (long id, String lastPerson) {
        var sql = """
                update taken
                set onderhoudsDatum = CURRENT_DATE(),
                    status = 'GEDAAN',
                    lastPerson = ?
                where id = ?
        """;
        if (jdbcClient.sql(sql).param(lastPerson).param(id).update() == 0) {
            throw new TaakNietGevondenException();
        }
    }

    Optional<Taak> findTaak(long id) {
        var sql = """
                select id, onderhoudsDatum, teller, omschrijving, machineId, limietId, maintenanceType, mode, status, version, lastPerson
                from taken
                where id = ?
               
        """;
        return jdbcClient.sql(sql).param(id).query(Taak.class).optional();
    }

    List<Taak> findTakenByWerknemerId (long id, long locatieId){
        var sql = """
                select\s
                    taken.id as id,
                    onderhoudsDatum,
                    teller,
                    omschrijving,
                    machines.id as machineId,
                    maintenanceType,
                    mode,
                    status,
                    lastPerson
                from taken
                inner join machines
                    on taken.machineId = machines.id
                inner join werknemers
                    on machines.locatieId = werknemers.locatieId
                inner join limieten
                    on taken.limietId = limieten.id
                where werknemers.id = ?
                  and machines.locatieId = ?
                 and (
                      (limieten.taakTypeEenheid = 'COUNT' and teller > limieten.hoeveelheid)
                      or
                      (limieten.taakTypeEenheid = 'DAY' and DATE_ADD(onderhoudsDatum, INTERVAL limieten.hoeveelheid DAY)
                 < curdate())
                      or
                      (limieten.taakTypeEenheid = 'NONE')  -- voorbeeld
                    )
                order by onderhoudsDatum is null, onderhoudsDatum;
                """;
        return jdbcClient.sql(sql)
                .param(id)
                .param(locatieId)
                .query(Taak.class).list();
    }

    public List<Taak> findTakenvanafDatum(LocalDate datum) {
        var sql = """
                select id,
                    onderhoudsDatum,
                    teller,
                    omschrijving,
                    machineId,
                    maintenanceType,
                    mode,
                    status,
                    lastPerson from taken
                where onderhoudsDatum >= ?
                order by onderhoudsDatum is null, onderhoudsDatum;
        """;
        return jdbcClient.sql(sql).param(datum).query(Taak.class).list();
    }

    }

