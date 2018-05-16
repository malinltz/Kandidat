
package KTS3G1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

public class RobotRutt {

    private int sleepTime;
    private static Random generator = new Random();
    public ControlUI cui;
    public DataStore ds;
    public OptPlan op;
    public HTTPny http;
    public int go;
    public int[] list;
    public int[] list2;
    public static String rutteN3;
    public int[] guiUp;

   // public Transceiver tc; 
    ArrayList<String> rutt;

    public RobotRutt(DataStore ds, ControlUI cui, OptPlan op, HTTPny http) {

        this.cui = cui;
        this.ds = ds;
        this.op = op;
        this.http = http;
        sleepTime = 1000; //1000 millisekunder
        
        rutt = new ArrayList<String>();
        
        list = new int[op.path.size()+1];
        int j = 0;
        while (op.shortestPathList[j] != 0) {
            list[j] = op.shortestPathList[j];    
            j++;
        }
        list[j] = http.narmstaNod2;
        //list[j+1]= http.narmastNod5; 

    }
    
    public void goRobotrutt() {
        
        rutt.clear();
        
            cui.appendStatus("Hello, hej! Nu börjar Wall-E köra: ");
            int i = 0;
            while(i <= list.length-2){
                
                if(list[i] == list[list.length-2]){
                    //ds.start = http.narmstaNod;
                    break;
                }   
                
                if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] > 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] == 0)) //Öst
                {
                    //Kollar på nästa två noder
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i]-1] == 0)) //Kör rakt fram
                    {
                        rutt.add("r");
                        cui.appendStatus("Fortsätt framåt");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör höger
                    {
                        rutt.add("h");
                        cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör vänster
                    {
                        rutt.add("v");
                        cui.appendStatus("Kör vänster");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör framåt
                    {
                        rutt.add("r");
                        cui.appendStatus("Fortsätt framåt");
                    }
                    }        
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] < 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] == 0)) //Väst
                {
                    //Kollar på nästa två noder
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör framåt
                    {
                        rutt.add("r");
                        cui.appendStatus("Fortsätt framåt");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör höger
                    {
                        rutt.add("h");
                        cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör vänster
                    {
                        rutt.add("v");
                        cui.appendStatus("Kör vänster");
                    }
                }
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] == 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] > 0)) //Nord
                {
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Kör framåt
                    {
                        rutt.add("r");
                        cui.appendStatus("Fortsätt framåt");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör höger
                    {
                         rutt.add("h");
                         cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör vänster
                    {
                         rutt.add("v");
                         cui.appendStatus("Kör vänster");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Sned sväng höger
                    {
                         rutt.add("h");
                         cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Sned sväng vänster
                    {
                         rutt.add("v");
                         cui.appendStatus("Kör vänster");
                    }
                }
                
                else if((ds.nodeX[list[i+1]-1] - ds.nodeX[list[i]-1] == 0) && (ds.nodeY[list[i+1]-1] - ds.nodeY[list[i]-1] < 0)) //Söder
                {
                    if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] == 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Kör framåt
                    {
                         rutt.add("r");
                         cui.appendStatus("Fortsätt framåt");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör höger
                    {
                         rutt.add("h");
                         cui.appendStatus("Kör höger");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] == 0)) //Kör vänster
                    {
                         rutt.add("v");
                         cui.appendStatus("Kör vänster");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] > 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] > 0)) //Sned sväng vänster
                    {
                         rutt.add("v");
                         cui.appendStatus("Kör vänster");
                    }
                    else if((ds.nodeX[list[i+2]-1] - ds.nodeX[list[i+1]-1] < 0) && (ds.nodeY[list[i+2]-1] - ds.nodeY[list[i+1]-1] < 0)) //Sned sväng höger
                    {
                         rutt.add("h");
                         cui.appendStatus("Kör höger");
                    }
                }
                i++;                
            }
     
                             //Gör så att VÄNSTERsvängarna funkar i ruttkommandonen.
     for (int e = 0; e < rutt.size()-2; e++){
          int kCount = rutt.size();
        //Ersätt rvr med v
        if (rutt.get(e).equals("r") && rutt.get(e+1).equals("v") &&  rutt.get(e+2).equals("r")){
            System.out.println("1v.");
             rutt.remove(e);     //Ta bort första r:et
             rutt.remove(e+1);     //Ta bort andra r:et
             kCount = kCount - 2;
         }
        //Ersätt rv med v
        else if (rutt.get(e).equals("r") && rutt.get(e+1).equals("v")){
             System.out.println("2v.");
            rutt.remove(e);     //Ta bort r:et
            kCount = kCount - 1;
         }
        //Ersätt vr med v
          else if (rutt.get(e).equals("v") && rutt.get(e+1).equals("r")){
              System.out.println("3v.");
              rutt.remove(e+1);   //Ta bort r:et
              kCount = kCount - 1;
         } 
        if (e == kCount){            
               System.out.println("Breakat vid i 1 = " + i);
               break;
           }
     }
     
                                //Gör så att HÖGERsvängarna funkar i ruttkommandonen. 
     for (int e = 0; e < rutt.size()-2; e++){
        int kkCount = rutt.size();         
        //Ersätt rhr med h
        if (rutt.get(e).equals("r") && rutt.get(e+1).equals("h") &&  rutt.get(e+2).equals("r")){
            System.out.println("1h.");
             rutt.remove(e);     //Ta bort första r:et
             rutt.remove(e+1);     //Ta bort andra r:et
             kkCount = kkCount - 2;
         }
        //Ersätt rh med h
        else if (rutt.get(e).equals("r") && rutt.get(e+1).equals("h")){
             System.out.println("2h.");
            rutt.remove(e);     //Ta bort r:et
            kkCount = kkCount - 1;
         }
        //Ersätt hr med h
          else if (rutt.get(e).equals("h") && rutt.get(e+1).equals("r")){
              System.out.println("3h.");
              rutt.remove(e+1);   //Ta bort r:et
              kkCount = kkCount - 1;
         } 
        if (i == kkCount){            
               System.out.println("Breakat vid i 2 = " + i);
               break;
           }
     }
     System.out.println("Kortade kommandon efter: " + rutt);

     String rutteN = String.join(", ", rutt); //Gör om rutten från ArrayList till String
     
        cui.appendStatus("Wall-E är nu klar!");
        cui.appendStatus2(rutteN);
        
        String rutteN2 = rutteN.replaceAll("\\s","");

        rutteN3 = rutteN2.replace(",", "");
        System.out.println(" " + rutteN3);
    }
  }
