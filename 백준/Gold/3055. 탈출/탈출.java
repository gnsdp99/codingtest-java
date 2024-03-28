import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Pos {
        int r, c, dist;
        Pos(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    static int R, C;
    static char[][] board;

    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<Pos> queueWater = new ArrayDeque<>();
    static Queue<Pos> queueHedgehog = new ArrayDeque<>();
    static boolean[][] visitedWater;
    static boolean[][] visitedHegehog;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visitedWater = new boolean[R][C];
        visitedHegehog = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            String row = br.readLine();
            for (int c = 0; c < C; c++) {
                board[r][c] = row.charAt(c);
                if (board[r][c] == 'S') {
                    queueHedgehog.offer(new Pos(r, c, 0));
                    visitedHegehog[r][c] = true;
                } else if (board[r][c] == '*') {
                    queueWater.offer(new Pos(r, c, 0));
                    visitedWater[r][c] = true;
                }
            }
        }
        int ans = 0;

        // BFS
        // 고슴도치는 물이 채워진 곳으로 이동할 수 없지만, 물은 고슴도치가 있던 곳으로 갈 수 있음.
        // 따라서 고슴도치만의 visited 배열을 만들어어야 함.
        while (!queueHedgehog.isEmpty()) {

            // 물 먼저 확장
            int size = queueWater.size();
            for (int i = 0; i < size; i++) {
                Pos water = queueWater.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = water.r + delta[d][0];
                    int nc = water.c + delta[d][1];
                    if (!isIn(nr, nc) || visitedWater[nr][nc]
                    || board[nr][nc] == 'D' || board[nr][nc] == 'X') continue;
                    queueWater.offer(new Pos(nr, nc, 0));
                    visitedWater[nr][nc] = true;
                }
            }

            size = queueHedgehog.size();
            for (int i = 0; i < size; i++) {
                Pos hedgehog = queueHedgehog.poll();
                if (board[hedgehog.r][hedgehog.c] == 'D') {
                    ans = hedgehog.dist;
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = hedgehog.r + delta[d][0];
                    int nc = hedgehog.c + delta[d][1];
                    if (!isIn(nr, nc) || visitedWater[nr][nc] || visitedHegehog[nr][nc]
                            || board[nr][nc] == 'X') continue;
                    queueHedgehog.offer(new Pos(nr, nc, hedgehog.dist + 1));
                    visitedHegehog[nr][nc] = true;
                }
            }
        }
        System.out.println(ans != 0 ? ans : "KAKTUS");
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}