package baekjoon;

import java.util.*;

public class b14502 {
    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map = new int[10][10];
    static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        // 연구소 지도 생성
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        List<int[]> zeroList = new ArrayList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 0){
                    zeroList.add(new int[]{i, j});
                }
            }
        }

        for(int i = 0; i < zeroList.size(); i++){
            for(int j = i + 1; j < zeroList.size(); j++){
                for(int k = j + 1; k < zeroList.size(); k++){
                    int[] w1 = zeroList.get(i);
                    int[] w2 = zeroList.get(j);
                    int[] w3 = zeroList.get(k);

                    map[w1[0]][w1[1]] = 1;
                    map[w2[0]][w2[1]] = 1;
                    map[w3[0]][w3[1]] = 1;

                    BFS();

                    map[w1[0]][w1[1]] = 0;
                    map[w2[0]][w2[1]] = 0;
                    map[w3[0]][w3[1]] = 0;
                }
            }
        }
        System.out.println(max);
    }
    private static void BFS(){
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 2){
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int r = current[0], c = current[1];

            for(int d = 0; d < 4; d++){
                int nr = r + dx[d];
                int nc = c + dy[d];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m){
                    if(!visited[nr][nc] && map[nr][nc] == 0){
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
        int safe = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 0 && !visited[i][j]){
                    safe++;
                }
            }
        }

        max = Math.max(safe, max);
    }
}
