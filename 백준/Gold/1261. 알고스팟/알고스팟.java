import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        public int compareTo(Node n) {
            return Integer.compare(dist, n.dist);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N + 1][M + 1];
        int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = input.charAt(j - 1) - '0';
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 1, 0));
        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[1][1] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.r == N && node.c == M) {
                System.out.println(node.dist);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = node.r + delta[d][0];
                int nc = node.c + delta[d][1];
                if (nr >= 1 && nr <= N && nc >= 1 && nc <= M && !visited[nr][nc]) {
                    if (board[nr][nc] == 1) {
                        pq.offer(new Node(nr, nc, node.dist + 1));
                    } else {
                        pq.offer(new Node(nr, nc, node.dist));
                    }
                    visited[nr][nc] = true;
                }
            }
        }
    }
}