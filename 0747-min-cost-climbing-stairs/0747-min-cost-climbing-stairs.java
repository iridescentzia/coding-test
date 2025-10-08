import java.util.*;

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        Map<Integer, Integer> memo = new HashMap<>();

        memo.put(0, 0);
        memo.put(1, 0);

        for(int i = 2; i <= cost.length; i++){
            memo.put(i, Math.min(cost[i-2] + memo.get(i-2), cost[i-1] + memo.get(i-1)));
        }

        return memo.get(cost.length);
    }
}