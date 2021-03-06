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

    private boolean status;
    public String message;
    public String uppdragslista;
    public String narmstaPlats;
    public String narmstaPlats1;
    public String narmstaPlats2;
    private String utmessage;
    public String inmessa;
    
   public String svaruppdrag2;

    public OptPlan op;
    OptPlan[] opt;
    public DataStore ds;
   // public Transceiver tc;
    // DataStore ds = new DataStore();
    public ControlUI cui;
    public RobotRutt RR;
  //  public GuiUpdate gu;
    public HTTPextern httpex;

    int NumberOfpassengers;
    int antal_passa = 0;

    public String plats;
    // public String ID;
    public int passagerare;
    public int passagerare2;
    public String grupp;
    public String listaplats;
    public String gruppess;
    public int storlek; //
    public int uppsizeInt;
    public int meddelandet;
    public String[] gruppmessage;
    public String svaruppdrag;

    String[] paxplats;
    // String datum [];
    String[] tid;
    String[] platser;

    String kostnad[];
    int iD[];
    String uppdrag[];
    public int uppdrag_valt;
    public int uppdrag_valt2;
    public String allauppdrag;
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
    public int narmstaNod5;
    public int narmstaNod6;
    int lagstaKostnad = 1000000;
    int lagstaKostnad2 = 1000000;
    int u = 0;
    boolean plocka_upp = false;
    ArrayList<String> ink; //alla inkommande platser
    ArrayList<String> upp; // 
    ArrayList<String> inmess; //meddelandet in från företagsguppen
    ArrayList<String> utmess; //meddelandet ut från oss till företagsgrupperna
    ArrayList<String> ater; //återsteller

    public static volatile boolean test = false;

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
                aterstall(1);
                Thread.sleep(sleepTime); //Behöver vi fördröjning?            

                Listaplats(); //Optimerar rutt till upphämtningsplats

                listauppdrag(narmstaPlats); //Listar uppdragen på upphämtningsplatsen samt gör optimering

                utmessages(); //Lägger upp vilken uppdragsplats vi vill ha.

                inmessages(); //Hämtar in vilken upphämtningsplats de andra vill ha.

                httpex = new HTTPextern(this, ds); //Använder Externa för att bestämma vilken plats vi får
                httpex.exprotokoll();

                if (u < 1) {

                    ds.start = ds.robotPos; //Uppdaterar AGV:ns startnod i första iterationen endast
                }
                ds.slut = narmstaNod; //Uppdaterar AGV:ns slutnod

                for (int j = 0; j < 128; j++) {    //Sätter alla 128 stycken bågar totalt till 0. För repaint grejen.
                    ds.arcColor[j] = 0;
                }

                cui.repaint(); //Repaintar

                op = new OptPlan(ds, cui); //Optimerar till den plats vi blev tilldelade
                op.createPlan();

                //Här kallas transiever, men den körs redan eftersom det är en TRÅD.
                RR = new RobotRutt(ds, cui, op, this);
                RR.goRobotrutt(); //Använder optimala rutten för att skicka kommandon till AGV:n

              //  gu = new GuiUpdate(ds, cui, op, this); //Uppdaterar AGV:ns position på kartan från startnod till slutnod
              //  Thread t2 = new Thread(gu);
              //  t2.start();

                // Thread.sleep(5000); //Behöver vi fördröjning?     
                while (true) { //Letar efter en Pick-Up

                    //    IF PICK-UP HAR HÄNT HÄR -> KÖR RESTEN AV RUN METODEN.
                  //  if (Transceiver.utfort.equals("p")) {
                        System.out.println("DET FUNKAR");
                        cui.appendStatus("Wall-E har nu lämnat/plockat upp passagerare");

                        listauppdrag(httpex.platsViFick2); //Listar uppdragen på upphämtningsplatsen samt gör optimering
                        
                         if (uppdrag_valt2 != 0){
                          //Här tar vi uppdrag!!
                          
                         String valdaupp=uppdrag_valt +","+ uppdrag_valt2;
                         String passar= passagerare +"," +passagerare2;
                         svaruppdrag = tauppdrag(httpex.platsViFick2, valdaupp, passar, "1"); //Plats, ID, Passagerare, Grupp
                        }
                         else svaruppdrag = tauppdrag(httpex.platsViFick2, String.valueOf(uppdrag_valt), String.valueOf(passagerare), "1"); //Plats, ID, Passagerare, Grupp
                        //    Här tar vi uppdrag!!
                        
                        if (svaruppdrag.equals("beviljas")) { //OM VI KAN TA UPPDRAGET
                            System.out.println("Svar från hemsida: " + svaruppdrag);
                            
                            ds.visualPassenger = ds.visualPassenger + ds.Antal_passagerare;
                            ds.visualPassenger2 = String.valueOf(ds.visualPassenger);
                            cui.appendPassText(ds.visualPassenger2);

                            ds.visualPoints = ds.visualPoints + ds.totPoang;
                            ds.visualPoints2 = String.valueOf(ds.visualPoints);
                            cui.appendPointsText(ds.visualPoints2);
                          

                            for (int j = 0; j < 128; j++) {    //Sätter alla 128 stycken bågar totalt till 0. För repaint grejen.
                                ds.arcColor[j] = 0;
                            }
                            cui.repaint(); //Repaintar

                            ds.start = narmstaNod2; //Sätter nya startnod
                            ds.slut = narmstaNod3; //Sätter ny slutnod

                            op = new OptPlan(ds, cui); //Optimerar till det/dem uppdrag som vi valt
                            op.createPlan();

                            //  Här kallas transiever, men den körs redan eftersom det är en TRÅD.
                            RR = new RobotRutt(ds, cui, op, this);
                            RR.goRobotrutt(); //Använder optimala rutten för att skicka kommandon till AGV:n

                          //  gu = new GuiUpdate(ds, cui, op, this); //Uppdaterar AGV:ns position på
                            
                            ds.start = narmstaNod4; //Updaterat startnod
                            
                            Thread.sleep(30000);
                            
                            if (uppdrag_valt2 != 0) {
                                

                        while (true) { //Letar efter en Pick-Up

                            //IF PICK-UP HAR HÄNT HÄR -> KÖR RESTEN AV RUN METODEN.
                          //  if (Transceiver.utfort.equals("p")) {
                                cui.appendStatus("Wall-E har nu lämnat/plockat upp passagerare");
                
                                    for (int j = 0; j < 128; j++) {    //Sätter alla 128 stycken bågar totalt till 0. För repaint grejen.
                                        ds.arcColor[j] = 0;
                                    }
                                    cui.repaint(); //Repaintar

                                    ds.start = narmstaNod4; //Sätter nya startnod
                                    ds.slut = narmstaNod5; //Sätter ny slutnod

                                    op = new OptPlan(ds, cui); //Optimerar till det/dem uppdrag som vi valt
                                    op.createPlan();

                                    //Här kallas transiever, men den körs redan eftersom det är en TRÅD.
                                    RR = new RobotRutt(ds, cui, op, this);
                                    RR.goRobotrutt(); //Använder optimala rutten för att skicka kommandon till AGV:n

                                  //  gu = new GuiUpdate(ds, cui, op, this); //Uppdaterar AGV:ns position på 

                                    break;
                                }
                            }
                             
                       // }
                          ds.start = narmstaNod6;//daterat startnod
                        //OM VI INTE KAN TA UPPDRAGET
                            System.out.println("Svar från hemsida: " + svaruppdrag);  
                    }

                        break;

                        
                        }

                   // }
                
                u++;
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
                cui.appendStatus3(ink.get(k)); //Denna är OK. /R
            }

            listaplats = ink.get(0); //delar upp infon i  en string
            storlek = Integer.parseInt(listaplats); //gör om till en int
            String[] sline;
            platser = new String[storlek]; //skapar array för platser
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
                op = new OptPlan(ds, cui);
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

    public void listauppdrag(String plats) { //läser in alla uppdag på den platsen man befinner sig i.

        try {
            String url = ("http://tnk111.n7.se/listauppdrag.php?plats=" + plats);

            URL urlobjekt1 = new URL(url);
            HttpURLConnection anslutning = (HttpURLConnection) urlobjekt1.openConnection();
            System.out.println("\nAnropar: " + url);
            anslutning.setRequestMethod("GET");

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
                cui.hallplatsuppdrag("ID: " + uppdragsid[k - 1] + ", Dest: " + destination[k - 1] //Denna är OK. /R
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
            narmstaNod5 = destNod1[1];
            narmstaNod6 = destNod2[1];
            //   System.out.println(destNod1[0]);
            for (int j = 0; j < uppsizeInt; j++) {
                ds.start = narmstaNod2;
                ds.slut = destNod1[j];
                op = new OptPlan(ds, cui);
                op.createPlan();
                
                if (op.pathCost < lagstaKostnad2) {
                    lagstaKostnad2 = op.pathCost;
                    narmstaPlats1 = destination[j];
                    narmstaNod3 = destNod1[j];
                    narmstaNod4 = destNod2[j];
                    narmstaPlats2 = destination[j + 1];
                    narmstaNod5 = destNod1[j + 1];
                    narmstaNod6 = destNod2[j + 1];
                }

            }
            
       
            for (int j = 0; j < uppsizeInt; j++) {

                uppdrag_valt = uppdragsid[0];
                
                if (samakning[j] == 0) {
                    if (pass[j] <= ds.kapacitet)//kollar kapacitet jämfört med passagerare 
                    {
                        ds.totPoang = ds.totPoang + nuPoints[j]; //plussar på poängen 
                       // System.out.println("Totala poäng kap större 0: " + ds.totPoang);
                        ds.kapacitet = ds.kapacitet - pass[j];
                        //uppdrag_valt2 = 0; //sätt den så den inte skrivs ut
                        
                       passagerare= pass[j];
                        
                        
                        cui.tauppdrag("Plats: " + plats + ", ID: " + uppdrag_valt
                                + ", Pass: " + passagerare + ", Grupp: 1");
                    } else if (pass[j] > ds.kapacitet && ds.kapacitet > 0)//kollar kapacitet jämfört med passagerare 
                    {   
                        ds.Antal_passagerare = pass[j] - ds.kapacitet;
                        ds.kapacitet = ds.kapacitet - ds.Antal_passagerare; //ska bli noll

                        //System.out.println(ds.kapacitet);
                        // System.out.println(pass[j] + "hlkasdjlfsk");
                        ds.totPoang = ds.Antal_passagerare;
                        //måste ta bort den andel passagerare som vi tagit från uppdraget
                        //System.out.println("Totala poäng kap mindre 0: " + ds.totPoang);
                        //System.out.println(ds.Antal_passagerare);
                        passagerare = ds.Antal_passagerare;
                        cui.tauppdrag("Plats: " + plats + ", ID: " + uppdrag_valt
                                + ", Pass: " + passagerare + ", Grupp: 1");
                    } else {
                        passagerare = ds.Antal_passagerare;
                    }
                    cui.tauppdrag("Plats: " + plats + ", ID: " + uppdrag_valt
                            + ", Pass: " + passagerare + ", Grupp: 1");
                    {
                        break;
                    }
                    
                } else if (samakning[j] == 1) {
                    //System.out.println("Samåkning1: " + samakning[j]);
                    if (pass[j] <= ds.kapacitet)//kollar kapacitet jämfört med passagerare 
                    { 
                        ds.Antal_passagerare = pass[j];
                        
                        passagerare=ds.Antal_passagerare;

                        ds.totPoang = ds.Antal_passagerare;

                        ds.kapacitet = ds.kapacitet - pass[j];
                        //System.out.println("Kapacitet 1: " + ds.kapacitet);
                        //System.out.println("Totala poäng kap större 1: " + ds.totPoang);
                        if (samakning[j + 1] == 1) {
                            // System.out.println("Samåkning 1.2: " + samakning[j + 1]);//1 
                            if (pass[j + 1] <= ds.kapacitet) {

                                ds.Antal_passagerare2 = pass[j + 1];
                                uppdrag_valt2 = uppdragsid[j + 1];
                              
                                
                                ds.totPoang = ds.Antal_passagerare2+ds.Antal_passagerare;
                                //System.out.println("Totala poäng kap större 1: " + ds.totPoang);
                                passagerare2 = ds.Antal_passagerare2;
                                
                                cui.tauppdrag(
                                        "Plats: " + plats + ", ID: " + uppdrag_valt + "," + uppdrag_valt2
                                        + ", Pass: " + passagerare+ "," +passagerare2 + ", Grupp: 1");


                            } else if (pass[j + 1] > ds.kapacitet && ds.kapacitet > 0) {
                                ds.Antal_passagerare2 =  ds.kapacitet;

                                uppdrag_valt2 = uppdragsid[j + 1];
                                ds.totPoang = ds.Antal_passagerare2+ds.Antal_passagerare;
                                 System.out.println(uppdrag_valt2);
                                //måste ta bort den andel passagerare som vi tagit från uppdraget
                                //System.out.println("Totala poäng kap mindre 1 j+1: " + ds.totPoang);
                                passagerare2 = ds.Antal_passagerare2;
                                
                                cui.tauppdrag(
                                        "Plats: " + plats + ", ID: " + uppdrag_valt + "," + uppdrag_valt2
                                        + ", Pass: " + passagerare +","+ passagerare2 + ", Grupp: 1");
                            } else {
                                passagerare = ds.Antal_passagerare;
                                cui.tauppdrag("Plats: " + plats + ", ID: " + uppdrag_valt
                                        + ", Pass: " + passagerare + ", Grupp: 1");
                            }
                            //om samakaning[j+1]=0
                            {  // remove(Object uppdrag_valt);
                                break;
                            }
                          
                        }
                    } else if (pass[j] > ds.kapacitet && ds.kapacitet > 0)//kollar kapacitet jämfört med passagerare 
                    { 
                        ds.Antal_passagerare = ds.kapacitet;
                        
                        ds.totPoang = ds.Antal_passagerare;
                        //måste ta bort den andel passagerare som vi tagit från uppdraget
                        //System.out.println("Totala poäng kap mindre 1: " + ds.totPoang);
                        //System.out.println(ds.Antal_passagerare);
                        cui.tauppdrag("Plats: " + plats + ", ID: " + uppdrag_valt
                                + ", Pass: " + passagerare + ", Grupp: 1");
                    } else { 
                        break;
                    }
                    //System.out.println("slutkap" + ds.kapacitet);
                    //System.out.println("Antal passagerare: " + ds.Antal_passagerare);
                    //System.out.println("Totala poäng: " + ds.totPoang);
                    //passagerare = ds.Antal_passagerare;
                    //passagerare2 = ds.Antal_passagerare2;
                }
                //ds.visualPassenger = ds.visualPassenger + ds.Antal_passagerare;
            }
        } catch (Exception c) {
            System.out.print("Fel: " + c.toString());
           
        }
        //om den går igenom första så händer det en greh och andra en annan? 
      
       
    }

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
                System.out.println("Hämtat från HTTP : " + inmess.get(k));
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
            }

            for (int f = 0; f < meddelandet; f++) {
                sline = uppdrag[f].split(",");
                cui.messagegrupper("Grupp: " + iD[f] + " Plats: " + paxplats[f] + " Kost: " + kostnad[f] + " Uppd: " + uppdrag[f]); //Denna är OK. /R
            }

        } catch (Exception k) {
            System.out.print("HEJ MALIN " + k.toString());
        }
    }

    public void utmessages() {
        
        
            if (uppdrag_valt2 !=0 ) {
                inmessa = narmstaPlats + "!" + lagstaKostnad + "!" + uppdrag_valt + "," + uppdrag_valt2; //Vi lägger upp vad vi önskar
            }
            else{
                inmessa = narmstaPlats + "!" + lagstaKostnad + "!" + uppdrag_valt; //Vi lägger upp vad vi önskar med bara en 
            }

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

        } catch (Exception k) {
            System.out.print(k.toString());
        }
    }

    public String tauppdrag(String plats, String ID, String passagerare, String grupp) { //hämtar från httpextern

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

    public int numPassa() {

        if (antal_passa == 0) {    // AGV är tom, inga passagerare finns i bilen. Enbart passagerare kan "gå in i AGV".
            antal_passa = antal_passa + passagerare;
        }
        if (plocka_upp == true) {   //Finns redan passagerare i bilen.
            antal_passa = antal_passa + passagerare;
        } else if (plocka_upp == false) {
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
            System.out.print("Återställer");

        } catch (Exception k) {
            System.out.print(k.toString());
        }
        //blir beviljas eller nekas
    }
}
