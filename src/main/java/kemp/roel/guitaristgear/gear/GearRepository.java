package kemp.roel.guitaristgear.gear;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Auteur: Roel Kemp (500781)
 */

@Repository
public interface GearRepository extends JpaRepository<Gear, Long> {

    /**
     * Haalt een Gear object uit de database op basis van id.
     * @param id Het id van het op te halen Gear object.
     * @return Het Gear object met het opgevraagde id.
     */
    Gear getGearById(Long id);

    /**
     * Haalt een lijst met alle Gear objecten uit de database.
     * @return Een lijst met alle Gear objecten. Kan leeg zijn.
     */
    List<Gear> findAll();

    /**
     *  Haalt een lijst met Gear objecten uit de database waarvan de String "type" overeenkomt met een meegegeven String.
     * @param type De String waar op gefilterd moet worden.
     * @return Een lijst van Gear objecten. Kan leeg zijn.
     */
    List<Gear> findGearByType(String type);
}