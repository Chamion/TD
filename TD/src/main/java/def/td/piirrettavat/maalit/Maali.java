/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.maalit;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.Liikkuva;
import def.td.piirrettavat.PolunPala;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Maali-olio esittää pelimaailmassa polkua pitkin kulkevaa vihollista. Maalit
 * kulkevat parametrina saanuttaan polkua pitkin.
 */
public class Maali extends Liikkuva {

    private ArrayList<PolunPala> polku;
    private int askel;
    private int hp;

    /**
     * Konstruktori asettaa Maalin parametrina annetun polun alkupisteeseen.
     *
     * @param polku Polku, jota pitkin Maali kulkee
     */
    public Maali(ArrayList<PolunPala> polku) {
        super(polku.get(0).sijainti());
        this.polku = polku;
        super.setNopeus(4);
        this.askel = 0;
        this.hp = 1;
    }

    public void setAskel(int uusi) {
        this.askel = uusi;
    }

    public void setHp(int uusi) {
        this.hp = uusi;
    }

    public int getHp() {
        return this.hp;
    }

    /**
     * Vähentää Maalin hp yhdellä.
     *
     * @return true, jos Maali kuoli osumaan
     */
    public boolean osuma() {
        this.hp--;
        return hp == 0;
    }

    /**
     * Metodi, jota tulisi kutsua, kun Maali kuolee.
     *
     * @param tila Pelitila, jossa Maali on
     */
    public void tuhoa(Pelitila tila) {
        return;
    }

    public boolean kuollut() {
        return this.hp <= 0;
    }

    /**
     * Siirtää Maalia sen nopeuden verran kohti seuraavaa PolunPalaa. Jos Maali
     * saavuttaa PolunPalan, metodi automaattisesti kasvattaa askelmuuttujaa.
     *
     * @return true, jos Maali saavutti polkunsa viimeisen PolunPalan
     */
    public boolean liiku() {
        PolunPala seuraava;
        try {
            seuraava = this.polku.get(this.askel + 1);
            while (super.liikuKohti(seuraava)) {
                this.askel++;
                seuraava = this.polku.get(this.askel + 1);
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public int getAskel() {
        return this.askel;
    }

    public void piirra(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillOval(this.sijainti()[0] - 5, this.sijainti()[1] - 5, 10, 10);
    }

    @Override
    public String toString() {
        return "Maali " + super.sijainti()[0] + "," + super.sijainti()[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) {
            return false;
        } else {
            return this.equals((Maali) o);
        }
    }

    private boolean equals(Maali o) {
        if (this.askel != o.getAskel()) {
            return false;
        }
        if (super.sijainti()[0] != o.sijainti()[0]
                || super.sijainti()[1] != o.sijainti()[1]) {
            return false;
        }
        if (super.getNopeus() != o.getNopeus()) {
            return false;
        }
        return this.hp == o.getHp();
    }

    @Override
    public int getSade() {
        return 10;
    }
}
