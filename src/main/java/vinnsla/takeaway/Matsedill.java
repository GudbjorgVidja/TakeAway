/**
 * Klasinn heldur utan um matseðil, sem er bara listi af Veitingar hlutum. Matsedill klasinn er notaður til að hafa
 * lista af veitingum á matseðlinum (MatsedillView), og í körfunni
 */
package vinnsla.takeaway;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a menu of food items.
 */
public class Matsedill {
    private ObservableList<Veitingar> veitingar; // The list of food items in the menu

    /**
     * Creates a new menu with an empty list of food items.
     * The list is implemented as an ObservableList for ease of use with JavaFX.
     */
    public Matsedill() {
        veitingar = FXCollections.observableArrayList();
    }

    /**
     * Creates a new food item with the given name and price,
     * and adds it to the menu.
     * @param matur The name of the food item
     * @param verd The price of the food item
     */
    public void setjaGogn(String matur, int verd) {
        veitingar.add(new Veitingar(matur, verd));
    }

    /**
     * Adds a food item to the menu.
     * @param v The food item to add to the menu
     */
    public void setjaMatsedil(Veitingar v) {
        veitingar.add(v);
    }

    /**
     * Removes a food item from the menu at the given index.
     * If the index is out of bounds, no item is removed.
     * @param index The index of the item to remove from the menu
     */
    public void takaAfMatsedli(int index) {
        if (!veitingar.isEmpty() && index >= 0 && index < veitingar.size()) {
            veitingar.remove(index);
        }
    }

    /**
     * Returns the list of food items in the menu.
     * @return The list of food items in the menu
     */
    public ObservableList<Veitingar> veitingarList() {
        return veitingar;
    }
}
