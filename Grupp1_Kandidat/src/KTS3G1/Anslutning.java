/*package KTS3G1;
import java.io.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import javax.microedition.io.*;
import javax.bluetooth.*;

 class tradTC implements Runnable {
    public void run() {
        System.out.println("Startar Transceiver!");
        Transceiver p1 = new Transceiver(); 
    }
}

 class tradRC implements Runnable {
    public void run() {
        System.out.println("Startar Receiver!");
        Receiver p2 = new Receiver(); 
    }
}

public class Anslutning  implements Runnable {
//********* Variabler: *********************
String lista = "hhrr";
public static String kommando;
public static String inskickat;
public static StreamConnection service;

Transceiver tc;
Receiver rc;
RobotRutt rr;

// *****************************************
    public Anslutning()  {
 
       }

    @Override
    public void run() {
                try {
         // btspp://201410149018:1
            service = (StreamConnection) Connector.open("btspp://201410149018:1");
            
            tradTC t1 = new tradTC();
            Thread b1 = new Thread(t1);
            b1.start();
            
            tradRC t2 = new tradRC();
            Thread b2 = new Thread(t2);
            
            kommando = String.valueOf(lista.charAt(0));
            
            System.out.println("Kommando = " + kommando);
            System.out.println("Inskickat = " + inskickat);
            TimeUnit.MILLISECONDS.sleep(180);
            
            int i = 0;
            while(true){
                
            if (inskickat != null){
                System.out.println("Inskickat = " + inskickat);
                
            if(inskickat.equals(kommando)){
                System.out.println("Startar Receiver och avslutar Transceiver!");
                b1.interrupt(); // Stoppar Transceiver
                b2.start(); // Startar Receiver
            }
            else{
                System.out.println("If-satsen funkar ej!");
            }
            if(Receiver.mottaget != null){
                b2.interrupt(); // Stoppar Receiver
                i++;
                kommando = String.valueOf(lista.charAt(i));
                b1.start(); // Startar Transceiver
                
                
                System.out.println("Startar Transceiver och avslutar Receiver!");
                
              }    
             }
           }
        } catch (Exception e) {
          System.out.print(e.toString());
        } 
    }
}
*/

