package ProjetJava.MastermindJavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("MastermindTemplate.fxml");
        File file = new File(url.getPath());
        Parent root = FXMLLoader.load(file.toURL());

        Scene scene = new Scene(root);

        primaryStage.setTitle("Mastermind");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
