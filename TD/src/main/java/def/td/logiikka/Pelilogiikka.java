/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import java.util.ArrayList;

/**
 *
 * @author jemisalo
 */
public class Pelilogiikka {

    private Pelitila tila;
    private ArrayList<Object> aallot;

    public Pelilogiikka(ArrayList<int[]> polunSijainnit, ArrayList<Object> aallot) {
        this.tila = new Pelitila(polunSijainnit);
        this.aallot = aallot;
    }

    public void tick() {
        this.tila.liiku();
        this.tila.tahtaa();
    }

    public void kaynnista() {
        //Timer event listener
    }

    public void seuraavaAalto() {
        //"Seuraava aalto" napin toiminto.
    }

    private void piirra() {
        //Paljon myöhemmin.
    }
}
