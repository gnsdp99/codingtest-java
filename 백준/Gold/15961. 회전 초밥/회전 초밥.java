import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈 
 * @date 24/02/23
 * @link https://www.acmicpc.net/problem/15961
 * @keyword_solution  
 * - 연속으로 k개를 먹었을 때 그 가짓수의 최댓값을 구하고, 그 가짓수에 쿠폰이 포함되어 있는지 아닌지 따져야 함
 * - 초밥은 원형으로 이루어져있으므로 마지막 인덱스에서 시작 인덱스로 넘어갈 수 있어야 함
 * - 조합 X (시간 초과)
 * - 슬라이딩 윈도우 기법으로 초밥의 가짓수를 확인한다.
 * - 배열에 포함된 각 초밥 종류의 수를 저장하고 왼쪽에서 빠질 때마다 -1, 오른쪽에서 추가될 때마다 +1 한다.
 * 
 * @input
 * - 초밥 접시의 수 N [2, 3,000,000]
 * - 초밥  가짓수 D [2, 3,000]
 * - 연속해서 먹어야 할 접시 수 [2, MIN(N, 3,000)]
 * - 쿠폰 번호 C [1, D]
 *  
 * @output
 * - 먹을 수 있는 초밥 "가짓수"의 최댓값 출력
 * 
 * @time_complex  O(N)
 * @perf 
 */

public class Main {

	static int N, D, K, C;
	static int[] numSelected; // 초밥 종류 별 선택된 횟수
	static int[] sushi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 초밥 접시 수
		D = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		K = Integer.parseInt(st.nextToken()); // 연속
		C = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		sushi = new int[N];
		numSelected = new int[D + 1];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int maxKind = 0; // 최대 가짓수
		// 0번부터 k개 선택으로 시작
		for (int i = 0; i < K; i++) {
			numSelected[sushi[i]]++;
			if (numSelected[sushi[i]] == 1) maxKind++;
		}
		
		if (N == K) { // 모두 같은 경우임
			System.out.println(numSelected[C] == 0 ? maxKind + 1 : maxKind);
			return;
		}
		
		// 시작이 N - 1번일 때 까지 반복
		int left = 1, right = K;
		int curKind = maxKind; 
		while (left < N) {
			if (right == N) right = 0; // 회전 
			
			numSelected[sushi[left - 1]]--; // 빠짐
			if (numSelected[sushi[left++ - 1]] == 0) curKind--; // 빠진 초밥 종류가 이제 없음
			
			numSelected[sushi[right]]++; // 추가
			if (numSelected[sushi[right++]] == 1) curKind++; // 추가된 초밥 종류가 새로운 것임
			
			if (curKind >= maxKind) { // 최댓값 갱신
				maxKind = numSelected[C] == 0 ? curKind + 1 : curKind;
			}
		}
		System.out.println(maxKind);
	}
}