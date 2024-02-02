import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/02 
 * @link https://www.acmicpc.net/problem/2531
 * @keyword_solution
 * 문제 요약  
 * - 같은 초밥이 둘 이상 존재할 수 있다.
 * - 벨트 위 k개의 연속된 초밥을 먹는다. 
 * - 가능한 한 많은 종류의 초밥을 먹어야 한다.
 * - 쿠폰에 쓰인 초밥은 연속되지 않아도 추가로 먹을 수 있다.
 * 
 * 풀이
 * - k개의 연속된 초밥 조합을 모두 구한다. >> 슬라이딩 윈도우
 * - 각 초밥이 몇 개 포함되었는지 나타내는 배열을 선언한다.
 * - 그 중에서 가장 긴 조합을 고르고, 만약 쿠폰에 쓰인 초밥이 없으면 1 추가한다.
 *  
 * @input
 * - N: 벨트에 놓인 접시의 수 [2, 30,000]
 * - d: 초밥의 가짓수 [2, 3,000]
 * - k: 연속해서 먹는 접시의 수 [2, min(N, 3,000)]
 * - c: 쿠폰 번호 [1, d] 
 * @output   
 * - 먹을 수 있는 초밥 가짓수의 최댓값.
 * @time_complex  O(N)
 * @perf 
 */

public class Main {

	static int N, d, k, c;
	
	static int[] sushi;
	
	static int[] numSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		numSelected = new int[d + 1];
		sushi = new int[N + k - 1];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < k - 1; i++) { // 마지막 원소도 슬라이딩 윈도우의 시작점이 되어야 함
			sushi[N + i] = sushi[i];
		}

		// 슬라이딩 윈도우
		int ans = 0;
		boolean isContain = false;
		for (int i = 0; i < k; i++) {
			if (sushi[i] == c) isContain = true;
			if (numSelected[sushi[i]] == 0) ans++;
			numSelected[sushi[i]]++;
		}
		
		int left = 1, right = k;
		int cnt = ans;
		while (right < N + k - 1) {
			numSelected[sushi[left - 1]]--;
			if (numSelected[sushi[left++ - 1]] == 0) cnt--;
			if (numSelected[sushi[right]] == 0) {
				cnt++;
			}
			numSelected[sushi[right++]]++;
			
			if (cnt > ans) {
				ans = cnt;
				if (numSelected[c] > 0) isContain = true;
			} else if (cnt == ans && numSelected[c] == 0) {
				isContain = false;
			}
		}
		
		if (!isContain) { // 쿠폰에 쓰인 초밥이 없으면 추가
			ans++;
		}
		
		System.out.println(ans);
	}
}