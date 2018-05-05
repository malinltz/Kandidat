package KTS3G1;

import java.util.Arrays;
import java.util.Random;
import java.util.List;

public class RobotRutt implements Runnable {

    private int sleepTime;
    private static Random generator = new Random();
    private ControlUI cui;
    private DataStore ds;
    private OptPlan op;
    public static String rutt = "";
    public int[] list;
    public int go;
    //Transceiver tc; 

    public RobotRutt(DataStore ds, ControlUI cui, OptPlan op) {

        this.cui = cui;
        this.ds = ds;
        this.op = op;
        sleepTime = 1000; //1000 millisekunder

        list = op.getIndex();
    }


    public void run() {
        try {
            
            cui.appendStatus("Hello, hej! Nu börjar Wall-E köra: ");
            int i = 0;
            while(list[i+2] != 0){

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
            }catch (NumberFormatException exception) {
        }
        cui.appendStatus("Wall-E är nu klar!");
        cui.appendStatus2(rutt);
    }
    public String gorutt() { 
        return rutt;
    }

//    public String passeradenoder(){
//        while(true){
//        int nodilistan=0;    
//        
//        if (Transceiver.utfort!=null){
//            nodilistan++;
//            cui.appendStatus("Walle har nu passerat nod"+list[nodilistan]);
//           
//        }
//        }
//    }

}

