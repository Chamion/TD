/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

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

    public boolean liiku() {
        if (super.liikuKohti(this.maali)) {
            this.maali.osuma();
            return true;
        }
        return false;
    }

    public Maali getMaali() {
        return this.maali;
    }
}
