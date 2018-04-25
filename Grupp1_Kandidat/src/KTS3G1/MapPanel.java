package KTS3G1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.lang.Math.*;

/**
 *
 */
public class MapPanel extends JPanel {

    DataStore ds;

    MapPanel(DataStore ds) {
        this.ds = ds;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Color LIGHT_COLOR = new Color(150, 150, 150);
        final Color DARK_COLOR = new Color(0, 0, 0);
        final Color RED_COLOR = new Color(255, 0, 0);
        int x, y;
        int x1, y1;
        int x2, y2;
        

        final int circlesize = 10;
        final int ysize = 350;
        final int xsize = 700;

        if (ds.networkRead == true) { // Only try to plot is data has been properly read from file

            // Compute scale factor in order to keep the map in proportion when the window is resized
            int height = getHeight();
            int width = getWidth();
            double xscale = 1.0 * width / xsize;
            double yscale = 1.0 * height / ysize;
            
            //Ritar ut roboten
            int xRobot = (int) (ds.robotX * xscale);
            int yRobot = (int) (ds.robotY * yscale);
            g.setColor(RED_COLOR);
            g.drawOval(xRobot - ((circlesize+10) / 2), height - yRobot - (circlesize+10) / 2, circlesize + 10, circlesize + 10);

            g.setColor(DARK_COLOR);

            // Draw nodes as circles
            for (int i = 0; i < ds.nodes; i++) {
                x = (int) (ds.nodeX[i] * xscale);
                y = (int) (ds.nodeY[i] * yscale);

                g.fillOval(x - (circlesize / 2), height - y - circlesize / 2, circlesize, circlesize);
                
                //Nodnummer
                String ritaNoder = String.valueOf(ds.nodNamn[i]);
                String ritaRiktigaNoder = ritaNoder.split("\\.", 2)[0];
                
                g.drawString(ritaRiktigaNoder,x - ((circlesize-7) / 2), height - y - ((circlesize+3) / 2));
                g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
                
            }

            // Draw arcs
            for (int i = 0; i < ds.arcs; i++) {
                x1 = (int) (ds.nodeX[ds.arcStart[i] - 1] * xscale);
                y1 = (int) (ds.nodeY[ds.arcStart[i] - 1] * yscale);
                x2 = (int) (ds.nodeX[ds.arcEnd[i] - 1] * xscale);
                y2 = (int) (ds.nodeY[ds.arcEnd[i] - 1] * yscale);

                //Beräknar och ritar ut siffror på båglängden
                int dist;
                dist = (int) Math.round(Math.hypot((ds.nodeX[ds.arcStart[i] - 1] - ds.nodeX[ds.arcEnd[i] - 1]), (ds.nodeY[ds.arcStart[i] - 1] - ds.nodeY[ds.arcEnd[i] - 1])));
                
                ds.arcCost[i] = dist;
                //g.drawString(String.valueOf(dist), (x1 + x2) / 2, (height - y1 + height - y2) / 2);
                // g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

                //System.out.println("Arc " + i + ": " + ds.arcStart[i] + " " + ds.arcEnd[i]);
                if (ds.arcColor[i] == 1) {
                    g.setColor(RED_COLOR);
                    g.drawString(String.valueOf(dist), (x1 + x2) / 2, ((height - y1) + (height - y2)) / 2);
                } else {
                    g.setColor(DARK_COLOR);
                }
                g.drawLine(x1, height - y1, x2, height - y2);
            }

        }
    }// end paintComponent
}