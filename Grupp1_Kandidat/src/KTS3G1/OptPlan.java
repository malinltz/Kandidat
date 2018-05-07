package KTS3G1;

import java.util.*;

public class OptPlan {

    ControlUI cui;
    RobotRutt RR; 
   
    private List<Vertex> nodes;
    private List<Edge> edges;
    public LinkedList<Vertex> path;
    public DataStore ds;
    public HTTPny http;
    

    public int[] shortestPathList = new int[1000];
    public int[] shortestPathListupp = new int[1000];
    
    int pathCost = 0;
    double x = 0;
    double y = 0;

    public OptPlan(DataStore ds) {
        this.ds = ds;

    }

    public void createPlan() {
      
         nodes = new ArrayList<Vertex>();
         edges = new ArrayList<Edge>();
          
         /*
            if (ds.atervant){
            slut = startnod; //startnoden;
            ds.atervant=false;
            
        start=nuvarande nod som man är på väg till ;  
        }
            else{
         
       String[] sline;

         http.startlist=Double.parseDouble(sline[0].trim());
         http.stopplist=Double.parseDouble(sline[1].trim());
*/

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
        
        // Compute shortest path       (MÅSTE VA -1)
        dijkstra.execute(nodes.get(ds.start-1));
        path = dijkstra.getPath(nodes.get(ds.slut-1));
         
        // Get shortest path
        for (int i = 0; i < path.size(); i++) {
            shortestPathList[i] = Integer.parseInt(path.get(i).getId());

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
                }
            }
        }
}
    
    public int[] getIndex() {
        
        return shortestPathList;
    }

    public int getCost() {
        
       return pathCost;
  }
}
