/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import def.td.frame.Kello;
import def.td.frame.Piirtoalusta;
import def.td.piirrettavat.tornit.HaulikkoTorni;
import def.td.piirrettavat.tornit.PerusTorni;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;

/**
 * Luokka, joka vastaanottaa KäLi-inputteja ja piirtää graafisen esityksen
 * Piirtoalustaan.
 */
public class Pelilogiikka {

    /**
     * Pelitila, joka mallintaa pelimaailmaa.
     */
    private Pelitila tila;
    /**
     * ArrayList jäljellä olevista Aalloista, jotka voidaan päästää
     * pelimaailmaan.
     */
    private ArrayList<Aalto> aallot;
    /**
     * Aalto, josta puretaan Maaleja pelimaailmaan.
     */
    private Aalto aktiivinenAalto;
    /**
     * Piirtoalusta, johon tila piirtyy.
     */
    private Piirtoalusta piirtoalusta;
    /**
     * Luku, joka esittää Tornin valintaa valikosta. Ei valintaa on 0.
     */
    private int valinta;
    /**
     * Ajastin, joka kutsuu tick-metodia kun peli on käynnissä.
     */
    private Timer kello;
    /**
     * Boolean arvo, joka on true, kun peli on käynnissä.
     */
    private boolean peliKaynnissa;

    /**
     * Vanhentunut konstruktori. Käytä uutta parametritonta.
     *
     * @see def.td.logiikka#Pelilogiikka(ArrayList,ArrayList)
     *
     * @param polunSijainnit ArrayList int-pareja, jotka esittävät PolunPalojen
     * koordinaatteja.
     * @param aallot ArrayList Aalto-olioita, jotka purkaantuvat
     * seuraavaAalto-metodilla.
     */
    public Pelilogiikka(ArrayList<int[]> polunSijainnit, ArrayList<Aalto> aallot) {
        this();
        this.tila = new Pelitila(polunSijainnit);
        this.aallot = aallot;
    }

    /**
     * Konstruktorille ei anneta parametreja. Pelilogiikka lukee kentän aina
     * tiedostosta kentta.txt.
     */
    public Pelilogiikka() {
        this.kello = new Timer(200, new Kello(this));
        this.aktiivinenAalto = null;
        this.valinta = 0;
        this.peliKaynnissa = false;
    }

    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
        this.piirtoalusta.setPelitila(this.tila);
    }

    public void setTila(Pelitila uusi) {
        this.tila = uusi;
        if (this.piirtoalusta != null) {
            this.piirtoalusta.setPelitila(tila);
        }
    }

    /**
     * Alustaa pelin lukemalla polun ja aallot annetusta tiedostosta ja
     * asettamalla oliomuuttujat tila ja aallot niiden mukaisesti. Käynnistää
     * myös ajastimen, joka kutsuu tick-metodia tasaisin väliajoin.
     *
     * @param filepath Ladattavan kentän tiedoston polku.
     */
    public void kaynnista(String filepath) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filepath);
        Scanner scanner = new Scanner(is);
        ArrayList<int[]> polku = new ArrayList<>();
        while (true) {
            String rivi = scanner.nextLine();
            if (rivi.equals("A")) {
                break;
            }
            String[] osat = rivi.split(",");
            polku.add(new int[]{Integer.parseInt(osat[0]), Integer.parseInt(osat[1])});
        }
        ArrayList<Aalto> aallot = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String rivi = scanner.nextLine();
            String[] osat = rivi.split(",");
            ArrayList<Object> syote = new ArrayList<>();
            for (String osa : osat) {
                try {
                    syote.add(Integer.parseInt(osa));
                } catch (Exception e) {
                    syote.add(osa);
                }
            }
            aallot.add(new Aalto(syote));
        }
        this.setTila(new Pelitila(polku, 20));
        this.aallot = aallot;
        this.kello.start();
        this.peliKaynnissa = true;
    }

    /**
     * Metodi, joka suorittaa kaikki toiminnot, jotka tulee suorittaa tasaisin
     * väliajoin. Päivittää Pelitilaa ja piirtää graafisen esityksen
     * Piirtoalustaan. Keskeyttää pelin, jos Pelitila päätyy lopputilanteeseen.
     */
    public void tick() {
        this.lisaaAallonMaali();
        if (this.tila.liiku()) {
            this.gameOver();
            return;
        }
        this.tila.tahtaa();
        if (this.piirtoalusta != null) {
            this.piirtoalusta.repaint();
        }
    }

    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }

    public Pelitila getTila() {
        return this.tila;
    }

    private void lisaaAallonMaali() {
        if (this.aktiivinenAalto == null) {
            return;
        }
        String alkio = this.aktiivinenAalto.tick();
        if (alkio != null) {
            this.tila.lisaaMaali(alkio);
        }
    }

    /**
     * Jos aktiivinenAalto on null tai tyhja ja pelimaailmassa ei ole enää
     * maaleja, aktivoi seuraavan Aallon, eli päästää pelimaailman polulle lisää
     * Maaleja.
     */
    public void seuraavaAalto() {
        if (this.aallot.isEmpty()) {
            return;
        }
        if (this.aktiivinenAalto != null) {
            if (!this.aktiivinenAalto.tyhja() || !this.tila.maalit().isEmpty()) {
                return;
            }
        }
        this.aktiivinenAalto = this.aallot.get(0);
        this.aallot.remove(0);
    }

    /**
     * Käsittelee klikkauksen parametrina annetuissa koordinaateissa. Klikkaus
     * on joko canvasClick tai valikkoClick riippuen y-koordinaatista.
     * valikkoClick voi x-koordinaatista riippuen päästää seuraavan aallon tai
     * valita tornin. canvasClick lisää valitun tornin pelimaailmaan, jos
     * mahdollista. Jos torni lisätään, valinta tyhjenee. Jos peli ei ole vielä
     * käynnistetty, käynnistää pelin kentällä kentta.txt.
     *
     * @param x Klikkauksen etäisyys ikkunan vasemmasta reunasta.
     * @param y Klikkauksen etäisyys ikkunan yläreunasta.
     */
    public void click(int x, int y) {
        if (!this.peliKaynnissa) {
            this.kaynnista("kentta.txt");
            return;
        }
        if (y >= 500) {
            this.valikkoClick(x, y);
        } else {
            this.canvasClick(x, y);
        }
    }

    private void canvasClick(int x, int y) {
        switch (this.valinta) {
            case 0:
                return;
            case 1:
                if (this.tila.lisaaTorni(new PerusTorni(x, y))) {
                    this.valinta = 0;
                }
                return;
            case 2:
                if (this.tila.lisaaTorni(new HaulikkoTorni(x, y))) {
                    this.valinta = 0;
                }
                return;
        }
    }

    private void valikkoClick(int x, int y) {
        if (x <= 100) {
            this.seuraavaAalto();
        } else if (x <= 200) {
            valinta = 1;
        } else if (x <= 300) {
            valinta = 2;
        }
    }

    private void gameOver() {
        this.kello.stop();
        this.peliKaynnissa = false;
    }
}
