## Graphs implementation in Java

This is a Java implementation of Graphs. The Graphs class contains functions to find the Minimum Spanning Tree (MST) of the graph using Prim's algorithm and has a function that implements Dijkstra's algorithm to find the shortest path.

### Usage

To use the Graphs class, you can create a new instance of the class in the main function in the Main.java file and then call the methods you need. Here's an example:

```java
// create a new graph with 5 vertices
Graphs graph = new Graphs(5);

// add edges to the graph
graph.addEdge(0, 1, 2);
graph.addEdge(0, 4, 6);
graph.addEdge(1, 2, 3);
graph.addEdge(1, 4, 8);
graph.addEdge(2, 3, 1);
graph.addEdge(3, 4, 4);

// find the MST of the graph using Prim's algorithm
graph.findMST();

// find the shortest path between two vertices using Dijkstra's algorithm
int shortestPath = graph.shortestPath(0, 3);
```

### Main Functions in the Graph Class

The Graph class contains the following functions:

- Graphs(int V): Creates a new graph with V vertices.
- void addEdge(int u, int v, int weight): Adds an edge between vertices u and v with weight weight.
- void findMST(): Finds the Minimum Spanning Tree (MST) of the graph using Prim's algorithm and prints the MST.
- int shortestPath(int start, int end): Finds the shortest path between vertices start and end using Dijkstra's algorithm and returns the length of the shortest path.

### Contributions

Contributions to this code are welcome. If you encounter any issues or have suggestions for improvements, such as the addition of methods to find the minimum spanning tree or shortest path using algorithms like Kruskal's algorithm, please don't hesitate to create an issue or pull request.
