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

/**
 *
 * @author malinlilliecreutz
 */
public class HTTPny {

    public String message;
    public String messageupp;
    private String url;

    private String utmessage;
    public OptPlan op;
    public DataStore ds;
    public ControlUI cui;
    private String gruppmessage;

    public String plats, ID, passagerare, grupp;
    public String listaplats;
    public int storlek;

    int[] startlist;
    int[] stopplist;

    String uppdragsid[];

    int[] pass;
    int[] samakning;
    int[] poäng;
    int[] dest; //destination på uppdraget 

    // private List<String> uppdrag;
    // String aline= null;
    private static String URL;
    //För att ta reda på vilka uppdragsplatser som finns vart

    //För att hämta ett uppdrag på platsen
    //  private static String URL12 = ("http://tnk111.n7.se/listauppdrag.php?plats=A");
    //För att skicka ett meddelande till HTTP- servern
    //För att läsa de meddelanden som grupper skickat
    ////För att ta reda på vilka uppdrag som finns på plats A
    private int sleepTime;

    // int j = 0;
    // int numberOfLines = 20;
    ArrayList<String> ink;
    // String[] Listupp;

    public HTTPny(DataStore ds, OptPlan op, ControlUI cui) {
        //  this.cui = cui;
        this.ds = ds;
        this.op = op;
        this.cui = cui;
        //sleepTime = generator.nextInt(20000);
        sleepTime = 1000; //1000 millisekunder

        ink = new ArrayList<String>();

    }
    //op = new OptPlan(ds);

    public void Listaplats() {

        url = URL;

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
                System.out.println("Upphämtningsplatser: " + ink.get(0));
                // System.out.println(ink.indexOf(k));
                // System.out.println(ink.spliterator(k));
                // Listupp[k] = Integer.parseInt(ink.get(k));
                //ink.

                //Collections.indexOfSubList(ink,ut);
            }

            listaplats = ink.get(0);
            storlek = Integer.parseInt(listaplats);
            // int line=0; 
            String[] sline;
            String listans[] = new String[storlek];
            String platser[] = new String[storlek];
            //  pass = new int[storlek];

            // line = scanner.nextLine();
            //  for(int i=1; i<.size(); i++)
            startlist = new int[storlek];
            stopplist = new int[storlek];

            cui.lista(ink);
            
             for (int j = 1; j < storlek + 1; j++) 
            {

                sline = ink.get(j).split(" ");
                listans[j-1]=sline[0];
                platser[j-1]=sline[1];
                

            }
            

            for (int i = 1; i < storlek + 1; i++) 
            {

                sline = listans[i].split(" ");
                startlist[i] = Integer.parseInt(sline[0].trim());
                stopplist[i] = Integer.parseInt(sline[1].trim());

            }

