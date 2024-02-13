import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/13
 * @link https://www.acmicpc.net/problem/16435
 * @keyword_solution  
 * 문제 요약
 * - 스네이크버드는 과일 하나를 먹으면 길이가 1 늘어난다.
 * - 과일들은 지상으로부터 높이 h_i에 존재한다. (1 <= i <= N)
 * - 스네이크버드는 자신의 길이보다 작거나 같은 높이에 있는 과일만 먹을 수 있다.
 * - 스네이크버드의 최대 길이를 구한다.
 * 
 * 구현
 * - 과일을 높이 순으로 정렬한다.
 * - 그러면 과일의 높이를 하나씩 비교하면서 최대 높이를 구할 수 있게 된다.
 *
 * @input 
 * - 과일의 개수 N [1, 1,000]
 * - 스네이크버드의 초기 길이 L [1, 10,000]
 * - 과일의 높이 h [1, 10,000]
 * 
 * @output   
 * - 스네이크버드의 최대 길이를 출력한다.
 * 
 * @time_complex O(N)  
 * @perf 
 */

public class Main {

	static int N, L;
	static int[] heights;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		heights = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(heights); // 그리디 탐색을 위한 정렬
		
		int idx = 0;
		while (idx < N && L >= heights[idx++]) L++;
		
		System.out.println(L);
	}
}