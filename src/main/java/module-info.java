module vidmot.takeaway {
    requires javafx.controls;
    requires javafx.fxml;
    requires algs4;


    opens vidmot.takeaway to javafx.fxml;
    exports vidmot.takeaway;
    exports vinnsla.takeaway;
}
