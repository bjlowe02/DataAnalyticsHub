module rmit.dataanalyticshub {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens rmit.dataanalyticshub to javafx.fxml;
    exports rmit.dataanalyticshub;
}