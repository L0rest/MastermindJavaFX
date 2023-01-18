package ProjetJava.MastermindJavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    public Canvas canvas;
    Modele modl;
    public HashMap<String,Color> couleurs = new HashMap<>();

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
        GraphicsContext gc = canvas.getGraphicsContext2D();



        for (int i = 0; i < this.modl.propositions.length; i++) {
            if (this.modl.propositions[i] != null) {
                for (int j = 0; j < ProjetJava.MastermindJavaFX.Modele.DIFFICULTE; j++) {
                    if (this.modl.propositions[i].jetons[j] != null) {
                        Color currColor = this.modl.propositions[i].jetons[j];
                        javafx.scene.paint.Paint fxColor = javafx.scene.paint.Color.rgb(currColor.getRed(), currColor.getGreen(), currColor.getBlue(), 1.0);
                        gc.setFill(fxColor);
                        gc.fillOval((60 * j) + 30, (60 * i) + 10, 40, 40);
                    }
                }
                for (int j = 0; j < this.modl.propositions[i].noirs + this.modl.propositions[i].blancs; j++) {
                    if (j < this.modl.propositions[i].noirs) {
                        gc.setFill(javafx.scene.paint.Color.BLACK);
                        gc.fillOval(270 + (20 * j), (60 * i) + 10, 15, 15);
                    } else {
                        gc.setFill(javafx.scene.paint.Color.WHITE);
                        gc.fillOval(270 + (20 * j), (60 * i) + 10, 15, 15);
                    }
                }

            }
        }
    }

}