            // ink.indexOf(k);
            // Collections.emptyList(ink(k));
            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception c) {
            System.out.print(c.toString());

        }

    }

    public String uppdrag(String plats) {
        url = URL;
        ArrayList<String> upp = new ArrayList<String>();

        try { // hämtar uppdrag

            String url = ("http://tnk111.n7.se/listauppdrag.php?plats=" + plats);
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

                upp.add(inkommande_text);
            }

            inkommande.close();

            for (int k = 0; k < upp.size(); k++) {
                System.out.println("Uppdrag: " + upp.get(k));
                //upp.spliterator("");
                // System.out.println("After Sorting:");
                // upp.ArrayList.sort();
            }
            //for(String counter: ink){

            //     counter.split("A");
            //    System.out.println(counter.toString());
            cui.lista(upp);
            // upp.spliterator(k);

            // upp.subList(5,56);
            messageupp = inkommande_samlat.toString();
            //System.out.println(message);
            //  String[] paras = messageupp.split(" ");
            // StringBuilder sb = new StringBuilder();
            // for ( String s : ink.toArray(arrayOfStrings) ) 
            // {
            //    sb.append(s);
            //    sb.append("\t");
            // }

            //  System.out.println(sb.toString());
            /*
            for (int i = 0; i < paras.length; i++) {

                paragraph1 = paras[i];
                System.out.println("Parametrar: " + paragraph1);
            }
             */
            cui.showStatus(messageupp);
            // cui.showStatus(paragraph1);
            //Delar upp uppdragsplatserna. Om vi får fler uppdragsplatser 
            //behöver vi ändra detta eftersom det är hårdkodat
            /*
             platser = message.substring(0,1); //Får ut en 3 (antal platser)
             plats1 = message.substring(1,8); //Får ut första platsen (A)
             plats2 = message.substring(8,15); //Får ut andra platsen (B)
             plats3 = message.substring(15,22); //Får ut andra platsen (B)

             cui.showStatus2(platser);
             cui.showStatus(plats1);
             cui.showStatus(plats2);
             cui.showStatus(plats3);
             */
            //Försöker att sätta slutnoden till upphämtningsplatsen
            //op.getCost();

            //int attakatill = Integer.parseInt(plats1.substring(2,4));
            //op.createPlan();
//        int attakatill2 = Integer.parseInt(plats1.substring(5,7));
//        
//        if(attakatill < attakatill2){
//            slut = attakatill2;
//        }
//        else {
//            slut = attakatill;
            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception c) {
            System.out.print(c.toString());

        }
        return messageupp;
    }

    public String tauppdrag(String plats, String ID, String passagerare, String grupp) {
        url = URL;
        ArrayList<String> ut = new ArrayList<String>();

        try { //lägger upp uppdrag
            String url = ("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=1&message=" + plats + ID + passagerare + grupp);

            URL urlobjekt2 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt2.openConnection();

            //add reuqest header
            anslutning.setRequestMethod("POST");
            //con.setRequestProperty("User-Agent", USER_AGENT);
            //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // int distance= op.pathCost;
            // Send post request
            anslutning.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(anslutning.getOutputStream());
            //wr.writeBytes(distance);
            wr.flush();
            wr.close();

            int responseCode = anslutning.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);// + paragraph1);
            // System.out.println("Post parameters : " + op.pathCost); //vad vi vill lägga upp?
            //  System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(anslutning.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            //  while ((inputLine = in.readLine()) != null) {
            //     response.append(inputLine);
            //   }
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                //  arrayOfStrings[j]= inkommande_text;
                // j++; 
                //  inkommande_text = inkommande.readLine() ; 

                ut.add(inputLine);
            }

            in.close();
            for (int k = 1; k < ut.size() - 1; k++) {
                System.out.println("Hej: " + ut.get(k));
            }
            cui.lista(ut);
            utmessage = response.toString();

            //   String[] paras = utmessage.split(";" + "");
            //   for (int i = 0; i < paras.length; i++) {
            //    paragraph2 = paras[i];
            //      System.out.println("Mottaget meddelande: " + paragraph2);
            //  }
            cui.svarHTTP(utmessage);
            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception e) {

            //print result
            System.out.println(e.toString());
        }
        return utmessage;
    }

    public String aterstall(String Scenario) {
        url = URL;
        ArrayList<String> utmess = new ArrayList<String>();

        try { //vad vi hämtar hem från de anrda 

            String url = ("http://tnk111.n7.se/aterstall.php?" + Scenario);
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
                //  arrayOfStrings[j]= inkommande_text;
                // j++; 
                //  inkommande_text = inkommande.readLine() ; 

                utmess.add(inkommande_text);
            }

            inkommande.close();
            for (int k = 0; k < utmess.size(); k++) {
                System.out.println("Ink: " + utmess.get(k));
            }
            cui.lista(utmess);
            gruppmessage = inkommande_samlat.toString();

            //  String[] paras = gruppmessage.split("");
            // for (int i = 0; i < paras.length; i++) {
            //    paragraph3 = paras[i];
            // }
            Thread.sleep(2000); //vilken sleeptime?
            cui.svarHTTP("Tid för meddelandet osv: " + "\n" + gruppmessage);

            //  String infogrupp = gruppmessage.substring(1,8);
        } catch (Exception k) {
            System.out.print(k.toString());
        }
        return gruppmessage;
    }

    public void messages() {
        url = URL;
        ArrayList<String> utmess = new ArrayList<String>();

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
                //  arrayOfStrings[j]= inkommande_text;
                // j++; 
                //  inkommande_text = inkommande.readLine() ; 

                utmess.add(inkommande_text);
            }

            inkommande.close();
            for (int k = 0; k < utmess.size(); k++) {
                System.out.println("Ink: " + utmess.get(k));
            }
            cui.lista(utmess);
            gruppmessage = inkommande_samlat.toString();

            //  String[] paras = gruppmessage.split("");
            // for (int i = 0; i < paras.length; i++) {
            //    paragraph3 = paras[i];
            // }
            Thread.sleep(2000); //vilken sleeptime?
            cui.svarHTTP("Tid för meddelandet osv: " + "\n" + gruppmessage);

            //  String infogrupp = gruppmessage.substring(1,8);
        } catch (Exception k) {
            System.out.print(k.toString());
        }
    }

    public void utmessages() {
        url = URL;
        ArrayList<String> utmess = new ArrayList<String>();

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
                //  arrayOfStrings[j]= inkommande_text;
                // j++; 
                //  inkommande_text = inkommande.readLine() ; 

                utmess.add(inkommande_text);
            }

            inkommande.close();
            for (int k = 0; k < utmess.size(); k++) {
                System.out.println("Ink: " + utmess.get(k));
            }
            cui.lista(utmess);
            gruppmessage = inkommande_samlat.toString();

            //  String[] paras = gruppmessage.split("");
            // for (int i = 0; i < paras.length; i++) {
            //    paragraph3 = paras[i];
            // }
            Thread.sleep(2000); //vilken sleeptime?
            cui.svarHTTP("Tid för meddelandet osv: " + "\n" + gruppmessage);

            //  String infogrupp = gruppmessage.substring(1,8);
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
