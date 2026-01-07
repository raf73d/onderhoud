package be.vdab.onderhoud.taken;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class TakenService {

    private final TakenRepository takenRepository;
    private final BackupTakenRepository backupTakenRepository;
    public TakenService(TakenRepository takenRepository, BackupTakenRepository backupTakenRepository) {
        this.takenRepository = takenRepository;
        this.backupTakenRepository = backupTakenRepository;
    }

    List<Taak> findTakenByWerknemerId(long werknemerId, long locatieId)
    {
        return takenRepository.findTakenByWerknemerId(werknemerId, locatieId);
    }

    List<Taak> findTakenVanafDatum (LocalDate datum){
        return takenRepository.findTakenvanafDatum(datum);
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
            var backupTaak = new BackupTaak((int)taak.getId(),
                    taak.getOnderhoudsDatum(),
                    taak.getTeller(),
                    taak.getLastPerson());
            backupTakenRepository.maakBackupTaak(backupTaak);

    }

    @Transactional
    void rollBackTaak(int id, String persoon)
    {
        BackupTaak backupTaak = backupTakenRepository.getBackupTaak(id)
                .orElseThrow(()->new BackupTaakNietGevondenException());
        Taak taak = takenRepository.findTaak(id)
                .orElseThrow(()->new TaakNietGevondenException());
        var onderhoudsdatum = backupTaak.onderhoudsDatum();
        var teller = backupTaak.teller();
        var lastPerson = persoon;
        taak.setLastPerson(lastPerson);
        taak.setTeller(teller);
        taak.setOnderhoudsDatum(onderhoudsdatum);
        if(taak.getStatus()==Status.GEDAAN)
        { taak.setStatus(Status.NIETGEDAAN);}
        else{ taak.setStatus(Status.GEDAAN);}
        takenRepository.herstelTaak(backupTaak.taakId(),taak.getOnderhoudsDatum(), taak.getTeller(), taak.getLastPerson());
    }
}
