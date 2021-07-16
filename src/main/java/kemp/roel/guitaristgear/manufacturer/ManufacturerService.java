package kemp.roel.guitaristgear.manufacturer;

import kemp.roel.guitaristgear.gear.Gear;
import kemp.roel.guitaristgear.gear.GearRepository;
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
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final GearRepository gearRepository;

    /**
     * Contructor voor ManufacturerService. Deze service moet verschillende Repositories kunnen benaderen.
     * @param manufacturerRepository Nodig om een of meer Manufacturer objecten op te halen, toe te voegen,
     *                               te bewerken of te verwijderen.
     * @param gearRepository Wordt gebruikt om te controleren of er zich een Gear object in de database bevind
     *                       dat is geassocieerd met een bepaald Manufacturer object.
     */
    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository, GearRepository gearRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.gearRepository = gearRepository;
    }

    /**
     * Geeft een lijst van alle Manufacturer objecten terug. Eventuuel gefilterd op mainProductType.
     * @param mainProductType De String waarmee gefilterd kan worden op mainProductType
     * @return Een lijst van Manufacturer objecten. Kan leeg zijn.
     */
    public List<Manufacturer> getAllManufacturers(String mainProductType) {
        List<Manufacturer> allManufacturers = manufacturerRepository.findAll();
        if (mainProductType != null) {
            mainProductType = mainProductType.replace("-", " ");

            List<Manufacturer> filteredByMainProductType = new ArrayList<>();
            for (Manufacturer m : allManufacturers) {
                if (m.getMainProductType().equalsIgnoreCase(mainProductType)) {
                    filteredByMainProductType.add(m);
                }
            }
            return filteredByMainProductType;
        }
        return allManufacturers;
    }

    /**
     * Geeft een Manufacturer object terug op basis van id.
     * @param id Het id van het opgevraagde manufacturer object.
     * @return Het opgevraagde manufacturer object. Mits aanwezig.
     * @throws ResponseStatusException Wordt gegooit als er geen Manufacturer object mat dat id in de DB aanwezig is.
     */
    public Manufacturer getManufacturerById(Long id) {
        Optional<Manufacturer> m = manufacturerRepository.findById(id);
        return m.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacturer with id " + id + " not found"));
    }

    /**
     * Geeft een Manufacturer object terug op basis van naam.
     * @param name De naam die hoort bij het opgevraagde Manufacturer object.
     * @return Het opgevraagde Manufacturer object.
     * @throws ResponseStatusException Wordt gegooit als er geen Manufacturer object mat die naam in de DB aanwezig is.
     */
    public Manufacturer getManufacturerByName(String name) {
        Optional<Manufacturer> m = manufacturerRepository.findManufacturerByName(name);
        return m.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Manufacturer with name \"" + name + "\" not found"));
    }

    /**
     * Voegt Manufacturer object toe aan de database.
     * @param manufacturer Het toe te voegen Manufacturer object.
     */
    public void addManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    /**
     * Overschrijft het Manufacturer object met het meegegeven id, met een nieuw Manufacturer object.
     * @param id Het id van het te overschrijven Manufacturer object.
     * @param manufacturer Het nieuwe Manufacturer object.
     */
    public void updateManufacturer(Long id, Manufacturer manufacturer) {
        if (manufacturerRepository.findById(id).isPresent()) {
            manufacturer.setId(id);
            manufacturerRepository.save(manufacturer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacturer with id " + id + " not found");
        }
    }

    /**
     * Verwijderd een Manufacturer object op basis van id.
     * @param id Het id van het te verwijderen Manufacturer object.
     */
    public void deleteManufacturer(Long id) {
        if (manufacturerRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacturer with id " + id + " not found");
        }
        else if (hasGearinDB(id)) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                    "Manufacturer with id " + id + " is still associated with Gear in database.");
        } else {
            manufacturerRepository.deleteById(id);
        }
    }

    /**
     * Een methode die controleert of een Manufacturer object geassocieerd is met een Gear object in de database.
     * @param manId Het id van het te controleren Manufacturer object.
     * @return true als het Manufacturer object geassocieerd is met een Gear object in de DB. Anders false.
     */
    public boolean hasGearinDB(Long manId) {
        for (Gear gear : gearRepository.findAll()) {
            if (gear.getBrand().getId().equals(manId)) {
                return true;
            }
        }
        return false;
    }
}