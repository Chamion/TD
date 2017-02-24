/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.frame;

import def.td.logiikka.Pelitila;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Canvas-luokka, johon pelin graafinen esitys piirtyy.
 */
public class Piirtoalusta extends JPanel {

    /**
     * Pelitila, jonka piirrettaviä olioita piirtyy Piirtoalustaan.
     */
    private Pelitila tila;

    /**
     * Konstruktori saa Pelitilan argumenttina, sillä kaikki piirrettävät oliot
     * ovat tallennettuna Pelitilaan.
     *
     * @param tila Pelitila, jonka Piirtoalusta piirtää.
     */
    public void setPelitila(Pelitila tila) {
        this.tila = tila;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.tila.piirra(graphics);
        this.piirraValikko(graphics);
    }

    private void piirraValikko(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 500, 100, 100);
        graphics.drawString("Seuraava Aalto", 3, 540);
        graphics.drawRect(100, 500, 100, 100);
        graphics.drawString("Perustorni", 120, 540);
        graphics.drawString("10p", 145, 560);
        graphics.drawRect(200, 500, 100, 100);
        graphics.drawString("Haulikkotorni", 210, 540);
        graphics.drawString("25p", 245, 560);
        graphics.drawString("pisteet: " + this.tila.getPisteet(), 400, 550);
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            return this.equals((Piirtoalusta) o);
        } else {
            return false;
        }
    }

    private boolean equals(Piirtoalusta p) {
        return this.toString().equals(p.toString());
    }
}
