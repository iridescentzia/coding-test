## 문제 파악

- n개의 노드 (1번부터 n개까지)
- times[i] = (시작 노드, 도착 노드, 가중치)
- k는 시작 노드

💡 k에서 모든 노드(1 ~ n)까지의 최단 거리를 구한 후, 그 중 가장 큰 값을 반환

## 접근 방법

- 다익스트라 알고리즘 + 우선순위 큐 (최소 힙)
    1. 그래프를 인접리스트로 변환한 뒤, times 배열을 인접리스트에 저장한다.
    2. 다익스트라 알고리즘 구현
        1. 초기값 세팅 : dist 배열 INF로 초기화, 우선순위 큐 선언하고 시작점 k 추가
        2. 다익스트라 탐색 시작
            1. 큐에서 꺼낸 현재 노드의 인접 노드들 방문
            2. 이미 처리된 노드라면 통과
            3. 현재 노드의 인접 노드(다음 노드)들을 순회
                1. 현재 노드를 통해 다음 노드까지 가는 시간 계산
                2. 더 짧은 경로 발견 시 거리 갱신하고 큐에 추가

                   → 즉, 다음 노드까지의 최소 값이 현재 노드를 통해 다음 노드까지 가는 시간보다 크다면 큐에 최소값 넣고 갱신

    3. 각 노드까지의 최단 시간 중 최대값 갱신

## 코드 구현

💟 그래프 Map에 저장

```java
import java.util.*;

// 그래프 간선과 가중치 class
class Edge implements Comparable<Edge> {
    int node, cost;
    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
		
		// 우선순위 큐 (최소힙)
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
		    // times 배열을 인접 리스트 형태 그래프로 변환
        Map<Integer, List<Edge>> graph = new HashMap<>();
        
        // 1~n번 노드에 대해 빈 리스트 초기화
        for(int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        // times 배열 순회하면서 간선 정보 그래프에 추가
        // 시작 노드, 도착 노드, 가중치
        for(int[] time : times) {
            graph.get(time[0]).add(new Edge(time[1], time[2]));
        }
        
        // 다익스트라 알고리즘으로 탐색하여 최대값 반환
        return dijkstra(graph, k, n);
    }
    public int dijkstra(Map<Integer, List<Edge>> graph, int start, int n){
		    // 초기 설정
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n+1]; // 각 노드까지의 최단 거리를 저장할 배열 선언
        Arrays.fill(dist, INF); // 모든 거리의 초기값을 무한대로 초기화
				
				// 우선순위 큐 (최소힙)
        Queue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0)); // 시작 노드 큐에 추가
        dist[start] = 0; // 시작 노드 거리 초기값은 0
				
				// 다익스트라 수행
        while(!pq.isEmpty()) {
            Edge cur = pq.remove(); // 현재 노드
            
            // 이미 최소거리 구한 노드라면 통과
            if(dist[cur.node] < cur.cost) continue;
						
						// 현재 노드의 모든 인접 노드 탐색
            for(Edge next : graph.get(cur.node)) {
                int nextCost = dist[cur.node] + next.cost; // 새로운 경로의 가중치
                // 새로운 경로가 기존 경로보다 가중치 작다면
                if(nextCost < dist[next.node]) {
                    pq.add(new Edge(next.node, nextCost)); // 우선순위 큐에 추가
                    dist[next.node] = nextCost; // 최단 거리 갱신
                }
            }
        }
        // 최대 값 구하기
        int maxVal = 0; 
        for(int i = 1; i <= n; i++) {
            if(dist[i] == INF) return -1; // 갈 수 없는 노드는 -1 반환
            maxVal = Math.max(maxVal, dist[i]); // 각 노드까지의 최단 시간 중 최대값 갱신
        }
        return maxVal;
    }
}
```

💟 그래프 List에 저장 (정답 풀이 코드)

```java
import java.util.*;

class Edge implements Comparable<Edge> {
    int node, cost;
    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    // 우선순위 큐 (최소힙)
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 그래프 변환(인접리스트로 변환)
        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph.get(u).add(new Edge(v,w));
        }

        // 다익스트라 알고리즘
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);

        // 초기값 넣기
        Queue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(k, 0));
        dist[k] = 0;

        while(!pq.isEmpty()){
            // 방문
            Edge cur = pq.poll();
            
            // 이미 처리된 노드 건너뛰기
            if(dist[cur.node] < cur.cost) continue;
            
            // 예약
            for(Edge next : graph.get(cur.node)) {
                int nextDist = dist[cur.node] + next.cost; // cur.node 통해서 가는 비용
                if(dist[next.node] > nextDist) {
                    pq.offer(new Edge(next.node, nextDist));
                    dist[next.node] = nextDist;
                }
            }
        }
           // 최대 값 구하기
        int maxVal = 0; 
        for(int i = 1; i <= n; i++) {
            if(dist[i] == INF) return -1; // 갈 수 없는 노드는 -1 반환
            maxVal = Math.max(maxVal, dist[i]); // 각 노드까지의 최단 시간 중 최대값 갱신
        }
        return maxVal;
    }
}
```