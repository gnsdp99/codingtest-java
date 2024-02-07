import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
 * - 가중치가 없기 때문에 현재 노드에서 최단 거리를 업데이트한 노드들은 바로 방문 처리해도 된다.
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
	static boolean[] visited; // 방문 여부 배열
	static Queue<int[]> queue = new ArrayDeque<>(); // 다익스트라 큐
	static Set<Integer> visitedNodes = new TreeSet<>(); // 최소 거리 K인 노드 집합
 
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
		
		// 다익스트라 
		queue.offer(new int[] {0, X});
		visited[X] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int dist = cur[0];
			int node = cur[1];
			
			if (dist == K) {
				visitedNodes.add(node);
				continue; // 최소 거리 K인 노드 다음 노드들은 K를 넘게 됨.
			}
			
			for (int next: graph.get(node)) {
				if (visited[next]) continue;
				visited[next] = true;
				queue.offer(new int[] {dist + 1, next});
			}
		}
		
		if (visitedNodes.size() == 0) sb.append(-1);
		else {
			for (Integer node: visitedNodes) {
				sb.append(node).append("\n");
			}
		}
		System.out.println(sb);
	}
}