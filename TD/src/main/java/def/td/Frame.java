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
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

public class Frame implements Runnable {

    private JFrame frame;

    public Frame() {

    }

    @Override
    public void run() {
        this.frame = new JFrame();
        this.frame.setPreferredSize(new Dimension(600, 600));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.luoKomponentit(frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        Piirtoalusta piirtoalusta = new Piirtoalusta();
        container.add(piirtoalusta);
    }

}
