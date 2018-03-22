/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bluetooth;

import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;

public class Mirror {

    public static void
            main(String args[]) {
        try {
            StreamConnectionNotifier service = (StreamConnectionNotifier) Connector.open(
                    "btspp://localhost:"
                    + new UUID(
                            0x1101
                    ).toString()
                    + ";name=TNK111-spegel-Grupp_X"
            );
            StreamConnection anslutning = (StreamConnection) service.acceptAndOpen();
            System.out.println(
                    "Anslutning upprättad"
            );
            PrintStream bluetooth_ut
                    = new PrintStream(anslutning.openOutputStream());
            BufferedReader bluetooth_in
                    = new BufferedReader(
                            new InputStreamReader(anslutning.openInputStream()));
            while (true) {
                System.out.println(
                        "Väntar på meddelande..."
                );
                String meddelande = bluetooth_in.readLine();
                if (meddelande
                        == null) {
                    break;
                }
                bluetooth_ut.println(meddelande);
                
                System.out.println(
                        "Mottaget och returnerat: "
                        + meddelande);
            }
            anslutning.close();
        } catch (IOException e) {
            System.out.print(e.toString());
        }
    }
}