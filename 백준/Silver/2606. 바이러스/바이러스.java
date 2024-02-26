import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N + 1];
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjList.get(A).add(B);
			adjList.get(B).add(A);
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		visited[1] = true;
		int cnt = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int adj: adjList.get(cur)) {
				if (!visited[adj]) {
					queue.offer(adj);
					visited[adj] = true;
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}