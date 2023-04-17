/**
 * Karfa er observable listi af Veitingar, veitingar hlutur er strengur fyrir mat og tala fyrir verð
 */
package vinnsla.takeaway;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a food item with a name and price.
 */
public class Veitingar {
    private StringProperty matur; // The name of the food item
    private IntegerProperty verd; // The price of the food item

    /**
     * Creates a new food item with the given name and price.
     *
     * @param matur The name of the food item
     * @param verd  The price of the food item
     */
    public Veitingar(String matur, int verd) {
        this.matur = new SimpleStringProperty(matur);
        this.verd = new SimpleIntegerProperty(verd);
    }

    /**
     * Returns the price of the food item.
     *
     * @return The price of the food item
     */
    public IntegerProperty getVerd() {
        return verd;
    }

    /**
     * Returns a string representation of the food item in the format
     * "name        price", where "name" is left-aligned in a 22-character
     * field and "price" is right-aligned in a 6-character field.
     *
     * @return A string representation of the food item
     */
    @Override
    public String toString() {
        return String.format("%-22s %6d", matur.get(), verd.get());
    }
}
