/*
package KTS3G1;

import java.util.Random;
import java.util.Arrays;

public class GuiUpdate implements Runnable{

    private int sleepTime;
    private static Random generator = new Random();
    public ControlUI cui;
    public DataStore ds; 
    public OptPlan op;
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
        
        //RR.guiUp hämtas från RobotRutt och är en kopia av List endast med noderna som vi får ACK av.
            
            if (Transceiver.utfort != null){
                cui.appendStatus("Walle har nu passerat nod" + RR.guiUp[i]);
                ds.passeradenoder =RR.guiUp[i];
                if(RR.guiUp[i] == RR.guiUp[RR.guiUp.length-1]){
                    //Få bågen som Wall-E befinner sig på att blinka här kanske ? lol
                    System.out.println("BREAKS");
                    break;
                }
            
                //Thread.sleep(sleepTime);

                ds.robotX = (int) (ds.nodeX[RR.guiUp[i] - 1]);
                ds.robotY = (int) (ds.nodeY[RR.guiUp[i] - 1]);

                i++;
                cui.repaint();

            }
            
            if(Transceiver.utfort.equals("p")){
                cui.appendStatus("Walle har nu lämnat/plockat upp passagerare");
                break;
            }
        }
        System.out.println("Wall-E är nu KLAR");
    }
}
    

    
*/