import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair p) {
            return Integer.compare(dist, p.dist);
        }
    }

    static int N, M;
    static int minDist = Integer.MAX_VALUE;
    static int minNode = -1;
    static Set<Integer> set = new HashSet<>();
    static int[][] minDistArr;
    static ArrayList<ArrayList<Pair>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        minDistArr = new int[set.size()][N + 1];
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            adjList.get(D).add(new Pair(E, L));
            adjList.get(E).add(new Pair(D, L));
        }

        int i = 0;
        for (int num : set) {
            dijkstra(num, i++);
        }
        int maxDist = Integer.MIN_VALUE;
        int minNode = Integer.MAX_VALUE;
        for (int j = 1; j <= N; j++) {
            int minDist = Integer.MAX_VALUE;
            for (int k = 0; k < minDistArr.length; k++) {
                if (minDist > minDistArr[k][j]) {
                    minDist = minDistArr[k][j];
                }
            }
            if (maxDist < minDist
            || (maxDist == minDist && minNode > j)) {
                maxDist = minDist;
                minNode = j;
            }
        }
        System.out.println(minNode);
    }

    static void dijkstra(int start, int kth) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (dist[p.node] < p.dist) {
                continue;
            }

            for (Pair adj : adjList.get(p.node)) {
                if (dist[adj.node] > p.dist + adj.dist) {
                    dist[adj.node] = p.dist + adj.dist;
                    pq.offer(new Pair(adj.node, dist[adj.node]));
                }
            }
        }

        minDistArr[kth] = dist;
    }
}