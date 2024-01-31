import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/01/31
 * @link https://www.acmicpc.net/problem/11659
 * @keyword_solution  
 * - 2차원 배열 사용 (메모리 초과)
 * >> arr[i][j] -> i번째 요소 ~ j번째 요소까지의 구간 합
 * 
 * - 재귀함수로 구간별 합을 모두 구한다.
 * 함수 정의: 이전 요소를 포함하거나 포함하지 않았을 때를 나누어 합을 구한다.
 * 변경 요소: 다음 요소, 이전 요소, 구간 합
 * 기저 조건: 다음 요소가 없을 때
 * 
 * 
 * - 누적 합
 * 1차원 배열로 해결할 수 있다.
 * arr[i]는 0번 요소 ~ i번 요소까지의 구간 합
 * i번째 수 ~ j번째 수의 구간 합은 arr[j] - arr[i - 1].
 * arr[i-1]을 빼는 이유는 i번째 요소를 포함해야 하기 때문이다.
 * 
 * @input
 * - 수의 개수 N [1, 100_000]
 * - 합을 구해야 하는 횟수 M [1, 100_000]
 * >> 매번 새로 구간 합을 구하면 O(NM)이므로 시간 초과 발생.  
 * @output
 * - 주어진 구간에 대한 합을 출력.
 * @time_complex O(N)
 * @perf 
 */

public class Main {

	static int N, M;
	
	static int[] partialSum = new int[100_001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			partialSum[i] = partialSum[i - 1] + Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(partialSum[end] - partialSum[start - 1]).append("\n");
		}
		System.out.println(sb);
	}
}