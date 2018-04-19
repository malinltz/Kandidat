package KTS3G1;

/**
 *
 */
public class KTS3G1 {

    DataStore ds;
    ControlUI cui;
  // Transmitter tm; 
   //Transceiver tc; 
  //  Mirror mi;
 //   Reciver re; 
    HTTPanrop http; 
    OptPlan op;
   
    
    KTS3G1(){ 
 
     //tm = new Transmitter();
     //re = new Reciver()

       http = new HTTPanrop();
      
       http.HTTPanrop("http://tnk111.n7.se/listaplatser.php");
       http.HTTPkontact("http://tnk111.n7.se/putmessage.php?groupid=1&messagetype=2&message=hejhej");
       http.HTTPuppdrag("http://tnk111.n7.se/getmessage.php?messagetype=2");
       
       
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
        cui.showStatus(http.newmesssage());
        
        
        
        // RobotRead r1 = new RobotRead(ds, cui);
         //Thread t1 = new Thread(r1);
         //t1.start();
        
         
        OptPlan op = new OptPlan(ds);
        op.createPlan();

        GuiUpdate g1 = new GuiUpdate(ds, cui, op);
        Thread t2 = new Thread(g1);
        t2.start();
        
       
        //Transceiver p1 = new Transceiver(tc,cui); 
        //Thread t3 = new Thread(p1);
        //t3.start();
        
    }
    
    /**
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