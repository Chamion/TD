/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.ammukset;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.maalit.Maali;

/**
 * Ammus, joka saa konstruktorissa suunnan kohti Maaliaan ja liikkuu tähän
 * muuttumattomaan suuntaan vakionopeudella. Alkunopeuteen ja -suuntaan voidaan
 * vaikuttaa offset-muuttujilla.
 */
public class SuoraAmmus extends Ammus {

    private final int xNopeus;
    private final int yNopeus;

    public SuoraAmmus(int[] sijainti, Maali maali, int xOffset, int yOffset) {
        super(sijainti, maali);
        double etaisyys = super.etaisyys(maali);
        this.xNopeus = (int) ((maali.sijainti()[0] - super.sijainti()[0]) * 15 / etaisyys) + xOffset;
        this.yNopeus = (int) ((maali.sijainti()[1] - super.sijainti()[1]) * 15 / etaisyys) + yOffset;
    }

    @Override
    public boolean liiku(Pelitila tila) {
        super.liikuKoordinaatein(this.xNopeus, this.yNopeus);
        int[] sijainti = this.sijainti();
        if (sijainti[0] < -10 || sijainti[0] > 610) {
            return true;
        }
        if (sijainti[1] < -10 || sijainti[1] > 610) {
            return true;
        }
        for (Maali maali : tila.maalit()) {
            if (this.etaisyys(maali) <= maali.getSade()) {
                maali.osuma();
                tila.tuhoaMaali(maali);
                return true;
            }
        }
        return false;
    }
}
