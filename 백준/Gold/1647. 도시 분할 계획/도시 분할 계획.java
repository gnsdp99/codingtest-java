import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 구현
 * - 모든 집을 최소 비용으로 연결하는 MST 문제
 * - 두 개의 마을로 나누어 각 마을의 MST를 구해야 함
 * - 어떻게 마을을 분리할 지가 중요
 * - 어찌 됐든 비용이 가장 작은 간선 부터 N-2개 선택하면 됨
 * - 이때 연결되지 않은 집이 2개 이상 나오면 3개의 마을이 되기 때문에 N - 2번째 간선을 선택할 때 2개의 집이 연결되어 있지 않으면 패스한다.
 *
 * 입력
 * - 제한: 2초, 256MB
 * - 집의 개수 N [2, 100,000]
 * - 도로의 개수 M [1, 1,000,000]
 * - A B C -> A번 집과 B번 집의 도로의 비용은 C
 * - 비용 C [1, 1,000]
 *
 * 출력
 * - 두 마을의 각 집들을 모두 연결하는 최소 비용을 출력
 *
 * 시간복잡도
 * - O(E * logV) = 16,000,000
 * - 최대 비용 O(C * N) = 100,000,000 -> int
 *
 * 결과
 *
 * */
public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dst, cost;

        Edge(int src, int dst, int cost) {
            this.src = src;
            this.dst = dst;
            this.cost = cost;
        }

        public int compareTo(Edge e) {
            return Integer.compare(cost, e.cost);
        }
    }
    static int N, M;
    static PriorityQueue<Edge> edgePQ = new PriorityQueue<>();
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edgePQ.offer(new Edge(A, B, C));
        }

        int ans = 0;
        int cnt = 0;
        while (cnt < N - 2) { // N - 2개의 간선 연결
            Edge edge = edgePQ.poll();
            if (union(edge.src, edge.dst)) {
                ans += edge.cost;
                cnt++;
            }
        }
        System.out.println(ans);
    }

    static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    static boolean union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) return false;

        parents[root2] = v1;
        return true;
    }
}