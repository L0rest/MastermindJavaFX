package ProjetJava.Mastermind;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Controleur implements ActionListener, MouseListener {

    Modele modl;
    Random rand;

    public Controleur(Modele m) {
        this.modl = m;
        this.rand = new Random();
        for (int i = 0; i < Modele.DIFFICULTE; i++) {
            this.modl.combinaison.jetons[i] = Modele.COULEURS[rand.nextInt(Modele.COULEURS.length)];
        }
        this.modl.propositions[0] = new Rangee();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color couleur = new Color(Integer.parseInt(e.getActionCommand()));
        if (this.modl.état == Modele.Etat.EN_COURS) {
            this.modl.completer_prop(couleur);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (e.getClickCount() == 2) {
                this.modl.annuler();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
