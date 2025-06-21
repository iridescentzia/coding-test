package baekjoon;

import java.util.*;

public class b19238 {
    static int N, M, fuel;
    static int[][] map;
    static Taxi taxi;
    static List<Passenger> passengers = new ArrayList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        fuel = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int tx = sc.nextInt() - 1;
        int ty = sc.nextInt() - 1;
        taxi = new Taxi(tx, ty);

        for (int i = 0; i < M; i++) {
            int sx = sc.nextInt() - 1;
            int sy = sc.nextInt() - 1;
            int ex = sc.nextInt() - 1;
            int ey = sc.nextInt() - 1;
            passengers.add(new Passenger(sx, sy, ex, ey));
        }

        for (int i = 0; i < M; i++) {
            int idx = findPassenger();
            if (idx == -1) {
                System.out.println(-1);
                return;
            }

            Passenger passenger = passengers.get(idx);
            int toPassenger = BFS(taxi.x, taxi.y, passenger.startX, passenger.startY);
            int toEndpoint = BFS(passenger.startX, passenger.startY, passenger.endX, passenger.endY);

            if (toPassenger == -1 || toEndpoint == -1 || fuel < toPassenger + toEndpoint) {
                System.out.println(-1);
                return;
            }

            fuel -= toPassenger;
            fuel -= toEndpoint;
            fuel += toEndpoint * 2;

            taxi.x = passenger.endX;
            taxi.y = passenger.endY;

            passengers.remove(idx);
        }

        System.out.println(fuel);
    }

    private static int BFS(int startX, int startY, int endX, int endY) {
        boolean[][] visited = new boolean[N][N];
        Queue<Entry> queue = new ArrayDeque<>();
        queue.offer(new Entry(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Entry current = queue.poll();

            if (current.x == endX && current.y == endY) {
                return current.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new Entry(nx, ny, current.dist + 1));
                }
            }
        }
        return -1;
    }

    private static int findPassenger() {
        boolean[][] visited = new boolean[N][N];
        Queue<Entry> queue = new ArrayDeque<>();
        queue.offer(new Entry(taxi.x, taxi.y, 0));
        visited[taxi.x][taxi.y] = true;

        List<PassengerInfo> nextPassenger = new ArrayList<>();

        while (!queue.isEmpty()) {
            Entry current = queue.poll();

            for (int i = 0; i < passengers.size(); i++) {
                Passenger passenger = passengers.get(i);
                if (current.x == passenger.startX && current.y == passenger.startY) {
                    nextPassenger.add(new PassengerInfo(i, current.dist, current.x, current.y));
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new Entry(nx, ny, current.dist + 1));
                }
            }
        }

        if (nextPassenger.isEmpty()) return -1;

        nextPassenger.sort((a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist;
            if (a.x != b.x) return a.x - b.x;
            return a.y - b.y;
        });

        return nextPassenger.get(0).index;
    }

    static class Passenger {
        int startX, startY, endX, endY;

        public Passenger(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    static class PassengerInfo {
        int index, dist, x, y;

        public PassengerInfo(int index, int dist, int x, int y) {
            this.index = index;
            this.dist = dist;
            this.x = x;
            this.y = y;
        }
    }

    static class Taxi {
        int x, y;

        public Taxi(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Entry {
        int x, y, dist;

        public Entry(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
