package be.vdab.onderhoud.taken;

import java.time.LocalDate;

public record BackupTaak(Integer taakId,
                         LocalDate onderhoudsDatum,
                         //belangrijk voor null
                         Integer teller,
                         String lastPerson) {

}
