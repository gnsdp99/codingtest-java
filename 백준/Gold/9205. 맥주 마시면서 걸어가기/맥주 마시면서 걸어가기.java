import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int[][] adjMatrix;
    static Pos[] positions;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            adjMatrix = new int[N + 2][N + 2];

            positions = new Pos[N + 2];

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                positions[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < N + 1; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    int dist = getDist(positions[i], positions[j]);
                    if (dist <= 1000) {
                        adjMatrix[i][j] = adjMatrix[j][i] = dist;
                    }
                }
            }

            // BFS로 갈 수 있는 지 확인
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[N + 2];
            queue.offer(0);
            visited[0] = true;

            String ans = "sad";
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == N + 1) {
                    ans = "happy";
                    break;
                }
                for (int adj = 0; adj < N + 2; adj++) {
                    if (adj == cur || visited[adj] || adjMatrix[cur][adj] == 0) {
                        continue;
                    }
                    queue.offer(adj);
                    visited[adj] = true;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static int getDist(Pos p1, Pos p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}