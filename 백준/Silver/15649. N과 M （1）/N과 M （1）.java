import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/01/30
 * @link https://www.acmicpc.net/problem/15649
 * @keyword_solution
 * - 1부터 N까지의 자연수 중에서 "중복 없이" M개를 고른 수열을 모두 구한다.
 * - 길이 M의 중복 없는 수열 -> 순열
 * 
 * - 재귀로 순열 구하기
 * - 함수 정의: 숫자를 하나 선택한다.
 * - 변경 요소: 선택한 숫자 배열, 숫자를 선택할 자릿수
 * - 기저 조건: 모든 자리에 숫자를 선택했을 때 종료
 *   
 * @input 
 * - 1 <= M <= N <= 8 >> 최대 8!이므로 완탐 가능.
 * 
 * @output
 * - 한 줄에 하나의 수열을 "사전 순으로" 출력한다. >> 1 ~ N으로 이루어진 배열을 오름차순으로 선언한다.
 *
 * @time_complex O(N!)
 * @perf 
 */

public class Main {
	
	static int N, M;
	static int[] numbers;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = i + 1;
		}
		
		// logic
		makePermutation(new int[M], 0, new boolean[N]);
		System.out.println(sb);
	}
	
	static void makePermutation(int[] selected, int curIdx, boolean[] isSelected) {
		// curIdx: selected 배열의 숫자를 선택할 위치
		if (curIdx >= M) {
			for (int num: selected) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!isSelected[i]) {
				selected[curIdx] = numbers[i];				
				isSelected[i] = true;
				makePermutation(selected, curIdx + 1, isSelected);
				isSelected[i] = false;
			}
		}
	}
}