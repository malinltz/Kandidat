
package KTS3G1;
import java.io.*;
import java.lang.*;
import javax.microedition.io.*;
import javax.bluetooth.*;


 

public class Anslutning implements Runnable {
 Transceiver tc;
 Receiver rc;

 public StreamConnection service;
 
    public Anslutning(){
        tc = new Transceiver();
        rc = new Receiver();
    
        try {
      // btspp://201410149018:1
      // D0:C5:F3:8D:E7:3C	
         service = (StreamConnection) Connector.open("btspp://201410149018:1");
         System.out.println("KOMMER HIT!");
            Transceiver p1 = new Transceiver(); 
            Thread t4 = new Thread(p1);
            t4.start();
                        
            Receiver b2 = new Receiver();
            Thread t5 = new Thread(b2);

            while(true){
            if(tc.mottaget != null){
                 t4.interrupt();
                 t5.start();
            }
            if(rc.mottaget != null){
                t5.interrupt();
                t4.start();
            } 
        }

         
        } catch (Exception e) {
          System.out.print(e.toString());
            
        }  
       
    }
    public boolean lyssna = true;

    @Override
    public void run() {
    }
 
}


