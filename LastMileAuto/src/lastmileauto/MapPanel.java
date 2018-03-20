package lastmileauto;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
// import 

/**
 *
 * @author clary35
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

            g.setColor(RED_COLOR);

            // Draw nodes as circles
            for (int i = 0; i < ds.nodes; i++) {
                x = (int) (ds.nodeX[i] * xscale);
                y = (int) (ds.nodeY[i] * yscale);
                
                g.fillOval(x - (circlesize / 2), height - y - circlesize / 2, circlesize, circlesize);
            }

            // Draw arcs
            for (int i = 0; i < ds.arcs; i++) {
                x1 = (int) (ds.nodeX[ds.arcStart[i] - 1] * xscale);
                y1 = (int) (ds.nodeY[ds.arcStart[i] - 1] * yscale);
                x2 = (int) (ds.nodeX[ds.arcEnd[i] - 1] * xscale);
                y2 = (int) (ds.nodeY[ds.arcEnd[i] - 1] * yscale);
               // ds.arcCost = String.valueOf(ds.arcs); 
                g.drawLine(x1, height - y1, x2, height - y2);
                //g.drawString("" +ds.arcCost[i], (x1,x2 ); 
                //Int 
                System.out.println("Arc "+i+": "+ds.arcStart[i]+" "+ds.arcEnd[i]);
                //System.out.println("Nodes "+i+": "+ds.arcStart[i]+" "+ds.arcEnd[i]);
                
             
                
             
            }
        }
    } // end paintComponent
}