import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 김예훈
 * @date 24/02/27
 * @link https://www.acmicpc.net/problem/1463
 * @keyword_solution  
 * - 완전탐색할 경우 O(3^N)임.
 * - D(i): i를 1로 만드는 연산의 최소 횟수
 * - bottom up, D(1) = 0, D(2) = D(3) = 1임을 이용.
 *  
 * @input
 * - 수 N [1, 1,000,000]
 *  
 * @output
 * - 1을 만드는 연산의 최소 횟수 출력
 *    
 * @time_complex  O(N)
 * @perf 15,520kb, 100ms
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		
		if (N <= 2) {
			System.out.println(N - 1);
			return;
		}
		
		dp[2] = dp[3] = 1;
		
		for (int i = 4; i <= N; i++) {
			dp[i] = dp[i - 1];
			int tmp = i / 3;
			if (i % 3 == 0 && dp[i] > dp[tmp]) dp[i] = dp[tmp];
			tmp = i >> 1;
			if (i % 2 == 0 && dp[i] > dp[tmp]) dp[i] = dp[tmp];
			dp[i]++;
		}
		System.out.println(dp[N]);
	}
}