package be.vdab.onderhoud.taken;

public class TaakNietGevondenException extends RuntimeException {
    public TaakNietGevondenException() {
        super("Taak niet gevonden.");
    }
}
