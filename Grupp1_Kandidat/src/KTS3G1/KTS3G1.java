

package KTS3G1;

/**
 *
 */
public class KTS3G1 {

    DataStore ds;
    ControlUI cui;
    HTTPny http;
    OptPlan op;
   // RobotRutt RR;
   // HTTPextern httpex;
    //Transceiver tc; 
    //Reciver re;   

    KTS3G1() {

        //tc= new Transceiver
        //tm = new Transmitter();
        //re = new Reciver()
        
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
        

      //  op = new OptPlan(ds);

    }

    public static void main(String[] args) {

        /* This is the "main" method what gets called when the application starts
         * All that is done here is to make an instance of the RobotControl class,
         * and thereby, call the RobotControl constructor.
         */
        KTS3G1 x = new KTS3G1();
        
    }
}


