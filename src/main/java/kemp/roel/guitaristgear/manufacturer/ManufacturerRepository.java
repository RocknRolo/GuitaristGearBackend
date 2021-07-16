package kemp.roel.guitaristgear.manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Auteur: Roel Kemp (500781)
 */

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    /**
     * Haalt een Manufacturer object uit de database op basis van een naam. Kan null zijn.
     * @param name De naam van het op te halen Manufacturer object.
     * @return Het opgevraagde Manufacturer object. Kan null zijn.
     */
    Optional<Manufacturer> findManufacturerByName(String name);
}