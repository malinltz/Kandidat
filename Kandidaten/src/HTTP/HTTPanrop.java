/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTP;

/*
 * @author malinlilliecreutz
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPanrop {

    double plats;
    double scenario;
    int id; // heltal
    double passagerare;
    int gruppid = 1; // säger vilken grupp det är
    double messagetype;

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

        } catch (Exception lista) 
            System.out.print(lista.toString());

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
            URL listauppdag = new URL(listauppdrag);
            HttpURLConnection anslutning = (HttpURLConnection) listauppdrag.openConnection();
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
            return listauppdrag;

        } catch (Exeption listauppdrag) 
            System.out.print(listauppdrag.toString(); // 

        }

    

    public String Tauppdrag(int plats, int id, int passagerare, double grupp) // listar alla uppdrag som tas upp på kartan
    {
        try {

            HTTPanrop http = new HTTPanrop();
            String tauppdrag = "http://tnk111.n7.se/listauppdrag.php";
            URL tauppdrag = new URL(tauppdrag);
            HttpURLConnection anslutning = (HttpURLConnection) tauppdrag.openConnection();
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
            return tauppdrag;

        } catch (Exeption listauppdrag) 
            System.out.print(listauppdrag.toString());
         
        }

    public void Aterstall(int scenario) // återställer allt till noll så AGVn är redo för ett nytt uppdrag. 
    {
// från användargränsnittet ska det finnas en återställknapp. 
        try {
            // får göras hur många gånger som helst men inte längre än en second. 
            HTTPanrop http = new HTTPanrop();
            String aterstall = "http://tnk111.n7.se/listauppdrag.php";
            URL aterstall = new URL(aterstall);
            HttpURLConnection anslutning = (HttpURLConnection) aterstall.openConnection();
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
            return aterstall;

        } catch (Exeption listauppdrag) 
            System.out.print(aterstall.toString());
        }

     

    public static void main(String[] args) {

        try {

            HTTPanrop http = new HTTPanrop();

            String url = "http://tnk111.n7.se";
            URL urlobjekt = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt.openConnection();
            System.out.println("\nAnropar: " + url);
            anslutning.setRequestMethod("GET"); // ny kod
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
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}

//http://tnk111.n7.se/getmessage.php?messagetype=2 http för kommunikation mellan företagsgrupperna.
// get and post parametrar
// gruppid = 1 
