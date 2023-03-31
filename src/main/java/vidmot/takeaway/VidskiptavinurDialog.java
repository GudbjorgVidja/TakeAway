/**
 * Klasinn fyrir dialog fyrir nýskráningu. Ef enginn notandi er í kerfinu er þessi klasi notaður til að gera dialog.
 * Hann tekur við inntaki frá notanda, nafni og heimilisfangi, og skilar Vidskiptavinur hlut.
 */

package vidmot.takeaway;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import vinnsla.takeaway.Vidskiptavinur;

import java.io.IOException;

public class VidskiptavinurDialog extends Dialog<Vidskiptavinur> {
    @FXML
    private TextField nafnField;//viðmótshlutur, textfield þar sem notandi slær inn nafn
    @FXML
    private TextField heimilisfangField;//textfield fyrir heimilisfang
    @FXML
    private ButtonType fxILagi;//Buttontype hlutur, í lagi takkinn

    private Vidskiptavinur vinur;//tilviksbreyta, skráningu nýs viðskiptavinar verður bætt inn í vinur

    /**
     * Smiðurinn tekur inn Vidskiptavinur hlut sem er null, og setur í tilviksbreytuna vinur.
     */
    public VidskiptavinurDialog(Vidskiptavinur vidskiptavinur) {
        vinur = vidskiptavinur;

        setDialogPane(lesaVidskiptavinurDialog());
        getDialogPane().setHeaderText("Stofnaðu aðgang til að halda áfram\nVinsamlegast sláðu inn nafn og heimilisfang");

        setBindingDialog();

        resultConverter();

        iLagiRegla();
    }


    /**
     * Gerir bindingu á milli textfield í dialognum og vinur tilviksbreytunnar(nafn og heimilisfang. Ef annað breytist
     * þá gerir hitt það líka. Gengur í báðar áttir.
     */
    private void setBindingDialog() {
        nafnField.textProperty().bindBidirectional(vinur.getNafn());
        heimilisfangField.textProperty().bindBidirectional(vinur.getHeimilisfang());
    }


    /**
     * regla gerð fyrir það hvenær í lagi takkinn er óvirkur. Ef textfield fyrir nafn eða heimilisfang er tómt, þá er
     * takkinn óvirkur.
     */
    private void iLagiRegla() {
        Node iLagi = getDialogPane().lookupButton(fxILagi);
        iLagi.disableProperty().bind(nafnField.textProperty().isEmpty().or(heimilisfangField.textProperty().isEmpty()));
    }


    /**
     * result converter settur fyrir dialoginn. Hér er niðurstaða móttekin og umbreytt í gildi sem skilað er í
     * PontunController
     * b er ButtonType, ButtonData fyrir í lagi takkann er OK_DONE, else er þegar ýtt er á annað en í lagi og þannig
     * hætt við skáningu.
     */
    private void resultConverter() {
        setResultConverter(b -> {
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return vinur;
            } else {
                return null;
            }
        });
    }

    /**
     * Gerir og skilar DialogPane hlut, og í smiðnum er þessi hlutur tekinn, settur sem DialogPane hlutur dialogsins,
     * og birtur.
     *
     * @return DialogPane hlutur eftir fxml skránni
     */
    private DialogPane lesaVidskiptavinurDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(View.VIDSKIPTAVINUR.getFileName()));

        try {
            fxmlLoader.setController(this);
            //fxmlLoader.setRoot(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }


    public static void main(String[] args) {

    }
}
