import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    int id, t;
    List<T> edges;

    public Node() {

    }
    public Node(int id, int t) {
        this.id = id;
        this.t = t;
        edges = new ArrayList<>();
    }

    public Node(int id) {
        this.id = id;
        this.t = -1;
        edges = new ArrayList<>();
    }

    public Node(int id, T e) {
        this.id = id;
        this.edges = new ArrayList<>();
        this.edges.add(e);
    }
}
