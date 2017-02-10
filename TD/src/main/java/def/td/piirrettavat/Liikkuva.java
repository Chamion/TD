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
public abstract class Liikkuva extends Sijainnillinen {

    private int nopeus;

    public Liikkuva(int[] sijainti) {
        super(sijainti);
        this.nopeus = 0;
    }

    public void setNopeus(int uusi) {
        this.nopeus = uusi;
    }
    
    public int getNopeus(){
        return this.nopeus;
    }
    
    public void liikuKoordinaatein(int x, int y){
        super.moveX(x);
        super.moveY(y);
    }

    public boolean liikuKohti(Sijainnillinen kohde) {
        double etaisyys = super.etaisyys(kohde);
        if (etaisyys <= this.nopeus) {
            return true;
        }
        int[] omaSijainti = super.sijainti();
        int[] kohdeSijainti = kohde.sijainti();
        super.moveX((int) Math.round((kohdeSijainti[0] - omaSijainti[0]) / etaisyys * this.nopeus));
        super.moveY((int) Math.round((kohdeSijainti[1] - omaSijainti[1]) / etaisyys * this.nopeus));
        //Pyöristysvirheitä
        return false;
    }
}
