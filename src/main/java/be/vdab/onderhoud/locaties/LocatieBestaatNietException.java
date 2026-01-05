package be.vdab.onderhoud.locaties;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocatieBestaatNietException extends RuntimeException {
    public LocatieBestaatNietException() {
        super("Locatie bestaat niet.");
    }
}
