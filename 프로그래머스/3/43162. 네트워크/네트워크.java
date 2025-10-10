import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int cnt = 0;
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                BFS(i, computers, visited);
                cnt++;
            }
        }
        return cnt;
    }
    
    private void BFS(int start, int[][] computers, boolean[] visited){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        
        while(!queue.isEmpty()){
            int current = queue.poll();
            
            for(int next = 0; next < computers.length; next++){
                if(computers[current][next] == 1 && !visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}