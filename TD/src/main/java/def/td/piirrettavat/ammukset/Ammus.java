/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.ammukset;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.Liikkuva;
import def.td.piirrettavat.maalit.Maali;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jemisalo
 */
public class Ammus extends Liikkuva {

    private Maali maali;

    public Ammus(int[] sijainti, Maali maali) {
        super(sijainti);
        this.maali = maali;
        super.setNopeus(20);
    }

    public boolean liiku(Pelitila tila) {
        if(this.maali == null){
            return true;
        }
        if (this.maali.kuollut()) {
            return true;
        }
        if (super.liikuKohti(this.maali)) {
            if (this.maali.osuma()) {
                tila.tuhoaMaali(this.maali);
            }
            return true;
        }
        return false;
    }

    public Maali getMaali() {
        return this.maali;
    }

    public void piirra(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect(this.sijainti()[0] - 2, this.sijainti()[1] - 2, 4, 4);
    }

    @Override
    public String toString() {
        return "Ammus " + this.sijainti()[0] + ", " + this.sijainti()[1];
    }
    
    @Override
    public boolean equals(Object o){
        if(this.getClass()!=o.getClass()){
            return false;
        }
        return this.equals((Ammus) o);
    }
    
    private boolean equals(Ammus o){
        if(super.sijainti()[0]!=o.sijainti()[0]
                || super.sijainti()[1]!=o.sijainti()[1]){
            return false;
        }
        if(this.maali!=o.getMaali()){
            return false;
        }
        if(super.getNopeus()!=o.getNopeus()){
            return false;
        }
        return true;
    }
}
