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
        //sleepTime = generator.nextInt(20000);
        sleepTime = 1000;
    }

    @Override
    public void run() {
        try {
            //cui.appendStatus("GuiUpdate startar och kommer att köra i " + sleepTime + " millisekunder.");
            int i = 0;
            
            int[] kortaste = op.getIndex();

            while (i <= kortaste.length) {
                Thread.sleep(sleepTime);
                
                //cui.appendStatus("Jag är tråd GuiUpdate! För " + i + ":te gången.");
                ds.robotX = (int) (ds.nodeX[kortaste[i] - 1]);
                ds.robotY = (int) (ds.nodeY[kortaste[i] - 1]);
                
                //System.out.println(list[i]);
                i++;

                cui.repaint();
  
            }

        } catch (InterruptedException exception) {
        }
        //cui.appendStatus("GuiUpdate är nu klar!");
    }
}