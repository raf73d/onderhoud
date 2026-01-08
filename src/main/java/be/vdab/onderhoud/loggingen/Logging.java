package be.vdab.onderhoud.loggingen;

import java.time.LocalDateTime;

public class Logging {
    private final int id;
    private final LocalDateTime datumEnTijd;
    private final String logging;
    private final String  persoon;

    public Logging(int id, LocalDateTime datumEnTijd, String logging, String persoon) {
        this.id = id;
        this.datumEnTijd = datumEnTijd;
        this.logging = logging;
        this.persoon = persoon;
    }
    public int getId() {
        return id;
    }

    public LocalDateTime getDatumEnTijd() {
        return datumEnTijd;
    }

    public String getLogging() {
        return logging;
    }

    public String getPersoon() {
        return persoon;
    }
}
