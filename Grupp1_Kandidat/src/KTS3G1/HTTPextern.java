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
    private String [] uppdrag;
    private String [] platserna;
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

    public HTTPextern(HTTPny http, DataStore ds) {

        this.http = http;
        this.ds = ds;
        dummy="";
       
        
        
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
            
for(int i = 0; i < 3;i++){
                              if(i==0){
                                  for(int j = 0; j < dummer.length; j++){
                                      uppd_lista1.add(dummer[j]);
                                      
                                  }
                              }
        
    }
}
/*
package planeringssystem;

import java.util.Arrays;
import java.util.LinkedList;

public class ReadGroup {

    private DataStore ds;
    private HTTPgrupp hg;
    private String[] dummyList;
    int[] tempos;
    String temp1;
    String temp2;
    String temp3;
    int temp4;

    public ReadGroup(DataStore ds, HTTPgrupp hg) {
        this.ds = ds;
        this.hg = hg;
        dummyList = new String[ds.cap];
        tempos = new int[3];
        temp1 = "";
        temp2 = "";
        temp3 = "";
        temp4 = 0;
    }

    public String Read() {

        //Dela upp meddelandet från HTTPgrupp
        for (int i = 0; i < 3; i++) {
            ds.groupList = hg.getmessage()[i].split(";");
            tempos[i] = Integer.parseInt(ds.groupList[1]);
            ds.groupDate[i] = ds.groupList[0];
            ds.groupID[i] = ds.groupList[1];
            ds.groupMessage[i] = ds.groupList[2];
        }

        //Swoopa i rätt ordning för att veta att det alltid blir rätt
        for (int i = 0; i < 2; i++) {
            if (tempos[i] > tempos[i + 1]) {
                //Swoppa alla dom platserna i alla arrayer
                //Swoppa groupDate
                temp1 = ds.groupDate[i];
                ds.groupDate[i] = ds.groupDate[i + 1];
                ds.groupDate[i + 1] = temp1;
                //Swoppa groupID
                temp2 = ds.groupID[i];
                ds.groupID[i] = ds.groupID[i + 1];
                ds.groupID[i + 1] = temp2;
                //Swoppa groupMessage
                temp3 = ds.groupMessage[i];
                ds.groupMessage[i] = ds.groupMessage[i + 1];
                ds.groupMessage[i + 1] = temp3;
                //Swoppa tempos
                temp4 = tempos[i];
                tempos[i] = tempos[i + 1];
                tempos[i + 1] = temp4;
                if (tempos[i] > tempos[i + 1]) {
                    //Swoppa groupDate
                    temp1 = ds.groupDate[i];
                    ds.groupDate[i] = ds.groupDate[i + 1];
                    ds.groupDate[i + 1] = temp1;
                    //Swoppa groupID
                    temp2 = ds.groupID[i];
                    ds.groupID[i] = ds.groupID[i + 1];
                    ds.groupID[i + 1] = temp2;
                    //Swoppa groupMessage
                    temp3 = ds.groupMessage[i];
                    ds.groupMessage[i] = ds.groupMessage[i + 1];
                    ds.groupMessage[i + 1] = temp3;
                    //Swoppa tempos
                    temp4 = tempos[i];
                    tempos[i] = tempos[i + 1];
                    tempos[i + 1] = temp4;
                }
                if (i > 0 && tempos[i - 1] > tempos[i]) {
                    //Swoppa groupDate
                    temp1 = ds.groupDate[i - 1];
                    ds.groupDate[i - 1] = ds.groupDate[i];
                    ds.groupDate[i] = temp1;
                    //Swoppa groupID
                    temp2 = ds.groupID[i - 1];
                    ds.groupID[i - 1] = ds.groupID[i];
                    ds.groupID[i] = temp2;
                    //Swoppa groupMessage
                    temp3 = ds.groupMessage[i - 1];
                    ds.groupMessage[i - 1] = ds.groupMessage[i];
                    ds.groupMessage[i] = temp3;
                    //Swoppa tempos
                    temp4 = tempos[i - 1];
                    tempos[i - 1] = tempos[i];
                    tempos[i] = temp4;
                }
            }
        }

        //Splitta meddelandet
        for (int i = 0; i < 3; i++) {
            ds.groupMessageSplit = ds.groupMessage[i].split("!");
            ds.groupPlats[i] = ds.groupMessageSplit[0];
            ds.groupCost[i] = ds.groupMessageSplit[1];
            ds.groupUppdrag[i] = ds.groupMessageSplit[2];
        }

        //Kolla vilka uppdrag respektive grupp ska göra
        for (int i = 0; i < 3; i++) {
            dummyList = ds.groupUppdrag[i].split(",");
            if (i == 0) {
                for (int j = 0; j < dummyList.length; j++) {
                    ds.uppdragGroup1.add((dummyList[j]));
                }
            } //Ändra ordning här breoend epå ordning i HTTP
            else if (i == 1) {
                for (int j = 0; j < dummyList.length; j++) {
                    ds.uppdragGroup4.add((dummyList[j]));
                }
            } else if (i == 2) {
                for (int j = 0; j < dummyList.length; j++) {
                    ds.uppdragGroup5.add((dummyList[j]));
                }
            }
        }

        System.out.println("Grupp 1 Uppdrag" + ds.uppdragGroup1);
        System.out.println("Grupp 4 Uppdrag" + ds.uppdragGroup4);
        System.out.println("Grupp 5 Uppdrag" + ds.uppdragGroup5);

        //Nu vill vi kolla om de andra grupperna har samma upphämtningsplats för att veta vem som ska göra vilket uppdrag
        for (int i = 0; i < 3; i++) {
            if (i != 1) {
                if (ds.groupPlats[i] == ds.groupPlats[1]) {
                    //Kolla hur det är med kostnaderna
                    if (Integer.parseInt(ds.groupCost[i]) < Integer.parseInt(ds.groupCost[1])) { //Om en annan grupp har lägre kostnad
                        //Då vill vi kolla om det finns ett eller flera uppdrag på den plasten som vi kan ta istället
                        //Om det inte finns andra uppdrag att ta på den plasten så vill vi byta till den näst närmaste platsen 
                        //Borde det göras en ny beräkning då?
                    } else if (Integer.parseInt(ds.groupCost[i]) == Integer.parseInt(ds.groupCost[1])) {
                        //Då vill vi jämföra grupp-ID
                        if (Integer.parseInt(ds.groupID[i]) < Integer.parseInt(ds.groupID[1])) {
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
*/