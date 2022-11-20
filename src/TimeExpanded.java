import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeExpanded {
    List<Edge> edges = new ArrayList<>();
    List<Edge>[] edgesNew;
    List<TimeExpandedEdge> edgesExtra = new ArrayList<>();
    ArrayList<List<Node<TimeExpandedEdge>>> timeExpandedGraph;
    int T, numOfNodes, numOfEdges, numOfNodesTimeExpanded;

    int[][] capacity;
    int[][] cost;

    public int run() {
        init();
        initTimeExpandedGraph();
        addSourceAndSinkTimeExpandedGraph();
        fillCostAndCapacityArrays();
        int result = MinCostMaxFlow.minCostMaxFlow(capacity, cost, 0, 1, numOfNodesTimeExpanded);
        return result;

    }

    void addSourceAndSinkTimeExpandedGraph() {
        int c1 = 2;
        for (int t = 0; t <= T; t++) {
            TimeExpandedEdge edge = new TimeExpandedEdge(0,c1, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, t);
            edgesExtra.add(edge);
            c1 += numOfNodes;
        }

        int c2 = numOfNodes + 1;
        for (int t = 0; t <= T; t++) {
            TimeExpandedEdge edge = new TimeExpandedEdge(c2,1, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, T+1);
            edgesExtra.add(edge);
            c2 += numOfNodes;
        }
    }

    void fillCostAndCapacityArrays() {
        for (Edge e: edges) {
            for (int t = 0; t <= T - e.cost; t++) {
                int idFrom = timeExpandedGraph.get(t).get(e.u).id;
                int idTo = timeExpandedGraph.get(t+e.cost).get(e.v).id;
                capacity[idFrom][idTo] = e.capacity;
                cost[idFrom][idTo] = e.cost;
            }
        }
        for (TimeExpandedEdge e: edgesExtra) {
            capacity[e.u][e.v] = e.capacity;
            cost[e.u][e.v] = e.cost;
        }
    }

    void initTimeExpandedGraph() {
        // For every edge
        // Add the nodes of that edge and the edge itself
        // Only add th edge itself if T allows it
        int id = 2;
        for (int t = 0; t <= T; t++) {
            timeExpandedGraph.add(new ArrayList<>());
            for (int i = 0; i < numOfNodes; i++) {
                Node<TimeExpandedEdge> n = new Node<>(id, t);
                timeExpandedGraph.get(t).add(n);
                id++;
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
        numOfNodesTimeExpanded = (T+1)*numOfNodes+2;
        capacity = new int[numOfNodesTimeExpanded][numOfNodesTimeExpanded];
        cost = new int[numOfNodesTimeExpanded][numOfNodesTimeExpanded];

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
            //edgesNew[u].add(e);
        }
        scanner.close();
    }
}
