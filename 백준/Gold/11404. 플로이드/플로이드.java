import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int num, dist;
        Node next;

        Node(int num, int dist, Node next) {
            this.num = num;
            this.dist = dist;
            this.next = next;
        }
    }

    static class Pair implements Comparable<Pair> {
        int num;
        long dist;

        Pair(int num, long dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair pair) {
            return Long.compare(dist, pair.dist);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Node[] adjList = new Node[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            adjList[A] = new Node(B, C, adjList[A]);
        }

        long INF = 100000L * 100000;
        boolean[] visited = new boolean[N + 1];
        long[] dists = new long[N + 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int start = 1; start <= N; start++) {
            Arrays.fill(visited, false);
            Arrays.fill(dists, INF);
            dists[start] = 0;
            pq.offer(new Pair(start, 0));

            while (!pq.isEmpty()) {

                Pair pair = pq.poll();

                if (dists[pair.num] < pair.dist) {
                    continue;
                }

                visited[pair.num] = true;

                for (Node adj = adjList[pair.num]; adj != null; adj = adj.next) {
                    if (!visited[adj.num] && dists[adj.num] > pair.dist + adj.dist) {
                        dists[adj.num] = pair.dist + adj.dist;
                        pq.offer(new Pair(adj.num, dists[adj.num]));
                    }
                }
            }
            for (int node = 1; node <= N; node++) {
                sb.append(dists[node] < INF ? dists[node] : 0).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}