import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/21
 * @link https://www.acmicpc.net/problem/15683
 * @keyword_solution  
 * - 감시할 수 있는 영역의 최대 크기를 찾아야 함 (사각지대의 최소 크기)
 * - 따라서 주어진 CCTV들의 방향에 따른 조합을 구해야 함
 * - CCTV의 최대 개수는 8개, 한 CCTV의 방향은 최대 4개이므로 4^8 = 2^16 = 65,536개의 경우의 수가 존재한다.
 * - 각 경우에서 사각지대의 최소 크기를 구한다.
 * 
 * - 1번 - 4가지, 2번 - 2가지, 3번 - 4가지, 4번 - 4가지, 5번 - 1가지
 * 
 * - CCTV를 하나씩 선택할 때 방향별로 재귀를 생성한다.
 * 
 * 
 * @input 
 * - 세로 크기 N [1, 8]
 * - 가로 크기 M [1, 8]
 * - 0: 빈칸, 1 ~ 5: CCTV, 6: 벽
 * 
 * @output
 * - 사각 지대의 최소 크기를 출력.
 *    
 * @time_complex  O(2^2N) 
 * @perf 
 */

public class Main { 
	static class CCTV {
		int num; // CCTV 번호
		int x, y; // 좌표
		CCTV (int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, numCCTV, ans, numBlock;
	static int X = 0, Y = 1;
	static int[][] board;
	static ArrayList<CCTV> cctvs = new ArrayList<>();
	
	static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][][] dirs = { // CCTV별 방향
			{},
			{{0}, {1}, {2}, {3}},
			{{0, 2}, {1, 3}},
			{{0, 1}, {1, 2}, {2, 3}, {3, 0}},
			{{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},
			{{0, 1, 2, 3}}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		ans = Integer.MAX_VALUE;
		numBlock = N * M; // 벽, CCTV 제외 남은 칸의 수
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] != 0 && board[i][j] != 6) {
					cctvs.add(new CCTV(board[i][j], i, j));
				}
				if (board[i][j] != 0) numBlock--;
			}
		}
		numCCTV = cctvs.size();

		getCombination(0, 0, new int[numCCTV]);
		System.out.println(ans);
	}
	
	static void getCombination(int kth, int start, int[] selected) {
		if (kth == numCCTV) {
			int cnt = watch(selected);
			ans = ans > numBlock - cnt ? numBlock - cnt : ans;
			return;
		}
		
		for (int i = start; i < numCCTV; i++) {
			int cctvNum = cctvs.get(i).num;
			for (int d = 0; d < dirs[cctvNum].length; d++) {
				selected[i] = d;
				getCombination(kth + 1, i + 1, selected);
				selected[i] = -1;
			}
		}
	}
	
	static int watch(int[] selected) {
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;
		for (int i = 0; i < numCCTV; i++) {
			CCTV cctv = cctvs.get(i);
			int nth = selected[i];
			for (int j = 0; j < dirs[cctv.num][nth].length; j++) {
				int dir = dirs[cctv.num][nth][j];
				int nx = cctv.x + delta[dir][X];
				int ny = cctv.y + delta[dir][Y];
				while (isIn(nx, ny)) {
					if (board[nx][ny] == 6) break;
					if (board[nx][ny] == 0 && !visited[nx][ny]) {
						cnt++;
						visited[nx][ny] = true;
					}
					nx += delta[dir][X];
					ny += delta[dir][Y];
				}
			}
		}
		return cnt;
	}
	
	static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}