package KTS3G1;
/**
 *
 */
public class KTS3G1 {

    DataStore ds;
    ControlUI cui;
    HTTPanrop http;
    OptPlan op;
    RobotRutt RR;
    Transceiver tc; 
    String badress; 


    //Reciver re; 
    String kanal = "1";

    String URL1;
    String URL2;
    String URL3;



    KTS3G1() {
         //tc= new Transceiver
        //tm = new Transmitter();
        //re = new Reciver()

        http = new HTTPanrop(ds, op);

      //  http.URL1 = ("http://tnk111.n7.se/listaplatser.php");
      //  http.URL2 = ("http://tnk111.n7.se/listaplatser.php");
      //  http.URL3 = ("http://tnk111.n7.se/listaplatser.php");

        // cui.bluetoothAdress(badress);
        // http.HTTPanrop("http://tnk111.n7.se/listaplatser.php");
//        http.HTTPkontact("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=2&message=hejhej");
        // http.HTTPuppdrag("http://tnk111.n7.se/getmessage.php?messagetype=2");
         http.getutmesssage();
        /*
         * Initialize the DataStore call where all "global" data will be stored
         */
        ds = new DataStore();

        /*
         * This sets the file path and read network text file. Adjust for your needs.
         */
        ds.setFileName("streets.txt");
        ds.readNet();
        /*
         * Initialize and show the GUI. The constructor gets access to the DataStore
         */
        cui = new ControlUI(ds);
        cui.setVisible(true);
        cui.bluetoothAdress(badress);
        cui.bluetoothchannel(kanal);
        cui.showStatus(http.newmesssage());

        /*    HTTPanrop h2 = new HTTPanrop(ds, op);
            Thread t5 = new Thread(h2);
            t5.start();
            
            OptPlan op = new OptPlan(ds);
            op.createPlan();
            
            GuiUpdate r1 = new GuiUpdate(ds, cui, op);
            Thread t2 = new Thread(r1);
            t2.run();
          
            RobotRutt r2 = new RobotRutt(ds, cui, op);
            Thread t1 = new Thread(r2);
            t1.start();
        */

    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* This is the "main" method what gets called when the application starts
         * All that is done here is to make an instance of the RobotControl class,
         * and thereby, call the RobotControl constructor.
         */
        KTS3G1 x = new KTS3G1();

    }
}
