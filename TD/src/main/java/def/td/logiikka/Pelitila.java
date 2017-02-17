/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import def.td.piirrettavat.ammukset.Ammus;
import def.td.piirrettavat.maalit.Maali;
import def.td.piirrettavat.PolunPala;
import def.td.piirrettavat.maalit.PalkkioMaali;
import def.td.piirrettavat.tornit.Torni;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Luokka, joka sisältää ja hallinnoi kaikkia pelimaailmassa esiintyviä olioita
 * ja niiden välisiä suhteita.
 */
public class Pelitila {

    private int pisteet;
    private ArrayList<Torni> tornit;
    private ArrayList<PolunPala> polku;
    private ArrayList<Maali> maalit;
    private ArrayList<Ammus> ammukset;

    public Pelitila() {
        this.tornit = new ArrayList<>();
        this.polku = new ArrayList<>();
        this.maalit = new ArrayList<>();
        this.ammukset = new ArrayList<>();
        this.pisteet = 0;
    }

    public Pelitila(ArrayList<int[]> sijainnit) {
        this();
        this.luoPolku(sijainnit);
    }

    private void luoPolku(ArrayList<int[]> sijainnit) {
        for (int[] sijainti : sijainnit) {
            this.polku.add(new PolunPala(sijainti));
        }
    }

    public void lisaaPisteet(int lisaa) {
        this.pisteet += lisaa;
        if (this.pisteet < 0) {
            this.pisteet = 0;
        }
    }

    /**
     * Lisää uuden Tornin pelimaailmaan.
     *
     * @param uusi Torni, joka lisätään
     */
    public void lisaaTorni(Torni uusi) {
        if (uusi.paallekkain(this.tornit)) {
            return;
        } else if (uusi.paallekkain(this.polku)) {
            return;
        } else if (uusi.hinta() > this.pisteet) {
            return;
        }
        this.tornit.add(uusi);
        this.pisteet -= uusi.hinta();
    }

    /**
     * Lisää oletusarvoisen Maalin polun alkuun.
     */
    public void lisaaMaali() {
        //tulee kuormittumaan, kun lisään erilaisia maalityyppejä.
        this.maalit.add(new Maali(this.polku));
    }

    /**
     * Lisää uuden Maalin pelimaailmaan.
     *
     * @param maali Maali, joka lisätään
     */
    public void lisaaMaali(Maali maali) {
        this.maalit.add(maali);
    }
    
    public void lisaaMaali(String stringform){
        String tyyppi = stringform.substring(0, 2);
        int hp = Integer.parseInt(stringform.substring(2,stringform.indexOf("/")));
        int param = Integer.parseInt(stringform.substring(stringform.indexOf("/")+2));
        switch (tyyppi){
            case "pa":
                PalkkioMaali lisattava = new PalkkioMaali(this.polku, hp);
                lisattava.setPalkkio(param);
                this.lisaaMaali(lisattava);
                break;
            default:
                break;
        }
    }

    /**
     * Poistaa parametrina annetun Maalin pelimaailmasta ja kutsuu sen
     * tuhoa()-metodia.
     *
     * @param maali Maali, joka tuhotaan
     */
    public void tuhoaMaali(Maali maali) {
        maali.tuhoa(this);
        if (this.maalit.contains(maali)) {
            this.maalit.remove(maali);
        }
    }

    /**
     * Lisää oletusarvoisen Ammuksen parametrina annettuun sijaintiin.
     *
     * @param maali Maali, joka annetaan Ammuksen parametriksi
     * @param sijainti koordinaattipari, joka annetaan Ammuksen parametriksi
     */
    public void lisaaAmmus(Maali maali, int[] sijainti) {
        //kuormitus myöhemmin
        this.ammukset.add(new Ammus(sijainti, maali));
    }

    /**
     * Lisää uuden Ammuksen pelimaailmaan.
     *
     * @param ammus Ammus, joka lisätään
     */
    public void lisaaAmmus(Ammus ammus) {
        this.ammukset.add(ammus);
    }

    /**
     * Kutsuu kaikkien pelimaailman tornien tahtaa()-metodia.
     */
    public void tahtaa() {
        for (Torni torni : this.tornit) {
            torni.tahtaa(this.maalit, this);
        }
    }

    /**
     * Kutsuu kaikkien pelimaailman Liikkuvien liiku()-metodia.
     */
    public void liiku() {
        for (Maali maali : this.maalit) {
            maali.liiku(); //poista tämä, kun if-lause on käytössä.
            /*if (maali.liiku()) {
                //gameover
            }*/
        }
        ArrayList<Ammus> tuhottavatA = new ArrayList<>();
        ArrayList<Maali> tuhottavatM = new ArrayList<>();
        for (Ammus ammus : this.ammukset) {
            if (ammus.liiku(this)) {
                tuhottavatA.add(ammus);
            }
        }
        for (Ammus tuhottava : tuhottavatA) {
            this.ammukset.remove(tuhottava);
        }
        for (Maali tuhottava : tuhottavatM) {
            this.maalit.remove(tuhottava);
        }
    }

    public ArrayList<Torni> tornit() {
        return this.tornit;
    }

    public ArrayList<PolunPala> polku() {
        return this.polku;
    }

    public ArrayList<Maali> maalit() {
        return this.maalit;
    }

    public ArrayList<Ammus> ammukset() {
        return this.ammukset;
    }

    public int getPisteet() {
        return this.pisteet;
    }

    /**
     * Kutsuu kaikkien pelimaailman olioiden piirra()-metodia.
     *
     * @param graphics Graphics, johon olioiden graafinen esitys piirretään
     */
    public void piirra(Graphics graphics) {
        for (Maali maali : this.maalit) {
            maali.piirra(graphics);
        }
        for (Torni torni : this.tornit) {
            torni.piirra(graphics);
        }
        for (Ammus ammus : this.ammukset) {
            ammus.piirra(graphics);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) {
            return false;
        }
        return this.equals((Pelitila) o);
    }

    private boolean equals(Pelitila o) {
        if (this.ammukset.size() != o.ammukset().size()) {
            return false;
        }
        for (int i = 0; i < this.ammukset.size(); i++) {
            if (!this.ammukset.get(i).equals(o.ammukset().get(i))) {
                return false;
            }
        }
        if (this.polku.size() != o.polku().size()) {
            return false;
        }
        for (int i = 0; i < this.polku.size(); i++) {
            if (!this.polku.get(i).equals(o.polku().get(i))) {
                return false;
            }
        }
        if (this.maalit.size() != o.maalit().size()) {
            return false;
        }
        for (int i = 0; i < this.maalit.size(); i++) {
            if (!this.maalit.get(i).equals(o.maalit().get(i))) {
                return false;
            }
        }
        if (this.tornit.size() != o.tornit().size()) {
            return false;
        }
        for (int i = 0; i < this.tornit.size(); i++) {
            if (!this.tornit.get(i).equals(o.tornit().get(i))) {
                return false;
            }
        }
        return true;
    }
}
