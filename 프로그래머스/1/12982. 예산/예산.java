import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int result = 0;
        Arrays.sort(d);
        
        for(int i = 0; i < d.length; i++) {
            if(budget < 0) return result;
            if(budget - d[i] >= 0) {
                result++;
                budget -= d[i];
            }
        }
        return result;
    }
}