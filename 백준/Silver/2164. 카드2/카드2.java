import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author 김예훈 
 * @date 24/02/02
 * @link https://www.acmicpc.net/problem/2164
 * @keyword_solution  
 * - 1번 카드가 가장 위, N번 카드가 가장 아래에 놓여 있다.
 * 1. 제일 위에 있는 카드를 버린다.
 * 2. 제일 위에 있는 카드를 제일 아래로 옮긴다.
 * >> 큐
 
 * - 1, 3, ..., 2n-1은 무조건 버려짐.
 * - N이 홀수이면, 한 바퀴 돌고 난 후 4, ..., N - 1, 2 가 됨.
 * - N이 짝수이면, 한 바퀴 돌고 난 후 2, ..., N이 됨.  
 * @input
 * N [1, 500_000] 
 * @output   
 * 남는 카드 번호 출력.
 * @time_complex O(N)
 * @perf 23,848kb, 120ms
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new ArrayDeque<>();
		
		if (N == 1) {
			System.out.println(1);
			return;
		}
		
		int start = N % 2 == 0 ? 2 : 4;
		for (int i = start; i <= N; i+=2) {				
			queue.offer(i);
		}
		if (N % 2 == 1) queue.offer(2);
		
		int size = queue.size();
		while (size >= 2) {
			queue.poll();
			queue.offer(queue.poll());
			size--;
		}
		System.out.println(queue.peek());
	}
}