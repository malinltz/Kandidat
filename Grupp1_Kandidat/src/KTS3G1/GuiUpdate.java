package KTS3G1;

import java.util.Random;
import java.util.Arrays;


public class GuiUpdate implements Runnable {

    private int sleepTime;
    private static Random generator = new Random();
    private ControlUI cui;
    private DataStore ds;
    private OptPlan op;
    
    public GuiUpdate(DataStore ds, ControlUI cui, OptPlan op) {
        this.cui = cui;
        this.ds = ds;
        this.op = op;
        sleepTime = 1000;
    }

    public void run() {
        try {
            
            int i = 0;
            
            int[] kortaste = op.getIndex();
            System.out.println(Arrays.toString(kortaste));
            while (kortaste[i] != 0) {
                Thread.sleep(sleepTime);

                ds.robotX = (int) (ds.nodeX[kortaste[i] - 1]);
                ds.robotY = (int) (ds.nodeY[kortaste[i] - 1]);
 
                i++;
                cui.repaint();  
            }

        } catch (InterruptedException exception) {
        }
        System.out.println("Wall-E är nu KLAR");
    }
}