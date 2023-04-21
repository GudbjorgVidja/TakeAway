/**
 * Karfa klasinn er í raun viðbót við Matsedill klasann. Inniheldur ekkert aukalega nema heildarverð.
 * Þegar ný karfa er gerð þá inniheldur hún Matsedill hlut, og heldur svo utan um heildarverð allra hluta í körfunni.
 */
package vinnsla.takeaway;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
/**
 * Klasi fyrir upplýsingar um körfuna.
 */
public class Karfa extends Matsedill {
    private final IntegerProperty heildarverd;// Heildarverð allra veitinga í körfunni

    /**
     * Þegar ný karfa er búin til, er líka Matsedill hlutur búinn til, sem hefur tóman ObservableList af Veitingar.
     */
    public Karfa() {
        heildarverd = new SimpleIntegerProperty(0);
        heildarverdListenerRegla();
    }

    /**
     * Bætir við listener í ObservableList hlutinn fyrir Karfa (listinn er geymdur í Matsedill).
     * Finnur hverju var bætt við og hvað var fjarlægt, og notar þær upplýsingar til að uppfæra heildarverðið.
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

    //Aðal viðbót Karfa í Matsedill
    public IntegerProperty getHeildarverd() {
        return heildarverd;
    }
}
