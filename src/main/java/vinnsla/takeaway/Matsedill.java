/**
 * Klasinn heldur utan um matseðil, sem er bara listi af Veitingar hlutum. Matsedill klasinn er notaður til að hafa
 * lista af veitingum á matseðlinum (MatsedillView), og í körfunni
 */
package vinnsla.takeaway;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Klasi fyrir upplýsingar um matseðilinn.
 */
public class Matsedill {
    private final ObservableList<Veitingar> veitingarListi; // Listi veitinga í matseðlinum

    /**
     * Býr til nýjann matseðil með tóman lista af réttum.
     * Listinn er á formi ObservableList til að auðvelda notkun með JavaFX.
     */
    public Matsedill() {
        veitingarListi = FXCollections.observableArrayList();
    }

    /**
     * Bætir við rétt á matseðil.
     *
     * @param veitingar Réttur sem á að bæta við.
     */
    public void baetaVidMatsedil(Veitingar veitingar) {
        veitingarListi.add(veitingar);
    }

    /**
     * Fjarlægir réttinn af matseðli á gefnu index.
     * Ef index er "out of bounds" þá er ekkert fjarlægt.
     *
     * @param index Index réttisins sem á að fjarlæga.
     */
    public void takaAfMatsedli(int index) {
        if (!veitingarListi.isEmpty() && index >= 0 && index < veitingarListi.size()) {
            veitingarListi.remove(index);
        }
    }

    /**
     * Skilar lista af réttunum á matseðlinum.
     *
     * @return Listi rétta á matseðli.
     */
    public ObservableList<Veitingar> veitingarList() {
        return veitingarListi;
    }
}
