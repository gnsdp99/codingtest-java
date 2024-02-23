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
 * @input 
 * - 이닝의 수 N [2, 50]
 * 
 * @output
 * - 얻을 수 있는 최대 점수 출력
 * 
 * @time_complex  O(N * 8!)
 * @perf 
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
			boolean[] base = new boolean[4]; // 1 ~ 3루 주자 여부 (이닝마다 초기화해야 함)
			
			while (numOut < 3) {
				int res = innings[i][sequence[curHitter]];
				
				if (res == 0) numOut++; // 아웃
				else score += 주자이동(base, res);
				
				curHitter = curHitter % 9 + 1;
			}
		}
		ans = ans < score ? score : ans;
	}
	
	static int 주자이동(boolean[] base, int res) {
		// res: 타격 결과
		int score = 0;
		for (int i = 3; i >= 1; i--) { // 3루부터 이동
			if (base[i]) {
				if (i + res >= 4) score += 1; // 홈 인
				else base[i + res] = true;
				base[i] = false; // 주자 진루
			}
		}
		if (res == 4) score += 1; // 홈런
		else base[res] = true; // 타자 진루
		return score;
	}
}