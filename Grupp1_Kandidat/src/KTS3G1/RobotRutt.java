
package KTS3G1;

import java.util.Arrays;
import java.util.Random;
import java.util.List;

public class RobotRutt implements Runnable {

    private int sleepTime;
    private static Random generator = new Random();
    public ControlUI cui;
    public DataStore ds;
    public OptPlan op;
    public HTTPny http;
    public static String rutt = "";
    public int go;
    public int[] list;

    //Transceiver tc; 


    public RobotRutt(DataStore ds, ControlUI cui, OptPlan op, HTTPny http) {

        this.cui = cui;
        this.ds = ds;
        this.op = op;
        this.http = http;
        sleepTime = 1000; //1000 millisekunder
        
        list = new int[op.path.size()+1];
        int i = 0;
        while (op.shortestPathList[i] != 0) {
            list[i] = op.shortestPathList[i];    
            i++;
        }
        list[i] = http.narmstaNod3;
        System.out.print(" RobotRutt: " + Arrays.toString(list));
    }
    
    public void run() {
        try {
            
            cui.appendStatus("Hello, hej! Nu börjar Wall-E köra: ");
            int i = 0;
            while(i <= list.length-2){
                
                if(list[i] == list[list.length-2]){
                    break;
                }
                        
                
                if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] > 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] == 0)) //Öst
                {
                    //Kollar på nästa två noder
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i]-1] == 0)) //Kör rakt fram
                    {
                        rutt = rutt + Character.toString('r');
                        cui.appendStatus("Fortsätt framåt");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör höger
                    {
                        rutt = rutt + Character.toString('h');
                        cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör vänster
                    {
                        rutt = rutt + Character.toString('v');
                        cui.appendStatus("Kör vänster");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör framåt
                    {
                        rutt = rutt + Character.toString('r');
                        cui.appendStatus("Fortsätt framåt");
                    }
                    }        
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] < 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] == 0)) //Väst
                {
                    //Kollar på nästa två noder
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör framåt
                    {
                        rutt = rutt + Character.toString('r');
                        cui.appendStatus("Fortsätt framåt");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör höger
                    {
                        rutt = rutt + Character.toString('v');
                        cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör vänster
                    {
                        rutt = rutt + Character.toString('h');
                        cui.appendStatus("Kör vänster");
                    }
                }
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] == 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] > 0)) //Nord
                {
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör framåt
                    {
                        rutt = rutt + Character.toString('r');
                        cui.appendStatus("Fortsätt framåt");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör höger
                    {
                         rutt = rutt + Character.toString('h');
                         cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör vänster
                    {
                         rutt = rutt + Character.toString('v');
                         cui.appendStatus("Kör vänster");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Sned sväng höger
                    {
                         rutt = rutt + Character.toString('h');
                         cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Sned sväng vänster
                    {
                         rutt = rutt + Character.toString('v');
                         cui.appendStatus("Kör vänster");
                    }
                }
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] == 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] < 0)) //Söder
                {
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör framåt
                    {
                         rutt = rutt + Character.toString('r');
                         cui.appendStatus("Fortsätt framåt");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör höger
                    {
                         rutt = rutt + Character.toString('h');
                         cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör vänster
                    {
                         rutt = rutt + Character.toString('v');
                         cui.appendStatus("Kör vänster");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Sned sväng vänster
                    {
                         rutt = rutt + Character.toString('v');
                         cui.appendStatus("Kör vänster");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Sned sväng höger
                    {
                         rutt = rutt + Character.toString('h');
                         cui.appendStatus("Kör höger");
                    }
                }
                i++;
            }
/*
        int nodilistan=0;   
            while(true){
        if (Transceiver.utfort!=null){
            nodilistan++;
            cui.appendStatus("Walle har nu passerat nod"+list[nodilistan]);
           op.startupp=list[nodilistan];
        }
        if(Transceiver.utfort.equals("p")){
            nodilistan = 0;
            cui.appendStatus("Walle har nu lämnat/plockat upp passagerare");
        }
         
        }*/
 
            }catch (NumberFormatException exception) {
        }
        cui.appendStatus("Wall-E är nu klar!");
        cui.appendStatus2(rutt);
    }
    public String gorutt() { 
        return rutt;
    }
}
