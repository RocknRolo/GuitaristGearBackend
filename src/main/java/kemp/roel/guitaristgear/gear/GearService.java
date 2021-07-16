package kemp.roel.guitaristgear.gear;

import kemp.roel.guitaristgear.guitarist.Guitarist;
import kemp.roel.guitaristgear.guitarist.GuitaristRepository;
import kemp.roel.guitaristgear.manufacturer.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Auteur: Roel Kemp (500781)
 */

@Service
public class GearService {
    private final GuitaristRepository guitaristRepository;
    private final GearRepository gearRepository;
    private final ManufacturerRepository manufacturerRepository;

    /**
     * Constructor voor GearService. Deze service moet verschillende repositories kunnen benaderen.
     * @param guitaristRepository Nodig om een Gear object met een Guitarist object te kunnen associëren.
     * @param gearRepository Nodig om een of meer Gear objecten op te halen, toe te voegen, te bewerken of te verwijderen.
     * @param manufacturerRepository Nodig om een Gear object met een Manufacturer object te kunnen associëren.
     */
    @Autowired
    public GearService(GuitaristRepository guitaristRepository, GearRepository gearRepository, ManufacturerRepository manufacturerRepository) {
        this.guitaristRepository = guitaristRepository;
        this.gearRepository = gearRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * Geeft een lijst met alle Gear objecten in de database terug. Kan filteren op merknaam en type.
     * @param brandName Filtert de lijst van alle Gear objecten op merknaam. "brandName" mag null zijn.
     * @param type Filtert de lijst van alle Gear objecten op type. "type" mag null zijn.
     * @return Een lijst met Gear objecten. Kan leeg zijn.
     */
    public List<Gear> getAllGear(String brandName, String type) {
        List<Gear> allGear = gearRepository.findAll();
        if (brandName != null && type != null) {
            brandName = brandName.replaceAll("-"," ");
            type = type.replaceAll("-", " ");

            List<Gear> filteredByBrandNameAndType = new ArrayList<>();
            for (Gear g : allGear) {
                if (g.getBrand().getName().equalsIgnoreCase(brandName) && g.getType().equalsIgnoreCase(type)) {
                    filteredByBrandNameAndType.add(g);
                }
            }
            return filteredByBrandNameAndType;
        }
        else if (brandName != null) {
            brandName = brandName.replaceAll("-"," ");

            List<Gear> filteredByBrandName = new ArrayList<>();
            for (Gear g : allGear) {
                if (g.getBrand().getName().equalsIgnoreCase(brandName)) {
                    filteredByBrandName.add(g);
                }
            }
            return filteredByBrandName;
        }
        else if (type != null) {
            type = type.replaceAll("-", " ");

            List<Gear> filteredByType = new ArrayList<>();
            for (Gear g : allGear) {
                if (g.getType().equalsIgnoreCase(type)) {
                    filteredByType.add(g);
                }
            }
            return filteredByType;
        }
        return allGear;
    }

    /**
     * Geeft een lijst met alle Gear objecten die horen bij een bepaalde gitarist terug.
     * @param guitaristId Het id van het Guitarist object waarvan alle Gear objecten worden opgevraagd.
     * @return Een lijst met Gear objecten. Kan leeg zijn.
     */
    public List<Gear> getAllGearFromGuitarist(Long guitaristId) {
        if (guitaristRepository.findById(guitaristId).isPresent()) {
            Guitarist guitarist = guitaristRepository.findById(guitaristId).get();
            return guitarist.getGearList();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitarist with id " + guitaristId + " not found");
        }
    }

    /**
     * Een getter die een Gear object opvraagt uit de GearRepository op basis van een id.
     * @param id Het id van het opgevraagde Gear object
     * @return Het opgevraagde Gear object.
     * @throws ResponseStatusException Gooit HTTP code 404 als het Gear object niet in de database aanwezig is.
     */
    public Gear getGearById(Long id){
        Optional<Gear> g = gearRepository.findById(id);
        return g.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gear with id " + id + " not found"));
    }

    /**
     * Voegt een Gear object toe aan de database.
     * @param gear Het Gear object dat toegevoegd moet worden aan de database.
     */
    public void addGear(Gear gear) {
        gearRepository.save(gear);
    }

    /**
     * Overschrijft een Gear object op basis van id met een ander Gear object.
     * @param id Het id van het te overschijven gear object.
     * @param gear Het nieuwe Gear object waarmee het oude Gear object overschreven moet worden.
     */
    public void updateGear(Long id, Gear gear) {
        if(gearRepository.findById(id).isPresent()) {
             if (gear.getType().matches(".*\\d.*")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
             }
            gear.setId(id);
            gearRepository.save(gear);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gear with id " + id + " not found");
        }
    }

    /**
     * Voegt een Gear object toe aan de "gear list" van een Guitarist object.
     * @param guitaristId Het id van het Guitarist object met de "gear list" waaraan het Gear object toegevoegd moet worden.
     * @param gear Het gear object dat aan de "gear list" toegevoegd moet worden.
     */
    public void addGearToGuitarist(Long guitaristId, Gear gear) {
        if (guitaristRepository.findById(guitaristId).isPresent()) {
            gear = gearRepository.getGearById(gear.getId());
            Guitarist guitarist = guitaristRepository.findById(guitaristId).get();

            guitarist.addGearToGearList(gear);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitarist with id " + guitaristId + " not found");
        }
        addGear(gear);
    }

    /**
     * Associeert een Gear object met een Manufacturer object.
     * @param manufacturerId Het Manufacturer object waar mee geassocieerd moet worden.
     * @param gear Het Gear object dat met het Manufacturer object geassocieerd moet worden.
     */
    public void addGearToManufacturer(Long manufacturerId, Gear gear) {
        if (manufacturerRepository.findById(manufacturerId).isPresent()) {
            gear = gearRepository.getGearById(gear.getId());
            gear.setBrand(manufacturerRepository.findById(manufacturerId).get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacturer with id " + manufacturerId + " not found");
        }
        addGear(gear);
    }

    /**
     * Verwijrdert een Gear object uit de database, als deze niet geassocieerd is met een Guitarist object.
     * @param id Het id van het Gear object dat uit de database verwijderd moet worden.
     */
    public void deleteGear(Long id) {
        if(gearRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gear with id " + id + " not found");
        } else if (isOwned(id)) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Cannot delete gear that is still owned by a guitarist");
        } else {
            gearRepository.deleteById(id);
        }
    }

    /**
     * Controleert of een Gear object geassocieerd is met een Guitarist object.
     * @param id Het id van het te controleren Gear object.
     * @return Geeft "true" terug als het Gear object nog geassocieerd is met een Guitarist object.
     */
    public boolean isOwned(Long id) {
        for (Guitarist g : guitaristRepository.findAll()) {
            for (Gear ge : g.getGearList()) {
                if (ge.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verwijderd de associatie tussen een Gear object en een Guitarist object.
     * @param guitaristId Het id van het Guitarist object waarvan het Gear object van de gear list gehaald moet worden.
     * @param gear Het Gear object dat van de gear list van het Guitarist object gehaald moet worden.
     */
    public void removeGearFromGearList(Long guitaristId, Gear gear) {
        if (guitaristRepository.findById(guitaristId).isPresent()) {
            Guitarist guitarist = guitaristRepository.findById(guitaristId).get();
            if (!guitarist.removeGearFromGearList(gear.getId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitarist does not own gear with id " + gear.getId());
            } else {
                guitaristRepository.save(guitarist);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitarist with id " + guitaristId + " not found");
        }
    }
}