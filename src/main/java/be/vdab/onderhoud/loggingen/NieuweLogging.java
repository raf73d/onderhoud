package be.vdab.onderhoud.loggingen;

import java.time.LocalDateTime;

public record NieuweLogging(LocalDateTime datumEnTijd,
                            String logging,
                            String persoon) {
}
