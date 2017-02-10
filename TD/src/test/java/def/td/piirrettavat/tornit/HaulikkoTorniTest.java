/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.tornit;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.ammukset.SuoraAmmus;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class HaulikkoTorniTest {

    private Torni torni;

    @Before
    public void setUp() {
        this.torni = new HaulikkoTorni(100, 100);
    }

    @Test
    public void ammuLuoOikeanAmmuksen() {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{0, 100});
        polku.add(new int[]{300, 100});
        Pelitila tila = new Pelitila(polku);
        tila.lisaaMaali();
        tila.lisaaPisteet(this.torni.hinta());
        tila.lisaaTorni(this.torni);
        this.torni.ammu(tila.maalit().get(0), tila);
        assertEquals(new SuoraAmmus(this.torni.sijainti(), tila.maalit().get(0), 0, 0), tila.ammukset().get(0));
    }

    @Test
    public void notEqualsMuunTyypinTorni() {
        assertFalse(this.torni.equals(new Torni(this.torni.sijainti()[0], this.torni.sijainti()[1])));
    }
}
