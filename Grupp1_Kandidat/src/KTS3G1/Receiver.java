
package KTS3G1;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.io.*;
import javax.bluetooth.*;

public class Receiver implements Runnable {
    public String mottaget = null;
            
    Anslutning asg;
    
   /*   public Receiver() {
            try {
            // btspp://201410149018:1
            // StreamConnectionNotifier service = (StreamConnectionNotifier) Connector.open("btspp://localhost:" + new UUID(0x1101).toString() + ";name=TNK111-test");
       
            StreamConnectionNotifier service = (StreamConnectionNotifier) Connector.open("btspp://201410149018:1");

            StreamConnection anslutning = (StreamConnection) service.acceptAndOpen();
            InputStream bluetooth_in = anslutning.openInputStream();
            byte buffer[] = new byte[80];
            int antal_bytes = bluetooth_in.read(buffer);
            String mottaget
                    = new String(buffer,
                            0, antal_bytes);
            System.out.println("\n"+"Mottaget meddelande: "+ mottaget);
         //   cui.appendStatus(mottaget);
        anslutning.close();
        } catch (IOException e) {
            System.err.print(e.toString());
        }
    }
    
            */
    
    
    @Override
    public void run() {
        
      
        try {
           StreamConnectionNotifier service = (StreamConnectionNotifier) asg.service;
            StreamConnection anslutning = (StreamConnection) service.acceptAndOpen();
            InputStream bluetooth_in = anslutning.openInputStream();
            byte buffer[] = new byte[80];
            int antal_bytes = bluetooth_in.read(buffer);
          
            mottaget = new String(buffer,  0, antal_bytes);
            System.out.println("\n"+"Mottaget meddelande(i rc): "+ mottaget);
                  anslutning.close();
        } catch (IOException e) {
              System.err.print(e.toString());
        }
            
         //   cui.appendStatus(mottaget);

    }
    
    }




