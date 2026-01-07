package be.vdab.onderhoud.taken;

import java.time.LocalDate;
import java.util.Date;

public class Taak {
    private final long id;
    private  LocalDate onderhoudsDatum;
    private  Integer teller;
    private final String omschrijving;
    private final long machineId;
    private final MaintenanceType maintenanceType;
    private final Mode mode;
    private  Status status;
    private  String lastPerson;

    public Taak(long id, LocalDate onderhoudsDatum, Integer teller, String omschrijving, long machineId, MaintenanceType maintenanceType, Mode mode, Status status, String lastPerson) {
        this.id = id;
        this.onderhoudsDatum = onderhoudsDatum;
        this.teller = teller;
        this.omschrijving = omschrijving;
        this.machineId = machineId;
        this.maintenanceType = maintenanceType;
        this.mode = mode;
        this.status = status;
        this.lastPerson = lastPerson;
    }

    public void setOnderhoudsDatum(LocalDate onderhoudsDatum) {
        this.onderhoudsDatum = onderhoudsDatum;
    }

    public void setLastPerson(String lastPerson) {
        this.lastPerson = lastPerson;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTeller(Integer teller) {
        this.teller = teller;
    }

    public LocalDate getOnderhoudsDatum() {
        return onderhoudsDatum;
    }

    public long getId() {
        return id;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public Mode getMode() {
        return mode;
    }

    public Status getStatus() {
        return status;
    }

    public long getMachineId() {
        return machineId;
    }

    public Integer getTeller() {
        return teller;
    }

    public String getLastPerson() {
        return lastPerson;
    }
}
