import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R, seq;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static int[] sequence;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        sequence = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList.get(i), (o1, o2) -> Integer.compare(o2, o1));
        }

        boolean[] visited = new boolean[N + 1];
        visited[R] = true;
        DFS(R, visited);

        for (int i = 1; i <= N; i++) {
            sb.append(sequence[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void DFS(int u , boolean[] visited) {

        sequence[u] = ++seq;

        for (int v: adjList.get(u)) {
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            DFS(v, visited);
        }
    }
}