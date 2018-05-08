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
//import java.util.Arrays;
import java.util.ArrayList;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class HTTPny implements Runnable {

    public String message;
    public String uppdragslista;
    public String narmstaPlats;
    private String gruppmessage;
    private String utmessage;
    public String inmessa;

    //  private String uppdragsplatser;
    public OptPlan op;
    OptPlan[] opt;
    OptPlan pathOpt1;
    OptPlan pathOpt2;
    public DataStore ds;
    public ControlUI cui;
    public RobotRutt RR;
    public HTTPextern httpex;


    public String plats;
    public String ID;
    public String passagerare;
    public String grupp;
    public String listaplats;//startnod eller? 
    public int storlek; //
    public String gruppess;
    public int uppsizeInt;
    public int meddelandet;

    int paxplats[];
    int kostnad[];
    String uppdrag[];
    String uppdrag_valt;
    public int uppdrags_counter;
   
    public int[] startlist;
    public int[] stopplist;

    int[] uppdrag1;
    int[] uppdrag2;
    String datumer;

    String[] uppdragsid;
    String[] destination;
    int[] nuPoints;
    int[] destNod1;
    int[] destNod2;
    int[] pass;
    int[] samakning;
    private int sleepTime;
    public int narmstaNod;
    public int narmstaNod2;
    public int narmstaNod3;
    double lagstaKostnad = 1000000;
    int u = 0;

    ArrayList<String> ink;
    ArrayList<String> upp;
    ArrayList<String> inmess; //meddelandet in från företagsguppen
    ArrayList<String> utmess; //meddelandet ut från oss till företagsgrupperna

    public HTTPny(DataStore ds, OptPlan op, ControlUI cui) {
        this.ds = ds;
        this.op = op;
        this.cui = cui;
        sleepTime = 1000; //1000 millisekunder

        ink = new ArrayList<String>();
        upp = new ArrayList<String>();
        inmess = new ArrayList<String>();
        utmess = new ArrayList<String>();
    }

    @Override 
    public void run() {
        try{
            
        while(u < 1000){ //Måste ändras från 1000 till vad de nu ska va för att fortsätta köra..?
            
            Listaplats(); //Optimerar rutt till upphämtningsplats
             
            utmessages(narmstaPlats); //Laddar upp vilken upphämtningsplats vi vill ha
            
            //Här någonstans checkar den vilken uppdragsplats vi får från externa protokollet.
            //httpex.exprotokoll();
            //Ger oss en upphämtningsplats
            
            ds.start = ds.robotPos; //Uppdaterar robotens start och slutnoder
            ds.slut = narmstaNod;
            
            op = new OptPlan(ds); //Optimerar till den plats vi blev tilldelade
            op.createPlan();
            
            //Här kallas transiever, men den körs redan eftersom det är en TRÅD.
            
            //GuiUpdate r1 = new GuiUpdate(ds, cui, op, this); //Ritar ut roboten på kartan. 
            //Thread t2 = new Thread(r1); //Måste lägga till i GuiUpdate om AGV utfört ett direktiv så uppdateras kartan.
            //t2.start();
            
            uppdrag_valt = listauppdrag(narmstaPlats); //Listar uppdragen på upphämtningsplatsen samt gör optimering
            
            int dummy;
            dummy = Integer.parseInt(uppdrag_valt);
            ds.totPoang = ds.totPoang + nuPoints[dummy];  //Här beräknar vi poäng för uppdraget
            cui.showStatus(ds.totPoang);
            
            //Någonstans här kolla antalet passagerare
            
            //utmassage(String plats?) här kanske?
            
            String svaruppdrag = tauppdrag(narmstaPlats, uppdrag_valt, passagerare, "1"); //Plats, ID, Passagerare, Grupp
            
            if (svaruppdrag.equals("beviljas")){
                
                for(int j=0; j <128; j++){    //128 stycken bågar totalt? Stämmer detta? pls kolla någon.
                    ds.arcColor[j] = 0;
                }
                
                
                ds.start = stopplist[Integer.parseInt(uppdrag_valt)-1];
                ds.slut = destNod1[Integer.parseInt(uppdrag_valt)-1];
                
                op = new OptPlan(ds);
                op.createPlan();
                
                //Här kallas transiever, men den körs redan eftersom det är en TRÅD.
                
                //GuiUpdate r1 = new GuiUpdate(ds, cui, op, this); //Ritar ut roboten på kartan.
                //Thread t2 = new Thread(r1); //Måste lägga till i GuiUpdate om AGV utfört ett direktiv så uppdateras kartan.
                //t2.start();
                
                
            }
            else {System.out.println("Svar från hemsida: " + svaruppdrag);
            }
            
                ds.start = destNod1[Integer.parseInt(uppdrag_valt)-1];
                u++;
    
             
        } 
        }catch (NumberFormatException e) { 
            System.out.print(e.toString()); 
        }
    }

    public void Listaplats() {

        try { // Kopplar upp till listan och hämtar info och returnerar
            String url = ("http://tnk111.n7.se/listaplatser.php");

            URL urlobjekt1 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt1.openConnection();
            System.out.println("\nAnropar: " + url);
            anslutning.setRequestMethod("GET"); // ny kod

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
                //System.out.println("Upphämtningsplatser: " + ink.get(k));

                cui.appendStatus3(ink.get(k));
            }

            listaplats = ink.get(0);
            storlek = Integer.parseInt(listaplats);
            String[] sline;
            String platser[] = new String[storlek];
            String listans[] = new String[storlek];

            startlist = new int[storlek];
            stopplist = new int[storlek];

            for (int j = 1; j < storlek + 1; j++) {
                sline = ink.get(j).split(";");
                platser[j - 1] = sline[0];
                listans[j - 1] = sline[1];
            }

            for (int i = 0; i < storlek; i++) {
                sline = listans[i].split(",");
                startlist[i] = Integer.parseInt(sline[0].trim());
                stopplist[i] = Integer.parseInt(sline[1].trim());

            }

            for (int j = 0; j < storlek; j++) {
                
                ds.start = ds.robotPos; 
                ds.slut = startlist[j];
                op = new OptPlan(ds);
                op.createPlan();

                cui.svarHTTP("Upp.Plats: " + platser[j] + " från " + ds.start + " till " + ds.slut + ", kostnad: " + op.pathCost);

                if (op.pathCost < lagstaKostnad) {
                    lagstaKostnad = op.pathCost;
                    narmstaPlats = platser[j];
                    narmstaNod = startlist[j];
                    narmstaNod2 = stopplist[j];
                }
            }
        } catch (Exception c) {
            System.out.print(c.toString());
        }
    }

    public String listauppdrag(String plats) {

        try {
            String url = ("http://tnk111.n7.se/listauppdrag.php?plats=" + plats);

            URL urlobjekt1 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt1.openConnection();
            System.out.println("\nAnropar: " + url);
            anslutning.setRequestMethod("GET");
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
                //System.out.println("Uppdrag: " + upp.get(k));
            }
            while (upp.get(0) != null) { //Kollar så att det finns uppdrag på platsen

            uppdragslista = inkommande_samlat.toString();

            //Variabler för uppdragslistan
            String uppsize = upp.get(0);
            uppsizeInt = Integer.parseInt(uppsize);
            String[] slice;
            uppdragsid = new String[uppsizeInt];
            destination = new String[uppsizeInt];
            pass = new int[uppsizeInt];
            samakning = new int[uppsizeInt];
            nuPoints = new int[uppsizeInt];
            destNod1 = new int[uppsizeInt];
            destNod2 = new int[uppsizeInt];

            //Delar upp uppdragslistan i ID,Destination,Passagerare,Samåkning,Poäng
            for (int k = 1; k < uppsizeInt + 1; k++) {
                slice = upp.get(k).split(";");
                uppdragsid[k - 1] = slice[0];
                destination[k - 1] = slice[1];
                pass[k - 1] = Integer.parseInt(slice[2]);
                samakning[k - 1] = Integer.parseInt(slice[3]);
                nuPoints[k - 1] = Integer.parseInt(slice[4]);
                
                cui.hallplatsuppdrag("Uppdrag som finns kvar: ");

                //Skriver ut i Statusrutan alla uppdrag på just den hållplatsen
                cui.hallplatsuppdrag("ID: " + uppdragsid[k - 1] + ", Dest: " + destination[k - 1]
                        + ", Pass: " + pass[k - 1] + ", Sam: " + samakning[k - 1]
                        + ", P: " + nuPoints[k - 1] + "");
            }

            for (int j = 0; j < uppsizeInt; j++) {
                slice = destination[j].split(",");
                destNod1[j] = Integer.parseInt(slice[0]);
                destNod2[j] = Integer.parseInt(slice[1]);
                cui.destination("Dest. mellan noderna: " + destNod1[j] + " & " + destNod2[j]);
            }

            for (int j = 0; j < uppsizeInt; j++) {

                ds.slut = destNod1[j];
                op = new OptPlan(ds);
                op.createPlan();

                cui.svarHTTP("Upp.Plats: " + destination[j] + " från " + ds.start + " till " + ds.slut + ", kostnad: " + op.pathCost);

                if (op.pathCost < lagstaKostnad) {
                    lagstaKostnad = op.pathCost;
                    narmstaPlats = destination[j];
                    narmstaNod = destNod1[j];
                    narmstaNod2 = destNod2[j];
                }
            }
            for (int j=0; j<uppsizeInt; j++){

            if (pass[j]<=ds.kapacitet)//kollar kapacitet jämfört med passagerare 
            {
            uppdrag_valt=uppdragsid[j];//vet inte riktigt vad denna gör 
            
                //Skriver ut vilket uppdrag vi har tagit i statusruta
                    cui.tauppdrag("Plats: " + plats + ", ID: " + ID
                    + ", Pass: " + passagerare + ", Grupp: " + grupp + "");
                    
                break;
                   
            }
            else if (j==(uppsizeInt-1)) //om kapaciteten är max 
            {
             cui.appendStatus("Vi kan inte ta emot fler");
            }
        }
        }
            


        } catch (Exception c) {
            System.out.print("Fel: " + c.toString());

        }
        return uppdragslista;
    }

    public String aterstall(int Scenarionr) {

        try { //vad vi hämtar hem från de andra 
            String url = ("http://tnk111.n7.se/aterstall.php?scenario=" + Scenarionr);

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
                utmess.add(inkommande_text);
            }

            inkommande.close();
            // for (int k = 0; k < utmess.size(); k++) {
            //     System.out.println("Ink: " + utmess.get(k));
            //  }
            gruppmessage = inkommande_samlat.toString();

        } catch (Exception k) {
            System.out.print(k.toString());
        }
        return gruppmessage;
    }

    public void inmessages() {

        String inmessa = "!" + narmstaPlats + "!" + lagstaKostnad + "!" + ID;

        try { //vad vi hämtar hem från de anrda 
            String url = ("http://tnk111.n7.se/getmessage.php?messagetype=1" + inmessa);

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
                inmess.add(inkommande_text);
            }

            inkommande.close();

            for (int k = 0; k < inmess.size(); k++) {
                System.out.println("Ink: " + inmess.get(k));
            }
            //gruppmessage = inkommande_samlat.toString();

            gruppess = inmess.get(0);
            meddelandet = Integer.parseInt(gruppess);

            String[] sline;
            System.out.println("HEJSAN");
            int datum[] = new int[meddelandet];
            int tid[] = new int[meddelandet];
            int iD[] = new int[meddelandet];
            String resten[] = new String[meddelandet];
            String info[] = new String[meddelandet];
            paxplats = new int[meddelandet];
            kostnad = new int[meddelandet];
            uppdrag = new String[meddelandet];

            uppdrag1 = new int[meddelandet];
            uppdrag2 = new int[meddelandet];

            //Splittar bort datum
            for (int p = 1; p < meddelandet + 1; p++) {
                sline = inmess.get(p).split(" ");
                datum[p - 1] = Integer.parseInt(sline[0]);
                resten[p - 1] = sline[1];
            }

            //Splittar bort tiden
            for (int j = 1; j < meddelandet + 1; j++) {
                sline = resten[j].split(";");
                tid[j - 1] = Integer.parseInt(sline[0]);
                iD[j - 1] = Integer.parseInt(sline[1]); //ID för de som de andra företagsgrupperna
                info[j - 1] = sline[2];
            }

            //Splittar Plats, Kostnad och Vilka uppdrag de vill göra
            for (int f = 1; f < meddelandet + 1; f++) {
                sline = info[f].split("!");
                paxplats[f - 1] = Integer.parseInt(sline[0].trim());
                kostnad[f - 1] = Integer.parseInt(sline[1].trim());
                uppdrag[f - 1] = sline[2];

                cui.appendStatus4(paxplats[f] + " " + kostnad[f] + " " + uppdrag[f]);
                
            }

            //Splittar Vilka uppdrag (Behöves ej?)
            for (int i = 0; i < meddelandet; i++) {

                sline = uppdrag[i].split(",");
                uppdrag1[i] = Integer.parseInt(sline[0].trim());
                uppdrag2[i] = Integer.parseInt(sline[1].trim());

                //   System.out.println(uppdrag1);
            }

            cui.messagegrupper(gruppess);
            //  System.out.println("Bästa uppdrag: " + paxplats[i] + kostnad[i] + uppdrag1 + uppdrag2);
        } catch (Exception k) {
            System.out.print("HEJ MALIN " + k.toString());
        }
    }

    public void utmessages(String platser) {

        //   platser = ( paxplats + "!" + kostnad + "!" + uppdrag) ;
        platser = ("A!750!1,3"); // hämtar info från httpextern

        try { //vad vi hämtar hem från de anrda 

            String url = ("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=1&message=" + platser);

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

                utmess.add(inkommande_text);
            }
            inkommande.close();

            for (int k = 0; k < utmess.size(); k++) {
                //System.out.println("Ink: " + utmess.get(k));
            }
            gruppmessage = inkommande_samlat.toString();

        } catch (Exception k) {
            System.out.print(k.toString());
        }
    }
     public String tauppdrag(String plats, String ID, String passagerare, String grupp) {

        try { //lägger upp uppdrag
            String url = ("http://tnk111.n7.se/tauppdrag.php?plats" + plats + "&id" + ID + "&passagerare=" + passagerare + "&grupp" + grupp);

            URL urlobjekt2 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt2.openConnection();

            anslutning.setRequestMethod("POST");
            anslutning.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(anslutning.getOutputStream());
            wr.flush();
            wr.close();

            System.out.println("\nSending 'POST' request to URL : " + url);
            int responseCode = anslutning.getResponseCode();
            System.out.println("Statuskod: " + responseCode);

            BufferedReader inkommande = new BufferedReader(
                    new InputStreamReader(anslutning.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = inkommande.readLine()) != null) {
                response.append(inputLine);
                upp.add(inputLine);
            }
            inkommande.close();
            utmessage = response.toString();

            
            //Här väljer vi uppdrag och kollar kapacitet 
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return utmessage;
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
