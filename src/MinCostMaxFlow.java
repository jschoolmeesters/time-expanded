import java.util.Arrays;

public class MinCostMaxFlow {

    public static int minCostMaxFlow(int[][] capacity, int[][] cost, int s, int t, int numOfNodes) {
        int[] distance = new int[numOfNodes];
        int[] parent = new int[numOfNodes];
        for (int flow = 0, flowCost = 0;; ++flow) {
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[s] = 0;
            for (int i = 0; i < numOfNodes; i++)
                for (int u = 0; u < numOfNodes; u++)
                    for (int k = 0; k < numOfNodes; k++)
                        if (capacity[u][k] > 0 && distance[u] < Integer.MAX_VALUE && distance[k] > distance[u] + cost[u][k]) {
                            distance[k] = distance[u] + cost[u][k];
                            parent[k] = u;
                        }
            if (distance[t] == Integer.MAX_VALUE)
                return flow;
            flowCost += distance[t];
            for (int v = t; v!= s; v = parent[v]) {
                --capacity[parent[v]][v];
                ++capacity[v][parent[v]];
            }
        }
    }
}

