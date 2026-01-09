package be.vdab.onderhoud.loggingen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class LoggingenService {
    private final LoggingenRepository loggingenRepository;

    public LoggingenService(LoggingenRepository loggingenRepository) {
        this.loggingenRepository = loggingenRepository;
    }
@Transactional
    int create (Logging logging) {
        var id =loggingenRepository.create(logging);
        return id;
    }

    List<Logging> findAll() {
        return loggingenRepository.findAll();
    }

    List<Logging> findBetweenDatums(LocalDate van, LocalDate tot){
        return loggingenRepository.findBetweenDatums(van,tot);
    }
}
