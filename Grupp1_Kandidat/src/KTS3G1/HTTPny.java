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
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.ArrayList;
import java.io.DataOutputStream;
import java.util.Collections;
//import java.util.regex.Pattern;


public class HTTPny {

    public String message;
    public String uppdragslista;
    private String url;

    private String utmessage;
    public OptPlan op;
    OptPlan [] opt;
    public DataStore ds;
    public ControlUI cui;
    public RobotRutt RR;
    private String gruppmessage;

    public String plats;
    public String ID;
    public String passagerare;
    public String grupp; 
    public String listaplats;
    public int storlek;
    public int uppsizeInt;

    int[] startlist;
    int[] stopplist;

    String[] uppdragsid;
    String[] destination;
    int[] nuPoints;
    int[] destNod1;
    int[] destNod2;
    int[] pass;
    int[] samakning;
    private int sleepTime;

     ArrayList<String> ink;
     ArrayList<String> upp;
     ArrayList<String> ut;
     ArrayList<String> utmess; 

    public HTTPny(DataStore ds, OptPlan op, ControlUI cui) {
        this.ds = ds;
        this.op = op;
        this.cui = cui;
        sleepTime = 1000; //1000 millisekunder

        ink = new ArrayList<String>();
        upp = new ArrayList<String>();
        ut = new ArrayList<String>();
        utmess = new ArrayList<String>();
    }

    public void Listaplats() {

        try { // Kopplar upp till listan och hämtar info och returnerar
            String url = ("http://tnk111.n7.se/listaplatser.php");
            
            URL urlobjekt1 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt1.openConnection();
            System.out.println("\nAnropar: " + url);
            anslutning.setRequestMethod("GET"); // ny kod
            //;
            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();

            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);

                ink.add(inkommande_text);
            }
            inkommande.close();

            for (int k = 0; k < ink.size(); k++) {
                System.out.println("Upphämtningsplatser: " + ink.get(k));
            }

            listaplats = ink.get(0);
            storlek = Integer.parseInt(listaplats);
            String[] sline;
            String platser[] = new String[storlek];
            String listans[] = new String[storlek];

            startlist = new int[storlek];
            stopplist = new int[storlek];

            for (int j = 1; j < storlek + 1; j++) {
                sline = ink.get(j).split(";");
                platser[j - 1] = sline[0];
                listans[j - 1] = sline[1];
            }
            
            for (int i = 0; i < storlek; i++) {
                sline = listans[i].split(",");
                startlist[i] = Integer.parseInt(sline[0].trim());
                stopplist[i] = Integer.parseInt(sline[1].trim());
                
                //ds.arcStart = startlist[i];
                //ds.arcEnd = stopplist[i];   
            }
                  
            cui.lista(ink);

