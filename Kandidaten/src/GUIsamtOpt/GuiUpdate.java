package GUIsamtOpt;

import java.util.Random;


public class GuiUpdate implements Runnable {

    private int sleepTime;
    private static Random generator = new Random();
    private ControlUI cui;
    private DataStore ds;

    public GuiUpdate(DataStore ds, ControlUI cui) {
        this.cui = cui;

        this.ds = ds;
        sleepTime = generator.nextInt(20000);
    }

    @Override
    public void run() {
        
        try {
            cui.appendStatus("GuiUpdate startar och kommer att köra i " + sleepTime + " millisekunder.");

            int i = 1;
            if (ds.updateUIflag == true) {

                while (i <= 50) {
                    Thread.sleep(sleepTime / 20);
                    cui.appendStatus("Jag är tråd GuiUpdate! För " + i + ":te gången");
                    ds.robotX = ds.robotX - 10;
                    cui.repaint();
                    i++;
                }
            }
        } catch (InterruptedException exception) {
        }
        cui.appendStatus("GuiUpdate är nu klar!");
        }
}
