import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main {

    static int N, K;
    static int[] delta = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int ans = find(N, K);
        System.out.println(ans);
    }

    static int find(int src, int dst) {
        int[] visited = new int[100_001];

        Queue<Integer> queue = new ArrayDeque();
        queue.offer(src);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == dst) {
                return visited[cur];
            }
            for (int d: delta) {
                int next = cur + d;
                if (next < 0 || next > 100_000) {
                    continue;
                }
                if (visited[next] == 0) {
                    queue.offer(next);
                    visited[next] = visited[cur] + 1;
                }
            }
            int next = cur << 1;
            if (next > 100_000) {
                continue;
            }
            if (visited[next] == 0) {
                queue.offer(next);
                visited[next] = visited[cur] + 1;
            }
        }
        return 0;
    }
}