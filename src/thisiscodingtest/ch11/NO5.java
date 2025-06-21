package thisiscodingtest.ch11;

import java.util.*;

public class NO5 {
    public static void main(String[] args) {
        int n, m ;
        int[] arr = new int[11]; // 공의 무게가 1부터 10까지 존재하기 때문에 반드시 11로 선언 -> 공의 무게가 무게 배열의 인덱스가 될 예정
        int result = 0;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        // arr 배열에 n개만큼 무게별(k) 공 개수 저장
        for (int i = 0; i < n; i++) {
            int k = sc.nextInt();
            arr[k] += 1; // 무게 k가 입력되면 카운팅(+1)
        }

        // 1부터 m까지 무게별 경우의 수 구하기
        for (int i = 1; i <= m; i++) {
            n -= arr[i]; // 남은 공의 개수
            result += arr[i] * n; // 무게별 경우의 수 합산
        }

        System.out.println(result);
    }
}
