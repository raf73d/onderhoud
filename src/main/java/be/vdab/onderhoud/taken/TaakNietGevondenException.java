package be.vdab.onderhoud.taken;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaakNietGevondenException extends RuntimeException {
    public TaakNietGevondenException() {
        super("Taak niet gevonden.");
    }
}
