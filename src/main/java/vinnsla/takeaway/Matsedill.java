/**
 * Klasinn heldur utan um matseðil, sem er bara listi af Veitingar hlutum. Matsedill klasinn er notaður til að hafa
 * lista af veitingum á matseðlinum (MatsedillView), og í körfunni
 */
package vinnsla.takeaway;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Matsedill {
    private ObservableList<Veitingar> veitingar = FXCollections.observableArrayList(); //initializa í smið?
    //private Veitingar rettur;


    /**
     * Smiður fyrir Matsedill klasann. Þegar nýr hlutur af klasanum Matsedill er gerður, þá er líka gerður veitingar
     * hlutur, sem er tilviksbreytan fyrir ObservableList
     */
    public Matsedill() {
        veitingar = FXCollections.observableArrayList();//gera hér?
    }


    /**
     * Gerir nýjan Veitingar hlut sem er matur og kostar verd, og bætir þessum Veitingar hlut á veitingar, sem
     * er ObservableList hlutur í Matsedill klasanum
     *
     * @param matur Strengur, rétturinn
     * @param verd  heiltala, verðið
     */
    public void setjaGogn(String matur, int verd) {
        veitingar.add(new Veitingar(matur, verd));
    }


    /**
     * Bætir Veitingar hlut í körfuna
     *
     * @param v Veitingar hlutur sem valinn var af matseðli
     */
    public void setjaMatsedil(Veitingar v) {
        veitingar.add(v);
    }

    /**
     * aðferð til að fjarlægja stak af matseðli með því að breyta observableList. Tekur réttinn úr körfu
     *
     * @param index sæti á lista
     */
    public void takaAfMatsedli(int index) {
        if (!veitingar.isEmpty()) veitingar.remove(index);
    }

    //skilar observable listanum fyrir tiltekinn Matseðill hlut
    public ObservableList<Veitingar> getObsVeitingar() {
        return veitingar;
    }
    
}
