import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(w, edge.w);
        }
    }

    static int V, E;
    static int[] parent;
    static int[] rank;
    static PriorityQueue<Edge> pq = new PriorityQueue();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        rank = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(A, B, C));
        }

        int ans = connect();
        System.out.println(ans);
    }

    static int connect() {
        int sum = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int A = edge.u;
            int B = edge.v;
            if (union(A, B)) {
                sum += edge.w;
            }
        }
        return sum;
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return false;
        }

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else {
            parent[pb] = pa;

            if (rank[pa] == rank[pb]) {
                ++rank[pa];
            }
        }
        return true;
    }
}