package thisiscodingtest.ch13;

import java.util.*;

public class NO15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 노드 개수
        int m = sc.nextInt(); // 간선 개수
        int k = sc.nextInt(); // 최단 거리
        int x = sc.nextInt(); // 시작 노드

        // 그래프 초기화
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력 받아 노드 간 연결
        for(int i = 0; i < m; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            graph.get(A).add(B);
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        dist[x] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int next : graph.get(current)) {
                if(dist[next] == -1) {
                    dist[next] = dist[current] + 1;
                    queue.offer(next);
                }
            }
        }
        boolean flag = false;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == k) {
                System.out.println(i);
                flag = true;
            }
        }
        if(!flag) {
            System.out.println(-1);
        }
    }
}
