/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.ammukset;

import def.td.piirrettavat.maalit.Maali;

/**
 *
 * @author jemisalo
 */
public class HakeutuvaAmmus extends Ammus {

    public HakeutuvaAmmus(int[] sijainti, Maali maali) {
        super(sijainti, maali);
        super.setNopeus(10);
    }
}
