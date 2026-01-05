package be.vdab.onderhoud.werknemers;

import be.vdab.onderhoud.locaties.LocatieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class WerknemerService {
    private final WerknemerRepository werknemerRepository;
    private final LocatieRepository locatieRepository;

    public WerknemerService(WerknemerRepository werknemerRepository, LocatieRepository locatieRepository) {
        this.werknemerRepository = werknemerRepository;
        this.locatieRepository = locatieRepository;
    }

    List<WerknemerMetLocatie> findByFamilieNaamBevat (String woord){
      List<Werknemer> werknemersLijst = werknemerRepository.findByFamilieNaamBevat(woord);
      List<WerknemerMetLocatie> werknemerMetLocatie = new ArrayList<>();
      for (Werknemer werknemer : werknemersLijst) {
          var locatie = locatieRepository.getLocatie((int)werknemer.getLocatieId()).get().getNaam();
          var nieuweWerkenemerMetLocatie= new WerknemerMetLocatie(werknemer.getId(),werknemer.getVoornaam(),werknemer.getFamilienaam(),locatie, (int) werknemer.getLocatieId());
          werknemerMetLocatie.add(nieuweWerkenemerMetLocatie);
      }
      return werknemerMetLocatie;
    }
}
