/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

import java.util.ArrayList;

/**
 *
 * @author jemisalo
 */
public class Torni extends Sijainnillinen {

    private int sade;
    private int hinta;
    private int kantama;
    private int lataus;
    private int maxLataus;

    public Torni(int x, int y) {
        super(x, y);
        this.sade = 10;
        this.hinta = 1;
        this.kantama = 100;
        this.lataus = 0;
        this.maxLataus = 10;
    }

    public void setSade(int uusi) {
        this.sade = uusi;
    }

    public void setKantama(int uusi) {
        this.kantama = uusi;
    }

    public void setLatausAika(int uusi) {
        this.maxLataus = uusi;
    }

    public boolean paallekkain(Sijainnillinen toinen) {
        return super.etaisyys(toinen) < this.sade + toinen.getSade();
    }

    public boolean paallekkain(ArrayList toiset) {
        for (Object toinen : toiset) {
            if (this.paallekkain((Sijainnillinen) toinen)) {
                return true;
            }
        }
        return false;
    }

    public void tahtaa(ArrayList<Maali> maalit) {
        if (this.lataus >= this.maxLataus) {
            Maali paras = null;
            for (Maali maali : maalit) {
                if (super.etaisyys(maali) < this.kantama) {
                    if (paras == null) {
                        paras = maali;
                    } else if (maali.getAskel() > paras.getAskel()) {
                        paras = maali;
                    }
                }
            }
            if (paras != null) {
                this.ammu(paras);
                this.lataus = 0;
            }
        } else {
            this.lataus++;
        }
    }

    private void ammu(Maali maali) {
        System.out.println(this.toString() + " ampuu kohdetta " + maali);
    }

    public int hinta() {
        return this.hinta;
    }

    @Override
    public int getSade() {
        return this.sade;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        } else {
            return this.equals((Torni) o);
        }
    }

    private boolean equals(Torni toinen) {
        if (this.hinta != toinen.hinta()) {
            return false;
        } else if (this.sijainti()[0] != toinen.sijainti()[0]
                || this.sijainti()[1] != toinen.sijainti()[1]) {
            return false;
        } else if (this.sade != toinen.getSade()) {
            return false;
        }
        return true;
    }

    //Deguggausmetodit
    @Override
    public String toString() {
        return "Torni " + super.sijainti()[0] + "," + super.sijainti()[1];
    }
}
