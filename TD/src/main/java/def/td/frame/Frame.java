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
import def.td.logiikka.Pelilogiikka;
import def.td.logiikka.Pelitila;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Luokka, joka sisältää ja hallinnoi Swing olioita.
 */
public class Frame implements Runnable {

    /**
     * JFrame, joka sisältää kaikki käyttöliittymän Swing-oliot
     */
    private JFrame frame;
    /**
     * Pelitila, joka tulee piirtää piirtoalustaan.
     */
    private Pelitila tila;
    /**
     * Pelilogiikka, joka käsittelee käyttöliittymän syötteet.
     */
    private final Pelilogiikka logiikka;
    /**
     * Piirtoalusta, johon piirtyy pelin tilanne.
     */
    private Piirtoalusta piirtoalusta;

    public Frame() {
        this.logiikka = new Pelilogiikka();
    }

    @Override
    public void run() {
        this.frame = new JFrame();
        this.frame.setPreferredSize(new Dimension(600, 600));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.luoKomponentit(frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void luoKomponentit(Container container){
        GridLayout layout = new GridLayout(0, 1);
        container.setLayout(layout);
        this.piirtoalusta = new Piirtoalusta();
        container.add(this.piirtoalusta);
        this.logiikka.setPiirtoalusta(piirtoalusta);
        this.frame.addMouseListener(new KlikkiKuuntelija(this.logiikka));
    }
}
