package be.vdab.onderhoud.werknemers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("werknemers")
public class WerknemerController {

    private final WerknemerService werknemerService;

    public WerknemerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping(params = "naamBevat")
    List<Werknemer> findByFamilieNaamBevat(String naamBevat) {
        return werknemerService.findByFamilieNaamBevat(naamBevat);
    }
}