package ProjetJava.MastermindJavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

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
    }

    public void addColor(ActionEvent actionEvent) {
        String s = (((Button) actionEvent.getSource()).getBackground().getFills().get(0).getFill()).toString();

        this.modl.completer_prop(couleurs.get(s));

    }

}
