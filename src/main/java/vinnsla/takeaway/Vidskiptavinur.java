/**
 * Smíðar hlut af klasanum Vidskiptavinur. Smiðurinn tekur inn tvo strengi, upplýsingar geymdar í StringProperty.
 * Vidskiptavinur inniheldur nafn og heimilisfang.
 * Það hvort viðskiptavinur hafi verið gerður ræður hvernig innskráning er (innskráning eða nýskráning).
 * Notum bæði nafn og heimilisfang, m.a. í GreidslaController.
 */

package vinnsla.takeaway;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vidskiptavinur {
    private StringProperty nafn; //nafn viðskiptavinar, StringProperty
    private StringProperty heimilisfang;// heimilisfang viðskiptavinar, StringProperty

    //smiður fyrir Vidskiptavinur hlut, strengir fyrir nafn og heimilisfang
    public Vidskiptavinur(String n, String h) {
        nafn = new SimpleStringProperty(n);
        heimilisfang = new SimpleStringProperty(h);
    }

    public StringProperty getNafn() {
        return nafn;
    }

    public StringProperty getHeimilisfang() {
        return heimilisfang;
    }
    
}
