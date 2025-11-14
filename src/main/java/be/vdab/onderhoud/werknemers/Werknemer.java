package be.vdab.onderhoud.werknemers;

public class Werknemer {
    private final long id;
    private final String voornaam;
    private final String familienaam;
    private final long locatieId;

    public Werknemer(long id, String voornaam, String familienaam, long locatieId) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.locatieId = locatieId;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public long getLocatieId() {
        return locatieId;
    }
}
