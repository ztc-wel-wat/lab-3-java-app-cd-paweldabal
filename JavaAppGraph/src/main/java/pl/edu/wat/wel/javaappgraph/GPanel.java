/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.wat.wel.javaappgraph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author pdaba
 */
public class GPanel extends JPanel {

    public ArrayList<Integer> point;
    private Integer position = 0;

    public void addPoint(Integer value) {
        
        Integer tmp = 100 - (int)((value / Math.pow(2, 12)) * 100);
        
        if (point.size() < 320) {
            point.add(tmp);
        } else {
            point.set(position++, tmp);
            position %= 320;
        }
    }

    public GPanel() {
        this.setPreferredSize(new Dimension(320, 100));
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        point = new ArrayList();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        g2.drawLine(0, 50, 320, 50);

        g2.setColor(Color.red);
        for (int i = 0; i < point.size(); i++) {
            g2.fillOval(i, point.get(i), 2, 2);
        }
    }
}
