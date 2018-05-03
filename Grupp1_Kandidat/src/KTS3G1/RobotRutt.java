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
    double [] x;
    double [] y;

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
   // @Override
    public void run() {
        try {

            cui.appendStatus("Jag heter Wall-E och börjar köra");

            for (int i = 0; i < list.length; i++) {
                if(list[i] == 0){
                    break;
                }
                
                System.out.println(list[i]); //Noder vi ska besöka
                System.out.println("Nu " + ds.nodeX[list[i]-1]); //Nuvarande Nod X
                System.out.println("Nästa " + ds.nodeX[list[i+2]-1]); //Nästa Nod X
                System.out.println("Nu " + ds.nodeY[list[i]-1]); //Nuvarande Nod Y
                System.out.println("Nästa " + ds.nodeY[list[i+2]-1]); //Nästa Nod Y


                if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] > 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] == 0)) //Öst
                {
                    //Kollar på nästa två noder
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i]-1] == 0)) //Kör rakt fram
                    {
                        rutt = "r"; //fortsätt framåt
                        cui.appendStatus("Fortsätt framåt");
                        System.out.println("VALD1");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör höger
                    {
                        rutt = "h"; //kör höger
                        cui.appendStatus("Kör höger");
                        System.out.println("VALD2");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör vänster
                    {
                        rutt = "v"; //vänster
                        cui.appendStatus("Kör vänster");
                        System.out.println("VALD3");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör framåt
                    {
                        rutt = "r"; //fortsätt framåt
                        cui.appendStatus("Fortsätt framåt");
                        System.out.println("VALD4");
                    }
                    }        
                
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] < 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] == 0)) //Väst
                {
                    //Kollar på nästa två noder
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör framåt
                    {
                        rutt = "r"; //fortsätt framåt
                        cui.appendStatus("Fortsätt framåt");
                        System.out.println("VALD5");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör höger
                    {
                        rutt = "v"; //kör vänster
                        cui.appendStatus("Kör höger");
                        System.out.println("VALD6"); 
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör vänster
                    {
                        rutt = "h"; //kör höger
                        cui.appendStatus("Kör vänster");
                        System.out.println("VALD7");
                    }
                }
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] == 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] > 0)) //Nord
                {
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör framåt
                    {
                        rutt = "r"; //fortsätt framåt
                        cui.appendStatus("Fortsätt framåt");
                        System.out.println("VALD8");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör höger
                    {
                         rutt = "h"; //kör höger
                         cui.appendStatus("Kör höger");
                         System.out.println("VALD9");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör vänster
                    {
                         rutt = "v"; //vänster
                         cui.appendStatus("Kör vänster");
                         System.out.println("VALD10");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Sned sväng höger
                    {
                         rutt = "h"; //kör höger
                         cui.appendStatus("Kör höger");
                         System.out.println("VALD11");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Sned sväng vänster
                    {
                         rutt = "v"; //vänster
                         cui.appendStatus("Kör vänster");
                         System.out.println("VALD12");
                    }
                }
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] == 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] < 0)) //Söder
                {
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör framåt
                    {
                         rutt = "r"; //fortsätt framåt
                         cui.appendStatus("Fortsätt framåt");
                         System.out.println("VALD13");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör höger
                    {
                         rutt = "h"; //kör höger
                         cui.appendStatus("Kör höger");
                         System.out.println("VALD14");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör vänster
                    {
                         rutt = "v"; //vänster
                         cui.appendStatus("Kör vänster");
                         System.out.println("VALD15");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Sned sväng vänster
                    {
                         rutt = "v"; //vänster
                         cui.appendStatus("Kör vänster");
                         System.out.println("VALD16");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Sned sväng höger
                    {
                         rutt = "h"; //kör höger
                         cui.appendStatus("Kör höger");
                         System.out.println("VALD17");
                    }
                }
            }
            }catch (NumberFormatException exception) {
        }
        cui.appendStatus("Wall-E är nu klar!");
            
    }
    public String gorutt() { 

        return rutt;
    }
}