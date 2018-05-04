

package KTS3G1;

/**
 *
 */
public class KTS3G1 {

    DataStore ds;
    ControlUI cui;
    HTTPny http;
    OptPlan op;
    RobotRutt RR;
    HTTPanrop ht;
    //HTTPextern hx;
    //Transceiver tc; 
    //Reciver re; 

    String badress="201410149018"; 
    String kanal = "1";
    

    String URL;
  //  String URL1;
  //  String URL12;
  //  String URL2;
  //  String URL3;
    
    //String upphamtningsplats="lol"; 
    //String allaUppdrag="Nötallergiker undanbedes och beivras"; 
    //String valtUppdrag="Helt seriöst"; 

   // String upphamtningsplats="hej1"; 
    //String allaUppdrag="hej2"; 
     // String valtUppdrag="Hej3"; 
   



    KTS3G1() {

        //tc= new Transceiver
        //tm = new Transmitter();
        //re = new Reciver()

        http = new HTTPny(ds, op, cui);
        // cui.bluetoothAdress(badress);

      ht = new HTTPanrop(http);

        // http.HTTPanrop("http://tnk111.n7.se/listaplatser.php");
//        http.HTTPkontact("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=2&message=hejhej");
        // http.HTTPuppdrag("http://tnk111.n7.se/getmessage.php?messagetype=2");

        
        /*
         * Initialize the DataStore call where all "global" data will be stored
         */
        ds = new DataStore();

        /*
         * This sets the file path and read network text file. Adjust for your needs.
         */
        ds.setFileName("streets.txt");
        ds.readNet();
        //ds.nodeX
        
        /*
         * Initialize and show the GUI. The constructor gets access to the DataStore
         */
        cui = new ControlUI(ds);
        cui.setVisible(true);
        cui.bluetoothAdress(badress);
        cui.bluetoothchannel(kanal);

        /*   while (cui.anslut == true) {
            Transceiver p1 = new Transceiver(cui);
            Thread t3 = new Thread(p1);
            t3.start();

        }*/
    }

    public static void main(String[] args) {

        /* This is the "main" method what gets called when the application starts
         * All that is done here is to make an instance of the RobotControl class,
         * and thereby, call the RobotControl constructor.
         */
        KTS3G1 x = new KTS3G1();

    }
}


