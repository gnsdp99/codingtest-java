import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {

    static class Node {
        int num, weight;
        Node next;

        Node(int num, int weight, Node next) {
            this.num = num;
            this.weight = weight;
            this.next = next;
        }
    }

    static class Pair implements Comparable<Pair> {
        int num, dist;

        Pair(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair p) {
            return Integer.compare(dist, p.dist);
        }
    }

    static int V, E, K;
    static int[] dist;
    static Node[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        adjList = new Node[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u] = new Node(v, w, adjList[u]);
        }

        find(K);

        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] != Integer.MAX_VALUE ? dist[i] : "INF").append("\n");
        }
        System.out.println(sb);
    }

    static void find(int src) {
        PriorityQueue<Pair> pq = new PriorityQueue();
        pq.offer(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (p.dist > dist[p.num]) {
                continue;
            }

            for (Node adj = adjList[p.num]; adj != null; adj = adj.next) {
                int sum = p.dist + adj.weight;
                if (dist[adj.num] > sum) {
                    dist[adj.num] = sum;
                    pq.offer(new Pair(adj.num, dist[adj.num]));
                }
            }
        }
    }
}