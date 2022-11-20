import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    int id, t;
    List<T> edges = new ArrayList<T>();

    public Node(int id, int t) {
        this.id = id;
        this.t = t;
        edges = new ArrayList<T>();
    }
}
