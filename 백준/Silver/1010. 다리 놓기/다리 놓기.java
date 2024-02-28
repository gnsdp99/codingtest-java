import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/28
 * @link https://www.acmicpc.net/problem/1010
 * @keyword_solution  
 * - 완전탐색 시 30!이므로 안됨.
 * - 서쪽의 i번 사이트는 동쪽의 [i, M - N + i] 까지만 연결할 수 있음
 * - D(i, j): 서쪽의 i번 사이트를 동쪽의 j번 사이트에 연결하는 경우의 수
 * - D[i][j] = D[i][j - 1] + D[i - 1][j - 1] (j <= M - N + i)
 * - D[i][i] = 1
 * - D[i][j] = 0 (j < i)
 * - D[i][j] = 0 (j > M - N + i)
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[N + 1][M + 1];
			for (int j = 1; j <= M - N + 1; j++) {
				dp[1][j] = j;
			}
			for (int i = 2; i <= N; i++) {
				dp[i][i] = 1;
				for (int j = i + 1; j <= M - N + i; j++) {
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
				}
			}
			sb.append(dp[N][M]).append("\n");
		}
		System.out.println(sb);
	}
}