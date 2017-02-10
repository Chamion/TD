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
        this.ammus = new SuoraAmmus(new int[]{100, 200}, this.tila.maalit().get(0), 0, 0);
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
        this.ammus.liiku(this.tila);
        assertTrue(this.tila.maalit().get(1).kuollut());
    }
}
