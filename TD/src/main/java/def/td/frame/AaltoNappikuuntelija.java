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
 *
 * @author jemisalo
 */
public class AaltoNappikuuntelija implements ActionListener {

    private Pelilogiikka logiikka;

    public AaltoNappikuuntelija(Pelilogiikka logiikka) {
        this.logiikka = logiikka;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.logiikka.seuraavaAalto();
    }

}
