import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 구현
 * - 최단 거리가 아닌 경우의 수를 구하는 것이므로 DFS를 한다.
 * - 완전탐색 시 4방 탐색이므로 O(4^(N^2))가 된다.
 * - 따라서 DP를 이용해 중복 탐색을 줄여야 한다.
 * - (1, 1)에서 탐색을 시작해 경로가 존재하는 곳을 체크한다.
 * - 그러면 같은 곳을 방문했을 때 1을 더해주고 바로 탐색을 종료하면 중복 탐색을 하지 않아도 된다.
 *
 *
 * 입력
 * - 지도의 세로 크기 M [1, 500]
 * - 지도의 세로 크기 N [1, 500]
 * - 각 지점의 높이 [1, 10,000]
 *
 * 출력
 * - 이동 가능한 경로의 수 출력
 * - [1, 10억 이하]
 *
 * 시간복잡도 O(N^2)
 *
 * 결과
 */

public class Main {

    static int M, N;
    static int[][] board;
    static int[][] dp;
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static final int X = 0, Y = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[M + 1][N + 1];
        dp = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (M >= 2 && board[2][1] > board[1][1] && board[1][2] > board[1][1]) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= M; i++) { // 경로가 없는 경우를 0으로 표현하기 위한 초기화
            Arrays.fill(dp[i], -1);
        }

        System.out.println(DFS(1, 1));
    }

    static int DFS(int x, int y) {
        if (x == M && y == N) return 1;
        if (dp[x][y] > -1) return dp[x][y];

        dp[x][y] = 0; // 경로가 없는 경우는 0임
        for (int[] d : delta) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (isIn(nx, ny) && board[x][y] > board[nx][ny]) {
                dp[x][y] += DFS(nx, ny);
            }
        }
        return dp[x][y];
    }

    static boolean isIn(int x, int y) {
        return x >= 1 && x <= M && y >= 1 && y <= N;
    }
}