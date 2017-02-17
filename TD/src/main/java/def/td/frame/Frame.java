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
import def.td.piirrettavat.tornit.HaulikkoTorni;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
        polku.add(new int[]{600, 130});
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
        this.logiikka.getTila().lisaaPisteet(100);
        this.logiikka.getTila().lisaaTorni(new HaulikkoTorni(200, 100));
        this.logiikka.seuraavaAalto();
    }

    @Override
    public void run() {
        this.frame = new JFrame();
        this.frame.setPreferredSize(new Dimension(600, 700));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.luoKomponentit(frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        this.piirtoalusta = new Piirtoalusta();
        container.add(this.piirtoalusta, BorderLayout.CENTER);
        this.logiikka.setPiirtoalusta(piirtoalusta);
        this.logiikka.kaynnista();
        this.frame.addMouseListener(new KlikkiKuuntelija(this.logiikka));
        Container valikko = new Container();
        JButton aaltoNappi = new JButton("Seuraava Aalto");
        aaltoNappi.addActionListener(new AaltoNappikuuntelija(this.logiikka));
        //valikko.add(aaltoNappi);
        container.add(aaltoNappi, BorderLayout.SOUTH);
    }

    private void piirra() {
        this.piirtoalusta.paintComponent(this.piirtoalusta.getGraphics());
    }
}
