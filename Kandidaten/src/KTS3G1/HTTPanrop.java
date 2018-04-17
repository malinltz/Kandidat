package KTS3G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.DataOutputStream;


public class HTTPanrop {

    private String message;
    private String url;
    private String utmessage;
    public OptPlan OP;
    public DataStore ds;

   
    public String HTTPanrop(String URL) {
        
        url = URL;
        OP = new OptPlan(ds);
     
        try {

           // String url = "http://tnk111.n7.se";
            URL urlobjekt = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt.openConnection();
            System.out.println("\nAnropar: " + url);
            //anslutning.setRequestMethod("GET"); // ny kod
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
            
          
        } catch (Exception e) {
            System.out.print(e.toString());
            
       }
       return message; 
    }
    
    public void HTTPkontact(String URL){
            url = URL;
            OP = new OptPlan(ds);
            
            try
            {
		URL urlobjekt = new URL(url);
		HttpURLConnection anslutning = (HttpURLConnection) urlobjekt.openConnection();

		//add reuqest header
		anslutning.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "putmessage.php?groupid=1&messagetype=2&message=test";
		
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
<<<<<<< HEAD
                in.close();
                utmessage = response.toString();
                System.out.println(utmessage );
		
=======
                System.out.println(response.toString());
                //message = response.toString();
		in.close();
>>>>>>> 4e3aa34152f3eb67c823b88af2bdf418c3bd52fa
            }catch(Exception e){
		
		//print result
		System.out.println(e.toString());
            }
            return utmessage;
	
}

 
<<<<<<< HEAD
 //   public String newmesssage() {
   //   return message;
  //  }
=======
    public String newmesssage() {
        
        
        return message;
    }
>>>>>>> 4e3aa34152f3eb67c823b88af2bdf418c3bd52fa
  
    
}
