/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.frame;

/**
 *
 * @author jemisalo
 */
import def.td.logiikka.Aalto;
import def.td.logiikka.Pelilogiikka;
import def.td.logiikka.Pelitila;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Luokka, joka sisältää ja hallinnoi Swing olioita.
 */
public class Frame implements Runnable {

    private JFrame frame;
    private Pelitila tila;
    private Pelilogiikka logiikka;
    private Piirtoalusta piirtoalusta;

    public Frame() {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{0, 150});
        polku.add(new int[]{15, 150});
        polku.add(new int[]{25, 160});
        polku.add(new int[]{35, 170});
        polku.add(new int[]{50, 170});
        polku.add(new int[]{50, 185});
        polku.add(new int[]{60, 195});
        polku.add(new int[]{75, 195});
        polku.add(new int[]{90, 195});
        for(int i=0;i<34;i++){
            polku.add(new int[]{105+15*i, 195});
        }
        ArrayList<Object> aaltoSyote = new ArrayList<>();
        aaltoSyote.add("pa1/1");
        ArrayList<Aalto> aallot = new ArrayList<>();
        aallot.add(new Aalto(aaltoSyote));
        aallot.add(new Aalto(aaltoSyote));
        aallot.add(new Aalto(aaltoSyote));
        aallot.add(new Aalto(aaltoSyote));
        aallot.add(new Aalto(aaltoSyote));
        aallot.add(new Aalto(aaltoSyote));
        this.logiikka = new Pelilogiikka(polku, aallot);
    }

    @Override
    public void run() {
        this.frame = new JFrame();
        this.frame.setPreferredSize(new Dimension(600, 600));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            this.luoKomponentit(frame.getContentPane());
        } catch (FileNotFoundException ex) {
        }
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void luoKomponentit(Container container) throws FileNotFoundException {
        GridLayout layout = new GridLayout(0,1);
        container.setLayout(layout);
        this.piirtoalusta = new Piirtoalusta();
        container.add(this.piirtoalusta);
        this.logiikka.setPiirtoalusta(piirtoalusta);
        this.logiikka.kaynnista("kentta.txt");
        this.frame.addMouseListener(new KlikkiKuuntelija(this.logiikka));
    }

    private void piirra() {
        this.piirtoalusta.paintComponent(this.piirtoalusta.getGraphics());
    }
}
