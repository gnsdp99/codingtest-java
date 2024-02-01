import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/01
 * @link https://www.acmicpc.net/problem/2961
 * @keyword_solution  
 * - 요리의 신맛은 재료의 신맛을 모두 곱한 값. (초기화 1로 해야 하는 것 주의)
 * - 요리의 쓴맛은 재료의 쓴맛을 모두 더한 값.
 * 
 * - 재료의 모든 부분 집합을 구한 후 쓴맛과 신맛의 차이를 구한다.
 * - 재료가 최대 10개이므로 부분 집합의 최대 갯수는 2^10 = 1,024개.
 * 1. 백트래킹
 * 가지치기 - 쓴맛과 신맛의 차이가 기존 최솟값보다 크거나 같아지면 가지치기 한다. XXXX
 * >> 가지치기 하면 안됨. 다른 재료를 추가했을 때 차이가 더 작아질 수 있음.
 * >> 신맛은 곱하기고 쓴맛은 더하기이기 때문에.
 * 매개변수 - 부분집합에 포함된 재료의 신맛의 곱, 쓴맛의 합, 자릿수   
 * 재료는 반드시 1개 이상 포함해야 하므로 공집합인 경우를 주의해야 한다.
 * 
 * 2. 비트마스킹
 * 
 * @input 
 * - 재료의 개수 N [1, 10]
 * - 요리의 신맛과 쓴맛은 모두 1_000_000_000보다 작은 양의 정수.
 * >> 쓴맛과 신맛의 차이의 최댓값은 999_999_999이다.
 * @output   
 * - 요리의 신맛과 쓴맛의 차이의 최솟값을 출력한다.
 * @time_complex O(2^N)  
 * @perf  11508kb, 76ms
 */

public class Main {
	
	static int N, ans = 999_999_999;
	static int[] sour = new int[10], bitter = new int[10];
	static boolean[] isSelected = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}
		
		// 방법 1 - 백트래킹
//		makePowerSet(0, 1, 0);
		
		// 방법 2 - 비트마스킹
        for (int i = 1; i < (1 << N); i++) { // 모든 부분 수열을 비트로 나타냄
            int S = 1, B = 0;
            for (int j = 0; j < N; j++) { // 각 부분 수열에 포함된 재료를 찾음
                if ((i & (1 << j)) == 0) continue;  // 존재하지 않음
                S *= sour[j];
                B+= bitter[j];
            }
            ans = Math.min(ans, Math.abs(S - B));
        }		
		System.out.println(ans);
	}
	
	static void makePowerSet(int nth, int mulSour, int sumBitter) {
		if (nth >= N) {
			if (sumBitter > 0) {
				int sub = Math.abs(mulSour - sumBitter);
				ans = sub < ans ? sub : ans;				
			}
			return;
		}
		
		isSelected[nth] = true;
		makePowerSet(nth + 1, mulSour * sour[nth], sumBitter + bitter[nth]);
		isSelected[nth] = false;
		makePowerSet(nth + 1, mulSour, sumBitter);
	}
}