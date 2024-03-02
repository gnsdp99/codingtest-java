import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main {
    static class Taxi {
        int x, y, fuel;
        Taxi(int x, int y, int fuel) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
        }
        void setPos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        void useFuel(int dist) {
            fuel -= dist;
        }
        void chargeFuel(int dist) {
            // 이동 거리 두 배만큼 충전
            fuel += dist * 2;
        }
        boolean findPassenger() {
            // 최단 거리 -> 작은 행 -> 작은 열의 승객을 찾는다.
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[N + 1][N + 1]; // 택시 위치로부터의 거리를 의미
            queue.offer(new int[]{x, y, 0});
            visited[x][y] = true;

            int minDist = MAX_DIST;
            int row = MAX_ROW;
            int col = MAX_COL;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int dist = cur[2];
                int key = N * x + y;
                if (dist > fuel || dist > minDist) break; // 더 이상 탐색할 필요 없음

                if (passengers.get(key) != null) {
                    // 승객 찾음
                    if (minDist == MAX_DIST) minDist = dist;
                    if (x < row || (x == row && y < col)) {
                        row = x;
                        col = y;
                    }
                    if (minDist == 0) break; // 가장 최단 거리를 찾음
                }

                for (int d = 0; d < deltaX.length; d++) {
                    int nx = x + deltaX[d];
                    int ny = y + deltaY[d];
                    if (!isIn(nx, ny) || !board[nx][ny] || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
            if (minDist == MAX_DIST) { // 승객을 못찾음
                return false;
            }

            // 승객의 출발지로 이동
            setPos(row, col);
            useFuel(minDist);
            return true;
        }
        boolean moveToDst() {
            // 승객을 목적지까지 이동시키는데 사용한 연료의 두 배 충전
            int key = N * x + y;
            int[] dst = passengers.get(key);
            int dx = dst[0]; // 도착 위치
            int dy = dst[1];

            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[N + 1][N + 1]; // 이동 거리
            queue.offer(new int[]{x, y, 0});
            visited[x][y] = true;

            int totalDist = 0;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int dist = cur[2];
                if (dist > fuel) { // 이동 불가능
                    return false;
                }
                if (x == dx && y == dy) { // 도착
                    totalDist = dist;
                    break;
                }
                for (int d = 0; d < deltaX.length; d++) {
                    int nx = x + deltaX[d];
                    int ny = y + deltaY[d];
                    if (!isIn(nx, ny) || !board[nx][ny] || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
            if (totalDist == 0) { // 이동 불가능
                return false;
            }
            useFuel(totalDist);
            chargeFuel(totalDist);
            passengers.remove(key); // 승객 삭제
            numPassenger -= 1;
            setPos(dx, dy);
            return true;
        }
    }
    static int N, M, numPassenger;
    static final int MAX_DIST = 40, MAX_ROW = 20, MAX_COL = 20;
    static boolean[][] board; // true: 빈 칸, false: 벽

    static int[] deltaX = {-1, 0, 1, 0};
    static int[] deltaY = {0, 1, 0, -1};
    static Map<Integer, int[]> passengers = new HashMap<>(); // key: 출발 위치의 1차원 좌표, value: 도착 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        board = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = !st.nextToken().equals("1");
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        Taxi taxi = new Taxi(x, y, fuel);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());
            int key = N * sx + sy;
            passengers.put(key, new int[]{dx, dy});
        }
        numPassenger = passengers.size();

        while (numPassenger > 0) {
            if (!taxi.findPassenger() || !taxi.moveToDst()) { // 실패
                System.out.println(-1);
                return;
            }
        }
        System.out.println(taxi.fuel);
    }

    static boolean isIn(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }
}