/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td;

/**
 *
 * @author jemisalo
 */
import def.td.logiikka.Pelitila;
import def.td.piirrettavat.Torni;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<int[]> polku = new ArrayList<>();
        polku.add(new int[]{0, 150});
        polku.add(new int[]{600, 130});
        Pelitila tila = new Pelitila(polku);
        tila.lisaaPisteet(100);
        tila.lisaaTorni(new Torni(200, 100));
        tila.tulostaTornit();
        tila.lisaaMaali();
        tila.tulostaMaalit();
        for (int i = 0; i < 50; i++) {
            tila.liiku();
            tila.tahtaa();
        }
        tila.tulostaTornit();
        tila.tulostaMaalit();
    }
}
