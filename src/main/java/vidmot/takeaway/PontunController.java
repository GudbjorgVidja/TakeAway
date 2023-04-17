/**
 * Klasinn er í raun aðal Controller klasi verkefnisins. Tekur allar upplýsingar sem notandi setur inn og beinir öllum
 * upplýsingum í viðeigandi klasa.
 */
package vidmot.takeaway;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import vinnsla.takeaway.Karfa;
import vinnsla.takeaway.Veitingar;
import vinnsla.takeaway.Vidskiptavinur;

import java.util.Optional;

public class PontunController {
    private Veitingar valinnRetturAfMatsedli;//Veitingar hlutur sem síðast var ýtt á af matseðli (til að bæta í körfu)

    //TODO initializa annars staðar
    private IntegerProperty indexRettsUrKorfu = new SimpleIntegerProperty();//index hlutar sem smellt er á í körfu (til að fjarlægja)
    @FXML
    private Label heildarverd;//heildarverð label
    @FXML
    private ListView<Veitingar> karfaListView; //ListView viðmótshlutur fyrir innihald körfunnar
    private Karfa karfa;//Karfa hlutur, inniheldur matsedill hlut
    private Vidskiptavinur vidskiptavinur; //viðskiptavinurinn í kerfinu, null eða með gildi

    @FXML
    private MatsedillView matsedillView;//matsedillView hluturinn, viðmótslhutur sem inniheldur ListView

    @FXML
    private ImageView avatar;//mynd fyrir innskráðan notanda
    @FXML
    private Label notandi;//nafn notanda

    @FXML
    private Button innskraning; //hnappur fyrir innskráningu

    @FXML
    private Button greidaHnappur; //greiðsluhnappur, stendur ganga frá pöntun á honum

    //smiður fyrir pontunController
    public void initialize() {
        //todo setja þessar þrjár línur í sér aðferð, t.d. upphafsstillaSenu
        karfa = new Karfa();
        vidskiptavinur = null;
        greidaHnappur.setDisable(true);

        heildarverdBinder();

        karfa.veitingarList().addListener((ListChangeListener<Veitingar>) change -> {
            karfaListView.setItems(karfa.veitingarList());
        });

        nyjastAfMatsedliListener();
    }


    /**
     * þegar ýtt er á hlut í körfu þá er hann sendur hingað og tekinn úr körfunni. Nær í index hlutar
     */
    @FXML
    private void fxTakaUrKorfuHandler(MouseEvent event) {

        int index = karfaListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            indexRettsUrKorfu.set(index);
            karfa.takaAfMatsedli(indexRettsUrKorfu.intValue());
        }

    }


    /**
     * Setur í körfu það sem smellt er á af matseðli, leitar að selected hlut
     */
    private void nyjastAfMatsedliListener() { //todo endurnefna sidastValidAfMatsedliListener, eða validAfMatsedliListener?
        matsedillView.getMatsedillListi().getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
            valinnRetturAfMatsedli = matsedillView.getMatsedillListi().getSelectionModel().getSelectedItem();
            System.out.println(valinnRetturAfMatsedli);
            fxSetjaIKorfuHandler();
        });
    }


    //bindur saman heildarverð í viðmóti og vinnslu
    private void heildarverdBinder() {
        heildarverd.textProperty().bind(karfa.getHeildarverd().asString());
    }


    /**
     * Þegar ýtt er á hlut á matseðli er hann sendur hingað og settur í körfu
     */
    private void fxSetjaIKorfuHandler() {
        karfa.setjaMatsedil(valinnRetturAfMatsedli);
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
        if (vidskiptavinur == null) {
            Dialog<Vidskiptavinur> vidskiptiDialog = new VidskiptavinurDialog();
            Optional<Vidskiptavinur> result = vidskiptiDialog.showAndWait();
            if (result.isPresent()) {
                vidskiptavinur = result.get();
                setjaInnskradUtlit();
            }

        } else {
            new LoginDialog();
        }

    }

    //það er innskráður notandi
    public void setInnskradur(boolean b) {
        greidaHnappur.setDisable(false);
        //TODO breyta þessu, kannski hafa ekkert viðfang?
    }


    //útlit sett á þegar einhver viðskiptavinur er í kerfinu, og innskráður
    private void setjaInnskradUtlit() {
        avatar.getStyleClass().add("innskrad");
        notandi.setText(vidskiptavinur.getNafn().getValue());
        innskraning.setText("Innskráning");
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

    public Vidskiptavinur getVidskiptavinur() {
        return vidskiptavinur;
    }

}
