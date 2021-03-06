/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.frame;

import def.td.logiikka.Pelilogiikka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Event Listener ajastimelle.
 */
public class Kello implements ActionListener {

    /**
     * Pelilogiikka, jonka tick-metodia Kello kutsuu.
     */
    private final Pelilogiikka logiikka;

    /**
     * Konstruktorille annetaan Pelilogiikka argumenttina.
     *
     * @param logiikka Pelilogiikka, jonka tick-metodia Kello kutsuu.
     */
    public Kello(Pelilogiikka logiikka) {
        this.logiikka = logiikka;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.logiikka.tick();
    }
}
