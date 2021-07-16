package kemp.roel.guitaristgear.manufacturer;

import kemp.roel.guitaristgear.gear.Gear;
import kemp.roel.guitaristgear.gear.GearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Auteur: Roel Kemp (500781)
 */

@RestController
@RequestMapping("/gg/manufacturer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;
    private final GearService gearService;

    /**
     * Constructor voor ManufacturerController.
     * @param manufacturerService Het ManufacturerService object waar de controller zijn informatie uit haalt.
     */
    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService, GearService gearService) {
        this.manufacturerService = manufacturerService;
        this.gearService = gearService;
    }

    // GET mappings

    /**
     * De methode die aangeroepen wordt als er een GET request wordt gestuurd naar "/gg/manufacturer".
     * Geeft een lijst terug van Manufacturer objecten. Eventueel gefilterd op mainProductType.
     * @param mainProductType Een String die gebruikt kan worden om te filteren op mainProductType. Mag null zijn.
     * @return Een lijst van Manufacturer objecten. Kan leeg zijn.
     */
    @GetMapping
    public List<Manufacturer> getAllManufacturers(String mainProductType) {
        return manufacturerService.getAllManufacturers(mainProductType);
    }

    /**
     * De methode die aangeroepen wordt als er een GET request wordt gestuurd naar "/gg/manufacturer/{id}".
     * Geeft een Manufacturer object terug op basis van id.
     * @param id Het id van het opgevraagde Manufacturer object.
     * @return Het opgevraagde manufacturer object.
     */
    @GetMapping("/{id}")
    public Manufacturer getManufacturerById(@PathVariable Long id) {
        return manufacturerService.getManufacturerById(id);
    }

    // POST mappings
    /**
     * De methode die aangeroepen wordt als er een POST request wordt gestuurd naar "/gg/manufacturer".
     * Voegt een Manufacturer object toe aan de database.
     * @param manufacturer Het manufacturer object dat toegevoegd moet worden aan de database.
     */
    @PostMapping()
    public void addManufacturer(@RequestBody Manufacturer manufacturer) {
        manufacturerService.addManufacturer(manufacturer);
    }

    // PUT mappings
    /**
     * De methode die aangeroepen wordt als er een PUT request wordt gestuurd naar "/gg/manufacturer/{id}".
     * Overschrijft het Manufacturer object met het meegegeven id, met een nieuw Manufacturer object.
     * @param id Het id van het te overschrijven Manufacturer object.
     * @param manufacturer Het nieuwe Manufacturer object.
     */
    @PutMapping("/{id}")
    public void updateManufacturer(@PathVariable Long id, @RequestBody Manufacturer manufacturer) {
        manufacturerService.updateManufacturer(id, manufacturer);
    }

    /**
     * De methode die aangeroepen wordt als er een PUT request wordt gestuurd naar "/gg/manufacturer/gear/{id}".
     * Associeert een Gear object met een Manufacturer object.
     * @param id Het id van het Manufacturer object dat met het Gear object geassocieerd moet worden.
     * @param gear Het Gear object dat met het Manufacturer object geassocieerd moet worden. De Request Body hoeft
     *             alleen het gearId van het te associeren Gear object te bevatten.
     */
    @PutMapping("/gear/{id}")
    public void addGearToManufacturer(@PathVariable Long id, @RequestBody Gear gear) {
        gearService.addGearToManufacturer(id, gear);
    }

    // DELETE mappings

    /**
     * De methode die aangeroepen wordt als er een DELETE request wordt gestuurd naar "/gg/manufacturer/{id}".
     * Verwijdert een Manufacturer object uit de database.
     * @param id Het id van het te verwijderen Manufacturer object.
     */
    @DeleteMapping("/{id}")
    public void deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
    }
}