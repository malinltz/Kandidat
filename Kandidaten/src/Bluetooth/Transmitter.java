/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package Bluetooth;


import java.io.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
public class Transmitter {

 public static void main(String args[]) {
try {
StreamConnection anslutning = (StreamConnection) 
        //20:14:10:14:90:18
        Connector.open("btspp://201410149018:1");
PrintStream bluetooth_ut = new PrintStream(anslutning.openOutputStream());
  
bluetooth_ut.println("Dressed for sucess"); 
//Thread.sleep(500);
anslutning.close();
} 
catch 
(Exception e) {  System.out.print(e.toString());   }
    }
}
*/