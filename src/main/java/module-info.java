module rmit.dataanalyticshub {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens rmit.dataanalyticshub to javafx.fxml;
    exports rmit.dataanalyticshub;
}