import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/28
 * @link https://www.acmicpc.net/problem/1010
 * @keyword_solution  
 * - M개의 동쪽 사이트 중에서 N개의 사이트를 조합하는 문제임.
 * - 앞에서 선택한 사이트 아래로만 선택할 수 있기 때문에 조합임.
 * - 하지만 입력 크기 때문에 완전탐색은 불가능.
 * - 따라서 DP 사용.
 * 
 * @input 
 * - 서쪽 사이트 수 N [0, 30]
 * - 동쪽 사이트 수 M [0, 30]
 * 
 * @output
 * - 주어진 조건으로 다리를 지을 수 있는 경우의 수 출력
 *    
 * @time_complex O(NM)  
 * @perf
 */

public class Main {

	static int N, M;
	static int[][] dp = new int[31][31];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sb.append(getCombination(M, N)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int getCombination(int n, int k) {
		if (dp[n][k] > 0) return dp[n][k];
		
		return dp[n][k] = (k == 0 || n == k) ? 1 : getCombination(n - 1, k) + getCombination(n - 1, k - 1);
	}
}