package KTS3G1;

import java.util.*;

public class OptPlan {

    ControlUI cui;
    private List<Vertex> nodes;
    private List<Edge> edges;
    private DataStore ds;
    private HTTPny http;

    
    public int  start = 2;//http.ink.get(k)
    //Dessa skall inte vara fixt utan mer som en vektor? 
    
    public int  slut = 75; //Inparametrar av något slag
   public int Origin = start;

    public int startupp = 40; //start av uppdrag startar där upphämtningsplatsslutar.
    public int slutupp = 50; //slut av uppdrag 

    public int[] shortestPathList = new int[1000];
    public int[] shortestPathListupp = new int[1000];
    
    public int pathCost = 0;
    int pathCostupp = 0;
    
    double x = 0;
    double y = 0;
    
    double xupp = 0;
    double yupp = 0;
    
    public OptPlan(DataStore ds) {
        this.ds = ds;

    }

    public void createPlan() {
<<<<<<< HEAD
        try {
            if (ds.atervant){
            slut = start; //startnoden;
            //ds.atervant=false;
            
//start=nuvarande nod som man är på väg till ;  
        }
            else{
         
        
=======
        
       // if (cui.atervant==true){
         //   slut = 0; 
       // }
>>>>>>> 952b040c8049e4bb4b3ef74f6f2fb36e224d0d9f
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();

        // Set up network
        for (int i = 0; i < ds.nodes; i++) {
            Vertex location = new Vertex("" + (i + 1), "Nod #" + (i + 1));
            nodes.add(location);
        }
        for (int i = 0; i < ds.arcs; i++) {
            Edge lane = new Edge("" + (i + 1), nodes.get(ds.arcStart[i] - 1), nodes.get(ds.arcEnd[i] - 1), 1); // Last argument is arc
            edges.add(lane);
        }
         

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

        // Compute shortest path       
        dijkstra.execute(nodes.get(start - 1));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(slut - 1));

        // Get shortest path
        for (int i = 0; i < path.size(); i++) {
            shortestPathList[i] = Integer.parseInt(path.get(i).getId());

            //System.out.println(Integer.parseInt(path.get(i).getId()));
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
                    //Lägger till kostanden för shortest path
                    pathCost = pathCost + ds.arcCost[j];
                    // System.out.println(pathCost);
                }
            }
        }

        //// ny för uppdragen

        // Compute shortest path av uppdragen     
        dijkstra.execute(nodes.get(startupp - 1));
        LinkedList<Vertex> pathupp = dijkstra.getPath(nodes.get(slutupp - 1));

        // Get shortest path
        for (int i = 0; i < pathupp.size(); i++) {
            shortestPathListupp[i] = Integer.parseInt(pathupp.get(i).getId());

            //System.out.println(Integer.parseInt(path.get(i).getId()));
            //System.out.println(ds.pathInt[i]);
            xupp = ds.nodeX[shortestPathListupp[i] - 1];
            yupp = ds.nodeY[shortestPathListupp[i] - 1];

            String nodePathupp = (" " + xupp + ", " + yupp);
            //System.out.println(" " + x + ", " + y);
            //cui.appendOptText(nodePath); Funkar ej lol okej

        }

        // Arcs in the shortest path
        for (int i = 0; i < pathupp.size() - 1; i++) {
            for (int j = 0; j < ds.arcs; j++) {
                if (ds.arcStart[j] == Integer.parseInt(pathupp.get(i).getId())
                        && ds.arcEnd[j] == Integer.parseInt(pathupp.get(i + 1).getId())) {

                    //Sätter shortest path till 1
                    ds.arcColor[j] = 2;
                    //Lägger till kostanden för shortest path
                    pathCostupp = pathCostupp + ds.arcCost[j];
                    // System.out.println(pathCostupp);

                }
            }
        }
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
