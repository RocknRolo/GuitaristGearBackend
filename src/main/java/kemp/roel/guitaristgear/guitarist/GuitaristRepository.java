package kemp.roel.guitaristgear.guitarist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Auteur: Roel Kemp (500781)
 */

@Repository
public interface GuitaristRepository extends JpaRepository<Guitarist, Long> {
    /**
     * Haalt Guitarist object op uit de database op basis van naam.
     * @param name De naam die hoort bij het op te halen Guitarist object.
     * @return Het opgevraagde Guitarist object. Kan null zijn.
     */
    Optional<Guitarist> findGuitaristByName(String name);

    /**
     * Geeft een lijst van alle Guitarist objecten in de database terug. Kan leeg zijn.
     * @return Een lijst van alle Guitarist objecten in de database.
     */
    List<Guitarist> findAll();

    /**
     * Geeft een lijst van Guitarist objecten die hetzelfde geboortejaar opslaan terug.
     * @param birthYear Het geboortejaar waar op gefilterd moet worden.
     * @return Een lijst van alle Guitarist objecten met het meegegeven geboortejaar. Kan leeg zijn.
     */
    List<Guitarist> findGuitaristsByBirthYear(int birthYear);
}