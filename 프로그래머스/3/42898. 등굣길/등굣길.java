import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        int mod = 1000000007;
        
        for(int[] puddle : puddles){
            map[puddle[1] -1][puddle[0] - 1] = -1;
        }
        
        map[0][0] = 1;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if (i == 0 && j == 0) continue;

                if(map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }
                long sum = 0;
                if(i > 0) sum += map[i-1][j];
                if(j > 0) sum += map[i][j-1]; 
                
                map[i][j] = (int)(sum % mod);
            }
        }
        
        return map[n-1][m-1];
    }
}