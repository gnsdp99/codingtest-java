import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/21
 * @link https://www.acmicpc.net/problem/1759
 * @keyword_solution  
 * - 서로 다른, 최소 한 개의 모음과 최소 두 개의 자음
 * - 알파벳이 증가하는 순서
 * 
 * - C_C_L의 조합을 구한다.
 * - 사전 순이기 때문에 알파벳을 모두 정렬 후 조합을 구한다.
 * - 몇 개의 자음, 모음을 사용했는지 기록한다. 
 * 
 * @input 
 * - 암호를 이루는 문자의 종류 L [3, 15]
 * - 암호에 사용될 수 있는 문자의 종류 [3, 15]
 * 
 * @output
 * - 한 줄에 하나씩 사전순으로 가능한 암호를 모두 출력   
 * @time_complex O(C_C_L)  
 * @perf 12,196kb, 80ms
 */

public class Main {

	static int L, C;
	static char[] alphabets;
	static boolean[] isVow; 
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alphabets = new char[C];
		isVow = new boolean[26];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabets[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alphabets); // 사전식을 위한 정렬
		
		isVow['a' - 'a'] = isVow['e' - 'a'] = isVow['i' - 'a'] = isVow['o' - 'a'] = isVow['u' - 'a'] = true;

		getCombination(0, 0, 0, new char[L]);
		System.out.println(sb);
	}
	
	static void getCombination(int kth, int start, int numVow, char[] password) {
		// kth: 선택할 자릿수, start: 후보군 시작 인덱스, numVow: 선택한 모음 개수, password: 만든 암호
		if (kth == L) {
			if (kth - numVow >= 2 && numVow >= 1) sb.append(password).append("\n");
			return;
		}
		
		for (int i = start; i < C; i++) {
			password[kth] = alphabets[i];
			getCombination(kth + 1, i + 1, isVow[alphabets[i] - 'a'] ? numVow + 1 : numVow, password);
		}
	}
}