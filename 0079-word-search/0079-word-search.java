import java.util.*;

class Solution {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public boolean exist(char[][] board, String word) {
        int r = board.length;
        int c = board[0].length;
        boolean[][] visited = new boolean[r][c];

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(backtrack(board, word, i, j, 0, visited)) return true;
                }
            }
        }
        return false;
    }
    private boolean backtrack(char[][] board, String word, int r, int c, int index, boolean[][] visited) {
        if(index == word.length()) return true;

        if(r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != word.charAt(index) || visited[r][c]) return false;

        visited[r][c] = true;

        for(int d = 0; d < 4; d++) {
            int nr = r + dx[d];
            int nc = c + dy[d];

            if(backtrack(board, word, nr, nc, index + 1, visited)) return true;
        }

        visited[r][c] = false;
        return false;
    }
}