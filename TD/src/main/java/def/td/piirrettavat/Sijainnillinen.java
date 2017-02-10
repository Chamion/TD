/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

/**
 * Abstrakti luokka koordinaatit sisältäville olioille. Sisältää metodeja
 * koordinaattien asettamiseen ja muuttamiseen.
 */
public abstract class Sijainnillinen {

    private int x;
    private int y;

    public Sijainnillinen(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Sijainnillinen(int[] sijainti) {
        this.x = sijainti[0];
        this.y = sijainti[1];
    }

    public int[] sijainti() {
        return new int[]{this.x, this.y};
    }

    public void moveX(int siirtyma) {
        this.x += siirtyma;
    }

    public void moveY(int siirtyma) {
        this.y += siirtyma;
    }

    /**
     * Laskee etäisyyden toiseen Sijainnillinen-olioon Pythagoraan lauseella.
     *
     * @param toinen Sijainnillinen-olio, johon etäisyyttä mitataan
     * @return tämän ja parametrin välinen etäisyys liukulukuna
     */
    public double etaisyys(Sijainnillinen toinen) {
        int[] sijainti = toinen.sijainti();
        return Math.sqrt(Math.pow(this.x - sijainti[0], 2) + Math.pow(this.y - sijainti[1], 2));
    }

    public int getSade() {
        return 0;
    }
}
