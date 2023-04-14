/**
 * Karfa klasinn er í raun viðbót við Matsedill klasann. Inniheldur ekkert aukalega nema heildarverð.
 * Þegar ný karfa er gerð þá inniheldur hún Matsedill hlut, og heldur svo utan um heildarverð allra hluta í körfunni.
 */
package vinnsla.takeaway;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;

public class Karfa extends Matsedill {
    private IntegerProperty heildarverd;//heildarverð á að uppfærast sjálfkrafa

    /**
     * Þegar ný karfa er gerð, þá er gerður nýr matseðill, sem inniheldur tóman observableList af veitingum.
     */
    public Karfa() {
        heildarverd = new SimpleIntegerProperty(0);
        heildarverdListenerRegla();
    }

    /**
     * settur listener á observableList hlutinn fyrir Karfa (listinn geymdur í Matsedill klasanum).
     * Finnur hvað bættist við eða var fjarlægt, og notar þær upplýsingar til að uppfæra heildarverð
     */
    private void heildarverdListenerRegla() {
        getObsVeitingar().addListener((ListChangeListener<Veitingar>) change -> {//matsedillKarfa.getobs
            change.next();
            if (change.wasAdded()) {
                heildarverd.setValue(heildarverd.getValue() + change.getAddedSubList().get(0).getVerd().getValue());
            } else if (change.wasRemoved()) {
                heildarverd.setValue(heildarverd.getValue() - change.getRemoved().get(0).getVerd().getValue());
            }
        });
    }


    //aðalviðbót Karfa við Matsedill
    public IntegerProperty getHeildarverd() {
        return heildarverd;
    }


    /**
     * main fallið var notað til að gera prófanir
     */
    public static void main(String[] args) {
        System.out.println("Geri matseðil og set gögn: ");
        Matsedill matsedillinn = new Matsedill();
        matsedillinn.setjaGogn("safi", 400);
        matsedillinn.setjaGogn("steik", 6000);
        matsedillinn.setjaGogn("banani", 20);

        System.out.println("\nFjöldi hluta á matseðli: " + matsedillinn.getObsVeitingar().size());

        for (int i = 0; i < matsedillinn.getObsVeitingar().size(); i++) {
            System.out.println(matsedillinn.getObsVeitingar().get(i));
        }


    }
}
