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
    private ObservableList<Veitingar> veitingarListi; // The list of food items in the menu

    /**
     * Býr til nýjann matseðil með tóman lista af réttum.
     * Listinn er á formi ObservableList til að auðvelda notkun með JavaFX.
     */
    public Matsedill() {
        veitingarListi = FXCollections.observableArrayList();
    }

    /**
     * Býr til nýjann matar hlut (rétt) með nafni og verði, og bætir honum við matseðilinn.
     *
     * @param matur Nafn réttisins
     * @param verd  Verð réttisins
     */
    public void setjaGogn(String matur, int verd) {
        veitingarListi.add(new Veitingar(matur, verd));
    }

    /**
     * Bætir við rétt á matseðil.
     *
     * @param veitingar Réttur sem á að bæta við.
     */
    public void baetaVidMatsedil(Veitingar veitingar) {
        veitingarListi.add(veitingar);
    }

    //todo hafa annað hvort aðferðina setjaGogn eða baetaVidMatsedil, ekki bæði. Gera það sama með smá mismunandi parametra


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
