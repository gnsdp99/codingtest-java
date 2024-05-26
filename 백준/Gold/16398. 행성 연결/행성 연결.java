import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long ans;
    static int[][] graph; // 입력 행렬
    static int[] key; // 최소 가중치
    static boolean[] mstSet; // MST 포함 여부

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        key = new int[N];
        mstSet = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
            key[i] = Integer.MAX_VALUE;
        }

        key[0] = 0;
        int num = 0;
        while (num++ < N) {
            int min = Integer.MAX_VALUE;
            int u = -1;
            for (int i = 0; i < N; i++) {
                if (!mstSet[i] && key[i] < min) {
                    min = key[i];
                    u = i;
                }
            }
            mstSet[u] = true;
            ans += key[u];
            for (int v = 0; v < N; v++) {
                if (!mstSet[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                }
            }
        }
        System.out.println(ans);
    }
}