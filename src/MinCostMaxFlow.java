public class MinCostMaxFlow {

    public static int minCostMaxFlow(int[][] capacity, int[][] cost, int s, int t, int numOfNodes) {
        int totalCost = 0;
        int[] distance = new int[numOfNodes];
        int[] parent = new int[numOfNodes];
        int totalFlow = 0;
        while (true) {
            totalCost = totalFlow;
            // Set all distances to max value, this means that they have not been set yet
            for (int i = 0; i < numOfNodes; i++)
                distance[i] = Integer.MAX_VALUE;
            // Source should have 0 as distance
            distance[0] = 0;
            for (int i = 0; i < numOfNodes; i++) { // Repeat for every node
                for (int u = 0; u < numOfNodes; u++) { // For all u
                    for (int v = 0; v < numOfNodes; v++) { // And for all v
                        if (capacity[u][v] > 0 && // If there is an edge between u and v with a positive capacity
                                distance[v] > distance[u] + cost[u][v] && // This means that there is a negative cost cycle
                                distance[u] < Integer.MAX_VALUE) { // Distance has te be set for u, otherwise you cannot add cost to it
                            distance[v] = distance[u] + cost[u][v];
                            parent[v] = u;
                        }
                    }
                }
            }
            if (distance[t] == Integer.MAX_VALUE)
                return totalFlow;

            // Update the total cost
            totalCost += distance[t];

            // Send flow back from the sink
            sendFlowBack(t,parent,capacity);

            totalFlow++;
        }
    }

    // Send flow back from the sink until reaching the source
    public static void sendFlowBack(int node, int[] parent, int[][] capacity) {
        int current = node;
        // While not back at the source
        while (parent[current] != 0) {
            int currentParent = parent[current];
            capacity[currentParent][current]--;
            capacity[current][currentParent]++;
            current = currentParent;
        }
    }
}

