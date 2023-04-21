/**
 * MatsedillView er sérhæfður klasi. MatsedillView hlutur hefur útlit sem ræst af matsedill-view.fxml skránni.
 * Þetta er í raun controller fyrir matsedill-view.fxml. MatsedillView erfir frá BorderPane, og inniheldur Label og
 * ListView hlut.
 */
package vidmot.takeaway;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import vinnsla.takeaway.Matsedill;
import vinnsla.takeaway.Veitingar;

import java.io.IOException;

public class MatsedillView extends BorderPane {

    private final Matsedill matsedill; //vinnsluhluturinn

    @FXML
    private ListView<Veitingar> fxMatsedill;//viðmótshluturinn fyrir matseðilinn


    //Smiður sem les inn fxml skrá, og setur svo rót og controller sem this
    public MatsedillView() {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("matsedill-view.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);

        try {
            fxmlloader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        matsedill = new Matsedill();
        lesaInnVeitingar();

        fxMatsedill.setItems(matsedill.veitingarList());
    }

    //skilar ListView hlut fyrir matseðilinn
    public ListView<Veitingar> getFxMatsedill() {
        return fxMatsedill;
    }

    /**
     * Vesen með að lesa inn ur skrá og nennti ekki að skrifa heildalínnu tilað gera hvern rétt.
     * Geri langan streng, réttur og verð til skiptis, aðskilin með kommu. Splitta upp í fylki, og nota lykkju
     * TODO lesa veitingarnar inn úr skrá? Líklega best að nota In
     */
    private void lesaInnVeitingar() {
        String runa = "Hamborgari, 2590, Kjötbollur, 3005, Kaffi, 200, Vatn, 990, Ávaxtasalat, 4490, Steik, 800, Kartöflusalat, 700, Gos, 500, Safi, 3000";
        String[] stokStok = runa.split(", ");
        for (int i = 0; i < stokStok.length; i += 2) {//i alltaf slétt tala
            matsedill.baetaVidMatsedil(new Veitingar(stokStok[i], Integer.parseInt(stokStok[i + 1])));
            //matsedill.setjaGogn(stokStok[i], Integer.parseInt(stokStok[i + 1]));
        }
    }

}
