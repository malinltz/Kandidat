package GUIsamtOpt;

/**
 *
 */
public class LastMileAuto {

    DataStore ds;
    ControlUI cui;
    
    LastMileAuto(){

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
        LastMileAuto x = new LastMileAuto();
    }
}
