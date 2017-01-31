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
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

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
    }
}
