/**
 * Controller klasi fyrir greiðslusenuna. Hef gert greidsla-view.fxml fyrir viðmót greiðslusenunnar.
 * Með tilviksbreytunni pontunController höfum við aðgang að pöntunarsenunni, og þannig nálgast klasinn heildarverð,
 * hvað er í körfu, og upplýsingar um innskráðan viðskiptavin.
 */

package vidmot.takeaway;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import vinnsla.takeaway.Veitingar;

public class GreidslaController {
    @FXML
    private Label fxGreidsluUpplysingar;//texti með upplýsingum um pöntunina
    @FXML
    private ListView<Veitingar> fxKarfaIPontun;//ListView viðmótshlutur, afrit af karfa listanum

    private PontunController pontunController;//okkar tilvik af pontunController


    //smiður fyrir GreidslaController
    public void initialize() {
        pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);//rétt tilvik af PontunController

        geraGreidslutexta();

        pontunController.getKarfa().veitingarList().addListener((ListChangeListener<? super Veitingar>) change -> {
            geraGreidslutexta();
        });

        fxKarfaIPontun.setItems(pontunController.getKarfa().veitingarList());
    }


    /**
     * Aðferð sem smíðar texta sem birtist í greiðslusenunni. Sækir heimilisfang og heildarverð í pontunController, og
     * biðtíminn er úr annari aðferð í GreidslaController
     */
    private void geraGreidslutexta() {
        fxGreidsluUpplysingar.setText("Heildarverð fyrir pöntunina er " + pontunController.getKarfa().getHeildarverd().get() +
                " kr.\n\nPöntun verður afhent við skráð heimilisfang: \n" + pontunController.getInnskradurVidskiptavinur().getHeimilisfang().get() +
                "\n\nÁætlaður afhendingartími er eftir " + afhendingartimiToString());
    }

    /**
     * Býr til streng sem inniheldur afhendingartíma.
     *
     * @param heildarbid heildarfjöldi mínútna sem afhendingartími á að reikna út frá
     * @return strengur sem inniheldur afhendingartímann í lesanlegri formi
     */
    private String buaTilAfhendingartima(int heildarbid) {
        String afhending = "";
        int klst = heildarbid / 60;
        int min = heildarbid % 60;

        if (klst > 0) {
            afhending = klst + " klst";
        }
        if (min != 0) {
            afhending += " og " + min + " mín";
        }
        return afhending;
    }
    /**
     * Býr til streng sem inniheldur afhendingartíma með því að kalla á aðferðina buaTilAfhendingartima með slembitölu
     * sem er fengin með að draga slembitölu á bilinu [0, 1440). Þessi aðferð skilar strengnum sem inniheldur
     * afhendingartímann.
     *
     * @return strengur sem inniheldur afhendingartímann í lesanlegri formi
     */
    private String afhendingartimiToString() {
        int heildarbid = (int) (Math.random() * 1440);
        return buaTilAfhendingartima(heildarbid);
    }

    /**
     * Ýtt á hnapp til að ganga frá pöntun. Hreinsar körfu og núllstillir heildarverð, og kallar á aðferð sem skilar
     * alert dialog með staðfestingu á pöntun
     *
     * @param actionEvent ýtt á staðfesta hnapp
     */
    @FXML
    private void fxStadfestingHandler(ActionEvent actionEvent) {
        birtaStadfestingAlertDialog();

        pontunController.getKarfa().veitingarList().clear();
        pontunController.getKarfa().getHeildarverd().set(0);

        ViewSwitcher.switchTo(View.PONTUN);
    }

    /**
     * aðferð sem gerir Alert dialog sem lætur vita að pöntun hafi verið móttekin
     */
    private void birtaStadfestingAlertDialog() {
        String spurning = "";
        ButtonType iLagi = new ButtonType("Í lagi", ButtonBar.ButtonData.OK_DONE);
        ButtonType haettaVid = new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.NONE, spurning, iLagi, haettaVid);
        alert.setTitle("Staðfesting");
        alert.setContentText("Pöntunin hefur verið móttekin");
        alert.showAndWait();
    }

    /**
     * ýtt á til baka hnapp í greiðslusenunni.
     * Fer til baka í PontunController, karfan er eins og hún var
     *
     * @param actionEvent ýtt á til baka hnapp
     */
    @FXML
    private void fxTilBakaHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.PONTUN);
    }
}
