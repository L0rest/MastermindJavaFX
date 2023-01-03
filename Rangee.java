package ProjetJava.Mastermind;

import java.awt.*;

public class Rangee {

    Color[] jetons;
    int indiceJeton;
    int noirs, blancs;

    public Rangee() {
        this.jetons = new Color[Modele.DIFFICULTE];
        this.indiceJeton = 0;
        this.noirs = 0;
        this.blancs = 0;
    }

}
