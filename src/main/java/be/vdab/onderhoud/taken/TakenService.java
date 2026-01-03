package be.vdab.onderhoud.taken;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class TakenService {

    private final TakenRepository takenRepository;

    public TakenService( TakenRepository takenRepository) {
        this.takenRepository = takenRepository;
    }

    List<Taak> findTakenByWerknemerId(long werknemerId, long locatieId)
    {
        return takenRepository.findTakenByWerknemerId(werknemerId, locatieId);
    }
   @Transactional (readOnly=false)
    void updateTaakTellerEnOnderhoudsdatum(long id){
        takenRepository.updateTaaktellerEnOnderhoudsdatum(id);
    }
}
