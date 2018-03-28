/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTTP;

/**
 *
 * @author malinlilliecreutz
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPanrop {
 
    int plats;
    int scenario;
    int 

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

public void listaplatser()
{
    String lista = "http://tnk111.n7.se/listaplatser.php";
    URL urllista = new URL(lista);
 return lista
}

public listauppdrag(plats){

listauppdrag.php

public tauppdrag( plats, id, passagerare, grupp){


}

public aterstall(scenario){


}

       

//http://tnk111.n7.se/listaplatser.php?scenario=1 

//http://tnk111.n7.se/aterstall.php?scenario=1