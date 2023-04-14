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
    private Label greidsluUpplysingar;//texti með upplýsingum um pöntunina
    @FXML
    private ListView<Veitingar> greidslaListView;//ListView viðmótshlutur, afrit af karfa listanum

    private PontunController pontunController;//okkar tilvik af pontunController


    //smiður fyrir GreidslaController
    public void initialize() {
        pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);//rétt tilvik af PontunController

        geraGreidslutexta();

        pontunController.getKarfa().getObsVeitingar().addListener((ListChangeListener<? super Veitingar>) change -> {
            geraGreidslutexta();
        });

        greidslaListView.setItems(pontunController.getKarfa().getObsVeitingar());
    }


    /**
     * Aðferð sem smíðar texta sem birtist í greiðslusenunni. Sækir heimilisfang og heildarverð í pontunController, og
     * biðtíminn er úr annari aðferð í GreidslaController
     */
    private void geraGreidslutexta() {//heildarverd.intValue()
        greidsluUpplysingar.setText("Heildarverð fyrir pöntunina er " + pontunController.getKarfa().getHeildarverd().get() +
                " kr.\n\nPöntun verður afhent við skráð heimilisfang: \n" + pontunController.getVidskiptavinur().getHeimilisfang().get() +
                "\n\nÁætlaður afhendingartími er eftir " + afhendingartimi());
    }


    /**
     * reiknar út afhendingartíma af handahófi og setur upp sem streng.
     *
     * @return bid, áætlaður biðtími sem strengur
     */
    private String afhendingartimi() {
        int heildarbid = (int) (Math.random() * 1440);
        String bid = "";
        int klst = heildarbid / 60;
        int min = heildarbid - 60 * klst;

        if (klst > 0) bid = klst + " klst";
        if (min != 0) bid += " og " + min + " mín";
        return bid;
    }

    /**
     * Ýtt á hnapp til að ganga frá pöntun. Hreinsar körfu og núllstillir heildarverð, og kallar á aðferð sem skilar
     * alert dialog með staðfestingu á pöntun
     *
     * @param actionEvent ýtt á staðfesta hnapp
     */
    @FXML
    private void fxStadfestingHandler(ActionEvent actionEvent) {
        stadfestingAlertDialog();

        pontunController.getKarfa().getObsVeitingar().clear();
        pontunController.getKarfa().getHeildarverd().set(0);

        ViewSwitcher.switchTo(View.PONTUN);
    }

    /**
     * aðferð sem gerir Alert dialog sem lætur vita að pöntun hafi verið móttekin
     */
    private void stadfestingAlertDialog() {
        String spurning = "";
        ButtonType iLagi = new ButtonType("Í lagi", ButtonBar.ButtonData.OK_DONE);
        ButtonType haettaVid = new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert a = new Alert(Alert.AlertType.NONE, spurning, iLagi, haettaVid);
        a.setTitle("Staðfesting");
        a.setContentText("Pöntunin hefur verið móttekin");
        a.showAndWait();
    }

    /**
     * ýtt á til baka hnapp í greiðslusenunni.
     * Fer til baka í PontunController, karfan er eins og hún var
     *
     * @param actionEvent ýtt á hnapp
     */
    @FXML
    private void fxTilBakaHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.PONTUN);
    }
}
