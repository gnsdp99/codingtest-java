import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Pos {
        int r, c, key, dist;

        Pos(int r, int c, int key, int dist) {
            this.r = r;
            this.c = c;
            this.key = key;
            this.dist = dist;
        }
    }

    static int N, M;
    static char[][] maze;
    static boolean[][][] visited;
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 열쇠가 6개이므로 6개 모두 가지면 비트로 111111 => 63, 0 ~ 63
        // 열쇠의 조합 별로 BFS 탐색
        // BFS 도중 새로운 열쇠를 찾으면 해당 조합의 BFS로 새로 진행

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new char[N][M];

        int sr = -1, sc = -1;
        for (int r = 0; r < N; r++) {
            String row = br.readLine();
            for (int c = 0; c < M; c++) {
                maze[r][c] = row.charAt(c);
                if (maze[r][c] == '0') {
                    sr = r;
                    sc = c;
                }
            }
        }

        visited = new boolean[N][M][64];

        int ans = BFS(sr, sc);
        System.out.println(ans);
    }

    static int BFS(int sr, int sc) {

        Queue<Pos> queue = new ArrayDeque<>();
        int key = 0;
        queue.offer(new Pos(sr, sc, key, 0));
        visited[sr][sc][key] = true;

        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            int r = pos.r;
            int c = pos.c;
            int k = pos.key;
            int dist = pos.dist;

            if (maze[r][c] == '1') {
                return dist;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + delta[d][0];
                int nc = c + delta[d][1];

                if (!isIn(nr, nc)) continue;
                if (visited[nr][nc][k]) continue;
                if (maze[nr][nc] == '#') continue;
                if ('A' <= maze[nr][nc] && maze[nr][nc] <= 'F' && (k & (1 << (maze[nr][nc] - 'A'))) == 0) continue;

                int tmp = k;
                if ('a' <= maze[nr][nc] && maze[nr][nc] <= 'f') {
                    tmp |= (1 << (maze[nr][nc] - 'a'));
                }

                visited[nr][nc][tmp] = true;
                queue.offer(new Pos(nr, nc, tmp, dist + 1));
            }
        }
        return -1;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}