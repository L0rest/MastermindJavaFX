package ProjetJava.MastermindJavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MastermindTemplate.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Mastermind");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
