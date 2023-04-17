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
    private TextField fxNafnField;//viðmótshlutur, textfield þar sem notandi slær inn nafn
    @FXML
    private TextField fxHeimilisfangField;//textfield fyrir heimilisfang
    @FXML
    private ButtonType fxILagi;//Buttontype hlutur, í lagi takkinn

    private Vidskiptavinur vidskiptavinur;//tilviksbreyta, skráningu nýs viðskiptavinar verður bætt inn í vidskiptavinur

    /**
     * Smiðurinn tekur inn Vidskiptavinur hlut sem er null, og setur í tilviksbreytuna vidskiptavinur.
     */
    public VidskiptavinurDialog() {
        vidskiptavinur = new Vidskiptavinur(null, null);

        setDialogPane(lesaVidskiptavinurDialog());
        getDialogPane().setHeaderText("Stofnaðu aðgang til að halda áfram\nVinsamlegast sláðu inn nafn og heimilisfang");

        setTextfieldBindingDialog();

        resultConverter();

        iLagiTakkiDisabledRegla();
    }


    /**
     * Gerir bindingu á milli textfield í dialognum og vidskiptavinur tilviksbreytunnar(nafn og heimilisfang. Ef annað breytist
     * þá gerir hitt það líka. Gengur í báðar áttir.
     */
    private void setTextfieldBindingDialog() {
        fxNafnField.textProperty().bindBidirectional(vidskiptavinur.getNafn());
        fxHeimilisfangField.textProperty().bindBidirectional(vidskiptavinur.getHeimilisfang());
    }


    /**
     * regla gerð fyrir það hvenær í lagi takkinn er óvirkur. Ef textfield fyrir nafn eða heimilisfang er tómt, þá er
     * takkinn óvirkur.
     */
    private void iLagiTakkiDisabledRegla() {
        Node iLagiTakki = getDialogPane().lookupButton(fxILagi);
        iLagiTakki.disableProperty().bind(fxNafnField.textProperty().isEmpty().or(fxHeimilisfangField.textProperty().isEmpty()));
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
                return vidskiptavinur;
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

}
