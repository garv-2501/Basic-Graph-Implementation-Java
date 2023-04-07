public class Edge {
    // Instance variables
    public Vertex from, to;
    public int weight;

    // Constructor
    public Edge(Vertex _from, Vertex _to, int _weight) {
        this.from = _from;
        this.to = _to;
        this.weight = _weight;
    }
}