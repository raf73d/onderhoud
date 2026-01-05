package be.vdab.onderhoud.taken;

import java.time.LocalDate;
import java.util.Date;

public class Taak {
    private final Long id;
    private final LocalDate onderhoudsDatum;
    private final Integer teller;
    private final String omschrijving;
    private final long machineId;
    private final MaintenanceType maintenanceType;
    private final Mode mode;
    private final Status status;
    private final String lastPerson;

    public Taak(Long id, LocalDate onderhoudsDatum, Integer teller, String omschrijving, long machineId, MaintenanceType maintenanceType, Mode mode, Status status, String lastPerson) {
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

    public LocalDate getOnderhoudsDatum() {
        return onderhoudsDatum;
    }

    public Long getId() {
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
