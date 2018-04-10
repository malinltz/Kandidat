
package HTTP;


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
