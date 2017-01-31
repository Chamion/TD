/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import def.td.Piirtoalusta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jemisalo
 */
public class Kello implements ActionListener {

    private final Pelitila tila;
    private final Piirtoalusta piirtoalusta;

    public Kello(Pelitila tila, Piirtoalusta piirtoalusta) {
        this.tila = tila;
        this.piirtoalusta = piirtoalusta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.tila.liiku();
        this.tila.tahtaa();
        this.piirtoalusta.repaint();
    }
}
