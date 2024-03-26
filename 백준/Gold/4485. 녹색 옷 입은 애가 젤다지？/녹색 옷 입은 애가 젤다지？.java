import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static int[][] lose;
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = 1;
        while (true) {

            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            board = new int[N + 1][N + 1];
            lose = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 1; i <= N; i++) {
                Arrays.fill(lose[i], INF);
            }
            lose[1][1] = board[1][1];

            // DFS + Memoization
            BFS(1, 1);
            sb.append("Problem ").append(tc++).append(": ").append(lose[N][N]).append("\n");
        }
        System.out.println(sb);
    }

    static void BFS(int sr, int sc) {

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int r = tmp[0];
            int c = tmp[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + delta[d][0];
                int nc = c + delta[d][1];

                if (isIn(nr, nc)
                && (lose[nr][nc] > lose[r][c] + board[nr][nc])) {
                    lose[nr][nc] = lose[r][c] + board[nr][nc];
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }

    static boolean isIn(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= N;
    }
}