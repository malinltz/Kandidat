
package KTS3G1;

import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transceiver implements Runnable{
//String lista = "hhvvhhrhrrh";  // hårdkodad sträcka för att testa h,v,r. Ska åka 2 varv på banan.
String kommando;
String inskickat = "";
String start = "s";
String pickup = "p";
private boolean anslut = false; 
public static volatile String utfort = "";
//int ant_pass = 4;
String antal_passagerare;
RobotRutt RR;
HTTPny http;

public Transceiver (HTTPny http){
    this.http = http;
}
       public void run (){
           
       while(true){
      
       try{
           //201410149018:1
           StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
           

           PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
            BufferedReader bluetooth_in
                    = new BufferedReader(
                            new InputStreamReader(anslutning.openInputStream()));
            
                int fem = 5;
                boolean starten = true;
                for(int i = 0; i< 5;i++){
                   int j = fem - i;
                   if(starten){
                    System.out.println("AGV värmer upp..." );
                    System.out.println("T-minus " + j + " sekunder");
                  //  cui.appendStatus("AGV värmer upp, startar om " + j + "sekunder");
                    starten = false;
                   } else{
                       System.out.println("T-minus " + j + "...");
                 //      cui.appendStatus(j + "...");
                   }
                      TimeUnit.MILLISECONDS.sleep(1000); 
                }    kommando = start;
                antal_passagerare = "1";
                while(true){
                TimeUnit.MILLISECONDS.sleep(1000);
                 String lista = RR.rutteN3;
                 System.out.println("\n"+"lista i tc = "+ lista);
                
                bluetooth_ut.print(kommando);
                TimeUnit.MILLISECONDS.sleep(50);
                inskickat = bluetooth_in.readLine();
                
            //    System.out.println("Skickat: "  + kommando);
            //    System.out.println("Mottaget: "  + inskickat);
            //    inskickat = "";
                
              //  antal_passagerare = String.valueOf(http.numPassa());
              //  System.out.println("Antalet passagerare som är i AGV: " + antal_passagerare);
              
               //while(true){

                String listan =  pickup; // Ska vara String listan = lista + pickup; Har inga pickups under generalrep.
                for(int i = 0; i < listan.length(); i++) {
                    //Thread.sleep(2000);
                 
                 kommando = String.valueOf(listan.charAt(i));
                 System.out.println(kommando);
                    while(true){
                        
                         bluetooth_ut.print(kommando);
                         inskickat = bluetooth_in.readLine();
                    if(inskickat.equals(kommando)){      //Skickar vad AGV ska utföra härnäst
                     //  System.out.println("Upprepar kommando");
                      TimeUnit.MILLISECONDS.sleep(100);
                    } 
                    else if(inskickat.equals("b")){          // AGV är i "point of no return"
                        System.out.println("AGV har upptäckt en skylt, PONR");
                //        cui.appendStatus("AGV har upptäckt en skylt, PONR");
                        kommando = "w";
                    }
                    else if(inskickat.equals("n")){  // n = numbers, AGV vill veta antalet passagerare i AGV.
                        
                        kommando = antal_passagerare; // Skickar antal passagerare
                      
                    System.out.println("Antalet passagerare = " + kommando);
                    }
                    else if(inskickat.equals("k")){         // AGV är klar med kommandot. 
                         System.out.println("Avbryter while-loop och läser nästa kommando");
                   //     cui.appendStatus("Avbryter while-loop och läser nästa kommando");
                         utfort = String.valueOf(listan.charAt(i));
                         System.out.println("Utfört = " + utfort);
                         if(utfort.equals("p")){
                             //http.test = true;
                             
                          //   System.out.println("pickUp i tc: " + http.pickUp);
                         }
                        break;
                    }
                    else if(inskickat.equals("q")){
                        System.out.println("AGV står stilla");
                        kommando = String.valueOf(listan.charAt(i));
                        System.out.println("Upprepar kommandot: " + kommando);
                        
                    }
                    else{
                        System.out.println("Spegling funkar ej...");
                    }                
                 }
                }
           }
           
        // anslutning.close();
           
           } catch (IOException e){
                        System.out.print(e.toString());
           } catch (InterruptedException ex) {
             
           } 
          }
        }
    }
