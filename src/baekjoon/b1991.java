package baekjoon;
import java.util.*;

public class b1991 {
    static int[][] tree = new int[26][2];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < N; i++) {
            String str = sc.nextLine();
            String[] se = str.split(" ");

            int root = se[0].charAt(0) - 'A';
            int left = se[1].equals(".") ? -1 : se[1].charAt(0) - 'A';
            int right = se[2].equals(".") ? -1 : se[2].charAt(0) - 'A';

            tree[root][0] = left;
            tree[root][1] = right;
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
        System.out.println();
    }
    private static void preOrder(int root){
        if(root == -1) return;
        System.out.print((char)(root + 'A'));
        preOrder(tree[root][0]);
        preOrder(tree[root][1]);
    }
    private static void inOrder(int root){
        if(root == -1) return;
        inOrder(tree[root][0]);
        System.out.print((char)(root + 'A'));
        inOrder(tree[root][1]);
    }
    private static void postOrder(int root){
        if(root == -1) return;
        postOrder(tree[root][0]);
        postOrder(tree[root][1]);
        System.out.print((char)(root + 'A'));
    }
}
