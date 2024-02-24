import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 구현
 * - 두 노드의 최소 공통 조상을 구한다.
 *
 * 입력
 * - 노드의 수 N [1, 100,000]
 * - 쿼리의 수 M [1, 100,000]
 *
 * 출력
 * - 두 노드의 최소 공통 조상 출력
 *
 * 시간복잡도 O(M logN)
 *
 * 결과 
 *
 * */

public class Main {

    static int N, M, MAX_HEIGHT;
    static int[] depth; // 각 노드의 깊이
    static boolean[] visited;
    static int[][] parent; // 각 노드의 2^k번째 부모 노드
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        MAX_HEIGHT = (int) (Math.log(N) / Math.log(2)); // 트리의 높이
        depth = new int[N + 1];
        visited = new boolean[N + 1];
        parent = new int[N + 1][MAX_HEIGHT + 1];
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList.get(A).add(B);
            adjList.get(B).add(A);
        }

        setParent();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int res = LCA(A, B);
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    static int LCA(int u, int v) {
        // 항상 u의 깊이가 더 깊도록 설정
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        int diff = depth[u] - depth[v];
        // 두 노드의 깊이 맞추기
        for (int k = 0; diff > 0; k++, diff >>= 1) {
            if ((diff & 1) == 1) {
                u = parent[u][k];
            }
        }

        if (u == v) return u; // 한 노드가 다른 노드의 부모임

        // 두 노드의 최소 공통 조상 찾기
        for (int k = MAX_HEIGHT; k >= 0; k--) {
            if (parent[u][k] != parent[v][k]) {
                u = parent[u][k];
                v = parent[v][k];
            }
        }

        // 최소 공통 조상의 자식 노드까지 이동함
        return parent[u][0];
    }

    static void setDepth(int cur) { // 각 노드의 깊이 및, 1칸 위 부모 설정
        visited[cur] = true;
        for (int adj : adjList.get(cur)) {
            if (!visited[adj]) {
                depth[adj] = depth[cur] + 1;
                parent[adj][0] = cur; // 자식 노드의 1칸 위 노드는 자기 자신
                setDepth(adj);
            }
        }
    }

    static void setParent() { // 각 노드의 2^k번째 부모 설정
        setDepth(1); // 루트(1번)부터 설정
        for (int k = 1; k <= MAX_HEIGHT; k++) {
            for (int node = 1; node <= N; node++) {
                parent[node][k] = parent[parent[node][k - 1]][k - 1];
            }
        }
    }
}