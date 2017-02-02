package def.td.logiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import def.td.piirrettavat.Maali;
import def.td.piirrettavat.PolunPala;
import def.td.piirrettavat.Torni;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class PelitilaTest {

    private Pelitila tila;

    @Before
    public void setUp() {
        this.tila = new Pelitila();
    }

    @Test
    public void pisteetAlussaNolla() {
        assertEquals(this.tila.getPisteet(), 0);
    }

    @Test
    public void lisaaPisteetKasvattaaPisteita() {
        this.tila.lisaaPisteet(10);
        assertEquals(this.tila.getPisteet(), 10);
    }

    @Test
    public void lisaaPisteetEiLuoNegatiivisiaPisteita() {
        this.tila.lisaaPisteet(10);
        this.tila.lisaaPisteet(-20);
        assertEquals(this.tila.getPisteet(), 0);
    }

    @Test
    public void tornitAlussaTyhja() {
        assertEquals(this.tila.tornit().size(), 0);
    }

    @Test
    public void KonstruktoriParametrillaLuoPolun() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        sijainnit.add(new int[]{200, 200});
        Pelitila polulla = new Pelitila(sijainnit);
        int[] polunSijainti = polulla.polku().get(0).sijainti();
        int[] annettuSijainti = sijainnit.get(0);
        assertEquals(polunSijainti[0], annettuSijainti[0]);
        assertEquals(polunSijainti[1], annettuSijainti[1]);
        polunSijainti = polulla.polku().get(1).sijainti();
        annettuSijainti = sijainnit.get(1);
        assertEquals(polunSijainti[0], annettuSijainti[0]);
        assertEquals(polunSijainti[1], annettuSijainti[1]);
        polunSijainti = polulla.polku().get(2).sijainti();
        annettuSijainti = sijainnit.get(2);
        assertEquals(polunSijainti[0], annettuSijainti[0]);
        assertEquals(polunSijainti[1], annettuSijainti[1]);
    }

    @Test
    public void torniaEiLisataJosEiVaraa() {
        this.tila.lisaaTorni(new Torni(100, 100));
        assertEquals(this.tila.tornit().size(), 0);
    }

    @Test
    public void lisaaTorniLuoTornin() {
        Torni torni = new Torni(100, 100);
        this.tila.lisaaPisteet(torni.hinta());
        this.tila.lisaaTorni(torni);
        assertEquals(this.tila.tornit().get(0), torni);
        assertEquals(this.tila.getPisteet(), 0);
    }

    @Test
    public void torniaEiLisataJosEiMahdu() {
        Torni torni = new Torni(100, 100);
        this.tila.lisaaPisteet(torni.hinta() * 10);
        this.tila.lisaaTorni(torni);
        assertEquals(this.tila.tornit().get(0), torni);
        this.tila.lisaaTorni(torni);
        assertEquals(this.tila.tornit().size(), 1);
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        Pelitila polulla = new Pelitila(sijainnit);
        polulla.lisaaPisteet(torni.hinta() * 10);
        polulla.lisaaTorni(torni);
        assertEquals(polulla.tornit().size(), 0);
    }

    @Test
    public void maalitAlussaTyhja() {
        assertTrue(this.tila.maalit().isEmpty());
    }

    @Test
    public void lisaaMaaliLuoUudenMaalinOikeaanSijaintiin() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        sijainnit.add(new int[]{200, 200});
        Pelitila polulla = new Pelitila(sijainnit);
        polulla.lisaaMaali();
        assertEquals(polulla.maalit().size(), 1);
        assertEquals(polulla.maalit().get(0).sijainti()[0], sijainnit.get(0)[0]);
        assertEquals(polulla.maalit().get(0).sijainti()[1], sijainnit.get(0)[1]);
    }

    @Test
    public void liikuSiirtaaKaikkiaLiikkuvia() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        sijainnit.add(new int[]{200, 200});
        Pelitila polulla = new Pelitila(sijainnit);
        polulla.lisaaMaali();
        polulla.lisaaAmmus(polulla.maalit().get(0), new int[]{0, 100});
        polulla.liiku();
        assertEquals(104, polulla.maalit().get(0).sijainti()[0]);
        assertEquals(100, polulla.maalit().get(0).sijainti()[1]);
        assertEquals(20, polulla.ammukset().get(0).sijainti()[0]);
        assertEquals(100, polulla.ammukset().get(0).sijainti()[1]);
    }

    @Test
    public void tahtaaSaaTorninAmpumaan() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        sijainnit.add(new int[]{200, 200});
        Pelitila polulla = new Pelitila(sijainnit);
        polulla.lisaaMaali();
        polulla.maalit().get(0).setNopeus(40);
        Torni torni = new Torni(250, 100);
        torni.setKantama(100);
        torni.setLatausAika(0);
        polulla.lisaaPisteet(torni.hinta());
        polulla.lisaaTorni(torni);
        polulla.liiku();
        polulla.tahtaa();
        assertTrue(polulla.ammukset().isEmpty());
        polulla.liiku();
        polulla.tahtaa();
        assertFalse(polulla.ammukset().isEmpty());
    }

    @Test
    public void tahtaaKasvattaaTornienLatauksia() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        Pelitila polulla = new Pelitila(sijainnit);
        polulla.lisaaMaali();
        Torni torni1 = new Torni(600, 100);
        torni1.setLatausAika(2);
        torni1.setKantama(1000);
        Torni torni2 = new Torni(100, 600);
        torni2.setLatausAika(3);
        torni2.setKantama(1000);
        polulla.lisaaPisteet(torni1.hinta() + torni2.hinta());
        polulla.lisaaTorni(torni1);
        polulla.lisaaTorni(torni2);
        polulla.tahtaa();
        assertTrue(polulla.ammukset().isEmpty());
        polulla.tahtaa();
        assertEquals(1, polulla.ammukset().size());
        polulla.tahtaa();
        assertEquals(2, polulla.ammukset().size());
    }

    @Test
    public void tuhoaMaaliTuhoaaParametrin() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        sijainnit.add(new int[]{200, 200});
        Pelitila polulla = new Pelitila(sijainnit);
        polulla.lisaaMaali();
        polulla.maalit().get(0).setNopeus(10);
        polulla.liiku();
        polulla.lisaaMaali();
        polulla.tuhoaMaali(polulla.maalit().get(1));
        assertEquals(1, polulla.maalit().size());
        assertEquals("Maali 110,100", polulla.maalit().get(0).toString());
    }

    @Test
    public void tuhoaMaaliEiTeeMitaanJosParametriEiOleTilanMaali() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        sijainnit.add(new int[]{200, 200});
        Pelitila polulla = new Pelitila(sijainnit);
        polulla.lisaaMaali();
        ArrayList<PolunPala> toinenPolku = new ArrayList<>();
        toinenPolku.add(new PolunPala(sijainnit.get(1)));
        toinenPolku.add(new PolunPala(sijainnit.get(2)));
        Maali toinen = new Maali(toinenPolku);
        polulla.tuhoaMaali(toinen);
        assertEquals(1, polulla.maalit().size());
        assertEquals("Maali 100,100", polulla.maalit().get(0).toString());
    }
}
