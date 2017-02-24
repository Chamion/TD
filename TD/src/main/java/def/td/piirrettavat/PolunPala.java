/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

import java.awt.Color;
import java.awt.Graphics;

/**
 * PolunPala-olio esittää yhtä koordinaattiparia polussa. PolunPalat ovat kuin
 * välietappeja, joiden kautta Maalien on kuljettava.
 */
public class PolunPala extends Sijainnillinen {

    /**
     * Konstruktorille annetaan samat parametrit kuin yläluokalle
     * Sijainnillinen.
     *
     * @see def.td.piirrettavat.tornit#Sijainnillinen(int, int)
     *
     * @param x x-koordinaatti pelimaailmassa
     * @param y y-koordinaatti pelimaailmassa
     */
    public PolunPala(int x, int y) {
        super(x, y);
    }

    /**
     * Konstruktorille voidaan antaa x ja y koordinaatit koordinaatit
     * sisältävänä int-parina.
     *
     * @param sijainti 2-pituinen int-taulukko, jossa sijainti[0] on
     * x-koordinaati ja sijainti[1] y-koordinaatti.
     */
    public PolunPala(int[] sijainti) {
        super(sijainti[0], sijainti[1]);
    }

    @Override
    public int getSade() {
        return 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) {
            return false;
        }
        return this.equals((PolunPala) o);
    }

    private boolean equals(PolunPala o) {
        if (super.sijainti()[0] != o.sijainti()[0]
                || super.sijainti()[1] != o.sijainti()[1]) {
            return false;
        }
        return true;
    }

    /**
     * Piirtää PolunPalan graafisen esityksen Piirtoalustaan.
     *
     * @param graphics Piirtoalustan graphics-olio.
     */
    public void piirra(Graphics graphics) {
        graphics.setColor(Color.gray);
        graphics.fillRect(this.sijainti()[0] - 10, this.sijainti()[1] - 10, 20, 20);
    }
}
