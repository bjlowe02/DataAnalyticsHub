module rmit.dataanalyticshub {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens rmit.dataanalyticshub to javafx.fxml;
    exports rmit.dataanalyticshub;
}