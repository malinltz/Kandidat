package KTS3G1;

import java.util.*;


public class OptPlan {

    ControlUI cui;
    private List<Vertex> nodes;
    private List<Edge> edges;
    private DataStore ds;
    public int start = 1; //Dessa skall inte vara fixt utan mer som en vektor? 
    public int slut = 5; //Inparametrar av något slag
    public int[] shortestPathList = new int[1000];
    int pathCost = 0;
    double x=0;
    double y=0;

    public OptPlan(DataStore ds) {
        this.ds = ds;

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
        
        // Compute shortest path
        dijkstra.execute(nodes.get(start-1));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(slut-1));
        
        // Get shortest path
        for (int i = 0; i < path.size(); i++) {
            shortestPathList[i] = Integer.parseInt(path.get(i).getId());

            System.out.println(Integer.parseInt(path.get(i).getId()));
            //System.out.println(ds.pathInt[i]);
            
           
             x = ds.nodeX[shortestPathList[i]]; 
            y = ds.nodeY[shortestPathList[i]];
            
            System.out.println("" +x+", " +y);
            
        }
        

        // Arcs in the shortest path
        for (int i = 0; i < path.size() - 1; i++) {
            for (int j = 0; j < ds.arcs; j++) {
                if (ds.arcStart[j] == Integer.parseInt(path.get(i).getId())
                        && ds.arcEnd[j] == Integer.parseInt(path.get(i + 1).getId())) {

                    //Sätter shortest path till 1
                    ds.arcColor[j]=1;
                    //Lägger till kostanden för shortest path
                    pathCost=pathCost+ds.arcCost[j];
                   // System.out.println(pathCost);
                   
                }
            }
        }
        //cui.appendOptText("Optimering utförs");
    }
    public int[] getIndex(){

        return shortestPathList;
        
    }
    
    public int getCost(){
        
        return pathCost;
        
    }
    
}