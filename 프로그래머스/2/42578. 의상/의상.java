import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] cloth : clothes) {
            String category = cloth[1];
            map.put(category, map.getOrDefault(category, 0) + 1);
        }
        
        int result = 1;
        
        for(int cnt : map.values()) {
            result *= (cnt + 1);
        }
        
        return result - 1;
    }
}