import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Queue<Integer>> tree = new ArrayList<>();
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			tree.add(new ArrayDeque<>());
		}
		
		st = new StringTokenizer(br.readLine());
		int root = -1;
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p != -1) {
				tree.get(p).offer(i);
			} else {
				root = i;
			}
		}
		
		int remove = Integer.parseInt(br.readLine());
		DFS(root, remove);
		System.out.println(ans);
	}
	
	static void DFS(int cur, int remove) {
		
		if (cur == remove) {
			return;
		}
		
		Queue<Integer> queue = tree.get(cur);
		
		if (queue.isEmpty() || (queue.size() == 1 && queue.peek() == remove)) {
			++ans;
			return;
		}
		
		for (int num: queue) {
			
			DFS(num, remove);
		}
	}
}