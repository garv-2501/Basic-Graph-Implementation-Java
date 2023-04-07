public class Main {
  public static void main(String[] args) {
    // Testing the graph class and its methods
    Graph g = new Graph();
    g.addVertex("A");
    g.addVertex("B");
    g.addVertex("C");
    g.addVertex("D");
    g.addVertex("E");
    g.addVertex("F");

    g.addEdge("A", "B", 2);
    g.addEdge("A", "C", 3);
    g.addEdge("B", "C", 3);
    g.addEdge("B", "D", 2);
    g.addEdge("B", "E", 3);
    g.addEdge("C", "E", 1);
    g.addEdge("D", "E", 4);
    g.addEdge("D", "F", 2);
    g.addEdge("E", "F", 3);

    System.out.println("Graph:");
    // print all the elements of the graph g
    for (Vertex v : g.vlist) {
        System.out.print(v.name + ": ");
        for (Edge e : v.adjlist) {
            System.out.print(e.to.name + "(" + e.weight + ") ");
        }
        System.out.println();
    }
    System.out.println();

    System.out.println("MST:");
    // print all the elements of the MST
    Graph mst = g.MST();
    for (Vertex v : mst.vlist) {
        System.out.print(v.name + ": ");
        for (Edge e : v.adjlist) {
            System.out.print(e.to.name + "(" + e.weight + ") ");
        }
        System.out.println();
    }

    System.out.println("MST Cost:");
    System.out.println(g.MSTCost());
    System.out.println();

    System.out.println("Shortest Path from A to F:");
    // print all the elements of the shortest path
    for (Vertex v : g.SP("A", "F").vlist) {
        System.out.print(v.name + " --> ");
    }
    System.out.print("Done");
    System.out.println("\n");

    System.out.println("Shortest Path Cost from A to F:");
    System.out.println(g.SPCost("A", "F"));
    System.out.println();
  }
}
