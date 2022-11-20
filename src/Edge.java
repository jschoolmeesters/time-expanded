public class Edge {
    int u, v, flow, capacity, cost, atTime;
    Edge reverse;

    public Edge (int u, int v, int flow, int capacity, int cost) {
        this.u = u;
        this.v = v;
        this.flow = flow;
        this.capacity = capacity;
        this.cost = cost;
    }

    public void setReverse(Edge reverse) {
        this.reverse = reverse;
    }
}
