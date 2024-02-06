import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/05
 * @link https://www.acmicpc.net/problem/2493
 * @keyword_solution  
 * 문제 요약
 * - 일직선 위에 N개의 탑이 있다.
 * - 각 탑은 꼭대기에서 왼쪽으로 레이저를 쏜다.
 * - 각 레이저는 가장 먼저 만나는 탑에서 수신한다.
 * 
 * 구현
 * 1. 자신의 왼쪽 탑들을 모두 탐색하면서 가장 가깝고 높은 탑을 찾는다. O(N^2) -> X
 * 2. 스택을 사용하여 수신기의 후보가 될 수 있는 탑들을 관리한다. 141,408kb, 916ms -> X
 * 3. 
 * 
 * 
 * 주의
 * - 탑의 높이는 탑의 번호가 아니다.
 * - 탑의 번호는 왼쪽 탑부터 1번이다.
 * @input
 * - 탑의 수 N [1, 500,000]
 * - 탑의 높이 [1, 100,000,000]
 * 
 * @output   
 * - 왼쪽 탑부터 순서대로 자신이 발사한 레이저를 수신하는 탑의 번호를 출력한다.
 * - 번호 사이에 빈칸 하나를 둔다.
 * - 아무도 레이저를 수신하지 못하면 0을 출력한다.
 * @time_complex 
 * @perf  141,408kb, 916ms
 */
class Tower {
	int height;
	int num;
	
	Tower(int height, int num) {
		this.height = height;
		this.num = num;
	}
}

public class Main {

	static int N, num = 1;
	static Stack<Tower> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
	
		// 첫 번째 탑
		stack.add(new Tower(Integer.parseInt(st.nextToken()), num++));
		sb.append(0).append(" ");
		
		while (st.hasMoreTokens()) {
			int height = Integer.parseInt(st.nextToken());
			while (!stack.isEmpty() && stack.peek().height <= height) {
				stack.pop();
			}
			if (stack.isEmpty()) sb.append(0);
			else sb.append(stack.peek().num);
			sb.append(" ");

			stack.add(new Tower(height, num++));
		}
		System.out.println(sb);
	}
}