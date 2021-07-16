package kemp.roel.guitaristgear.gear;

import kemp.roel.guitaristgear.manufacturer.Manufacturer;

import javax.persistence.*;

/**
 * Auteur: Roel Kemp (500781)
 */

@Entity
public class Gear {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String type;
    private int weightInGrams;

    @ManyToOne
    private Manufacturer brand;

    /**
     * Default constructor voor een Gear object.
     */
    public Gear() {}

    /**
     * Constructor voor een Gear object.
     * @param id Het unieke id van het object.
     * @param name De naam van het Gear object.
     * @param type Het type van het Gear object. (bijv. Guitar of Amplifier)
     * @param weightInGrams Het gewicht in grammen.
     */
    public Gear(Long id, String name, String type, int weightInGrams) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weightInGrams = weightInGrams;
    }

    /**
     * De getter voor de id.
     * @return Het id van het Gear object.
     */
    public Long getId() {
        return id;
    }

    /**
     * De getter voor het Manufacturer object dat met dit Gear object geassocieerd is.
     * @return Het Manufacturer object dat met dit Gear object geassocieerd is.
     */
    public Manufacturer getBrand() {
        return brand;
    }

    /**
     * De getter voor de naam van dit Gear object.
     * @return De naam van dit Gear object.
     */
    public String getName() {
        return name;
    }

    /**
     * De getter voor het type van dit Gear object.
     * @return Het type van dit gear object.
     */
    public String getType() {
        return type;
    }

    /**
     * De getter voor het gewicht in grammen van dit Gear object.
     * @return Het gewicht in grammen van dit Gear object.
     */
    public int getWeightInGrams() {
        return weightInGrams;
    }

    /**
     * De setter voor het id van dit Gear object.
     * @param id Het nieuwe id van dit Gear object.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * De setter voor het Manufacturer object dat geassocieerd is met dit Gear object.
     * @param brand Het Manufactuer object dat geassocieerd moet worden met dit Gear object.
     */
    public void setBrand(Manufacturer brand) {
        this.brand = brand;
    }
}