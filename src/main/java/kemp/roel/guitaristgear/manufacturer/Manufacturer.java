package kemp.roel.guitaristgear.manufacturer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Auteur: Roel Kemp (500781)
 */

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String mainProductType;
    private String placeFounded;
    private int yearFounded;

    /**
     * Default constructor voor Manufacturer.
     */
    public Manufacturer() {}

    /**
     * Constructor voor Manufacturer.
     * @param id Het id van het Manufacturer object.
     * @param name De naam van het Manufacturer object
     * @param mainProductType Het type product dat het meest geproduceert word door de producent die
     *                        gerepresenteerd wordt het Manufacturer object.
     * @param placeFounded De plaatsnaam van de plaats waar de producent die gerepresenteerd wordt door het
     *                     Manufacturer object is opgericht.
     * @param yearFounded Het jaar dat de producent die gerepresenteerd wordt door het Manufacturer object is begonnen.
     */
    public Manufacturer(Long id, String name, String mainProductType, String placeFounded, int yearFounded) {
        this.id = id;
        this.name = name;
        this.mainProductType = mainProductType;
        this.placeFounded = placeFounded;
        this.yearFounded = yearFounded;
    }

    /**
     * Getter voor de id van het Manufacturer object.
     * @return Het id van het Manufacturer object.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter voor de naam die hoort bij het Manufacturer object.
     * @return De naam die hoort bij het Manufacturer object.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter voor de naam van het product dat de producent die gerepresenteerd wordt door het Manufacturer object
     * het meest produceert.
     * @return De naam van het product dat de producent die gerepresenteerd wordt door het Manufacturer object
     * het meest produceert.
     */
    public String getMainProductType() {
        return mainProductType;
    }

    /**
     * Getter voor de plaatsnaam van de plaats waar de producent die gerepresenteerd wordt door het
     * Manufacturer object is opgericht.
     * @return De plaatsnaam van de plaats waar de producent die gerepresenteerd wordt door het
     *         Manufacturer object is opgericht.
     */
    public String getPlaceFounded() {
        return placeFounded;
    }

    /**
     * Getter voor het jaar dat de producent die gerepresenteerd wordt door het Manufacturer object is begonnen.
     * @return Het jaar dat de producent die gerepresenteerd wordt door het Manufacturer object is begonnen.
     */
    public int getYearFounded() {
        return yearFounded;
    }

    /**
     * Setter voor de id van het Manufacturer object.
     * @param id Het nieuwe id van het Manufacturer object.
     */
    public void setId(Long id) {
        this.id = id;
    }
}