package KTS3G1;

import java.io.File;
import java.util.Scanner;

/**
 *
 */
public class DataStore {

    String fileName = null;
    int nodes;
    int arcs;
    double[] nodeX;
    double[] nodeY;
    int[] arcStart;
    int[] arcEnd;
    int[] arcCost;
    boolean networkRead;
    boolean updateUIflag;

    double robotX;
    double robotY;
    int[] arcColor;

    public DataStore() {
        // Initialize the datastore with fixed size arrays for storing the network data
        nodes = 0;
        arcs = 0;
        nodeX = new double[1000];
        nodeY = new double[1000];
        arcStart = new int[1000];
        arcEnd = new int[1000];
        arcCost = new int[1000];
        arcColor = new int[1000];

        networkRead = false;
        updateUIflag = false;

    }

    public void setFileName(String newFileName) {
        this.fileName = newFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void readNet() {
        String line;

        if (fileName == null) {
            System.err.println("No file name set. Data read aborted.");
            return;
        }
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file, "iso-8859-1");
            String[] sline;

            // Read number of nodes
            line = (scanner.nextLine());
            nodes = Integer.parseInt(line.trim());
            line = scanner.nextLine();
            arcs = Integer.parseInt(line.trim());

            // Debug printout: network size data
            System.out.println("Nodes: " + nodes);
            System.out.println("Arcs: " + arcs);

            // Read nodes as number, x, y
            for (int i = 0; i < nodes; i++) {
                line = scanner.nextLine();
                //split space separated data on line
                sline = line.split(" ");
                nodeX[i] = Double.parseDouble(sline[1].trim());
                nodeY[i] = Double.parseDouble(sline[2].trim());
            }

            // Debug printout: print data for node 1
            System.out.println("Node 1: " + nodeX[0] + " " + nodeY[0]);

            // Read arc list as start node number, end node number
            for (int i = 0; i < arcs; i++) {
                line = scanner.nextLine();
                //split space separated data on line
                sline = line.split(" ");
                arcStart[i] = Integer.parseInt(sline[0].trim());
                arcEnd[i] = Integer.parseInt(sline[1].trim());
                arcCost[i] = Integer.parseInt(sline[2].trim());

            }

            networkRead = true;  // Indicate that all network data is in place in the DataStore
            updateUIflag = true;

            robotX = nodeX[70];
            robotY = nodeY[70];

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

}
