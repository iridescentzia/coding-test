import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        final int N = rooms.size();
        boolean[] visited = new boolean[N];

        DFS(rooms, visited, 0);

        for(boolean v : visited) {
            if(!v) return false;
        }

        return true;
    }

    private void DFS(List<List<Integer>> rooms, boolean[] visited, int n) {
        visited[n] = true;

        for(int key : rooms.get(n)) {
            if(!visited[key]) {
                DFS(rooms, visited, key);
            }
        }
    }
}