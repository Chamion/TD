/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import def.td.frame.Kello;
import def.td.frame.Piirtoalusta;
import def.td.piirrettavat.tornit.PerusTorni;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author jemisalo
 */
public class Pelilogiikka {

    private Pelitila tila;
    private ArrayList<Object> aallot;
    private Piirtoalusta piirtoalusta;

    public Pelilogiikka(ArrayList<int[]> polunSijainnit, ArrayList<Object> aallot) {
        this.tila = new Pelitila(polunSijainnit);
        this.aallot = aallot;
    }

    //Debuggaukseen
    public Pelilogiikka() {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{0, 150});
        polku.add(new int[]{600, 130});
        this.tila = new Pelitila(polku);
        this.tila.lisaaPisteet(100);
        this.tila.lisaaTorni(new PerusTorni(200, 100));
        this.tila.lisaaMaali();
        this.tila.maalit().get(0).setHp(10);
        this.aallot = new ArrayList<>();
    }

    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
        this.piirtoalusta.setPelitila(this.tila);
    }
    
    public void setTila(Pelitila uusi){
        this.tila = uusi;
        if(this.piirtoalusta!=null){
            this.piirtoalusta.setPelitila(tila);
        }
    }

    public void kaynnista() {
        Timer kello = new Timer(200, new Kello(this));
        kello.start();
    }
    
    public void tick(){
        this.tila.liiku();
        this.tila.tahtaa();
        if(this.piirtoalusta!=null){
            this.piirtoalusta.repaint();
        }
    }

    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }
    
    public Pelitila getTila(){
        return this.tila;
    }
}
