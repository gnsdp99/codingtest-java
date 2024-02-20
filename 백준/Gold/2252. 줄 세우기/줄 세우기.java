/**
 * @author 김예훈 
 * @date 24/02/20
 * @link https://www.acmicpc.net/problem/2252
 * @keyword_solution  
 * - 선후 관계가 있는 정렬이므로 위상 정렬을 이용한다.
 * - 진입 차수를 계산하고 0인 노드를 큐에 삽입한다.
 * - 큐에서 꺼낸 노드와 연결된 다른 노드의 진입 차수를 1 감소시킨다.
 * - 진입 차수가 0이 된 노드는 큐에 삽입한다.
 * - 모든 노드의 진입 차수가 0이 될 때가지 반복한다.
 * - A B 관계에서 B의 진입 차수를 1 증가한다. (A가 먼저 나와야 하므로) 
 * 
 * @input
 * - 학생의 수 N [1, 32,000]
 * - 키 비교 횟수 M [1, 100,000] 
 * - A, B -> A가 B의 앞에 서야 함.
 * 
 * @output
 * - 줄 세운 결과를 출력한다. 답이 여러 개일 수 있다.
 *    
 * @time_complex  O(V + E)
 * @perf 
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, cnt;
	static int[] inDegrees;
	static Queue<Integer> queue = new ArrayDeque<>();
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegrees = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjList.get(A).add(B);
			inDegrees[B]++;
		}

		for (int i = 1; i <= N; i++) {
			if (inDegrees[i] == 0) queue.offer(i);
		}
		
		while (cnt < N) {
			cnt++;
			int cur = queue.poll();
			sb.append(cur).append(" ");
			for (int next: adjList.get(cur)) {
				if (--inDegrees[next] == 0) {
					queue.offer(next);
					
				}
			}
		}
		System.out.println(sb);
	}
}