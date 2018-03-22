package Bluetooth;


import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;

public class Transceiver{

    public static void
            main(String args[]) {
        try {
            StreamConnection anslutning = (StreamConnection) Connector.open("btspp://201410149018:1");
            PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
            BufferedReader bluetooth_in
                    = new BufferedReader(
                            new InputStreamReader(anslutning.openInputStream()));
            BufferedReader tangentbord
                    = new BufferedReader(
                            new InputStreamReader(System.in));
            while (true) {
                String meddelande_ut = tangentbord.readLine();
                if (meddelande_ut
                        == null) {
                    break;
                }
                bluetooth_ut.println(meddelande_ut);
                String meddelande_in = bluetooth_in.readLine();
                System.out.println("Mottaget: "  + meddelande_in);
            }
            anslutning.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}