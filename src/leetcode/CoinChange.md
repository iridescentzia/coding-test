## 문제 파악

동전 종류가 담긴 coins 배열을 이용하여, 같은 종류의 동전을 여러 번 사용할 수 있다는 조건 하에 목표 금액(amount)을 만들 때 필요한 가장 적은 동전 개수를 구하는 문제이다.

1. BFS 탐색
- 최단 거리 탐색 방식으로 amount에 도달할 수 있는 최소 동전 개수를 구한다.
- 각 상태는 현재까지 만든 금액과 사용한 동전 수로 구성된다.

    ```java
    int money = current[0]; // 현재 금액
    int cnt = current[1]; // 동전 개수
    ```

- Queue에는 [현재 금액, 동전 개수]로 저장하고, 이미 방문한 금액은 다시 Queue에 넣지 않는다. → 중복 탐색 방지
- BFS 로직상 가장 먼저 amount에 도달하는 경우가 최소 동전 개수이므로, 도달하자마자 바로 반환한다.

2. ⬆️ DP - Top-down (가장 큰 금액부터 더해보기)

## 코드 구현

💟 BFS

```java
import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {
		    // 금액이 0인 경우 동전 0개로 바로 만들 수 있어서 0 반환
        if(amount == 0) return 0;
				
				// LinkedList로 큐 생성(BFS 탐색) - [현재 금액, 동전 개수]
        Queue<int[]> queue = new LinkedList<>();
        // 방문 여부 저장할 배열 선언(인덱스 amount를 사용해야 하기때문에 amount+1로 배열 크기 설정)
        boolean[] visited = new boolean[amount + 1];
				
				// 큐에 시작값으로 금액 0원, 동전 0개 enqueue
        queue.offer(new int[]{0,0});
        visited[0] = true;
				
				// 큐가 empty 될 때까지 반복
        while(!queue.isEmpty()) {
            int[] current = queue.poll(); // 현재 상태 dequeue
            int money = current[0]; // 현재 금액
            int cnt = current[1]; // 동전 개수
						
						// coins 배열 순회
            for(int coin : coins) {
		            // 다음 상태 = 현재 금액 + 사용 가능한 동전
                int next = money + coin;
								
								// 목표 금액 amount 도달하면 현재 동전 개수 + 1
                if(next == amount) return cnt + 1;
                // 금액이 amount 이상이거나 이미 방문한 금액이면 pass
                if(next > amount || visited[next]) continue;

                visited[next] = true;
                // 큐에 다음 값 추가
                queue.offer(new int[]{next, cnt + 1});
            }
        }
        // 목표 금액 amount를 만들 수 없으면 -1 반환
        return -1;
    }
}
```

💟 DP 시간초과

```java
import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> memo = new HashMap<>();
        int cnt = Integer.MAX_VALUE;

        if(amount < 0) return -1;
        if(amount == 0) return 0;

        for(int coin : coins){
            int result = coinChange(coins, amount - coin);
            if(result >= 0) {
                cnt = Math.min(cnt, result + 1);
            }
        }

        memo.put(amount, cnt == Integer.MAX_VALUE? -1 : cnt);
        return memo.get(amount);
    }
}
```

💟 DP 정답

```java
import java.util.*;

class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        int cnt = Integer.MAX_VALUE;
				
        // 기저 조건
        if(amount < 0) return -1;
        if(amount == 0) return 0;
        
        if(memo.containsKey(amount)) return memo.get(amount);

        for(int coin : coins){
            int result = coinChange(coins, amount - coin);
            if(result >= 0) {
                cnt = Math.min(cnt, result + 1);
            }
        }

        memo.put(amount, cnt == Integer.MAX_VALUE? -1 : cnt);
        return memo.get(amount);
    }
}
```
