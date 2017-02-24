/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.maalit;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.PolunPala;
import java.util.ArrayList;

/**
 * Maali, jonka tuhouduttua, pelaaja saa pisteitä.
 */
public class PalkkioMaali extends Maali {

    /**
     * Luku, joka kuvaa Maalin tuhoutumisesta saatavien pisteiden määrää.
     */
    private int palkkio;

    /**
     * Konstruktorille annetaan polku, kuten yläluokalle Maali. Lisäksi
     * konstruktori saa parametrina osumapisteiden alkuarvon. Konstruktor
     * asettaa palkkion oletusarvoon 1. Jos palkkion tulee olla muu kuin 1,
     * kutsu setPalkkio.
     *
     * @see def.td.piirrettavat.maalit#Maali(ArrayList)
     *
     * @param polku Polku, jota pitkin Maali kulkee
     * @param hp alkuarvo osumapisteille
     */
    public PalkkioMaali(ArrayList<PolunPala> polku, int hp) {
        super(polku);
        super.setHp(hp);
        super.setNopeus(5);
        this.palkkio = 1;
    }

    public void setPalkkio(int uusi) {
        if (uusi < 0) {
            return;
        }
        this.palkkio = uusi;
    }

    public int getPalkkio() {
        return this.palkkio;
    }

    /**
     * PalkkioMaalin tuhoutuessa Pelitilan pisteet kasvavat PalkkioMaalin
     * palkkion verran.
     *
     * @param tila Pelitila, jossa Maali on
     */
    @Override
    public void tuhoa(Pelitila tila) {
        tila.lisaaPisteet(this.palkkio);
    }
}
