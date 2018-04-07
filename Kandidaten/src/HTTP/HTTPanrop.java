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
    int grupp; 
    
    Public Listaplatser() //listar alla platser som finns att aka till
{
    String lista = "http://tnk111.n7.se/listaplatser.php";
    URL urllista = new URL(lista);
 return lista; // anger först det antal upphämtningsplatser som finnsdefinierade.
}

Public Listauppdrag(String plats) //listar alla uppdrag som kommer att finnas på kartan
{

String listauppdraghttp://tnk111.n7.se/listauppdrag.php

public Tauppdrag( int plats, int id, int passagerare, int grupp) // listar alla uppdrag som tas upp på kartan
{


}

public Aterstall(int scenario) // återställer allt till noll så AGVn är redo för ett nytt uppdrag. 
{


}
    

    public static void main(String[] args) {
        
        try {
            
            
            HTTPanrop http = new HTTPanrop();
            
            
            String url = "http://tnk111.n7.se/aterstall.php?scenario=1";
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
            System.out.println(inkommande_samlat.toString());
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    } 
} 




       
//http://tnk111.n7.se/getmessage.php?messagetype=2 http för kommunikation mellan företagsgrupperna.