import java.util.*;

class Solution {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int n, m;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        if(maps[0][0] == 0) return -1;
    
        return BFS(maps, visited);
    }
    
    private int BFS(int[][] maps, boolean[][] visited){
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dist = new int[n][m];
        
        queue.offer(new int[]{0,0});
        visited[0][0] = true;
        dist[0][0] = 1;
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int x = current[0], y = current[1];
            
            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                    if(!visited[nx][ny] && maps[nx][ny] != 0){
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[x][y] + 1;
                        
                        if(nx == n-1 && ny == m-1) return dist[nx][ny];
                        
                        queue.offer(new int[]{nx,ny});
                    }
                }
            }
        }
        return -1;
    }
}