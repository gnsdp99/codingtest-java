import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - N개의 도시(노드)와 M개의 버스(간선)이 있을 때, A도시에서 B도시로 가는 최소 비용을 구한다.
 *
 * 구현
 * 다익스트라
 * - 출발 도시에서 다른 모든 도시로의 최소비용을 구해야 특정 도시까지의 최소비용을 구할 수 있다.
 *
 * 입력
 * - 도시의 개수 N [1, 1,000]
 * - 버스의 개수 M [1, 100,000]
 * - 버스의 정보는 [출발 도시, 도착 도시, 비용]으로 주어진다.
 * - 버스의 비용은 [1, 100,000] >> 모든 비용이 100,000인 경우, 최대 100,000 * 1,000 = 1e8 (int형의 최댓값보단 작음)
 *
 * 출력
 * - 출발 도시에서 도착 도시까지 가는 최소 비용을 출력한다.
 *
 * 시간복잡도
 * O(MlogN) - 우선순위 큐 사용 시.
 *
 * 결과
 */
public class Main {

    static class Node implements Comparable<Node> {
        int number, cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static class Edge {
        int dst, weight;

        public Edge(int dst, int weight) {
            this.dst = dst;
            this.weight = weight;
        }
    }

    static int N, M, SRC, DST;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>(); // 인덱스가 src인 인접 리스트
    static int[] costs;
    static PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        costs = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            costs[i] = INF;
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        st = new StringTokenizer(br.readLine());
        SRC = Integer.parseInt(st.nextToken());
        DST = Integer.parseInt(st.nextToken());

        // 다익스트라 알고리즘
        costs[SRC] = 0; // 1번이 시작점이 아님을 주의
        priorityQueue.offer(new Node(SRC, 0));
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if (costs[node.number] < node.cost) continue; // 한 노드가 큐에 여러 번 들어온 경우 더 큰 비용은 무시

            for (Edge e : graph.get(node.number)) { // 최소 비용 갱신
                if (costs[e.dst] > node.cost + e.weight) {
                    costs[e.dst] = node.cost + e.weight;
                    priorityQueue.offer(new Node(e.dst, costs[e.dst]));
                }
            }
        }
        System.out.println(costs[DST]);
    }
}