import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> result = new ArrayList<>();
        
        for(int[] command : commands){
            int i = command[0] - 1;
            int j = command[1];
            int k = command[2] - 1;
            
            int[] arr = Arrays.copyOfRange(array, i, j);
            Arrays.sort(arr);
            result.add(arr[k]);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}