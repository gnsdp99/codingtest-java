import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈 
 * @date  24/01/31
 * @link https://www.acmicpc.net/problem/15650
 * @keyword_solution
 * - 조건에 맞는 길이가 M인 수열을 구한다.
 * 조건 1. N개의 숫자 중 중복이 없어야 한다.
 * 조건 2. 수열이 오름차순이어야 한다.
 * >> 조합
 * @input 
 * 1 <= M <= N <= 8
 * @output   
 * - 한 줄에 하나의 수열을 출력한다.
 * - 각 수열은 공백으로 구분한다.
 * @time_complex  O(N^2)
 * @perf 
 */

public class Main {

	static int N, M;
	
	static int[] selected;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		selected = new int[M];
		
		makeCombination(0, 1);
		System.out.println(sb);
	}
	
	static void makeCombination(int nth, int start) {
		if (nth >= M) {
			for (int num: selected) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			selected[nth] = i;
			makeCombination(nth + 1, i + 1);
		}
	}
}