            Thread.sleep(2000); //vilken sleeptime?

          }catch (Exception c) {
            System.out.print(c.toString());
        }
    }

    public String listauppdrag(String plats) {
        
        try {

            String url = ("http://tnk111.n7.se/listauppdrag.php?plats=" + plats);
            URL urlobjekt1 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) 
            urlobjekt1.openConnection();
            System.out.println("\nAnropar: " + url);
            anslutning.setRequestMethod("GET");
            //;
            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();

            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);

                upp.add(inkommande_text);
            }

            inkommande.close();

            for (int k = 0; k < upp.size(); k++) {
                System.out.println("Uppdrag: " + upp.get(k));
            }
            
            uppdragslista = inkommande_samlat.toString();
            
         //Variabler för uppdragslistan
        String uppsize = upp.get(0);
        uppsizeInt = Integer.parseInt(uppsize); 
        String [] slice;
        uppdragsid = new String[uppsizeInt];
        destination  = new String[uppsizeInt];
        pass  = new int[uppsizeInt]; 
        samakning  = new int[uppsizeInt];
        nuPoints = new int[uppsizeInt];
        destNod1 = new int[uppsizeInt];
        destNod2 = new int[uppsizeInt];
        
        //Delar upp uppdragslistan i ID,Destination,Passagerare,Samåkning,Poäng
        for(int k = 1; k <uppsizeInt+1 ; k++){
             slice = upp.get(k).split(";");
             uppdragsid[k-1] = slice[0];
             destination[k-1] = slice[1];
             pass[k-1] = Integer.parseInt(slice[2]);
             samakning[k-1] = Integer.parseInt(slice[3]);
             nuPoints[k-1] = Integer.parseInt(slice[4]);

        //Skriver ut i Statusrutan alla uppdrag på just den hållplatsen
            cui.hallplatsuppdrag("ID: "  + uppdragsid[k-1] + ", Destination: " + destination[k-1]
            + ", Passagerare: " + pass[k-1] + ", Samåkning: " + samakning[k-1]
            + ", Poäng: " + nuPoints[k-1] + "");      
        }
        
        for(int j = 0; j <uppsizeInt; j++){
            slice = destination[j].split(",");    
            destNod1[j] =Integer.parseInt(slice[0]);
            destNod2[j] =Integer.parseInt(slice[1]);
            cui.destination("Destination ligger mellan noderna: " + destNod1[j] + " och " + destNod2[j]); 
            
            //ds.arcStart[j] = destNod1[j];
            //ds.arcEnd[j] = destNod2[j]; 
        }

            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception c) {
            System.out.print("Fel: " + c.toString());

        }
        return uppdragslista;
    }

    public String tauppdrag(String plats, String ID, String passagerare, String grupp) {
 
        try { //lägger upp uppdrag
            String url = ("http://tnk111.n7.se/tauppdrag.php?plats"+ plats + "&id"+ ID +"&passagerare="+  passagerare + "&grupp"+  grupp);

            URL urlobjekt2 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) 
            urlobjekt2.openConnection();

            anslutning.setRequestMethod("POST");
            anslutning.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(anslutning.getOutputStream());
            wr.flush();
            wr.close();
            
            System.out.println("\nSending 'POST' request to URL : " + url);
            int responseCode = anslutning.getResponseCode();
            System.out.println("Statuskod: " + responseCode);

            BufferedReader inkommande = new BufferedReader(
            new InputStreamReader(anslutning.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = inkommande.readLine()) != null) {
                response.append(inputLine);
                ut.add(inputLine);
            }
            
            inkommande.close();
            
            //Skriver ut vilket uppdrag vi tagit i statusruta
            cui.tauppdrag("Plats: "  + plats + ", ID: " + ID
            + ", Passagerare: " + passagerare + ", Grupp: " + grupp + "");    
 
            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception e) {

            //print result
            System.out.println(e.toString());
        }
        return utmessage;
    }

    public String aterstall(int Scenarionr) {

        try { //vad vi hämtar hem från de andra 
            String url = ("http://tnk111.n7.se/aterstall.php?scenario=" + Scenarionr);
            
            URL urlobjekt3 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt3.openConnection();
            System.out.println("\nAnropar: " + url);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text = "";
            StringBuffer inkommande_samlat = new StringBuffer();

            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
                utmess.add(inkommande_text);
            }

            inkommande.close();
            for (int k = 0; k < utmess.size(); k++) {
                System.out.println("Ink: " + utmess.get(k));
            }
            
            gruppmessage = inkommande_samlat.toString();

            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception k) {
            System.out.print(k.toString());
        }
        return gruppmessage;
    }

    public void inmessages() {
        
        try { //vad vi hämtar hem från de anrda 
            String url = ("http://tnk111.n7.se/getmessage.php?messagetype=1");
            
            URL urlobjekt3 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt3.openConnection();
            System.out.println("\nAnropar: " + url);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text = "";
            StringBuffer inkommande_samlat = new StringBuffer();

            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
                utmess.add(inkommande_text);
            }

            inkommande.close();
            for (int k = 0; k < utmess.size(); k++) {
                System.out.println("Ink: " + utmess.get(k));
            }

            gruppmessage = inkommande_samlat.toString();

            Thread.sleep(2000); //vilken sleeptime?
            //cui.svarHTTP("Tid för meddelandet osv: " + "\n" + gruppmessage);

        } catch (Exception k) {
            System.out.print(k.toString());
        }
    }

    public void utmessages() {
       
        String messut = "A!50!1";

        try { //vad vi hämtar hem från de anrda 
            String url = ("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=1&message=" + messut);
            
            URL urlobjekt3 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt3.openConnection();
            System.out.println("\nAnropar: " + url);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text = "";
            StringBuffer inkommande_samlat = new StringBuffer();

            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);

                utmess.add(inkommande_text);
            }

            inkommande.close();
            for (int k = 0; k < utmess.size(); k++) {
                System.out.println("Ink: " + utmess.get(k));
            }
            
            gruppmessage = inkommande_samlat.toString();

            Thread.sleep(2000); //vilken sleeptime?
            //cui.svarHTTP("Tid för meddelandet osv: " + "\n" + gruppmessage);

        } catch (Exception k) {
            System.out.print(k.toString());
        }
    }
  

    public String newmesssage() {

        return message;
    }

    public String getutmesssage() {

        return utmessage;
    }

    public String gruppmessages() {

        return gruppmessage;
    }

}
