import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main { // Kruskal Algorithm

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
    static int V, E;
    static long ans = 0;
    static PriorityQueue<Edge> edgesPQ = new PriorityQueue<>();
    static Subset[] subsets; // 부분 집합 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        subsets = new Subset[V + 1];
        for (int i = 1; i <= V; i++) {
            subsets[i] = new Subset(i, 0); // Make-set 연산
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edgesPQ.offer(new Edge(A, B, C));
        }

        kruskal();
        System.out.println(ans);
    }

    static void kruskal() {
        int numSelected = 0; // MST 집합에 포함한 간선의 수
        while (numSelected < V - 1) {
            Edge edge = edgesPQ.poll();
            int rootSrc = find(edge.src);
            int rootDst = find(edge.dst);
            if (rootSrc != rootDst) { // 사이클을 생성하지 않으므로 합집합
                union(rootSrc, rootDst);
                ans += edge.weight;
                numSelected++;
            }
        }
    }

    static int find(int v) {
        int parent = subsets[v].parent;
        if (parent == v) return v;
        return subsets[v].parent = find(parent); // 경로 압축
    }

    static void union(int root1, int root2) {
        if (subsets[root1].rank == subsets[root2].rank) subsets[root1].rank++;
        if (subsets[root1].rank > subsets[root2].rank) subsets[root2].parent = root1;
        else subsets[root1].parent = root2;
    }
}