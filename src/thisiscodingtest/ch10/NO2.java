package thisiscodingtest.ch10;

import java.util.Scanner;

public class NO2 {
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 학생 번호 (학생 수)
        int M = sc.nextInt();

        parent = new int[N+1]; // 각 학생 번호의 부모 노드를 저장할 배열

        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < M; i++) {
            int operation = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(operation == 0) {
                union(a,b);
            } else {
                if(find(a) == find(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }

            }
        }
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) {
            parent[b] = a;
        }else {
            parent[a] = b;
        }
    }

    private static int find(int x) {
        if(x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}
