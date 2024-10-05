import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int M, N;
    static int[][] board;
    static int[][] dp;

    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[M - 1][N - 1] = 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = find(0, 0);
        System.out.println(ans);
    }

    static int find(int r, int c) {
        if (dp[r][c] > -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];

            if (isNotIn(nr, nc) || board[r][c] <= board[nr][nc]) {
                continue;
            }

            dp[r][c] += find(nr, nc);
        }
        return dp[r][c];
    }

    static boolean isNotIn(int r, int c) {
        return r < 0 || r >= M || c < 0 || c >= N;
    }
}