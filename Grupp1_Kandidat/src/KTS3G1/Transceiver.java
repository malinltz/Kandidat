package KTS3G1;

import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Transceiver implements Runnable{
       public String mottaget = "";
     private  Anslutning asg;
       
        
    public Transceiver() {
       try{
           // StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
           System.out.println("Transceiver startad i public Transceiver!");
           StreamConnection anslutning = (StreamConnection) asg.service;
           System.out.println("Transceiver startad i public Transceiver2!");
           PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
           System.out.println("Transceiver startad i public Transceiver3!");
            BufferedReader bluetooth_in
                    = new BufferedReader(
                            new InputStreamReader(anslutning.openInputStream()));
            
System.out.println("Transceiver startad i public Transceiver!4");
            BufferedReader tangentbord
                    = new BufferedReader(
                            new InputStreamReader(System.in));
System.out.println("Transceiver startad i public Transceiver!5");
            while (true) {
              //String meddelande_ut = tangentbord.readLine();
              System.out.println("Transceiver startad i public Transceiver!");
                String meddelande_ut = asg.kommando;
               
              if (meddelande_ut == null) {
                    break;
                }
              System.out.println("Transceiver startad i public Transceiver6!");
                bluetooth_ut.print(meddelande_ut);
                mottaget = bluetooth_in.readLine();
                System.out.println("Skickat : "  + asg.kommando + " (i tc)");
                System.out.println("Mottaget : "  + mottaget + " (i tc)");
            }

         anslutning.close();
           
           } catch (IOException e){
                        System.out.print(e.toString());
           } 
      /*    try {
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
     */
    }
       public void run (){
       /*    try{
           System.out.println("Transceiver startad i public void run!");
           StreamConnection anslutning = (StreamConnection) asg.service;
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
                mottaget = bluetooth_in.readLine();
                System.out.println("Mottaget (i tc): "  + mottaget);
            }
          anslutning.close();
           
           } catch (IOException e){
                        System.out.print(e.toString());
           }    
           }
*/
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
}