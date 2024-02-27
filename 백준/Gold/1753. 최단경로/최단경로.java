import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/27
 * @link https://www.acmicpc.net/problem/1753
 * @keyword_solution  
 * - 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 것이고 가중치가 양수이므로 다익스트라 알고리즘 사용
 * 
 * @input 
 * - 정점의 개수 V [1, 20,000]
 * - 간선의 개수 E [1, 300,000]
 * - 가중치 [1, 10]
 * 
 * @output
 * - 각 정점으로의 최단 경로값 출력.
 * - 시작점은 0으로 출력, 경로가 존재하지 않으면 INF 출력
 * 
 * @time_complex  O(E * logV)
 * @perf 
 */

public class Main {
	
	static class Node implements Comparable<Node> {
		int num, weight;
		Node(int dst, int weight) {
			this.num = dst;
			this.weight = weight;
		}
		public int compareTo(Node o) {
			return Integer.compare(weight, o.weight);
		}
	}

	static int V, E, K;
	static final int INF = Integer.MAX_VALUE;
	static int[] minDist;
	static ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
	static PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		minDist = new int[V + 1];
		Arrays.fill(minDist, INF);
		
		for (int i = 0; i <= V; i++) {
			adjList.add(new ArrayList<>());
		}
		
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList.get(u).add(new Node(v, w));
		}
		
		minDist[K] = 0; // 시작점
		priorityQueue.offer(new Node(K, 0));
		while (!priorityQueue.isEmpty()) {
			Node cur = priorityQueue.poll();
			
			if (minDist[cur.num] < cur.weight) continue;
			
			for (Node adj: adjList.get(cur.num)) {
				if (minDist[adj.num] > cur.weight + adj.weight) {
					minDist[adj.num] = cur.weight + adj.weight;
					priorityQueue.offer(new Node(adj.num, minDist[adj.num]));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			sb.append(minDist[i] != INF ? minDist[i] : "INF").append("\n");
		}
		System.out.println(sb);
	}
}