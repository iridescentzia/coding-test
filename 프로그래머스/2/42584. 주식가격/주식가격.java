import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] result = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty()) {
                int top = stack.peek();
                
                if(prices[top] > prices[i]) {
                    result[top] = i - top;
                    stack.pop();
                }
                else break;
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            int i = stack.pop();
            result[i] = prices.length - i - 1;
        }
        return result;
    }
}