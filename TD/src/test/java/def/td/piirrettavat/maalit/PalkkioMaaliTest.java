/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.maalit;

import def.td.logiikka.Pelitila;
import def.td.piirrettavat.PolunPala;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class PalkkioMaaliTest {

    private PalkkioMaali maali;

    @Before
    public void setUp() {
        ArrayList<PolunPala> polku = new ArrayList<>();
        polku.add(new PolunPala(new int[]{100, 100}));
        polku.add(new PolunPala(new int[]{302, 100}));
        this.maali = new PalkkioMaali(polku, 5);
    }

    @Test
    public void palkkioAluksi1() {
        assertEquals(1, this.maali.getPalkkio());
    }

    @Test
    public void setPalkkioAsettaaPalkkion() {
        this.maali.setPalkkio(0);
        assertEquals(0, this.maali.getPalkkio());
        this.maali.setPalkkio(-1);
        assertEquals(0, this.maali.getPalkkio());
    }

    @Test
    public void tuhoaKasvattaaPisteita() {
        Pelitila tila = new Pelitila();
        tila.lisaaMaali(this.maali);
        int alkupisteet = tila.getPisteet();
        this.maali.setPalkkio(1);
        this.maali.tuhoa(tila);
        assertEquals(alkupisteet + 1, tila.getPisteet());
    }
}
