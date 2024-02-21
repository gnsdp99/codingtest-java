import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/21
 * @link https://www.acmicpc.net/problem/13023
 * @keyword_solution  
 * - 각 노드는 0 ~ N-1번 (크기 N으로 초기화)
 * 
 * - 그래프를 완성하고 각 노드마다 DFS를 한다.
 * - 깊이 5이상 진행되면 존재하는 것이므로 종료한다.
 * 
 * @input
 * - 사람의 수 N [5, 2,000]
 * - 친구 관계의 수 M [1, 2,000]
 * - a b -> a와 b는 친구
 *  
 * @output
 * - 조건을 만족하는 친구 관계가 존재하면 1, 없으면 0을 출력.   
 * 
 * @time_complex  O(NM), 2,000 * 2,000 = 4,000,000
 * @perf 
 */

public class Main {

	static class Node {
		int num;
		Node next;
		Node (int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	
	static int N, M;
	static Node[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new Node[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a] = new Node(b, adjList[a]);
			adjList[b] = new Node(a, adjList[b]);
		}
		
		for (int v = 0; v < N; v++) {
			DFS(v, 1, (1 << v));
		}
		System.out.println(0);
	}
	
	static void DFS(int node, int cnt, int visitedBit) {
		if (cnt == 5) { // 조건 만족 시 바로 종료
			System.out.println(1);
			System.exit(0);
		}
		
		for (Node tmp = adjList[node]; tmp != null; tmp = tmp.next) {
			if ((visitedBit & (1 << tmp.num)) == 0) DFS(tmp.num, cnt + 1, visitedBit | (1 << tmp.num));
		}
	}
}