
package KTS3G1;
import java.io.*;
import java.lang.*;
import javax.microedition.io.*;
import javax.bluetooth.*;

public class Anslutning {
    
    public Anslutning(){
        try {
      // btspp://201410149018:1
        StreamConnectionNotifier service = (StreamConnectionNotifier) Connector.open("btspp://201410149018:1");
        
        Transceiver tc = new Transceiver();
        Receiver rc = new Receiver();
        
        } catch (Exception e) {
          System.out.print(e.toString());
            
        }  
       }
}
