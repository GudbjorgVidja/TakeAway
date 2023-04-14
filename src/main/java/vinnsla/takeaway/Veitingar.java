/**
 * Karfa er observable listi af Veitingar, veitingar hlutur er strengur fyrir mat og tala fyrir verð
 */
package vinnsla.takeaway;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Veitingar {
    private StringProperty matur;
    private IntegerProperty verd;

    public Veitingar(String m, int v) {
        matur = new SimpleStringProperty(m);
        verd = new SimpleIntegerProperty(v);
    }

    public IntegerProperty getVerd() {
        return verd;
    }

    //Notar sniðinn streng
    public String toString() {
        return String.format("%-22s %6d", matur.get(), verd.get());
    }

}
