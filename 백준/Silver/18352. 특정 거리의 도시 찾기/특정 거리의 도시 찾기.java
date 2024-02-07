import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @author 김예훈
 * @date 24/02/07
 * @link https://www.acmicpc.net/problem/18352
 * @keyword_solution  
 * 문제 요약
 * - 모든 도로의 거리는 1이고 단방향이다.
 * - 도시 X로부터 출발해 도달할 수 있는 모든 도시 중, 최단 거리가 K인 모든 도시들의 번호를 구한다.
 * 
 * 구현
 * - 가중치가 없는 최단거리 알고리즘이므로 다익스트라 알고리즘을 사용한다.
 * - 우선순위 큐를 사용할 때 거리가 최소인 노드를 정렬해야 하므로 배열을 사용해야 한다.
 * - 이때 Comparator로 우선순위 기준을 만들어야 한다.
 * 
 * @input
 * - 도시의 개수 N [2, 300,000]
 * - 도로의 개수 M [1, 1,000,000]
 * - 최단 거리 K [1, 300,000]
 * - 입력 A B -> A에서 B로 단방향 연결됨
 *  
 * @output
 * - X에서 출발해 최단 거리 K로 도달할 수 있는 도시의 번호를 한 줄에 하나씩 오름차순으로 출력한다.
 * - 하나도 존재하지 않으면 -1을 출력한다.
 *    
 * @time_complex O(NM)
 *   
 * @perf 
 */

public class Main {

	static int N, M, K, X;
	static List<ArrayList<Integer>> graph = new ArrayList<>(); // 인접 리스트
	static int[] dists; // 거리 배열
	static boolean[] visited; // 방문 배열
	static Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
		if (o1[0] == o2[0]) return (o1[1] - o2[1]);
		else return (o1[0] - o2[0]);
	}); // 최단 거리 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[N + 1];
		dists = new int[N + 1];
		
		// 인접 리스트 생성
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
		}
		// 거리 배열 초기화
		for (int i = 1; i <= N; i++) {
			if (i != X) dists[i] = Integer.MAX_VALUE;
		}
		
		// 다익스트라 
		pq.offer(new int[] {0, X});
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			for (int next: graph.get(cur[1])) {
				if (visited[next]) continue;
				int newDist = cur[0] + 1;
				if (dists[next] > newDist) {
					dists[next] = newDist;
					pq.offer(new int[] {dists[next], next});
				}
				
			}
			visited[cur[1]] = true;
		}

		// 최단 거리 K인 노드 찾기
		for (int i = 1; i <= N; i++) {
			if (dists[i] == K) sb.append(i).append("\n");
		}
		if (sb.length() == 0) sb.append(-1);
		System.out.println(sb);
	}
}