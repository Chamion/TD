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

    /**
     * Matka, jonka Ammus liikkuu x-akselin suunnassa liiku-metodilla.
     */
    private final int xNopeus;
    /**
     * Matka, jonka Ammus liikkuu y-akselin suunnassa liiku-metodilla.
     */
    private final int yNopeus;

    /**
     * Konstruktori saa parametrina sijainnin ja maalin, kuten yläluokka Ammus.
     * Lisäksi konstruktorille annetaan nopeus, joka on lopullinen ja xOffset ja
     * yOffset häirintäarvot, jotka vaikuttavat Ammuksen nopeuteen ja
     * liikumarataan.
     *
     * @see def.td.piirrettavat.ammukset#Ammus(int[],Maali)
     *
     * @param sijainti Ampuvan Tornin sijainti
     * @param maali Maali, jota kohti Ammus ammutaan
     * @param nopeus Ammuksen liikkumisnopeus kohti maalia
     * @param xOffset Luku, joka lisätään xNopeuteen nopeuden laskemisen jälkeen
     * @param yOffset Luku, joka lisätään yNopeuteen nopeuden laskemisen jälkeen
     */
    public SuoraAmmus(int[] sijainti, Maali maali, int nopeus, int xOffset, int yOffset) {
        super(sijainti, maali);
        double etaisyys = super.etaisyys(maali);
        this.xNopeus = (int) ((maali.sijainti()[0] - super.sijainti()[0]) * nopeus / etaisyys) + xOffset;
        this.yNopeus = (int) ((maali.sijainti()[1] - super.sijainti()[1]) * nopeus / etaisyys) + yOffset;
    }

    /**
     * Konstruktori saa parametrina sijainnin ja maalin, kuten yläluokka Ammus.
     * Lisäksi konstruktorille annetaan nopeus, joka on lopullinen.
     *
     * @param sijainti Ampuvan Tornin sijainti
     * @param maali Maali, jota kohti Ammus ammutaan
     * @param nopeus Ammuksen liikkumisnopeus kohti maalia
     */
    public SuoraAmmus(int[] sijainti, Maali maali, int nopeus) {
        super(sijainti, maali);
        double etaisyys = super.etaisyys(maali);
        this.xNopeus = (int) ((maali.sijainti()[0] - super.sijainti()[0]) * nopeus / etaisyys);
        this.yNopeus = (int) ((maali.sijainti()[1] - super.sijainti()[1]) * nopeus / etaisyys);
    }

    /**
     * SuoraAmmus liikkuu riippumatta sen maalista ja siten se voi osua mihin
     * tahansa pelimaailman maaliin.
     *
     * @param tila Pelitila, jossa Ammus on
     * @return true, jos Ammus tulisi poistaa pelimaailmasta
     */
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
                if (maali.osuma()) {
                    tila.tuhoaMaali(maali);
                }
                return true;
            }
        }
        return false;
    }
}
