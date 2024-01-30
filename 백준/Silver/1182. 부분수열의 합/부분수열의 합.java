import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/01/30
 * @link https://www.acmicpc.net/problem/1182
 * @keyword_solution
 * - 부분수열은 꼭 연속할 필요 없음.  
 * 백트래킹 (부분집합)
 * - 길이 1 ~ N까지 조합을 모두 구하는데, 배열로 원소를 저장하지 않고 합만 구한다.
 * - 함수 정의: 남은 수열에서 숫자를 하나 뽑는다.
 * - 변경 요소: 뽑은 개수, 부분 수열의 합, 남은 수열
 * - 기저 조건: 부분 집합 구성 완료(뽑아야 할 수 모두 뽑음)
 * 
 * @input 
 * 정수의 개수 N (1 <= N <= 20) >> 20!은 시간초과 발생하므로 조심.
 * 목표 합 S (|S| <= 1_000_000)
 * 주어지는 수열의 정수의 절댓값은 100_000이하이다.
 * @output
 * 합이 S가 되는 부분수열의 개수 출력.
 * @time_complex  
 * @perf 
 */

public class Main {
	
	static int N, S, cnt, maxIdx;
	static int[] sequence;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		sequence = new int[N];
		for (int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		
		// logic
		for (int i = 1; i <= N; i++) {
			maxIdx = i;
			getSum(0, 0, 0);
		}
		System.out.println(cnt);
	}
	
	static void getSum(int sum, int curIdx, int nextIdx) {
		if (curIdx >= maxIdx) {
			if (sum == S) {
				cnt++;				
			}
			return;
		}
		
		for (int i = nextIdx; i < N; i++) {
			getSum(sum + sequence[i], curIdx + 1, i + 1);
		}
	}
}