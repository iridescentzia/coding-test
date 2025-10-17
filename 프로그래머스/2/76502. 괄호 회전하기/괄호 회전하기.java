import java.util.*;

class Solution {
    public int solution(String s) {
        int cnt = 0;
        
        for(int i = 0; i < s.length(); i++) {
            String str = s.substring(i) + s.substring(0,i);
            if(isValid(str)) cnt++;
        }
        
        return cnt;
    }
    
    private boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        
        for(char c : str.toCharArray()) {
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else {
                if(stack.isEmpty()) return false;
                
                char t = stack.pop();
                
                if(c == ')' && t != '(' || c == ']' && t != '[' || c == '}' && t != '{') return false;
            }
        }
        return stack.isEmpty();
    }
}