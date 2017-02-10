/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.maalit;

import def.td.piirrettavat.PolunPala;
import def.td.piirrettavat.maalit.Maali;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class MaaliTest {

    private Maali maali;

    @Before
    public void setUp() {
        ArrayList<PolunPala> polku = new ArrayList<>();
        polku.add(new PolunPala(new int[]{100, 100}));
        polku.add(new PolunPala(new int[]{302, 100}));
        this.maali = new Maali(polku);
        this.maali.setNopeus(4);
    }

    @Test
    public void liikuSiirtaaMaaliaOikeanMatkan() {
        this.maali.liiku();
        assertEquals(104, this.maali.sijainti()[0]);
        assertEquals(100, this.maali.sijainti()[1]);
        this.maali.liiku();
        assertEquals(108, this.maali.sijainti()[0]);
        assertEquals(100, this.maali.sijainti()[1]);
    }

    @Test
    public void liikkuuSiirtaaMaaliaVinottainOikeanMatkan() {
        ArrayList<PolunPala> polku = new ArrayList<>();
        polku.add(new PolunPala(new int[]{100, 100}));
        polku.add(new PolunPala(new int[]{200, 200}));
        Maali vinoLiikkuja = new Maali(polku);
        vinoLiikkuja.setNopeus(4);
        vinoLiikkuja.liiku();
        assertEquals(103, vinoLiikkuja.sijainti()[0]);
        assertEquals(103, vinoLiikkuja.sijainti()[1]);
        vinoLiikkuja.setNopeus(5);
        vinoLiikkuja.liiku();
        assertEquals(107, vinoLiikkuja.sijainti()[0]);
        assertEquals(107, vinoLiikkuja.sijainti()[1]);
        vinoLiikkuja.setNopeus(6);
        vinoLiikkuja.liiku();
        assertEquals(111, vinoLiikkuja.sijainti()[0]);
        assertEquals(111, vinoLiikkuja.sijainti()[1]);
    }

    @Test
    public void liikkuuPalauttaaTrueJosMaaliOnSaavuttanutMaaranpaan() {
        for (int i = 0; i < 50; i++) {
            this.maali.liiku();
        }
        assertTrue(this.maali.liiku());
    }

    @Test
    public void maaliKuoleeKunHpOnNolla() {
        this.maali.setHp(3);
        this.maali.osuma();
        this.maali.osuma();
        assertFalse(this.maali.kuollut());
        this.maali.osuma();
        assertTrue(this.maali.kuollut());
        this.maali.osuma();
        assertTrue(this.maali.kuollut());
    }

    @Test
    public void askelPaivittyyOikeinMaalinLiikkuessaPolulla() {
        ArrayList<PolunPala> polku = new ArrayList<>();
        polku.add(new PolunPala(new int[]{100, 100}));
        polku.add(new PolunPala(new int[]{106, 100}));
        polku.add(new PolunPala(new int[]{112, 100}));
        polku.add(new PolunPala(new int[]{120, 100}));
        Maali askeltaja = new Maali(polku);
        askeltaja.setNopeus(4);
        assertEquals(0, askeltaja.getAskel());
        askeltaja.liiku();
        assertEquals(0, askeltaja.getAskel());
        askeltaja.liiku();
        assertEquals(1, askeltaja.getAskel());
        askeltaja.liiku();
        assertEquals(2, askeltaja.getAskel());
    }

    @Test
    public void equalsTunnistaaSamat() {
        ArrayList<PolunPala> polku = new ArrayList<>();
        polku.add(new PolunPala(new int[]{100, 100}));
        polku.add(new PolunPala(new int[]{302, 100}));
        Maali sama = new Maali(polku);
        sama.setNopeus(4);
        assertTrue(sama.equals(this.maali));
    }

    @Test
    public void equalsHylkaaEri() {
        ArrayList<PolunPala> polku = new ArrayList<>();
        polku.add(new PolunPala(new int[]{96, 100}));
        polku.add(new PolunPala(new int[]{100, 100}));
        polku.add(new PolunPala(new int[]{302, 100}));
        Maali eri = new Maali(polku);
        eri.setNopeus(4);
        assertFalse(eri.equals(this.maali));
        eri.liiku();
        assertFalse(eri.equals(this.maali));
    }
}
