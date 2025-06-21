package baekjoon;

import java.util.*;

public class b15686 {
    static int n, m;
    static int[][] arr = new int[50][50];
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 1) {
                    houses.add(new int[]{i, j});
                }
                else if (arr[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }
        combination(0,0, new ArrayList<>());
        System.out.println(minValue);
    }

    private static void combination(int start, int depth, List<int[]> selected){
        if(depth == m){
            calcMinValue(selected);
            return;
        }

        for(int i = start; i < chickens.size(); i++){
            selected.add(chickens.get(i));
            combination(i+1, depth+1, selected);
            selected.remove(selected.size()-1);
        }
    }

    private static void calcMinValue(List<int[]> selected){
        int result = 0;

        for(int[] house : houses){
            int dist = Integer.MAX_VALUE;
            for(int[] chicken : selected){
                int d = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
                dist = Math.min(dist, d);
            }
            result += dist;
        }
        minValue = Math.min(result, minValue);
    }
}
