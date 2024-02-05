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
 * 2. 스택을 사용하여 수신기의 후보가 될 수 있는 탑들을 관리한다.
 * - 왼쪽 탑부터 하나씩 스택의 top과 비교하며 자신보다 큰 top이 나올 때까지 꺼낸다.
 * - 찾거나 모두 꺼내면 자기 자신을 넣는다. 
 * - 스택의 요소는 Integer 배열 [번호, 높이]이다.
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
 * @time_complex O(N)
 * - 오름차순인 경우 -> O(N)
 * - 내림차순인 경우 -> O(N)
 * @perf 
 */

public class Main {

	static int N, num = 1;
	static Stack<Integer[]> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
	
		// 첫 번째 탑
		stack.add(new Integer[] {num++, Integer.parseInt(st.nextToken())});
		sb.append(0).append(" ");
		
		while (st.hasMoreTokens()) {
			int tower = Integer.parseInt(st.nextToken());
			while (!stack.isEmpty() && stack.peek()[1] <= tower) {
				stack.pop();
			}
			if (stack.isEmpty()) sb.append(0);
			else sb.append(stack.peek()[0]);
			sb.append(" ");
			
			stack.add(new Integer[] {num++, tower});
		}
		System.out.println(sb);
	}
}