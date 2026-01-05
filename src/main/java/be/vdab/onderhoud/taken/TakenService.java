package be.vdab.onderhoud.taken;


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
   @Transactional
    void updateTaakTellerEnOFOnderhoudsdatum(long id, String lastPerson){
        var taak = takenRepository.findTaak(id).orElseThrow(()->new TaakNietGevondenException());

            if (taak.getTeller() == null) {
                   takenRepository.updateTaakOnderhoudsdatum(id, lastPerson);
            }
            else {
                takenRepository.updateTaaktellerEnOnderhoudsdatum(id, lastPerson);
            };


    }
}
