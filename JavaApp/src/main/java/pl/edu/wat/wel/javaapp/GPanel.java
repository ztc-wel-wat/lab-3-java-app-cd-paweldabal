/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.wat.wel.javaapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author pdaba
 */
public class GPanel extends JPanel{

    public GPanel() {
        this.setPreferredSize(new Dimension(320, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(0,50,320,50);
        
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }  
}
