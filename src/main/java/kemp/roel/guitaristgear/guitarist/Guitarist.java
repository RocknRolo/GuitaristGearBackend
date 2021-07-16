package kemp.roel.guitaristgear.guitarist;

import kemp.roel.guitaristgear.gear.Gear;

import javax.persistence.*;
import java.util.List;

/**
 * Auteur: Roel Kemp (500781)
 */

@Entity
public class Guitarist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String birthPlace;
    private int birthYear;
    private String genre;

    @ManyToMany
    private List<Gear> gearList;

    /**
     * Default constructor voor een Guitarist object.
     */
    public Guitarist() {}

    /**
     * Constructor voor een Guitarist object.
     * @param id Het id van Guitarist object.
     * @param name De naam van de gitarist die het Guitarist object representeert.
     * @param birthPlace De geboorteplaats van de gitarist die het Guitarist object representeert.
     * @param birthYear Het geboortejaar van de gitarist die het Guitarist object representeert.
     * @param genre Het genre muziek dat de gitarist die het Guitarist object representeert, speelt.
     */
    public Guitarist(Long id, String name, String birthPlace, int birthYear, String genre) {
        this.id = id;
        this.name = name;
        this.birthPlace = birthPlace;
        this.birthYear = birthYear;
        this.genre = genre;
    }

    /**
     * Getter voor de id van het Guitarist object.
     * @return De id van het Guitarist object.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter van de id van het Guitarist object.
     * @param id Het nieuwe id van het Guitarist object.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter voor de naam van het Guitarist object.
     * @return De naam van het Guitarist object.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter voor de geboorteplaats die hoort bij het Guitarist object.
     * @return De naam van de geboorteplaats die bij het Guitarist object hoort.
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * Getter voor het geboortejaar dat hoor bij het Guitarist object.
     * @return Het geboortejaar dat bij het Guitarist object hoort.
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Getter voor het genre dat bij het Guitarist object hoort.
     * @return Het genre dat bij het Guitarist object hoort.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Getter voor de lijst met Gear objecten die bij het Guitarist object hoort.
     * @return De lijst met Gear objecten die bij het Guitarist object hoort.
     */
    public List<Gear> getGearList() {
        return gearList;
    }

    /**
     * Methode om een Gear object toe te voegen aan de "gear list" van dit Guitarist object.
     * @param gear Het Gear object dat aan de lijst toegevoegd moet worden.
     */
    public void addGearToGearList(Gear gear) {
        gearList.add(gear);
    }

    /**
     * Een methode om een Gear object te vinden op de "gear list" van dit Guiarist object.
     * @param id De id van het te vinden Gear object.
     * @return Als het Gear object met de meegegeven id gevonden is wordt het opgevraagde Gear object teruggeven.
     *         Anders wordt er null teruggegeven.
     */
    public Gear findGearOnGearList(Long id) {
        for (Gear gear : gearList) {
            if (gear.getId().equals(id)) {
                return gear;
            }
        }
        return null;
    }

    /**
     * Een methode om een Gear object van de "gear list" van dit Guitarist object te verwijderen.
     * @param id Het id van het te verwijderen Gear object.
     * @return "true" als het verwijderen van de lijst gelukt is. Anders "false".
     */
    public boolean removeGearFromGearList(Long id) {
        if (findGearOnGearList(id) != null) {
            gearList.remove(findGearOnGearList(id));
            return true;
        }
        return false;
    }
}