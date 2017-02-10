/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.tornit;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.maalit.Maali;
import def.td.piirrettavat.Sijainnillinen;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author jemisalo
 */
public class Torni extends Sijainnillinen {

    private int sade;
    private int hinta;
    private int kantama;
    private int lataus;
    private int maxLataus;

    public Torni(int x, int y) {
        super(x, y);
        this.sade = 10;
        this.hinta = 1;
        this.kantama = 100;
        this.lataus = 1;
        this.maxLataus = 10;
    }

    public void setSade(int uusi) {
        this.sade = uusi;
    }

    public void setKantama(int uusi) {
        this.kantama = uusi;
    }

    public void setLatausAika(int uusi) {
        this.maxLataus = uusi;
    }

    public void setHinta(int uusi) {
        this.hinta = uusi;
    }

    public boolean paallekkain(Sijainnillinen toinen) {
        return super.etaisyys(toinen) < this.sade + toinen.getSade();
    }

    public boolean paallekkain(ArrayList toiset) {
        for (Object toinen : toiset) {
            if (this.paallekkain((Sijainnillinen) toinen)) {
                return true;
            }
        }
        return false;
    }

    public void tahtaa(ArrayList<Maali> maalit, Pelitila tila) {
        if (this.lataus >= this.maxLataus) {
            Maali paras = null;
            for (Maali maali : maalit) {
                if (super.etaisyys(maali) < this.kantama) {
                    if (paras == null) {
                        paras = maali;
                    } else if (maali.getAskel() > paras.getAskel()) {
                        paras = maali;
                    }
                }
            }
            if (paras != null) {
                this.ammu(paras, tila);
                this.lataus = 1;
            }
        } else {
            this.lataus++;
        }
    }

    public void ammu(Maali maali, Pelitila tila) {
        tila.lisaaAmmus(maali, this.sijainti());
    }

    public int hinta() {
        return this.hinta;
    }

    public void piirra(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(this.sijainti()[0] - 5, this.sijainti()[1] - 5, 10, 10);
    }

    @Override
    public int getSade() {
        return 10;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        } else {
            return this.equals((Torni) o);
        }
    }

    private boolean equals(Torni toinen) {
        if (this.hinta != toinen.hinta()) {
            return false;
        } else if (this.sijainti()[0] != toinen.sijainti()[0]
                || this.sijainti()[1] != toinen.sijainti()[1]) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Torni " + super.sijainti()[0] + "," + super.sijainti()[1];
    }

}
