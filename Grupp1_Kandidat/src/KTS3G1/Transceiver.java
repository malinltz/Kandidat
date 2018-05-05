package KTS3G1;

import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transceiver implements Runnable{
RobotRutt RR;
//String lista = "hhhrrhhhrhr";
String kommando;
String inskickat = "";
String start = "s";
String pickup = "p";
int antal_passagerare;
public static String utfort;

  public Transceiver() { 
      String lista = RR.gorutt();
      System.out.println("\n"+"listan = "+ lista);
     
       while(true){
       try{
           //201410149018:1
           
           //001A7DDA7106	
           StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
 
          // StreamConnection anslutning = (StreamConnection) Anslutning.service;
        //listan =  lista + pickup;

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
                int fem = 5;
                boolean starten = true;
                for(int i = 0; i< 5;i++){
                   int j = fem - i;
                   if(starten){
                    System.out.println("AGV värmer upp, startar om " + j + "sekunder" );
                    starten = false;
                   } else{
                       System.out.println(j + "...");
                   }
                      TimeUnit.MILLISECONDS.sleep(1000); 
                }
                kommando = start;
                bluetooth_ut.print(kommando);
                TimeUnit.MILLISECONDS.sleep(50);
                inskickat = bluetooth_in.readLine();
                
                System.out.println("Skickat : "  + kommando);
                System.out.println("Mottaget : "  + inskickat);
                inskickat = "";
               
                while(true){
             // listan =  lista + pickup;
                for(int i = 0; i < lista.length(); i++) {
                 utfort = null;
                 kommando = String.valueOf(lista.charAt(i));
                 System.out.println(kommando);
                    while(true){
                         bluetooth_ut.print(kommando);
                         inskickat = bluetooth_in.readLine();
                    if(inskickat.equals(kommando)){      //Skickar vad AGV ska utföra härnäst
                      // System.out.println("Upprepar kommando");
                       TimeUnit.MILLISECONDS.sleep(100);
                    } 
                    else if(inskickat.equals("b")){          // AGV är i "point of no return"
                        System.out.println("AVG har upptäckt en skylt, PONR");
                        kommando = "w";
                    }
                    else if(inskickat.equals("k")){         // AGV är klar med kommandot. 
                         System.out.println("Avbryter while-loop och läser nästa kommando");
                         utfort = String.valueOf(lista.charAt(i));
                        break;
                    }
                    else{
                        System.out.println("Spegling funkar ej, helvete då...");
                    } 
                }
            }
                if(lista.equals("")){
                    break;
                  //  System.out.println("Uppdragslistan är tom");
                }
            }
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

