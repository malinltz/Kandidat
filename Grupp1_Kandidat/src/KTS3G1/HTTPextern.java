/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KTS3G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.List;
import java.io.DataOutputStream;
//import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HTTPextern {

    public OptPlan op;
    public DataStore ds;
    public HTTPny http;
    int sleepTime;
    int capacity = 3; //exempel på kapacitet
    int ID = 1;
    int[] messagetyp;
    private String[] dummer;
    //  private String [] platserna;
    // String url;
    String[] uppdragViFick;
    String uppdragViFick2;
    String dummy;
    String message;
    String test;
    int dist = 0;
    public String plats;
    int platsgrupp;
    //int langd=3; 
    int capacitet4 = 20; //fixt capacitet för grupp 4
    int capacitet5 = 50; //fixt capacitet för grupp 5
    int capacitet1 = 60; //fixt capacitet för grupp 1(vår grupp)

    ArrayList<String> uppdragG1;
    ArrayList<String> uppdragG4;
    ArrayList<String> uppdragG5;
    ArrayList<String> ejUppdrag;

    public HTTPextern(HTTPny http, DataStore ds) {

        this.http = http;
        this.ds = ds;
        dummy = "";
        uppdragG1 = new ArrayList<String>();
        uppdragG4 = new ArrayList<String>();
        uppdragG5 = new ArrayList<String>();
        ejUppdrag = new ArrayList<String>();
    }

    public void exprotokoll() {

        
        for (int i = 0; i < http.meddelandet; i++) {
            dummer = http.uppdrag[i].split(",");
            if (http.iD[i] == 1) {
                for (int j = 0; j < dummer.length; j++) {
                    uppdragG1.add((dummer[j]));
                }
            } //Ändra ordning här breoende på ordning i HTTP
            else if (http.iD[i] == 4) {
                for (int j = 0; j < dummer.length; j++) {
                    uppdragG4.add((dummer[j]));
                }
            } else if (http.iD[i] == 5) {
                for (int j = 0; j < dummer.length; j++) {
                    uppdragG5.add((dummer[j]));
                }
            }
        }

        System.out.println("Grupp 1 Uppdrag" + uppdragG1);
        System.out.println("Grupp 4 Uppdrag" + uppdragG4);
        System.out.println("Grupp 5 Uppdrag" + uppdragG5);
        
        ejUppdrag.clear();
        for (int i = 0; i < 3; i++) //Tre grupper.
            
        { //kollar vilken plats som är bäst
            
            if (http.iD[i] != 1) //Får felmeddelande för att ID=1 (vår grupp) är den enda som ligger uppe.
            {
                //Jämför om fler grupper vill ha samma plats.
                if (http.paxplats[i] == http.paxplats[1]) {
                    
                    //Kolla hur det är med kostnaderna
                   if (Integer.parseInt(http.kostnad[i]) < Integer.parseInt(http.kostnad[0])) { 
                        
                       //Lägga till här att vi inte kan få den uppdragsplatsen.
                       if (i == 0) {
                          for (int j = 0; j < uppdragG4.size(); j++) {
                             if (!ejUppdrag.contains(j)) {
                                  ejUppdrag.add(String.valueOf(j));

                                 }
                                }
                            } else if (i == 2) {
                          for (int j = 0; j < uppdragG5.size(); j++) {
                             if (!ejUppdrag.contains(Integer.parseInt(uppdragG5.get(j)))) {
                                 ejUppdrag.add(uppdragG5.get(j));

                                 }
                                }
                            }
                    }
                   else if (Integer.parseInt(http.kostnad[i]) == Integer.parseInt(http.kostnad[0])) {
                       
                        //Då vill vi jämföra grupp-ID
                        if (http.iD[i] < http.iD[0]) {
                            
                           //Lägga till här att vi inte kan få den uppdragsplatsen.
                       if (i == 0) {
                          for (int j = 0; j < uppdragG4.size(); j++) {
                             if (!ejUppdrag.contains(j)) {
                                  ejUppdrag.add(String.valueOf(j));

                                 }
                                }
                            } else if (i == 2) {
                          for (int j = 0; j < uppdragG5.size(); j++) {
                             if (!ejUppdrag.contains(uppdragG5.get(j))) {
                                 ejUppdrag.add(uppdragG5.get(j));

                                 }
                                }
                            }
                        }
                   else{
                           System.out.println("Vi fick uppdraget!");
                        }
                    }
                   
                }
                else {
                    uppdragViFick2 = http.narmstaPlats;
                }
            }
        }
        //Gör om till ArrayList
      List<String> wordList = Arrays.asList(http.uppdrag);  
      
 
        //Här tar den bort de uppdrag som de andra grupperna fick
         for (int k = 0; k < wordList.size(); k++) {
            for (int j = 0; j < ejUppdrag.size(); j++) {
               if (wordList.get(k) == ejUppdrag.get(j)) {
                   wordList.remove(k);
           }
        }
     }
         
         //Gör om till Array String igen.
      uppdragViFick = wordList.stream().toArray(String[]::new);
         
      uppdragViFick2 = String.join(",", uppdragViFick);
  }   
}
