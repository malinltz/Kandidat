/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

package KTS3G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.List;
import java.io.DataOutputStream;
//import java.util.regex.Pattern;
import java.util.ArrayList;
*/

/**
 *
 * @author malinlilliecreutz
 **/

/*
public class HTTPextern {

    public OptPlan op;
    public DataStore ds;
    public HTTPny http;
    int sleepTime;
    int capacity = 3; //exempel på kapacitet
    int ID = 1;
    int[] messagetyp;
    String[] uppdrag;
    String platserna;
    String url;
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

    public HTTPextern(HTTPny http, DataStore ds) {

        this.http = http;
        this.ds = ds;
        sleepTime = 1000;
    }

    
    //bubble sort 
    //linked list
    public void exprotokoll() {

       // platsgrupp = Integer.parseInt(http.plats);
        
       //kör idbaserat istället
       //går inte ut utan sätter att ni inte får ta det uppdraget
        for (int i = 0; i < http.meddelandet ; i++)  //kollar hela meddelandet
        {  
         
                if(String.valueOf(http.paxplats).equals(http.narmstaPlats)) // kolla om platsen är samma som en annan företagsgrupp
                {
                 
                    else {
                    System.out.println("Ta uppdraget"); //om det inte är samma så ta man uppdraget. 
                    
                   // http.tauppdrag(plats, ID, passagerare, grupp); //kör att man ska ta uppdraget
                }

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
                }
    
        
        //ta uppdrag 
            


}
*/