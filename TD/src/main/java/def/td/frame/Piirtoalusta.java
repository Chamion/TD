/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.frame;

import def.td.logiikka.Pelitila;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Canvas-luokka, johon pelin graafinen esitys piirtyy.
 */
public class Piirtoalusta extends JPanel {

    private Pelitila tila;

    public Piirtoalusta() {
        super.setBackground(Color.WHITE);
    }

    public void setPelitila(Pelitila tila) {
        this.tila = tila;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.tila.piirra(graphics);
        this.piirraValikko(graphics);
    }
    
    private void piirraValikko(Graphics graphics){
        graphics.setColor(Color.black);
        graphics.drawRect(0, 500, 100, 100);
        graphics.drawRect(100, 500, 100, 100);
        graphics.drawRect(200, 500, 100, 100);
        graphics.drawString("pisteet: "+this.tila.getPisteet(), 400, 550);
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            return this.equals((Piirtoalusta) o);
        } else {
            return false;
        }
    }

    private boolean equals(Piirtoalusta p) {
        return this.toString().equals(p.toString());
    }
}
