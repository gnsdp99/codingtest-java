import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/01
 * @link https://www.acmicpc.net/problem/12891
 * @keyword_solution  
 * - DNA 문자열에서 조건을 만족하는 부분문자열을 만든다.
 * 조건: 부분문자열에 각 문자가 특정 개수 이상 등장해야 한다.
 * - 단, 순서가 존재한다.
 * >> 순열을 만들 경우 시간 초과.
 * 
 * 부분 문자열의 길이가 고정되어 있기 때문에 슬라이딩 윈도우 활용 가능.
 * 
 * @input 
 * - DNA 문자열 길이 |S|, 부분문자열 길이 |P| (1 <= P <= S <= 1_000_000)
 * - 문자별 최소 등장 횟수는 |S|보다 작거나 같은 음이 아닌 정수.
 * @output   
 * - 만들 수 있는 부분문자열의 종류의 "수"를 출력.
 * @time_complex 
 * @perf 
 */

public class Main {
	
	static int S, P, ans;
	static final int A = 0, C = 1, G = 2, T = 3;
	static int[] DNA;
	static int[] minCnt = new int[4]; // A C G T
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		DNA = new int[S];
		String line = br.readLine();
		for (int i = 0; i < S; i++) {
			char ch = line.charAt(i);
			DNA[i] = ch == 'A' ? A : ch == 'C' ? C : ch == 'G' ? G : T;
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			minCnt[i] = Integer.parseInt(st.nextToken());
		}

		// logic
		int ans = 0;
		for (int i = 0; i < P; i++) {
			minCnt[DNA[i]]--;
		}
		if (isSatiesfied()) ans++;
		
		int left = 1, right = P;
		while (right < S) {
			minCnt[DNA[right++]]--;
			minCnt[DNA[left++ - 1]]++;
			if (isSatiesfied()) ans++;
		}
		System.out.println(ans);
	}
	
	static boolean isSatiesfied() {
		for (int i = 0; i < 4; i++) {
			if (minCnt[i] > 0)
				return false;
		}
		return true;
	}
}