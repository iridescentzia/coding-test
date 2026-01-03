import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int cnt = 0;
        
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                for(int k = j+1; k < nums.length; k++){
                    int sum = nums[i]+nums[j]+nums[k];
                    if(isPrime(sum)) cnt++;
                }
            }
        }
        return cnt;
    }
    private boolean isPrime(int num) {
        for(int l = 2; l < num; l++){
            if(num % l == 0) return false;
        }
        return true;
    }
}