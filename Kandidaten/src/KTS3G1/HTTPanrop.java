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
    private String urlupp;
    public OptPlan OP;
    public DataStore ds;

   /*
    double plats;
    double scenario;
    int id; // heltal
    double passagerare;
    int gruppid = 1; // säger vilken grupp det är
    double messagetype;
    int points; // poäng för varje 
    
    public void Listaplatser() //listar alla platser som finns att aka till
    {
        try {
            HTTPanrop http = new HTTPanrop();
            String lista = "http://tnk111.n7.se/listaplatser.php";
            URL urllista = new URL(lista);
            HttpURLConnection anslutning = (HttpURLConnection) urllista.openConnection();
            System.out.println("\nAnropar: " + lista);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);
            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();
            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }
            inkommande.close();
            System.out.println(inkommande_samlat.toString());
           // return lista; // anger först det antal upphämtningsplatser som finnsdefinierade.

        } catch (Exception lista) {
            System.out.print(lista.toString());

        }
    } 
        //På de efterföljande raderna i svaret listas platsens namn (A, B etc) och 
        //två nod-nummer separerade med komma som svarar mot den länk gatunätet där platsen finns fysiskt. 
        //Platsens namn och nod-numren separeras med ett semikolon

    

    public void Listauppdrag(double plats) //listar alla uppdrag som kommer att finnas på kartan
    {
        try {
            // får göras hur många gånger som helst men inte längre än en second. 
            HTTPanrop http = new HTTPanrop();
            String listauppdrag = "http://tnk111.n7.se/listauppdrag.php";
            URL urllistauppdrag = new URL(listauppdrag);
            HttpURLConnection anslutning = (HttpURLConnection) urllistauppdrag.openConnection();
            System.out.println("\nAnropar: " + listauppdrag);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);
            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();
            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }
            inkommande.close();
            System.out.println(inkommande_samlat.toString());
           

        } catch (Exception listauppdrag) {
            System.out.print(listauppdrag.toString()); // 
        }
        }
        //Först presenteras antalet uppdrag som finns tillgängliga, därutöver en lista som anger id-nummer;
        //destination vilken anges som två nod-nummer separerade med kommatecken; 
        //antal passagerare i uppdraget; om passagerarna kan tänka sig att samåka eller inte (1 indikerar att samåkning är möjlig,
        //0 indikerar att personerna i uppdraget inte kan samåka med andra uppdrag); och en poäng, för varje uppdrag. 
        //Varje uppdrag avgränsas på en ny rad, och varje element på raden avgränsas med ett semikolon.
        //Uppdragets poäng kan ses som den ”intäkt” som fås när uppdraget genomförs

    

    public String Tauppdrag(int plats, int id, int passagerare, double grupp) // listar alla uppdrag som tas upp på kartan
    {
        try {

            HTTPanrop http = new HTTPanrop();
            String tauppdrag = "http://tnk111.n7.se/listauppdrag.php";
            URL urltauppdrag = new URL(tauppdrag);
            HttpURLConnection anslutning = (HttpURLConnection) urltauppdrag.openConnection();
            System.out.println("\nAnropar: " + tauppdrag);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);
            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();
            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }
            inkommande.close();
            System.out.println(inkommande_samlat.toString());
            

        } catch (Exception listauppdrag) {
            System.out.print(listauppdrag.toString());
        }
    

    public void Aterstall(int scenario) // återställer allt till noll så AGVn är redo för ett nytt uppdrag. 
    {
// från användargränsnittet ska det finnas en återställknapp. 
        try {
            // får göras hur många gånger som helst men inte längre än en second. 
            HTTPanrop http = new HTTPanrop();
            String aterstall = "http://tnk111.n7.se/listauppdrag.php";
            URL urlaterstall = new URL(aterstall);
            HttpURLConnection anslutning = (HttpURLConnection) urlaterstall.openConnection();
            System.out.println("\nAnropar: " + aterstall);

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);
            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();
            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }
            inkommande.close();
            System.out.println(inkommande_samlat.toString());

        } catch (Exception aterstall) {
            System.out.print(aterstall.toString());
        }
    }
     */
    public String HTTPanrop(String URL) {
        
        url = URL;
        OP = new OptPlan(ds);
     
        try {

           // String url = "http://tnk111.n7.se";
            URL urlobjekt = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt.openConnection();
            System.out.println("\nAnropar: " + url);
            // anslutning.setRequestMethod("GET"); // ny kod
            ;

            int mottagen_status = anslutning.getResponseCode();
            System.out.println("Statuskod: " + mottagen_status);

            BufferedReader inkommande = new BufferedReader(new InputStreamReader(anslutning.getInputStream()));
            String inkommande_text;
            StringBuffer inkommande_samlat = new StringBuffer();
            while ((inkommande_text = inkommande.readLine()) != null) {
                inkommande_samlat.append(inkommande_text);
            }
            inkommande.close();
            System.out.println(inkommande_samlat.toString());
            message = inkommande_samlat.toString();
            System.out.println("hej");
        } catch (Exception e) {
            System.out.print(e.toString());
            
        }
        return url; 
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
                System.out.println(response.toString());
                message = response.toString();
		in.close();
            }catch(Exception e){
		
		//print result
		System.out.println(e.toString());
            }
           // return url;
	
}


    public String newmesssage() {
        return message;
    }
  
    
}
 


//http://tnk111.n7.se/getmessage.php?messagetype=2 http för kommunikation mellan företagsgrupperna.
// get and post parametrar
// gruppid = 1 
