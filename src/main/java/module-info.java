module vidmot.takeaway {
    requires javafx.controls;
    requires javafx.fxml;


    opens vidmot.takeaway to javafx.fxml;
    exports vidmot.takeaway;
}
