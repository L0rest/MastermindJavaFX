package ProjetJava.MastermindJavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    public Canvas canvas;
    public HBox clavier;
    Modele modl;
    public HashMap<String, Color> couleurs = new HashMap<>();

    public void insertionsCouleursDico() {
        couleurs.put("0xffff00ff", Color.YELLOW);
        couleurs.put("0x008000ff", Color.GREEN);
        couleurs.put("0x0000ffff", Color.BLUE);
        couleurs.put("0xff00ffff", Color.MAGENTA);
        couleurs.put("0xff0000ff", Color.RED);
        couleurs.put("0xffa500ff", Color.ORANGE);
        couleurs.put("0xffffffff", Color.WHITE);
        couleurs.put("0x000000ff", Color.BLACK);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.modl = new Modele();
        insertionsCouleursDico();
        drawShapes();
    }

    public void addColor(ActionEvent actionEvent) {
        String s = (((Button) actionEvent.getSource()).getBackground().getFills().get(0).getFill()).toString();
        this.modl.completer_prop(couleurs.get(s));

        drawShapes();
    }

    public void drawShapes() {
        GraphicsContext gcanv = canvas.getGraphicsContext2D();
        gcanv.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < this.modl.propositions.length; i++) {
            if (this.modl.propositions[i] != null) {
                for (int j = 0; j < ProjetJava.MastermindJavaFX.Modele.DIFFICULTE; j++) {
                    if (this.modl.propositions[i].jetons[j] != null) {
                        Color currColor = this.modl.propositions[i].jetons[j];
                        javafx.scene.paint.Paint fxColor = javafx.scene.paint.Color.rgb(currColor.getRed(), currColor.getGreen(), currColor.getBlue(), 1.0);
                        gcanv.setFill(fxColor);
                        gcanv.fillOval((60 * j) + 30, (60 * i) + 10, 40, 40);
                    }
                }
                for (int j = 0; j < this.modl.propositions[i].noirs + this.modl.propositions[i].blancs; j++) {
                    if (j < this.modl.propositions[i].noirs) {
                        gcanv.setFill(javafx.scene.paint.Color.BLACK);
                        gcanv.fillOval(270 + (20 * j), (60 * i) + 10, 15, 15);
                    } else {
                        gcanv.setFill(javafx.scene.paint.Color.DARKGRAY);
                        gcanv.fillOval(270 + (20 * j), (60 * i) + 10, 15, 15);
                    }
                }

            }
        }

        modif_clavier();
    }

    public void undoMove(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (mouseEvent.getClickCount() == 2) {
                this.modl.annuler();
                drawShapes();
            }
        }
    }

    public void modif_clavier() {
        if (this.modl.état == Modele.Etat.GAGNE) {
            clavier.getChildren().clear();
            clavier.getChildren().add(new Label("Victoire !"));
        } else if (this.modl.état == Modele.Etat.PERDU) {
            clavier.getChildren().clear();
            clavier.getChildren().add(new Label("Perdu..."));
        }
    }

}
