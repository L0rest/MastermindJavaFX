package ProjetJava.MastermindJavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("MastermindTemplate.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Controleur control = new Controleur();
        loader.setController(control);

        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle("Mastermind");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
