import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 김예훈
 * @date 24/02/01
 * @link https://www.acmicpc.net/problem/2023
 * @keyword_solution
 * - N자리의 수 중 왼쪽부터 1자리, 2자리, ..., N자리가 모두 소수인 수를 찾는다.
 * 
 * 신기한 소수를 어떻게 판별?
 * 8! = 720 * 7 * 8 = 5040 * 8 = 40320
 * 순열 만들면서 가지치기
 * - 1자리부터 소수인 것만 재귀 호출.
 *   
 * @input
 * - N [1, 8] 
 * @output   
 * - N자리의 신기한 소수를 오름차순으로 한 줄에 하나씩 출력한다.
 * @time_complex  N!
 * @perf 
 */

public class Main {
	
	static int N;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		makePermutation(0, "");
		
		System.out.println(sb);
	}
	
	static void makePermutation(int nth, String selected) {
		if (nth >= N) {
			sb.append(selected).append("\n");
			return;
		}
		
		char ch = nth == 0 ? '2' : '0';
		while (ch <= '9') {
			int tmp = Integer.parseInt(selected + ch);
			if (isPrimeNumber(tmp)) {
				makePermutation(nth + 1, selected + ch);
			}
			ch++;
		}
	}
	
	static boolean isPrimeNumber(int number) {
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}