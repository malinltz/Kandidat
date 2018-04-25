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
        sleepTime = 1000; //1000 millisekunder
    }

    @Override
    public void run() {
        
        try {
            //cui.appendStatus("GuiUpdate startar och kör i " + sleepTime + " ms.");
            
            int i = 0;
            
          

                while (i < op.shortestPathList.length-1) {
                    Thread.sleep(sleepTime);
                    //cui.appendStatus("För " + i + ":te gången");
//                    ds.robotX = (int) ds.nodeX[op.shortestPathList[i] - 1];
//                    ds.robotY = (int) ds.nodeY[op.shortestPathList[i] - 1];
  //                  System.out.println(ds.robotX);
    //                System.out.println(ds.robotX);
                    i++;
                    cui.repaint();
                   //System.out.println(op.shortestPathList[i]);
                   // System.out.println(ds.nodeX);
                    
                }
            
        } catch (InterruptedException exception) {
        }
        //cui.appendStatus("GuiUpdate är nu klar!");
        }
    
    public void Gui()
    {
    
        
    }
    
    
}
