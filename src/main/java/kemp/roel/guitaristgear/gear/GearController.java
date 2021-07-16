package kemp.roel.guitaristgear.gear;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Auteur: Roel Kemp (500781)
 */

@RestController
@RequestMapping("/gg/gear")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GearController {

    private final GearService gearService;

    /**
     * Constructor voor GearController.
     * @param gearService Het GearService object waar de controller zijn informatie uit haalt.
     */
    @Autowired
    public GearController(GearService gearService) {
        this.gearService = gearService;
    }

    // GET mappings

    /**
     * De methode die aangeroepen wordt als er een GET request wordt gestuurd naar "/gg/gear".
     * Geeft een lijst van Gear objecten terug, eventueel gefilterd op "brandName" of "type".
     * @param brandName De String waarmee Gear objecten met de naam van een individueel Manufacturer
     *                  object gefilterd kunnen worden. Mag null zijn.
     * @param type De String waarmee Gear objecten op een bepaald type
     *             gefilterd kunnen worden. Mag null zijn.
     * @return Een lijst van Gear objecten. Kan leeg zijn.
     */
    @GetMapping
    public List<Gear> getAllGear(@RequestParam(required = false) String brandName, @RequestParam(required = false) String type) {
        return gearService.getAllGear(brandName, type);
    }

    /**
     * De methode die aangeroepen wordt als er een GET request wordt gestuurd naar "/gg/gear/{id}".
     * Geeft een Gear object terug.
     * @param id Het id van het opgevraagde Gear object
     * @return Het Gear object met het opgevraagde id.
     */
    @GetMapping("/{id}")
    public Gear getGearById(@PathVariable Long id) {
        return gearService.getGearById(id);
    }

    /**
     * De methode die aangeroepen wordt als er een GET request wordt gestuurd naar "/gg/gear/guitarist/{guitaristId}".
     * Geeft een lijst van Gear objecten terug die geassocieerd zijn met een Guitarist object met een bepaald id.
     * @param guitaristId Het id van het Guitarist object waarvan alle objecten terug gegeven moeten worden.
     * @return Een lijst van Gear objecten terug die geassocieerd zijn met een Guitarist object met een bepaald id.
     *         Kan leeg zijn.
     */
    @GetMapping("/guitarist/{guitaristId}")
    public List<Gear> getAllGearFromGuitarist(@PathVariable Long guitaristId) {
        return gearService.getAllGearFromGuitarist(guitaristId);
    }

    // POST mappings

    /**
     * De methode die aangeroepen wordt als er een POST request wordt gestuurd naar "/gg/gear".
     * Voegt een Gear object toe aan de database.
     * @param gear Het Gear object dat aan de database toegevoegd moet worden.
     */
    @PostMapping
    public void addGear(@RequestBody Gear gear) {
        gearService.addGear(gear);
    }

    // PUT mappings

    /**
     * De methode die aangeroepen wordt als er een PUT request wordt gestuurd naar "/gg/gear/{id}".
     * Overschijft het Gear object in de database met het meegegeven id, met een ander Gear object.
     * @param id Het id van het te overschrijven Gear object in de database.
     * @param gear Het nieuwe Gear object.
     */
    @PutMapping("/{id}")
    public void updateGear(@PathVariable Long id, @RequestBody Gear gear) {
        gearService.updateGear(id, gear);
    }

    /**
     * De methode die aangeroepen wordt als er een PUT request wordt gestuurd naar "/gg/gear/guitarist/{id}".
     * Associeert een Gear object met een Guitarist object.
     * @param id Het id van het Guitarist object waarmee het Gear object geassocieerd moet worden.
     * @param gear Het gear object dat met het Guitarist object geassocieerd moet worden. In de Request Body
     *             hoeft alleen maar het id te staan van het te associeren Gear object.
     */
    @PutMapping("/guitarist/{id}")
    public void addGearToGuitarist(@PathVariable Long id, @RequestBody Gear gear) {
        gearService.addGearToGuitarist(id, gear);
    }

    /**
     * De methode die aangeroepen wordt als er een PUT request wordt gestuurd naar "/gg/gear/manufacturer/{id}".
     * Associeert een Gear object met een Manufacturer object.
     * @param id Het id van het Manufacturer object waarmee het Gear object geassocieerd moet worden.
     * @param gear Het gear object dat met het Guitarist object geassocieerd moet worden. In de Request Body
     *             hoeft alleen maar het id te staan van het te associeren Gear object.
     */
    @PutMapping("/manufacturer/{id}")
    public void addGearToManufacturer(@PathVariable Long id, @RequestBody Gear gear) {
        gearService.addGearToManufacturer(id, gear);
    }

    // DELETE mappings

    /**
     * De methode die aangeroepen wordt als er een DELETE request wordt gestuurd naar "/gg/gear/{id}".
     * Verwijdert het Gear object met het meegegeven id uit de database.
     * @param id Het id van het te verwijderen Gear object.
     */
    @DeleteMapping("/{id}")
    public void deleteGear(@PathVariable Long id) {
        gearService.deleteGear(id);
    }

    /**
     * De methode die aangeroepen wordt als er een DELETE request wordt gestuurd naar "/gg/gear/guitarist/{id}".
     * Disassocieert een Gear object van een Guitarist object.
     * @param id Het id van het Guitarist object waarvan het Gear object van de gear list gehaald moet worden.
     * @param gear Het gear object dat van de gear list van het Guitarist object verwijderd moet worden.
     *             In de Request Body hoeft alleen maar het id te staan van het te associeren Gear object.
     */
    @DeleteMapping("/guitarist/{id}")
    public void removeGearFromGuitarist(@PathVariable Long id, @RequestBody Gear gear) {
        gearService.removeGearFromGearList(id, gear);
    }
}