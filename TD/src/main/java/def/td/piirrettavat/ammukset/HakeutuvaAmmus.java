/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.ammukset;

import def.td.piirrettavat.maalit.Maali;

/**
 * Ammus, joka liikkuu aina nopeutensa verran kohti Maaliaan.
 */
public class HakeutuvaAmmus extends Ammus {

    /**
     * Konstruktori saa samat parametrit kuin yläluokka Ammus.
     *
     * @see def.td.piirrettavat.ammukset#Ammus(int[],Maali)
     *
     * @param sijainti Ampuvan Tornin sijainti
     * @param maali Maali, johon Ammus hakeutuu
     */
    public HakeutuvaAmmus(int[] sijainti, Maali maali) {
        super(sijainti, maali);
        super.setNopeus(10);
    }
}
