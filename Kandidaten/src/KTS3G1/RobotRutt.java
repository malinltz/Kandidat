package KTS3G1;
/*
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
        sleepTime = generator.nextInt(20000);
        sleepTime = 1000; //1000 millisekunder

    }

    @Override
    public void run() {
        try {

            list = op.getIndex();
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

    public void rutt() {
    
    for (int i = 0; i < ds.nodes ;i ++) 
        
        if (ds.nodeX[list[i]]< ds.nodeX[list[i+11]) //varför 11?
        {
            if (rutt== "m") //nästa höger
            {}
            if(rutt== "z") //lyssnar 
            {}
            if (rutt== "n")//nästa vänster
            {}
            if (rutt== "o")//stanna för passagerare
            {}
            if (rutt== "p")//nollställ alla instruktioner
            {}
           }
    
    else if (ds.nodeX[list[i]]< ds.nodeX[list[i+11]{
         if (rutt== "m") //nästa höger
            {}
            if(rutt== "z") //lyssnar 
            {}
            if (rutt== "n")//nästa vänster
            {}
            if (rutt== "o")//stanna för passagerare
            {}
            if (rutt== "p")//nollställ alla instruktioner
            {}
    }        
        }
    
     for (int i = 0; i < ds.nodeY ;i ++) {}
  
  
  
  
  
    public String gorutt() {

        return rutt;
    }
}
*/