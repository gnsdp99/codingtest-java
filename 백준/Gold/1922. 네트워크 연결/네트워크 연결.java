import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - 모든 컴퓨터를 최소 비용으로 연결한다.
 *
 * 구현
 * - kruskal 알고리즘 또는 prim 알고리즘으로 MST를 만든다.
 *
 * 입력
 * - 컴퓨터의 수 N [1, 1,000]
 * - 컴퓨터를 연결한 선의 수 M [1, 100,000]
 * - 각 비용 [1, 10,000] , 최대 비용 10,000 * 999 = 9,990,000
 *
 * 출력
 * - 모든 컴퓨터를 연결하는 최소 비용을 출력한다.
 *
 * 시간복잡도
 * O(E logV)
 *
 * 결과
 * 47,816kb, 500ms
 * */
public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dst, weight;

        Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        public int compareTo(Edge e) {
            return Integer.compare(weight, e.weight);
        }
    }
    static class Subset {
        int parent, rank;

        Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
    static int N, M;
    static PriorityQueue<Edge> edgePQ = new PriorityQueue<>();
    static Subset[] subsets; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        subsets = new Subset[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edgePQ.offer(new Edge(A, B, C));
        }
        for (int i = 1; i <= N; i++) {
            subsets[i] = new Subset(i, 0);
        }

        int cnt = 0;
        int ans = 0;
        while (cnt < N - 1) { // N-1개의 간선 선택
            Edge edge = edgePQ.poll();
            int rootA = find(edge.src);
            int rootB = find(edge.dst);
            if (rootA == rootB) continue; // 사이클

            ans += edge.weight;
            union(rootA, rootB);
            cnt++;
        }
        System.out.println(ans);
    }

    static int find(int v) {
        int parent = subsets[v].parent;
        if (parent == v) return v;
        return subsets[v].parent = find(parent);
    }

    static void union(int v1, int v2) {
        if (subsets[v1].rank == subsets[v2].rank) subsets[v1].rank++;
        if (subsets[v1].rank > subsets[v2].rank) subsets[v2].parent = v1;
        else subsets[v1].parent = v2;
    }
}