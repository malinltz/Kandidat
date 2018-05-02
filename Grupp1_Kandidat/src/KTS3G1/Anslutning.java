package KTS3G1;
import java.io.*;
import java.lang.*;
import javax.microedition.io.*;
import javax.bluetooth.*;

public class Anslutning implements Runnable {
String lista = "rhhrr";
public String kommando;
public static StreamConnection service;
Transceiver tc;
Receiver rc;
    public Anslutning(){
        try {
         // btspp://201410149018:1
            service = (StreamConnection) Connector.open("btspp://201410149018:1");

            System.out.println("Startar Transceiver!");
            Transceiver p1 = new Transceiver(); 
            Thread t4 = new Thread(p1);  
            t4.start();
           
            int i = 0;
            kommando = String.valueOf(lista.charAt(i));
            System.out.println("tc.mottaget =  " + tc.mottaget);
            System.out.println(tc.mottaget);
            System.out.println("Kommando = " + kommando);
           
            if(tc.mottaget.equals(kommando)){
                System.out.println("Startar Receiver och avslutar Transceiver!");
                 t4.interrupt();
                 Receiver b2 = new Receiver();
                 Thread t5 = new Thread(b2);
                 t5.start();
            }
            else
                System.out.println("If-satsen funkar ej!");
            if(rc.mottaget != null){
                i++;
                System.out.println("Startar Transceiver och avslutar Receiver!");
                t4.start();
            }       
        } catch (Exception e) {
          System.out.print(e.toString());
        }  
       }
    @Override
    public void run() {
    }
 
}


