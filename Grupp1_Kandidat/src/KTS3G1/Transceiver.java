package KTS3G1;

import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transceiver implements Runnable{
       
   public Transceiver() {
       try{
           // StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
 
           StreamConnection anslutning = (StreamConnection) Anslutning.service;
        
           PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
        
            BufferedReader bluetooth_in
                    = new BufferedReader(
                            new InputStreamReader(anslutning.openInputStream()));
            

            BufferedReader tangentbord
                    = new BufferedReader(
                            new InputStreamReader(System.in));

           // while (true) {
              //String meddelande_ut = tangentbord.readLine();

                String meddelande_ut = Anslutning.kommando;
               
             // if (meddelande_ut == null) {
              //      break;
            //    }
                bluetooth_ut.print(Anslutning.kommando);
                Anslutning.inskickat = bluetooth_in.readLine();
                System.out.println("Skickat : "  + Anslutning.kommando + " (i tc)");
                System.out.println("Mottaget : "  + Anslutning.inskickat + " (i tc)");
           // }

         anslutning.close();
           
           } catch (IOException e){
                        System.out.print(e.toString());
           } 
    }
       public void run (){
        }
    }