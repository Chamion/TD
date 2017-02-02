/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import def.td.frame.Piirtoalusta;
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
        this.logiikka = new Pelilogiikka();
    }

    @Test
    public void setPiirtoalustaAsettaaPiirtoalustan() {
        Piirtoalusta piirtoalusta = new Piirtoalusta();
        Pelitila tila = new Pelitila();
        piirtoalusta.setPelitila(tila);
        this.logiikka.setPiirtoalusta(piirtoalusta);
        assertEquals(piirtoalusta, this.logiikka.getPiirtoalusta());
    }

}
