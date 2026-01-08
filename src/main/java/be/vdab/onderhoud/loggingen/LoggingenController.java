package be.vdab.onderhoud.loggingen;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("loggingen")
public class LoggingenController {
    private final LoggingenService loggingenService;

    public LoggingenController(LoggingenService loggingenService) {
        this.loggingenService = loggingenService;
    }

    @PostMapping
    int create(@RequestBody @Valid NieuweLogging nieuweLogging){
        var logging = new Logging (0,nieuweLogging.datumEnTijd(),nieuweLogging.logging(),nieuweLogging.persoon());
        var id = loggingenService.create(logging);
        return id;
    }

    @GetMapping
    List<Logging> findAll(){
        return loggingenService.findAll();
    }
}
