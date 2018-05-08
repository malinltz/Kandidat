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

/**
 *
 * @author malinlilliecreutz
 **/

public class HTTPextern {

    public OptPlan op;
    public DataStore ds;
    public HTTPny http;
    int sleepTime;
    int capacity = 3; //exempel på kapacitet
    int ID = 1;
    int[] messagetyp;
    private String [] dummer;
  //  private String [] platserna;
   // String url;
    String dummy;
    String message;
    String test;
    int dist = 0;
    public String plats;
    int platsgrupp;
    //int langd=3; 
    int capacitet4= 20; //fixt capacitet för grupp 4
    int capacitet5= 50; //fixt capacitet för grupp 5
    int capacitet1= 60; //fixt capacitet för grupp 1(vår grupp)
    
    ArrayList <String> uppdragG1;
    ArrayList <String> uppdragG4;
    ArrayList <String> uppdragG5;

    public HTTPextern(HTTPny http, DataStore ds) {

        this.http = http;
        this.ds = ds;
        dummy="";
        uppdragG1= new ArrayList <String>();
        uppdragG4= new ArrayList <String>();
        uppdragG5= new ArrayList <String>();
    
        sleepTime = 1000;
    }

    
    //bubble sort 
    //linked list
    public String exprotokoll() {

       // platsgrupp = Integer.parseInt(http.plats);
        
       //kör idbaserat istället
       //går inte ut utan sätter att ni inte får ta det uppdraget
                   //Splittar Vilka uppdrag (Behöves ej?)
          
       
       
      //Kolla vilka uddrag respektive grupp ska göra
        for (int i = 0; i < 3; i++) {
            dummer = http.uppdrag[i].split(",");
            if (i == 0) {
                for (int j = 0; j < dummer.length; j++) {
                    uppdragG1.add((dummer[j]));
                }
            } //Ändra ordning här breoend epå ordning i HTTP
            else if (i == 1) {
                for (int j = 0; j < dummer.length; j++) {
                    uppdragG4.add((dummer[j]));
                }
            } else if (i == 2) {
                for (int j = 0; j < dummer.length; j++) {
                    uppdragG5.add((dummer[j]));
                }
            }
        }

        System.out.println("Grupp 1 Uppdrag" + uppdragG1);
        System.out.println("Grupp 4 Uppdrag" + uppdragG4);
        System.out.println("Grupp 5 Uppdrag" + uppdragG5);
        
   
        for (int i = 0; i < 3; i++) {
            if (i != 0) {
                if (http.paxplats[i] == http.paxplats[1]) {
                    //Kolla hur det är med kostnaderna
                    if (http.kostnad[i] < http.kostnad[0])
                    { 
                    } else if (http.kostnad[i] < http.kostnad[0]) {
                        //Då vill vi jämföra grupp-ID
                        if (http.iD[i] < http.iD[0]) {
                            //Då vill att den gruppen ska få det och då måste vi byta uppdrag 
                        }
                    }
                    // Else: Då vill vi inte göra något för då ska v ta det uppdraget.

                }
            }
             
        }
        return "hej";
      
    }
}
        

/*
                    if(String.valueOf(http.kostnad).equals(http.lagstaKostnad)) // kolla om kostnaden är samma fär 
                    {
                        if else(http.kostnad < http.lagstaKostnad) //om kostnaden för de andra är mindre än lägsta kostnaden så får de andra uppdraget
                                 //går ut och ta näst bästa
                                {
                                    else
                                    
                                    http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                    }
                        if(String.valueOf(http.uppdrag).equals(http.uppdragsid)) //kolla om antalet uppdrag är samm
                       
                            {
                                if else ((http.uppdrag) < (http.uppdragsid))
                                        //går ut och ta näst bästa
                                        {
                                else 
                                http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                                            
                              if(capacitet1 == capacitet4 || capacitet5 == capacitet1) //kolla om antalet uppdragsid är samma från den gruppen
                              {
                                  if else ( capacitet1< capacitet4 || capacitet1 < capacitet5)
                            //gå till nästa upphämtningsplats
                              }
                              else http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                                //ta uppdrag 
                                  
                              if(String.valueOf(http.ID > ID )//om företagsgruppens id är lägre än en annan så  får den uppdraget
                              {
                                    if else (String.valueOf(http.paxplats) < (http.narmstaPlats)) 
                                    //gå till nästa upphämtningsplats
                              } 
                              else http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                                //ta uppdrag
                                  
                                  }
                              else http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                            //ta uppdrag 

                              }
                             else  http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                                //ta uppdrag 
                        }
                         else   http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                        //ta uppdrag 
                    }
        
                else   http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                     }
                //ta uppdrag 
                
                return 
                }
    
        
        //ta uppdrag 


*/