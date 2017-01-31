/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

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
        assertEquals(this.maali.sijainti()[0], 104);
        assertEquals(this.maali.sijainti()[1], 100);
        this.maali.liiku();
        assertEquals(this.maali.sijainti()[0], 108);
        assertEquals(this.maali.sijainti()[1], 100);
    }

    @Test
    public void liikkuuSiirtaaMaaliaVinottainOikeanMatkan() {
        ArrayList<PolunPala> polku = new ArrayList<>();
        polku.add(new PolunPala(new int[]{100, 100}));
        polku.add(new PolunPala(new int[]{200, 200}));
        Maali vinoLiikkuja = new Maali(polku);
        vinoLiikkuja.setNopeus(4);
        vinoLiikkuja.liiku();
        assertEquals(vinoLiikkuja.sijainti()[0], 103);
        assertEquals(vinoLiikkuja.sijainti()[1], 103);
        vinoLiikkuja.setNopeus(5);
        vinoLiikkuja.liiku();
        assertEquals(vinoLiikkuja.sijainti()[0], 107);
        assertEquals(vinoLiikkuja.sijainti()[1], 107);
        vinoLiikkuja.setNopeus(6);
        vinoLiikkuja.liiku();
        assertEquals(vinoLiikkuja.sijainti()[0], 111);
        assertEquals(vinoLiikkuja.sijainti()[1], 111);
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
        assertEquals(askeltaja.getAskel(), 0);
        askeltaja.liiku();
        assertEquals(askeltaja.getAskel(), 0);
        askeltaja.liiku();
        assertEquals(askeltaja.getAskel(), 1);
        askeltaja.liiku();
        assertEquals(askeltaja.getAskel(), 2);
    }
}
