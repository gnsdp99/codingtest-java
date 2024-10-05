import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, ans;
    static int[][] board;
    static int[][] path;
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        path = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j) - 'A';
            }
        }

        int visited = 1 << board[0][0];
        move(0, 0, visited, 1);

        System.out.println(ans);
    }

    static void move(int r, int c, int visited, int cnt) {
        if (cnt > ans) {
            ans = cnt;
        }

        path[r][c] = visited;

        for (int d = 0; d < 4; d++) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];

            if (isNotIn(nr, nc)) {
                continue;
            }

            int next = 1 << board[nr][nc];
            if ((visited & next) != 0) {
                continue;
            }
            if (((visited | next) == path[nr][nc])) {
                continue;
            }

            move(nr, nc, visited | next, cnt + 1);
        }
    }

    static boolean isNotIn(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}