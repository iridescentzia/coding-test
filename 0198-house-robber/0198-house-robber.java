import java.util.*;

class Solution {
    public int rob(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();

        if(nums.length == 1) return nums[0];

        memo.put(0, nums[0]);
        memo.put(1, Math.max(nums[0], nums[1]));

        for(int i = 2; i < nums.length; i++){
            memo.put(i, Math.max(memo.get(i-2) + nums[i], memo.get(i-1)));
        }

        return memo.get(nums.length-1);
    }
}