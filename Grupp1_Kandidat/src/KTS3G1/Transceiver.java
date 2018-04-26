package KTS3G1;

import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.lang.*;


public class Transceiver implements Runnable{
   
    
   //     private ControlUI cui;
        
    public Transceiver() {
           try {
     //      cui.appendStatus("Ansluter till AGV");
            StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
            PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
            BufferedReader bluetooth_in
                    = new BufferedReader(
                            new InputStreamReader(anslutning.openInputStream()));
            BufferedReader tangentbord
                    = new BufferedReader(
                            new InputStreamReader(System.in));
            while (true) {
                String meddelande_ut = tangentbord.readLine();
          
              if (meddelande_ut == null) {
                    break;
                }
                bluetooth_ut.print(meddelande_ut);
                String meddelande_in = bluetooth_in.readLine();
                System.out.println("Mottaget: "  + meddelande_in);
            }
           anslutning.close();
    
        } catch (Exception e) {
            System.out.print(e.toString());
            
        }
    }
       public void run (){
           /*
           PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
            BufferedReader bluetooth_in
                    = new BufferedReader(
                            new InputStreamReader(anslutning.openInputStream()));
            BufferedReader tangentbord
                    = new BufferedReader(
                            new InputStreamReader(System.in));
            while (true) {
                String meddelande_ut = tangentbord.readLine();
          
              if (meddelande_ut == null) {
                    break;
                }
                bluetooth_ut.print(meddelande_ut);
                String meddelande_in = bluetooth_in.readLine();
                System.out.println("Mottaget: "  + meddelande_in);
            }
           anslutning.close();
           */
        
    }
}
/*      HA KVAR IFALL ATT!
    public static void
            main(String args[]) {
        try {
            StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
            PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
            BufferedReader bluetooth_in  = new BufferedReader(new InputStreamReader(anslutning.openInputStream()));
            BufferedReader tangentbord = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String meddelande_ut = tangentbord.readLine();
                if (meddelande_ut
                        == null) {
                    break;
                }
                bluetooth_ut.println(meddelande_ut);
                String meddelande_in = bluetooth_in.readLine();
                System.out.println("Mottaget: "  + meddelande_in);
            }
            anslutning.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
*/