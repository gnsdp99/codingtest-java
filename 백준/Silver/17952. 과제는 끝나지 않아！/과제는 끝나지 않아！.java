import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Task {
		int grade;
		int restTime;
		public Task(int grade, int restTime) {
			this.grade = grade;
			this.restTime = restTime;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Stack<Task> stack = new Stack<>();
		int ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 0) {
				if (stack.isEmpty()) continue; 
				stack.peek().restTime--;
			} else {
				int A = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());				
				stack.push(new Task(A, T - 1));
			}
			if (stack.peek().restTime == 0) {
				ans += stack.pop().grade;
			}
		}
		System.out.println(ans);
	}
}