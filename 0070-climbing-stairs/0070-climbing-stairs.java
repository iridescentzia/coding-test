import java.util.*;

class Solution {
    public int climbStairs(int n) {
        Map<Integer, Integer> memo = new HashMap<>();

        memo.put(0,1);
        memo.put(1,1);

        for(int i = 2; i <= n; i++){
            memo.put(i, memo.get(i-2) + memo.get(i-1));
        }

        return memo.get(n);
    }
}