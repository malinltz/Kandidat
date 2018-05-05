package KTS3G1;

import java.util.*;

public class OptPlan {

    ControlUI cui;
    RobotRutt RR; 
    private List<Vertex> nodes;
    private List<Edge> edges;
    public LinkedList<Vertex> path;
    private DataStore ds;
    private HTTPny http;
    public int platsw= 5;

    public int startupp = 40; //start av uppdrag startar där upphämtningsplatsslutar.
    public int slutupp = 50; //slut av uppdrag 
    public int startnod=0;//ändra till startnod

    public int[] shortestPathList = new int[1000];
    public int[] shortestPathListupp = new int[1000];
    
    public int[] nodeStart= new int[1000];;
    public int[] nodeEnd= new int[1000];; 
    
    int pathCost;
    int pathCostupp = 0;
    double x = 0;
    double y = 0;
    double xupp = 0;
    double yupp = 0;
    

    
    public OptPlan(DataStore ds) {
        this.ds = ds;
       

    }

    public void createPlan() {
        try {
//            if (ds.atervant){
//            slut = startnod; //startnoden;
//            //ds.atervant=false;
//            
////start=nuvarande nod som man är på väg till ;  
//        }
//            else{
         
      // String[] sline;

       //  http.startlist=Double.parseDouble(sline[0].trim());
        // http.stopplist=Double.parseDouble(sline[1].trim());
        

        nodes = new ArrayList<Vertex>();
        path = new LinkedList<Vertex>();
        edges = new ArrayList<Edge>();
        
        //http = new HTTPny;

        // Set up network
        for (int i = 0; i < ds.nodes; i++) {
            Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1));
            nodes.add(location);
            
           ds.arcEnd[i] = Integer.parseInt(nodes.get(i).getId());
           ds.arcStart[i]= Integer.parseInt(nodes.get(i).getId());

        }
        for (int i = 0; i < ds.arcs; i++) {
            Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), 1); // Last argument is arc
            edges.add(lane);
            
        }
    
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

        
        // Compute shortest path       
        dijkstra.execute(nodes.get(ds.start-1));
        System.out.println("HEEEJ");
        path = dijkstra.getPath(nodes.get(ds.slut-1));
        
         System.out.println(Arrays.toString(path.toArray()) + "FIKA ÄR GOTT");
         
        // Get shortest path
        for (int i = 0; i < path.size()-1; i++) {
            shortestPathList[i] = Integer.parseInt(path.get(i).getId());

            System.out.println(shortestPathList[i]);
            //System.out.println(ds.pathInt[i]);
            x = ds.nodeX[shortestPathList[i] - 1];
            y = ds.nodeY[shortestPathList[i] - 1];

            String nodePath = (" " + x + ", " + y);
            //System.out.println(" " + x + ", " + y);
            //cui.appendOptText(nodePath); Funkar ej lol okej

        }

        // Arcs in the shortest path
        for (int i = 0; i < path.size() - 1; i++) {
            for (int j = 0; j < ds.arcs; j++) {
                if (ds.arcStart[j] == Integer.parseInt(path.get(i).getId())
                        && ds.arcEnd[j] == Integer.parseInt(path.get(i + 1).getId())) {

                    //Sätter shortest path till 1
                    ds.arcColor[j] = 1;  
                }
            }
            
            pathCost = pathCost + ds.arcCost[i];
            System.out.println("BAAAJS" + pathCost);

        }
        }catch(NullPointerException exception){}
        
    }
    public int[] getIndex() {
        
        return shortestPathList;
  
    }

    public int getCost() {
        
        return pathCost;
    }
    

    public int[] getuppdrag() {
        return shortestPathListupp;
    }

    public int getCostupp() {

        return pathCostupp;

    }

}
