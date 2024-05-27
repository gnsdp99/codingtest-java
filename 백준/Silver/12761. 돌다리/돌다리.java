import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);
        Queue<Integer> queue = new ArrayDeque<>();

        int[] delta = {1, -1, A, B, -A, -B};

        visited[N] = 0;
        queue.offer(N);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (num == M) {
                break;
            }
            for (int d : delta) {
                if (isIn(num + d) && visited[num + d] == -1) {
                    visited[num + d] = visited[num] + 1;
                    queue.offer(num + d);
                }
            }
            for (int i = 2; i <= 3; i++) {
                int tmp = num * delta[i];
                if (isIn(tmp) && visited[tmp] == -1) {
                    visited[tmp] = visited[num] + 1;
                    queue.offer(tmp);
                }
            }
        }
        System.out.println(visited[M]);
    }

    static boolean isIn(int n) {
        return n >= 0 && n <= 100_000;
    }
}