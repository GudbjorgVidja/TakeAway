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
 * Klasi fyrir upplýsingar viðskiptavinar.
 */
public class Vidskiptavinur {
    private final StringProperty nafn; // The customer's name
    private final StringProperty heimilisfang;// The customer's address

    /**
     * Býr til nýjan viðskiptaivn með nafni og hemilisfangi.
     *
     * @param nafn         Nafn viðskiptavinar
     * @param heimilisfang Heimilisfang viðskiptavinar
     */
    public Vidskiptavinur(String nafn, String heimilisfang) {
        this.nafn = new SimpleStringProperty(nafn);
        this.heimilisfang = new SimpleStringProperty(heimilisfang);
    }

    /**
     * Skilar nafn viðskiptavinar
     *
     * @return Nafn viðskiptavinar
     */
    public StringProperty getNafn() {
        return nafn;
    }

    /**
     * Skilar heimilisfang viðskiptavinar
     *
     * @return Heimilisfang viðskiptavinar
     */
    public StringProperty getHeimilisfang() {
        return heimilisfang;
    }
}
