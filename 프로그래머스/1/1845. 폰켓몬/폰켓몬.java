import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for(int n : nums) {
            set.add(n);
        }
        
        int maxPick = nums.length / 2;
        int kind = set.size();
        
        return Math.min(maxPick, kind);
    }
}