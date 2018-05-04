package KTS3G1;



import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transceiver implements Runnable{
    
String lista = "hrrhrr";
public static String kommando;
public static String inskickat = "";

       
   public Transceiver() {
       
       while(true){
       try{
           StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");

          // StreamConnection anslutning = (StreamConnection) Anslutning.service;
        kommando = String.valueOf(lista.charAt(0));
        
           PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
        
            BufferedReader bluetooth_in
                    = new BufferedReader(
                            new InputStreamReader(anslutning.openInputStream()));
           
         //   BufferedReader tangentbord
           //         = new BufferedReader(
             //               new InputStreamReader(System.in));

           // while (true) {
              //String meddelande_ut = tangentbord.readLine();

              //  String meddelande_ut = Anslutning.kommando;
               
             // if (meddelande_ut == null) {
              //      break;
            //    }
            

                bluetooth_ut.print(kommando);
                inskickat = bluetooth_in.readLine();
                
                System.out.println("Skickat : "  + kommando);
                System.out.println("Mottaget : "  + inskickat);
                
                for(int i = 0; i < lista.length(); i++) {
                 kommando = String.valueOf(lista.charAt(i));
                    while(true){
                         
                         bluetooth_ut.print(kommando);
                         inskickat = bluetooth_in.readLine();
                    if(inskickat.equals(kommando)){      //Skickar vad AGV ska utföra härnäst
                       System.out.println("Upprepar kommando");
                       TimeUnit.MILLISECONDS.sleep(1000);
                    } else{
                        System.out.println("Spegling funkar ej, helvete då...");
                    } 
                        
                    if(inskickat.equals("b")){          // AGV är i "point of no return"
                        System.out.println("AVG har upptäckt en skylt, PONR");
                       kommando = "w";
                       
                    }
                     if(inskickat.equals("k")){         // AGV är klar med kommandot. 
                         System.out.println("Avbryter while-loop och läser nästa kommando");
                        break;
                    }
                     
                }
            }
                
                System.out.println("Skickat2 : "  + kommando);
                System.out.println("Mottaget2 : "  + inskickat);
           // }

         anslutning.close();
           
           } catch (IOException e){
                        System.out.print(e.toString());
           } catch (InterruptedException ex) {
               Logger.getLogger(Transceiver.class.getName()).log(Level.SEVERE, null, ex);
           } 
             }
        }
       public void run (){
        }
    }