import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> hashtable = new HashMap<>();
        
        for(String p : participant){
            hashtable.put(p, hashtable.getOrDefault(p,0) + 1);
        }
        
        for(String c : completion){
            hashtable.put(c, hashtable.get(c) - 1);
        }
        
        for(String key : hashtable.keySet()) {
            if(hashtable.get(key) == 1) return key;
        }
        
        return null;
    }
}