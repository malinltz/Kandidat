package KTS3G1;

import java.util.Random;
import java.util.List;

public class RobotRutt implements Runnable {

    private int sleepTime;
    private static Random generator = new Random();
    private ControlUI cui;
    private DataStore ds;
    private OptPlan op;
    public String rutt;
    public int[] list;
    public int go;

    public RobotRutt(DataStore ds, ControlUI cui, OptPlan op) {

        this.cui = cui;
        this.ds = ds;
        this.op = op;
        //sleepTime = generator.nextInt(20000);
        sleepTime = 1000; //1000 millisekunder

        list = op.getIndex();
    }

    /*

        list = op.getIndex();
    
   }

    @Override
    public void run() {
        try {


            cui.appendStatus("Jag heter Wall-E och kommer att köra i " + sleepTime + "millisekunder.");
            int i = 1;
            while (i < 10) {
                Thread.sleep(sleepTime / 10);
                cui.appendStatus("Jag är Wall-E! För " + i + ":te gången.");
                i++;

                while (i <= 20) {
                    Thread.sleep(sleepTime / 10);
                    cui.appendStatus("Jag är Wall-E ! För " + i + ":te gången.");
                    ds.updateUIflag = true;
                    i++;

                }
            }

        } catch (InterruptedException exception) {
        }
        cui.appendStatus("Wall-E är nu klar!");

    }

}
/*
    public void rutt() {
    

     */
    
    //vad ska jämföra med? listan och nästa steg eller med vad det är?

    @Override
    public void run() {
        try {

            cui.appendStatus("Jag heter Wall-E och börjar köra");

            for (int i = 0; i < ds.nodes; i++) {
                if (ds.nodeX[list[i]] < ds.nodeX[i+11]) //varför 11?
                {

                    if (ds.nodeX[list[i]] == ds.nodeX[list[i] + 1]) {
                        rutt = "r"; //fortsätt framåt
                        cui.appendStatus("Fortsätt framåt");
                        //Thread.sleep(sleepTime);
                    }
                    if (ds.nodeX[list[i]] > ds.nodeX[i + 1]) //lyssnar 
                    {
                        rutt = "h"; //kör höger
                        cui.appendStatus("Kör höger");
                    }
                    if (ds.nodeX[list[i]] < ds.nodeX[list[i] + 1]) //lyssnar 
                    {
                        rutt = "v"; //vänster
                        cui.appendStatus("Kör vänster");
                    }
                } else if (ds.nodeX[list[i]] > ds.nodeX[list[i] + 1])//nästa vänster
                {
                    if (ds.nodeX[list[i]] == ds.nodeX[list[i]] + 1) {
                        rutt = "r"; //fortsätt framåt
                        cui.appendStatus("Fortsätt framåt");
                    }
                    if (ds.nodeX[list[i]] > ds.nodeX[list[i] + 1]) //lyssnar 
                    {
                        rutt = "v"; //kör vänster
                        cui.appendStatus("Kör vänster");
                    }
                    if (ds.nodeX[list[i]] < ds.nodeX[list[i] + 1]) //lyssnar 
                    {
                        rutt = "h"; //kör höger
                        cui.appendStatus("Kör höger");
                    } else if (ds.nodeY[list[i]] < ds.nodeY[list[i] + 1]) //varför 11?
                    {

                        if (ds.nodeY[list[i]] == ds.nodeY[list[i] + 1]) {
                            rutt = "r"; //fortsätt framåt
                            cui.appendStatus("Fortsätt framåt");
                        }
                        if (ds.nodeY[list[i]] > ds.nodeY[list[i] + 1]) //lyssnar 
                        {
                            rutt = "h"; //kör höger
                            cui.appendStatus("Kör höger");
                        }
                        if (ds.nodeY[list[i]] < ds.nodeY[list[i] + 1]) //lyssnar 
                        {
                            rutt = "v"; //vänster
                            cui.appendStatus("Kör vänster");
                        }
                    } else if (ds.nodeY[list[i]] > ds.nodeY[list[i] + 1])//nästa vänster
                    {
                        if (ds.nodeY[list[i]] == ds.nodeY[list[i]] + 1) {
                            rutt = "r"; //fortsätt framåt
                            cui.appendStatus("Fortsätt framåt");
                        }
                        if (ds.nodeY[list[i]] > ds.nodeY[list[i] + 1]) //lyssnar 
                        {
                            rutt = "v"; //kör vänster
                            cui.appendStatus("Kör vänster");
                        }
                        if (ds.nodeY[list[i]] < ds.nodeY[list[i] + 1]) //lyssnar 
                        {
                            rutt = "h"; //kör höger
                            cui.appendStatus("Kör höger");
                        }

                    }

                }
            }
        } catch (NumberFormatException exception) {
        }
        cui.appendStatus("Wall-E är nu klar!");

    }

    public String gorutt() {

        return rutt;
    }
}
