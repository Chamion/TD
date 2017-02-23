/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.ammukset;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.maalit.Maali;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class SuoraAmmusTest {

    private Ammus ammus;
    private Pelitila tila;

    @Before
    public void setUp() {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{100, 100});
        polku.add(new int[]{200, 100});
        this.tila = new Pelitila(polku);
        this.tila.lisaaMaali();
        this.ammus = new SuoraAmmus(new int[]{100, 200}, this.tila.maalit().get(0), 15, 0, 0);
    }

    @Test
    public void notEqualsMuunTyypinAmmus() {
        assertFalse(this.ammus.equals(new Ammus(new int[]{100, 200}, this.tila.maalit().get(0))));
    }

    @Test
    public void liikuSiirtaaAmmusta() {
        this.ammus.liiku(this.tila);
        assertEquals(100, this.ammus.sijainti()[0]);
        assertEquals(185, this.ammus.sijainti()[1]);
    }

    @Test
    public void ammusEiSeuraa() {
        this.tila.maalit().get(0).setNopeus(80);
        this.tila.liiku();
        this.ammus.liiku(this.tila);
        assertEquals(100, this.ammus.sijainti()[0]);
        assertEquals(185, this.ammus.sijainti()[1]);
    }

    @Test
    public void ammusTormaaMaaliinJokaEiOleSen() {
        this.tila.lisaaMaali();
        this.tila.maalit().get(1).setHp(1);
        this.tila.maalit().get(1).moveY(85);
        Maali maali = this.tila.maalit().get(1);
        assertTrue(this.tila.maalit().contains(maali));
        this.ammus.liiku(this.tila);
        assertFalse(this.tila.maalit().contains(maali));
    }
    
    @Test
    public void offsetParametritVaikuttavat(){
        Ammus[] ammukset = new Ammus[9];
        ammukset[0] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,0,0);
        ammukset[1] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,0,3);
        ammukset[2] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,2,4);
        ammukset[3] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,6,0);
        ammukset[4] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,1,-1);
        ammukset[5] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,0,-3);
        ammukset[6] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,-7,-2);
        ammukset[7] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,-4,0);
        ammukset[8] = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,-1,3);
        for(Ammus ammus : ammukset){
            ammus.liiku(this.tila);
        }
        for(int i=1;i<9;i++){
            for(int j=1;j<9;j++){
                if(i!=j){
                    assertFalse(ammukset[i].equals(ammukset[j]));
                }
            }
        }
    }
    
    @Test
    public void liikuTrueJosYliLaidan(){
        this.ammus = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,700,0);
        assertTrue(this.ammus.liiku(this.tila));
        this.ammus = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,-700,0);
        assertTrue(this.ammus.liiku(this.tila));
        this.ammus = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,0,700);
        assertTrue(this.ammus.liiku(this.tila));
        this.ammus = new SuoraAmmus(new int[]{300,300},this.tila.maalit().get(0), 15,0,-700);
        assertTrue(this.ammus.liiku(this.tila));
    }
}
