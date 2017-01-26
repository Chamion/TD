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
public class Maali extends Liikkuva {

    private ArrayList<PolunPala> polku;
    private int askel;
    private int hp;

    public Maali(ArrayList<PolunPala> polku) {
        super(polku.get(0).sijainti());
        this.polku = polku;
        super.setNopeus(4);
        this.askel = 0;
        this.hp = 1;
    }

    public void setAskel(int uusi) {
        this.askel = uusi;
    }

    public void setHp(int uusi) {
        this.hp = uusi;
    }

    public boolean osuma() {
        this.hp--;
        if (hp == 0) {
            return true;
        }
        return false;
    }

    public boolean kuollut() {
        return this.hp <= 0;
    }

    public boolean liiku() {
        PolunPala seuraava;
        try {
            seuraava = this.polku.get(this.askel + 1);
            while (super.liikuKohti(seuraava)) {
                this.askel++;
                seuraava = this.polku.get(this.askel + 1);
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public int getAskel() {
        return this.askel;
    }

    @Override
    public String toString() {
        return "Maali " + super.sijainti()[0] + "," + super.sijainti()[1];
    }
}
