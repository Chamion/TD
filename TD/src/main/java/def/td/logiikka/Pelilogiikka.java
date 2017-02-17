/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import def.td.frame.Kello;
import def.td.frame.Piirtoalusta;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Luokka, joka vastaanottaa KäLi-inputteja ja piirtää graafisen esityksen
 * Piirtoalustaan.
 */
public class Pelilogiikka {

    private Pelitila tila;
    private ArrayList<Aalto> aallot;
    private Aalto aktiivinenAalto;
    private Piirtoalusta piirtoalusta;

    public Pelilogiikka(ArrayList<int[]> polunSijainnit, ArrayList<Aalto> aallot) {
        this.tila = new Pelitila(polunSijainnit);
        this.aallot = aallot;
        this.aktiivinenAalto = null;
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
     * Luo ajastimen, joka kutsuu tick()-metodia tasaisin väliajoin.
     */
    public void kaynnista() {
        Timer kello = new Timer(200, new Kello(this));
        kello.start();
    }

    /**
     * Metodi, joka suorittaa kaikki toiminnot, jotka tulee suorittaa tasaisin
     * väliajoin. Päivittää Pelitilaa ja piirtää graafisen esityksen
     * Piirtoalustaan.
     */
    public void tick() {
        this.lisaaAallonMaali();
        this.tila.liiku();
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
    
    private void lisaaAallonMaali(){
        if(this.aktiivinenAalto==null){
            return;
        }
        String alkio = this.aktiivinenAalto.tick();
        if(alkio!=null){
            this.tila.lisaaMaali(alkio);
        }
    }
    
    private Aalto seuraavaAalto(){
        if(this.aallot.isEmpty()){
            return null;
        }
        Aalto seuraava = this.aallot.get(0);
        this.aallot.remove(0);
        return seuraava;
    }
}
