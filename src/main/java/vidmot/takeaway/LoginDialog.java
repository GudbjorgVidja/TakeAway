/**
 * Klasinn LoginDialog gerir TextDialog hlut ef notandi er nú þegar í kerfinu. Notandi þarf að slá inn lykilorð og getur
 * svo ýtt á hnappinn "í lagi" til að senda inn svarið. Lykilorðið er ekki geymt eða staðfest, en ekki hægt að ýta
 * í hnappinn nema eitthvað inntak sé í textField.
 */
package vidmot.takeaway;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class LoginDialog {
    /**
     * Smiður fyrir klasann
     */
    public LoginDialog() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        String nafn = pontunController.getInnskradurVidskiptavinur().getNafn().get();

        TextInputDialog loginDialog = geraDialog(nafn);

        Optional<String> utkoma = loginDialog.showAndWait();

        if (utkoma.isPresent()) pontunController.setInnskradur(true);
    }

    /**
     * Aðferðin býr til dialog sem notaður er í smiðnum.
     *
     * @param nafn Strengur, nafn viðskiptavinar
     * @return TextInputDialog með upplýsingum
     */
    private TextInputDialog geraDialog(String nafn) {
        TextInputDialog loginDialog = new TextInputDialog();

        Node iLagi = loginDialog.getDialogPane().lookupButton(ButtonType.OK);
        iLagi.disableProperty().bind(loginDialog.getEditor().textProperty().isEmpty());
        loginDialog.setTitle("innskráning");
        loginDialog.setHeaderText("Viltu skrá þig inn sem " + nafn + "?");
        loginDialog.setContentText("Lykilorð: ");

        return loginDialog;
    }

}
