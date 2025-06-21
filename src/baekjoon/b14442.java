package baekjoon;

import java.util.*;

public class b14442 {
    static int n, m, k;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map = new int[1000][1000];
    static int[][][] dist = new int[1000][1000][11];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int result = BFS();
        System.out.println(result);
    }
    private static int BFS() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});
        dist[0][0][0] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], wall = current[2];

            if (x == n - 1 && y == m - 1) return dist[x][y][wall];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if(map[nx][ny] == 0 && dist[nx][ny][wall] == 0) {
                        dist[nx][ny][wall] = dist[x][y][wall] + 1;
                        queue.offer(new int[]{nx, ny, wall});
                    }
                    if(map[nx][ny] == 1 && wall < k && dist[nx][ny][wall + 1] == 0) {
                        dist[nx][ny][wall + 1] = dist[x][y][wall] + 1;
                        queue.offer(new int[]{nx, ny, wall + 1});
                    }
                }
            }
        }
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            if(dist[n-1][m-1][i] != 0) {
                minValue = Math.min(minValue, dist[n-1][m-1][i]);
            }
        }
        return minValue == Integer.MAX_VALUE ? -1 : minValue;
    }
}
