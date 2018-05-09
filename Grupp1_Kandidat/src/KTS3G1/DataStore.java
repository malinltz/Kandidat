package KTS3G1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataStore {

    String fileName = null;
    int nodes;
    int arcs;
    int dist=0;
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
    public int start = 7; //Startposition. Måste ändras till förgående uppdrags slutposition på något sätt.
    public int robotPos = start;
    public int slut;
    public int startnod=0; 
    public int kapacitet =8;
    int[] arcColor;
    int totPoang = 0;
    int [] poang; 
    int Antal_passagerare=0; 
     int passeradenoder=0;
     int starta=0; 

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

            // Read nodes as number, x, y
            for (int i = 0; i < nodes; i++) {
                line = scanner.nextLine();
                //split space separated data on line
                sline = line.split(" ");
                nodNamn[i] = Double.parseDouble(sline[0].trim());
                nodeX[i] = Double.parseDouble(sline[1].trim());
                nodeY[i] = Double.parseDouble(sline[2].trim());
            }

            // Read arc list as start node number, end node number
            for (int i = 0; i < arcs; i++) {
                line = scanner.nextLine();
                //split space separated data on line
                sline = line.split(" ");
                arcStart[i] = Integer.parseInt(sline[0].trim());
                arcEnd[i] = Integer.parseInt(sline[1].trim());
            }
         
            networkRead = true;  // Indicate that all network data is in place in the DataStore
            updateUIflag = true;

            robotX = nodeX[start-1]; //Sätts till -1 för att få rätt startNod
            robotY = nodeY[start-1]; //Sätts till -1 för att få rätt startNod
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}