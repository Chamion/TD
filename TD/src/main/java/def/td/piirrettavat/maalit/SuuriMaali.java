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
 *
 * @author jemisalo
 */
public class SuuriMaali extends Maali {

    public SuuriMaali(ArrayList<PolunPala> polku, int hp) {
        super(polku);
        super.setHp(hp);
        super.setNopeus(5);
    }
    @Override
    public void tuhoa(Pelitila tila){
        tila.lisaaPisteet(1);
    }
}
