package vidmot.takeaway;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TakeAwayApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TakeAwayApplication.class.getResource("pontun-view.fxml"));

        Scene pontunarsena = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("TakeAway pöntunarforrit");
        stage.setScene(pontunarsena);
        
        ViewSwitcher.setScene(pontunarsena);
        ViewSwitcher.switchTo(View.PONTUN);

        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
