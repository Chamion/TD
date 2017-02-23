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
import def.td.piirrettavat.tornit.Torni;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
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
    private int valinta;

    public Pelilogiikka(ArrayList<int[]> polunSijainnit, ArrayList<Aalto> aallot) {
        this.tila = new Pelitila(polunSijainnit);
        this.aallot = aallot;
        this.aktiivinenAalto = null;
        this.valinta = 0;
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
     * 
     */
    public void kaynnista(String filepath) throws FileNotFoundException {
        File kentta = new File(filepath);
        Scanner scanner = new Scanner(kentta);
        ArrayList<int[]> polku = new ArrayList<>();
        while(true){
            String rivi = scanner.nextLine();
            if(rivi.equals("A")){
                break;
            }
            String[] osat = rivi.split(",");
            polku.add(new int[]{Integer.parseInt(osat[0]),Integer.parseInt(osat[1])});
        }
        ArrayList<Aalto> aallot = new ArrayList<>();
        while(scanner.hasNextLine()){
            String rivi = scanner.nextLine();
            String[] osat = rivi.split(",");
            ArrayList<Object> syote = new ArrayList<>();
            for(String osa : osat){
                try{
                    syote.add(Integer.parseInt(osa));
                }catch(Exception e){
                    syote.add(osa);
                }
            }
            aallot.add(new Aalto(syote));
        }
        this.setTila(new Pelitila(polku,20));
        this.aallot = aallot;
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
     * Jos aktiivinenAalto on null tai tyhja, aktivoi seuraavan Aallon, eli
     * päästää pelimaailman polulle lisää Maaleja.
     */
    public void seuraavaAalto() {
        if (this.aallot.isEmpty()) {
            return;
        }
        if (this.aktiivinenAalto != null) {
            if (!this.aktiivinenAalto.tyhja()) {
                return;
            }
        }
        this.aktiivinenAalto = this.aallot.get(0);
        this.aallot.remove(0);
    }

    public void click(int x, int y) {
        if(y>=500){
            this.valikkoClick(x,y);
        }else{
            this.canvasClick(x,y);
        }
    }
    private void canvasClick(int x, int y){
        switch (this.valinta){
            case 0:
                return;
            case 1:
                if(this.tila.lisaaTorni(new PerusTorni(x,y))){
                    this.valinta = 0;
                }
                return;
            case 2:
                if(this.tila.lisaaTorni(new HaulikkoTorni(x,y))){
                    this.valinta = 0;
                }
                return;
        }
    }
    private void valikkoClick(int x, int y){
        if(x<=100){
            this.seuraavaAalto();
        }else if(x<=200){
            valinta = 1;
        }else if(x<=300){
            valinta = 2;
        }
    }
}
