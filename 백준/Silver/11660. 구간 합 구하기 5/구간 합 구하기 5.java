import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈 
 * @date 24/01/31
 * @link https://www.acmicpc.net/problem/11660
 * @keyword_solution  
 * - 2차원 배열의 누적합
 * arr[i][j]는 (0, 0) ~ (i, j)의 합이다.
 * arr[i][j]는 arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1] + val[i][j]와 같다.
 * arr[i-1][j]는 (0, 0) ~ (i - 1, j)
 * arr[i][j-1]는 (0, 0) ~ (i, j - 1)
 * arr[i-1][j-1]는 (0, 0) ~ (i - 1, j - 1)
 * 즉 두 개의 사각형 영역을 더하고 겹치는 부분은 빼야 한다.
 * 
 * (x1, y1) ~ (x2, y2)의 구간 합은 arr[x2][y2] - arr[x1 - 1][y2] - arr[x1][y1 -1] + arr[x1 -1][y1-1]이다.
 * (x1, y1) ~ (x2, y2)의 구간 합을 구하려면 (0, 0) ~ (x2, y2)의 합에서 (0, 0) ~ (x1-1, y2)과 (0, 0) ~ (x1, y2-1)의 합을 빼주어야 한다.
 * 그런데 두 합을 뺄 때 (0, 0) ~ (x1-1, y1-1)을 중복해서 빼기 때문에 한 번 더해주어야 한다.
 * 
 * x1 또는 y1이 1인 경우는 위 식을 사용하지 않고 간단하게 구할 수 있음.
 * 
 * @input
 * 1 <= N <= 1024 메모리 초과는 안나올듯. 
 * @output
 * (x1, y1) ~ (x2, y2)의 합을 출력.
 * 
 * @time_complex  O(N^2)
 * @perf 
 */

public class Main {

	static int N, M;
	
	static int[][] partialSum = new int[1025][1025];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// logic
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				partialSum[i][j] = partialSum[i - 1][j] + partialSum[i][j - 1] - partialSum[i - 1][j - 1] + Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = partialSum[x2][y2];
			
			if (x1 == 1 && y1 != 1) {
				sum -= partialSum[x2][y1 - 1];
			}
			if (y1 == 1) {
				sum -= partialSum[x1 - 1][y2];
			}  
			if (x1 != 1 && y1 != 1) {
				sum -= (partialSum[x1 - 1][y2] + partialSum[x2][y1 - 1] - partialSum[x1 - 1][y1 - 1]);
			}
			
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}