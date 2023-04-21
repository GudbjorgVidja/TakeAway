/**
 * Klasinn er í raun aðal Controller klasi verkefnisins. Tekur allar upplýsingar sem notandi setur inn og beinir öllum
 * upplýsingum í viðeigandi klasa.
 */
package vidmot.takeaway;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import vinnsla.takeaway.Karfa;
import vinnsla.takeaway.Veitingar;
import vinnsla.takeaway.Vidskiptavinur;

import java.util.Optional;

public class PontunController {
    private Veitingar valinnRetturAfMatsedli;//Veitingar hlutur sem síðast var ýtt á af matseðli (til að bæta í körfu)

    private IntegerProperty indexRetturUrKorfu;// = new SimpleIntegerProperty();//index hlutar sem smellt er á í körfu (til að fjarlægja)
    @FXML
    private Label fxHeildarverd;//heildarverð label
    @FXML
    private ListView<Veitingar> fxKarfa; //ListView viðmótshlutur fyrir innihald körfunnar
    private Karfa karfa;//Karfa hlutur, inniheldur matsedill hlut
    private Vidskiptavinur innskradurVidskiptavinur; //viðskiptavinurinn í kerfinu, null eða með gildi

    @FXML
    private MatsedillView fxMatsedill;//MatsedillView hluturinn, viðmótshlutur sem inniheldur ListView

    @FXML
    private ImageView fxNotandamynd;//mynd fyrir innskráðan notanda
    @FXML
    private Label fxNotandanafn;//nafn notanda

    @FXML
    private Button fxInnskraningHnappur; //hnappur fyrir innskráningu

    @FXML
    private Button fxGreidaHnappur; //greiðsluhnappur, stendur ganga frá pöntun á honum

    //smiður fyrir pontunController
    public void initialize() {
        upphafsstillaTilviksbreytur();

        heildarverdBinder();

        Bindings.bindContent(fxKarfa.getItems(), karfa.veitingarList());

        validAfMatsedliListener();
    }

    /**
     * Tilviksbreytur upphafsstilltar í upphafi keyrslu
     */
    private void upphafsstillaTilviksbreytur() {
        karfa = new Karfa();
        innskradurVidskiptavinur = null;
        indexRetturUrKorfu = new SimpleIntegerProperty();
    }


    /**
     * þegar ýtt er á hlut í körfu þá er hann sendur hingað og tekinn úr körfunni. Nær í index hlutar
     */
    @FXML
    private void fxTakaUrKorfuHandler(MouseEvent event) {

        int index = fxKarfa.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            indexRetturUrKorfu.set(index);
            karfa.takaAfMatsedli(indexRetturUrKorfu.intValue());
        }

    }


    /**
     * Setur í körfu það sem smellt er á af matseðli, leitar að selected hlut
     */
    private void validAfMatsedliListener() {
        fxMatsedill.getFxMatsedill().getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
            valinnRetturAfMatsedli = fxMatsedill.getFxMatsedill().getSelectionModel().getSelectedItem();
            fxSetjaIKorfuHandler();
        });
    }


    //bindur saman heildarverð í viðmóti og vinnslu
    private void heildarverdBinder() {
        fxHeildarverd.textProperty().bind(karfa.getHeildarverd().asString());
    }


    /**
     * Þegar ýtt er á hlut á matseðli er hann sendur hingað og settur í körfu
     */
    private void fxSetjaIKorfuHandler() {
        //todo endurnefna, held að samkvæmt skilgreiningu sé þetta ekki handler. kallað á einu sinni, úr listener
        karfa.baetaVidMatsedil(valinnRetturAfMatsedli);
    }

    /**
     * Ef enginn viðskiptavinur í kerfinu þá notum við VidskiptavinurDialog, til að gera nýjan viðskiptavin.
     * Ef viðskiptavinur er nú þegar í kerfinu þá þarf hann bara að slá inn lykilorð, fer í LoginDialog.
     * ef enginn viðskiptavinur: texti á takka er "nýskráning", annars innskráning
     *
     * @param actionEvent ýtt á innskráning hnapp
     */
    @FXML
    private void fxInnskraningHandler(ActionEvent actionEvent) {
        if (innskradurVidskiptavinur == null) {
            Optional<Vidskiptavinur> result = (new VidskiptavinurDialog()).showAndWait();

            if (result.isPresent()) {
                innskradurVidskiptavinur = result.get();
                setjaInnskradUtlit();
            }

        } else {
            geraLoginDialog(); //virkar líka að hafa bara stutta aðferð
        }

    }

    /**
     * Aðferðin gerir dialog fyrir viðskiptavin til að logga sig inn. Þetta var áður í klasanum LoginDialog.java
     */
    private void geraLoginDialog() {
        TextInputDialog loginDialog = new TextInputDialog();
        (loginDialog.getDialogPane().lookupButton(ButtonType.OK)).disableProperty().bind(loginDialog.getEditor().textProperty().isEmpty());

        loginDialog.setTitle("innskráning");
        loginDialog.setHeaderText("Viltu skrá þig inn sem " + innskradurVidskiptavinur.getNafn().get() + "?");
        loginDialog.setContentText("Lykilorð: ");

        Optional<String> utkoma = loginDialog.showAndWait();
        if (utkoma.isPresent()) setInnskradur(true);
    }

    //það er innskráður notandi
    public void setInnskradur(boolean b) {
        fxGreidaHnappur.setDisable(false);
        //TODO breyta þessu, kannski hafa ekkert viðfang?
        //allavega laga þetta
    }


    /**
     * breytir útliti þegar viðskiptavinur er innskráður
     */
    private void setjaInnskradUtlit() {
        fxNotandamynd.getStyleClass().add("innskrad");
        fxNotandanafn.setText(innskradurVidskiptavinur.getNafn().getValue());
        fxInnskraningHnappur.setText("Innskráning");
    }

    public Karfa getKarfa() {
        return karfa;
    }

    /**
     * Greiðsluhnappur (nú ganga frá pöntun) tekur þig hingað, og héðan opnast GreidslaController
     *
     * @param actionEvent Ýtt á greiðsluhnapp
     */
    @FXML
    private void fxGreidaHandler(ActionEvent actionEvent) {
        ViewSwitcher.switchTo(View.GREIDSLA);
    }

    public Vidskiptavinur getInnskradurVidskiptavinur() {
        return innskradurVidskiptavinur;
    }

}
