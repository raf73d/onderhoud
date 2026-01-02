package be.vdab.onderhoud.taken;

import be.vdab.onderhoud.werknemers.Werknemer;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TakenRepository {
    private final JdbcClient jdbcClient;

    public TakenRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
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
                    status
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
    }

