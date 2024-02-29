import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/29
 * @link https://www.acmicpc.net/problem/10971
 * @keyword_solution  
 * - 순열로 구현하면 O(N!)이다. 이 문제에서는 통과 가능.
 * - 이때 경로의 중복이 발생하기 때문에 DP를 활용한다.
 * - 우선 출발 도시는 임의로 하나 정한다. 어디서 출발하든 경로가 같으면 결과는 같음.
 * - D(i, j): 현재 i번 도시에 있고, j가 방문 도시 비트일 때, 현재 상태에서 외판원 순회에 필요한 최소 비용
 * - D[i][j] = min(D[i][j], tsp(next, {visited + next}) + W[i][next])
 * - 
 * 
 * @input 
 * - 도시의 수 N [2, 10]
 * - 각 행렬의 성분 [1, 1,000,000]
 * 
 * @output   
 * - 외판원 순회에 필요한 최소 비용 출력
 * 
 * @time_complex  O(N^2 * 2^N), dp 테이블 N * 2^N개를 채울 때 N번의 연산이 필요. 
 * @perf 
 */

public class Main {

	static int N;
	static int[][] W;
	static int[][] dp;
	static final int INF = Integer.MAX_VALUE / 2;
	static int FULL_BIT; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		FULL_BIT = (1 << N) - 1; // 모두 방문했을 때의 비트
		dp = new int[N][FULL_BIT];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		System.out.println(tsp(0, 0, 1));
	}
	
	static int tsp(int start, int cur, int visitedBit) {
		if (visitedBit == FULL_BIT) {
			return W[cur][start] != 0 ? W[cur][start] : INF; // 전부 방문하면 시작점으로 돌아감
		}

		if (dp[cur][visitedBit] == INF) { // 방문하지 않은 경로
			for (int next = 1; next < N; next++) {
				if (W[cur][next] == 0 || (visitedBit & (1 << next)) != 0) continue; // 경로가 없거나 방문한 도시임
				dp[cur][visitedBit] = Math.min(dp[cur][visitedBit], tsp(start, next, visitedBit | (1 << next)) + W[cur][next]);
			}			
		}
		
		return dp[cur][visitedBit];
	}
}