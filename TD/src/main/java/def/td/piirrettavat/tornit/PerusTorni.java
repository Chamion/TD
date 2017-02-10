/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.tornit;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.ammukset.HakeutuvaAmmus;
import def.td.piirrettavat.maalit.Maali;

/**
 * Torni, joka ampuu HakeutuvaAmmuksia yksi kerrallaan.
 */
public class PerusTorni extends Torni {

    public PerusTorni(int x, int y) {
        super(x, y);
        super.setHinta(10);
        super.setLatausAika(10);
        super.setKantama(200);
        super.setSade(10);
    }

    @Override
    public void ammu(Maali maali, Pelitila tila) {
        tila.lisaaAmmus(new HakeutuvaAmmus(this.sijainti(), maali));
    }
}
