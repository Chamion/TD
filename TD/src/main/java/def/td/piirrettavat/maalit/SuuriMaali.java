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
public class SuuriMaali extends Maali {

    private int palkkio;

    public SuuriMaali(ArrayList<PolunPala> polku, int hp) {
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

    @Override
    public void tuhoa(Pelitila tila) {
        tila.lisaaPisteet(1);
    }
}
