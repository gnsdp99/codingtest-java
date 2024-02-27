import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/27
 * @link https://www.acmicpc.net/problem/1149
 * @keyword_solution 
 * - 완전탐색 시 O(2^N)이 된다.
 * - 최소 O(N^2)으로 해결해야 함.
 * - D(i, j): 1 ~ i번 집을 칠할 때 i번째 집을 j 색으로 칠한 최소비용
 * 
 * @input
 * - 집의 수 [2, 1,000]
 * - 비용 [1, 1,000]
 *  
 * @output
 * - 모든 집을 칠하는 비용의 최솟값 출력.
 *    
 * @time_complex  O(N)
 * @perf 
 */

public class Main {

	static int N;
	static final int numColor = 3;
	static int[][] minCost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		minCost = new int[N + 1][numColor];
		
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < numColor; j++) { // 1번 집 먼저 처리
			minCost[1][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < numColor; j++) {
				int cost = Integer.parseInt(st.nextToken());
				minCost[i][j] = Math.min(minCost[i - 1][(j + 1) % 3], minCost[i - 1][(j + 2) % 3]) + cost;
			}
		}
		
		int ans = minCost[N][0];
		for (int j = 1; j < numColor; j++) {
			if (ans > minCost[N][j]) ans = minCost[N][j];
		}
		System.out.println(ans);
	}
}