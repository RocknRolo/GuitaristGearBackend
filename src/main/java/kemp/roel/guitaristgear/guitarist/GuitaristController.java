package kemp.roel.guitaristgear.guitarist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Auteur: Roel Kemp (500781)
 */

@RestController
@RequestMapping("/gg/guitarist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GuitaristController {

    private final GuitaristService guitaristService;

    /**
     * Constructor voor GuitaristController.
     * @param guitaristService Het GuitaristService object waar de controller zijn informatie uit haalt.
     */
    @Autowired
    public GuitaristController(GuitaristService guitaristService) {
        this.guitaristService = guitaristService;
    }

    // GET mappings

    /**
     * De methode die aangeroepen wordt als er een GET request wordt gestuurd naar "/gg/guitarist".
     * Geeft een lijst van alle Guitarist objecten terug. Mogelijk gefilterd op genre en/of geboortejaar.
     * @param genre De String waarmee Guitarist objecten op genre gefilterd kunnen worden. Mag null zijn.
     * @param birthYear De String waarmee Guitarist objecten op geboortejaar gefilterd kunnen worden. Mag null zijn.
     * @return Een lijst van Guitarist objecten.
     */
    @GetMapping
    public List<Guitarist> getAllGuitarists(@RequestParam(required = false) String genre,
                                            @RequestParam(required = false) String birthYear) {
        return guitaristService.getAllGuitarists(genre, birthYear);
    }

    /**
     * De methode die aangeroepen wordt als er een GET request wordt gestuurd naar "/gg/guitarist/{id}".
     * Geeft het Guitarist object met het opgevraagde id terug.
     * @param id Het id van de opgevraagde gitarist
     * @return Het opgevraagde Guitarist object.
     */
    @GetMapping("/{id}")
    public Guitarist getGuitaristById(@PathVariable Long id) {
        return guitaristService.getGuitaristById(id);
    }

    // POST mappings

    /**
     * De methode die aangeroepen wordt als er een POST request wordt gestuurd naar "/gg/guitarist".
     * Voegt een Guitarist object toe aan de database.
     * @param guitarist Het toe te voegen Guitarist object.
     */
    @PostMapping
    public void addGuitarist(@RequestBody Guitarist guitarist) {
        guitaristService.addGuitarist(guitarist);
    }

    // PUT mappings

    /**
     * De methode die aangeroepen wordt als er een PUT request wordt gestuurd naar "/gg/guitarist/{id}".
     * Overschrijft in de database het Guitarist object met het meegegeven id, met het meegegeven Guitarist object.
     * @param id Het id van het te overschrijven Guitarist object.
     * @param guitarist Het nieuwe Guitarist object.
     */
    @PutMapping("/{id}")
    public void updateGuitarist(@PathVariable Long id, @RequestBody Guitarist guitarist) {
        guitaristService.updateGuitarist(id, guitarist);
    }

    // DELETE mappings.

    /**
     * De methode die aangeroepen wordt als er een DELETE request wordt gestuurd naar "/gg/guitarist/{id}".
     * Verwijderd het Guitarist object met het meegegeven id uit de database.
     * @param id Het id van het te verwijderen Guitarist object.
     */
    @DeleteMapping("/{id}")
    public void deleteGuitarist(@PathVariable Long id) {
        guitaristService.deleteGuitarist(id);
    }
}