/*package KTS3G1;

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

public class HTTPanrop implements Runnable {

    public String message;
    public String messageupp;
   // public String paragraph1;
   // public String paragraph2;
   // public String paragraph3;
   // public String platser;
  //  public String plats1;
  //  public String plats2;
   // public String plats3;
    private String url1;
    private String url12;
    private String url2;
    private String url3;
    private String utmessage;
    public OptPlan op;
    public DataStore ds;
    public ControlUI cui;
    private String gruppmessage;

    // private List<String> uppdrag;
    // String aline= null;
    ArrayList<String> ink = new ArrayList<String>();
    ArrayList<String> upp = new ArrayList<String>();
    ArrayList<String> ut = new ArrayList<String>();
    ArrayList<String> utmess = new ArrayList<String>();
    
    //För att ta reda på vilka uppdragsplatser som finns vart
    private static String URL1 = ("http://tnk111.n7.se/listaplatser.php");
    //För att hämta ett uppdrag på platsen
    private static String URL12 = ("http://tnk111.n7.se/listauppdrag.php?plats=A");
    //För att skicka ett meddelande till HTTP- servern
    private static String URL2 = ("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=1&message=kul");

    private static String URL3 = ("http://tnk111.n7.se/getmessage.php?messagetype=1");

    //För att läsa de meddelanden som grupper skickat

    ////För att ta reda på vilka uppdrag som finns på plats A
  
    private int sleepTime;

    // int j = 0;
    // int numberOfLines = 20;
    String[] Listupp;
    
    public HTTPanrop(DataStore ds, OptPlan op, ControlUI cui) {
        //  this.cui = cui;
        this.ds = ds;
        this.op = op;
        this.cui = cui;
        //sleepTime = generator.nextInt(20000);
        sleepTime = 1000; //1000 millisekunder
    }

    @Override
    public void run() { //Lägg till en 

        url1 = URL1;
        url12 = URL12;
        url2 = URL2;
        url3 = URL3;
        //op = new OptPlan(ds);

        try { // Kopplar upp till listan och hämtar info

            URL urlobjekt1 = new URL(url1);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt1.openConnection();
            System.out.println("\nAnropar: " + url1);
            anslutning.setRequestMethod("GET"); // ny kod
            //;
            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();
            
            while ((inkommande_text = inkommande.readLine()) != null)
            {
                inkommande_samlat.append(inkommande_text);
                
                ink.add(inkommande_text);
            }
            inkommande.close();
            
         for(int k = 0; k < ink.size(); k++){
            System.out.println("Upphämtningsplatser: " + ink.get(k));
             // System.out.println(ink.indexOf(k));
             // System.out.println(ink.spliterator(k));
             // Listupp[k] = Integer.parseInt(ink.get(k));
             //ink.
             //Collections.indexOfSubList(ink,ut);
         }
         cui.lista(ink);
        // ink.indexOf(k);
         
   // Collections.emptyList(ink(k));
            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception c) {
            System.out.print(c.toString());

        }
        try { // hämtar uppdrag

            URL urlobjekt1 = new URL(url12);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt1.openConnection();
            System.out.println("\nAnropar: " + url12);
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
            
             for(int k = 0; k < upp.size(); k++){
            System.out.println("Uppdrag: " + upp.get(k));
            //upp.spliterator("");
             // System.out.println("After Sorting:");
            // upp.ArrayList.sort();
                         }
	   for(String counter: ink){
			
                        counter.split("A");
                        System.out.println(counter.toString());
                        
               }
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
//        }
            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception c) {
            System.out.print(c.toString());

        }

        try { //lägger upp uppdrag
            URL urlobjekt2 = new URL(url2);
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
            System.out.println("\nSending 'POST' request to URL : " + url2);// + paragraph1);
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
             for(int k = 0; k < ut.size(); k++){
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
        try { //vad vi hämtar hem från de anrda 

            URL urlobjekt3 = new URL(url3);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt3.openConnection();
            System.out.println("\nAnropar: " + url3);

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
             for(int k = 0; k < utmess.size(); k++){
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
*/