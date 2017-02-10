/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

/**
 * PolunPala-olio esittää yhtä koordinaattiparia polussa. PolunPalat ovat kuin
 * välietappeja, joiden kautta Maalien on kuljettava.
 */
public class PolunPala extends Sijainnillinen {

    public PolunPala(int x, int y) {
        super(x, y);
    }

    public PolunPala(int[] sijainti) {
        super(sijainti[0], sijainti[1]);
    }

    @Override
    public int getSade() {
        return 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) {
            return false;
        }
        return this.equals((PolunPala) o);
    }

    private boolean equals(PolunPala o) {
        if (super.sijainti()[0] != o.sijainti()[0]
                || super.sijainti()[1] != o.sijainti()[1]) {
            return false;
        }
        return true;
    }
}
