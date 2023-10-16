package rmit.dataanalyticshub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.getIcons().add(new Image("file:src/main/resources/rmit/dataanalyticshub/images/icon.png"));
        stage.setTitle("Data Analytics Hub | Login");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() { //detects when a user is exiting program
        System.out.println("Stage is closing");
    }

    public static void main(String[] args) {
        launch();
    }
}