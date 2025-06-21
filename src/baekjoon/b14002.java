package baekjoon;

import java.util.*;

public class b14002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] memo = new int[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        Arrays.fill(memo, 1);
        Arrays.fill(prev, -1);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(memo[i] < memo[j] + 1) {
                        memo[i] = memo[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }
        int maxVal = 0;
        int lastIdx = 0;
        for(int i = 0; i < n; i++) {
            if(memo[i] > maxVal) {
                maxVal = memo[i];
                lastIdx = i;
            }
        }
        System.out.println(maxVal);

        Stack<Integer> stack = new Stack<>();

        while(lastIdx != -1) {
            stack.push(nums[lastIdx]);
            lastIdx = prev[lastIdx];
        }

        while(!stack.isEmpty()) {
            System.out.println(stack.pop() + " ");
        }
    }
}
