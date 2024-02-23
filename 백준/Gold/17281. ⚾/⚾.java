import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈 
 * @date 24/02/23
 * @link https://www.acmicpc.net/problem/17281
 * @keyword_solution  
 * - 1번 선수가 4번 타자인 채로 나머지 8명의 순서를 정해야 한다.
 * - 8!의 순열에 대해 최대 점수를 구해야 한다.
 * 
 * 비트마스킹
 * - 주자를 비트로 관리한다.
 * - 현재 주자 비트를 1100라고 하면  2, 3루에 있는 것이다.
 * - 이때 타자가 3루타를 치면 1000이 되어야 하고 2점을 획득한다.
 * - (1100 + 1) << 3 = 1101000  -> 기존 주자에 타자를 태워 3칸 이동한다.
 * - 점수 = (1101000 >> 4)의 비트 개수 = 2 -> 4번째 비트부터 획득한 점수이므로 4번째 비트 위로의 개수만 센다.
 * - 바뀐 주자 = 1101000 % 16 = 1000
 * 
 * @input 
 * - 이닝의 수 N [2, 50]
 * 
 * @output
 * - 얻을 수 있는 최대 점수 출력
 * 
 * @time_complex  O(N * 8!)
 * @perf 60,272kb, 504ms
 */
public class Main {

	static int INNING, ans;
	static int[][] innings;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		INNING = Integer.parseInt(br.readLine());
		innings = new int[INNING + 1][10];
		for (int i = 1; i <= INNING; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] sequence = new int[10];
		getPermutation(1, 0, sequence);
		System.out.println(ans);
	}
	
	static void getPermutation(int kth, int selectBit, int[] sequence) {
		if (kth == 10) {
			// 점수 계산
			startGame(sequence);
			return;
		}
		
		if (kth == 4) {
			sequence[kth] = 1;
			getPermutation(kth + 1, selectBit | (1 << 1), sequence);
			return;
		}
		for (int i = 2; i <= 9; i++) {
			if ((selectBit & (1 << i)) != 0) continue;
			sequence[kth] = i;
			getPermutation(kth + 1, selectBit | (1 << i), sequence);
		}
	}
	
	static void startGame(int[] sequence) {
		int curHitter = 1; // 몇 번 선수가 아니라 몇 번 타자 (sequence의 인덱스)
		int score = 0;
		
		for (int i = 1; i <= INNING; i++) {
			int numOut = 0;
			int base = 0; // 1 ~ 3루 주자 여부 (이닝마다 초기화해야 함)
			
			while (numOut < 3) {
				int res = innings[i][sequence[curHitter]];
				
				if (res == 0) numOut++; // 아웃
				else {
					base = (base + 1) << res;
					score += Integer.bitCount(base >> 4);
					base = base % 16;
				}
				
				curHitter = curHitter % 9 + 1;
			}
		}
		ans = ans < score ? score : ans;
	}
}