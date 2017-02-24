/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

/**
 * Abstrakti luokka Liikkuville olioille. Sisältää metodeja olion sijainnin
 * muuttamiseen.
 */
public abstract class Liikkuva extends Sijainnillinen {

    /**
     * Matka, jonka Liikkuva siirtyy liikuKohti-metodilla.
     */
    private int nopeus;

    /**
     * Konstruktori saa parametrina int[]-muotoisen lukuparin, joka esittää
     * olion koordinaatteja pelimaailmassa.
     *
     * @param sijainti 2-pituinen int-taulukko, jossa sijainti[0] on
     * x-koordinaati ja sijainti[1] y-koordinaatti.
     */
    public Liikkuva(int[] sijainti) {
        super(sijainti);
        this.nopeus = 0;
    }

    public void setNopeus(int uusi) {
        this.nopeus = uusi;
    }

    public int getNopeus() {
        return this.nopeus;
    }

    /**
     * Siirtää olioa parametrien verran muuttamalla sijaintia.
     *
     * @param x x-koordinaatin muutos
     * @param y y-koordinaatin muutos
     */
    public void liikuKoordinaatein(int x, int y) {
        super.moveX(x);
        super.moveY(y);
    }

    /**
     * Siirtää olioa nopeuden verran kohti parametrina annettua kohdetta.
     *
     * @param kohde Sijainnillinen-olio, jota kohti liikutaan
     * @return true, jos kohde saavutettiin
     */
    public boolean liikuKohti(Sijainnillinen kohde) {
        double etaisyys = super.etaisyys(kohde);
        if (etaisyys <= this.nopeus) {
            return true;
        }
        int[] omaSijainti = super.sijainti();
        int[] kohdeSijainti = kohde.sijainti();
        super.moveX((int) Math.round((kohdeSijainti[0] - omaSijainti[0]) / etaisyys * this.nopeus));
        super.moveY((int) Math.round((kohdeSijainti[1] - omaSijainti[1]) / etaisyys * this.nopeus));
        //Pyöristysvirheitä
        return false;
    }
}
