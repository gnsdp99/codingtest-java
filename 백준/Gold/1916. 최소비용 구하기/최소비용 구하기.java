import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {

    static class Node implements Comparable<Node> {
        int num, weight;
        Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(weight, node.weight);
        }
    }

    static int N, M, src, dst;
    static int[] minDist;
    static ArrayList<ArrayList<Node>> adjList = new ArrayList();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        minDist = new int[N + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        for (int i = 0; i < N + 1; i++) {
            adjList.add(new ArrayList());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(u).add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        src = Integer.parseInt(st.nextToken());
        dst = Integer.parseInt(st.nextToken());

        find();

        System.out.println(minDist[dst]);
    }

    static void find() {
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.offer(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.num == dst) {
                return;
            }

            if (node.weight > minDist[node.num]) {
                continue;
            }

            for (Node adj : adjList.get(node.num)) {
                int sum = node.weight + adj.weight;
                if (minDist[adj.num] > sum) {
                    minDist[adj.num] = sum;
                    pq.offer(new Node(adj.num, minDist[adj.num]));
                }
            }
        }
    }
}