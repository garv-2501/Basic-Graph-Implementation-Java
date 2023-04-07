import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Graph {
    public ArrayList<Vertex> vlist;

    public Graph() { // Constructor to initialize the ArrayList
        this.vlist = new ArrayList<>();
    }

    public void addVertex(String name) { // Method to add a new vertex to the graph
        vlist.add(new Vertex(name));
    }
    
    public Vertex getVertex(String name) { // Method to get a vertex from the graph by name
        for (Vertex v : vlist) {
            if (v.name.equals(name)) {
                return v;
            }
        }
        return null;
    }
    
    public void addEdge(String from, String to, int weight) { // Method to add an edge between two vertices
        Vertex v1 = getVertex(from);
        Vertex v2 = getVertex(to);
        if (v1 != null && v2 != null) {
            v1.adjlist.add(new Edge(v1, v2, weight));
            v2.adjlist.add(new Edge(v2, v1, weight));
        }
    }
    
    public Edge getEdge(String from, String to) { // Method to get an edge between two vertices
        Vertex v1 = getVertex(from);
        Vertex v2 = getVertex(to);
        if (v1 != null && v2 != null) {
            for (Edge e : v1.adjlist) {
                if (e.to == v2) {
                    return e;
                }
            }
        }
        return null;
    }

    // Implementing MST using the prim's algorithm
    public Graph MST() {
        Graph mst = new Graph(); // Create a new graph to store the MST
        if (vlist.isEmpty()) {
            return mst;
        }
    
        // Add all vertices of the original graph to the MST
        for (Vertex v : vlist) {
            mst.addVertex(v.name);
        }
    
        // Use a priority queue to keep track of the minimum-weight edges
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
    
        // Start with the first vertex in the original graph
        ArrayList<Vertex> visited = new ArrayList<>();
        visited.add(vlist.get(0));
    
        // Loop until all vertices have been visited
        while (visited.size() < vlist.size()) {
            // Add all edges connected to the most recently visited vertex to the priority queue
            for (Edge e : visited.get(visited.size() - 1).adjlist) {
                if (!visited.contains(e.to)) {
                    pq.add(e);
                }
            }
    
            // Find the minimum-weight edge that connects an unvisited vertex to the visited set
            Edge minEdge = pq.poll();
            while (visited.contains(minEdge.to)) {
                minEdge = pq.poll();
            }
    
            // Add the new edge to the MST and mark the destination vertex as visited
            mst.addEdge(minEdge.from.name, minEdge.to.name, minEdge.weight);
            visited.add(minEdge.to);
        }
    
        // Return the completed minimum spanning tree
        return mst;
    }
    
    // Findind the cost of the MST
    public int MSTCost() {
        Graph mst = this.MST();
        int totalCost = 0;

        // Add up the weight of all edges in the MST
        for (Vertex v : mst.vlist) {
            for (Edge e : v.adjlist) {
                totalCost += e.weight;
            }
        }

        return totalCost / 2;
    }

    // Implement Dijkstra's Algorithm for Shortest Path
    public Graph SP(String from, String to) {
        Graph sp = new Graph();

        Vertex start = getVertex(from);
        Vertex end = getVertex(to);

        // Return an empty graph if the starting or ending vertex is not found
        if (start == null || end == null) {
            return sp;
        }

        // Create an array to store the distance from the starting vertex
        int[] dist = new int[vlist.size()];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // Create an array to store the parent of each vertex
        Vertex[] parent = new Vertex[vlist.size()];

        // Create an array to keep track of which vertices have been visited
        boolean[] visited = new boolean[vlist.size()];

        // Create a priority queue
        PriorityQueue<VertexDist> pq = new PriorityQueue<>(Comparator.comparingInt(vd -> vd.dist));

        // Set the starting vertex distance to 0
        dist[vlist.indexOf(start)] = 0;
        pq.add(new VertexDist(start, 0));

        // Loop until the priority queue is empty
        while (!pq.isEmpty()) {
            VertexDist current = pq.poll();
            Vertex currVertex = current.vertex;
            int currDist = current.dist;
            if (!visited[vlist.indexOf(currVertex)]) {
                visited[vlist.indexOf(currVertex)] = true;
    
                if (currVertex == end) {
                    break;
                }
    
                // Update the distance of all adjacent vertices
                for (Edge e : currVertex.adjlist) {
                    int newDist = currDist + e.weight;
                    if (newDist < dist[vlist.indexOf(e.to)]) {
                        dist[vlist.indexOf(e.to)] = newDist;
                        parent[vlist.indexOf(e.to)] = currVertex;
                        pq.add(new VertexDist(e.to, newDist));
                    }
                }
            }
        }
    
        // Reconstruct the shortest path
        Vertex current = end;
        while (current != null) {
            sp.addVertex(current.name);
            current = parent[vlist.indexOf(current)];
        }
    
        // Add the edges to the shortest path
        for (int i = sp.vlist.size() - 1; i > 0; i--) {
            Vertex v1 = sp.vlist.get(i);
            Vertex v2 = sp.vlist.get(i - 1);
            Edge e = getEdge(v1.name, v2.name);
            if (e != null) {
                sp.addEdge(v1.name, v2.name, e.weight);
            }
        }
    
        return sp;
    }
    
    // Find the cost of the shortest path
    public int SPCost(String from, String to) {
        Vertex start = getVertex(from);
        Vertex end = getVertex(to);
    
        // Return -1 if the starting or ending vertex is not found
        if (start == null || end == null) {
            return -1;
        }
    
        // Return 0 if the starting and ending vertices are the same
        Graph sp = SP(from, to);
        int cost = 0;
    
        // Add up the weight of all edges in the shortest path
        for (int i = sp.vlist.size() - 1; i > 0; i--) {
            Vertex v1 = sp.vlist.get(i);
            Vertex v2 = sp.vlist.get(i - 1);
            Edge e = getEdge(v1.name, v2.name);
            if (e != null) {
                cost += e.weight;
            }
        }
    
        return cost;
    }
    
    private class VertexDist {
        Vertex vertex;
        int dist;
    
        // Constructor
        public VertexDist(Vertex vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}