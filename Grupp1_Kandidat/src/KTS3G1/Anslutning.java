/*

package KTS3G1;
import java.io.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.io.*;
import javax.bluetooth.*;

public class Anslutning  implements Runnable {
    
//********* Variabler: *********************
String lista = "ghhrr";
public static String kommando;
public static String inskickat;
boolean kontroll = false;
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

        
        service = (StreamConnection) Connector.open("btspp://201410149018:1");
        kommando = String.valueOf(lista.charAt(0));
        Transceiver trc = new Transceiver();
        Thread b1 = new Thread(trc);
        b1.start();
        
            for(int i = 0; i < lista.length(); i++) {
            kommando = String.valueOf(lista.charAt(i));
            TimeUnit.MILLISECONDS.sleep(50);
            if(inskickat.equals(kommando)){
                while(kontroll){
                    kommando = "f";
                    
                    if(inskickat.equals("p")){
                       kommando = "f";
                    }
                    else if(inskickat.equals("k")){
                        kommando = "f";
                    }
                    else if(inskickat.equals("b")){
                        break;
                    }
                        TimeUnit.MILLISECONDS.sleep(50);
                }
                    
                
            }else
                System.out.println("Inskickat = " + inskickat + " Kommando = " + kommando);
        }
        
    } catch (IOException | InterruptedException ex ) {
        
        }
    }
}


*/
