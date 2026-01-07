package be.vdab.onderhoud.taken;

import be.vdab.onderhoud.werknemers.Werknemer;
import be.vdab.onderhoud.werknemers.WerknemerMetLocatie;
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
    @GetMapping(params = "datum")
    List<Taak> findTakenVanafDatum (@RequestParam LocalDate datum) {
        return takenService.findTakenVanafDatum(datum);
    }

    @PutMapping("bevestigen/{id}")
    void updateTakenTellerEnDatum(@PathVariable long id, @RequestBody String lastPerson) {
        takenService.updateTaakTellerEnOFOnderhoudsdatum(id ,lastPerson);
    }

    @PutMapping("{id}/aanpassen")
    void rollBackTaak(@PathVariable int id, @RequestBody String persoon) {
        takenService.rollBackTaak(id,persoon);
    }
}
