package basic;
import java.util.*;

class Edge implements Comparable<Edge> {
    int node, cost;
    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class Dijkstra {
    public static void main(String[] args) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        graph.put(1, Arrays.asList(new Edge(2,2), new Edge(4,1)));
        graph.put(2, Arrays.asList(new Edge(5, 2), new Edge(3, 1), new Edge(6, 4)));
        graph.put(3, Arrays.asList(new Edge(6, 4)));
        graph.put(4, Arrays.asList(new Edge(3, 3), new Edge(7, 5)));
        graph.put(5, Arrays.asList(new Edge(8, 1)));
        graph.put(6, Arrays.asList(new Edge(5, 3)));
        graph.put(7, Arrays.asList(new Edge(6, 7), new Edge(8, 6)));
        graph.put(8, new ArrayList<>());

        dijkstra(graph, 1, 8, graph.size());
    }

    public static int dijkstra(Map<Integer, List<Edge>> graph, int start, int end, int n) {
        // 초기 설정
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        // 시작점 예약
        Queue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            // 방문
            Edge cur = pq.remove();
            if(dist[cur.node] < cur.cost) continue;

            // 예약
            for(Edge next : graph.get(cur.node)) {
                int nextCost = dist[cur.node] + next.cost;
                if(nextCost < dist[next.node]) {
                    pq.add(new Edge(next.node, nextCost));
                    dist[next.node] = nextCost;
                }
            }
        }
        return dist[end];
    }
}
