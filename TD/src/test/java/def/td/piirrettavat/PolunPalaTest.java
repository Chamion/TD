/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

import def.td.piirrettavat.tornit.Torni;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class PolunPalaTest {

    private PolunPala pala;

    @Before
    public void setUp() {
        this.pala = new PolunPala(new int[]{100, 100});
    }

    @Test
    public void sijaintiPalauttaaOikeanArvon() {
        assertEquals(100, this.pala.sijainti()[0]);
        assertEquals(100, this.pala.sijainti()[1]);
    }

    @Test
    public void sadeOnKymmenen() {
        assertEquals(10, this.pala.getSade());
    }

    @Test
    public void equalsTunnistaaSamat() {
        PolunPala sama = new PolunPala(new int[]{100, 100});
        assertTrue(sama.equals(this.pala));
    }

    @Test
    public void equalsHylkaaEri() {
        PolunPala eri = new PolunPala(new int[]{101, 100});
        assertFalse(eri.equals(this.pala));
        eri = new PolunPala(new int[]{99, 100});
        assertFalse(eri.equals(this.pala));
        eri = new PolunPala(new int[]{100, 101});
        assertFalse(eri.equals(this.pala));
        eri = new PolunPala(new int[]{100, 99});
        assertFalse(eri.equals(this.pala));
        assertFalse(this.pala.equals(new Torni(100, 100)));
    }
}
