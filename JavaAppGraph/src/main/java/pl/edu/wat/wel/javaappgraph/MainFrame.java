/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.wat.wel.javaappgraph;

import com.fazecast.jSerialComm.SerialPort;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.QuickChart;

/**
 *
 * @author pdaba
 */
public class MainFrame extends JFrame {

    SerialPort sp = null;
    GPanel graph = null;

    public MainFrame() {
        graph = new GPanel();
        add(graph);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("JavaAppGraph");
        this.sp = connect();

        getAdcValue();
    }

    public int getAdcValue() {
        byte[] readEcho = new byte[64];
        int cmdIndex = 9;
        String cmdValues = "";
        String cmdText = "";
        boolean cmdTypeSet = false;
        String cmdResponse = "";
        boolean cmdStatus = false;

        Integer value = 0;

        String cmd = Protocol.getCmd(cmdIndex, cmdValues, cmdText, cmdTypeSet);
        sp.writeBytes(cmd.getBytes(), cmd.length());
        sp.readBytes(readEcho, cmd.length() + 4);
        // Read echo
        cmdResponse = new String(readEcho);
        cmdStatus = Protocol.checkResponse(cmdIndex, cmdResponse);
        if (cmdStatus) {
            if (cmdTypeSet) {
                System.out.println((cmdResponse.substring(cmd.length())));
            } else {
                byte[] readBuffer = new byte[32];
                // Read respond
                sp.readBytes(readBuffer, readBuffer.length);
                String cmdResp = new String(readBuffer);
                value = Integer.parseInt(cmdResp.substring(2, cmdResp.indexOf(';')).toUpperCase(), 16);
                System.out.println(cmdResp + " " + value);
            }
        }
        return value;
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();

        double[] xData = new double[]{0.0, 1.0, 2.0};
        double[] yData = new double[]{2.0, 1.0, 0.0};

        // Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

        // Show it
        var sw = new SwingWrapper(chart);
        sw.displayChart();

        while (true) {
            frame.graph.addPoint(frame.getAdcValue());
            frame.graph.repaint();
            
            yData = new double[frame.graph.point.size()];
            xData = new double[frame.graph.point.size()];
            for (int i = 0; i < frame.graph.point.size(); i++) {
                xData[i] = (double)i;
                yData[i] = (double)frame.graph.point.get(i);
            }
            
            chart.updateXYSeries("y(x)", xData, yData, null);
            sw.repaintChart();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public SerialPort connect() {
        var serialPorts = SerialPort.getCommPorts();
        SerialPort SP = SerialPort.getCommPort(serialPorts[0].getSystemPortName());
        SP.setBaudRate(115200);
        SP.setNumDataBits(8);
        SP.setNumStopBits(1);
        SP.setParity(0);
        SP.openPort();
        SP.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 50, 0);

        return SP;
    }
}
