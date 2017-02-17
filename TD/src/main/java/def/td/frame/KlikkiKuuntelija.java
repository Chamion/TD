/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.frame;

import def.td.logiikka.Pelilogiikka;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author jemisalo
 */
public class KlikkiKuuntelija implements MouseListener {

    private Pelilogiikka logiikka;

    public KlikkiKuuntelija(Pelilogiikka logiikka) {
        this.logiikka = logiikka;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        this.logiikka.click(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }

}
