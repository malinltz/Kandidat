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
<<<<<<< HEAD
    Transceiver tc; 
    String badress; 
=======
    HTTPextern hx;
>>>>>>> 21ec974263aae6cdd2ca8744ae04b4f69ca7f0d5


    //Reciver re; 
    String kanal = "1";

    String URL1;
    String URL2;
    String URL3;
    

 //   String upphamtningsplats="lol"; 
    String allaUppdrag="Nötallergiker undanbedes och beivras"; 
    String valtUppdrag="Helt seriöst"; 

    String upphamtningsplats="hej1"; 
  //  String allaUppdrag="hej2"; 
  //  String valtUppdrag="Hej3"; 
   




    KTS3G1() {
         //tc= new Transceiver
        //tm = new Transmitter();
        //re = new Reciver()
<<<<<<< HEAD

        http = new HTTPanrop(ds, op);

      //  http.URL1 = ("http://tnk111.n7.se/listaplatser.php");
      //  http.URL2 = ("http://tnk111.n7.se/listaplatser.php");
      //  http.URL3 = ("http://tnk111.n7.se/listaplatser.php");
=======
>>>>>>> 21ec974263aae6cdd2ca8744ae04b4f69ca7f0d5

        http = new HTTPanrop(ds, op, cui);
        // cui.bluetoothAdress(badress);

      

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
<<<<<<< HEAD
=======
        //ds.nodeX
        
        

>>>>>>> 21ec974263aae6cdd2ca8744ae04b4f69ca7f0d5
        /*
         * Initialize and show the GUI. The constructor gets access to the DataStore
         */
        cui = new ControlUI(ds);
        cui.setVisible(true);
        cui.bluetoothAdress(badress);
        cui.bluetoothchannel(kanal);
<<<<<<< HEAD
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

=======
       // cui.valtUppdrag(valtUppdrag);
        //cui.upphamtningsplats(upphamtningsplats);
        //cui.allaUppdrag(allaUppdrag);
        
        

        cui.showStatus(http.paragraph1);
        //cui.showStatus(http.message);
     
        
        

        cui.showStatus(http.gruppmessages());
        //cui.showStatus(http.newmesssage());
       // cui.showStatus(URL1);
       

      //  cui.showStatus(http.newmesssage());
  
        /*Transceiver p1 = new Transceiver(cui); 
        Thread t3 = new Thread(p1);
        t3.start();
       
        Reciver p2 = new Reciver(tc,cui); 
        Thread t4 = new Thread(p2);
        t4.start();
         */
       /* GuiUpdate G1 = new GuiUpdate(ds, cui, op);
        Thread t5 = new Thread(G1);
        t5.start();

        RobotRutt r1 = new RobotRutt(ds, cui, op);
        Thread t1 = new Thread(r1);
        t1.start();


    HTTPanrop h2 = new HTTPanrop(ds, op);
    Thread t4 = new Thread(h2);
     t4.start();
     
   
        HTTPanrop h2 = new HTTPanrop(ds, op);
        Thread t4 = new Thread(h2);
        t4.start();
         */


        /*   while (cui.anslut == true) {
            Transceiver p1 = new Transceiver(cui);
            Thread t3 = new Thread(p1);
            t3.start();

        }*/
       
>>>>>>> 21ec974263aae6cdd2ca8744ae04b4f69ca7f0d5
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
