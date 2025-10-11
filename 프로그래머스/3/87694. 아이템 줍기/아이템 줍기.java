import java.util.*;

class Solution {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    int MAX_SIZE = 102;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[MAX_SIZE][MAX_SIZE];
        
        // 좌표 2배 확장
        for(int[] r : rectangle) {
            for(int i = 0; i < 4; i++) {
                r[i] *= 2;
            }
        }
        
        // 각 사각형 합치기
        for(int[] r : rectangle) {
            for(int x = r[0]; x <= r[2]; x++) {
                for(int y = r[1]; y <= r[3]; y++) {
                    map[x][y] = 1;
                }
            }
        }
        
        // 테두리만 남기고 내부 제거하기
        for(int[] r : rectangle) {
            for(int x = r[0] + 1; x < r[2]; x++) {
                for(int y = r[1] + 1; y < r[3]; y++) {
                    map[x][y] = 0;
                }
            }
        }
        
        return BFS(map, characterX*2, characterY*2, itemX*2, itemY*2) / 2;
    }
    
    private int BFS(int[][] map, int sx, int sy, int tx, int ty){
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[MAX_SIZE][MAX_SIZE];
        int[][] dist = new int[MAX_SIZE][MAX_SIZE];
        
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        dist[sx][sy] = 0;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            
            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(nx >= 0 && nx < MAX_SIZE && ny >= 0 && ny < MAX_SIZE) {
                    if(!visited[nx][ny] && map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[x][y] + 1;
                        
                        if(nx == tx && ny == ty) return dist[nx][ny];
                        
                        queue.offer(new int[]{nx,ny});
                    }
                }
            }
        }
        return -1;
    }
}