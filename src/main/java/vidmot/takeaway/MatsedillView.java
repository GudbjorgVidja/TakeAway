/**
 * MatsedillView er sérhæfður klasi. MatsedillView hlutur hefur útlit sem ræst af matsedill-view.fxml skránni.
 * Þetta er í raun controller fyrir matsedill-view.fxml. MatsedillView erfir frá BorderPane, og inniheldur Label og
 * ListView hlut.
 */
package vidmot.takeaway;

import edu.princeton.cs.algs4.In;
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
        lesaFXML();
        matsedill = new Matsedill();
        lesaInnVeitingar();

        fxMatsedill.setItems(matsedill.veitingarList());
    }

    /**
     * aðferð til að lesa inn viðmót úr fxml fyrir sérhæfðan klasa
     */
    private void lesaFXML() {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("matsedill-view.fxml"));
        fxmlloader.setRoot(this);
        fxmlloader.setController(this);

        try {
            fxmlloader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    //skilar ListView hlut fyrir matseðilinn
    public ListView<Veitingar> getFxMatsedill() {
        return fxMatsedill;
    }

    /**
     * Les inn eina línu í einu úr skránni, en hver lína í veitingar.txt er String, int.
     * Gerir Veitingar hlut úr línunni, og bætir við matseðilinn í viðmótinu þar til engin lína er eftir.
     */
    private void lesaInnVeitingar() {
        In in = new In("src/main/java/vinnsla/takeaway/veitingar.txt");
        while (in.hasNextChar()) {
            String[] lina = in.readLine().split(", ");
            matsedill.baetaVidMatsedil(new Veitingar(lina[0], Integer.parseInt(lina[1])));
        }
    }

}
