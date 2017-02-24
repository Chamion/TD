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

    /**
     * Olion x-koordinaatti pelimaailmassa.
     */
    private int x;
    /**
     * Olion y-koordinaatti pelimaailmassa.
     */
    private int y;

    /**
     * Konstruktorille annetaan parametreina olion x ja y koordinaatit
     * pelimaailmassa.
     *
     * @param x x-koordinaatti pelimaailmassa
     * @param y y-koordinaatti pelimaailmassa
     */
    public Sijainnillinen(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Konstruktorille voidaan antaa x ja y koordinaatit koordinaatit
     * sisältävänä int-parina.
     *
     * @param sijainti 2-pituinen int-taulukko, jossa sijainti[0] on
     * x-koordinaati ja sijainti[1] y-koordinaatti.
     */
    public Sijainnillinen(int[] sijainti) {
        this.x = sijainti[0];
        this.y = sijainti[1];
    }

    /**
     * Palauttaa olion sijainnin pelimaailmassa int-parina.
     *
     * @return int[]-taulukko, jossa [0] on x-koordinaatti ja [1]
     * y-koordinaatti.
     */
    public int[] sijainti() {
        return new int[]{this.x, this.y};
    }

    /**
     * Muuttaa oliomuuttujan x arvoa parametrin verran.
     *
     * @param siirtyma Luku, joka lisätään x-koordinaattiin.
     */
    public void moveX(int siirtyma) {
        this.x += siirtyma;
    }

    /**
     * Muuttaa oliomuuttujan y arvoa parametrin verran.
     *
     * @param siirtyma Luku, joka lisätään y-koordinaattiin.
     */
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
