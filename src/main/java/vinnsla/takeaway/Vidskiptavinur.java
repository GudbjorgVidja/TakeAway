/**
 * Smíðar hlut af klasanum Vidskiptavinur. Smiðurinn tekur inn tvo strengi, upplýsingar geymdar í StringProperty.
 * Vidskiptavinur inniheldur nafn og heimilisfang.
 * Það hvort viðskiptavinur hafi verið gerður ræður hvernig innskráning er (innskráning eða nýskráning).
 * Notum bæði nafn og heimilisfang, m.a. í GreidslaController.
 */

package vinnsla.takeaway;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a customer with a name and address.
 */
public class Vidskiptavinur {
    private StringProperty nafn; // The customer's name
    private StringProperty heimilisfang;// The customer's address

    /**
     * Creates a new customer with the given name and address.
     * @param n The customer's name
     * @param h The customer's address
     */
    public Vidskiptavinur(String n, String h) {
        nafn = new SimpleStringProperty(n);
        heimilisfang = new SimpleStringProperty(h);
    }

    /**
     * Returns the customer's name.
     * @return The customer's name
     */
    public StringProperty getNafn() {
        return nafn;
    }

    /**
     * Returns the customer's address.
     * @return The customer's address
     */
    public StringProperty getHeimilisfang() {
        return heimilisfang;
    }
}
