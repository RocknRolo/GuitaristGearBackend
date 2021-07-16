package kemp.roel.guitaristgear.guitarist;

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
public class GuitaristService {
    private final GuitaristRepository guitaristRepository;


    /**
     * Constructor voor GuitaristService.
     * @param guitaristRepository Nodig om een of meer Guitarist objecten op te halen,
     *                            toe te voegen, te bewerken of te verwijderen.
     */
    @Autowired
    public GuitaristService(GuitaristRepository guitaristRepository) {
        this.guitaristRepository = guitaristRepository;
    }

    /**
     * Geeft een lijst terug met alle Guitarist objecten in de database, eventueel gefilterd op genre of geboortejaar.
     * @param genre Het genre waar op gefilterd moet worden. Mag null zijn.
     * @param birthYear Het geboortejaar waar op gefilterd moet worden. Mag null zijn.
     * @return Een lijst met Guitarist objecten.
     */
    public List<Guitarist> getAllGuitarists(String genre, String birthYear) {
        List<Guitarist> allGuitarists = guitaristRepository.findAll();
        if (genre != null && birthYear != null) {
            genre = genre.replaceAll("-", " ");

            List<Guitarist> filteredByGenreAndBirthYear = new ArrayList<>();
            for (Guitarist g : allGuitarists) {
                if (g.getGenre().equalsIgnoreCase(genre) && g.getBirthYear() == Integer.parseInt(birthYear)) {
                    filteredByGenreAndBirthYear.add(g);
                }
            }
            return filteredByGenreAndBirthYear;
        }
        if (genre != null) {
            genre = genre.replaceAll("-", " ");
            List<Guitarist> filteredByGenre = new ArrayList<>();
            for (Guitarist g : allGuitarists) {
                if (g.getGenre().equalsIgnoreCase(genre)) {
                    filteredByGenre.add(g);
                }
            }
            return filteredByGenre;
        }
        if (birthYear != null) {
            return guitaristRepository.findGuitaristsByBirthYear(Integer.parseInt(birthYear));
        }
        return guitaristRepository.findAll();
    }

    /**
     * Geeft een Guitarist object terug op basis van id.
     * @param id Het id van het opgevraagde Guitarist object.
     * @return Het opgevraagde Guitarist object. Mits aanwezig.
     * @throws ResponseStatusException Wordt gegooit als er geen Guitarist object met het opgevraagde id gevonden is.
     */
    public Guitarist getGuitaristById(Long id) {
        Optional<Guitarist> g = guitaristRepository.findById(id);
        return g.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitarist with id " + id + " not found"));
    }

    /**
     * Geeft een Guitarist object terug op basis van naam.
     * @param name De naam die hoort bij het te vinden Guitarist object.
     * @return Het gevonden Guitarist object. Mits aanwezig.
     * @throws ResponseStatusException Als er geen Guitarist object met de opgevraagde naam in de database aanwezig is.
     */
    public Guitarist getGuitaristByName(String name) {
        Optional<Guitarist> g = guitaristRepository.findGuitaristByName(name);
        return g.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Guitarist with name \"" + name + "\" not found"));
    }

    /**
     * Voegt een Guitarist object toe aan de database.
     * @param guitarist Het toe te voegen Guitarist object.
     */
    public void addGuitarist(Guitarist guitarist) {
        guitaristRepository.save(guitarist);
    }

    /**
     * Overschrijft het Guitarist object dat het meegegeven id heeft met een nieuw Guitarist object.
     * @param id Het id van het Guitarist object dat overschreven moet worden.
     * @param guitarist Het nieuwe Guitarist object waarmee het oude object overschreven moet worden.
     */
    public void updateGuitarist(Long id, Guitarist guitarist) {
        if(guitaristRepository.findById(id).isPresent()) {
            guitarist.setId(id);
            guitaristRepository.save(guitarist);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitarist with id " + id + " not found");
        }
    }

    /**
     * Verwijdert een Guitarist object met een bepaald id uit de database.
     * @param id Het id van het te verwijderen Guitarist object.
     */
    public void deleteGuitarist(Long id) {
        if(guitaristRepository.findById(id).isPresent()) {
            guitaristRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitarist with id " + id + " not found");
        }
    }
}