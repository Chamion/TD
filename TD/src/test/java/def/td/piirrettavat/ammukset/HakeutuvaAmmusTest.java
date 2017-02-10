/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat.ammukset;

import def.td.logiikka.Pelitila;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class HakeutuvaAmmusTest {

    private Ammus ammus;
    private Pelitila tila;

    @Before
    public void setUp() {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{100, 100});
        polku.add(new int[]{302, 100});
        this.tila = new Pelitila(polku);
        this.tila.lisaaMaali();
        this.ammus = new HakeutuvaAmmus(new int[]{100, 200}, this.tila.maalit().get(0));
    }

    @Test
    public void notEqualsMuunTyypinAmmus() {
        assertFalse(this.ammus.equals(new Ammus(new int[]{100, 200}, this.tila.maalit().get(0))));
    }
}
