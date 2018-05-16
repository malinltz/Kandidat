package KTS3G1;



import java.util.*;

public class OptPlan {

    
   // RobotRutt RR; 
   
    private List<Vertex> nodes;
    private List<Edge> edges;
    public LinkedList<Vertex> path;
    public DataStore ds;
    public HTTPny http;
    public ControlUI cui;
    

    public int[] shortestPathList;
    
    int pathCost = 0;
    double x = 0;
    double y = 0;

    public OptPlan(DataStore ds, ControlUI cui) {
        this.ds = ds;
        this.cui = cui;

        shortestPathList = new int[1000];        
    }

    public void createPlan() {
        
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
        
        // Compute shortest path       (MÅSTE VA -1)
        dijkstra.execute(nodes.get(ds.start-1));
        path = dijkstra.getPath(nodes.get(ds.slut-1));
         
        cui.showStatus2("Optimering av rutt utförs.");
        
        // Get shortest path
        for (int i = 0; i < path.size(); i++) { 
            shortestPathList[i] = Integer.parseInt(path.get(i).getId());
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
      
        //Tar bort U-Svängar
        for (int j = 0; j < path.size(); j++) {
            
            if(ds.arcCost[j] + ds.arcCost[j+1] + ds.arcCost[j+2] == 90){
                
                
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
