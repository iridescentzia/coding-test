import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount+1];
        Arrays.fill(memo, Integer.MAX_VALUE);

        return dp(memo, coins, amount);
    }
    private int dp(int[] memo, int[] coins, int amount){
        if(amount == 0) return 0;

        int result = Integer.MAX_VALUE;

        for(int coin : coins){
            if(amount - coin >= 0){
                if(memo[amount - coin] == Integer.MAX_VALUE){
                    memo[amount - coin] = dp(memo, coins, amount - coin);
                }
                if(memo[amount - coin] != -1){
                    result = Math.min(result, memo[amount - coin]);
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result + 1;
    }
}