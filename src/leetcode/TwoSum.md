## 문제 파악

nums 배열에서 두 수를 골라 그 합이 target이 되는 두 인덱스를 반환한다.

## 접근 방법과 코드 구현

1. Brute-force
- 이중 for문을 사용하여 배열의 모든 쌍을 탐색한 뒤, 두 수의 합이 target과 같을 경우 인덱스를 반환한다.
- 이중 for문으로 배열의 모든 두 수 조합을 탐색하기 때문에 시간 복잡도는 O(n^2)으로 비효율적이다.

💟 Brute-force (직접 해결한 방식)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
```

1. HashMap (성능 개선)
- HashMap으로 배열을 한 번만 순회한다.
- 각 숫자를 순회하면서 target에서 현재 숫자를 뺀 값이 HashMap에 존재하는지 확인하고, 존재한다면 반환한다.
- 시간 복잡도를 O(n)으로 효율적이다.

💟 HashMap

```java

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int secondNum = target - nums[i];
            if(hashtable.containsKey(secondNum)) {
                return new int[]{i, hashtable.get(secondNum)};
            }
            hashtable.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
}
```

## 배우게 된 점

많이 접했던 스타일의 유형인데, 항상 이중 for문으로 Brute-force로만 접근했었다. 이후 해답에서 HashMap을 사용한 성능 개선 버전을 보게 되었고, 간단한 문제에도 시간 복잡도를 고려해볼 필요가 있다는 점을 느낄 수 있었다.
