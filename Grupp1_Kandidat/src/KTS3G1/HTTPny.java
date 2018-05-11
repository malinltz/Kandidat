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
    private String utmessage;
    public String inmessa;

    public OptPlan op;
    OptPlan[] opt;
    public DataStore ds;
    public ControlUI cui;
    public RobotRutt RR;
    public GuiUpdate gu;

   // public HTTPextern httpex;
    int NumberOfpassengers; 
     int antal_passa = 0;
     

    public String plats;
    // public String ID;
    public int passagerare;
    public String grupp;
    public String listaplats;
    public String gruppess;
    public int storlek; //
    public int uppsizeInt;
    public int meddelandet;
    public String[] gruppmessage;

    String[] paxplats;
    // String datum [];
    String[] tid;

    String kostnad[];
    int iD[];
    String uppdrag[];
    public int uppdrag_valt;
    public int uppdrag_valt2;
    public int uppdrags_counter;

    public int[] startlist;
    public int[] stopplist;

    int[] uppdrag1;
    int[] uppdrag2;
    String datumer;

    public int[] uppdragsid;
    String[] destination;

    String[] resten;
    String[] info;
    int[] nuPoints;
    int[] destNod1;
    int[] destNod2;
    int[] pass;
    int[] samakning;
    private int sleepTime = 1000;
    public int narmstaNod;
    public int narmstaNod2;
    public int narmstaNod3;
    public int narmstaNod4;
    int lagstaKostnad = 1000000;
    int u = 0;
    boolean plocka_upp = false;
    ArrayList<String> ink; //alla inkommande platser
    ArrayList<String> upp; // 
    ArrayList<String> inmess; //meddelandet in från företagsguppen
    ArrayList<String> utmess; //meddelandet ut från oss till företagsgrupperna
    ArrayList<String> ater; //återsteller

    public HTTPny(DataStore ds, OptPlan op, ControlUI cui) {
        this.ds = ds;
        this.op = op;
        this.cui = cui;
        sleepTime = 1000; //1000 millisekunder

        ink = new ArrayList<String>(); //alla inkommande platser
        upp = new ArrayList<String>(); // 
        inmess = new ArrayList<String>();//meddelandet in från företagsguppen
        utmess = new ArrayList<String>(); //meddelandet ut från oss till företagsgrupperna
        ater = new ArrayList<String>(); //återsteller
    }

    @Override
    public void run() {


        try {

            while (u < 1) {

                Thread.sleep(sleepTime); //hur länge det ska vara en fördröjning
                //Måste ändras från 1000 till vad de nu ska va för att fortsätta köra..?

                Listaplats(); //Optimerar rutt till upphämtningsplats

                // utmessages(); //Lägger upp vilken uppdragsplats vi vill ha.
                // inmessages(); //Hämtar in vilken upphämtningsplats de andra vill ha.
                //Här någonstans checkar den vilken uppdragsplats vi får från externa protokollet.
                //httpex.exprotokoll();
                //Ger oss en upphämtningsplats
                ds.start = ds.robotPos; //Uppdaterar robotens start och slutnoder
                ds.slut = narmstaNod;

                for (int j = 0; j < 128; j++) {    //Sätter alla 128 stycken bågar totalt till 0. För repaint grejen.

                    ds.arcColor[j] = 0;
                }

                op = new OptPlan(ds); //Optimerar till den plats vi blev tilldelade
                op.createPlan();

                //Här kallas transiever, men den körs redan eftersom det är en TRÅD.
                RR = new RobotRutt(ds, cui, op, this);
                RR.goRobotrutt();

                //gu = new GuiUpdate(ds, cui, op, this); //Ritar ut roboten på kartan. 
                //Thread t2 = new Thread(gu);
                //t2.start();
                //IF PICK-UP HAR HÄNT HÄR -> KÖR RESTEN AV RUN METODEN.
                uppdrag_valt = listauppdrag(narmstaPlats); //Listar uppdragen på upphämtningsplatsen samt gör optimering

                utmessages(); //Lägger upp vilken uppdragsplats vi vill ha.

                inmessages(); //Hämtar in vilken upphämtningsplats de andra vill ha.

                // httpex= new HTTPextern(this, ds);
                // httpex.exprotokoll();
                // String svaruppdrag = tauppdrag(httpex.plats, httpex.ID , passagerare, "1"); //Plats, ID, Passagerare, Grupp
                String svaruppdrag = tauppdrag(narmstaPlats, uppdrag_valt, passagerare, "1"); //Plats, ID, Passagerare, Grupp

                if (svaruppdrag.equals("beviljas")) {
                    System.out.println("Svar från hemsida: " + svaruppdrag);

                    for (int j = 0; j < 128; j++) {    //Sätter alla 128 stycken bågar totalt till 0. För repaint grejen.
                        ds.arcColor[j] = 0;
                    }

                    ds.start = narmstaNod2;
                    ds.slut = narmstaNod3;

                    op = new OptPlan(ds);
                    op.createPlan();

                    //Här kallas transiever, men den körs redan eftersom det är en TRÅD.
                    RR = new RobotRutt(ds, cui, op, this);
                    RR.goRobotrutt();

                    gu = new GuiUpdate(ds, cui, op, this); //Ritar ut roboten på kartan. 

                } else {
                    System.out.println("Svar från hemsida: " + svaruppdrag);
                }
                ds.start = narmstaNod4;
                u++; //counter för antal uppdrag
                // ds.poang ++;

                aterstall(1);

            }
        } catch (InterruptedException e) {
            System.out.print(e.toString());
        }
    }

    public void Listaplats() { // lista på alla besöksplatser.

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
                //System.out.println(ink.get(k));
                cui.appendStatus3(ink.get(k));
            }

            listaplats = ink.get(0); //delar upp infon i  en string
            storlek = Integer.parseInt(listaplats); //gör om till en int
            String[] sline;
            String platser[] = new String[storlek]; //skapar array för platser
            String listans[] = new String[storlek]; //skapar array för listan

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

    public int listauppdrag(String plats) { //läser in alla uppdag på den platsen man befinner sig i.

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

            //while (upp.get(0) != null) { //Kollar så att det finns uppdrag på platsen
            uppdragslista = inkommande_samlat.toString();

            //Variabler för uppdragslistan
            String uppsize = upp.get(0);
            uppsizeInt = Integer.parseInt(uppsize);
            String[] slice;
            uppdragsid = new int[uppsizeInt];
            destination = new String[uppsizeInt];
            pass = new int[uppsizeInt];
            samakning = new int[uppsizeInt];
            nuPoints = new int[uppsizeInt];
            destNod1 = new int[uppsizeInt];
            destNod2 = new int[uppsizeInt];

            cui.hallplatsuppdrag("Uppdrag som finns kvar: ");
            //Delar upp uppdragslistan i ID,Destination,Passagerare,Samåkning,Poäng
            for (int k = 1; k < uppsizeInt + 1; k++) {
                slice = upp.get(k).split(";");
                uppdragsid[k - 1] = Integer.parseInt(slice[0]);
                destination[k - 1] = slice[1];
                pass[k - 1] = Integer.parseInt(slice[2]);
                samakning[k - 1] = Integer.parseInt(slice[3]);
                nuPoints[k - 1] = Integer.parseInt(slice[4]);
                //Skriver ut i Statusrutan alla uppdrag på just den hållplatsen
                cui.hallplatsuppdrag("ID: " + uppdragsid[k - 1] + ", Dest: " + destination[k - 1]
                        + ", Pass: " + pass[k - 1] + ", Sam: " + samakning[k - 1]
                        + ", P: " + nuPoints[k - 1] + "");
            }

            for (int j = 0; j < uppsizeInt; j++) {
                slice = destination[j].split(",");
                destNod1[j] = Integer.parseInt(slice[0]);
                destNod2[j] = Integer.parseInt(slice[1]);
            }

            narmstaNod3 = destNod1[0];
            narmstaNod4 = destNod2[0];

            op = new OptPlan(ds);
            op.createPlan();
            lagstaKostnad = op.pathCost;
            //Dessa nedan behöver inte användas
            //cui.bastaPlats(":");
            //cui.bastaPlats("Upp.Plats: " + destination[0] + " från " + ds.start + " till " + ds.slut + ", kostnad: " + op.pathCost);


            for (int j=0; j <uppsizeInt; j++){
                System.out.println("Samåkning0: " + samakning[j]); 
                    
                uppdrag_valt=uppdragsid[j] ;
                uppdrag_valt2 = uppdragsid[j+1];
            if (samakning[j]==0)
            {
            
                if(pass[j] <= ds.kapacitet)//kollar kapacitet jämfört med passagerare 
                {
                    ds.totPoang=ds.totPoang+nuPoints[j];
                    System.out.println("Totala poäng1: " + ds.totPoang);
              
                }   
                else if (pass[j] > ds.kapacitet)//kollar kapacitet jämfört med passagerare 
                {
                    ds.totPoang=(((pass[j]-ds.kapacitet)/pass[j])*nuPoints[j])+ds.totPoang;
                    //måste ta bort den andel passagerare som vi tagit från uppdraget
                    System.out.println("Totala poäng2: " + ds.totPoang);
                }
            }
              //Någonstans här kolla antalet passagerare

                // uppdrag_valt=uppdragsid[j]; //Väljer uppdraget som är bäst för oss.
            
             //samåkningen ska funka om det är 1 och inte om den är 0. 
                
                else if(samakning[j]==1){ 
                    System.out.println("Samåkning1: " + samakning[j]); 
                if(pass[j] <= ds.kapacitet)//kollar kapacitet jämfört med passagerare 
                {
                    ds.totPoang=ds.totPoang+nuPoints[j];
                  ds.kapacitet=ds.kapacitet-pass[j];
                    System.out.println("Antal passsagerare: " + ds.kapacitet);
                    System.out.println("Totala poäng3: " + ds.totPoang);
                    if (samakning[j+1]==1){
                        System.out.println("Samåkning3: " + samakning[j]); 
                        
                     if(pass[j+1]<=ds.kapacitet)
                    {
                    ds.kapacitet=ds.kapacitet-pass[j];
                    ds.totPoang=ds.totPoang+nuPoints[j+1];
                    System.out.println("Totala poäng4: " + ds.totPoang);
                    }   
                    
                     else if(pass[j+1]>(ds.kapacitet)){
                         
                         ds.kapacitet=ds.kapacitet-pass[j];
                        ds.Antal_passagerare=pass[j+1]-ds.kapacitet;
                         ds.totPoang=(((pass[j+1]-ds.Antal_passagerare)/(pass[j+1]))*(nuPoints[j+1]))+ds.totPoang;
                         System.out.println("Totala poäng5: " + ds.totPoang);
                     }
                         //0
                    
                   // uppdrag_valt=uppdragsid[j] ;
               
                }   
                else if (pass[j] > ds.kapacitet)//kollar kapacitet jämfört med passagerare 
                {
                    
                    ds.Antal_passagerare=pass[j]-ds.kapacitet; 
                    System.out.println("ds.antal passagerare: " + ds.Antal_passagerare);
                    ds.totPoang=(((pass[j]-ds.kapacitet)/pass[j])*nuPoints[j])+ds.totPoang;
                    //måste ta bort den andel passagerare som vi tagit från uppdraget
                    System.out.println("Totala poäng6: " + ds.totPoang);
                }
                      
                           
                }      
                       
                    
                
                
            
                
                
            
            passagerare = pass[j];
            
//                  System.out.println("hejhejhejhejehjehjeh"+ds.Antal_passagerare);
//                   System.out.println("I morgon är en annan dag"+passagerare);
           
               
            //System.out.println(uppdrag_valt);

                //Skriver ut vilket uppdrag vi har tagit i statusruta
                cui.tauppdrag("Plats: " + plats + ", ID: " + uppdrag_valt + "," + uppdrag_valt2
                        + ", Pass: " + passagerare + ", Grupp: 1");

                break;

            }

            ds.Antal_passagerare = ds.Antal_passagerare + passagerare;
        
            }

        } catch (Exception c) {
            System.out.print("Fel: " + c.toString());
            System.out.println("uhejhej");

        }
        return uppdrag_valt;

    }

    /*  public int getPassagerare(int uppdrag_valt){
      
      int passagerardummy = uppdrag_valt;
       
      ds.Antal_passagerare = pass[passagerardummy];  //Funkar inte, blir error.
        
      return NumberOfpassengers; 
    }
     */

    public void inmessages() {

        try { //vad vi hämtar hem från de anrda från hemsidan 
            String url = ("http://tnk111.n7.se/getmessage.php?messagetype=1");

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

            // gruppmessage = inkommande_samlat.toString();
            //Variabler för uppdragslistan
            //  String uppsize = upp.get(0);
            //  uppsizeInt = Integer.parseInt(uppsize);
            for (int k = 0; k < inmess.size(); k++) {
                System.out.println("Ink: " + inmess.get(k));
                cui.messagegrupper(inmess.get(k));
                //sline = inmess.get(k).split(";");

                //skriv ut i rutan
            }

            String[] sline;

            // gruppmessage = inkommande_samlat.toString();
            //gruppess = inmess.get(0);
            meddelandet = inmess.size();

            //det blir tre för vi tar in tre rader
            //  System.out.println(meddelandet+ "KHJH"); //blir tre som vi vill
            // System.out.println(gruppmessage.length+ "KHJHdasfd"); //blir 1
            resten = new String[meddelandet];
            tid = new String[meddelandet];
            iD = new int[meddelandet];
            info = new String[meddelandet];
            paxplats = new String[meddelandet];
            kostnad = new String[meddelandet];
            uppdrag = new String[meddelandet];

            //uppdrag1 = new int[meddelandet];
            //uppdrag2 = new int[meddelandet];
            //Splittar bort datum
            for (int p = 0; p < meddelandet; p++) {

                //  String uppsize = upp.get(0);
                //  uppsizeInt = Integer.parseInt(uppsize);
                sline = inmess.get(p).split(";");
                tid[p] = sline[0];
                iD[p] = Integer.parseInt(sline[1]);
                resten[p] = sline[2];

            
            }
            //Splittar Plats, Kostnad och Vilka uppdrag de vill göra
            for (int f = 0; f < meddelandet; f++) {
                sline = resten[f].split("!");
                
                paxplats[f] = sline[0];
                kostnad[f] = sline[1];
                uppdrag[f] = sline[2];
             // System.out.println(uppdrag[f]);
            }

              for (int f = 0; f < meddelandet; f++) {
                sline = uppdrag[f].split(",");
                 System.out.println(uppdrag[f]);
                //uppdrag1[f] = Integer.parseInt(sline[0]);
               // uppdrag2[f] = Integer.parseInt(sline[1]);
               cui.messagegrupper(iD[f]+ " " + paxplats[f] + " " + kostnad[f] + " " + uppdrag[f]);
            }
            
         // System.out.println(iD.length + " " + paxplats.length + " " + kostnad.length + " " + uppdrag.length);
            // System.out.println("Bästa uppdrag: " + paxplats[i] + kostnad[i] + uppdrag1 + uppdrag2);

        } catch (Exception k) {
            System.out.print("HEJ MALIN " + k.toString());
            //  System.out.print(meddelandet);
        }
    }

    public void utmessages() {

        String inmessa = narmstaPlats + "!" + lagstaKostnad + "!" + uppdrag_valt + "," + uppdrag_valt2; //Vi lägger upp vad vi önskar

        try { //vad vi hämtar hem från de anrda 

            String url = ("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=1&message=" + inmessa);

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

        } catch (Exception k) {
            System.out.print(k.toString());
        }
    }

    public String tauppdrag(String plats, int ID, int passagerare, String grupp) { //hämtar från httpextern

        //exprotokoll()
        //plats = httpex.plats;
        //ID= httpex.uppdragG1.size();
        //sätt in någon typ av loop så den bara kör när vi är på platsen.   
        //while ( boolean =true){}
        try { //lägger upp uppdrag

            String url = ("http://tnk111.n7.se/tauppdrag.php?plats=" + plats + "&id=" + ID + "&passagerare=" + passagerare + "&grupp=1");

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

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return utmessage;
        //returnerar beviljas om uppdraget är kvar och annars nekas
    }

  public int numPassa (int passagerare){
        
           if(antal_passa == 0){    // AGV är tom, inga passagerare finns i bilen. Enbart passagerare kan "gå in i AGV".
              antal_passa = antal_passa + passagerare;
  }
           if(plocka_upp == true){   //Finns redan passagerare i bilen.
              antal_passa = antal_passa + passagerare;
           }
              else if (plocka_upp == false){
                antal_passa = antal_passa - passagerare;
           } 
           plocka_upp = false;
      return antal_passa;
  }
     
    public void aterstall(int Scenarionr) {

        cui.appendStatus("\nÅterställer.");

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
                ater.add(inkommande_text);
            }

            inkommande.close();
            // for (int k = 0; k < utmess.size(); k++) {
            //     System.out.println("Ink: " + utmess.get(k));
            //  }
            //  gruppmessage = inkommande_samlat.toString();
            System.out.print("Återställer");

        } catch (Exception k) {
            System.out.print(k.toString());
        }
        //blir beviljas eller nekas
    }
}
