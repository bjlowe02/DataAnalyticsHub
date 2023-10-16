module rmit.dataanalyticshub {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;

    opens rmit.dataanalyticshub to javafx.fxml;
    exports rmit.dataanalyticshub;
    exports rmit.dataanalyticshub.createAccount;
    opens rmit.dataanalyticshub.createAccount to javafx.fxml;
    exports rmit.dataanalyticshub.hub;
    opens rmit.dataanalyticshub.hub to javafx.fxml;
    exports rmit.dataanalyticshub.login;
    opens rmit.dataanalyticshub.login to javafx.fxml;
}