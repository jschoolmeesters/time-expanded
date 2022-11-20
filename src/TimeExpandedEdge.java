public class TimeExpandedEdge {
    int u, v, flow, capacity, cost, atTime;
    TimeExpandedEdge reverse;

    public TimeExpandedEdge(int u, int v, int flow, int capacity, int cost, int atTime) {
        this.u = u;
        this.v = v;
        this.flow = flow;
        this.capacity = capacity;
        this.cost = cost;
        this.atTime = atTime;
    }
    public TimeExpandedEdge(Edge e, int atTime) {
        this.u = e.u;
        this.v = e.v;
        this.flow = e.flow;
        this.capacity = e.capacity;
        this.cost = e.cost;
        this.atTime = atTime;
    }

    public void setReverse(TimeExpandedEdge reverse) {
        this.reverse = reverse;
    }
    public void setAtTime(int atTime) { this.atTime = atTime; };
}
