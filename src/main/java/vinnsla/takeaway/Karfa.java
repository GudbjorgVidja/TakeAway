/**
 * Karfa klasinn er í raun viðbót við Matsedill klasann. Inniheldur ekkert aukalega nema heildarverð.
 * Þegar ný karfa er gerð þá inniheldur hún Matsedill hlut, og heldur svo utan um heildarverð allra hluta í körfunni.
 */
package vinnsla.takeaway;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
/**
 * Represents the cart of food items.
 */
public class Karfa extends Matsedill {
    private IntegerProperty heildarverd;//total price for all items in the cart

    /**
     * When a new cart is created, a new Matsedill object is also created, which contains an empty ObservableList of Veitingar.
     */
    public Karfa() {
        heildarverd = new SimpleIntegerProperty(0);
        heildarverdListenerRegla();
    }

    /**
     * Adds a listener to the ObservableList object for Karfa (the list is stored in the Matsedill class).
     * Finds out what was added or removed, and uses that information to update the total price.
     */
    private void heildarverdListenerRegla() {
        veitingarList().addListener((ListChangeListener<Veitingar>) change -> {
            change.next();
            if (change.wasAdded()) {
                int heild = 0;
                for (Veitingar v : change.getAddedSubList()) {
                    heild += v.getVerd().get();
                }
                heildarverd.set(heildarverd.get() + heild);
            } else if (change.wasRemoved()) {
                int heild = 0;
                for (Veitingar v : change.getRemoved()) {
                    heild += v.getVerd().get();
                }
                heildarverd.set(heildarverd.get() - heild);
            }
        });
    }

    //The main addition of Karfa to Matsedill
    public IntegerProperty getHeildarverd() {
        return heildarverd;
    }
}
