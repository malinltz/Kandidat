/*package KTS3G1;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.io.*;
import javax.bluetooth.*;

public class Receiver implements Runnable {
    
      public Receiver() throws InterruptedException {
          while(Anslutning.rece) {
              System.out.println("Lyssnar...");
          try {
            
            StreamConnectionNotifier anslut = (StreamConnectionNotifier) Anslutning.service;
            StreamConnection anslutning = (StreamConnection) anslut.acceptAndOpen();
            InputStream bluetooth_in = anslutning.openInputStream();
            byte buffer[] = new byte[80];
            int antal_bytes = bluetooth_in.read(buffer);
          
            Anslutning.mottaget = new String(buffer,  0, antal_bytes);
            System.out.println("\n"+"Mottaget meddelande(i rc): "+ Anslutning.mottaget);
                  anslutning.close();
                  if(Anslutning.mottaget != null){
                      
                  }
        } catch (IOException e) {
              System.err.print(e.toString());
        }
          TimeUnit.MILLISECONDS.sleep(500);
          }
          while (Anslutning.rece == false){
          System.out.println("Sover i Receiver");
          TimeUnit.MILLISECONDS.sleep(500);
              
          }
    }
    @Override
    public void run() {
                 }
    }
*/