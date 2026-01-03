package be.vdab.onderhoud.taken;

import be.vdab.onderhoud.werknemers.Werknemer;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("taken")
public class TakenController {
    private final TakenService takenService;

    public TakenController(TakenService takenService) {
        this.takenService = takenService;
    }

    @GetMapping("{id}/{locatieId}")
    List<Taak> findTakenByWerknemerId(@PathVariable long id, @PathVariable long locatieId) {
        return takenService.findTakenByWerknemerId(id, locatieId);
    }
    @PutMapping("{id}")
    void updateTakenTellerEnDatum(@PathVariable long id) {
        takenService.updateTaakTellerEnOnderhoudsdatum(id);
    }
}
