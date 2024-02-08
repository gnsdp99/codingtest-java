import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 요약
 * - 방향 그래프의 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구한다.
 * - 모든 간선의 가중치는 10 이하의 자연수이다. (1 ~ 10)
 *
 * 구현
 * - 양의 가중치를 가진 그래프에서 하나의 시작점으로부터 다른 모든 노드까지의 최단 거리를 구하는 문제이다.
 * - 따라서 다익스트라 알고리즘을 구현한다.
 * - 우선순위 큐를 사용하여 최소 거리 노드부터 탐색할 수 있도록 한다.
 *
 * 입력
 * - 정점의 개수 V [1, 20,000]
 * - 간선의 개수 E [1, 300,000]
 * - 모든 정점은 1 ~ V의 번호가 붙어 있다.
 * - 입력 (u, v, w)는 'u -> v의 가중치가 w이다' 라는 뜻이다.
 *
 * 출력
 * - 시작점에서 각 노드로의 최단 경로 값을 순서대로 출력한다.
 * - 시작점 자신은 0으로 출력하고, 경로가 존재하지 않으면 INF를 출력한다.
 *
 * 시간복잡도
 * - 인접 행렬로 구현 시 O(V^2)
 * - 우선순위 큐로 구현 시 O(ElogV)
 *
 * 결과
 *
 * */

public class Main {
	
	static class Node {
	    int num;
	    int dist;

	    Node(int num, int dist) {
	        this.num = num;
	        this.dist = dist;
	    }
	}
	
    static final int MAX = Integer.MAX_VALUE;
    static int V, E, K;
    static int[] dists; // 시작점으로부터의 최소 거리 배열
    static Queue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> {
        return Integer.compare(n1.dist, n2.dist); // Node 클래스의 비교 기준을 설정해야 한다.
    }); // 우선순위 큐
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>(); // 연결 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 노드의 수
        E = Integer.parseInt(st.nextToken()); // 간선의 수
        K = Integer.parseInt(br.readLine()); // 시작 노드 번호

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 간선의 시작 노드
            int v = Integer.parseInt(st.nextToken()); // 간선의 도착 노드
            int w = Integer.parseInt(st.nextToken()); // 가중치
            graph.get(u).add(new Node(v, w));
        }

        // logic
        dijkstra();

        // 출력
        for (int i = 1; i <= V; i++) {
            sb.append(dists[i] != MAX ? dists[i] : "INF").append("\n");
        }
        System.out.println(sb);
    }

    static void dijkstra() { // 다익스트라 알고리즘
        dists = new int[V + 1]; // 노드 번호는 1 ~ V
        for (int i = 1; i <= V; i++) {
            dists[i] = MAX; // 최소 거리를 갱신하기 위해 정수 최댓값으로 초기화
        }
        dists[K] = 0; // 시작점으로부터 시작점은 0

        priorityQueue.offer(new Node(K, 0));
        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            int num = cur.num;
            int dist = cur.dist;
            if (dist > dists[num]) continue; // 같은 노드를 여러 번 넣은 경우 이미 최소 거리가 아닌 것은 탐색 X

            for (Node next: graph.get(num)) {
                if (dists[next.num] > dist + next.dist) { // 최소 거리 갱신
                    dists[next.num] = dist + next.dist;
                    priorityQueue.offer(new Node(next.num, dists[next.num])); // 최소 거리 갱신한 노드는 탐색의 대상
                }
            }
        }
    }
}