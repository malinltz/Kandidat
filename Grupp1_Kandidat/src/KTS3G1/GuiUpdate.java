/*
package KTS3G1;

import java.util.Random;
import java.util.Arrays;

public class GuiUpdate implements Runnable{

    private int sleepTime;
    private static Random generator = new Random();
 //   public ControlUI cui;
    public DataStore ds; 
    public OptPlan op;
 //   public Transceiver tr;
    public HTTPny http;
    public RobotRutt RR;
    public int[] kortaste;

    public GuiUpdate(DataStore ds, ControlUI cui, OptPlan op, HTTPny http) {
        this.cui = cui;
        this.ds = ds;
        this.op = op;
        this.http = http;
        sleepTime = 1000;
    }

@Override
    public void run() {
        
        int i = 0;
        
        
    while(true){ //Behövs ingen try-catch om inte Sleeptime används.
        

            /*
            if (Transceiver.utfort != null){
                cui.appendStatus("Walle har nu passerat nod" + RR.guiUp2[i]);
                ds.passeradenoder =RR.guiUp2[i];
                if(RR.guiUp[i] == RR.guiUp2[RR.guiUp2.length-1]){
                    //Få bågen som Wall-E befinner sig på att blinka här kanske ? lol
                    System.out.println("BREAKS");
                    
                    break;
                }
            
                //Thread.sleep(sleepTime);

                ds.robotX = (int) (ds.nodeX[RR.guiUp2[i] - 1]);
                ds.robotY = (int) (ds.nodeY[RR.guiUp2[i] - 1]);

                i++;
                cui.repaint();

            }
        
            
           if(Transceiver.utfort.equals("p")){
               cui.appendStatus("Wall-E har nu lämnat/plockat upp passagerare");
                
               ds.robotX = (int) (ds.nodeX[ds.slut - 1]);
                ds.robotY = (int) (ds.nodeY[ds.slut - 1]);
                cui.repaint();
                break;
            }        
        }
    }
}
*/