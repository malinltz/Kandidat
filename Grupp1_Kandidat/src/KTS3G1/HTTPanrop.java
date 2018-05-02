package KTS3G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.List;
import java.io.DataOutputStream;
//import java.util.regex.Pattern;

public class HTTPanrop implements Runnable {

    public String message;
    public String messageupp;
    public String paragraph1;
    public String paragraph2;
    public String paragraph3;
    public String platser;
    public String plats1;
    public String plats2;
    public String plats3;
    private String url1;
    private String url12;
    private String url2;
    private String url3;
    private String utmessage;
    public OptPlan op;
    public DataStore ds;
    public ControlUI cui;
    private String gruppmessage;
    
    
    //För att ta reda på vilka uppdragsplatser som finns vart
    private static String URL1 = ("http://tnk111.n7.se/listaplatser.php");
       //För att hämta ett uppdrag på platsen
    private static String URL12 = ("http://tnk111.n7.se/listauppdrag.php?plats=A");
     //För att skicka ett meddelande till HTTP- servern
    private static String URL2 = ("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=1&message=hejhejhej");

    private static String URL3 = ("http://tnk111.n7.se/getmessage.php?messagetype=1");


    //För att läsa de meddelanden som grupper skickat
//  private static String URL3 = ("http://tnk111.n7.se/getmessage.php?messagetype=2");
    ////För att ta reda på vilka uppdrag som finns på plats A
  //  private static String URL3 = ("http://tnk111.n7.se/listauppdrag.php?plats=A");
    

    private int sleepTime;

    public HTTPanrop(DataStore ds, OptPlan op, ControlUI cui) {
        //  this.cui = cui;
        this.ds = ds;
        this.op = op;
        this.cui= cui;
        //sleepTime = generator.nextInt(20000);
        sleepTime = 1000; //1000 millisekunder
    }
    @Override
    public void run() { //Lägg till en 

        url1 = URL1;
        url12= URL12;
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
            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }

            inkommande.close();
            message = inkommande_samlat.toString();
            //System.out.println(message);
            String[] paras = message.split(" ");

            for (int i = 0; i < paras.length; i++) {

                paragraph1 = paras[i];
                System.out.println("Parametrar: " + paragraph1);
            }
            
            //Delar upp uppdragsplatserna. Om vi får fler uppdragsplatser 
            //behöver vi ändra detta eftersom det är hårdkodat
            
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
//        
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
            }

            inkommande.close();
            messageupp = inkommande_samlat.toString();
            //System.out.println(message);
            String[] paras = messageupp.split(" ");

            for (int i = 0; i < paras.length; i++) {

                paragraph1 = paras[i];
                System.out.println("Parametrar: " + paragraph1);
            }
           
            //cui.showStatus(messageupp);
            cui.showStatus(paragraph1);
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
//        }
//        
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

            int distance= op.pathCost;
            

            // Send post request
            anslutning.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(anslutning.getOutputStream());
            //wr.writeBytes(distance);
            wr.flush();
            wr.close();

            int responseCode = anslutning.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url2);// + paragraph1);
            System.out.println("Post parameters : " + distance); //vad vi vill lägga upp?
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(anslutning.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            utmessage = response.toString();

            String[] paras = utmessage.split(";" + "");

            for (int i = 0; i < paras.length; i++) {
                paragraph2 = paras[i];

                System.out.println("Mottaget meddelande: " + paragraph2);

            }
            cui.svarHTTP(paragraph2);
            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception e) {

            //print result
            System.out.println(e.toString());
        }
            try { //vad vi hämtar hem

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
                }

                inkommande.close();
                gruppmessage = inkommande_samlat.toString();

                String[] paras = gruppmessage.split("");

                for (int i = 0; i < paras.length; i++) {

                    paragraph3 = paras[i];

                }
                Thread.sleep(2000); //vilken sleeptime?
                cui.svarHTTP("Tid för meddelandet osv: " + "\n"+ gruppmessage);

               String infogrupp = gruppmessage.substring(1,8);
                
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
