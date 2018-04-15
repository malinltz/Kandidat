package KTS3G1;

import java.util.*;


public class OptPlan {

    private List<Vertex> nodes;
    private List<Edge> edges;
    private DataStore ds;

    public OptPlan(DataStore ds) {
        this.ds = ds;
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
    }

    public void createPlan() {

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
        dijkstra.execute(nodes.get(60));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(70));
        
        // Get shortest path
        for (int i = 0; i < path.size(); i++) {

            System.out.println(path.get(i));
        }

        // Arcs in the shortest path
        for (int i = 0; i < path.size() - 1; i++) {
            for (int j = 0; j < ds.arcs; j++) {
                if (ds.arcStart[j] == Integer.parseInt(path.get(i).getId())
                        && ds.arcEnd[j]
                        == Integer.parseInt(path.get(i + 1).getId())) {

                    System.out.println("Arc: " + j);
                    ds.arcColor[j]=1;
                }
            }
        }
    }
}
    

