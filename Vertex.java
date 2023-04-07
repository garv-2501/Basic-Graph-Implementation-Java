import java.util.ArrayList;

public class Vertex {
    // Instance variables
    public String name;
    public ArrayList<Edge> adjlist;

    // Constructor
    public Vertex(String _name) {
        this.name = _name;
        this.adjlist = new ArrayList<>();
    }
}