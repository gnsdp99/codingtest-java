import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author 김예훈
 * @date 24/02/07
 * @link https://www.acmicpc.net/problem/11286
 * @keyword_solution  
 * 문제 요약
 * - 절댓값 힙이라는 자료구조를 구현한다.
 * 1. 배열에 정수 x(x != 0)을 넣는다.
 * 2. 배열에서 절댓값이 가장 작은 값을 출력하고, 배열에서 제거한다.
 * 절댓값이 가장 작은 값이 여러개일 때는 실제 값이 작은 값을 출력하고 제거한다.
 * 
 * 구현
 * - 우선순위 큐의 정렬 기준을 절댓값으로 한다. 같은 경우 실제 값을 비교한다.
 * 
 * @input 
 * - 연산의 개수 N [1, 100,000]
 * - x가 0이 아니면 배열에 x를 넣는 연산이다.
 * - x가 0이라면 배열에서 절댓값이 가장 작은 값을 출력하고 제거하는 연산이다.
 * 
 * @output   
 * - 입력에서 x가 0일 때 연산 결과를 출력한다.
 * - 배열이 비어있을 때는 0을 출력한다.
 * 
 * @time_complex O(N)
 * @perf 
 */

public class Main {

	static int N;
	static Queue<Integer> heap = new PriorityQueue<>((i1, i2) -> {
		if (Math.abs(i1) == Math.abs(i2)) return Integer.compare(i1, i2);
		return Integer.compare(Math.abs(i1), Math.abs(i2));
	});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x != 0) heap.offer(x);
			else {
				if (heap.isEmpty()) sb.append(0);
				else sb.append(heap.poll());
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}