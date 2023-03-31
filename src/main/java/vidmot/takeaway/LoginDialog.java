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
     * Smiður fyrir klasann, allt gert í smiðnum.
     */
    public LoginDialog() {
        TextInputDialog d = new TextInputDialog();
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        String nafn = pontunController.getVidskiptavinur().getNafn().get();

        Node iLagi = d.getDialogPane().lookupButton(ButtonType.OK);
        iLagi.disableProperty().bind(d.getEditor().textProperty().isEmpty());
        d.setTitle("innskráning");
        d.setHeaderText("Viltu skrá þig inn sem " + nafn + "?");
        d.setContentText("Lykilorð: ");
        Optional<String> utkoma = d.showAndWait();

        if (utkoma.isPresent()) pontunController.setInnskradur(true);
    }


    public static void main(String[] args) {

    }
}
