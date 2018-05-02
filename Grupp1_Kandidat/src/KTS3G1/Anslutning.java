
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
    
        try {
      // btspp://201410149018:1
         service = (StreamConnection) Connector.open("btspp://201410149018:1");
         rc.run();
         tc.run();
         service.close();
         
  

        } catch (Exception e) {
          System.out.print(e.toString());
            
        }  
       
    }
    public boolean lyssna = true;

    @Override
    public void run() {
    }
 
}


