package def.td.piirrettavat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class TorniTest {

    private Torni torni;

    @Before
    public void setUp() {
        this.torni = new Torni(100, 100);
    }

    @Test
    public void sijaintiPalauttaaOikeanArvon() {
        assertEquals(this.torni.sijainti()[0], 100);
        assertEquals(this.torni.sijainti()[1], 100);
    }

    @Test
    public void sadeOnKymmenen() {
        assertEquals(this.torni.getSade(), 10);
    }

    @Test
    public void hintaOnYksi() {
        assertEquals(this.torni.hinta(), 1);
    }

    //Päällekkäisyystehtävät olettavat säteen olevan 10.
    @Test
    public void paallekkainTrueJosJaVainJosPaallekkainParametrinKanssa() {
        Torni toinen = new Torni(100, 100);
        assertTrue(this.torni.paallekkain(toinen));
        toinen = new Torni(80, 80);
        assertFalse(this.torni.paallekkain(toinen));
        toinen = new Torni(100, 120);
        assertFalse(this.torni.paallekkain(toinen));
        toinen = new Torni(112, 112);
        assertTrue(this.torni.paallekkain(toinen));
        toinen = new Torni(-100, -100);
        assertFalse(this.torni.paallekkain(toinen));
    }

    @Test
    public void paallekkainTrueJosPaallekkainJonkinParameterinElementinKanssa() {
        ArrayList<Torni> toiset = new ArrayList<>();
        toiset.add(new Torni(80, 80));
        toiset.add(new Torni(120, 100));
        toiset.add(new Torni(90, 100));
        assertTrue(this.torni.paallekkain(toiset));
    }

    @Test
    public void paallekkainToimiiMyosPolunPaloilla() {
        PolunPala toinen = new PolunPala(90, 100);
        assertTrue(this.torni.paallekkain(toinen));
        toinen = new PolunPala(80, 80);
        assertFalse(this.torni.paallekkain(toinen));
        toinen = new PolunPala(100, 120);
        assertFalse(this.torni.paallekkain(toinen));
        toinen = new PolunPala(112, 112);
        assertTrue(this.torni.paallekkain(toinen));
        toinen = new PolunPala(-100, -100);
        assertFalse(this.torni.paallekkain(toinen));
    }

    @Test
    public void paallekkainToimiiMyosPolunPalaListoilla() {
        ArrayList<PolunPala> toiset = new ArrayList<>();
        toiset.add(new PolunPala(80, 80));
        toiset.add(new PolunPala(120, 100));
        toiset.add(new PolunPala(90, 100));
        assertTrue(this.torni.paallekkain(toiset));
    }
}
