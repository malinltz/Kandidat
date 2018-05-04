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

/**
 *
 * @author malinlilliecreutz

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
    int dist=0;

    public HTTPextern(HTTPny http, DataStore ds) {
        
        this.http = http;
        this.ds = ds;
        sleepTime = 1000;
    }

    public void exprotokoll() {

        http.ink.get(dist);
      
        //om företagsgruppens id är lägre än en annan så  får den uppdraget
        
        if (ID < http.ink.size()) //jämför viåt id med id från de andra
        
        if (capacity < http.ut.size()) //jämför kapaciteten
        
      //  if( op.pathCost < http.) // jämför minsta kostnad för oss med minsta kostnad för de anrda
       
        
       // if( op.shortestPathList > http.utmess.size()) // jämför antalet uppdarg
        
        {
            System.out.println(""); //
        }
        System.out.println();
        System.out.println();

    

       
 //  http.platser;
 //  http.plats1;
 //  http.plats2;
 //  http.plats3;
         
    }

}
*/
/*public Uppdrag(DataStore ds) { 
        aterstall("1");
        this.ds = ds;
        listaplatser();
        valtUppdrag = listauppdrag(narmstaPlats);           //Skickar in upphämtningsplats, skickar ut vilket uppdrag vi väljer
        pax = getPassagerare(valtUppdrag);                  //Skickar ut passagerarantal på det valda uppdraget
        oppis1path = new ArrayList<Integer>();
        oppis2path = new ArrayList<Integer>();
        oppispath = new ArrayList<Integer>();
        
       String svaruppdrag = tauppdrag(narmstaPlats, valtUppdrag, pax, ds.grupp);
       
            if (svaruppdrag.equals("beviljas")){
                
                for(int i=0; i <128; i++){
            
                    ds.arcColor[i] = 0;           
            }
                
            ds.startRutt = ds.robotpos;        
            ds.slutRutt = linkNod2[Integer.parseInt(valtUppdrag)-1];
                             
            oppis1 = new OptPlan(ds);
            oppis1path = oppis1.createPlan();
  
            ds.startRutt = linkNod1[Integer.parseInt(valtUppdrag)-1];       
            ds.slutRutt = destNod1[Integer.parseInt(valtUppdrag)-1];
                             
            oppis2 = new OptPlan(ds);
            oppis2path = oppis2.createPlan();



           oppis2 = new OptPlan(ds);
            oppis2path = oppis2.createPlan();
               
            
            for ( int i = 0; i < oppis1path.size(); i++ ){
            oppispath.add(oppis1path.get(i));
            }
             System.out.println("Oppis1path: " + oppis1path);  
            
              for ( int i = 2; i < oppis2path.size(); i++ ){
            oppispath.add(oppis2path.get(i));
            } 
             System.out.println("Oppis2path: " + oppis2path);    
              
            System.out.println("Oppispath: " + oppispath);  
            
            opt = new OptPlan(ds);
            opt.compass(oppispath);
            
            ds.cui.repaint();
  
        }
        else {System.out.println("Svar från hemsida: " + svaruppdrag);}
        
        aterstall("1");
    
    }

      */