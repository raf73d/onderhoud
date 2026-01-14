package be.vdab.onderhoud.techniekerbadge;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serial")
public class SerialController {

    private final SerialService serialService;

    public SerialController(SerialService serialService) {
        this.serialService = serialService;
    }

    @GetMapping("/start")
    public ResponseEntity<String> startCommunication() {
        try {
            String naam= serialService.startSerialCommunication();
            return ResponseEntity.ok(naam);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fout: " + e.getMessage());
        }
    }
    @GetMapping("/connectie")
    public ResponseEntity<String> connectieCommunication() {
        if (!serialService.isArduinoConnected()) {
            return ResponseEntity.status(400).body("poortNok");
        }
        return ResponseEntity.status(HttpStatus.OK).body("poortOk");
    }
}
