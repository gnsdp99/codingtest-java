import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - 한 도시에서 다른 모든 도시로 가는 최단 경로를 구한다.
 * - 음의 가중치가 존재할 수 있다.
 *
 * 구현
 * - 음의 가중치가 포함된 그래프에서 한 노드로부터 다른 모든 노드로의 최단 거리를 구하는 문제이다.
 * - 따라서 벨만 포드 알고리즘을 사용한다.
 *
 * 입력
 * - 노드의 수 N [1, 500]
 * - 간선의 수 M [1, 6,000]
 * - 가중치 C [-10,000, 10,000]
 * - 가중치가 -10,000일 때 N * M * C = 500 * 6,000 * (-10,000) = -3*1e10이므로 int형의 최대 범위인 -2*1e9를 벗어난다.
 * - 따라서 long 타입을 사용해야 underflow가 발생하지 않고 정확한 음수값을 얻을 수 있다.

 * 출력
 * - 음의 사이클이 존재한다면 -1을 출력한다.
 * - 그렇지 않으면 1번 도시에서 다른 모든 도시로의 최단 거리를 출력한다. (1번 노드는 제외)
 * - 경로가 없다면 -1을 출력한다.
 *
 * 시간복잡도
 * - O(NM) = 500 * 6,000 = 3,000,000
 *
 * 결과
 *
 * */

public class Main {
    static class Edge {
        int src, dst, weight;

        public Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }
    }
    static int N, M;
    static final int INF = Integer.MAX_VALUE;
    static Edge[] edges;// 그래프 인접 리스트
    static long[] dists; // 최단 거리 테이블

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        dists = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            dists[i] = INF;
        }
        dists[1] = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(A, B, C);
        }

        // Bellman-Ford
        boolean hasNegCycle = false;
        for (int i = 0; i < N; i++) {
            for (Edge e : edges) {
                if (dists[e.src] != INF && dists[e.dst] > dists[e.src] + e.weight) {
                    dists[e.dst] = dists[e.src] + e.weight;
                    if (i == N - 1) { // N번째 탐색에도 최단 거리가 갱신되면 음의 사이클이 존재하는 것.
                        hasNegCycle = true;
                        break;
                    };
                }
            }
        }
        if (hasNegCycle) {
            sb.append(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                sb.append(dists[i] != INF ? dists[i] : -1).append("\n");
            }
        }
        System.out.println(sb);
    }
}