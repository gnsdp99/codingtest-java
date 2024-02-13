import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 김예훈
 * @date 24/02/13
 * @link https://www.acmicpc.net/problem/2839
 * @keyword_solution  
 * 문제 요약
 * - 사탕가게에 설탕을 정확히 N킬로그램 배달해야 한다.
 * - 설탕은 3킬로 or 5킬로그램 봉지에 들어 있다.
 * - 최대한 적은 봉지로 배달하려면 몇 봉지가 필요한 지 구한다.
 * 
 * 구현
 * 1. 그리디
 * - 봉지의 수를 최소로 하려면 5kg의 수를 최대한으로 해야 한다.
 * - 5kg를 최대 개수부터 최소 개수까지 만들면서 정확히 N을 만들 수 있는지 구한다.
 * 
 * @input 
 * - 설탕의 양 N [3, 5,000]
 * 
 * @output   
 * - 배달해야 하는 최소 봉지 수를 출력한다.
 * - 정확히 N킬로그램을 만들 수 없으면 -1을 출력한다.
 * 
 * @time_complex O(N)
 * 
 * @perf 
 */

public class Main {

	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		N = Integer.parseInt(br.readLine());
		
		// 5kg 봉지의 최대 갯수부터 하나씩 줄이면서 비교한다.
		int maxNumFive = N / 5; // 5kg 봉지의 최대 갯수
		
		for (int numFive = maxNumFive; numFive >= 0; numFive--) { // numFive: 5kg 봉지의 갯수
			if ((N - 5 * numFive) % 3 == 0) {
				int numThree = (N - 5 * numFive) / 3; // 3kg 봉지의 갯수
				ans = numFive + numThree;
				break;
			}
		}
		
		System.out.println(ans != 0 ? ans : -1);
	}
}