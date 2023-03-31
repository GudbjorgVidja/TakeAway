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
    private Veitingar nuverandiRettur;//Veitingar hlutur sem síðast var ýtt á af matseðli (til að bæta í körfu)
    private IntegerProperty indexUrKorfu = new SimpleIntegerProperty();//index hlutar sem smellt er á í körfu (til að fjarlægja)
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
        karfa = new Karfa();
        vidskiptavinur = null;
        greidaHnappur.setDisable(true);

        heildarverdBinder();

        karfa.getObsVeitingar().addListener((ListChangeListener<Veitingar>) change -> {
            karfaListView.setItems(karfa.getObsVeitingar());
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
            indexUrKorfu.set(index);
            karfa.takaAfMatsedli(indexUrKorfu.intValue());
        }

    }


    /**
     * Setur í körfu það sem smellt er á af matseðli, leitar að selected hlut
     */
    private void nyjastAfMatsedliListener() {
        matsedillView.getMatsedillListi().getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
            nuverandiRettur = matsedillView.getMatsedillListi().getSelectionModel().getSelectedItem();
            System.out.println(nuverandiRettur);
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
        karfa.setjaMatsedil(nuverandiRettur);
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
            Dialog<Vidskiptavinur> vidskiptiDialog = new VidskiptavinurDialog(new Vidskiptavinur(null, null));
            Optional<Vidskiptavinur> result = vidskiptiDialog.showAndWait();
            if (result.isPresent()) {
                vidskiptavinur = result.get();
                innskradUtlit();
                avatar.getStyleClass().add("innskrad");
                innskraning.setText("Innskráning");
            }

        } else {
            LoginDialog innskraLykilord = new LoginDialog();
        }

    }

    //það er innskráður notandi
    public void setInnskradur(boolean b) {
        greidaHnappur.setDisable(false);
    }


    //útlit sett á þegar einhver viðskiptavinur er í kerfinu, og innskráður
    private void innskradUtlit() {
        avatar.getStyleClass().add("innskrad");
        notandi.setText(vidskiptavinur.getNafn().getValue());
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
