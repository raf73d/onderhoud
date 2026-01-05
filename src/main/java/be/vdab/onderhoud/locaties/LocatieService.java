package be.vdab.onderhoud.locaties;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class LocatieService {
    private final LocatieRepository locatieRepository;

    public LocatieService(LocatieRepository locatieRepository) {
        this.locatieRepository = locatieRepository;
    }

    Optional<Locatie> getlocatie(int id){
        return locatieRepository.getLocatie(id);
    }
}
