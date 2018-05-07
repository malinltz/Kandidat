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
    String[] uppdrag;
    String platserna;
    String url;
    String dummy;
    String message;
    String test;
    int dist = 0;
    public String plats;
    int platsgrupp;

    public HTTPextern(HTTPny http, DataStore ds) {

        this.http = http;
        this.ds = ds;
        sleepTime = 1000;
    }

    public void exprotokoll() {

       // platsgrupp = Integer.parseInt(http.plats);
        
        for (int i = 0; i < http.meddelandet ; i++) // kolla om platsen är samma som en annan företagsgrupp
        {  
            if (http.datum[i] < http.datum[i]+1) //kollar så att vi tar in den senaste datan genom att gemföra datan
            {
                if(http.platser[i]==)
            }
        //om företagsgruppens id är lägre än en annan så  får den uppdraget
            }
        
            
        for (int i = 0; i < http.meddelandet ; i++) // kolla hur kostnaden från en bilen till upphämtningsplatsen är
        {
            if (ID < http.ink.size()) //jämför viåt id med id från de andra
           
            {
               
        for (int i = 0; i < http.meddelandet ; i++)  //jämför hur många uppdrag
                
                if (capacity < http.ut.size()) 
                //
                //jämför kapaciteten
                //  if( op.pathCost < http.) // jämför minsta kostnad för oss med minsta kostnad för de anrda
                // if( op.shortestPathList > http.utmess.size()) // jämför antalet uppdarg
                {
                    System.out.println(""); //
                }
         }
        for (int i = 0; i < http.meddelandet ; i++) {}
           
        }
        System.out.println();
 
    }

}