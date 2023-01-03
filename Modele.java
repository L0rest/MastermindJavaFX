package ProjetJava.Mastermind;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Observable;

public class Modele extends Observable {

    static final Color[] COULEURS = {Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.RED, Color.ORANGE, Color.WHITE, Color.BLACK};
    static final int N_TENTATIVES = 10;
    static final int DIFFICULTE = 4;

    public enum Etat {
        EN_COURS, GAGNE, PERDU
    }

    public Etat état;
    public Rangee combinaison;
    public Rangee[] propositions;
    public int tentative;

    public Modele() {
        this.état = Etat.EN_COURS;
        this.combinaison = new Rangee();
        this.propositions = new Rangee[N_TENTATIVES];
        this.tentative = 0;
    }

    public void archiver(Rangee r) {
        this.propositions[this.tentative] = r;
    }

    public void evaluer(Rangee r) {
        modif_blancs_noirs(r);
        archiver(r);
        this.tentative++;

        if (Arrays.toString(r.jetons).equals(Arrays.toString(this.combinaison.jetons))) {
            this.état = Etat.GAGNE;
        } else if (this.tentative == Modele.N_TENTATIVES) {
            this.état = Etat.PERDU;
        } else {
            new_prop();
        }
    }

    public void modif_blancs_noirs(Rangee r) {
        ArrayList<Color> rComb = new ArrayList<>(Arrays.asList(this.combinaison.jetons));
        ArrayList<Color> rProp = new ArrayList<>(Arrays.asList(r.jetons));
        int i = 0;

        while (i < rProp.size()) {
            if (Objects.equals(rProp.get(i).toString(), rComb.get(i).toString())) {
                r.noirs++;
                rComb.remove(i);
                rProp.remove(i);
            }
            else {
                i++;
            }
        }

        for (Color couleurProp : rProp) {
            if (rComb.contains(couleurProp)) {
                r.blancs++;
                rComb.remove(couleurProp);
            }
        }

    }

    public void new_prop() {
        this.propositions[this.tentative] = new Rangee();
    }

    public void completer_prop(Color c) {
        Rangee rangee_cour = this.propositions[this.tentative];

        rangee_cour.jetons[rangee_cour.indiceJeton] = c;
        if (rangee_cour.indiceJeton < 3) {
            rangee_cour.indiceJeton ++;
        }
        else {
            evaluer(rangee_cour);
        }

        this.setChanged();
        this.notifyObservers(this.propositions);
    }

    public void annuler() {
        Rangee rangee_cour = this.propositions[this.tentative];

        if (rangee_cour.indiceJeton > 0) {
            rangee_cour.indiceJeton --;
            rangee_cour.jetons[rangee_cour.indiceJeton] = null;
            archiver(rangee_cour);

            this.setChanged();
            this.notifyObservers(this.propositions);
        }

    }

}
