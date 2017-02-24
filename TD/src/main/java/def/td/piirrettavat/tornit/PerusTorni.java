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

    /**
     * Konstruktori saa samat parametrit kuin yläluokka Torni.
     *
     * @see def.td.piirrettavat.tornit#Torni(int,int)
     *
     * @param x Tornin x-koordinaatti
     * @param y Tornin y-koordinaatti
     */
    public PerusTorni(int x, int y) {
        super(x, y);
        super.setHinta(10);
        super.setLatausAika(10);
        super.setKantama(200);
        super.setSade(10);
    }

    /**
     * Perustorni ampuu HakeutuvaAmmuksia Ammusten sijaan.
     *
     * @param maali Maali, jota kohti Torni ampuu
     * @param tila Pelitila, jossa torni on
     */
    @Override
    public void ammu(Maali maali, Pelitila tila) {
        tila.lisaaAmmus(new HakeutuvaAmmus(this.sijainti(), maali));
    }
}
