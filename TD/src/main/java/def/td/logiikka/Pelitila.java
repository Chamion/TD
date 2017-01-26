/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import def.td.piirrettavat.Ammus;
import def.td.piirrettavat.Maali;
import def.td.piirrettavat.PolunPala;
import def.td.piirrettavat.Torni;
import java.util.ArrayList;

/**
 *
 * @author jemisalo
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
        this.tornit = new ArrayList<>();
        this.polku = new ArrayList<>();
        this.maalit = new ArrayList<>();
        this.ammukset = new ArrayList<>();
        this.pisteet = 0;
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

    public void lisaaMaali() {
        //tulee muuttumaan, kun lisään erilaisia maalityyppejä.
        this.maalit.add(new Maali(this.polku));
    }

    public void tahtaa() {
        for (Torni torni : this.tornit) {
            torni.tahtaa(this.maalit);
        }
    }

    public void liiku() {
        /*for (Maali maali : this.maalit) {
            if (maali.liiku()) {
                //gameover
            }
        }*/
        for (Ammus ammus : this.ammukset) {
            ArrayList<Ammus> tuhottavatA = new ArrayList<>();
            ArrayList<Maali> tuhottavatM = new ArrayList<>();
            if (ammus.liiku()) {
                tuhottavatA.add(ammus);
                if (ammus.getMaali().kuollut()) {
                    tuhottavatM.add(ammus.getMaali());
                }
            }
            for (Ammus tuhottava : tuhottavatA) {
                this.ammukset.remove(tuhottava);
            }
            for (Maali tuhottava : tuhottavatM) {
                this.maalit.remove(tuhottava);
            }
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

    public int getPisteet() {
        return this.pisteet;
    }

    //Debuggaukseen
    public void tulostaTornit() {
        for (Torni torni : this.tornit) {
            System.out.println(torni);
        }
    }

    public void tulostaMaalit() {
        for (Maali maali : this.maalit) {
            System.out.println(maali);
        }
    }
}
