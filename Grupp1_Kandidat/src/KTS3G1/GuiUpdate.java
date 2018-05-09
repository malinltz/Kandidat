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
    public int[] kortaste;

    public GuiUpdate(DataStore ds, ControlUI cui, OptPlan op, HTTPny http) {
        this.cui = cui;
        this.ds = ds;
        this.op = op;
        this.http = http;
        sleepTime = 1000;

        kortaste = new int[op.path.size() + 1];
        
        int i = 0;
        while (op.shortestPathList[i] != 0) {
            kortaste[i] = op.shortestPathList[i];
            i++;
        }
        kortaste[i] = http.narmstaNod2;
    }

@Override
    public void run() {
        int i = 0;
        
while(true){
            
            if (Transceiver.utfort!=null){
                cui.appendStatus("Walle har nu passerat nod"+ kortaste[i]);
                ds.passeradenoder =kortaste[i];
                if(kortaste[i] == kortaste[kortaste.length-1]){
                    //Få bågen som Wall-E befinner sig på att blinka här kanske ? lol
                    System.out.println("BREAKS");
                    break;
                }
            
                //Thread.sleep(sleepTime);

                ds.robotX = (int) (ds.nodeX[kortaste[i] - 1]);
                ds.robotY = (int) (ds.nodeY[kortaste[i] - 1]);

                i++;
                cui.repaint();

            }
            
            if(Transceiver.utfort.equals("p")){
                cui.appendStatus("Walle har nu lämnat/plockat upp passagerare");
                break;
            }
        }
        System.out.println("Wall-E är nu KLAR");*/
    

    
