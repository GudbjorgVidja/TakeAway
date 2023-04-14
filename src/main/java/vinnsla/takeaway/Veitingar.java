/**
 * Karfa er observable listi af Veitingar, veitingar hlutur er strengur fyrir mat og tala fyrir verð
 */
package vinnsla.takeaway;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Veitingar {
    private StringProperty matur = new SimpleStringProperty();
    private IntegerProperty verd = new SimpleIntegerProperty();

    public Veitingar(String m, int v) {
        matur.setValue(m);
        verd.setValue(v);
    }

    public IntegerProperty getVerd() {
        return verd;
    }


    //gera tab misoft eftir lengd matur strengs
    public String toString() {
        return matur.getValue() + fjoldiTab() + verd.intValue();
        //return matur.getValue() + ": \t " + verd.intValue();
    }

    //hve oft kemur tab fyrir tiltekið orð
    //heildarlengd(stafapláss)-pláss orðsins,/8 +1 ef %8!=0
    private String fjoldiTab() {
        int heildarfj = 32;
        String tab = "";
        String s = matur.get();
        int afgangur = heildarfj - s.length();
        int n;

        n = afgangur / 8;
        if (afgangur % 8 != 0) {
            n += 1;
        }
        n -= 1;

        for (int i = 0; i < n; i++) {
            tab += "\t";
        }

        return tab;
    }
    
}
