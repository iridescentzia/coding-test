import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new ArrayDeque<>(); // 각 기능의 완료일 저장
        List<Integer> result = new ArrayList<>(); // 배포일 기준 배포될 기능 개수 저장
        
        // 각 기능의 완료일을 계산하여 큐에 저장
        for(int i = 0; i < progresses.length; i++) {
            int day = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            queue.offer(day);
        }
        
        // 큐에서 완료일을 꺼내면서 배포 가능한 기능 묶음 계산
        while(!queue.isEmpty()) {
            int deployment = queue.poll();
            int cnt = 1;
            
            while(!queue.isEmpty() && queue.peek() <= deployment) {
                queue.poll();
                cnt++;
            }
            result.add(cnt);
        }
        
        int[] answer = new int[result.size()];
        
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}