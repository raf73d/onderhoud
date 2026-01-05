package be.vdab.onderhoud.locaties;

public class Locatie {
    private final int id;
    private final String naam;


    public Locatie(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
