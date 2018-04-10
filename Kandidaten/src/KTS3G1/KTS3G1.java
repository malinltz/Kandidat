package KTS3G1;

/**
 *
 */
public class KTS3G1 {

    DataStore ds;
    ControlUI cui;
   Transmitter tm; 
   // Transceiver tc; 
  //  Mirror mi;
 //   Reciver re; 
    HTTPanrop http; 
    
    KTS3G1(){ 
 
        //Bn = "B827EB003082";
        //Cn = "22";

       tm = new Transmitter();
      //  re = new Reciver();
       

       HTTPanrop http = new HTTPanrop();

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
        
        cui = new ControlUI(ds,tm);
        cui.setVisible(true);
        cui.showStatus();
        
        // RobotRead r1 = new RobotRead(ds, cui);
         //Thread t1 = new Thread(r1);
         //t1.start();
         
        GuiUpdate g1 = new GuiUpdate(ds, cui);
        Thread t2 = new Thread(g1);
        t2.start();
         
          OptPlan op = new OptPlan(ds);
          op.createPlan();
        
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