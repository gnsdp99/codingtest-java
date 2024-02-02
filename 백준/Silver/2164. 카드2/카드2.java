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
 * @input
 * N [1, 500_000] 
 * @output   
 * 남는 카드 번호 출력.
 * @time_complex O(N)
 * @perf 
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		int size = N;
		while (size >= 2) {
			queue.poll();
			queue.offer(queue.poll());
			size--;
		}
		System.out.println(queue.peek());
	}
}