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
    public void run() {
        try {

            cui.appendStatus("Jag heter Wall-E och börjar köra");
            
            //PROBLEM!!!
            //[i]-1 jämförs med nästkommande koordinat i nodeX/nodeY. Inte nästkommande koordinat i List

            for (int i = 0; i < list.length; i++) {
                if(list[i] == 0){
                    break;
                }
                System.out.println(Arrays.toString(list)); //Noder vi ska besöka
                System.out.println("Nu " + ds.nodeX[list[i]-1]); //Nuvarande Nod X
                System.out.println("Nästa " + ds.nodeX[list[i]]); //Nästa Nod X
                System.out.println("Nu " + ds.nodeY[list[i]-1]); //Nuvarande Nod Y
                System.out.println("Nästa " + ds.nodeY[list[i]]); //Nästa Nod Y


             //   if (ds.nodeX[list[i]-1] < ds.nodeX[list[i]]) 

                if (ds.nodeX[list[i]-1] < ds.nodeX[list[i]])

                {

                    if (ds.nodeY[list[i]-1] == ds.nodeY[list[i]]) //lyssnar
                    {
                        rutt = "r"; //fortsätt framåt
                        cui.appendStatus("Fortsätt framåt");
                        //Thread.sleep(sleepTime);
                        System.out.println("VALD1");
                    }

                    if (ds.nodeY[list[i]-1] > ds.nodeY[list[i]]) //lyssnar 
                    {
                        rutt = "h"; //kör höger
                        cui.appendStatus("Kör höger");
                        System.out.println("VALD2");
                    }
                    if (ds.nodeY[list[i]-1] < ds.nodeY[list[i]]) //lyssnar 
                    {
                        rutt = "v"; //vänster
                        cui.appendStatus("Kör vänster");
                        System.out.println("VALD3");
                    }
                } 
                
                else if (ds.nodeX[list[i]-1] > ds.nodeX[list[i]])
                {
                    if (ds.nodeY[list[i]-1] == ds.nodeY[list[i]]) //lyssnar
                    {
                        rutt = "r"; //fortsätt framåt
                        cui.appendStatus("Fortsätt framåt");
                        System.out.println("VALD4");
                    }
                    if (ds.nodeY[list[i]-1] > ds.nodeY[list[i]]) //lyssnar 
                    {
                        rutt = "v"; //kör vänster
                        cui.appendStatus("Kör vänster");
                        System.out.println("VALD5");
                    }
                    if (ds.nodeY[list[i]-1] < ds.nodeY[list[i]]) //lyssnar 
                    {
                        rutt = "h"; //kör höger
                        cui.appendStatus("Kör höger");
                        System.out.println("VALD6");
                    }
                } 
                
                else if (ds.nodeY[list[i]-1] < ds.nodeY[list[i]])
                    {

                        if (ds.nodeX[list[i]-1] == ds.nodeX[list[i]]) //lyssnar
                        {
                            rutt = "r"; //fortsätt framåt
                            cui.appendStatus("Fortsätt framåt");
                            System.out.println("VALD7");
                        }
                        if (ds.nodeX[list[i]-1] > ds.nodeX[list[i]]) //lyssnar 
                        {
                            rutt = "h"; //kör höger
                            cui.appendStatus("Kör höger");
                            System.out.println("VALD8");
                        }
                        if (ds.nodeX[list[i]-1] < ds.nodeX[list[i]]) //lyssnar 
                        {
                            rutt = "v"; //vänster
                            cui.appendStatus("Kör vänster");
                            System.out.println("VALD9");
                        }
                    } 
                
                else if (ds.nodeY[list[i]-1] > ds.nodeY[list[i]])
                    {
                        if (ds.nodeX[list[i]-1] == ds.nodeX[list[i]]) //lyssnar
                        {
                            rutt = "r"; //fortsätt framåt
                            cui.appendStatus("Fortsätt framåt");
                            System.out.println("VALD10");
                        }
                        if (ds.nodeX[list[i]-1] > ds.nodeX[list[i]]) //lyssnar 
                        {
                            rutt = "v"; //kör vänster
                            cui.appendStatus("Kör vänster");
                            System.out.println("VALD11");
                        }
                        if (ds.nodeX[list[i]-1] < ds.nodeX[list[i]]) //lyssnar 
                        {
                            rutt = "h"; //kör höger
                            cui.appendStatus("Kör höger");
                            System.out.println("VALD12");
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
