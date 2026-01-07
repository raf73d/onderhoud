package be.vdab.onderhoud.taken;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BackupTaakNietGevondenException extends RuntimeException {
    public BackupTaakNietGevondenException() {
        super("Backup taak niet gevonden");
    }
}
