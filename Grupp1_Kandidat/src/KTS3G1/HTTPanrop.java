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
    public String paragraph;
    private String url1;
    private String url2;
    private String url3;
    private String utmessage;
    public OptPlan op;
    public DataStore ds;
    private String gruppmessage;
    private static String URL1 = ("http://tnk111.n7.se/listaplatser.php");
    private static String URL2 = ("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=2&message=hejhej");
    private static String URL3 = ("http://tnk111.n7.se/getmessage.php?messagetype=2");
    
    private int sleepTime;

    public HTTPanrop(DataStore ds, OptPlan op) {
        //  this.cui = cui;
        this.ds = ds;
        this.op = op;
        //sleepTime = generator.nextInt(20000);
        sleepTime = 1000; //1000 millisekunder
    }

    public void run() {

        url1 = URL1;
        url2 = URL2;
        url3 = URL3;
        op = new OptPlan(ds);

        try { // Kopplar upp till listan
            // String url1 = "http://tnk111.n7.se"; 
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
            System.out.println(message);

            String[] paras = message.split(" ");

            for (int i = 0; i < paras.length; i++) {

                paragraph = paras[i];
                System.out.println("Parametrar: " + paragraph);
            }

            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception e) {
            System.out.print(e.toString());

        }
        try {
            URL urlobjekt2 = new URL(url2);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt2.openConnection();

            //add reuqest header
            anslutning.setRequestMethod("POST");
            //con.setRequestProperty("User-Agent", USER_AGENT);
            //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = message;

            // Send post request
            anslutning.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(anslutning.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = anslutning.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url2);
            System.out.println("Post parameters : " + urlParameters);
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
            System.out.println(utmessage);

            String[] paras = utmessage.split(";" + "");

            for (int i = 0; i < paras.length; i++) {
                paragraph = paras[i];

                System.out.println("Parametrar: " + paragraph);

            }
            Thread.sleep(2000); //vilken sleeptime?

        } catch (Exception e) {

            //print result
            System.out.println(e.toString());

            try {

                URL urlobjekt3 = new URL(url3);
                HttpURLConnection anslutning = (HttpURLConnection) urlobjekt3.openConnection();
                System.out.println("\nAnropar: " + url3);

                int mottagen_status = anslutning.getResponseCode();
                System.out.println("Statuskod: " + mottagen_status);

                BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
                String inkommande_text;
                StringBuffer inkommande_samlat = new StringBuffer();
                while ((inkommande_text = inkommande.readLine()) != null) {
                    inkommande_samlat.append(inkommande_text);
                }

                inkommande.close();
                gruppmessage = inkommande_samlat.toString();
                System.out.println(gruppmessage);

                String[] paras = gruppmessage.split(";" + "");

                for (int i = 0; i < paras.length; i++) {
                    paragraph = paras[i];
                    System.out.println("Parametrar: " + paragraph);
                }

            } catch (Exception k) {
                System.out.print(k.toString());
            }

        }

    }

    /* public String HTTPkontact(String URL) {
       url = URL;
       op = new OptPlan(ds);

        try {
            URL urlobjekt = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt.openConnection();

            //add reuqest header
            anslutning.setRequestMethod("POST");
            //con.setRequestProperty("User-Agent", USER_AGENT);
            //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = message;

            // Send post request
            anslutning.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(anslutning.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = anslutning.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
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
            System.out.println(utmessage);

            String[] paras = utmessage.split(";" + "");

            for (int i = 0; i < paras.length; i++) {
                paragraph = paras[i];
             
                System.out.println("Parametrar: " + paragraph);

            }
        } catch (Exception e) {

            //print result
            System.out.println(e.toString());
        }
        return utmessage;

    }

    
    public String HTTPuppdrag(String URL) {

       url = URL;
       op = new OptPlan(ds);

        try {

            URL urlobjekt = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt.openConnection();
            System.out.println("\nAnropar: " + url);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();
            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }

            inkommande.close();
            gruppmessage = inkommande_samlat.toString();
            System.out.println(gruppmessage);

            String[] paras = gruppmessage.split(";" + "");

            for (int i = 0; i < paras.length; i++) {
                paragraph = paras[i];
                System.out.println("Parametrar: " + paragraph);
            }
//
        } catch (Exception e) {
            System.out.print(e.toString());

        }
        return gruppmessage;
     */
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
