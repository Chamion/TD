/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.ammukset;

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
        this.ammus = new Ammus(new int[]{100, 200}, this.tila.maalit().get(0));
        this.ammus.setNopeus(20);
    }

    @Test
    public void liikuSiirtaaAmmustaOikeanMatkan() {
        this.ammus.liiku(tila);
        assertEquals(100, this.ammus.sijainti()[0]);
        assertEquals(180, this.ammus.sijainti()[1]);
        this.ammus.setNopeus(10);
        this.ammus.liiku(tila);
        assertEquals(100, this.ammus.sijainti()[0]);
        assertEquals(170, this.ammus.sijainti()[1]);
    }

    @Test
    public void liikuSiirtaaAmmustaOikeanMatkanVinottain() {
        Ammus vino = new Ammus(new int[]{200, 200}, this.tila.maalit().get(0));
        vino.liiku(tila);
        assertEquals(186, vino.sijainti()[0]);
        assertEquals(186, vino.sijainti()[1]);
    }

    @Test
    public void liikuPalauttaaTrueOsuessa() {
        assertFalse(ammus.liiku(tila));
        assertFalse(ammus.liiku(tila));
        assertFalse(ammus.liiku(tila));
        assertFalse(ammus.liiku(tila));
        assertTrue(ammus.liiku(tila));
    }

    @Test
    public void getMaaliPalauttaaOikeanMaalin() {
        assertEquals(this.tila.maalit().get(0).toString(), this.ammus.getMaali().toString());
    }

    @Test
    public void equalsTunnistaaSamat() {
        Ammus sama1 = new Ammus(new int[]{100, 200}, this.tila.maalit().get(0));
        sama1.setNopeus(20);
        assertTrue(sama1.equals(this.ammus));
        Ammus sama2 = new Ammus(new int[]{100, 220}, this.tila.maalit().get(0));
        sama2.setNopeus(20);
        sama2.liiku(this.tila);
        assertTrue(sama2.equals(this.ammus));
    }

    @Test
    public void equalsHylkaaEri() {
        Ammus eri1 = new Ammus(new int[]{100, 200}, this.tila.maalit().get(0));
        eri1.setNopeus(10);
        assertFalse(eri1.equals(this.ammus));
        Ammus eri2 = new Ammus(new int[]{100, 200}, this.tila.maalit().get(0));
        eri2.setNopeus(20);
        eri2.liiku(this.tila);
        assertFalse(eri2.equals(this.ammus));
        this.tila.liiku();
        this.tila.lisaaMaali();
        Ammus eri3 = new Ammus(new int[]{100, 200}, this.tila.maalit().get(1));
        assertFalse(eri3.equals(this.ammus));
    }
}
