package KTS3G1;

import java.util.Random;


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

            while (i <= kortaste.length-1) {
                Thread.sleep(sleepTime);
                
                if(kortaste[i] == 0)
                {
                    System.out.println("Wall-E KLAR");
                    break;
                }

                ds.robotX = (int) (ds.nodeX[kortaste[i] - 1]);
                ds.robotY = (int) (ds.nodeY[kortaste[i] - 1]);
                
                //int[] tillbakaX =  ds.robotX;
                //int[] tillbakaY =  ds.robotY;
                
               // System.out.println(kortaste.length);
               //Om du får felmeddelanden: 
               //Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: -1
               //at KTS3G1.GuiUpdate.run(GuiUpdate.java:34)
               //at java.lang.Thread.run(Thread.java:748)
               //Antaglien för att kortaste.length=1000
                i++;
                cui.repaint();  
            }

        } catch (InterruptedException exception) {
        }
        //cui.appendStatus("GuiUpdate är nu klar!");
    }
}