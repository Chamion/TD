package def.td.logiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import def.td.piirrettavat.maalit.Maali;
import def.td.piirrettavat.PolunPala;
import def.td.piirrettavat.ammukset.Ammus;
import def.td.piirrettavat.tornit.Torni;
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
        assertEquals(0, this.tila.getPisteet());
    }

    @Test
    public void lisaaPisteetKasvattaaPisteita() {
        this.tila.lisaaPisteet(10);
        assertEquals(10, this.tila.getPisteet());
        this.tila.lisaaPisteet(-12);
        assertEquals(0, this.tila.getPisteet());
    }

    @Test
    public void lisaaPisteetEiLuoNegatiivisiaPisteita() {
        this.tila.lisaaPisteet(10);
        this.tila.lisaaPisteet(-20);
        assertEquals(0, this.tila.getPisteet());
    }

    @Test
    public void tornitAlussaTyhja() {
        assertEquals(0, this.tila.tornit().size());
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
        assertEquals(annettuSijainti[0], polunSijainti[0]);
        assertEquals(annettuSijainti[1], polunSijainti[1]);
        polunSijainti = polulla.polku().get(1).sijainti();
        annettuSijainti = sijainnit.get(1);
        assertEquals(annettuSijainti[0], polunSijainti[0]);
        assertEquals(annettuSijainti[1], polunSijainti[1]);
        polunSijainti = polulla.polku().get(2).sijainti();
        annettuSijainti = sijainnit.get(2);
        assertEquals(annettuSijainti[0], polunSijainti[0]);
        assertEquals(annettuSijainti[1], polunSijainti[1]);
    }

    @Test
    public void torniaEiLisataJosEiVaraa() {
        this.tila.lisaaTorni(new Torni(100, 100));
        assertEquals(0, this.tila.tornit().size());
    }

    @Test
    public void lisaaTorniLuoTornin() {
        Torni torni = new Torni(100, 100);
        this.tila.lisaaPisteet(torni.hinta());
        this.tila.lisaaTorni(torni);
        assertEquals(torni, this.tila.tornit().get(0));
        assertEquals(0, this.tila.getPisteet());
    }

    @Test
    public void torniaEiLisataJosEiMahdu() {
        Torni torni = new Torni(100, 100);
        this.tila.lisaaPisteet(torni.hinta() * 10);
        this.tila.lisaaTorni(torni);
        assertEquals(torni, this.tila.tornit().get(0));
        this.tila.lisaaTorni(torni);
        assertEquals(1, this.tila.tornit().size());
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        Pelitila polulla = new Pelitila(sijainnit);
        polulla.lisaaPisteet(torni.hinta() * 10);
        polulla.lisaaTorni(torni);
        assertEquals(0, polulla.tornit().size());
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
        assertEquals(1, polulla.maalit().size());
        assertEquals(sijainnit.get(0)[0], polulla.maalit().get(0).sijainti()[0]);
        assertEquals(sijainnit.get(0)[1], polulla.maalit().get(0).sijainti()[1]);
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
        assertEquals(polulla.maalit().get(0).sijainti()[0], 104);
        assertEquals(polulla.maalit().get(0).sijainti()[1], 100);
        assertEquals(polulla.ammukset().get(0).sijainti()[0], 20);
        assertEquals(polulla.ammukset().get(0).sijainti()[1], 100);
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
        assertEquals(polulla.ammukset().size(), 1);
        polulla.tahtaa();
        assertEquals(polulla.ammukset().size(), 2);
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

    @Test
    public void equalsTunnistaaSamat() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        Pelitila sama1 = new Pelitila(sijainnit);
        Pelitila sama2 = new Pelitila(sijainnit);
        assertTrue(sama2.equals(sama1));
        sama1.lisaaMaali();
        sama2.lisaaMaali();
        assertTrue(sama1.equals(sama2));
        Torni torni = new Torni(500, 500);
        torni.setHinta(0);
        sama1.lisaaTorni(torni);
        sama2.lisaaTorni(torni);
        assertTrue(sama1.equals(sama2));
        Ammus ammus = new Ammus(new int[]{300, 300}, sama1.maalit().get(0));
        sama1.lisaaAmmus(ammus);
        sama2.lisaaAmmus(ammus);
        assertTrue(sama1.equals(sama2));
    }

    @Test
    public void equalsHylkaaEri() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        ArrayList<int[]> sijainnit2 = new ArrayList<>();
        sijainnit2.add(new int[]{100, 100});
        sijainnit2.add(new int[]{200, 100});
        sijainnit2.add(new int[]{200, 200});
        Pelitila eri1 = new Pelitila(sijainnit);
        Pelitila eri2 = new Pelitila(sijainnit2);
        assertFalse(eri1.equals(eri2));

        eri1 = new Pelitila(sijainnit);
        eri2 = new Pelitila(sijainnit);
        eri1.lisaaMaali();
        eri2.lisaaMaali();
        eri1.liiku();
        assertFalse(eri1.equals(eri2));

        eri1 = new Pelitila(sijainnit);
        eri2 = new Pelitila(sijainnit);
        eri1.lisaaMaali();
        eri2.lisaaMaali();
        eri1.lisaaAmmus(new Ammus(new int[]{200, 200}, eri1.maalit().get(0)));
        eri2.lisaaAmmus(new Ammus(new int[]{300, 300}, eri2.maalit().get(0)));
        assertFalse(eri1.equals(eri2));

        eri1 = new Pelitila(sijainnit);
        eri2 = new Pelitila(sijainnit);
        Torni torni1 = new Torni(300, 300);
        torni1.setHinta(0);
        eri1.lisaaTorni(torni1);
        Torni torni2 = new Torni(400, 400);
        torni2.setHinta(0);
        eri2.lisaaTorni(torni2);
        assertFalse(eri1.equals(eri2));
    }
}
