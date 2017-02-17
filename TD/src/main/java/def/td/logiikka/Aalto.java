/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import java.util.ArrayList;

/**
 * Aalto-olio palauttaa pelimaailmaan lisättäviä Maaleja String-muodossa, kun
 * sen tick-metodia kutsutaan Pelilogiikan tick-metodissa. Aalto ei ota kantaa,
 * mille polulle Maalit lisätään.
 */
public class Aalto {

    private final ArrayList<Object> syote;
    private int odotus;
    private int syoteIndex;

    public Aalto(ArrayList<Object> syote) {
        this.syote = syote;
        this.odotus = 0;
        this.syoteIndex = 0;
    }

    public Aalto(String stringform) {
        this.syote = new ArrayList<>();
        this.odotus = 0;
        this.syoteIndex = 0;
        for (String alkio : stringform.split(",")) {
            try {
                this.syote.add(Integer.parseInt(alkio));
            } catch (Exception e) {
                this.syote.add(alkio);
            }
        }
    }

    /**
     * Palauttaa true, jos Aalto on jo palauttanut kaikki sen maalit. False, jos
     * maaleja on vielä palauttamatta.
     *
     * @return true, jos tick() ei enää voi palauttaa String-arvoja.
     */
    public boolean tyhja() {
        return this.syoteIndex >= this.syote.size();
    }

    /**
     * Palauttaa null, ja String arvoja, jotka kertovat Pelilogiikalle, mitä
     * maaleja lisätä pelimaailmaan. String-arvot ovat muotoa "AAx/y", missä AA
     * on kaksimerkkinen Maalityypin tunnus, x on Maalin alku-HP ja y toinen
     * maaliin liittyvä parametri.
     *
     * @return null, jos maalia ei tule lisätä, Maalin String-esitys jos maali
     * tulisi lisätä.
     */
    public String tick() {
        if (this.odotus > 0) {
            this.odotus--;
            return null;
        }
        if (this.syoteIndex >= this.syote.size()) {
            return null;
        }
        Object alkio = this.syote.get(this.syoteIndex);
        this.syoteIndex++;
        try {
            this.odotus = (int) alkio - 1;
        } catch (Exception e) {
            return (String) alkio;
        }
        return null;
    }
}
