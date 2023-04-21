/**
 * Karfa er observable listi af Veitingar, veitingar hlutur er strengur fyrir mat og tala fyrir verð
 */
package vinnsla.takeaway;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasi fyrir veitingarnar - nafn réttar og verð þeirra.
 */
public class Veitingar {
    private final StringProperty matur; // The name of the food item
    private final IntegerProperty verd; // The price of the food item

    /**
     * Býr til nýjan matur hlut með nafni og verði.
     *
     * @param matur Nafn réttisins
     * @param verd  Verð réttisins
     */
    public Veitingar(String matur, int verd) {
        this.matur = new SimpleStringProperty(matur);
        this.verd = new SimpleIntegerProperty(verd);
    }

    /**
     * Skilar verð réttisins
     *
     * @return Verð réttisins
     */
    public IntegerProperty getVerd() {
        return verd;
    }

    /**
     * Skilar strengi sem táknar réttinn á þennan hátt:  "nafn        price".
     *
     * @return Streng af réttinum.
     */
    @Override
    public String toString() {
        return String.format("%-22s %6d", matur.get(), verd.get());
    }
}
