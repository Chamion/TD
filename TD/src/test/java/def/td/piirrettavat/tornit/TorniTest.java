package def.td.piirrettavat.tornit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    //Päällekkäisyystestit olettavat säteen olevan 10.
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

    @Test
    public void setHintaMuuttaaHintaa() {
        if (this.torni.hinta() != 1) {
            this.torni.setHinta(1);
            assertEquals(1, this.torni.hinta());
        } else {
            this.torni.setHinta(2);
            assertEquals(2, this.torni.hinta());
        }
    }

    @Test
    public void setLatausAikaMuuttaaVaadittuaLatausta() {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{0, 100});
        polku.add(new int[]{300, 100});
        Pelitila tila = new Pelitila(polku);
        tila.lisaaMaali();
        tila.lisaaPisteet(this.torni.hinta());
        tila.lisaaTorni(this.torni);
        tila.tornit().get(0).setLatausAika(99999);
        tila.maalit().get(0).setNopeus(12);
        for (int i = 1; i < 10; i++) {
            tila.liiku();
            tila.tahtaa();
            assertTrue(tila.ammukset().isEmpty());
        }
        tila.tornit().get(0).setLatausAika(0);
        tila.liiku();
        tila.tahtaa();
        assertFalse(tila.ammukset().isEmpty());
    }
    
    @Test
    public void equalsTunnistaaSamat(){
        Torni sama = new Torni(100, 100);
        assertTrue(sama.equals(this.torni));
    }
    
    @Test
    public void equalsHylkaaEri(){
        Torni eri = new Torni(100,99);
        assertFalse(eri.equals(this.torni));
        assertFalse(this.torni.equals(new PolunPala(100,100)));
    }
}
