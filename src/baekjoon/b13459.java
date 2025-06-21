package baekjoon;

import java.util.*;

public class b13459 {
    static int n, m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] grid;

    static class State {
        int rx, ry, bx, by, cnt;

        State(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        grid = new char[n][m];
        int rx = 0, ry = 0, bx = 0, by = 0;
        for (int i = 0; i < n; i++) {
            grid[i] = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }
        System.out.println(BFS(rx,ry,bx,by));
    }

    private static int BFS(int rx, int ry, int bx, int by) {
        Queue<State> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[n][m][n][m];

        queue.offer(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            State curState = queue.poll();

            if(curState.cnt > 10) return 0;

            for(int dir = 0; dir < 4; dir++) {
                int[] nRed = move(curState.rx, curState.ry, dir);
                int[] nBlue = move(curState.bx, curState.by, dir);

                if(grid[nBlue[0]][nBlue[1]] == 'O') continue;
                if(grid[nRed[0]][nRed[1]] == 'O') return 1;

                if(nRed[0] == nBlue[0] && nRed[1] == nBlue[1]) {
                    int rDist = Math.abs(nRed[0] - curState.rx) + Math.abs(nRed[1] - curState.ry);
                    int bDist = Math.abs(nBlue[0] - curState.bx) + Math.abs(nBlue[1] - curState.by);
                    if(rDist > bDist) {
                        nRed[0] -= dx[dir];
                        nRed[1] -= dy[dir];
                    } else {
                        nBlue[0] -= dx[dir];
                        nBlue[1] -= dy[dir];
                    }
                }

                State nState = new State(nRed[0], nRed[1], nBlue[0], nBlue[1], curState.cnt + 1);
                if(!visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]]) {
                    visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]] = true;
                    queue.offer(nState);
                }
            }
        }
        return 0;
    }

    private static int[] move(int x, int y, int dir) {
        int nx = x, ny = y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            if(grid[nx][ny] == '#') {
                nx -= dx[dir];
                ny -= dy[dir];
                break;
            }
            if(grid[nx][ny] == 'O') {
                break;
            }
        }
        return new int[]{nx, ny};
    }
}
