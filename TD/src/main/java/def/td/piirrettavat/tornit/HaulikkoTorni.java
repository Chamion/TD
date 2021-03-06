/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.tornit;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.ammukset.SuoraAmmus;
import def.td.piirrettavat.maalit.Maali;

/**
 * Torni, joka ampuu SuoraAmmuksia ryppäittäin.
 */
public class HaulikkoTorni extends Torni {

    /**
     * taulukko kokonaislukutaulukoita, jotka kuvaavat häirintäarvopareja
     * {xOffset,yOffset}. Kun HaulikkoTorni ampuu, se käyttää jokaista näistä
     * häirintäarvopareista kerran parametreina SuoraAmmukselle.
     */
    private int[][] hajonta;

    /**
     * Konstruktori saa samat parametrit kuin yläluokka Torni.
     *
     * @see def.td.piirrettavat.tornit#Torni(int,int)
     *
     * @param x Tornin x-koordinaatti
     * @param y Tornin y-koordinaatti
     */
    public HaulikkoTorni(int x, int y) {
        super(x, y);
        super.setHinta(25);
        super.setLatausAika(10);
        super.setKantama(150);
        super.setSade(10);
        this.hajonta = new int[9][];
        this.hajonta[0] = new int[]{0, 0};
        this.hajonta[1] = new int[]{3, 0};
        this.hajonta[2] = new int[]{2, 2};
        this.hajonta[3] = new int[]{0, 3};
        this.hajonta[4] = new int[]{-2, 2};
        this.hajonta[5] = new int[]{-3, 0};
        this.hajonta[6] = new int[]{-2, -2};
        this.hajonta[7] = new int[]{0, -3};
        this.hajonta[8] = new int[]{2, -2};
    }

    /**
     * HaulikkoTorni ampuu 9 SuoraAmmusta aina, kun ammu-metodia kutsutaan.
     *
     * @param maali Maali, jota kohti ammutaan
     * @param tila Pelitila, jossa Torni on
     */
    @Override
    public void ammu(Maali maali, Pelitila tila) {
        for (int[] offset : this.hajonta) {
            tila.lisaaAmmus(new SuoraAmmus(super.sijainti(), maali, 15, offset[0], offset[1]));
        }
    }
}
