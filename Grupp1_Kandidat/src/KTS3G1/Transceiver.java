package KTS3G1;

import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transceiver implements Runnable{
//String lista = "hph";  // hårdkodad sträcka för att testa h,v,r. Ska åka 2 varv på banan.

String kommando;
String inskickat = "";
String start = "s";
String pickup = "p";
public static boolean anslut = false; 
public static String utfort;
int ant_pass = 4;
String antal_passagerare;
RobotRutt RR;

 /*class kontrollen {
     boolean anslut;
     public void setValue(boolean anslut){
         this.anslut = anslut;
     }
    }
 */

public static boolean returnanslut(){
    return anslut;
}
  public Transceiver() { 
      
     /* 
      String lista = RR.rutt;
      System.out.println("\n"+"lista = "+ lista);

       while(true){
       try{
           //201410149018:1
           StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
           ansluten = true; 
          // StreamConnection anslutning = (StreamConnection) Anslutning.service;

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
                    System.out.println("AGV värmer upp..." );
                    System.out.println("T-minus " + j + " sekunder");
                  //  cui.appendStatus("AGV värmer upp, startar om " + j + "sekunder");
                    starten = false;
                   } else{
                       System.out.println("T-minus " + j + "...");
                       
                 //      cui.appendStatus(j + "...");
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
               
                
                antal_passagerare = String.valueOf(ant_pass);
                String listan =  lista + pickup;
                for(int i = 0; i < listan.length(); i++) {
                 utfort = null;
                 kommando = String.valueOf(listan.charAt(i));
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
                if(listan.equals("")){
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
          }*/ 
        }
       public void run (){

     

       while(true){
                 String lista = RR.rutt;
                 System.out.println("\n"+"lista = "+ lista);
       try{
           //201410149018:1
           StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
           anslut = true;
          // StreamConnection anslutning = (StreamConnection) Anslutning.service;

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
                    System.out.println("AGV värmer upp..." );
                    System.out.println("T-minus " + j + " sekunder");
                  //  cui.appendStatus("AGV värmer upp, startar om " + j + "sekunder");
                    starten = false;
                   } else{
                       System.out.println("T-minus " + j + "...");
                       
                 //      cui.appendStatus(j + "...");
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
               
                
                antal_passagerare = String.valueOf(ant_pass);
                String listan =  lista; // Ska vara String listan = lista + pickup; Har inga pickups under generalrep.
                for(int i = 0; i < listan.length(); i++) {
                 utfort = null;
                 kommando = String.valueOf(listan.charAt(i));
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
                if(listan.equals("")){
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
    }
