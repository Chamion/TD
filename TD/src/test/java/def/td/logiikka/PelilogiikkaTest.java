/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import def.td.frame.Piirtoalusta;
import def.td.piirrettavat.tornit.HaulikkoTorni;
import def.td.piirrettavat.tornit.Torni;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class PelilogiikkaTest {

    private Pelilogiikka logiikka;

    @Before
    public void setUp() {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{0, 150});
        polku.add(new int[]{600, 130});
        ArrayList<Object> aaltoSyote = new ArrayList<>();
        aaltoSyote.add("pa1/1");
        ArrayList<Aalto> aallot = new ArrayList<>();
        aallot.add(new Aalto(aaltoSyote));
        this.logiikka = new Pelilogiikka(polku,aallot);
        this.logiikka.getTila().lisaaPisteet(100);
        this.logiikka.getTila().lisaaTorni(new HaulikkoTorni(200, 100));
        this.logiikka.getTila().lisaaMaali();
        this.logiikka.getTila().maalit().get(0).setHp(10);
    }

    @Test
    public void setPiirtoalustaAsettaaPiirtoalustan() {
        Piirtoalusta piirtoalusta = new Piirtoalusta();
        Pelitila tila = new Pelitila();
        piirtoalusta.setPelitila(tila);
        this.logiikka.setPiirtoalusta(piirtoalusta);
        assertEquals(piirtoalusta, this.logiikka.getPiirtoalusta());
    }

    @Test
    public void setTilaAsettaaPelitilan() {
        this.logiikka.setTila(new Pelitila());
        assertTrue(this.logiikka.getTila().equals(new Pelitila()));
    }

    @Test
    public void tickLiikuttaa() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        sijainnit.add(new int[]{200, 200});
        Pelilogiikka liikuttaja = new Pelilogiikka(sijainnit, new ArrayList<>());
        liikuttaja.getTila().lisaaMaali();
        liikuttaja.getTila().lisaaAmmus(liikuttaja.getTila().maalit().get(0), new int[]{0, 100});
        liikuttaja.tick();
        assertEquals(104, liikuttaja.getTila().maalit().get(0).sijainti()[0]);
        assertEquals(100, liikuttaja.getTila().maalit().get(0).sijainti()[1]);
        assertEquals(20, liikuttaja.getTila().ammukset().get(0).sijainti()[0]);
        assertEquals(100, liikuttaja.getTila().ammukset().get(0).sijainti()[1]);
    }

    @Test
    public void tickTahtaa() {
        ArrayList<int[]> sijainnit = new ArrayList<>();
        sijainnit.add(new int[]{100, 100});
        sijainnit.add(new int[]{200, 100});
        Pelilogiikka tahtaaja = new Pelilogiikka(sijainnit, new ArrayList<>());
        tahtaaja.getTila().lisaaMaali();
        Torni torni = new Torni(120, 100);
        torni.setLatausAika(1);
        torni.setKantama(100);
        torni.setHinta(0);
        tahtaaja.getTila().lisaaTorni(torni);
        tahtaaja.tick();
        assertFalse(tahtaaja.getTila().ammukset().isEmpty());
    }
    
    
}
