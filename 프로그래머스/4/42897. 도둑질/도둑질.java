import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        if(n == 1) return money[0];
        if(n == 2) return Math.max(money[0], money[1]);
        
        int d1 = dp(money, 0, n-2);
        int d2 = dp(money, 1, n-1);
        
        return Math.max(d1, d2);
    }
    private int dp(int[] money, int start, int end){
        int prev2 = 0;
        int prev1 = 0;
        
        for(int i = start; i <= end; i++) {
            int cur = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        
        return prev1;
    }
}