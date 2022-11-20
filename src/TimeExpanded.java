import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeExpanded {
    List<Edge> edges = new ArrayList<>();
    ArrayList<List<Node<TimeExpandedEdge>>> timeExpandedGraph;
    int T, numOfNodes, numOfEdges;

    public void run() {
        init();
        initTimeExpandedGraph();
        setEdgesTimeExpandedGraph();
        addSourceAndSinkTimeExpandedGraph();
    }

    void addSourceAndSinkTimeExpandedGraph() {
        timeExpandedGraph.add(new ArrayList<>()); // Store source and sink at time T+1

        Node<TimeExpandedEdge> source = new Node<>(0, T+1);
        for (int t = 0; t <= T; t++) {
            TimeExpandedEdge edge = new TimeExpandedEdge(0,0, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, t);
            source.edges.add(edge);
        }
        timeExpandedGraph.get(T+1).add(source);

        Node<TimeExpandedEdge> sink = new Node<>(numOfNodes-1, T+1);
        timeExpandedGraph.get(T+1).add(sink);

        TimeExpandedEdge edge = new TimeExpandedEdge(numOfNodes-1,numOfNodes, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, T+1);
        for (int t = 0; t <= T; t++) {
            timeExpandedGraph.get(T).get(numOfNodes-1).edges.add(edge);
        }
    }

    void setEdgesTimeExpandedGraph() {
        for (Edge e: edges) {
            for (int t = 0; t <= T - e.cost; t++) {
                TimeExpandedEdge edge = new TimeExpandedEdge(e, t + e.cost);
                timeExpandedGraph.get(t).get(e.u).edges.add(edge);
            }
        }
    }

    void initTimeExpandedGraph() {
        // For every edge
        // Add the nodes of that edge and the edge itself
        // Only add th edge itself if T allows it

        for (int t = 0; t <= T; t++) {
            timeExpandedGraph.add(new ArrayList<>());
            for (int i = 0; i < numOfNodes; i++) {
                Node<TimeExpandedEdge> n = new Node<>(i, t);
                timeExpandedGraph.get(t).add(n);
            }
        }
    }

    void init() {
        Scanner scanner = new Scanner(System.in);
        // Read the first line.
        numOfNodes = scanner.nextInt();
        numOfEdges = scanner.nextInt();
        T = scanner.nextInt();
        timeExpandedGraph = new ArrayList<>();

        // Build the graph.
        for (int i = 0; i < numOfEdges; i++) {
            // Read line and add the edge it to the graph.
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int cost = scanner.nextInt();
            int capacity = scanner.nextInt();

            // Set pointer from each edge "a" to
            // its reverse edge "b" and vice versa.
            Edge e = new Edge(u, v, 0, capacity, cost);
            Edge r = new Edge(v, u, 0, capacity, -cost);
            e.setReverse(r);
            r.setReverse(e);
            edges.add(e);
            //graph[v].edges.add(r);
        }
        scanner.close();
    }
}
