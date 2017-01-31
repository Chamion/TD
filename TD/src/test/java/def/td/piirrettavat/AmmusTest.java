/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

import def.td.logiikka.Pelitila;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class AmmusTest {
    
    private Ammus ammus;
    private Pelitila tila;
    
    @Before
    public void setUp() {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{100, 100});
        polku.add(new int[]{302, 100});
        this.tila = new Pelitila(polku);
        this.tila.lisaaMaali();
        this.ammus = new Ammus(new int[]{100, 200},this.tila.maalit().get(0));
    }
    
    @Test
    public void liikuSiirtaaAmmustaOikeanMatkan(){
        this.ammus.liiku(tila);
        assertEquals(100,this.ammus.sijainti()[0]);
        assertEquals(180,this.ammus.sijainti()[1]);
    }
    @Test
    public void liikuSiirtaaAmmustaOikeanMatkanVinottain(){
        Ammus vino = new Ammus(new int[]{200,200},this.tila.maalit().get(0));
        vino.liiku(tila);
        assertEquals(186,vino.sijainti()[0]);
        assertEquals(186,vino.sijainti()[1]);
    }
    @Test
    public void liikuPalauttaaTrueOsuessa(){
        assertFalse(ammus.liiku(tila));
        assertFalse(ammus.liiku(tila));
        assertFalse(ammus.liiku(tila));
        assertFalse(ammus.liiku(tila));
        assertTrue(ammus.liiku(tila));
    }
    @Test
    public void getMaaliPalauttaaOikeanMaalin(){
        assertEquals(this.tila.maalit().get(0).toString(),this.ammus.getMaali().toString());
    }
}
