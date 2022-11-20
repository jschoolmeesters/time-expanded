import java.util.Arrays;

public class MinCostMaxFlow {

    public static int[] minCostMaxFlow(int[][] capacity, int[][] cost, int s, int t) {
        int n = capacity.length;
        int[] distance = new int[n];
        int[] p = new int[n];
        for (int flow = 0, flowCost = 0; ; ++flow) {
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[s] = 0;
            for (int i = 0; i < n; i++) // Just for iterating over all edges
                for (int j = 0; j < n; j++)
                    for (int k = 0; k < n; k++)
                        if (capacity[j][k] > 0 && distance[j] < Integer.MAX_VALUE && distance[k] > distance[j] + cost[j][k]) {
                            distance[k] = distance[j] + cost[j][k];
                            p[k] = j;
                        }
            if (distance[t] == Integer.MAX_VALUE)
                return new int[]{flowCost, flow};
            flowCost += distance[t];
            for (int v = t; v != s; v = p[v]) {
                --capacity[p[v]][v];
                ++capacity[v][p[v]];
            }
        }
    }

    public static void addEdge(int[][] cap, int[][] cost, int u, int v, int edgeCapacity, int edgeCost) {
        cap[u][v] = edgeCapacity;
        cost[u][v] = edgeCost;
        cost[v][u] = -edgeCost;
    }
}

