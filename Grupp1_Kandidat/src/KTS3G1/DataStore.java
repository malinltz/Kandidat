package KTS3G1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**

 */
public class DataStore {

    String fileName = null;
    int nodes;
    int arcs;
    int dist;
    double[] nodeX;
    double[] nodeY;
    double[] nodNamn;
    int[] arcStart;
    int[] arcEnd;
    int[] arcCost;
    boolean networkRead;
    boolean updateUIflag;
    boolean atervant; 
    double robotX;
    double robotY;
    public int start = 2;
    public int slut = 20;
   // int [] nodeStart;
   // int [] nodeEnd; 
    
    int[] arcColor;
   // int pathInt;
    

    public DataStore() {
        // Initialize the datastore with fixed size arrays for storing the network data
        nodes = 0;
        arcs = 0;
        nodeX = new double[1000];
        nodeY = new double[1000];
        nodNamn = new double[1000];
        arcStart = new int[1000];
        arcEnd = new int[1000];
        arcCost = new int[1000];
        arcColor = new int[1000];
       
        
        atervant = false; 
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
            //System.out.println("Nodes: " + nodes);
            //System.out.println("Arcs: " + arcs);

            // Read nodes as number, x, y
            for (int i = 0; i < nodes; i++) {
                line = scanner.nextLine();
                //split space separated data on line

                sline = line.split(" ");

                nodNamn[i] = Double.parseDouble(sline[0].trim());
                nodeX[i] = Double.parseDouble(sline[1].trim());
                nodeY[i] = Double.parseDouble(sline[2].trim());
                
                //System.out.println(nodeX[i]+" X");
                //System.out.println(nodeY[i]+" Y");
            }
            for (int i=0; i < nodes; i++)
            {
            // Debug printout: print data for node 1
            //System.out.println("Node: " + nodeX[i] + " " + nodeY[i]);
            //System.out.println("Node 2: " + nodeX[1] + " " + nodeY[1]);
            }
            // Read arc list as start node number, end node number
            for (int i = 0; i < arcs; i++) {
                line = scanner.nextLine();
                //split space separated data on line
                sline = line.split(" ");
                arcStart[i] = Integer.parseInt(sline[0].trim());
                arcEnd[i] = Integer.parseInt(sline[1].trim());
               // arcCost[i] = Integer.parseInt(sline[2].trim());
               
                //Beräknar och ritar ut siffror på båglängden
                dist = (int) Math.round(Math.hypot((nodeX[arcStart[i] - 1] - nodeX[arcEnd[i] - 1]), (nodeY[arcStart[i] - 1] - nodeY[arcEnd[i] - 1])));
                arcCost[i] = dist;

            }
         
            networkRead = true;  // Indicate that all network data is in place in the DataStore
            updateUIflag = true;

            robotX = nodeX[20];
            robotY = nodeY[20];

            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

}
