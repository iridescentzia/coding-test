package baekjoon;

import java.util.*;

public class b1697 {
    static int n, k;
    static final int MAX_VALUE = 100000;
    static int[] dist = new int[MAX_VALUE + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        BFS();
    }
    private static void BFS() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        dist[n] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if(current == k) {
                System.out.println(dist[k]);
                return;
            }

            for(int next : new int[]{current - 1, current + 1, current * 2}) {
                if(next >= 0 && next <= MAX_VALUE && dist[next] == 0) {
                    dist[next] = dist[current] + 1;
                    queue.offer(next);
                }
            }
        }
    }
}
