import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int r, c, dist;

        Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(dist, n.dist);
        }
    }

    static int N;
    static int[][] board;
    static int[][] dists;
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
            dists = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 1; i <= N; i++) {
                Arrays.fill(dists[i], INF);
            }
            dists[1][1] = board[1][1];

            dijkstra();
            sb.append("Problem ").append(tc++).append(": ").append(dists[N][N]).append("\n");
        }
        System.out.println(sb);
    }

    static void dijkstra() {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(1, 1, dists[1][1]));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int r = node.r;
            int c = node.c;
            int dist = node.dist;

            if (dists[r][c] < dist) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + delta[d][0];
                int nc = c + delta[d][1];

                if (isIn(nr, nc)
                && dists[r][c] + board[nr][nc] < dists[nr][nc]) {
                    dists[nr][nc] = dists[r][c] + board[nr][nc];
                    priorityQueue.offer(new Node(nr, nc, dists[nr][nc]));
                }
            }
        }
    }

    static boolean isIn(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= N;
    }
}