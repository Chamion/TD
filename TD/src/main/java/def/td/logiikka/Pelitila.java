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
    
    public Pelitila(ArrayList<int[]> sijainnit, int pisteet) {
        this(sijainnit);
        this.pisteet = pisteet;
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
    public boolean lisaaTorni(Torni uusi) {
        if (uusi.paallekkain(this.tornit)) {
            return false;
        } else if (uusi.paallekkain(this.polku)) {
            return false;
        } else if (uusi.hinta() > this.pisteet) {
            return false;
        }
        this.tornit.add(uusi);
        this.pisteet -= uusi.hinta();
        return true;
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

    /**
     * Kutsuu lisaaMaali-metodia parametrilla, jonka määrittelee annettu
     * parametri. Metodi lukee uudelle Maalille annettavat tiedot parametrista.
     *
     * @param stringform Maalin String-esitys, jonka tulee olla Aalto-olion
     * käyttämässä formaatissa.
     */
    public void lisaaMaali(String stringform) {
        String tyyppi = stringform.substring(0, 2);
        int hp = Integer.parseInt(stringform.substring(2, stringform.indexOf("/")));
        int param = Integer.parseInt(stringform.substring(stringform.indexOf("/") + 1));
        switch (tyyppi) {
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
        for (Ammus ammus : this.ammukset) {
            if (ammus.liiku(this)) {
                tuhottavatA.add(ammus);
            }
        }
        for (Ammus tuhottava : tuhottavatA) {
            this.ammukset.remove(tuhottava);
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
        for (PolunPala pala : this.polku) {
            pala.piirra(graphics);
        }
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
        return (listatSamat(this.ammukset, o.ammukset())
                && listatSamat(this.maalit, o.maalit())
                && listatSamat(this.polku, o.polku())
                && listatSamat(this.tornit, o.tornit()));
    }

    private boolean listatSamat(ArrayList lista1, ArrayList lista2) {
        if (lista1.size() != lista2.size()) {
            return false;
        }
        for (int i = 0; i < lista1.size(); i++) {
            if (!lista1.get(i).equals(lista2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
