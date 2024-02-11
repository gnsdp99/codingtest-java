import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - N개의 도시(노드)와 M개의 버스(간선)가 존재할 때, A도시에서 B도시로가는 최소 비용과 경로를 구한다.

 * 구현
 * 다익스트라
 * - 출발 도시에서 다른 모든 도시로의 최소 경로를 구해야 한다.
 * - 각 도시별로 경로 리스트를 만들어 관리한다. N * N = 1,000,000
 * - 만약 A -> B로의 최소 비용이 갱신되면 B의 경로 리스트에는 A의 최소 경로 + B가 되는 것이다.

 * 입력
 * - 도시의 개수 N [1, 1,000]
 * - 버스의 개수 M [1, 100,000]
 * - 버스 정보는 [출발 도시, 도착 도시, 비용]으로 주어진다.
 * - 비용은 [0, 100,000]

 * 출력
 * - 출발 도시에서 도착 도시로 이동하는 최소 비용을 출력한다.
 * - 최소 비용에 포함된 도시의 개수를 출력한다.
 * - 그 도시들을 방문 순서대로 출력한다.

 * 시간복잡도
 * O(EVlogV) = 매 간선 탐색(E)마다 우선순위 큐 연산(logV) + 경로 저장(V)
 * 1e3 * 1e5 = 1e8

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
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>(); // 인덱스가 src 노드인 인접 리스트
    static int[] costs; // 최소 비용 배열
    static PriorityQueue<Node> priorityQueue = new PriorityQueue<>(); // 우선순위 큐
    static ArrayList<ArrayList<Integer>> paths = new ArrayList<>(); // 각 노드의 최소 경로 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // 초기화
        costs = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            costs[i] = INF;
            graph.add(new ArrayList<>());
            paths.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) { // 간선 입력
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(src).add(new Edge(dst, weight));
        }
        st = new StringTokenizer(br.readLine());
        SRC = Integer.parseInt(st.nextToken());
        DST = Integer.parseInt(st.nextToken());
        costs[SRC] = 0;

        // 다익스트라 알고리즘
        priorityQueue.offer(new Node(SRC, 0));
        paths.get(SRC).add(SRC);
        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            if (costs[cur.number] < cur.cost) continue;

            for (Edge e : graph.get(cur.number)) {
                if (costs[e.dst] > cur.cost + e.weight) {
                    costs[e.dst] = cur.cost + e.weight;
                    priorityQueue.offer(new Node(e.dst, costs[e.dst]));
                    copyPath(cur.number, e.dst);
                }
            }
        }
        sb.append(costs[DST]).append("\n").append(paths.get(DST).size()).append("\n");
        for (int n : paths.get(DST)) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }

    static void copyPath(int srcIdx, int dstIdx) {
        paths.get(dstIdx).clear();
        for (int n : paths.get(srcIdx)) {
            paths.get(dstIdx).add(n);
        }
        paths.get(dstIdx).add(dstIdx);
    }
}