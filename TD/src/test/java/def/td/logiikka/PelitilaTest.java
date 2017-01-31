package def.td.logiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    /*public void liikuJaTahtaa(){
        Paljon helpopi testata, kun torni ampuu ammuksia.
    }*/
}
